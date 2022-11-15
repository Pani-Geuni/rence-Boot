<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="titleSection">
	<h1>정산</h1>
</div>

<div class="boardWrap">
	<div class="boardWrap sales">
		<div class="titleSection sales">
			<h3>일일 정산</h3>
			<ul class="sales-mini-nav">
				<li id="sales-mini-nav-day" class="nav-item <c:if test="${sales_date eq 'day'}">active</c:if>"><p>일일</p></li>
				<li id="sales-mini-nav-week" class="nav-item <c:if test="${sales_date eq 'week'}">active</c:if>"><p>주간</p></li>
				<li id="sales-mini-nav-month" class="nav-item <c:if test="${sales_date eq 'month'}">active</c:if>"><p>월간</p></li>
			</ul>
			<!-- END mini-nav -->
		</div>
		<!-- END titleSection sales -->

		<div id="today-sales" class="sales-state">
			<div class="sales-item">
				<p>매출 순이익</p>
				<span id="sales-income" class="sales-price">${svo.sales_income}</span>
			</div>
			<div class="sales-item">
				<p>매출액</p>
				<span id="sales-revenue" class="sales-price">${svo.sales_total}</span>
			</div>
			<div class="sales-item">
				<p>취소 금액</p>
				<span id="sales-cancel" class="sales-price">${svo.sales_cancel}</span>
			</div>
		</div>
		<!-- END today-sales -->

		<div id="yesterday-sales" class="sales-state">
			<div class="sales-item">
				<p>
					<c:if test="${sales_date eq 'day'}">전일</c:if>
					<c:if test="${sales_date eq 'week'}">전주</c:if>
					<c:if test="${sales_date eq 'month'}">전월</c:if>
				 	매출 총이익
				 </p>
				<span id="sales-income" class="sales-price">${svo.pre_sales_income}</span>
			</div>
			<div class="sales-item">
				<p>
					<c:if test="${sales_date eq 'day'}">전일</c:if>
					<c:if test="${sales_date eq 'week'}">전주</c:if>
					<c:if test="${sales_date eq 'month'}">전월</c:if>
					매출액
				</p>
				<span id="sales-revenue" class="sales-price">${svo.pre_sales_total}</span>
			</div>
			<div class="sales-item">
				<p>
					<c:if test="${sales_date eq 'day'}">전일</c:if>
					<c:if test="${sales_date eq 'week'}">전주</c:if>
					<c:if test="${sales_date eq 'month'}">전월</c:if>
					취소 금액
				</p>
				<span id="sales-cancel" class="sales-price">${svo.pre_sales_cancel}</span>
			</div>
		</div>
		<!-- END today-sales -->

		<div class="sales-state">
			<div class="sales-item">
				<p>매출 차이</p>
				<span id="sales-diff" class="sales-price">${svo.sales_gap}</span>
			</div>
		</div>
		<!--  -->
	</div>
	<!-- END boardWrap sales -->

	<div class="boardWrap all-sales-list">
		<div class="titleSection">
			<h3>정산 내역</h3>
		</div>
		<!-- END titleSection sales -->

		<div class="custom-table">
			<div class="ct-header">
				<div class="ct-header-cell">예약 날짜</div>
				<div class="ct-header-cell">대여 공간</div>
				<div class="ct-header-cell">결제 금액</div>
				<div class="ct-header-cell">결제 방식</div>
				<div class="ct-header-cell">정산 여부</div>
			</div>
			<!-- END ct-header -->
			<div class="ct-body">
				<c:forEach var="vos" items="${s_vos}">
					<div class="ct-body-row">
						<div class="ct-body-cell">
							${vos.reserve_sdate} ~ <br />${vos.reserve_edate}
						</div>
						<div class="ct-body-cell">${vos.room_name}</div>
						<div class="ct-body-cell">
							<p class="sales-price">${vos.actual_payment}</p>
						</div>
						<div class="ct-body-cell">
							<c:if test="${vos.payment_state eq 'T'}">선결제</c:if>
							<c:if test="${vos.payment_state eq 'F'}">후결제</c:if>
						</div>
						<div class="ct-body-cell">
							<button class="ct-body-btn is_sales_btn"
								<c:if test="${vos.sales_state eq 'T'}">end="true"</c:if>
								<c:if test="${vos.sales_state eq 'F'}">end="false" payment_no=${vos.payment_no} room_no=${vos.room_no}</c:if>
							>
								<c:if test="${vos.sales_state eq 'T'}">완료</c:if>
								<c:if test="${vos.sales_state eq 'F'}">미완료</c:if>
							</button>
						</div>
					</div>
					<!-- END ct-body-row -->
				</c:forEach>
			</div>
			<!-- END ct-body -->
		</div>
		<!-- END custom-table -->
	</div>
	<!-- END boardWrap sales-list -->
</div>
<!-- END boardWrap -->