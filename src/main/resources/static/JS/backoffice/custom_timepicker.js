/**
 * @author 전판근
 */

// ******************
// 호스트 신청에 필요한 JS
// ******************

$(function () {
  // TIME PICKER
  $('.time-picker').timepicker({
    timeFormat: 'HH:mm',
    interval: 60,
    defaultTime: '09',
    dynamic: false,
    dropdown: true,
    scrollbar: true,
  })

  // multi checkbox
  var chk_option_arr = new Array()
  $('#submit-application').click(function () {
    $('input[name=option]:checked').each(function (event) {
      chk_option_arr.push($(this).val())
    })

    alert(chk_option_arr)
    $('#options').val(chk_option_arr)

    var nearby_option_arr = new Array()
    $('input[name=nearby-option]:checked').each(function (event) {
      nearby_option_arr.push($(this).val())
    })

    alert(nearby_option_arr)
    $('#nearby-options').val(nearby_option_arr)
  })
})
