<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%  int cnt = 0; %>

<div class="review-list-wrap">
    <section class="page-title-section">
        <span class="page-title">작성한 후기들을 바로 확인해 보세요</span>
    </section>


    <section class="question-history-section">
        <section class="question-history-title-section">
            <div class="list-title-txt-wrap">
                <span class="list-title-txt list-num">번호</span>
                <span class="list-title-txt list-company-name">이용 장소</span>
                <span class="list-title-txt list-content">후기 내용</span>
                <span class="list-title-txt list-point">별점</span>
                <span class="list-title-txt list-date">작성일</span>
            </div>
        </section>
        <section class="question-history-wrap">
            <ul>
            	<c:forEach var="obj" items="${res.list}">
	                <li class="question-list">
                         <div class="question-content-wrap">
                             <span class="list-title-txt list-num"><%=++cnt%></span>
                             <span class="list-title-txt list-company-name">${obj.company_name}</span>
                             <span class="list-title-txt list-content">${obj.review_content}</span>
                             <span class="list-title-txt list-point">${obj.review_point}</span>
                             <span class="list-title-txt list-date">${obj.review_date}</span>
                         </div>
                         <div class="detail-question-wrap blind">
                             <div class="question-line">
                                 <div class="q-line">
                                     <label class="q_label">
                                         이용 장소&nbsp;&nbsp;|&nbsp;&nbsp;
                                         ${obj.company_name}&nbsp;(${obj.room_name})
                                     </label>
                                     <div class="question-btn-wrap">
                                         <span class="question-btn" idx="${obj.review_no}">삭제</span>
                                     </div>
                                 </div>
                                 <span>${obj.review_content}</span>
                             </div>
                         </div>
                     </li>
            	</c:forEach>
            </ul>
        </section>
    </section>
</div>