/**
* @author 김예은
*/
$(function(){
    $(".type-list").click(function(){
        if($(this).hasClass("type-desk")){
            location.href = "/list_page?type=desk&condition=date"
            // location.href = "/office/list_page?type=desk&condition=date"
        }
        if($(this).hasClass("type-meeting-room")){
            location.href = "/list_page?type=meeting_room&condition=date"
            // location.href = "/office/list_page?type=meeting_room&condition=date"
        }
        if($(this).hasClass("type-office")){
            location.href = "/list_page?type=office&condition=date"
            // location.href = "/office/list_page?type=office&condition=date"
        }
    });
});