/**
 * @author 김예은, 전판근
 */
 $(function(){
  let test = 1;

  $("#common-alert-btn").click(function(){
    $(".popup-background:eq(1)").addClass("blind");
    $("#common-alert-popup").addClass("blind");

    if($(".common-alert-txt").text() == "성공적으로 문의가 등록되었습니다." || $(".common-alert-txt").text() == "성공적으로 후기가 등록되었습니다."){
      location.reload();
    }
  });

  /***** ************** *****/ 
  /***** 슬라이드 이미지 *****/ 
  /***** ************** *****/ 
  let position = 0;
  $(".next").click(function () {
    if (test < $(".img").length) {
      position += 960;
      $(".container").css("transform", "translateX(-"+position+"px)");
      test = test + 1;
    }
  });

  $(".prev").click(function () {
    if (test != 1) {
      position -= 960;
      $(".container").css("transform", "translateX(-"+position+"px)");
      test = test - 1;
    }
  });


  /***** ************** *****/ 
  /***** DATEPICKER부분 *****/ 
  /***** ************** *****/
  $(".time-input").datepicker({
    dateFormat: 'yy/mm/dd' //달력 날짜 형태
    ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
    ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
    ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
    ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
    ,buttonText: "선택" //버튼 호버 텍스트              
    ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
    ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
    ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
    ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
    ,minDate: new Date() //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
});      


  /***** *** ******* *****/ 
  /***** 고정된 부분 *****/ 
  /***** *** ******* *****/ 
  /*** 예약 시간 실패 팝업창 닫기 버튼 클릭 ***/
  $("#time-fail-close-btn").click(function(){
    $(".fixed-popup").addClass("blind");
  });

  /*** 예약 타입 셀렉트 클릭 시 -> 커스텀 셀렉트 SHOW ***/
  $(".type-border").click(function(){
    if($(".custom-select-type:eq(0)").hasClass("blind")){
      $(".custom-select-type:eq(0)").removeClass("blind");
      $(".type-border:eq(0)").addClass("open-select");
    }else{
      $(".custom-select-type:eq(0)").addClass("blind");
      $(".type-border:eq(0)").removeClass("open-select");
    }
  });
  
  /*** 예약 타입 리스트 클릭 ***/
  $(".custom-select-type-list").click(function(){
    $(".room-li-txt").text($(this).children(".room-name").text());
    $(".room-li-txt").prop("check", true);
    $(".custom-select-type:eq(0)").addClass("blind");
    $(".type-border:eq(0)").removeClass("open-select");
  });
  
  /*** 개월 수 셀렉트 클릭 시 -> 커스텀 셀렉트 SHOW ***/
  $("#month_section").click(function(){
    if($(".time-input").val() != '')
      $(".month-select-wrap").toggleClass("blind");
    else{
      $(".fixed-popup").removeClass("blind");
      $(".using-time-fail-txt").html("체크인 시간 먼저 선택하여 주십시오.");
    }
  });
  /*** 개월 수 리스트 클릭 ***/
  $(".month-select-li").click(function(e){
    e.stopPropagation();
    
    $(".month-select-txt").text($(this).text());
    $(".month-select-txt").prop("check", true);
    $(this).parents(".month-select-wrap").addClass("blind");

    var now = new Date($(".time-input").val());	// 현재 날짜 및 시간
    console.log("현재 : ", now);
    var checkout = new Date(now.setMonth(now.getMonth() + Number($(this).attr("month"))));	// 한달 후
    console.log("한달 후 : ", checkout);

    $(".duration").removeClass("blind");
    $(".duration").text("기간 | " + $(".time-input").val() + " ~ " +checkout.getFullYear() + "/" + (Number(checkout.getMonth()) + 1) + "/" + checkout.getDate() );
    $(".duration").prop("last-date", checkout.getFullYear() + "/" + (Number(checkout.getMonth()) + 1) + "/" + checkout.getDate() );
  });

  /*** 예약 가능 여부 버튼 클릭 ***/
  $("#check_available").click(function(){
    // 예약 타입 선택 O
    if($(".type-border-txt").prop("check") == true){
      if($(".time-input:eq(0)").val() != '' && $(".time-input:eq(1)").val() != ''){
        // 예약 가능 확인 로직
      }
      // 예약 타입 선택 O, 체크인 or 체크아웃 시간 X
      else{
        $(".fixed-popup").removeClass("blind");
        $(".using-time-fail-txt").html("체크인 시간과 개월 수를<br><br>모두 선택하여 주십시오.");
      }
    }
    // 예약 타입 선택 X
    else{
      $(".fixed-popup").removeClass("blind");
      $(".using-time-fail-txt").html("예약 타입을 선택하여 주십시오.");
    }
  });



  /***** *** ********** *****/ 
  /***** 고정 안 된 부분 *****/ 
  /***** *** ********** *****/ 

  /****** 메뉴 섹션 ******/
  $(".menu").click(function(){
    $(".menu").removeClass("click-menu");
    $(this).addClass("click-menu");
    
    if($(this).attr("menu") == "info"){
      $(".space-description-wrap").removeClass("blind");
      $(".question-wrap:eq(0)").addClass("blind");
      $("#review-wrap").addClass("blind");
    }
    else if($(this).attr("menu") == "qna"){
      $(".space-description-wrap").addClass("blind");
      $(".question-wrap:eq(0)").removeClass("blind");
      $("#review-wrap").addClass("blind");
    }
    else if($(this).attr("menu") == "review"){
      $(".space-description-wrap").addClass("blind");
      $(".question-wrap:eq(0)").addClass("blind");
      $("#review-wrap").removeClass("blind");
    }
  });


  /***** *** ******** *****/ 
  /***** REVIEW POPUP *****/ 
  /***** *** ******** *****/
  /** 팝업 닫기 버튼 클릭 이벤트 */ 
  $("#review-close-btn").click(function(){
    // TEXTAREA 초기화
    $("#review-write").val("");
    // 글자수 초기화
    $(".review-length").text("0");

    $("#review-write").removeClass("null-input-border");
    $(".question-popup-select-val-wrap:eq(1)").removeClass("null-input-border");
    $(".question-popup-select-val-wrap:eq(1)").removeClass("open-select");
    
    $("#review-select-choice").text("타입을 선택해 주세요");
    $("#review-select-choice").attr("choice_idx", "");
    $("#question-select-choice").attr("choice", "");
    $(".question-popup-select:eq(1)").addClass("blind");
    
    // 팝업 닫기
    $("#review-popup").addClass("blind");
  });

  /*** 후기 작성 경고 테두리 제거  ***/
  $("#review-write").click(function(){
    $("#review-write").removeClass("null-input-border");
  });

  $(".question-popup-select-val-wrap:eq(1)").click(function(){
    if($(".question-popup-select:eq(1)").hasClass("blind")){
      $(".question-popup-select-val-wrap:eq(1)").addClass("open-select");
      $(".question-popup-select:eq(1)").removeClass("blind");
      $(".question-popup-select-val-wrap:eq(1)").removeClass("null-input-border");
    }
    else{
      $(".question-popup-select-val-wrap:eq(1)").removeClass("open-select");
      $(".question-popup-select:eq(1)").addClass("blind");
    }
  });

  /** 팝업 셀렉트 리스트 클릭 이벤트 */
  $(".question-popup-select-li").click(function(){
    $("#review-select-choice").val($(this).val());
    $(".question-popup-select").addClass("blind");
    $(".question-popup-select").removeClass("open-select");
  });

  /** 팝업 셀렉트 리스트 클릭 이벤트 */
  $("#review-popup").on("click", ".question-popup-select-li", function(){
    $("#review-select-choice").text($(this).text());
    $("#review-select-choice").attr("choice", "true");
    $("#review-select-choice").attr("choice_idx", $(this).attr("idx"));

    $(".question-popup-select-val-wrap:eq(1)").removeClass("open-select");
    $(".question-popup-select:eq(1)").addClass("blind");
  });
  
  /** 이미지 등록 버튼 클릭 이벤트 */
  // $(".review-upload-btn").click(function(){
  //   $(".file").click();
  // });

  /** 이미지 등록 시 파일명 SHOW */
  // $(".file").on('change',function(){
  //   var fileName = $(".file").val();
  //   var fArr = fileName.split("\\");

  //   $(".review-upload-value").val(fArr[fArr.length - 1]);
  // });

  /** 문의글 작성 시 글자수 제한 */
  $("#review-write").on("keydown keyup", function(){
    if($(this).val().length > 500){
      $(this).val($(this).val().substring(0,500));
    }
    $(".review-length").text($(this).val().length);
  });

  $("#review-create-btn").click(function(){
    if($("#review-select-choice").attr("choice") == "true" && $("#review-write").val().trim().length > 0){
      var point = 0;
      for(var i = 0; i<5; i++){
          if($(".g-star").hasClass("blind")) point++;
      }

      $.ajax({
        url : "/rence/insert_review",
        type : "GET",
        dataType : 'json',
        data : {
            user_no : $.cookie("user_no"),
            backoffice_no : location.href.split("backoffice_no=")[1].split("&")[0],
            room_no : $("#review-select-choice").attr("choice_idx"),
            review_point : point,
            review_content : $("#review-write").val().trim()
        },
        success : function(res) {
            if(res.result == 1){
              // TEXTAREA 초기화
              $("#review-write").val("");
              // 글자수 초기화
              $(".review-length").text("0");

              $("#review-write").removeClass("null-input-border");
              $(".question-popup-select-val-wrap:eq(1)").removeClass("null-input-border");
              $(".question-popup-select-val-wrap:eq(1)").removeClass("open-select");
              
              $("#review-select-choice").text("타입을 선택해 주세요");
              $("#review-select-choice").attr("choice_idx", "");
              $("#question-select-choice").attr("choice", "");
              $(".question-popup-select:eq(1)").addClass("blind");
              
              // 팝업 닫기
              $("#review-popup").addClass("blind");

              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("성공적으로 후기가 등록되었습니다.");
            }else{
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("비밀번호가 일치하지않습니다.");
            }
        },
        error : function(error) {
            console.log(error);
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
    });
    }
    else{
      if($("#review-write").val().trim().length == 0){
        $("#review-write").addClass("null-input-border");
      }
      if($("#review-select-choice").attr("choice") != "true"){
        $(".question-popup-select-val-wrap:eq(1)").addClass("null-input-border");
      }
    }
  });

  /*** 별점 클릭 이벤트 ***/
  $(".popup-star-li").click(function(){
    // 별 초기화
    $(".y-star").addClass("blind");
    $(".g-star").removeClass("blind");

    // 클릭한 별 파악
    var last_idx = $(this).attr("id"); 

    var arr = $(".popup-star-li");
    console.log(arr);
    console.log(arr.length);

    for(var i = 0; i < arr.length; i++){
      $(arr[i]).children(".y-star").removeClass("blind");
      $(arr[i]).children(".g-star").addClass("blind");
      console.log($(arr[i]).children(".y-star"));
      console.log($(arr[i]).children(".g-star"));

      if($(arr[i]).attr("id") == last_idx){
        break;
      }
    }

  });


  /***** ************** *****/ 
  /***** QUESTION POPUP *****/ 
  /***** ************** *****/

  /*** 팝업 닫기 버튼 클릭  ***/
  $("#question-close-btn").click(function(){
    // TEXTAREA 초기화
    $("#question-write").val("");
    // 글자수 초기화
    $(".qna-length").text("0");

    $("#question-write").removeClass("null-input-border");
    $(".question-popup-select-val-wrap:eq(0)").removeClass("null-input-border");
    $(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
    
    $("#question-select-choice").text("타입을 선택해 주세요");
    $("#question-select-choice").attr("choice_idx", "");
    $("#question-select-choice").attr("choice", "");
    $(".question-popup-select:eq(0)").addClass("blind");
    
    // 팝업 닫기
    $("#question-popup").addClass("blind");
  });

  /*** 문의 작성 경고 테두리 제거  ***/
  $("#question-write").click(function(){
    $("#question-write").removeClass("null-input-border");
  });
  
  /*** 문의 작성 글자수 제한  ***/
  $("#question-write").on("keydown keyup", function(){
    if($(this).val().length > 500){
      $(this).val($(this).val().substring(0,500));
    }
    $(".qna-length").text($(this).val().length);
  });

  $(".question-popup-select-val-wrap:eq(0)").click(function(){
    if($(".question-popup-select:eq(0)").hasClass("blind")){
      $(".question-popup-select-val-wrap:eq(0)").addClass("open-select");
      $(".question-popup-select:eq(0)").removeClass("blind");
      $(".question-popup-select-val-wrap:eq(0)").removeClass("null-input-border");
    }
    else{
      $(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
      $(".question-popup-select:eq(0)").addClass("blind");
    }
  });

  /** 팝업 셀렉트 리스트 클릭 이벤트 */
  $("#question-popup").on("click", ".question-popup-select-li", function(){
    $("#question-select-choice").text($(this).text());
    $("#question-select-choice").attr("choice", "true");
    $("#question-select-choice").attr("choice_idx", $(this).attr("idx"));

    $(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
    $(".question-popup-select:eq(0)").addClass("blind");
  });

  $("#question-createBtn").click(function(){
    if($("#question-select-choice").attr("choice") == "true" && $("#question-write").val().trim().length > 0){
        $.ajax({
          url : "/rence/insert_question",
          type : "GET",
          dataType : 'json',
          data : {
              user_no : $.cookie("user_no"),
              backoffice_no : location.href.split("backoffice_no=")[1].split("&")[0],
              room_no : $("#question-select-choice").attr("choice_idx"),
              comment_content : $("#question-write").val().trim()
          },
          success : function(res) {
              if(res.result == 1){
                $(".qna-length").text("0");
                $("#question-write").val("");

                $(".question-popup-select-val-wrap:eq(0)").removeClass("null-input-border");
                $("#question-write").removeClass("null-input-border");
                
                $("#question-select-choice").text("타입을 선택해 주세요");
                $("#question-select-choice").attr("choice_idx", "");
                $("#question-select-choice").attr("choice", "");
                
                $(".question-popup-select-val-wrap:eq(0)").removeClass("open-select");
                $(".question-popup-select:eq(0)").addClass("blind");
                $("#question-popup").addClass("blind");

                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("성공적으로 문의가 등록되었습니다.");
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
      else{
        if($("#question-write").val().trim().length == 0){
          $("#question-write").addClass("null-input-border");
        }
        if($("#question-select-choice").attr("choice") != "true"){
          $(".question-popup-select-val-wrap:eq(0)").addClass("null-input-border");
        }
    }
  });
});