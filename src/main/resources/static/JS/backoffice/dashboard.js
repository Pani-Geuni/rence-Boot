/**
 * @author : 전판근, 김예은
 */
 $(function () {
  $('.logo-mku').click(function(){
    location.href = '/rence/backoffice_landing';
  });

  $('#go-rence-home').click(function(){
    location.href = '/rence/';
  });

  // ****************************************
  // Dash Board Side Menu 현재 페이지 active 설정
  // ****************************************
  // backoffice_main 일 때

  switch ($(location).attr('pathname')) {
    // 좌측 Side Menu 분기
    case '/rence/backoffice_main':
      $('#menu-home').addClass('active');
      break;

    case '/rence/backoffice_room':
      $('#menu-space').addClass('active');
      $('#mini-nav-list').addClass('active');
      break;

    case '/rence/backoffice_qna':
      $('#menu-space').addClass('active');
      $('#mini-nav-qna').addClass('active');
      break;

    case '/rence/backoffice_review':
      $('#menu-space').addClass('active');
      $('#mini-nav-review').addClass('active');
      break;

    case '/rence/backoffice_reserve':
    case '/rence/backoffice_search_reserve':
      $('#menu-reserve').addClass('active');
      break;

    case '/rence/backoffice_day_sales':
      $('#menu-sales').addClass('active');
      $('#sales-mini-nav-day').addClass('active');
      break;

    case '/rence/backoffice_week_sales':
      $('#menu-sales').addClass('active');
      $('#sales-mini-nav-week').addClass('active');
      break;

    case '/rence/backoffice_month_sales':
      $('#menu-sales').addClass('active');
      $('#sales-mini-nav-month').addClass('active');
      break;

    case '/rence/backoffice_settings':
      $('#menu-settings').addClass('active');
      break;

    default:
      break;
  }

  // *****************
  // 좌측 공통 Side Menu
  // *****************
  $('#menu-home').click(function(){
    location.href = '/rence/backoffice_main?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#menu-space').click(function(){
    location.href = '/rence/backoffice_room?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#menu-reserve').click(function(){
    location.href = "/rence/backoffice_reserve?backoffice_no=" + $.cookie("backoffice_no") + "&reserve_state=all";
  });

  $('#menu-sales').click(function(){
    location.href = '/rence/backoffice_day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=day";
  });

  $('#menu-settings').click(function(){
    location.href = '/rence/backoffice_settings?backoffice_no=' + $.cookie("backoffice_no");
  });

  // ****************
  // 공간 관리 mini-nav
  // ****************
  $('#mini-nav-list').click(function(){
    location.href = '/rence/backoffice_room?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#mini-nav-qna').click(function(){
    location.href = '/rence/backoffice_qna?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#mini-nav-review').click(function(){
    location.href = '/rence/backoffice_review?backoffice_no=' + $.cookie("backoffice_no");
  });

  // ****************
  // 정산 관리 mini-nav
  // ****************
  $('#sales-mini-nav-day').click(function(){
    location.href = '/rence/backoffice_day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=day";
  });

  $('#sales-mini-nav-week').click(function(){
    location.href = '/rence/backoffice_day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=week";
  });

  $('#sales-mini-nav-month').click(function(){
    location.href = '/rence/backoffice_day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=month";
  });
});