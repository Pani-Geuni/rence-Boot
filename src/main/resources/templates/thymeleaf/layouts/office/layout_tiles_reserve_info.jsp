<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- 과거 예약 상세 정보 / 현재 예약 상세 정보 / 예약 및 결제 페이지 -->
<!DOCTYPE html>
<html>
<head>
	<title><tiles:getAsString name="title"></tiles:getAsString></title>
	<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">
	
	<link rel="stylesheet" href="${path}/resources/CSS/common/common.css" />
	<link rel="stylesheet" href="${path}/resources/CSS/office/login.css" />
	<link rel="stylesheet" href="${path}/resources/CSS/office/header.css" />
	<link rel="stylesheet" href="${path}/resources/CSS/office/reserve_info.css" />
	<link rel="stylesheet" href="${path}/resources/CSS/common/footer.css" />

    <script src="${path}/resources/JS/common/jquery-3.6.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script src="${path}/resources/JS/common/jquery.cookie.js"></script>
    <script src="${path}/resources/JS/office/header.js"></script>
    <script src="${path}/resources/JS/office/user_popup.js"></script>
    <script src="${path}/resources/JS/office/payment_page.js"></script>
</head>
<body>
	<div class ="pageWrap">
		<tiles:insertAttribute name="header" />
		<section class ="contentWrap">
			<tiles:insertAttribute name="content" />
<%-- 			<tiles:insertAttribute name="footer" /> --%>
		</section>
	</div>
	
	<div class="popup-background blind">
        <!-- START LOGIN SECTION -->
        <div id="login-section" class="blind">
            <section class="login-popup-logo-section">
                <img src="${path}/resources/IMG/common/RENCE.svg" class="popup-logo">
            </section>
            <section class="login-popup-input-section">
                <input type="text" id="login-id" class="login-popup-input" placeholder="아이디를 입력하세요." autocomplete="off"/>
                <input type="password" id="login-pw" class="login-popup-input" placeholder="비밀번호를 입력하세요."/>
            </section>
            <section class="login-popup-btn-section">
                <input type="button" id="login-btn" value="로그인">
                <div class="txt-btn-wrap">
                    <span id="go-find-id" class="txt-btn">아이디 찾기</span>
                    <span id="go-find-pw" class="txt-btn">비밀번호 찾기</span>
					<span class="txt-btn login-close">창닫기</span>
                </div>
            </section>
        </div>
        <!-- END LOGIN SECTION -->
        
        <!-- START JOIN SECTION -->
        <div id="join-section" class="blind">
            <section class="join-popup-title-section">
                <span>회원가입</span>
            </section>
            <section class="join-popup-input-section">
                <div class="input-wrap">
                    <div class="input-check">
                        <input type="email" id="join-email" class="join-popup-input-short" placeholder="이메일" autocomplete="off"/>
                        <input type = "button" id="check_email" class="join-popup-check-btn" value="인증하기"/>
                    </div>
                    <span class="warning-text blind">다시 시도해주세요.</span>
                </div>
                <div class="input-wrap">
                    <div class="input-check">
                        <input type="text" id="join-email-code" class="join-popup-input-short" placeholder="인증번호" autocomplete="off"/>
                        <input type = "button" id="check_email-code" class="join-popup-check-btn" value="확인"/>
                    </div>
                    <span class="warning-text blind">다시 시도해주세요.</span>
                </div>
                <div class="input-wrap">
                    <div class="input-check">
                        <input type="text" id="join-id" class="join-popup-input-short" placeholder="아이디(소문자, 숫자만 입력 = 5~10자))" autocomplete="off"/>
                        <input type = "button" id="check_id" class="join-popup-check-btn" value="중복확인"/>
                    </div>
                    <span class="warning-text blind">이미 아이디가 존재합니다.</span>
                </div>
                <div class="input-wrap">
                    <input type="password" id="join-pw" class="join-popup-input" placeholder="비밀번호(영문+숫자+특수문자 = 8~10글자)"/>
                    <span class="warning-text blind">다시 시도해주세요.</span>
                </div>
                <div class="input-wrap">
                    <input type="password" id="join-re-pw" class="join-popup-input" placeholder="비밀번호 재입력"/>
                    <span class="warning-text blind">다시 시도해주세요.</span>
                </div>
                <div class="input-wrap">
                    <input type="text" id="join-name" class="join-popup-input" placeholder="이름" autocomplete="off"/>
                </div>
                <div class="input-wrap">
                    <input type="text" id="join-tel" class="join-popup-input" placeholder="전화번호(-없이 입력)" autocomplete="off"/>
                    <span class="warning-text blind">-없이 입력하세요.</span>
                </div>
                <div class="input-wrap">
                    <input type="text" id="join-birth" class="join-popup-input" placeholder="생년월일(숫자로만 입력)" autocomplete="off"/>
                    <span class="warning-text blind">숫자로만 입력가능합니다.</span>
                </div>
            </section>
            <section class="join-popup-btn-section">
                <input type="button" id="join-btn" value="회원가입">
            </section>
        </div>
        <!-- END JOIN SECTION -->

        <!-- START FIND-ID SECTION -->
        <div id="find-id-section" class="find-section blind">
            <section class="find-popup-logo-section">
                <span>아이디 찾기</span>
            </section>
            <section class="find-popup-input-section">
                <span class="find-guide-txt">회원가입 시 입력한 이메일을 입력해주세요.</span>
                <input type="email" id="find-id-email" class="find-popup-input" placeholder="이메일을 입력하세요." autocomplete="off"/>
            </section>
            <section class="find-popup-btn-section">
                <input type="button" id="find-id-btn" class="find-btn" value="메일 보내기">
                <input type="button" id="find-id-close" class="p-close" value="창닫기">
            </section>
        </div>
        <!-- END FIND-ID SECTION -->

        <!-- START FIND-PW SECTION -->
        <div id="find-pw-section" class="find-section blind">
            <section class="find-popup-logo-section">
                <span>비밀번호 찾기</span>
            </section>
            <section class="find-popup-input-section">
                <div class="email-wrap">
                    <span class="find-guide-txt">회원가입 시 입력한 이메일을 입력해주세요.</span>
                    <input type="email" id="find-pw-email" class="find-popup-input" placeholder="이메일을 입력하세요." autocomplete="off"/>
                </div>
                <div>
                    <span class="find-guide-txt">회원가입 시 입력한 아이디를 입력해 주세요.</span>
                    <input type="text" id="find-pw-id" class="find-popup-input" placeholder="아이디를 입력하세요." autocomplete="off"/>
                </div>
            </section>
            <section class="find-popup-btn-section">
                <input type="button" id="find-pw-btn" class="find-btn" value="비밀번호 찾기">
                <input type="button" id="find-pw-close" class="p-close" value="창닫기">
            </section>
        </div>
        <!-- END FIND-PW SECTION -->

        <!-- START LOGOUT CONFIRM POPUP -->
        <div id ="logout-popup" class="confirm-popup blind">
            <section class="confirm-txt-section">
                <span class="logout-txt">
                	로그아웃 하시겠습니까?<br><br>
                    로그아웃 시 메인페이지로 이동됩니다.
                </span>
            </section>
            <section class="confirm-btn-section">
                <div id="logout-btn" class="confirm-yesBtn">로그아웃</div>
                <div id="logout-closeBtn" class="confirm-noBtn">닫기</div>
            </section>
        </div>
        
		<!-- 현재 예약 리스트 예약 상세 정보 페이지 -->
		<!-- START RESERVE-CANCLE CONFIRM POPUP -->
        <div id ="reserve-cancle-popup" class="confirm-popup blind">
            <section class="confirm-txt-section">
                <span class="reserve-cancle-txt">
                    예약을 정말로 취소하시겠습니까?<br><br>
                    환불 규정에 따라 환불 될 예정입니다.
                </span>
            </section>
            <section class="confirm-btn-section">
                <div id="refund-btn" class="confirm-yesBtn">확인</div>
                <div id="refund-closeBtn" class="confirm-noBtn">취소</div>
            </section>
        </div>
        <!-- END RESERVE-CANCLE CONFIRM POPUP -->
        
		<!-- 예약 및 결제 페이지 -->
        <!-- START reserve-success ALERT POPUP -->
        <div id ="reserve-success-alert-popup" class="alert-popup blind">
            <section class="alert-txt-section">
                <span>
                    예약에 성공하였습니다.
                </span>
            </section>
            <section id = "reserve-success-alert-btn" class="alert-btn-section">
                <span>확인</span>
            </section>
        </div>
    </div>

    <div class="popup-background blind">
    	<!-- START COMMON CUSTOM ALERT POPUP -->
        <div id ="common-alert-popup" class="alert-popup blind">
            <section class="alert-txt-section">
                <span class="common-alert-txt"></span>
            </section>
            <section id = "common-alert-btn" class="alert-btn-section">
                <span>확인</span>
            </section>
        </div>
        
        <!-- START SUCCESS CUSTOM ALERT POPUP -->
        <div id ="success-alert-popup" class="alert-popup blind">
            <section class="alert-txt-section">
                <span>해당 이메일로 비밀번호를 전송하였습니다.</span>
            </section>
            <section id = "success-alert-btn" class="alert-btn-section">
                <span>확인</span>
            </section>
        </div>

        <!-- START FAIL CUSTOM ALERT POPUP -->
        <div id ="fail-alert-popup" class="alert-popup blind">
            <section class="alert-txt-section">
                <span>해당 이메일로 가입된 회원이 없습니다.</span>
            </section>
            <section id = "fail-alert-btn" class="alert-btn-section">
                <span>확인</span>
            </section>
        </div>
    </div>
</body>
</html>