<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- 리스트 NULL일 때 -->
<c:if test="${res.cnt == 0}">
	<div class="list-page-wrap">
	    <section class="list-box-null-section">
	        <div class="null-first-section">
	            <span class="advice-txt">해당 검색어에 관한 공간이 없습니다.</span>
	        </div>
	        <div class="null-second-section">
	            <span class="advice-txt-small">이런 공간은 어떠세요?</span>
	            <input type="button" id="go-main-btn" class="go-all-list" value="다른 곳 보러 가기"/>
	        </div>
	    </section>
	</div>
</c:if>

<!-- 리스트 NOT NULL일 때 -->
<c:if test="${res.cnt != 0}">
	<section class="listPage-section">
	    <div class="sort-wrap">
	        <div class="sort">
	            <span class="choice-sort-text">
		            <c:if test="${res.condition eq 'date'}">
		            	최신순
		            </c:if>
		            <c:if test="${res.condition eq 'rating'}">
		            	별점순
		            </c:if>
		            <c:if test="${res.condition eq 'cheap'}">
		            	가격 낮은순
		            </c:if>
		            <c:if test="${res.condition eq 'expensive'}">
		            	가격 높은순
		            </c:if>
	            </span>
	            <img src="${path}/resources/IMG/list/dropdown.svg" alt="sort-dropdown-img" class="sort-dropdown-img" id="sort-dropdown-img"/>
	        </div>
	        <div class="sort-select-box-wrap blind">
	            <ul class="sort-select-box">
	                <li condition="date" class="sort-select-list">최신순</li>
                    <li condition="rating" class="sort-select-list">별점순</li>
                    <li condition="cheap" class="sort-select-list">가격 낮은순</li>
                    <li condition="expensive" class="sort-select-list">가격 높은순</li>
	            </ul>
	        </div>    
	    </div>
	    
	    <div class="listPage-wrap">
	        <div class="list-box-wrap">
		        <c:forEach var="obj" items="${res.list}">
				<!-- START LIST BOX -->
		          <div class="list-box" idx="${obj.backoffice_no}">
		              <section>
		                  <img src="${path}/resources/upload/${obj.backoffice_image}" alt="default-space-img" class="list-thumbnail" />
		              </section>
		              <section class="list-box-info">
		                  <ul>
		                      <li class="box-info-c-name">${obj.company_name}</li>
		                      <li class="box-location-wrap">
		                          <img src="${path}/resources/IMG/office/location-icon.svg" alt="location-icon" class="location-icon">
		                          <span class="box-info-location">${obj.roadname_address}</span>
		                      </li>
		                      <li class="box-tag-wrap">
		                          <span class="box-tag">${obj.backoffice_tag}</span>
		                      </li>
		                      <li class="box-price-rating-wrap">
		                          <div class="box-room-min-price-wrap">
		                              <span class="box-room-min-price">최소 ${obj.min_room_price}</span>
		                              <span class="price-unit">원/시간</span>
		                          </div>
		                          <div class="box-room-rating">
		                              <img src="${path}/resources/IMG/common/star.svg" alt="box-star" class="box-star" />
		                              <span class="rating-num">${obj.avg_rating}</span>
		                          </div>
		                      </li>
		                  </ul>
		              </section>
		          </div>
		          <!-- END LIST BOX -->
		          </c:forEach>
	        </div>
	    </div>
	</section>
</c:if>