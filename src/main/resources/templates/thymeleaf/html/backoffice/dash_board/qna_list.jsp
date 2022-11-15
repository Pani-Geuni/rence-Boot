<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<div class="boardWrap qna">
	<div class="custom-table qna">
		<div class="ct-header qna">
			<div class="ct-header-cell qna">처리 상태</div>
			<div class="ct-header-cell qna">문의 공간</div>
			<div class="ct-header-cell qna">문의 내용</div>
			<div class="ct-header-cell qna">작성일</div>
			<div class="ct-header-cell qna">답변</div>
		</div>
		<!-- END ct-header -->

		<c:forEach var="vos" items="${q_vos}">
			<div class="ct-body-row qna">
				<div class="ct-body-cell qna">
					<p class="qna-process 
						<c:if test="${vos.comment_state eq 'T'}">true</c:if>
						<c:if test="${vos.comment_state eq 'F'}">false</c:if>
					">
						<c:if test="${vos.comment_state eq 'T'}">처리</c:if>
						<c:if test="${vos.comment_state eq 'F'}">미처리</c:if>
					</p>
				</div>
				<div class="ct-body-cell qna">
					<p>${vos.room_name}</p>
				</div>
				<div class="ct-body-cell qna">
					<p>${vos.comment_content}</p>
				</div>
				<div class="ct-body-cell qna">
					<p>${vos.comment_date}</p>
				</div>
				<div class="ct-body-cell qna">
					<button class="ct-body-btn
						<c:if test="${vos.comment_state eq 'T'}">qna-delete</c:if>
						<c:if test="${vos.comment_state eq 'F'}">qna-add</c:if>"
						comment_no="${vos.comment_no}" room_no = "${vos.room_no}" answer_no = "${vos.answer_no}"
					>
						<c:if test="${vos.comment_state eq 'T'}">답변 삭제</c:if>
						<c:if test="${vos.comment_state eq 'F'}">답변 작성</c:if>
					</button>
				</div>
			</div>
			<!-- END ct-body-row -->
			<div class="detail-qna-wrap blind">
	            <div class="question-section">
	              <h5 class="question-title">Q.</h5>
	              <p class="question-content">
	                ${vos.comment_content}
	              </p>
	            </div>
	            <!-- END question-section -->
	            <c:if test="${vos.answer_content ne null}">
		            <div class="answer-section">
		              <div class="answer-title-section">
		                <h5 class="answer-title">A.</h5>
		                <p class="answer-date">작성일 | ${vos.answer_date}</p>
		              </div>
		              <p class="answer-content">
		                ${vos.answer_content}
		              </p>
		            </div>
	            </c:if>
	          </div>
		</c:forEach>

		<ul class="pagination blind">
			<li class="page-item">
				<button>
					<img src="${path}/resources/IMG/dash-board/ico-double-left.svg" alt="-10" />
				</button>
			</li>
			<li class="page-item">
				<button>
					<img src="${path}/resources/IMG/dash-board/ico-left.svg" alt="-1" />
				</button>
			</li>
			<li class="page-item"><button>1</button></li>
			<li class="page-item"><button>2</button></li>
			<li class="page-item"><button>3</button></li>
			<li class="page-item"><button>4</button></li>
			<li class="page-item"><button>5</button></li>
			<li class="page-item">
				<button>
					<img src="${path}/resources/IMG/dash-board/ico-right.svg" alt="+1" />
				</button>
			</li>
			<li class="page-item">
				<button>
					<img src="${path}/resources/IMG/dash-board/ico-double-right.svg" alt="+10" />
				</button>
			</li>
		</ul>
		<!-- END pagination -->
	</div>
</div>
<!-- END boardWrap qna -->