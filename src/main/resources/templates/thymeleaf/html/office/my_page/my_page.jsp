<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="my-page-wrap">
	<div class="my-page">
		<section class="profile-section">
			<div class="profile">
				<div class="profile-img-wrap">
					<img src="${path}/resources/upload/${umvo.user_image}" alt="profile-img" class="profile-img" /> 
					<span class="img-update-txt">프로필 사진 수정</span>
				</div>
				<div class="profile-info-wrap">
					<section class="info-name-section">
						<span class="info-name">${umvo.user_name}</span>
						<span class="pw-update-txt">비밀번호 수정</span>
					</section>
					<section class="info-etc-section">
						<span>${umvo.user_id}</span> 
						<span>•</span> 
						<span>${umvo.user_email}</span>
					</section>
					<section class="info-etc-section info-etc-section-second">
						<span>${umvo.user_tel}</span> 
						<span>•</span> 
						<span>${umvo.user_birth}</span>
					</section>
					<section class="user-delete-section">
						<span class="user-delete-btn">탈퇴하기</span>
					</section>
				</div>
			</div>
		</section>

		<section class="my-page-menu-section">
             <div id="reserve-menu" class="menu-box left-menu menu-top">
                 <section class="menu-box-img-section">
                     <img src="${path}/resources/IMG/my-page/calendar.svg" alt="menu-box-img" class="menu-box-img" />
                 </section>
                 <section  class="menu-box-txt-section">
                     <span class="menu-box-title">예약 현황</span>
                     <span class="menu-box-txt">예약한 내역을 확인해보세요.</span>
                 </section>
             </div>
             <div id="mileage-menu" class="menu-box menu-top">
                 <section class="menu-box-img-section">
                     <img src="${path}/resources/IMG/my-page/bi_coin.svg" alt="menu-box-img" class="menu-box-img" />
                 </section>
                 <section class="menu-box-txt-section">
                     <div class="menu-box-txt-wrap">
                         <span class="menu-box-title">마일리지 조회</span>
                         <span  class="point-txt">내 마일리지 : ${umvo.mileage_total}원</span>
                     </div>
                     <span class="menu-box-txt">쌓인 마일리지를 조회할 수 있어요.</span>
                 </section>
             </div>
             <div id="review-menu" class="menu-box left-menu">
                 <section class="menu-box-img-section">
                     <img src="${path}/resources/IMG/my-page/review.svg" alt="menu-box-img" class="menu-box-img" />
                 </section>
                 <section  class="menu-box-txt-section">
                     <span class="menu-box-title">후기 내역</span>
                     <span class="menu-box-txt">작성한 후기 내역을 확인해보세요.</span>
                 </section>
             </div>
             <div id="question-menu" class="menu-box">
                 <section class="menu-box-img-section">
                     <img src="${path}/resources/IMG/my-page/letter.svg" alt="menu-box-img" class="menu-box-img" />
                 </section>
                 <section  class="menu-box-txt-section">
                     <span class="menu-box-title">문의 내역</span>
                     <span class="menu-box-txt">업체에 문의한 내용을 바로 볼 수 있어요.</span>
                 </section>
             </div>
         </section>
	</div>
</div>