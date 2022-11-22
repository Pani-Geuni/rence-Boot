/**
* @author 김예은
*/
$(function(){
    $(".menus").click(function(){
        $(".menus").removeClass("choice");
        $(".menus").addClass("un-choice");

        $(this).removeClass("un-choice");
        $(this).addClass("choice");

        location.href = "/rence/mileage_search_list?searchKey="+$(this).attr("id")+"&user_no="+$.cookie("user_no")+"&page=1";
    });
    
    $(".paging-box.paging-num").click(function(){
        if(location.href.includes("/rence/mileage?")){
            location.href = "/rence/mileage?user_no="+$.cookie("user_no")+"&page=" + $(this).attr("idx");
        }else{
            var searchKey = location.href.split("/rence/mileage_search_list?searchKey=")[1].split("&user_no")[0];
            location.href = "/rence/mileage_search_list?searchKey="+ searchKey +"&user_no="+$.cookie("user_no")+"&page=" + $(this).attr("idx");
        }
    });
});