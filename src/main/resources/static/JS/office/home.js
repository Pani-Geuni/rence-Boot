/**
* @author 김예은
*/
$(function(){
    $(".type-list").click(function(){
        if($(this).hasClass("type-desk")){
            location.href = "/office/list_page?type=desk&condition=date&page=1";
        }
        if($(this).hasClass("type-meeting-room")){
            location.href = "/office/list_page?type=meeting_room&condition=date&page=1";
        }
        if($(this).hasClass("type-office")){
            location.href = "/office/list_page?type=office&condition=date&page=1";
        }
    });
});