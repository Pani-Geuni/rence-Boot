<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- popup background -->
<div class="reset-pw-background">
	<div id="popup-update-pw" class="">
		<div class="titleSection">
			<h3>새로운 비밀번호를 입력해 주세요</h3>
		</div>

		<div class="input-group">
			<div class="input-item">
				<input name="backoffice_pw" id="input-update-pw" class="input-update-pw" type="password" placeholder="새로운 비밀번호를 입력해 주세요." />
				<span class="pw-warning-text blind"></span>
			</div>
			<div class="input-item"> 
				<input name="backoffice_pw_check" id="input-update-pw-re" class="input-update-pw" type="password" placeholder="새로운 비밀번호를 다시 입력해 주세요." />
				<span class="pw-warning-text blind"></span>
			</div>
				
		</div>

		<div class="btn-popup-group">
			<button type="submit" id="btn-pw-update">확인</button>
		</div>
	</div>
	<!-- END popup-update-pw -->
</div>