/**
 * @author : 김예은
 */

$(function () {
  /** 파일 추가할 시 */
  $("#multipartFile_room").change(function(){
    console.log($('#multipartFile_room').get(0).files[0]);

    var img_name = "";
    var length = $('#multipartFile_room').get(0).files.length;

    for(var i = 0; i < length; i++){
      var type = $('#multipartFile_room').get(0).files[i].type;
      if(type == "image/jpeg" || type == "image/jpg" || type == "image/png"){
        img_name += $('#multipartFile_room').get(0).files[i].name;
        if(i != length -1){
          img_name += " / ";
        }
      }else{
        $(".popup-background:eq(1)").removeClass("blind");
        $("#common-alert-popup").removeClass("blind");
        $(".common-alert-txt").text("jpg, jpeg, png 확장자만 선택가능합니다.");
      }
    }

    console.log(img_name);

    $(".upload-name").val(img_name);
  });
})
