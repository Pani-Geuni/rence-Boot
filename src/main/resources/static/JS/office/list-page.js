/**
 * @author 김예은
 */
 $(function(){
    // 다른 공간 보기 버튼 - 메인 페이지로 이동
    $("#go-main-btn").click(function(){
        location.href="/";
    });

    //정렬 셀렉트 클릭 시 커스텀 셀렉트 SHOW
    $(".sort").click(function(){
        $(".sort-select-box-wrap").toggleClass("blind");
    });

    // 정렬 조건 클릭 시 데이터 요청
    $(".sort-select-list").on("click", function(){
        if(location.href.includes("list_page")){
            var type = location.href.split("?type=")[1].split("&")[0];
            location.href = "/office/list_page?type=" + type + "&condition=" + $(this).attr("condition") + "&page=1";
            console.log("type : " + type + ", condition : " + $(this).attr("condition"));
        }else{
            var url = location.href.split("&condition=")[0];
            location.href= url+"&condition="+$(this).attr("condition") + "&page=1";
        }
    });

    $(".listPage-wrap").scroll(function(){
        if(Math.ceil($(this).scrollTop() + $(this).innerHeight()) >= $(this).prop('scrollHeight')){
            if($(".list-box").length < Number($("#maxCnt").attr("maxCnt"))){
                //로딩 화면
                $(".popup-background:eq(1)").removeClass("blind");
                $("#spinner-section").removeClass("blind");  

                if(window.location.href.includes("list_page")){
                    var type = window.location.href.split("type=")[1].split("&")[0];
                    var condition = window.location.href.split("condition=")[1].split("&")[0];
                    var page = $("#maxCnt").attr("nowCnt");
    
                    $.ajax({
                        url : "/office/list_paging",
                        type : "GET",
                        dataType : 'json',
                        data : {
                            type : type,
                            page : Number(page) + 1,
                            condition : condition
                        },
                        success : function(res) {
                            //로딩 화면 닫기
                            $(".popup-background:eq(1)").addClass("blind");
                            $("#spinner-section").addClass("blind");
    
                            var now = $("#maxCnt").attr("nowCnt");
                            $("#maxCnt").attr("nowCnt", Number(now) + 1);

                            for(var i = 0; i < res.cnt; i++){
                                var box = $($(".list-box")[0]).clone();
                                box.attr("idx", res.list[i].backoffice_no);
                                box.find(".list-thumbnail").attr("src", res.list[i].backoffice_image);
                                box.find(".box-info-c-name").text(res.list[i].company_name);
                                box.find(".box-info-location").text(res.list[i].roadname_address);
                                box.find(".box-tag").text(res.list[i].backoffice_tag);
                                box.find(".box-room-min-price").text("최소 " + res.list[i].min_room_price);
                                box.find(".rating-num").text(res.list[i].avg_rating);
    
                                $(".list-box-wrap").append(box);
                            }
                        },
                        error : function() {
                            //로딩 화면 닫기
                            $(".popup-background:eq(1)").addClass("blind");
                            $("#spinner-section").addClass("blind");
                        }
                    });
                }else{
                    var type = window.location.href.split("type=")[1].split("&")[0];
                    var location = window.location.href.split("location=")[1].split("&")[0];
                    var searchWord = window.location.href.split("searchWord=")[1].split("&")[0];
                    var condition = window.location.href.split("condition=")[1].split("&")[0];
                    var page = $("#maxCnt").attr("nowCnt");
    
                    $.ajax({
                        url : "/office/search_list_paging",
                        type : "GET",
                        dataType : 'json',
                        data : {
                            type : type,
                            location : location,
                            searchWord : searchWord,
                            page : Number(page) + 1,
                            condition : condition
                        },
                        success : function(res) {
                            //로딩 화면 닫기
                            $(".popup-background:eq(1)").addClass("blind");
                            $("#spinner-section").addClass("blind");
    
                            var now = $("#maxCnt").attr("nowCnt");
                            $("#maxCnt").attr("nowCnt", Number(now) + 1);

                            for(var i = 0; i < res.cnt; i++){
                                var box = $($(".list-box")[0]).clone();
                                box.attr("idx", res.list[i].backoffice_no);
                                box.find(".list-thumbnail").attr("src", res.list[i].backoffice_image);
                                box.find(".box-info-c-name").text(res.list[i].company_name);
                                box.find(".box-info-location").text(res.list[i].roadname_address);
                                box.find(".box-tag").text(res.list[i].backoffice_tag);
                                box.find(".box-room-min-price").text("최소 " + res.list[i].min_room_price);
                                box.find(".rating-num").text(res.list[i].avg_rating);
    
                                $(".list-box-wrap").append(box);
                            }
                        },
                        error : function() {
                            //로딩 화면 닫기
                            $(".popup-background:eq(1)").addClass("blind");
                            $("#spinner-section").addClass("blind");
                        }
                    });
                }
            }
        }
    });

    // 공간 소개 페이지 이동
    $(".listPage-wrap").on("click", ".list-box", function(){
        var backoffice_no = $(this).attr("idx");
        var type = window.location.href.split("?type=")[1].split("&")[0];
        
        if(type != "office")
            window.location.href="/office/space_introduce?backoffice_no="+backoffice_no+"&introduce_menu=info";
        else 
            window.location.href="/office/space_introduce_office?backoffice_no="+backoffice_no+"&introduce_menu=info";
    });
});