/**
 * @author 김예은, 전판근
 */
 $(function(){
  let test = 1;
  let pickerDate = '';

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
  $(".time-input").datetimepicker({
    dateFormat:'yy/mm/dd',
    monthNamesShort:[ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
    dayNamesMin:[ '일', '월', '화', '수', '목', '금', '토' ],
    showMonthAfterYear:true,
    minDate: new Date().toLocaleDateString().replaceAll(". ", "/"),

    // timepicker 설정
    timeFormat:'HH:mm:ss',
    controlType:'select',
    oneLine:true,
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
    if($(".custom-select-type").hasClass("blind")){
      $(".custom-select-type").removeClass("blind");
      $(".type-border").addClass("open-select");
    }else{
      $(".custom-select-type").addClass("blind");
      $(".type-border").removeClass("open-select");
    }
  });
  
  /*** 예약 타입 리스트 클릭 ***/
  $(".custom-select-type-list").click(function(){
    $(".type-border-txt").text($(this).children(".room-name").text());
    $(".type-border-txt").prop("check", true);
    
    let attr_room_no = $(this).children(".room-name").attr("room_no");
    $("#type-choice-value").attr("room_no", attr_room_no);
    
    $(".custom-select-type").addClass("blind");
    $(".type-border").removeClass("open-select");
  });

   
  // ***************
  // 선택 날짜 요일 체크
  // ***************
  function getDayOfWeek(date) {
    const week = [0, 1, 2, 3, 4, 5, 6]
    const dayOfWeek = week[new Date(date).getDay()]
    return dayOfWeek
  }

  // 예약 날짜 미선택 시 사용 시간 display none
  if (!pickerDate) {
    $('#reserve-time-boundary').css('display', 'none')
  }

  // 예약 선택 시간
  let pick_time_list = []

  // 예약 선택 시간 boundary
  // ex) 11, 16시 선택 시
  // 이 배열에는 11, 12, 13, 14, 15, 16이 들어감.
  // 예약이 가능한지 체크하기 위함.
  // 중간 시간에 예약이 있으면 불가능함.
  let check_reserve_time = []

  // 시간 선택
  $('.type-border-txt.time-input').change(function () {
    $('.time-boundary-item').removeClass('selected')
    pick_time_list = []
    pickerDate = $('.type-border-txt.time-input').val()
    let dayOfWeek = getDayOfWeek(pickerDate)

    // ***********************
    // 해당 요일의 운영 시간 가져오기
    // ***********************
    let stime = ''
    let etime = ''

	// *************************
	// 예약 가능한 시간대를 리스트로 표시
	// *************************
//    $('.running-time-li-wrap li').each(function (index, item) {
//      if (index === dayOfWeek) {
//        let running_time = $(this).children('span').text().trim()
//
//        if (running_time !== '휴무') {
//          let split_time = running_time.split(' ~ ')
//
//          stime = parseInt(split_time[0].split(':')[0])
//          etime = parseInt(split_time[1].split(':')[0])
//
//          // 예약 날짜 선택 시 사용 시간 보이게 하기.
//          $('#reserve-time-boundary').css('display', 'flex')
//          let running_time_list = []
//          for (var t = stime; t < etime; t++) {
//            running_time_list.push(t)
//          }
//
//          // 운영 시간 display 표시
//          $('.time-boundary-list li').each(function (index, item) {
//            // 이전 운영 시간 display 초기화
//            $(this).css('display', 'none')
//            if (running_time_list.includes(index)) {
//              $(this).css('display', 'flex')
//            }
//          })
//        } else {
//          $('.time-boundary-list li').each(function (index, item) {
//            $(this).css('display', 'none')
//          });
//        }
//      }
//    });
  });

  // pick_time_list에 시간이 하나만 들어가 있으면 1시간 대여
  // 2개 있으면 그 사이 시간을 대여하는 것.
  $('.time-boundary-item').on('click', function () {
    if ($(this).hasClass('selected')) {
      // 선택 취소 했을 때
      pick_time_list.pop($(this).val());
      $(this).toggleClass('selected');
    } else {
      // 선택 되었을 때
      if (pick_time_list.length < 2) {
        pick_time_list.push($(this).val());
        pick_time_list.sort();
        $(this).toggleClass('selected');
      }
    }

    // 예약 불가 처리
    if (pick_time_list.length == 2) {
      check_reserve_time = []
      for (var t = pick_time_list[0]; t <= pick_time_list[1]; t++) {
        check_reserve_time.push(t);
      }

      // 예약 가능 유무 체크
      let flag = 0
      let tmp_time_range = []

      $('.time-boundary-list li').each(function (index, item) {
        if (check_reserve_time.includes($(this).val())) {
          console.log($(this).val())
          tmp_time_range.push($(this).val())
        }
      })

      if (
        JSON.stringify(check_reserve_time) === JSON.stringify(tmp_time_range)
      ) {
        flag = 1
      } else {
        flag = 0
      }

      // 선택 시간 중간에 예약이 있어서
      // 예약이 불가능 할 때, 팝업 띄우기
      // TODO: 단, 이 부분은 예약 테이블과 연결하여 실 테스트 해봐야 함.
      if (flag === 0) {
        $('.fixed-popup').removeClass('blind')
        $('.using-time-fail-txt:eq(0)').html(
          '선택 시간 사이에 예약이 존재하여<br><br>해당 시간은 예약할 수 없습니다.',
        )
      }
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