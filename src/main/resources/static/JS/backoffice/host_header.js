/**
* @author : 전판근, 김예은
*/
$(function () {

  /** 로고 버튼 클릭 시 메인 페이지 이동 **/ 
  $('.logo-mku').click(function(){
    location.href = '/rence/backoffice_landing';
  });

  /** 로그인 전 헤더 메뉴 */
  $('#before_hostMenu').click(function(){
    $('#before_login>.custom-select-host').toggleClass('blind');
  });
  /** 로그인 후 헤더 메뉴 */
  $('#after_hostMenu').click(function(){
    $('#after_login>.custom-select-host').toggleClass('blind');
  });
  

  /** 로그인 메뉴 - 로그인 팝업 띄움 */
  $('#go-login').click(function(){
    $('#before_login>.custom-select-host').addClass('blind');
    $('.popup-background:eq(0)').removeClass('blind');
    $('#login-section').removeClass('blind');
  });
  
  /** RENCE 페이지 이동 메뉴 */
  $('#go-user-home').click(function(){
    location.href = '/rence/';
  });
  
  /** 공간등록신청 메뉴 */
  $('#go-backOffice').click(function(){
    location.href = '/rence/backoffice_insert';
  });
  
  /** 호스트 메인 페이지 이동 */
  $('.btn-start-hosting').click(function(){
    if($.cookie("backoffice_no") != undefined)
      location.href = '/rence/backoffice_main?backoffice_no=' + $.cookie("backoffice_no");
    else{
      $(".popup-background:eq(1)").removeClass("blind");
      $("#common-alert-popup").removeClass("blind");
      $(".common-alert-txt").text("로그인 후 사용가능한 페이지입니다.");
    }
  });
  
  /** 호스트 신청 페이지 */
  $('.btn-apply-hosting').click(function () {
    location.href = '/rence/backoffice_insert';
  });

  /** 호스트 마이페이지 */
  $('#go-myPage').click(function () {
    $('#after_login>.custom-select-host').addClass('blind');
    location.href = '/rence/go_my_page';
  });
  
  /** 호스트 로그아웃 */
  $('#go-logOut').click(function () {
    $('#after_login>.custom-select-host').addClass('blind');
    $('#logout-popup').removeClass('blind');
    $('.popup-background:eq(0)').removeClass('blind');
  });


  // ==================
  // 비밀번호 재설정 input 체크
  // ==================
  $('.input-update-pw').on('keydown keyup', function () {
    if ($(this).attr('id') == 'input-update-pw') {
      var password = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-z0-9!@#$%^&*]{8,10}$/;
      if (!password.test($(this).val().trim())) {
        $('.pw-warning-text:eq(0)').removeClass('blind');
        $('.pw-warning-text:eq(0)').text( '비밀번호 조건 (소문자+숫자+특수문자 포함 8~10글자)');
      } else {
        $('.pw-warning-text:eq(0)').addClass('blind');

        if($('#input-update-pw-re').val().trim().length > 0){
          if ($(this).val().trim() != $('#input-update-pw-re').val().trim()) {
            $('.pw-warning-text:eq(1)').removeClass('blind')
            $('.pw-warning-text:eq(1)').text('위의 비밀번호와 일치하지 않습니다.');
          } else {
            $('.pw-warning-text:eq(1)').addClass('blind');
          }
        }
      }
    } else if ($(this).attr('id') == 'input-update-pw-re') {
      if ($(this).val().trim() != $('#input-update-pw').val().trim()) {
        $('.pw-warning-text:eq(1)').removeClass('blind');
        $('.pw-warning-text:eq(1)').text('위의 비밀번호와 일치하지 않습니다.');
      } else {
        $('.pw-warning-text:eq(1)').addClass('blind');
      }
    }
  })

  // 비밀번호 재설정 ajax
  $('#btn-pw-update').click(function () {
    var password = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,10}$/;
    let backoffice_no = $(location).attr('search').split('?backoffice_no=')[1];

    if ( $('#input-update-pw').val().trim().length > 0 && $('#input-update-pw-re').val().trim().length > 0) {
      if($('#input-update-pw').val().trim() == $('#input-update-pw-re').val().trim() && password.test($('#input-update-pw').val().trim())){
        $.ajax({
          url: '/rence/backoffice_settingOK_pw',
          type: 'POST',
          dataType: 'json',
          data: {
            backoffice_no: backoffice_no,
            backoffice_pw: CryptoJS.SHA256( $('#input-update-pw').val().trim()).toString()
          },
          success: function (res) {
            if (res.result == 1) {
              location.href = '/rence/backoffice_landing';
            } else {
              $(".popup-background:eq(1)").removeClass("blind");
              $("#common-alert-popup").removeClass("blind");
              $(".common-alert-txt").text("비밀번호 변경에 실패하였습니다.");
            }
          },
          error: function () {
            $(".popup-background:eq(1)").removeClass("blind");
            $("#common-alert-popup").removeClass("blind");
            $(".common-alert-txt").text("비밀번호 변경에 실패하였습니다.");
          },
        });
      } else {
        $('.popup-background:eq(0)').removeClass('blind');
        $('#fail-alert-popup').removeClass('blind');
      }
    }else{
      if($("#input-update-pw").val().trim().length == 0){
        $("#input-update-pw").addClass("null-input-border");
      }
      if($("#input-update-pw-re").val().trim().length == 0){
        $("#input-update-pw-re").addClass("null-input-border");
      }
    }
  })
});