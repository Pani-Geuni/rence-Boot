/**
* @author 김예은
*/
$(function(){
    $("#go-home-btn").click(function(){
        location.href="/";
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
            location.href="/rence/reserve_list?time_point=now&user_no="+$.cookie("user_no")+"&page=1";
        }else if($(this).attr("id") == "timePoint-before"){
            $(".timePoint-value").attr("time-point", "before");
            location.href="/rence/reserve_list?time_point=before&user_no="+$.cookie("user_no")+"&page=1";
        }

        $(".timePoint-custom-select-wrap").addClass("blind");
    });
    
    $(".timePoint-select-wrap").click(function(){
        $(".timePoint-custom-select-wrap").toggleClass("blind");
    });

    // 페이지 번호 클릭 이벤트
    $(".paging-num-wrap").on("click", ".paging-box.paging-num", function(){
		//로딩 화면
		$(".popup-background:eq(1)").removeClass("blind");
		$("#spinner-section").removeClass("blind");
		
        location.href = "/rence/reserve_list?time_point=" + window.location.href.split("time_point=")[1].split("&")[0] + "&user_no="+ $.cookie("user_no") + "&page=" + $(this).attr("idx");
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