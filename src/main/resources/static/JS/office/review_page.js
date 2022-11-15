/**
* @author 김예은
*/
$(function(){
    $(".question-content-wrap").click(function(){
        $(this).siblings(".detail-question-wrap").toggleClass("blind");
    });

    $(".question-btn").click(function(){
        $(".popup-background:eq(0)").removeClass("blind");
        $("#q-delete-popup").removeClass("blind");
        $("#q-delete-btn").attr("idx", $(this).attr("idx"));
    });
    
    $("#q-delete-closeBtn").click(function(){
    	$(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
        $("#q-delete-btn").attr("idx", undefined);
    });
    $("#q-delete-btn").click(function(){
    	$(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
        $("#q-delete-btn").attr("idx", undefined);

        location.href = "/rence/delete_review?user_no=" + $.cookie("user_no") + "&review_no="+$("#q-delete-btn").attr("idx");
    });
});