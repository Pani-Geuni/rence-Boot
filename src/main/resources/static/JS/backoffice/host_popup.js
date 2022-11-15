/**
* @author : 전판근, 김예은
*/
$(function () {
  /** 공용 알러트 창닫기 버튼 */ 
  $("#common-alert-btn").click(function(){
    $(".popup-background:eq(1)").addClass("blind");
    $("#common-alert-popup").addClass("blind");

    if($(".common-alert-txt").text() == "수정이 완료되었습니다." || $(".common-alert-txt").text() == "공간이 추가되었습니다."
      || $(".common-alert-txt").text() == "삭제가 완료되었습니다." || $(".common-alert-txt").text() == "답변을 삭제하였습니다."
      || $(".common-alert-txt").text() == "답글이 등록되었습니다."|| $(".common-alert-txt").text() == "정산처리되었습니다."){
      location.reload();
    }
  });

  /******************************* */
  /***********로그인 팝업 ********* */
  /******************************* */
  // 로그인 인풋 클릭 시 경고 테두리 제거
  $(".login-popup-input").click(function(){
    if($(this).hasClass("null-input-border")){
        $(this).removeClass("null-input-border");
    }
  });
  $("#login-btn").click(function(){
    if($("#login-id").val().trim().length == 0){
      $("#login-id").addClass("null-input-border");
    }
    if($("#login-pw").val().trim().length == 0){
      $("#login-pw").addClass("null-input-border");
    }

    if($("#login-id").val().trim().length > 0 && $("#login-pw").val().trim().length > 0){
      $.ajax({
        url : "/rence/backoffice_loginOK",
        type : "POST",
        dataType : 'json',
        data : {
          backoffice_id : $("#login-id").val().trim(),
          backoffice_pw : CryptoJS.SHA256($("#login-pw").val().trim()).toString()
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
              $('.popup-background:eq(0)').addClass('blind');
              $('#login-section').addClass('blind');

              location.href = '/rence/backoffice_main?backoffice_no=' + $.cookie("backoffice_no");
          }else{
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("로그인에 실패하였습니다.");
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
  });
    
  /** 비밀번호 찾기 버튼 클릭 */
  $("#go-find-pw").click(function(){
    $('#login-section').addClass('blind');
    $("#find-pw-section").removeClass("blind");
  });

  /** 로그인 팝업 창닫기 */
  $("#host-login-close").click(function(){
    //INPUT 초기화
    $("#login-id").val("");
    $("#login-pw").val("");

    $("#login-id").removeClass("null-input-border");
    $("#login-pw").removeClass("null-input-border");

    // 팝업 관련창 닫음
    $('.popup-background:eq(0)').addClass('blind');
    $('#login-section').addClass('blind');
  });


  /********************************* */
  /******** 비밀번호 찾기 팝업 ********/
  /********************************* */
  // 비밀번호 찾기 인풋 클릭 시 경고 테두리 제거
  $(".find-popup-input").click(function(){
    if($(this).hasClass("null-input-border")){
        $(this).removeClass("null-input-border");
    }
  });

  // 비밀번호 창닫기 버튼
  $("#find-pw-close").click(function(){
    $(".popup-background:eq(0)").addClass("blind");
    $("#find-pw-section").addClass("blind");
  });

  //비밀번호 찾기 버튼
  $("#find-pw-btn").click(function(){
    if($("#find-pw-email").val().trim().length == 0){
      $("#find-pw-email").addClass("null-input-border");
    }
    if($("#find-pw-backoffice-code").val().trim().length == 0){
      $("#find-pw-backoffice-code").addClass("null-input-border");
    }

    if($("#find-pw-email").val().trim().length > 0 && $("#find-pw-backoffice-code").val().trim().length > 0){
      $.ajax({
        url : "/rence/backoffice_reset_pw",
        type : "GET",
        dataType : 'json',
        data : {
          backoffice_id : $("#find-pw-backoffice-code").val().trim(),
          backoffice_email : $("#find-pw-email").val().trim()
        },
        success : function(res) {
          // 비밀번호찾기 성공
          if(res.result == 1){
              //INPUT 초기화
              $("#find-pw-email").val("");
              $("#find-pw-backoffice-code").val("");

              $("#find-pw-email").removeClass("null-input-border");
              $("#find-pw-backoffice-code").removeClass("null-input-border");

              // 팝업 관련창 닫음
              $(".popup-background:eq(0)").addClass("blind");
              $("#find-pw-section").addClass("blind");

              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("이메일로 비밀번호를 발송해드렸어요!");
          }else{
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("해당 정보로 가입된 호스트가 없습니다.");
          }
      },
      error : function() {
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
      });
    }
  });


  
  /********************************* */
  /***********로그아웃 팝업 ********* */
  /********************************* */
  $("#logout-btn").click(function(){
    location.href="/rence/backoffice_logout";
  });
  $("#logout-closeBtn").click(function(){
    $('.popup-background:eq(0)').removeClass('blind');
    $('#logout-popup').removeClass('blind');
  });



  $('#join-btn').click(function(){
    $('.popup-background:eq(0)').addClass('blind');
    $('#join-section').addClass('blind');
  });

  $('#success-alert-btn').click(function(){
    $('.popup-background:eq(0)').addClass('blind');
    $('#success-alert-popup').addClass('blind');
  });
  $('#fail-alert-btn').click(function(){
    $('.popup-background:eq(0)').addClass('blind');
    $('#fail-alert-popup').addClass('blind');
  });

  $('#logout-closeBtn').click(function(){
    $('#logout-popup').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });

  /****** ********* *****/
  /****** 공간 추가 *****/
  /****** ********* *****/
  // 추가 버튼 클릭 -> 추가 팝업창 SHOW
  $('#btn-room-add').click(function(){
    $.ajax({
      url:"/rence/backoffice_insert_room",
        type : "GET",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no")
        },
        success : function(res) {
          $('.popup-background:eq(0)').removeClass('blind');
          $('#room-insert-section').removeClass('blind');
          
          for(var i = 0; i < res.room_type.length; i++){
            var sample = $(".insert-type-select-item:eq(0)").clone();
            sample.removeClass("blind");
            sample.attr("type", res.room_type[i]);

            if(res.room_type[i] == 'desk') sample.text("데스크");
            else if(res.room_type[i] == 'meeting_04') sample.text("미팅룸(4인)");
            else if(res.room_type[i] == 'meeting_06') sample.text("미팅룸(6인)");
            else if(res.room_type[i] == 'meeting_10') sample.text("미팅룸(10인)");
            else if(res.room_type[i] == 'office') sample.text("오피스");

            if(res.room_type[i] == "office"){
              $(".room-input-wrap:eq(2)").removeClass("blind");
            }
            else{
              if(!$(".room-input-wrap:eq(2)").hasClass("blind")) 
                $(".room-input-wrap:eq(2)").addClass("blind");
            }

            $(".insert-type-select-list").append(sample);
          }
        },
        error : function() {
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
    });
  });

  $('#insert-room-type-label').on('click', function(){
    $('.insert-type-select-list').toggleClass('blind');
    $("#room-type-select").removeClass("null-input-border");
  });

  $('.insert-type-select-list').on('click', '.insert-type-select-item', function(){
    let type = $(this).text();

    if(type == '데스크') $('#edit_room_type').val("desk");
    else if(type == '미팅룸(4인)') $('#edit_room_type').val("meeting_04");
    else if(type == '미팅룸(6인)') $('#edit_room_type').val("meeting_06");
    else if(type == '미팅룸(10인)') $('#edit_room_type').val("meeting_10");
    else if(type == '오피스') $('#edit_room_type').val("office");

    if(type == "오피스"){
      $(".room-input-wrap:eq(2)").removeClass("blind");
    }else{
      $("#input-price-name").val("");
      $(".room-input-wrap:eq(2)").addClass("blind");
    }

    $('#insert-room-type-label').text(type);
    $('#insert-room-type-label').css('color', '#000000');
    $('.insert-type-select-list').addClass('blind');
  });

   // 공간 가격 정규표현식 사용
   $("#input-price-name").on("keyup keydown", function(){
    let check = /^[0-9]+$/;

    if(!check.test($("#input-price-name").val().trim())){
      $(this).siblings(".r-input-warning:eq(0)").removeClass("blind");
    }else{
      $(this).siblings(".r-input-warning:eq(0)").addClass("blind");
    }
  });

  // 닫기 버튼 클릭 -> 추가 팝업창 HIDE
  $('#btn-insert-cancel').click(function(){
    $('#edit_room_type').val('');
    $("#input-room-name").val('');
    $("#input-price-name").val("");
    $("#insert-room-type-label").text('타입을 선택해주세요.');

    $(".r-input-warning:eq(0)").addClass("blind");

    $('#insert-room-type-label').css('color', '#808080');

    // 경고 테두리 초기화
    $("#input-room-name").removeClass("null-input-border");
    $("#input-price-name").removeClass("null-input-border");
    $("#room-type-select").removeClass("null-input-border");

    var sample = $(".insert-type-select-item:eq(0)").clone();
    $(".insert-type-select-list").empty().append(sample);

    $('.insert-type-select-list').addClass('blind');
    $('#room-insert-section').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });

  // 추가 버튼 클릭 -> 추가 로직
  $('#btn-insert').click(function(){
    // 입력값 not null인지 확인
    if($("#input-room-name").val().trim().length > 0 && $('#edit_room_type').val().length > 0){
      if($('#edit_room_type').val() == 'office'){
        if($("#input-price-name").val().trim().length > 0){
          insert();
        }else{
          $("#input-price-name").addClass("null-input-border");
        }
      }else{
        insert();
      }
    }else{
      if($("#input-room-name").val().trim().length == 0){
        $("#input-room-name").addClass("null-input-border");
      }
      if($("#input-price-name").val().trim().length == 0){
        $("#input-price-name").addClass("null-input-border");
      }
      if($('#edit_room_type').val().length == 0){
        $("#room-type-select").addClass("null-input-border");
      }
    }
  });

  $("#insert-success-alert-btn").click(function(){
    $(".popup-background:eq(0)").addClass("blind");
    $("#insert-success-alert-popup").addClass("blind");

    location.reload();
  });


  /****** ********* *****/
  /****** 공간 수정 *****/
  /****** ********* *****/

  // 수정 버튼 클릭 -> 수정 팝업 오픈
  $('.btn-room-edit').on('click', function(){
    $.ajax({
      url:"/rence/backoffice_update_room",
        type : "GET",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no"),
          room_no : $(this).attr("idx")
        },
        success : function(res) {
          console.log(res);
          $('.popup-background:eq(0)').removeClass('blind');
          $('#room-edit-section').removeClass('blind');
          $("#btn-edit").attr("idx", res.rmvo.room_no);

          $('#m-edit_room_type').val(res.rmvo.room_type);
          $("#m-input-room-name").val(res.rmvo.room_name);
          $("#m-input-price-name").val(res.rmvo.room_price);

          var typeName = "";
          if(res.rmvo.room_type == 'desk') typeName = "데스크";
          else if(res.rmvo.room_type == 'meeting_04') typeName = "미팅룸(4인)";
          else if(res.rmvo.room_type == 'meeting_06') typeName = "미팅룸(6인)";
          else if(res.rmvo.room_type == 'meeting_10') typeName = "미팅룸(10인)";
          else if(res.rmvo.room_type == 'office') typeName = "오피스";

          if(typeName == "오피스"){
            $(".room-input-wrap:eq(5)").removeClass("blind");
          }else{
            $("#m-input-price-name").val("");
            $(".room-input-wrap:eq(5)").addClass("blind");
          }

          $("#m-edit-room-type-label").val(res.rmvo.room_type);
          $("#m-edit-room-type-label").text(typeName);

          for(var i = 0; i < res.room_type.length; i++){
            var sample = $(".edit-type-select-item:eq(0)").clone();
            sample.removeClass("blind");
            sample.attr("type", res.room_type[i]);
            console.log(res.room_type[i])

            if(res.room_type[i] == 'desk') sample.text("데스크");
            else if(res.room_type[i] == 'meeting_04') sample.text("미팅룸(4인)");
            else if(res.room_type[i] == 'meeting_06') sample.text("미팅룸(6인)");
            else if(res.room_type[i] == 'meeting_10') sample.text("미팅룸(10인)");
            else if(res.room_type[i] == 'office') sample.text("오피스");

            $(".edit-type-select-list").append(sample);
          }
        },
        error : function() {
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
    });
  });

  // 수정 팝업 닫기 버튼 -> 수정 팝업 닫음
  $('#btn-edit-cancel').click(function(){
    // input 초기화
    $('#m-edit_room_type').val('');
    $("#m-input-room-name").val('');
    $("#m-input-price-name").val("");
    $("#m-edit-room-type-label").text('타입을 선택해주세요.');

    $('#m-edit-room-type-label').css('color', '#808080');

    // 경고 테두리 초기화
    $("#m-input-room-name").removeClass("null-input-border");
    $("#m-input-price-name").removeClass("null-input-border");
    $("#m-room-type-select").removeClass("null-input-border");

    var sample = $(".edit-type-select-item:eq(0)").clone();
    $(".edit-type-select-list").empty().append(sample);

    $(".r-input-warning:eq(1)").addClass("blind");

    $('.edit-type-select-list').addClass('blind');
    $('#room-edit-section').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });

  // 인풋 창 클릭 시 경고 테두리 초기화
  $('.room-input').click(function(){
    $(this).removeClass("null-input-border");
  });

  // 공간 가격 정규표현식 사용
  $("#m-input-price-name").on("keyup keydown", function(){
    let check = /^[0-9]+$/;

    if(!check.test($("#m-input-price-name").val().trim())){
      $(this).siblings(".r-input-warning:eq(1)").removeClass("blind");
    }else{
      $(this).siblings(".r-input-warning:eq(1)").addClass("blind");
    }
  });
  
  // 수정 팝업 - 공간 타입 선택 버튼 클릭
  $('#m-edit-room-type-label').on('click', function(){
    $("#m-room-type-select").removeClass("null-input-border");
    $('.edit-type-select-list').toggleClass('blind');
  });

  // 수정 팝업 - 공간 타입 선택 후 처리
  $('.edit-type-select-list').on('click', '.edit-type-select-item', function(){
    let type = $(this).text();

    if(type == '데스크') $('#m-edit_room_type').val("desk");
    else if(type == '미팅룸(4인)') $('#m-edit_room_type').val("meeting_04");
    else if(type == '미팅룸(6인)') $('#m-edit_room_type').val("meeting_06");
    else if(type == '미팅룸(10인)') $('#m-edit_room_type').val("meeting_10");
    else if(type == '오피스') $('#m-edit_room_type').val("office");

    if(type == "오피스"){
      $(".room-input-wrap:eq(5)").removeClass("blind");
    }else{
      $("#m-input-price-name").val("");
      $(".room-input-wrap:eq(5)").addClass("blind");
    }

    $('#m-edit-room-type-label').text(type);
    $('#m-edit-room-type-label').css('color', '#000000');
    $('.edit-type-select-list').addClass('blind');
  });

  // 변경 버튼 클릭 -> 변경 로직
  $('#btn-edit').click(function(){
    // 입력값 not null인지 확인
    if($("#m-input-room-name").val().trim().length > 0 && $('#m-edit_room_type').val().length > 0){
      if($('#m-edit_room_type').val() == "office"){
        if($("#m-input-price-name").val().trim().length > 0){
          update();
        }
      }else{
        update();
      }
    }else{
      if($("#m-input-room-name").val().trim().length == 0){
        $("#m-input-room-name").addClass("null-input-border");
      }
      if($("#m-input-price-name").val().trim().length == 0){
        $("#m-input-price-name").addClass("null-input-border");
      }
      if($('#m-edit_room_type').val().length == 0){
        $("#m-room-type-select").addClass("null-input-border");
      }
    }
  });


  /****** ********* *****/
  /****** 공간 삭제 *****/
  /****** ********* *****/
  // 삭제 버튼 클릭 -> 삭제 팝업 오픈
  $('.btn-room-delete').on('click', function(){
    $('.popup-background:eq(0)').removeClass('blind');
    $('#space-delete-popup').removeClass('blind');
    $("#delete-space-btn").attr("idx", $(this).attr("idx"));
  });

  // 공간 삭제 버튼 클릭 -> 삭제 로직 처리
  $("#delete-space-btn").click(function(){
    $.ajax({
      url:"/rence/backoffice_deleteOK_room",
        type : "POST",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no"),
          room_no : $(this).attr("idx")
        },
        success : function(res) {
          $('.popup-background:eq(0)').addClass('blind');
          $('#host-delete-popup').addClass('blind');
          $("#delete-space-btn").attr("idx", "");

          // 삭제 성공
          if(res.result == 1){
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("삭제가 완료되었습니다.");
          }else{
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("삭제에 실패하였습니다.");
          }
        },
        error : function() {
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
    });
  });

  // 삭제 버튼 클릭 -> 삭제 팝업 오픈
  $('#delete-closeBtn').on('click', function(){
    $('.popup-background:eq(0)').addClass('blind');
    $('#space-delete-popup').addClass('blind');
    $("#delete-space-btn").attr("idx", "");
  });


  /** ************* **/ 
  /** 문의 관련 팝업 **/ 
  /** ************* **/ 
  // 문의 클릭 시 자세히 보기
  $(".ct-body-row.qna").on('click', function(event){
    $(this).next(".detail-qna-wrap").toggleClass("blind");
    event.defaultPrevented;
  });

  // 문의 답글 삭제 요청
  $('.ct-body-btn.qna-delete').on('click', function(){
    $(".popup-background:eq(0)").removeClass("blind");
    $("#answer-delete-popup").removeClass("blind");
    $("#delete-answer-btn").attr("comment_no", $(this).attr("answer_no"));
    $("#delete-answer-btn").attr("mother_no", $(this).attr("comment_no"));
  });

  // 답글 삭제 여부 컴펌창 - "삭제" 버튼 클릭
  $("#delete-answer-btn").click(function(){
    $.ajax({
      url:"/rence/backoffice_deleteOK_comment",
      type : "POST",
      dataType : 'json',
      data : {
        backoffice_no : $.cookie("backoffice_no"),
        mother_no : $(this).attr("mother_no"),
        comment_no : $(this).attr("comment_no")
      },
      success : function(res) {
        if(res.result == 1){
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("답변을 삭제하였습니다.");
        }else{
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("답변을 삭제 처리에 실패하였습니다.");
        }
      },
      error : function(error) {
          console.log(error);
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
      }            
    });
  });

  // 답글 삭제 여부 컴펌창 - 닫기 버튼
  $("#delete-answer-closeBtn").click(function(){
    $(".popup-background:eq(0)").addClass("blind");
    $("#answer-delete-popup").addClass("blind");
    $("#delete-answer-btn").attr("comment_no", "");
    $("#delete-answer-btn").attr("mother_no", "");
  });

  // 문의 답글 작성
  $('.ct-body-btn.qna-add').on('click', function(){
    $.ajax({
      url:"/rence/backoffice_insert_comment",
      type : "GET",
      dataType : 'json',
      data : {
        backoffice_no : $.cookie("backoffice_no"),
        room_no : $(this).attr("room_no"),
        comment_no : $(this).attr("comment_no")
      },
      success : function(res) {
        $("#q_room_name").text(res.cvo.room_name);
        $("#user_comment").text(res.cvo.comment_content);
        $("#h_comment_insert").attr("comment_no", res.cvo.comment_no);
        $("#h_comment_insert").attr("room_no", res.cvo.room_no);

        $('#comment-section').removeClass('blind');
        $('.popup-background:eq(0)').removeClass('blind');
      },
      error : function() {
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
      }            
    });
  });

  // 문의 답글 작성 팝업 - 창닫기버튼
  $('.btn-comment-cancel').on('click', function(){
    $("#host-comment").val("");
    $(".now_txt_length").text("0");

    $('#comment-section').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });

  // 문의 답글 작성 팝업 - 답글 추가 버튼
  $("#h_comment_insert").click(function(){
    if($("#host-comment").val().trim().length > 0){
      $.ajax({
        url:"/rence/backoffice_insertOK_comment",
        type : "POST",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no"),
          comment_no : $(this).attr("comment_no"),
          room_no : $(this).attr("room_no"),
          comment_content : $("#host-comment").val().trim()
        },
        success : function(res) {
            if(res.result == 1){
              $("#host-comment").val("");
              $(".now_txt_length").text("0");
          
              $('#comment-section').addClass('blind');
              $('.popup-background:eq(0)').addClass('blind');
  
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("답글이 등록되었습니다.");
            }else{
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("답글 등록에 실패하였습니다.");
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
      $("#host-comment").addClass("null-input-border");
    }
  });

  // 답글 작성 시 글자수 제한
  $("#host-comment").on("keyup keydown", function(){
    $(".now_txt_length").text($(this).val().trim().length);

    if($(this).val().trim().length > 500){
      $(this).val($(this).val().trim().substring(0,500));
    }
  });


  /** *********************** **/ 
          /** 정산 관련 **/ 
  /** *********************** **/
  $(".is_sales_btn").click(function(){
    if($(this).attr("end") == "false"){
      $('.popup-background:eq(0)').removeClass('blind');
      $("#calculate-popup").removeClass("blind");
      $("#calculate-btn").attr("room_no", $(this).attr("room_no"));
      $("#calculate-btn").attr("payment_no", $(this).attr("payment_no"));
    }
  });

  $("#calculate-btn").click(function(){
    $.ajax({
      url:"/rence/backoffice_updateOK_sales",
      type : "POST",
      dataType : 'json',
      data : {
        backoffice_no : $.cookie("backoffice_no"),
        payment_no : $(this).attr("payment_no"),
        room_no : $(this).attr("room_no")
      },
      success : function(res) {
        console.log(res.result);
        
        if(res.result == 1){
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("정산처리되었습니다.");
        }else{
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("정산처리에 실패하였습니다.");
        }
      },
      error : function(error) {
          console.log(error);
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
      }            
  });
  });

  $("#calculate-closeBtn").click(function(){
    $('.popup-background:eq(0)').addClass('blind');
    $("#calculate-popup").addClass("blind");
  });



  /** *********************** **/ 
  /** 환경 설정 부분 팝업 관련 **/ 
  /** *********************** **/ 
  $('#btn-update-pw').on('click', function(){
    $('#popup-update-pw').removeClass('blind');
    $('.popup-background:eq(0)').removeClass('blind');
  });

  $('#btn-popup-close').on('click', function(){
    $('#popup-update-pw').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });

  $('#btn-host-delete').on('click', function(){
    $('#host-delete-popup').removeClass('blind');
    $('.popup-background:eq(0)').removeClass('blind');
  });

  $('#host-delete-closeBtn').on('click', function(){
    $('#host-delete-popup').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });

  /** 삭제 요청 버튼 **/
  $("#delete-host-btn").on('click', function(){
    $.ajax({
      url : "/rence/backoffice_setting_delete",
      type : "POST",
      dataType : 'json',
      data : {
        backoffice_no : $.cookie("backoffice_no")
      },
      success : function(res) {
          console.log(res.result);
          // 호스트 삭제 성공
          if(res.result == 1){
            $('.popup-background:eq(0)').addClass('blind');
            $('#host-delete-popup').addClass('blind');

            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("마스터에게 삭제 요청되었습니다.");
          }else{
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("남은 예약이 존재하여 삭제할 수 없습니다.");
          }
      },
      error : function() {
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
      }
    });
  });

  /** 호스트 로그아웃 요청 버튼 -> 팝업 띄우기 **/
  $('#btn-host-logout').on("click" ,function(){
    $('.popup-background:eq(0)').removeClass('blind');
    $('#logout-popup').removeClass('blind');
  });


  /** 비밀번호 변경 팝업 닫기 버튼*/
  $("#btn-popup-close").on("click",function(){
    $(".input-check-pw").removeClass("null-input-border");
    $(".input-check-pw").val("");
  });

  /** 현재 비밀번호 일치 여부 확인 버튼*/
  $("#btn-popup-confirm").on("click",function(){
    if($(".input-check-pw").val().trim().length > 0){
      $.ajax({
        url : "/rence/backoffice_update_pw",
        type : "GET",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no"),
          backoffice_pw : CryptoJS.SHA256($(".input-check-pw").val().trim()).toString()
        },
        success : function(res) {
            // 현재 비밀번호 일치 성공
            if(res.result == 1){
              location.href="/rence/backoffice_setting_pw?backoffice_no=" + window.btoa($.cookie("backoffice_no"));
            }else if(res.result == 0){
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("일치하지않는 비밀번호입니다.");

              $(".input-check-pw").removeClass("null-input-border");
              $(".input-check-pw").val("");
            }
        },
        error : function() {
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
      });
    }else{
      $(".input-check-pw").addClass("null-input-border");
    }
  });
  
  
  
  /****************************** */
  /***********FUNCTION*********** */
  /****************************** */

  function insert(){
    $.ajax({
      url:"/rence/backoffice_insertOK_room",
        type : "POST",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no"),
          room_name : $("#input-room-name").val().trim(),
          room_type : $('#edit_room_type').val(),
          room_price : $("#input-price-name").val().trim()
        },
        success : function(res) {
            // 변경 성공
            if(res.result == 1){
                // input 초기화
                $('#edit_room_type').val('');
                $("#input-room-name").val('');
                $("#input-price-name").val("");
                $("#insert-room-type-label").text('타입을 선택해주세요.');

                $('#insert-room-type-label').css('color', '#808080');

                // 경고 테두리 초기화
                $("#input-room-name").removeClass("null-input-border");
                $("#input-price-name").removeClass("null-input-border");
                $("#room-type-select").removeClass("null-input-border");

                $('.popup-background:eq(0)').addClass('blind');
                $('#room-insert-section').addClass('blind');

                var sample = $(".insert-type-select-item:eq(0)").clone();
                $(".insert-type-select-list").empty().append(sample);

                $(".popup-background:eq(0)").removeClass("blind");
                $("#insert-success-alert-popup").removeClass("blind");
            }else{
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("수정에 실패하였습니다.");
            }
        },
        error : function() {
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
    });
  }

  function update(){
    $.ajax({
      url:"/rence/backoffice_updateOK_room",
        type : "POST",
        dataType : 'json',
        data : {
          backoffice_no : $.cookie("backoffice_no"),
          room_no : $('#btn-edit').attr("idx"),
          room_name : $("#m-input-room-name").val().trim(),
          room_type : $('#m-edit_room_type').val(),
          room_price : $("#m-input-price-name").val().trim()
        },
        success : function(res) {
            // 변경 성공
            if(res.result == 1){
                // input 초기화
                $('#m-edit_room_type').val('');
                $("#m-input-room-name").val('');
                $("#m-input-price-name").val("");
                $("#m-edit-room-type-label").text('타입을 선택해주세요.');

                $('#m-edit-room-type-label').css('color', '#808080');

                // 경고 테두리 초기화
                $("#m-input-room-name").removeClass("null-input-border");
                $("#m-input-price-name").removeClass("null-input-border");
                $("#m-room-type-select").removeClass("null-input-border");

                $('#room-edit-section').addClass('blind');
                $('.popup-background:eq(0)').addClass('blind');

                var sample = $(".edit-type-select-item:eq(0)").clone();
                $(".edit-type-select-list").empty().append(sample);

                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("수정이 완료되었습니다.");
            }else{
                $(".popup-background:eq(1)").removeClass("blind");
                $("#common-alert-popup").removeClass("blind");
                $(".common-alert-txt").text("수정에 실패하였습니다.");
            }
        },
        error : function() {
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
        }
    });
  }

});