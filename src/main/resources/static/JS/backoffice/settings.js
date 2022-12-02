/**
* @author : 전판근, 김예은
*/
$(function() {
	$('#btn-update-host').click(function() {
		location.href = '/backoffice/update_host?backoffice_no=' + $.cookie("backoffice_no");
	})
})