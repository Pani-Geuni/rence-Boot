/**
* @author : 전판근, 김예은
*/
$(function() {
	
	$('#btn-update-host').click(function() {
		console.log("clidk")
		location.href = '/backoffice/update_host?backoffice_no=' + $.cookie("backoffice_no");
	})
})