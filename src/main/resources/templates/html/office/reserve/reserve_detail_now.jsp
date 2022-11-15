<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="space-detail-wrap">
    <section class="page-title-section">
        <span class="page-title">현재 나의 예약 상세 정보를 보여드려요</span>
    </section>
	
	<!-- START BODY-SEMI-WRAP  -->
    <section class="body-semi-wrap">
        <div class="deatil-info-section">
        	<!-- START RESERVE-INFO-SECTION  -->
            <section class="reserve-info-section">
                <span class="section-title">예약 정보</span>
                <div class="reserve-info-wrap">
                    <section>
                        <img src="${path}/resources/upload/${res.info_obj.backoffice_image}" alt="result-img" class="reserve-info-img" />
                    </section>
                    <section class="reserve-info-txt">
                        <span class="info-company-name">${res.info_obj.company_name}</span>
                        <ul class="info-li-wrap">
                            <li class="info-li">
                                <label class="info-label">공간 타입</label>
                                <span class="info-text">${res.info_obj.room_type}</span>
                            </li>
                            <li class="info-li">
                                <label class="info-label">예약 날짜</label>
                                <span class="info-text">${res.info_obj.reserve_sdate} ~ ${res.info_obj.reserve_edate}</span>
                            </li>
                            <li class="info-li">
                                <label class="info-label">공간 이름</label>
                                <span class="info-text">${res.info_obj.room_name}</span>
                            </li>
                            <li class="info-li">
                                <label class="info-label">공간 가격</label>
                                <span class="info-text">${res.info_obj.room_price}원/시간</span>
                            </li>
                        </ul>
                    </section>
                </div>
            </section>
            <!-- END RESERVE-INFO-SECTION  -->
            
            <section class="user-host-info-section">
                <section class="uh-info-section">
                    <span class="person-type-title">예약자 정보</span>
                    <div class="uh-info-wrap">
                        <ul>
                            <li class="host-info-li">
                                <label class="uh-label">예약자</label>
                                <span class="uh-text">${res.user_obj.user_name}</span>
                            </li>
                            <li class="host-info-li">
                                <label  class="uh-label">연락처</label>
                                <span class="uh-text">${res.user_obj.user_tel}</span>
                            </li>
                            <li class="host-info-li">
                                <label  class="uh-label">이메일</label>
                                <span class="uh-text">${res.user_obj.user_email}</span>
                            </li>
                        </ul>
                    </div>
                </section>
                <section class="uh-info-section">
                    <span class="person-type-title">호스트 정보</span>
                    <div class="uh-info-wrap">
                        <ul>
                            <li class="host-info-li">
                                <label class="uh-label">사업자명</label>
                                <span class="uh-text">${res.info_obj.backoffice_name}</span>
                            </li>
                            <li class="host-info-li">
                                <label  class="uh-label">위치</label>
                                <span class="uh-text">${res.info_obj.full_address}</span>
                            </li>
                            <li class="host-info-li">
                                <label  class="uh-label">연락처</label>
                                <span class="uh-text">${res.info_obj.backoffice_tel} ${res.info_obj.backoffice_email}</span>
                            </li>
                        </ul>
                    </div>
                </section>
            </section>
            
            <!-- START CAREFUL-INFO-SECTION  -->
            <section  class="careful-info-section">
                <span class="section-title">예약시 주의 사항</span>

                <ul class="section-list-wrap">
                    <li class="section-list">1. 결제 방법 - 선결제 선택 시 보증금 없이 총 사용 금액이 결제됩니다.</li>
                    <li class="section-list">2. 결제 방법 - 당일 결제 선택 시 보증금(총 예약 금액의 20%)만 결제되며, 나머지 금액은 예약 당일 해당 공간에서 이루어집니다.</li>
                    <li class="section-list">3. 공간 내 기물 파손 시 배상해야 해며, 배상 시 비용은 새제품 가격으로 책정됩니다.</li>
                    <li class="section-list">4. 공간 내 화기 사용은 금하며, 건물 전체가 금연 건물입니다.</li>
                    <li class="section-list">5. 공간 내 간단한 음료와 간식만 드실 수 있으며, 드신 후 쓰레기는 분리 배출 부탁드립니다.</li>
                    <li class="section-list">6. 이용중 지나친 소음이 발생하지 않도록 주의 부탁드립니다.</li>
                    <li class="section-list">7. 선결제 시, 총 결제 금액의 5%가 마일리지로 적립됩니다.</li>
                </ul>
            </section>
            <!-- END CAREFUL-INFO-SECTION  -->
            
            <!-- START REFUND-INFO-SECTION  -->
            <section class="refund-info-section">
                <span class="section-title">환불 규정 안내</span>
                <ul class="refund-list-wrap">
                    <li class="refund-list">
                        <label class="refund-label">예약 후 1시간 이내 취소</label>
                        <span>
                            총 금액의 100% 환불
                        </span>
                    </li>
                    <li class="refund-list">
                        <label class="after-refund-label">예약 후 1시간 이후 취소</label>
                        <span>
                            총 금액의 80% 환불 (보증금을 제외한 나머지 가격 환불)
                        </span>
                    </li>
                </ul>
            </section>
            <!-- END REFUND-INFO-SECTION  -->
            
            <!-- START PAYMENT-SECTION  -->
            <section class="payment-section">
                <span class="section-title">결제 금액 안내</span>
                <div class="payment-info-wrap">
                    <section class="payment-company-section">
                        <ul class="payment-info-li-wrap">
                            <li class="info-li">
                                <label class="info-label">공간 타입</label>
                                <span class="info-text">${res.info_obj.room_type}</span>
                            </li>
                            <li class="info-li">
                                <label class="info-label">예약 날짜</label>
                                <span class="info-text">${res.info_obj.reserve_sdate} ~ ${res.info_obj.reserve_edate}</span>
                            </li>
                            <li class="info-li">
                                <label class="info-label">공간 이름</label>
                                <span class="info-text">${res.info_obj.room_name}</span>
                            </li>
                            <li class="info-li">
                                <label class="info-label">공간 가격</label>
                                <span class="info-text">${res.info_obj.room_price}원/시간</span>
                            </li>
                        </ul>
                    </section>
                    <section class="pay-mileage-section">
                        <ul class="payment-info-li-wrap">
                            <li class="info-li">
                                <label class="pay-info-label">결제 금액</label>
                                <span class="pay-info-text">${res.info_obj.actual_payment}원</span>
                            </li>
                            <li class="info-li">
                                <label class="pay-info-label">마일리지 적립 금액</label>
                                <span class="pay-info-text">${res.info_obj.mileage_change}원</span>
                            </li>
                        </ul>
                    </section>
                </div>
            </section>
            <!-- END PAYMENT-SECTION  -->
            
            <!-- START BTN-SECTION  -->
            <section class="btn-section">
                <div id="pay-cancel-btn" class="pay-btn" idx="${res.info_obj.reserve_no}">
                    <span>예약 취소</span>
                </div>
            </section>
            <!-- END BTN-SECTION  -->
        </div>
        
        
        <div id = "footerWrap">
            <h5>© Golfzon Tech Academy, Inc. All rights reserved.</h5>
        </div>
    </section>
    <!-- END BODY-SEMI-WRAP  -->
</div>