/**
 * @author 김예은
 */
$(function(){

    /** 문의 리스트 클릭 시, 문의 내용 상세창 SHOW / HIDE **/ 
    $(".question-content-wrap").click(function(){
        $(this).siblings(".detail-question-wrap").toggleClass("blind");
    });

    /** 댓글 삭제 버튼 -> 댓글 컨펌 팝업 SHOW */
    $(".question-d-btn").click(function(){
        var idx = $(this).attr("idx");
        $(".popup-background:eq(0)").removeClass("blind");
        $("#q-delete-popup").removeClass("blind");
        $("#q-delete-closeBtn").attr("idx", idx);
    });
    
    /** 댓글 컨펌 팝업 - 삭제 버튼 */
    $("#q-delete-btn").click(function(){
        $(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
        
        location.href="/rence/delete_comment?user_no=" + $.cookie("user_no") + "&comment_no=" + $(this).attr("idx");
    });

    /** 댓글 컨펌 팝업 - 창닫기 버튼 */
    $("#q-delete-closeBtn").click(function(){
        $(".popup-background:eq(0)").addClass("blind");
        $("#q-delete-popup").addClass("blind");
    });

    // 페이지 번호 클릭 이벤트
    $(".paging-box.paging-num").click(function(){
        location.href = "/rence/question_list?user_no=" + $.cookie("user_no") + "&page=" + $(this).attr("idx");
    });
});