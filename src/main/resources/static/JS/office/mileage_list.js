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
    
    $(".paging-num-wrap").on("click", ".paging-box.paging-num", function(){
        if(location.href.includes("/rence/mileage?")){
            location.href = "/rence/mileage?user_no="+$.cookie("user_no")+"&page=" + $(this).attr("idx");
        }else{
            var searchKey = location.href.split("/rence/mileage_search_list?searchKey=")[1].split("&user_no")[0];
            location.href = "/rence/mileage_search_list?searchKey="+ searchKey +"&user_no="+$.cookie("user_no")+"&page=" + $(this).attr("idx");
        }
    });
    
    // 다음 페이지 리스트로 이동
    $(".next-page-btn").click(function(){
		var start = Number($($(".paging-box.paging-num")[0]).text()) + 5;
		var last = Number($($(".paging-box.paging-num")[4]).text()) + 5;
		var totalPageCnt = Number($("#totalPageCnt").val());
		
		if($(".before-page-btn").hasClass("hide")){
			$(".before-page-btn").removeClass("hide");
		}
		
		if(last >= totalPageCnt){
			last = totalPageCnt;
			$(".next-page-btn").addClass("hide");
		}
		
		var sample = $(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".paging-num-wrap").append(sample_span);
		}
	});
	
    // 이전 페이지 리스트로 이동
    $(".before-page-btn").click(function(){
		var start = Number($($(".paging-box.paging-num")[0]).text()) - 5;
		var last = Number($(".paging-box.paging-num:last").text()) - 5;
		
		if(last % 5 != 0){
			last += 5 - (last % 5);
		}
		
		if($(".next-page-btn").hasClass("hide")){
			$(".next-page-btn").removeClass("hide");
		}
		
		if(start == 1){
			$(".before-page-btn").addClass("hide");
		}
		
		var sample = $(".paging-num-wrap>.paging-box.paging-num:eq(0)").clone();
		$(".paging-num-wrap").empty();
		
		for(var i = start; i <= last; i++){
			var sample_span = sample.clone();

			sample_span.text(i);
			sample_span.attr("idx", i);
			sample_span.removeClass("choice");
			sample_span.addClass("un-choice");
			
			$(".paging-num-wrap").append(sample_span);
		}
	});
});