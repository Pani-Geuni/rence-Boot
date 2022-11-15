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
	<h1>공간 관리</h1>
	<button id="btn-room-add" class="btn-room-add">추가</button>
	<ul class="mini-nav">
		<li><p id="mini-nav-list" class="nav-item" id="room-list">리스트</p></li>
		<li><p id="mini-nav-qna" class="nav-item" id="qna-list">문의</p></li>
		<li><p id="mini-nav-review" class="nav-item" id="review-list">후기</p></li>
	</ul>
	<!-- END mini-nav -->
</div>
<!-- END titleSection -->

<div class="boardWrap">
	<div class="room-list">
		<c:forEach var="vos" items="${rm_vos}">
			<div class="room-item">
				<div class="item-header">
					<p>
						${vos.room_name}&nbsp;
						<c:if test="${vos.room_type eq 'desk'}">(1인 데스크)</c:if>
						<c:if test="${vos.room_type eq 'meeting_04'}">(4인 회의실)</c:if>
						<c:if test="${vos.room_type eq 'meeting_06'}">(6인 회의실)</c:if>
						<c:if test="${vos.room_type eq 'meeting_10'}">(10인 회의실)</c:if>
						<c:if test="${vos.room_type eq 'office'}">(오피스)</c:if>
					</p>
					<div class="item-buttons">
						<button class="btn-room-edit" idx="${vos.room_no}">수정</button>
						<button class="btn-room-delete" idx="${vos.room_no}">삭제</button>
					</div>
					<!-- END item-buttons -->
				</div>
				<!-- END item-header -->
				<div class="item-body">
					<p id="room-type">
						<c:if test="${vos.room_type eq 'desk'}">데스크</c:if>
						<c:if test="${vos.room_type eq 'meeting_04'}">회의실</c:if>
						<c:if test="${vos.room_type eq 'meeting_06'}">회의실</c:if>
						<c:if test="${vos.room_type eq 'meeting_10'}">회의실</c:if>
						<c:if test="${vos.room_type eq 'office'}">오피스</c:if>
					</p>
					<span id="room-price" class="room-price">${vos.room_price}</span>
				</div>
				<!-- END item-body -->
			</div>
		</c:forEach>
	</div>
	<!-- END room-list -->
</div>
<!-- END boardWrap -->