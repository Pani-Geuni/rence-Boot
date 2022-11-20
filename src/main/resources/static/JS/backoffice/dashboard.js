/**
 * @author : 전판근, 김예은
 */
 $(function () {
  $('.logo-mku').click(function(){
    location.href = '/backoffice/landing';
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
    case '/backoffice/main':
      $('#menu-home').addClass('active');
      break;

    case '/backoffice/room':
      $('#menu-space').addClass('active');
      $('#mini-nav-list').addClass('active');
      break;

    case '/backoffice/qna':
      $('#menu-space').addClass('active');
      $('#mini-nav-qna').addClass('active');
      break;

    case '/backoffice/review':
      $('#menu-space').addClass('active');
      $('#mini-nav-review').addClass('active');
      break;
      
    case '/backoffice/schedule':
      $('#menu-schedule').addClass('active');
      break;

    case '/backoffice/reserve':
    case '/rence/backoffice_search_reserve':
      $('#menu-reserve').addClass('active');
      break;

    case '/backoffice/day_sales':
      $('#menu-sales').addClass('active');
      break;

    case '/backoffice/settings':
      $('#menu-settings').addClass('active');
      break;

    default:
      break;
  }

  // *****************
  // 좌측 공통 Side Menu
  // *****************
  $('#menu-home').click(function(){
    location.href = '/backoffice/main?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#menu-space').click(function(){
    location.href = '/backoffice/room?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#menu-reserve').click(function(){
    location.href = "/backoffice/reserve?backoffice_no=" + $.cookie("backoffice_no") + "&reserve_state=all";
  });

  $('#menu-sales').click(function(){
    location.href = '/backoffice/day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=day";
  });

  $('#menu-settings').click(function(){
    location.href = '/backoffice/settings?backoffice_no=' + $.cookie("backoffice_no");
  });

  // ****************
  // 공간 관리 mini-nav
  // ****************
  $('#mini-nav-list').click(function(){
    location.href = '/backoffice/room?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#mini-nav-qna').click(function(){
    location.href = '/backoffice/qna?backoffice_no=' + $.cookie("backoffice_no");
  });

  $('#mini-nav-review').click(function(){
    location.href = '/backoffice/review?backoffice_no=' + $.cookie("backoffice_no");
  });

  // ****************
  // 정산 관리 mini-nav
  // ****************
  $('#sales-mini-nav-day').click(function(){
    location.href = '/backoffice/day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=day";
  });

  $('#sales-mini-nav-week').click(function(){
    location.href = '/backoffice/day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=week";
  });

  $('#sales-mini-nav-month').click(function(){
    location.href = '/backoffice/day_sales?backoffice_no=' + $.cookie("backoffice_no") + "&sales_date=month";
  });
});