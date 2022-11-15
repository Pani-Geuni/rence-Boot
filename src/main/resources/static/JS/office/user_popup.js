/**
 * @author 김예은
 */
$(function(){
    /**** **************************************** ****/
    /**** 인풋 창 클릭 시 NULL을 알렸던 CSS제거 처리 ****/
    /**** **************************************** ****/
    //로그인
    $(".login-popup-input").click(function(){
        if($(this).hasClass("null-input-border")){
            $(this).removeClass("null-input-border");
        }
    });

    //회원가입
    $(".join-popup-input-short").click(function(){
        if($(this).hasClass("null-input-border")){
            $(this).removeClass("null-input-border");
        }
    });
    $(".join-popup-input").click(function(){
        if($(this).hasClass("null-input-border")){
            $(this).removeClass("null-input-border");
        }
    });


    /*********************************/ 
    /******* 버튼 클릭 이벤트 *********/ 
    /*********************************/ 
    // 공용 알러트 창 닫기버튼
    $("#common-alert-btn").click(function(){
        $(".popup-background:eq(1)").addClass("blind");
        $("#common-alert-popup").addClass("blind");
    });

    //로그인 버튼 클릭
    $("#login-btn").click(function(){
        //로그인 시도
        if($("#login-id").val().trim().length > 0 && $("#login-pw").val().trim().length > 0){
            $.ajax({
                url : "/rence/user_loginOK",
                type : "POST",
                dataType : 'json',
                data : {
                    user_id : $("#login-id").val().trim(),
                    // user_pw : window.btoa($("#login-pw").val().trim())
                    user_pw : CryptoJS.SHA256($("#login-pw").val().trim()).toString()
                },
                success : function(res) {
                    // 로그인 성공
                    if(res.result == 1){
                        //INPUT 초기화
                        $("#login-id").val("");
                        $("#login-pw").val("");
    
                        $("#login-id").removeClass("null-input-border");
                        $("#login-pw").removeClass("null-input-border");
    
                        // 팝업 관련창 닫음
                        $("#login-section").addClass("blind");
                        $(".popup-background:eq(0)").addClass("blind");

                        location.href="/rence/";
                    }else{
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("로그인에 실패하였습니다.");
                    }
                },
                error : function() {
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                }
            });
        }
        // 로그인 실패
        else{
            if($("#login-id").val().trim().length == 0){
                console.log($("#login-id"));
                $("#login-id").addClass("null-input-border");
            }
            if($("#login-pw").val().trim().length == 0){
                $("#login-pw").addClass("null-input-border");
            }
        }
    });
    //아이디 찾기 버튼 클릭 이벤트
    $("#go-find-id").click(function(){
        $("#login-section").addClass("blind");
        $("#find-id-section").removeClass("blind");
    });
    //비밀번호 찾기 버튼 클릭 이벤트
    $("#go-find-pw").click(function(){
        $("#login-section").addClass("blind");
        $("#find-pw-section").removeClass("blind");
    });
    //창닫기 찾기 버튼 클릭 이벤트
    $(".login-close").click(function(){
        //INPUT 초기화
        $("#login-id").val("");
        $("#login-pw").val("");

        $("#login-id").removeClass("null-input-border");
        $("#login-pw").removeClass("null-input-border");

        // 팝업 관련창 닫음
        $("#login-section").addClass("blind");
        $(".popup-background:eq(0)").addClass("blind");
    });

    
    /*********** 아이디 찾기 팝업 ************/
    $("#find-id-close").click(function(){
        //INPUT 초기화
        $("#find-id-email").val("");

        // 경고 테두리 초기화
        $("#find-id-email").removeClass("null-input-border");

        // 팝업 관련창 닫음
        $("#find-id-section").addClass("blind");
        $(".popup-background:eq(0)").addClass("blind");
    });
    $("#find-id-btn").click(function(){
        if($("#find-id-email").val().trim().length > 0){
            $.ajax({
                url : "/rence/find_id",
                type : "POST",
                dataType : 'json',
                data : {
                    user_email : $("#find-id-email").val().trim()
                },
                success : function(res) {
                    // 아이디 찾기 성공
                    if(res.result == 1){
                        //INPUT 초기화
                        $("#find-id-email").val("");

                        $("#find-id-email").removeClass("null-input-border");

                        // 팝업 관련창 닫음
                        $("#find-id-section").addClass("blind");
                        $(".popup-background:eq(0)").addClass("blind");

                        // 성공 알림창
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("이메일로 아이디를 발송해드렸어요!");
                    }
                    // 아이디 찾기 실패
                    else{
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("이메일 발송에 실패하였습니다.");
                    }
                },
                error : function(error) {
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                }
            });
        }else{
            $("#find-id-email").addClass("null-input-border");
        }
    });
    
    $(".find-popup-input").click(function(){
        if($(this).hasClass("null-input-border")){
            $(this).removeClass("null-input-border");
        }
    });
    
    /*********** 비밀번호 찾기 팝업 ************/
    $("#find-pw-close").click(function(){
        // INPUT 초기화
        $(".find-popup-input").val("");

        // 경고 테두리 초기화
        $(".find-popup-input").removeClass("null-input-border");

        // 팝업 관련창 닫음
        $("#find-pw-section").addClass("blind");
        $(".popup-background:eq(0)").addClass("blind");
    });
    $("#find-pw-btn").click(function(){
        if($("#find-pw-email").val().trim().length > 0 && $("#find-pw-id").val().trim().length > 0){
            $.ajax({
                url : "/rence/find_pw",
                type : "POST",
                dataType : 'json',
                data : {
                    user_email : $("#find-pw-email").val().trim(),
                    user_id : $("#find-pw-id").val().trim()
                },
                success : function(res) {
                    // 비밀번호 찾기 성공
                    if(res.result == 1){
                        // INPUT 초기화
                        $(".find-popup-input").val("");

                        // 경고 테두리 초기화
                        $(".find-popup-input").removeClass("null-input-border");

                        // 팝업 관련창 닫음
                        $("#find-pw-section").addClass("blind");
                        $(".popup-background:eq(0)").addClass("blind");

                        // 성공 알림창
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("이메일로 비밀번호를 발송해드렸어요!");
                    }
                    // 아이디 찾기 실패
                    else{
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("이메일 발송에 실패하였습니다.");
                    }
                },
                error : function(error) {
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                }
            });
        }else{
            if($("#find-pw-email").val().trim().length == 0){
                $("#find-pw-email").addClass("null-input-border");
            }
            if($("#find-pw-id").val().trim().length == 0){
                $("#find-pw-id").addClass("null-input-border");
            }
        }
    });
    

    /*********** 회원가입 팝업 ************/
    //회원가입 버튼 클릭
    $("#join-btn").click(function(){
        if(
            $("#join-email").val().trim().length > 0
            && $("#join-email-code").val().trim().length > 0
            && $("#join-id").val().trim().length > 0
            && $("#join-pw").val().trim().length > 0
            && $("#join-re-pw").val().trim().length > 0
            && $("#join-name").val().trim().length > 0
            && $("#join-tel").val().trim().length > 0
            && $("#join-birth").val().trim().length > 0)
        {
            var arr = $(".warning-text");
            var tmp = true;
            for(var i = 0; i < arr.length; i++){
                if(!$(arr[i]).hasClass("blind")){
                    tmp = false;
                    break;
                }
            }

            if(tmp == true){
                if($("#check_email").prop("check") == true){
                    if($("#check_email-code").prop("check") == true){
                        if($("#check_id").prop("check") == true){
                            // 회원가입 로직 처리
                            $.ajax({
                                url : "/rence/joinOK",
                                type : "POST",
                                dataType : 'json',
                                data : {
                                    user_id : $("#join-id").val().trim(),
                                    user_pw : CryptoJS.SHA256($("#join-pw").val().trim()).toString(),
                                    user_email : $("#join-email").val().trim(),
                                    user_name : $("#join-name").val().trim(),
                                    user_tel : $("#join-tel").val().trim(),
                                    user_birth : $("#join-birth").val().trim()
                                },
                                success : function(res) {
                                    // 회원가입 성공
                                    if(res.result == 1){
                                        //INPUT 초기화
                                        $(".join-popup-input").val("");
                                        $(".join-popup-input-short").val("");
                                        $(".join-popup-input-short").removeClass("readOnly");
                                        $(".join-popup-input-short").attr("readonly", false);

                                        // 에러 메세지 초기화
                                        $(".warning-text").addClass("blind");

                                        // 경고 테두리 초기화
                                        $(".join-popup-input").removeClass("null-input-border");
                                        $(".join-popup-input-short").removeClass("null-input-border");

                                        // 버튼 초기화
                                        $("#check_id").prop("check", undefined);
                                        $("#check_id").val("중복확인");
                                        $("#check_email").prop("check", undefined);
                                        $("#check_email").val("인증하기");
                                        $("#check_email-code").prop("check", undefined);
                                        $("#check_email-code").val("확인");

                                        // 팝업 관련창 닫음
                                        $("#join-section").addClass("blind");
                                        $(".popup-background:eq(0)").addClass("blind");

                                        // 성공 알림창
                                        $(".popup-background:eq(1)").removeClass("blind");
                                        $("#common-alert-popup").removeClass("blind");
                                        $(".common-alert-txt").text("회원가입에 성공하였습니다.");
                                    }else{
                                        $(".popup-background:eq(1)").removeClass("blind");
                                        $("#common-alert-popup").removeClass("blind");
                                        $(".common-alert-txt").text("예상치못한 오류로 회원가입에 실패하였습니다.");
                                    }
                                },
                                error : function(error) {
                                    $(".popup-background:eq(1)").removeClass("blind");
                                    $("#common-alert-popup").removeClass("blind");
                                    $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                                }
                            })
                        }else{
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("아이디 중복체크를 완료하세요.");
                        }
                    }
                    else{
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("인증번호 확인을 완료하세요.");
                    }
                }else{
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("이메일 인증을 완료하세요.");
                }
            }

        }
        else{
            if($("#join-email").val().trim().length == 0){
                $("#join-email").addClass("null-input-border");
            }
            if($("#join-email-code").val().trim().length == 0){
                $("#join-email-code").addClass("null-input-border");
            }
            if($("#join-id").val().trim().length == 0){
                $("#join-id").addClass("null-input-border");
            }
            if($("#join-pw").val().trim().length == 0){
                $("#join-pw").addClass("null-input-border");
            }
            if($("#join-re-pw").val().trim().length == 0){
                $("#join-re-pw").addClass("null-input-border");
            }
            if($("#join-name").val().trim().length == 0){
                $("#join-name").addClass("null-input-border");
            }
            if($("#join-tel").val().trim().length == 0){
                $("#join-tel").addClass("null-input-border");
            }
            if($("#join-birth").val().trim().length == 0){
                $("#join-birth").addClass("null-input-border");
            }
        }
    });

    $(".join-popup-input").on('keydown keyup', function(){
        if($(this).attr("id")=="join-pw"){
            var password = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,10}$/;
            if(!password.test($(this).val().trim())){
                $(".warning-text:eq(3)").removeClass("blind");
                $(".warning-text:eq(3)").text("비밀번호 조건과 일치하지않습니다.");
            }else{
                $(".warning-text:eq(3)").addClass("blind");

                if($("#join-re-pw").val().trim().length > 0){
                    if($(this).val().trim() != $("#join-re-pw").val().trim()){
                        $(".warning-text:eq(4)").removeClass("blind");
                        $(".warning-text:eq(4)").text("위 비밀번호와 일치하지않습니다.");
                    }else{
                        $(".warning-text:eq(4)").addClass("blind");
                    }
                }
            }
        }else if($(this).attr("id")=="join-re-pw"){
            if($(this).val().trim() != $("#join-pw").val().trim()){
                $(".warning-text:eq(4)").removeClass("blind");
                $(".warning-text:eq(4)").text("위 비밀번호와 일치하지않습니다.");
            }else{
                $(".warning-text:eq(4)").addClass("blind");
            }
        }else if($(this).attr("id")=="join-tel"){
            var phoneReg = /^01(0|1[6-9])(\d{3,4})(\d{4})$/;
            if(!phoneReg.test($(this).val().trim())){
                $(".warning-text:eq(5)").removeClass("blind");
                $(".warning-text:eq(5)").text("전화번호 형식이 아닙니다.");
            }else{
                $(".warning-text:eq(5)").addClass("blind");
            }
        }
        if($(this).attr("id")=="join-birth"){
            var birthReg = /^[0-9]{8}$/;
            if(!birthReg.test($(this).val().trim())){
                $(".warning-text:eq(6)").removeClass("blind");
                $(".warning-text:eq(6)").text("지정된 생년월일 형식이 아닙니다.");
            }else{
                $(".warning-text:eq(6)").addClass("blind");
            }
        }
    });
    
    $(".join-popup-input-short").on('keydown keyup', function(){
        // 이메일 형식인지 확인
        if($(this).attr("id")=="join-email"){
            var email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            if(!email.test($(this).val().trim())){
                $(".warning-text:eq(0)").removeClass("blind");
                $(".warning-text:eq(0)").text("이메일 형식이 아닙니다.");
            }else{
                $(".warning-text:eq(0)").addClass("blind");
            }
        }
        // 아이디 형식에 맞는지 확인
        else if($(this).attr("id")=="join-id"){
            var regExp = /^[a-z]+[a-z0-9]{4,5}$/g;            
            if(!regExp.test($(this).val().trim())){
                $(".warning-text:eq(2)").removeClass("blind");
                $(".warning-text:eq(2)").text("아이디 형식이 아닙니다.");
            }else{
                $(".warning-text:eq(2)").addClass("blind");
            }
        }
    });

    // 아이디 중복 체크 버튼 클릭 이벤트
    $("#check_id").click(function(){
        if($("#check_id").prop("check") == true){
            $("#check_id").prop("check", false);
            $("#join-id").attr("readonly", false);
            $("#join-id").removeClass("readOnly");
            $("#check_id").val("중복확인");
        }else{
            if($("#join-id").val().trim().length > 0){
                if($(".warning-text:eq(2)").hasClass("blind")  || $(".warning-text:eq(0)").text() == "이미 존재하는 아이디입니다."){
                    $.ajax({
                        url:"/rence/user_idCheckOK",
                        type : "POST",
                        dataType : 'json',
                        data : {
                            user_id : $("#join-id").val().trim()
                        },
                        success : function(res) {
                            // 아이디 중복 성공
                            if(res.result == 1){
                                $("#check_id").prop("check", true);
                                $("#check_id").val("재입력");
                                $("#join-id").attr("readonly", true);
                                $("#join-id").addClass("readOnly");
                            }else{
                                $(".warning-text:eq(2)").removeClass("blind");
                                $(".warning-text:eq(2)").text("이미 존재하는 아이디입니다.");
                            }
                        },
                        error : function(error) {
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                        }            
                    });
                }else{
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("조건에 맞는 아이디를 입력하신 후 중복체크 해주세요.");
                }
            }else{
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("아이디를 입력하신 후 중복체크 해주세요.");
            }
        }
    });
    

    // 이메일 인증 버튼 클릭 이벤트
    $("#check_email").click(function(){
        if($("#check_email").prop("check") != true){
            if($("#join-email").val().trim().length > 0){
                if($(".warning-text:eq(0)").hasClass("blind") || $(".warning-text:eq(0)").text() == "이미 존재하는 이메일입니다."){
                    $.ajax({
                        url:"/rence/user_auth",
                        type : "POST",
                        dataType : 'json',
                        data : {
                            user_email : $("#join-email").val().trim()
                        },
                        success : function(res) {
                            // 이메일 중복 성공
                            if(res.authNum == 1){
                                $("#check_email").prop("check", true);
                                $("#check_email").val("인증완료");
                                $("#join-email").attr("readonly", true);
                                $("#join-email").addClass("readOnly");

                                $(".popup-background:eq(1)").removeClass("blind");
                                $("#common-alert-popup").removeClass("blind");
                                $(".common-alert-txt").text("이메일로 인증번호를 발송하였습니다.");
                            }else if(res.authNum == 2){
                                $(".warning-text:eq(0)").removeClass("blind");
                                $(".warning-text:eq(0)").text("이미 존재하는 이메일입니다.");
                            }else{
                                $(".popup-background:eq(1)").removeClass("blind");
                                $("#common-alert-popup").removeClass("blind");
                                $(".common-alert-txt").text("오류 발생으로 인해 이메일 인증번호 발송에 실패하였습니다.");
                            }
                        },
                        error : function(error) {
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                        }            
                    })
                }else{
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("조건에 맞는 이메일을 입력하신 후 중복체크 해주세요.");
                }
            }else{
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("이메일을 입력하신 후 중복체크 해주세요.");
            }
        }
    });


    // 이메일 인증 코드 버튼 클릭
    $("#check_email-code").click(function(){
        if($("#check_email-code").prop("check") != true){
            if($("#join-email-code").val().trim().length > 0){
                $.ajax({
                    url:"/rence/user_authOK",
                    type : "POST",
                    dataType : 'json',
                    data : {
                        user_email : $("#join-email").val().trim(),
                        email_code : $("#join-email-code").val().trim()
                    },
                    success : function(res) {
                        // 이메일 인증번호 확인 성공
                        if(res.result == 1){
                            $("#check_email-code").prop("check", true);
                            $("#check_email-code").val("인증완료");
                            $("#join-email-code").attr("readonly", true);
                            $("#join-email-code").addClass("readOnly");
                        }else{
                            $(".popup-background:eq(1)").removeClass("blind");
                            $("#common-alert-popup").removeClass("blind");
                            $(".common-alert-txt").text("알맞지 않은 인증번호입니다.");
                        }
                    },
                    error : function(error) {
                        console.log(error);
                        $(".popup-background:eq(1)").removeClass("blind");
                        $("#common-alert-popup").removeClass("blind");
                        $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                    }            
                });
            }else{
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("인증번호 입력하신 후 시도해주세요.");
            }
        }
    });

    // 팝업 알러트 버튼 클릭 이벤트
    $("#success-alert-btn").click(function(){
        $(".popup-background").addClass("blind");
        $("#success-alert-popup").addClass("blind");
    });
    $("#fail-alert-btn").click(function(){
        $(".popup-background:eq(1)").addClass("blind");
        $("#fail-alert-popup").addClass("blind");
    });

    
    /*********** 로그아웃 팝업 ************/
    // 로그아웃 창닫기 버튼 클릭 이벤트
    $("#logout-closeBtn").click(function(){
        $("#logout-popup").addClass("blind");
        $(".popup-background:eq(0)").addClass("blind");
    });
    $("#logout-btn").click(function(){
        location.href="/rence/user_logoutOK";
    });

});