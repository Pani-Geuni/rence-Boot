/**
 * @author 김예은, 전판근
 */
 $(function(){
  let test = 1;

  $("#common-alert-btn").click(function(){
    $(".popup-background:eq(1)").addClass("blind");
    $("#common-alert-popup").addClass("blind");

    if($(this).attr("is_reload") == "true"){
      location.reload();
      $(this).attr("is_reload", false);
    }

  });

  /***** ************** *****/ 
  /***** 슬라이드 이미지 *****/ 
  /***** ************** *****/ 
  $(".prev").addClass("hide");
  
  if ($(".img").length == 1) {
    $(".next").addClass("hide");
  }

  else{
	  let position = 0;
	  $(".next").click(function () {
	    if (test < $(".img").length) {
	      position += 960;
	      $(".container").css("transform", "translateX(-"+position+"px)");
	      test = test + 1;

	      if(test == $(".img").length){
	        $(".next").addClass("hide");
	      }
	      if(test == 2){
	        $(".prev").removeClass("hide");
	      }
	    }
	  });
	
	  $(".prev").click(function () {
	    if (test != 1) {
	      position -= 960;
	      $(".container").css("transform", "translateX(-"+position+"px)");
	      test = test - 1;
        
        if(test == $(".img").length-1){
          $(".next").removeClass("hide");
	      }
        if(test == 1){
	        $(".prev").addClass("hide");
	      }
	    }
	  });
  }


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
  $("#office_check_available").click(function(){
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


  /***** ************* *****/ 
  /***** QUESTION MENU *****/ 
  /***** ************* *****/
  $(".answer_toggle").click(function(){
    var answer_li = $(this).parents(".quest-list").next(".answer-list");
    if(answer_li.hasClass("blind")){
      answer_li.removeClass("blind");
      $(".answer_toggle").text("답변 닫기");
    }else{
      answer_li.addClass("blind");
      $(".answer_toggle").text("답변 보기");
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

    $("#toggle").prop("checked",false);
    
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
      //로딩 화면
      $(".popup-background:eq(1)").removeClass("blind");
      $("#spinner-section").removeClass("blind");  
      var is_secret = "";
      $("#toggle").prop("checked") ? is_secret = 'T' : is_secret = 'F';

      $.ajax({
        url : "/office/insert_question",
        type : "GET",
        dataType : 'json',
        data : {
            user_no : $.cookie("user_no"),
            backoffice_no : location.href.split("backoffice_no=")[1].split("&")[0],
            room_no : $("#question-select-choice").attr("choice_idx"),
            comment_content : $("#question-write").val().trim(),
            is_secret : is_secret
        },
        success : function(res) {
          //로딩 화면 닫기
          $(".popup-background:eq(1)").addClass("blind");
          $("#spinner-section").addClass("blind");

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

            $("#toggle").prop("checked",false);

            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("성공적으로 문의가 등록되었습니다.");
            $("#common-alert-btn").attr("is_reload", true);
          }else{
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("비밀번호가 일치하지않습니다.");
          }
        },
        error : function() {
          //로딩 화면 닫기
          $(".popup-background:eq(1)").addClass("blind");
          $("#spinner-section").addClass("blind");

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