/**
* @author 김예은
*/
$(function(){
    $(".menus").click(function(){
        $(".menus").removeClass("choice");
        $(".menus").addClass("un-choice");

        $(this).removeClass("un-choice");
        $(this).addClass("choice");

        location.href = "/rence/mileage_search_list?searchKey="+$(this).attr("id")+"&user_no="+$.cookie("user_no");
    });
    
    $(".paging-box.paging-num").click(function(){
        location.href = "/rence/mileage?user_no="+$.cookie("user_no")+"&page=" + $(this).attr("idx");
    });
});