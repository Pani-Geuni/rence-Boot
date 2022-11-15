/**
 * @author 김예은
 */
$(function() {
  $("#input_searchBar").on('keydown', function(e){
    if(e.keyCode == 13){
      var reserve_state = "";
      switch($(".reserve-item.active").text()){
        case "전체" :
          reserve_state = "all";
          break;
        case "예약중" :
          reserve_state = "in_use";
          break;
        case "취소" :
          reserve_state = "cancel";
          break;
        case "종료" :
          reserve_state = "end";
          break;
      }
      location.href="/rence/backoffice_search_reserve?backoffice_no=" + $.cookie("backoffice_no") + "&reserve_state="+ reserve_state + "&searchword=" + $("#input_searchBar").val().trim();
    }
  });

  // 미니 메뉴 부분 클릭 이벤트
  $(".reserve-item").click(function(){
    var reserve_state = "";
      switch($(this).text()){
        case "전체" :
          reserve_state = "all";
          break;
        case "예약중" :
          reserve_state = "in_use";
          break;
        case "취소" :
          reserve_state = "cancel";
          break;
        case "종료" :
          reserve_state = "end";
          break;
      }
    location.href="/rence/backoffice_reserve?backoffice_no=" + $.cookie("backoffice_no") + "&reserve_state="+ reserve_state;
  });
});