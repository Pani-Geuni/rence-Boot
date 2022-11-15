<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="reserve-list-wrap">
    <div class="reserve-list">
        <section class="page-title-section">
            <span class="page-title">
                <span class="timePoint-select-wrap">
                	<c:if test="${res.type eq 'now'}">
	                    <span class="timePoint-value" time-point="now">현재</span>
                	</c:if>
                	<c:if test="${res.type eq 'before'}">
	                    <span class="timePoint-value" time-point="before">과거</span>
                	</c:if>
                    <img src="${path}/resources/IMG/space-introduce/full-dropdown.svg" alt="full-dropdown" class="full-dropdown"></img>
                </span>
                <span>예약한 내역을 보여드려요.</span>
            </span>
            <div class="timePoint-custom-select-wrap blind">
                <ul class="timePoint-custom-select">
                    <li id="timePoint-now" class="timePoint-custom-select-li">현재</li>
                    <li id="timePoint-before" class="timePoint-custom-select-li">과거</li>
                </ul>
            </div>
        </section>
        <section class="reserve-list-section">
        	<c:if test="${res.cnt == 0}">
	            <section class="reserve-null">
	                <div class="null-first-section">
	                    <span class="advice-txt">예약 내역이 비어있습니다.</span>
	                </div>
	                <div class="null-second-section">
	                    <span class="advice-txt-small">원하는 공간을 예약하러 갈까요?</span>
                        <input type="button" id="go-home-btn" class="go-home" value="홈으로 가기"/>
	                </div>
	            </section>
        	</c:if>
        	
        	<c:if test="${res.cnt != 0}">
	            <section class="reserve-not-null">
	                <section class="box-section">
						<!-- START forEach -->
						<c:forEach var = "obj" items="${res.list}">
		                    <div class = "reserve-box before" idx="${obj.reserve_no}">
		                        <section class="box-img-section">
		                            <img src="${path}/resources/upload/${obj.backoffice_image}" alt ="default-space-img" class="space-img" />
		                        </section>
		                        <section class="box-txt-section">
		                            <span class="b-office-name">${obj.company_name}</span>
		                            <span  class="b-office-location">
		                                <img src = "${path}/resources/IMG/office/location-icon.svg" alt="location-icon" class ="location-icon" />
		                                <span class="location-name">${obj.roadname_address}</span>
		                            </span>
		                            <span class="reserve-time">
		                                <span class="reserve-start-time">
		                                    ${obj.reserve_sdate} ~
		                                </span>
		                                <span class="reserve-end-time">
		                                    ${obj.reserve_edate}
		                                </span>
		                            </span>
		                            <span>
		                                <span class="price-label">결제 금액</span>
		                                <span class="price">${obj.payment_total}원</span>
		                            </span>
		                        </section>
		                    </div>
		                </c:forEach>
		                <!-- END forEach -->
	                </section>

                <section class="paging-section blind">
                    <div class="paging-wrap">
                        <span class="paging-box">&lt; &lt;</span>
                        <span class="paging-box">&lt;</span>
                        <span class="paging-box paging-num choice sample">1</span>
                        <span class="paging-box paging-num un-choice sample">2</span>
                        <span class="paging-box paging-num un-choice sample">3</span>
                        <span class="paging-box paging-num un-choice sample">4</span>
                        <span class="paging-box">></span>
                        <span class="paging-box">>></span>
                    </div>
                </section>
            </section>
           </c:if>
        </section>
    </div>
</div>