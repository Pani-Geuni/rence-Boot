<!-- ------------------- -->
<!-- @author 김예은, 전판근 -->
<!-- ------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />	


<section class="space-detail-introduce-section">
	<div class="space-detail-introduce-wrap">
		<section class="space-detail-title-section">
            <span  class="space-detail-company-name">
                ${ ovo.company_name }
            </span>
            <c:forEach var="type_item" items="${ type_list }">
	            <span class="space-detail-type">${ type_item }</span>
            </c:forEach>
        </section>
        <section class="space-detail-info-section">
            <div class="space-rating">
                <img src="${path}/resources/IMG/common/grey-star.svg" class="space-rating-icon" alt="space-rating-icon" />
                <span class="space-info-txt">${ ovo.avg_rating }</span>
            </div>
            <div class="space-tags">
            	<c:forEach var="tag_item" items="${ tag_list }">
	                <span class="space-info-txt tag-txt">${ tag_item }</span>
            	</c:forEach>
            </div>
        </section>
        <div class="space-location">
            <img src="${path}/resources/IMG/common/location-icon.svg" class="space-location-icon" alt="space-location-icon" />
            <span class="space-info-txt">${ ovo.roadname_address } ${ ovo.detail_address }</span>
        </div>
		<section class="spage-imgs-section">
			<div class="window">
				<ul class="container">
					<c:forEach var="img_item" items="${ img_list }">
						<li class="img">
							<img src="${path}/resources/upload/${ img_item }" class="company-img" />
						</li>
					</c:forEach>
				</ul>

				<div class="button-container">
					<span class="prev button-size">&lt;</span>
					<span class="next button-size">></span>
				</div>
			</div>
		</section>
		<section class="dynamic-section">
			<!-- START un-fixed-section -->
			<section class="un-fixed-section">
				<section class="menu-tab-section">
					<ul class="menu-tab">
						<li class="menu click-menu" menu="info">공간 소개</li>
						<li class="menu" menu="qna">문의</li>
						<li class="menu" menu="review">후기</li>
					</ul>
				</section>

				<!-- 공간 소개 -->
				<div class="space-description-wrap">
					<div class="space-description"> 
						${ ovo.backoffice_info }
					</div>

					<div class="option-wrap">
						<label class="section-title"> 옵션 </label>
						<ul class="option-list-wrap">
							<c:forEach var="option_item" items="${ option_list }">
								<li class="option-list">${ option_item }</li>
							</c:forEach>
						</ul>
					</div>

					<div class="around-info-wrap">
						<label class="section-title"> 주변 시설 </label>
						<ul id="" class="option-list-wrap">
							<c:forEach var="around_item" items="${ around_option_list }">
								<li class="option-list">${ around_item }</li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<!-- 문의 -->
				<div class="question-wrap blind">
					<section class="question-wrap-title">
						<section class="question-left">
							<span class="big-title">문의</span> <span class="small-title">${cvos_cnt}개</span>
						</section>
						<section id="question-create-btn" class="question-right">
							<span>문의하기</span>
						</section>

						<!-- 문의하기 팝업창 -->
                        <div id="question-popup" class="question-popup-wrap blind">
                            <span class="question-popup-title">문의하기</span>

                            <section class="question-popup-type-select-wrap">
                                <span class="question-popup-small-title">문의할 공간 타입</span>

                                <!-- select 열 때 open-select추가 -->
                                <div class="question-popup-select-val-wrap">
                                    <span id="question-select-choice" class="question-popup-select-value">
                                        타입을 선택해 주세요
                                    </span>
                                    <img src="${path}/resources/IMG/space-introduce/full-dropdown.svg" alt="full-dropdown" class="full-dropdown"></img>
                                </div>

                                <ul class="question-popup-select blind">
                                    <c:forEach var="vos" items="${rvos}">
	                                    <li class="question-popup-select-li" idx="${vos.room_no}">
	                                        ${vos.room_name}
	                                    </li>
	                                </c:forEach>
                                </ul>
                            </section>
                            <section class="textarea-section">
                                <textarea id="question-write" class="question-write"></textarea>
                                <div class="txt-length-wrap">
                                    <span class="qna-length">0</span>
                                    <span class="total-length">/ 500</span>
                                </div>
                            </section>
                            <section class="quest-popup-btn-section">
                                <span id="question-createBtn" class="popup-create-btn">등록</span>
                                <span id="question-close-btn" class="popup-close-btn">취소</span>
                            </section>
                        </div>
					</section>

					<section class="quest-list-section">
						<ul class="quest-list-wrap">
							<c:forEach var="cvo" items="${ cvos }">
								<li class="quest-list">
									<section>
										<img src="${path}/resources/IMG/header/loopy.jpg" alt="write-user-img" class="write-user-img" />
									</section>
									<section class="quest-content-section">
										<ul class="quest-content-wrap">
											<li class="quest-content-list quest-content-writer">${ cvo.user_name }</li>
											<li class="quest-content-list quest-content">${ cvo.comment_content }</li>
											<li class="quest-content-list quest-content-date">${ cvo.comment_date }</li>
										</ul>
									</section>
								</li>
								<c:if test="${cvo.answer_date ne null}">
									<li class="quest-list">
										<section>
											<img src="${path}/resources/upload/img_host_001.jpg" alt="write-user-img" class="write-user-img" />
										</section>
										<section class="quest-content-section">
											<ul class="quest-content-wrap">
												<li class="quest-content-list quest-content-writer">HOST</li>
												<li class="quest-content-list quest-content">${ cvo.answer_content}</li>
												<li class="quest-content-list quest-content-date">${cvo.answer_date}</li>
											</ul>
										</section>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</section>
				</div>

				<!-- 후기 -->
				<div id="review-wrap" class="question-wrap blind">
					<section class="question-wrap-title">
						<section class="question-left">
							<span class="big-title">후기</span> <span class="small-title">${review_cnt}개</span>
						</section>
						<section id="review-write-btn" class="question-right">
							<span>후기쓰기</span>
						</section>

						<!-- 후기쓰기 팝업창 -->
                        <div id="review-popup" class="review-popup-wrap blind">
                            <span class="question-popup-title">후기 남기기</span>

                            <section class="question-popup-type-select-wrap">
                                <span class="question-popup-small-title">선택할 공간</span>

                                <!-- select 열 때 open-select추가 -->
                                <div class="question-popup-select-val-wrap">
                                    <span id="review-select-choice" class="question-popup-select-value">
                                        공간  타입을 선택해 주세요
                                    </span>
                                    <img src="${path}/resources/IMG/space-introduce/full-dropdown.svg" alt="full-dropdown" class="full-dropdown"></img>
                                </div>

                                <ul class="question-popup-select blind">
                                    <c:forEach var="vos" items="${rvos}">
	                                    <li class="question-popup-select-li" idx="${vos.room_no}">
	                                        ${vos.room_name}
	                                    </li>
	                                </c:forEach>
                                </ul>
                            </section>
                            <section class="textarea-section">
                                <textarea id="review-write" class="question-write"></textarea>
                                <div class="txt-length-wrap">
                                    <span class="review-length">0</span>
                                    <span class="review-total-length">/ 500</span>
                                </div>
                            </section>
                            <section class="popup-star-section">
                                <span class="popup-star-title">별점</span>
                                <ul class="popup-star-wrap">
                                    <li id="star_1" class="popup-star-li">
                                        <img src="${path}/resources/IMG/common/y-star.svg" alt="후기 리뷰 별점 이미지" class="y-star r-star blind"/>
                                        <img src="${path}/resources/IMG/common/star.svg" alt="후기 리뷰 별점 이미지" class="g-star r-star "/>
                                    </li>
                                    <li id="star_2" class="popup-star-li">
                                        <img src="${path}/resources/IMG/common/y-star.svg" alt="후기 리뷰 별점 이미지" class="y-star r-star blind"/>
                                        <img src="${path}/resources/IMG/common/star.svg" alt="후기 리뷰 별점 이미지" class="g-star r-star "/>
                                    </li>
                                    <li id="star_3" class="popup-star-li">
                                        <img src="${path}/resources/IMG/common/y-star.svg" alt="후기 리뷰 별점 이미지" class="y-star r-star blind"/>
                                        <img src="${path}/resources/IMG/common/star.svg" alt="후기 리뷰 별점 이미지" class="g-star r-star"/>
                                    </li>
                                    <li id="star_4" class="popup-star-li">
                                        <img src="${path}/resources/IMG/common/y-star.svg" alt="후기 리뷰 별점 이미지" class="y-star r-star blind"/>
                                        <img src="${path}/resources/IMG/common/star.svg" alt="후기 리뷰 별점 이미지" class="g-star r-star"/>
                                    </li>
                                    <li id="star_5" class="popup-star-li">
                                        <img src="${path}/resources/IMG/common/y-star.svg" alt="후기 리뷰 별점 이미지" class="y-star r-star blind"/>
                                        <img src="${path}/resources/IMG/common/star.svg" alt="후기 리뷰 별점 이미지" class="g-star r-star"/>
                                    </li>
                                </ul>
                            </section>
                            <section class="quest-popup-btn-section">
                                <span id="review-create-btn" class="popup-create-btn">등록</span>
                                <span id="review-close-btn" class="popup-close-btn">취소</span>
                            </section>
                        </div>
					</section>

					<section class="quest-list-section">
						<ul class="quest-list-wrap">
							<c:forEach var="revo" items="${ revos }">
								<li class="quest-list">
									<section>
										<img src="${path}/resources/IMG/header/loopy.jpg" alt="write-user-img" class="write-user-img" />
									</section>
									<section class="quest-content-section">
										<ul class="quest-content-wrap">
											<li class="quest-content-list quest-content-writer">
												<span class="quest-writer">${ revo.user_name }</span> 
												
												<span class="review-star-wrap">
													<img src="${path}/resources/IMG/common/star.svg" class="review-star-img" alt="review-star-img" /> 
													<span class="review-star-num">${ revo.review_point }</span>
												</span>
											</li>
											<li class="quest-content-list quest-content">${ revo.review_content }</li>
											<li class="quest-content-list quest-content-date">${ revo.review_date }</li>
										</ul>
									</section>
								</li>
							</c:forEach>
						</ul>
					</section>
				</div>
			</section>
			<!-- END un-fixed-section -->

			<!-- START fixed-section -->
			<section class="fixed-section">
				<section class="fixed-popup blind">
					<div class="alert-popup">
						<section class="alert-txt-section">
							<span class="using-time-fail-txt"> 
								해당 시간 예약이 불가능합니다.<br>
								<br>다른 시간대를 선택하여 주십시오.
							</span>
						</section>
						<section id="time-fail-close-btn" class="alert-btn-section">
							<span>확인</span>
						</section>
					</div>
				</section>

				<section class="fixed-section-top">
					<section class="running-time-section">
						<label class="fixed-section-label"> 운영 시간 </label>
						<ul class="running-time-li-wrap">
							<li class="running-time-li">
								<label>일</label> 
								<c:choose>
									<c:when test="${ otvo.sun_dayoff eq 'F' }">
										<span>${ otvo.sun_stime } ~ ${ otvo.sun_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="running-time-li">
								<label>월</label> 
								<c:choose>
									<c:when test="${ otvo.mon_dayoff eq 'F' }">
										<span>${ otvo.mon_stime } ~ ${ otvo.mon_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="running-time-li">
								<label>화</label> 
								<c:choose>
									<c:when test="${ otvo.tue_dayoff eq 'F' }">
										<span>${ otvo.tue_stime } ~ ${ otvo.tue_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="running-time-li">
								<label>수</label> 
								<c:choose>
									<c:when test="${ otvo.wed_dayoff eq 'F' }">
										<span>${ otvo.wed_stime } ~ ${ otvo.wed_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="running-time-li">
								<label>목</label> 
								<c:choose>
									<c:when test="${ otvo.thu_dayoff eq 'F' }">
										<span>${ otvo.thu_stime } ~ ${ otvo.thu_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="running-time-li">
								<label>금</label> 
								<c:choose>
									<c:when test="${ otvo.fri_dayoff eq 'F' }">
										<span>${ otvo.fri_stime } ~ ${ otvo.fri_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="running-time-li">
								<label>토</label> 
								<c:choose>
									<c:when test="${ otvo.sat_dayoff eq 'F' }">
										<span>${ otvo.sat_stime } ~ ${ otvo.sat_etime }</span>
									</c:when>
									<c:otherwise>
										<span>휴무</span>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</section>
					<section class="type-location-txt">
						<div class="space-type-loca">
							<label class="fixed-section-label"> 공간 유형 </label>
							<c:forEach var="type_item" items="${ type_list }">
								<span class="fixed-section-txt"> ${ type_item } </span>
							</c:forEach> 
						</div>
						<div class="space-type-loca">
							<label class="fixed-section-label"> 위치 </label>
							<div class="fixed-section-loca">
								<img src="${path}/resources/IMG/common/location-icon.svg" class="space-loca-icon" alt="location-icon"> 
								<span class="fixed-section-txt"> ${ ovo.short_roadname_address } </span>
							</div>
						</div>
					</section>
				</section>
				<section class="reserve-type-section">
                    <label class="fixed-section-label">
                        예약 공간
                    </label>

                    <!-- 타입 셀렉트 열 때 open-select클래스 add해야함 -->
                    <!-- 타입 셀렉트 닫을 때 open-select클래스 remove해야함 -->
                    <div class="type-border">
                        <span class="type-border-txt room-li-txt">오피스 공간을 선택해주세요.</span>
                        <img src="${path}/resources/IMG/space-introduce/full-dropdown.svg" alt="full-dropdown" class="full-dropdown"></img>
                    </div>

                    <!-- CUSTOM SELECT SECTION -->
                    <ul class="custom-select-type blind">
                        <li class="custom-select-type-list">
                            <span class="room-name">
                                101호 (오피스)
                            </span>
                            <span class="room-price-unit">
                                <span class="room-price-big">
                                    10,000원
                                </span>
                                <span class="room-unit-small">
                                    /시간
                                </span>
                            </span>
                        </li>
                    </ul>
                </section>
				<section class="using-time-section">
                    <section class="time-boundary">
                        <label class="fixed-section-label">
                            체크인 시간
                        </label>
                        <div class="time-select-wrap">
                            <input type="text" class="type-border-txt time-input" placeholder="날짜/시간 추가" readonly/>
                            <img src="${path}/resources/IMG/space-introduce/full-dropdown.svg" alt="full-dropdown" class="full-dropdown"></img>
                        </div>
                    </section>
                    <section class="time-boundary">
                        <label class="fixed-section-label">대여 개월 수</label>
                        <div id="month_section" class="time-select-wrap">
                            <span class="type-border-txt month-select-txt">개월수</span>
                            <img src="${path}/resources/IMG/space-introduce/full-dropdown.svg" alt="full-dropdown" class="full-dropdown"></img>

                            <div class="month-select-wrap blind">
                                <ul class="month-select">
                                    <li class="month-select-li" month="1">1개월</li>
                                    <li class="month-select-li" month="2">2개월</li>
                                    <li class="month-select-li" month="3">3개월</li>
                                    <li class="month-select-li" month="4">4개월</li>
                                    <li class="month-select-li" month="5">5개월</li>
                                    <li class="month-select-li" month="6">6개월</li>
                                    <li class="month-select-li" month="7">7개월</li>
                                    <li class="month-select-li" month="8">8개월</li>
                                    <li class="month-select-li" month="9">9개월</li>
                                    <li class="month-select-li" month="10">10개월</li>
                                    <li class="month-select-li" month="11">11개월</li>
                                    <li class="month-select-li" month="12">12개월</li>
                                </ul>
                            </div>
                        </div>
                    </section>
                    <span class="duration blind"></span>
                </section>
				<section class="maybe-pay-section blind">
					<label class="fixed-section-label"> 예상 결제 금액 </label> 
					<span class="fixed-section-label"> 40,000원 </span>
				</section>
				<section id="check_available" class="btn-section">
					<span>예약 가능 여부 확인하기</span>
				</section>
			</section>
			<!-- END fixed-section -->
		</section>
	</div>
</section>