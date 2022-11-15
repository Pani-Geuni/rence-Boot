/**
 * @author 김예은
 */
$(function(){
    /*********************************/ 
    /******* 버튼 클릭 이벤트 *********/ 
    /*********************************/ 
    // 공용 알러트 창 닫기버튼
    $("#common-alert-btn").click(function(){
        if($(".common-alert-txt").text() == "회원탈퇴되었습니다."){
            location.href = "/rence/";
        }
        $(".popup-background:eq(1)").addClass("blind");
        $("#common-alert-popup").addClass("blind");
    });
    //회원 탈퇴 버튼 클릭
    $(".user-delete-btn").click(function(){
        $(".popup-background:eq(0)").removeClass("blind");
        $("#user-delete-confirm-popup").removeClass("blind");
    });


    /****** ********************* ******/
    /****** 메뉴에 따른 페이지 이동 ******/
    /****** ********************* ******/
    $("#reserve-menu").click(function(){
        location.href="/rence/reserve_list?time_point=now&user_no="+$.cookie("user_no");
    });
    $("#mileage-menu").click(function(){
        location.href="/rence/go_mileage?user_no="+$.cookie("user_no");
    });
    $("#review-menu").click(function(){
        location.href="/rence/review_list?user_no="+$.cookie("user_no");
    });
    $("#question-menu").click(function(){
        location.href="/rence/question_list?user_no="+$.cookie("user_no");
    });


    /****** ************************ ******/
    /****** 프로필 수정 링크 클릭 부분 ******/
    /****** ************************ ******/
    $(".img-update-txt").click(function(){
        $(".popup-background:eq(0)").removeClass("blind");
        $("#modify-img-section").removeClass("blind");
    });
    $("#modify-img-closeBtn").click(function(){
        $(".review-upload-value").removeClass("null-input-border");
        $(".review-upload-value").val("");
        $(".popup-background:eq(0)").addClass("blind");
        $("#modify-img-section").addClass("blind");
    });
    $(".review-upload-btn").click(function(){
        $(".file").click();
    });
    $(".file").on('change',function(){
        var fileName = $(".file").val();
        var fArr = fileName.split("\\");
    
        $(".review-upload-value").removeClass("null-input-border");
        $(".review-upload-value").val(fArr[fArr.length - 1]);
    });

    $("#modify-img-modifyBtn").submit(function(){
        // 파일 선택안하고 시도
        if($(".review-upload-value").val().length == 0){
            $(".review-upload-value").addClass("null-input-border");
            return false;
        }
    });

    /****** ***************** ******/
    /****** 비밀번호 변경 팝업 ******/
    /****** ***************** ******/
    // 비밀번호 변경 팝업 열기
    $(".pw-update-txt").click(function(){
        $(".popup-background:eq(0)").removeClass("blind");
        $("#modify-pw-section").removeClass("blind");
    });

    // INPUT 경고 테두리 없애기
    $(".modify-popup-input").click(function(){
        if($(this).hasClass("null-input-border")){
            $(this).removeClass("null-input-border");
        }
    });
    $(".modify-popup-input-short").click(function(){
        if($(this).hasClass("null-input-border")){
            $(this).removeClass("null-input-border");
        }
    });

    // 비밀번호 일치하는지 확인하기 버튼
    $("#check-now-pw").click(function(){
        if($("#modify-pw-now").val().trim().length > 0){
            if( $(".modify-error-txt:eq(0)").hasClass("blind")){
                $.ajax({
                    url : "/rence/check_now_pw",
                    type : "POST",
                    dataType : 'json',
                    data : {
                        user_no : $.cookie("user_no"),
                        user_pw : CryptoJS.SHA256($("#modify-pw-now").val().trim()).toString()
                    },
                    success : function(res) {
                        // 비밀번호 일치 성공
                        if(res.result == 1){
                            $("#check-now-pw").prop("check", true);
                            $("#modify-pw-now").attr("readonly", true);
                            $("#modify-pw-now").addClass("readOnly");
                        }else{
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("비밀번호가 일치하지않습니다.");
                        }
                    },
                    error : function() {
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                    }
                });
            }
        }else{
            $("#modify-pw-now").addClass("null-input-border");
        }
    });

    // 수정하기 버튼
    $("#modify-btn").click(function(){
        if($("#modify-pw-now").val().trim().length > 0 && $("#modify-pw-new").val().trim().length > 0 && $("#modify-pw-renew").val().trim().length > 0){
            if( $(".modify-error-txt:eq(0)").hasClass("blind") 
                && $(".modify-error-txt:eq(1)").hasClass("blind")
                && $(".modify-error-txt:eq(2)").hasClass("blind")
                && !$(".modify-popup-input-short").hasClass("null-input-border")
                && !$(".modify-popup-input:eq(0)").hasClass("null-input-border")
                && !$(".modify-popup-input:eq(1)").hasClass("null-input-border")){
                // 수정 로직 처리
                $.ajax({
                    url:"/rence/user_pw_updateOK",
                    type : "POST",
                    dataType : 'json',
                    data : {
                        user_no : $.cookie("user_no"),
                        user_pw : CryptoJS.SHA256($("#modify-pw-renew").val().trim()).toString()
                    },
                    success : function(res) {
                        // 비밀번호 변경 성공
                        if(res.result == 1){
                            //INPUT 초기화
                            $(".modify-popup-input").val("");
                            $(".modify-popup-input-short").val("");
                            
                            // 경고 테두리 초기화
                            $(".modify-popup-input-short").removeClass("null-input-border");
                            $(".modify-popup-input").removeClass("null-input-border");

                            // 성공 알림창
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("비밀번호가 변경되었습니다.");
                        }else{
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("예상치못한 오류로 비밀번호 변경에 실패하였습니다.");
                        }
                    },
                    error : function() {
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                    }
                });
            }
        }else{
            if($("#modify-pw-now").val().trim().length == 0){
                $("#modify-pw-now").addClass("null-input-border");
            }
            if($("#modify-pw-new").val().trim().length == 0){
                $("#modify-pw-new").addClass("null-input-border");
            }
            if($("#modify-pw-renew").val().trim().length == 0){
                $("#modify-pw-renew").addClass("null-input-border");
            }
        }
    });

    // 비밀번호 변경 팝업창 닫기 버튼
    $("#modify-close-btn").click(function(){
        //INPUT 초기화
        $(".modify-popup-input").val("");
        $(".modify-popup-input-short").val("");
        
        // 경고 테두리 초기화
        $(".modify-popup-input-short").removeClass("null-input-border");
        $(".modify-popup-input").removeClass("null-input-border");

        // 에러 메세지 초기화
        $(".modify-error-txt").addClass("blind");

        // 팝업 관련창 닫음
        $("#modify-pw-section").addClass("blind");
        $(".popup-background:eq(0)").addClass("blind");
    });


    // 인풋 조건들 확인
    $(".modify-popup-input").on('keydown keyup', function(){
        if($("#check-now-pw").prop("check")){
            if($(this).attr("id")=="modify-pw-new"){
                // 새 비밀번호가 비밀번호 생성 조건에 맞는지
                var password = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,10}$/;
                if(!password.test($(this).val().trim())){
                    $(".modify-error-txt:eq(1)").removeClass("blind");
                    $(".modify-error-txt:eq(1)").text("비밀번호 조건과 일치하지않습니다.");
                }else{
                    $(".modify-error-txt:eq(1)").addClass("blind");

                    // 새 비밀번호가 현재 비밀번호와 다른지
                    if($(this).val().trim() == $("#modify-pw-now").val().trim()){
                        $(".modify-error-txt:eq(1)").removeClass("blind");
                        $(".modify-error-txt:eq(1)").text("현재 비밀번호와 다른 비밀번호를 사용하세요.");
                    }else{
                        $(".modify-error-txt:eq(1)").addClass("blind");

                        if($("#modify-pw-renew").val().trim().length > 0){
                            if($(this).val().trim() != $("#modify-pw-renew").val().trim()){
                                $(".modify-error-txt:eq(2)").removeClass("blind");
                                $(".modify-error-txt:eq(2)").text("위 비밀번호와 일치하지않습니다.");
                            }else{
                                $(".modify-error-txt:eq(2)").addClass("blind");
                            }
                        }
                    }
                }
            }
            else if($(this).attr("id")=="modify-pw-renew"){
                if($(this).val().trim() != $("#modify-pw-new").val().trim()){
                    $(".modify-error-txt:eq(2)").removeClass("blind");
                    $(".modify-error-txt:eq(2)").text("위 비밀번호와 일치하지않습니다.");
                }else{
                    $(".modify-error-txt:eq(2)").addClass("blind");
                }
            }
        }
        else{
            $(".modify-popup-input").val("");
            $(".modify-error-txt:eq(1)").removeClass("blind");
            $(".modify-error-txt:eq(1)").text("현재 비밀번호 먼저 확인해주세요.");
        }
    });



    /***** ************ *****/
    /***** 회원 탈퇴 팝업 ****/
    /***** ************ *****/
    // 회원 탈퇴 컨펌창 닫기버튼
    $("#user-delete-popup-close").click(function(){
        $(".popup-background:eq(0)").addClass("blind");
        $("#user-delete-confirm-popup").addClass("blind");
    });

    // 회원 탈퇴 처리
    $("#user-delete").click(function(){
        //AJAX
        $.ajax({
            url:"/rence/secedeOK",
            type : "POST",
            dataType : 'json',
            data : {
                user_no : $.cookie("user_no")
            },
            success : function(res) {
                // 회원탈퇴 성공
                if(res.result == 1){
                    // 성공 알림창
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("회원탈퇴되었습니다.");
                }else{
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("예상치못한 오류로 회원탈퇴에 실패하였습니다.");
                }
            },
            error : function() {
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
            }            
        });
    });
});