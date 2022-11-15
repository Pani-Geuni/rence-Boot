/**
 * @author 김예은
 */
$(function(){
    // 다른 공간 보기 버튼 - 메인 페이지로 이동
    $("#go-main-btn").click(function(){
        location.href="/rence/";
    });

    //정렬 셀렉트 클릭 시 커스텀 셀렉트 SHOW
    $(".sort").click(function(){
        $(".sort-select-box-wrap").toggleClass("blind");
    });

    // 정렬 조건 클릭 시 데이터 요청
    $(".sort-select-list").click(function(){
        if(location.href.split("/rence/")[1].split("?")[0] == "list_page"){
            var type = location.href.split("?type=")[1].split("&")[0];
            location.href = "/rence/list_page?type=" + type + "&condition=" + $(this).attr("condition");
        }else if(location.href.split("/rence/")[1].split("?")[0] == "search_list"){
            var url = location.href.split("8090")[1].split("&condition=")[0];
            location.href= url+"&condition="+$(this).attr("condition");
        }
    });

    // 공간 소개 페이지 이동
    $(".list-box").click(function(){
        var backoffice_no = $(this).attr("idx");
        var type = location.href.split("?type=")[1].split("&")[0];
        
        if(type != "office")
            location.href="/rence/space_introduce?backoffice_no="+backoffice_no+"&introduce_menu=info";
        else 
            location.href="/rence/space_introduce_office?backoffice_no="+backoffice_no+"&introduce_menu=info";
    });
});