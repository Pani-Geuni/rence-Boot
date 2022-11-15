/**
* @author : 전판근
*/

$(function(){

	// master header nav 변경
	switch($(location).attr('pathname')) {
	
		case '/rence/master_main':
			$('#nav-item-agree').addClass('active');
			break;
			
		case '/rence/master_backoffice_end':
			$('#nav-item-delete').addClass('active');
			break;
			
		default:
			break;
	}

	// location.href 작업
	$('.logo-mku').click(function() {
		location.href="/rence/master_main";
	})
	
	$('#nav-item-agree').click(function() {
		location.href="/rence/master_main";
	})
	
	$('#nav-item-delete').click(function() {
		location.href="/rence/master_backoffice_end";
	})
	
	// 로그인 실패 팝업 닫기
	$('#common-alert-btn').click(function() {
		$('#common-alert-popup').addClass('blind');
		$('.popup-background').addClass('blind');
	})

	// master login
	$("#btn-master-login").click(function () {
	
		// 로그인 시도
		// 로그인 성공
		if ($('#master-id').val().trim().length > 0 && $('#master-pw').val().trim().length > 0) {
			$.ajax({
				url : "/rence/master_loginOK",
				type : "POST",
				dataType : "json",
				data : {
					master_id : $('#master-id').val().trim(),
					master_pw : CryptoJS.SHA256($('#master-pw').val().trim()).toString()
				},
				
				success : function(res) {
					// 로그인 성공 시
					if (res.result == 1) {
						// INPUT 초기화
						$('#master-id').val("");
						$('#master-pw').val("");
						
						location.href="/rence/master_main";
					} else {
						$('.popup-background').removeClass('blind');
						$('#common-alert-popup').removeClass('blind');
						$(".common-alert-txt").text("로그인에 실패하였습니다.");
					}
				},
				error : function(error) {
					console.log(error);
					$(".popup-background:eq(1)").removeClass("blind");
                    $("#common-alert-popup").removeClass("blind");
                    $(".common-alert-txt").text("오류 발생으로 인해 처리에 실패하였습니다.");
                }
			});
		}
		
		// 로그인 실패
		else {
			if ($('#master-id').val().trim().length == 0) {
				$("#master-id").addClass("null-input-border");
			}
			
			if ($('#master-pw').val().trim().length == 0) {
				$("#master-pw").addClass("null-input-border");
			}
		}
	});
	
	// Master 로그아웃
	$('#btn-logout').click(function() {
		location.href='/rence/master_logoutOK';
	})


	// 신청 호스트 수락 버튼 클릭
	$('#btn-grant-host').click(function() {
		$('.popup-background:eq(0)').removeClass('blind');
		$('#grant-popup').removeClass('blind');
	})
	
	$('#grant-closeBtn').click(function() {
		$('#grant-popup').addClass('blind');
		$('.popup-background:eq(0)').addClass('blind');
	})
	
	$('#grant-btn').click(function() {
	
		$.ajax({
			url : "/rence/master_grant",
			type : "POST",
			dataType: "json",
			data : {
				backoffice_no : $("#backoffice_no").text(),
				backoffice_email : $("#backoffice_email").text() 
			},
			
			success : function(res) {
				console.log("success");
				
				if (res.result == "1") {
					location.href="/rence/master_main";
				} else {
					console.log("ajax fail");
				}
			},
			error : function(error) {
				console.log($("#backoffice_no").text())
				console.log($("#backoffice_email").text())
				console.log(error);
			}
		});
	})
	
	
	// 신청 호스트 거절 버튼 클릭
	$('#btn-refuse-host').click(function() {
		$('.popup-background:eq(0)').removeClass('blind');
		$('#refuse-popup').removeClass('blind');
	})
	
	$('#refuse-closeBtn').click(function() {
		$('#refuse-popup').addClass('blind');
		$('.popup-background:eq(0)').addClass('blind');
	})
	
	$('#refuse-btn').click(function() {
	
		$.ajax({
			url : "/rence/master_refuse",
			type : "POST",
			dataType: "json",
			data : {
				backoffice_no : $("#backoffice_no").text(),
				backoffice_email : $("#backoffice_email").text() 
			},
			
			success : function(res) {
				console.log("success");
				
				if (res.result == "1") {
					location.href="/rence/master_main";
				} else {
					console.log("ajax fail");
				}
			},
			error : function(error) {
				console.log($("#backoffice_no").text())
				console.log($("#backoffice_email").text())
				console.log(error);
			}
		});
	})
	
	// 삭제 신청 호스트 버튼 클릭
	$('#btn-delete-host').click(function() {
		$('.popup-background:eq(0)').removeClass('blind');
		$('#delete-popup').removeClass('blind');
	})
	
	$('#delete-closeBtn').click(function() {
		$('#delete-popup').addClass('blind');
		$('.popup-background:eq(0)').addClass('blind');
	})
	
	$('#delete-btn').click(function() {
	
		$.ajax({
			url : "/rence/master_revoke",
			type : "POST",
			dataType: "json",
			data : {
				backoffice_no : $("#backoffice_no").text(),
				backoffice_email : $("#backoffice_email").text() 
			},
			
			success : function(res) {
				console.log("success");
				
				if (res.result == "1") {
					location.href="/rence/master_backoffice_end";
				} else {
					console.log("ajax fail");
				}
			},
			error : function(error) {
				console.log($("#backoffice_no").text())
				console.log($("#backoffice_email").text())
				console.log(error);
			}
		});
	})
	
})