/**
* @author 김예은
*/
$(function(){
    $("#go-home-btn").click(function(){
        location.href="/rence/";
    });
    

    $(".reserve-box").click(function(){
        if($(".timePoint-value").attr("time-point") == "before"){
            location.href = "/rence/reserved_info?reserve_no="+$(this).attr("idx");
        }else if($(".timePoint-value").attr("time-point") == "now"){
            location.href = "/rence/reserve_info?reserve_no="+$(this).attr("idx");
        }
    });

    $(".timePoint-custom-select-li").click(function(){
        $(".timePoint-value").text($(this).text());

        if($(this).attr("id") == "timePoint-now"){
            $(".timePoint-value").attr("time-point", "now");
            location.href="/rence/reserve_list?time_point=now&user_no="+$.cookie("user_no");
        }else if($(this).attr("id") == "timePoint-before"){
            $(".timePoint-value").attr("time-point", "before");
            location.href="/rence/reserve_list?time_point=before&user_no="+$.cookie("user_no");
        }

        $(".timePoint-custom-select-wrap").addClass("blind");
    });
    
    $(".timePoint-select-wrap").click(function(){
        $(".timePoint-custom-select-wrap").toggleClass("blind");
    });
});