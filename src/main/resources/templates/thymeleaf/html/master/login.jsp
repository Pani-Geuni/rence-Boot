<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

<link rel="stylesheet" href="${path}/resources/CSS/common/common.css" />

<link rel="stylesheet" href="${path}/resources/CSS/master/master-login.css" />

<script type="text/javascript" src="${path}/resources/JS/common/core.min.js"></script>
<script type="text/javascript" src="${path}/resources/JS/common/sha256.min.js"></script>

<script src="${path}/resources/JS/common/jquery-3.6.1.min.js"></script>
<script src="${path}/resources/JS/common/jquery.cookie.js"></script>

<script src="${path}/resources/JS/master/master.js"></script>

<title>Rence Master 로그인</title>
</head>
<body>
	<div class="contentWrap">
		<div class="loginWrap">
			<div class="logo-section">
				<img src="${path}/resources/IMG/common/Rence-Master.svg"
					class="logo-mku">
			</div>
			<!-- END logoSection -->

			<div class="inputSection">
				<input id="master-id" type="text" placeholder="관리자 아이디를 입력하세요" /> <input
					id="master-pw" type="password" placeholder="비밀번호를 입력하세요" />
			</div>
			<!-- END inputSection -->

			<button id="btn-master-login" class="btn-master-login">로그인</button>
		</div>
		<!-- END loginWrap -->
	</div>
	<!-- END contentWrap -->

	<div class="popup-background blind">
		<!-- START COMMON CUSTOM ALERT POPUP -->
		<div id="common-alert-popup" class="alert-popup blind">
			<section class="alert-txt-section">
				<span class="common-alert-txt"></span>
			</section>
			<section id="common-alert-btn" class="alert-btn-section">
				<span>확인</span>
			</section>
		</div>
	</div>
</body>
</html>