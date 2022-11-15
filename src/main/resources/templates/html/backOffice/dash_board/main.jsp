<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <section class="board-contentWrap"> -->
<div class="titleSection">
	<h1>대시보드</h1>
</div>

<!-- 대시보드에서 위에 있는 대시보드 2개 (예약 관리 요약, 최신 문의 내역) -->
<div class="boardWrap">
	<div class="cardSection reserve">
		<div class="card-title">
			<h3>예약 관리 요약</h3>
		</div>
		<div class="custom-table">
			<div class="ct-header">
				<div class="ct-header-cell">예약 날짜</div>
				<div class="ct-header-cell">예약 공간</div>
				<div class="ct-header-cell">예약자</div>
				<div class="ct-header-cell">결제 금액</div>
				<div class="ct-header-cell">상태</div>
			</div>
			<!-- END ct-header -->

			<div class="ct-body">
				<c:forEach var = "vos" items="${r_vos}">
					<div class="ct-body-row" idx="${vos.reserve_no}">
						<div class="ct-body-cell">
							<p>
								${vos.reserve_sdate} ~ <br />${vos.reserve_edate}
							</p>
						</div>
						<div class="ct-body-cell">
							<p>${vos.room_name}</p>
						</div>
						<div class="ct-body-cell">
							<p>${vos.user_name}</p>
						</div>
						<div class="ct-body-cell">
							<p class="reserve-price">${vos.actual_payment}</p>
						</div>
						<div class="ct-body-cell">
							<p>
								<c:if test = "${vos.reserve_state eq 'begin'}">결제완료</c:if>
								<c:if test = "${vos.reserve_state eq 'in_use'}">이용중</c:if>
								<c:if test = "${vos.reserve_state eq 'end'}">이용완료</c:if>
								<c:if test = "${vos.reserve_state eq 'cancel'}">취소</c:if>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- END ct-body -->
		</div>
		<!-- END custom table -->
	</div>
	<!-- END 예약 관리 요약 -->

	<div class="cardSection comment">
		<div class="card-title">
			<h3>최신 문의 내역</h3>
		</div>
		<div class="comment-list">
			<c:forEach var = "vos" items="${c_vos}">
				<div class="comment-item" idx="${vos.comment_no}">
					<div class="comment-title">
						<p>${vos.room_name}</p>
						<p>${vos.comment_date}</p>
					</div>
					<!-- END comment-title -->
					<div class="comment-content">
						<p>${vos.comment_content}</p>
					</div>
				</div>
				<!-- END comment-item -->
			</c:forEach>
			
		</div>
		<!-- END comment-list -->
	</div>
	<!-- END cardSection comment 최신 문의 내역 -->
</div>
<!-- END boardWrap -->

<div class="boardWrap">
	<div class="cardSection sales">
		<div class="card-title">
			<h3>일일 정산 요약</h3>
		</div>

		<div class="sales-list">
			<div class="sales-item">
				<p>매출 순이익</p>
				<span class="sales-income">${svo.sales_income}</span>
			</div>
			<!-- END sales-item -->
			<div class="sales-item">
				<p>매출액</p>
				<span class="sales-revenue">${svo.sales_total}</span>
			</div>
			<!-- END sales-item -->
			<div class="sales-item">
				<p>취소 금액</p>
				<span class="sales-cancel">${svo.sales_cancel}</span>
			</div>
			<!-- END sales-item -->
		</div>
		<!-- END sales-list -->
	</div>
	<!-- END cardSection sales 일일 정산 요약 -->
	<div class="cardSection room">
		<div class="card-title">
			<h3>공간 요약</h3>
		</div>
		<div class="summary-list">
			<div class="summary-list-row">
				<div class="summary-list-item">
					<p>별점</p>
					<span>${rmvo.review_point}</span>
				</div>
				<!-- END summary-list-item -->
				<div class="summary-list-item">
					<p>총 예약</p>
					<span class="reserve-count">${rmvo.reserve_no}</span>
				</div>
				<!-- END summary-list-item -->
			</div>
			<!-- END summary-list-row -->
			<div class="summary-list-row">
				<div class="summary-list-item">
					<p>문의</p>
					<span class="qna-count">${rmvo.comment_no}</span>
				</div>
				<!-- END summary-list-item -->
				<div class="summary-list-item">
					<p>후기</p>
					<span class="review-count">${rmvo.review_no}</span>
				</div>
				<!-- END summary-list-item -->
			</div>
			<!-- END summary-list-row -->
		</div>
		<!-- END summary-list -->
	</div>
	<!-- END cardSection sales 일일 정산 요약 -->
</div>
<!-- END boardWrap -->
<!-- </section> -->