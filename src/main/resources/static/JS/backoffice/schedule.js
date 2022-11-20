/**
 * @author : 전판근
 */

$(function () {
  // *************
  // 전체 선택 체크박스
  // *************
  $('#select-all-room').click(function () {
    console.log('asdf')

    if ($(this).is(':checked')) {
      $('.room-checkbox').attr('checked', true)
    } else {
      $('.room-checkbox').attr('checked', false)
    }
  })

  $('#select-all-room').change(function () {
    if ($(this).is(':checked')) {
      $("input:checkbox[name='select-room']").prop('checked', true)
    } else {
      $("input:checkbox[name='select-room']").prop('checked', false)
    }
  })
})
