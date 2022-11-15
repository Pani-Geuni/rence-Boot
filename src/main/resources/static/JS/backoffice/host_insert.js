/**
 * @author : 전판근, 김예은
 */

// ******************
// 호스트 신청에 필요한 JS
// ******************

$(function () {
  /** 공용 알러트 창닫기 버튼 */ 
  $("#common-alert-btn").click(function(){
    $(".popup-background:eq(1)").addClass("blind");
    $("#common-alert-popup").addClass("blind");
  });

  $("input, textarea").click(function(){
    if($(this).hasClass("null-input-border")){
      $(this).removeClass("null-input-border");
    }
  });

  // 빈 항목 팝업 닫기
  $('#empty-fail-alert-btn').click(function(){
    $('#fail-alert-popup').addClass('blind');
    $('.popup-background:eq(0)').addClass('blind');
  });


  // TIME PICKER
  $('.time-picker').timepicker({
    timeFormat: 'HH:mm',
    interval: 60,
    defaultTime: '09',
    dynamic: false,
    dropdown: true,
    scrollbar: true,
  })

  // 휴무일 체크시 timepicker block
  $('#sun_dayoff').click(function(){
    if (this.checked) {
      $('#sun_stime').attr('disabled', true);
      $('#sun_etime').attr('disabled', true);
    } else {
      $('#sun_stime').attr('disabled', false);
      $('#sun_etime').attr('disabled', false);
    }
  })

  $('#mon_dayoff').click(function(){
    if (this.checked) {
      $('#mon_stime').attr('disabled', true);
      $('#mon_etime').attr('disabled', true);
    } else {
      $('#mon_stime').attr('disabled', false);
      $('#mon_etime').attr('disabled', false);
    }
  })

  $('#tue_dayoff').click(function(){
    if (this.checked) {
      $('#tue_stime').attr('disabled', true);
      $('#tue_etime').attr('disabled', true);
    } else {
      $('#tue_stime').attr('disabled', false);
      $('#tue_etime').attr('disabled', false);
    }
  })

  $('#wed_dayoff').click(function(){
    if (this.checked) {
      $('#wed_stime').attr('disabled', true);
      $('#wed_etime').attr('disabled', true);
    } else {
      $('#wed_stime').attr('disabled', false);
      $('#wed_etime').attr('disabled', false);
    }
  })

  $('#thu_dayoff').click(function(){
    if (this.checked) {
      $('#thu_stime').attr('disabled', true);
      $('#thu_etime').attr('disabled', true);
    } else {
      $('#thu_stime').attr('disabled', false);
      $('#thu_etime').attr('disabled', false);
    }
  })

  $('#fri_dayoff').click(function(){
    if (this.checked) {
      $('#fri_stime').attr('disabled', true);
      $('#fri_etime').attr('disabled', true);
    } else {
      $('#fri_stime').attr('disabled', false);
      $('#fri_etime').attr('disabled', false);
    }
  })

  $('#sat_dayoff').click(function(){
    if (this.checked) {
      $('#sat_stime').attr('disabled', true);
      $('#sat_etime').attr('disabled', true);
    } else {
      $('#sat_stime').attr('disabled', false);
      $('#sat_etime').attr('disabled', false);
    }
  });

  /** 공간 타입 체크 박스 - 데스크/회의룸 둘 중 하나라도 체크 시 오피스는 체크할 수 없음 */
  $("#type_checkbox_desk, #type_checkbox_meeting_room").click(function(){
    $("#type_checkbox_office").attr("disabled", true);
    $("#type_checkbox_office").siblings("label").css("text-decoration", "line-through");

    if(!$("#type_checkbox_desk").is(':checked') && !$("#type_checkbox_meeting_room").is(':checked')){
      $("#type_checkbox_office").attr("disabled", false);
      $("#type_checkbox_office").siblings("label").css("text-decoration", "none");
    }
  });

  /** 공간 타입 체크 박스 - 오피스 체크 시 데스크/회의룸은 체크할 수 없음 */
  $("#type_checkbox_office").click(function(){
    $("#type_checkbox_desk").attr("disabled", true);
    $("#type_checkbox_desk").siblings("label").css("text-decoration", "line-through");
    $("#type_checkbox_meeting_room").attr("disabled", true);
    $("#type_checkbox_meeting_room").siblings("label").css("text-decoration", "line-through");
    
    
    if(!$("#type_checkbox_office").is(':checked')){
      $("#type_checkbox_desk").attr("disabled", false);
      $("#type_checkbox_desk").siblings("label").css("text-decoration", "none");
      $("#type_checkbox_meeting_room").attr("disabled", false);
      $("#type_checkbox_meeting_room").siblings("label").css("text-decoration", "none");
    }
  });


  /** 사업자 번호 형식 맞는지 확인 */
  $("#backoffice_id").on('keyup keydown', function(){
    /* 사업자등록번호 */
    var regExp = /^([0-9]{3})-?([0-9]{2})-?([0-9]{5})$/;
    if(!regExp.test($("#backoffice_id").val().trim())){
      $(".warning-text:eq(0)").removeClass("blind");
    }else{
      $(".warning-text:eq(0)").addClass("blind");
    }
  });

  /** 전화번호 형식 맞는지 확인 */
  $("#backoffice_tel").on('keyup keydown', function(){
    /* 유전 전화 + 휴대폰 번호 */
    var telExp = /^(0[2-8][0-5]?|01[01346-9])-?([1-9]{1}[0-9]{2,3})-?([0-9]{4})$/;
    
    /* 대표전화번호 1588 등 */
    var telExp2 =/^(1544|1566|1577|1588|1644|1688)-?([0-9]{4})$/;
    
    if(!telExp.test($("#backoffice_tel").val().trim()) && !telExp2.test($("#backoffice_tel").val().trim())){
      $(".warning-text:eq(1)").removeClass("blind");
    }else{
      $(".warning-text:eq(1)").addClass("blind");
    }

  });


  $("#backoffice_info").on("keydown keyup", function(){
    $(".b_info_txt_length").text($(this).val().length);

    if($(this).val().length > 500){
      $(this).val($(this).val().substring(0,500));
    }
  });


  /** 인증번호 발송 버튼 클릭 **/
  $("#btn-certification").click(function(){
    if(!$("#btn-certification").prop("check")){
      if($("#backoffice_email").val().trim().length > 0){
        var email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        
        if(email.test($("#backoffice_email").val().trim())){
          $.ajax({
            url:"/rence/backoffice_auth",
            type : "GET",
            dataType : 'json',
            data : {
              backoffice_email : $("#backoffice_email").val().trim()
            },
            success : function(res) {
                // 이메일 중복 성공
                if(res.result == 1){
                    $("#btn-certification").prop("check", true);
                    $("#btn-certification").val("인증완료");
                    $("#backoffice_email").attr("readonly", true);
                    $("#backoffice_email").addClass("readOnly");
  
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("이메일로 인증번호를 발송하였습니다.");
                }else{
                    $(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("이미 존재하는 이메일입니다.");
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
      }
      else{
        $("#backoffice_email").addClass("null-input-border");
      }
    }
  });

  /** 인증번호 확인 버튼 클릭 **/
  $("#btn-check-certification").click(function(){
    if(!$("#btn-check-certification").prop("check")){
      if($("#auth_code").val().trim().length > 0){
        $.ajax({
          url:"/rence/backoffice_authOK",
          type : "POST",
          dataType : 'json',
          data : {
            backoffice_email : $("#backoffice_email").val().trim(),
            auth_code : $("#auth_code").val().trim()
          },
          success : function(res) {
              // 이메일 중복 성공
              if(res.result == 1){
                  $("#btn-check-certification").prop("check", true);
                  $("#btn-check-certification").val("인증완료");
                  $("#auth_code").attr("readonly", true);
                  $("#auth_code").addClass("readOnly");
  
                  $(".popup-background:eq(1)").removeClass("blind");
                  $("#common-alert-popup").removeClass("blind");
                  $(".common-alert-txt").text("인증완료 되었습니다.");
              }else{
                  $(".popup-background:eq(1)").removeClass("blind");
                  $("#common-alert-popup").removeClass("blind");
                  $(".common-alert-txt").text("인증에 실패하였습니다.");
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
        $("#auth_code").addClass("null-input-border");
      }
    }
  });


  /** 호스트 신청 버튼 클릭 */ 
  $("#submit").click(function(){
    // 1. 필수 input / textarea 입력되었는지 확인
    if(
      $("#owner_name").val().trim().length > 0 && $("#backoffice_id").val().trim().length > 0 && 
      $("#backoffice_name").val().trim().length > 0 && $("#company_name").val().trim().length > 0 &&
      $("#backoffice_tel").val().trim().length > 0 && $("#backoffice_email").val().trim().length > 0 &&
      $("#auth_code").val().trim().length > 0 && $("#zipcode").val().trim().length > 0 && 
      $("#backoffice_info").val().trim().length > 0
    ){
      // 2. 정규표현식을 모두 만족하는지 확인
      if($(".warning-text:eq(0)").hasClass("blind") && $(".warning-text:eq(1)").hasClass("blind")){
        // 3. 이메일 인증 완료 되었는지 확인
        if($("#btn-certification").prop("check") && $("#btn-check-certification").prop("check")){
          // 4. 공간 타입을 선택했는지 확인
          var desk_checked = $('#type_checkbox_desk').is(':checked');
          var meeting_room_checked = $('#type_checkbox_meeting_room').is(':checked');
          var office_checked = $('#type_checkbox_office').is(':checked');
    
          if (desk_checked || meeting_room_checked || office_checked) {
            $("#real-submit").click();
          } else {
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("공간 타입을 선택해주세요.");
          }
            
        }else{
          $(".popup-background:eq(1)").removeClass("blind");
          $("#common-alert-popup").removeClass("blind");
          $(".common-alert-txt").text("이메일 인증을 완료해주세요.");
        }
      }

    }
    else{
      if($("#owner_name").val().trim().length == 0){
        $("#owner_name").addClass("null-input-border");
      }
      if($("#backoffice_id").val().trim().length == 0){
        $("#backoffice_id").addClass("null-input-border");
      }
      if($("#backoffice_name").val().trim().length == 0){
        $("#backoffice_name").addClass("null-input-border");
      }
      if($("#company_name").val().trim().length == 0){
        $("#company_name").addClass("null-input-border");
      }
      if($("#backoffice_tel").val().trim().length == 0){
        $("#backoffice_tel").addClass("null-input-border");
      }
      if($("#backoffice_email").val().trim().length == 0){
        $("#backoffice_email").addClass("null-input-border");
      }
      if($("#auth_code").val().trim().length == 0){
        $("#auth_code").addClass("null-input-border");
      }
      if($("#zipcode").val().trim().length == 0){
        $("#zipcode").addClass("null-input-border");
        $("#roadname_address").addClass("null-input-border");
        $("#number_address").addClass("null-input-border");
      }
      if($("#backoffice_info").val().trim().length == 0){
        $("#backoffice_info").addClass("null-input-border");
      }
    }

  });

});