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
        <h1>예약 관리</h1>
      </div>
      <!-- END titleSection -->

      <div class="boardWrap reserve">
        <section class="reserve-header">
          <div class="searchBar-section">
            <div class="searchBar-wrap">
              <div id="type" class="sb_1 type">
<!--                 <span>타입</span> -->
                <span>예약자</span>
              </div>
              <div class="sb_2">
                <input type="text" placeholder="검색어를 입력하세요." id="input_searchBar" />
              </div>

              <!-- CUSTOM SELECT -->
              <section>
                <!-- START TYPE SELECT -->
                <div id="custom-type-select" class="type-select-wrap blind">
                  <ul class="type-select">
                    <li id="type-list-desk" class="type-select-list" type="desk">데스크</li>
                    <li id="type-list-metting-room" class="type-select-list" type="meeting-room">회의실</li>
                    <li id="type-list-office" class="type-select-list" type="office">오피스</li>
                  </ul>
                </div>
                <!-- END TYPE SELECT -->
              </section>
            </div>
          </div>

          <ul class="reserve-filter-list">
            <li id="reserve-all" class="reserve-item <c:if test="${reserve_state eq 'all'}">active</c:if>">전체</li>
            <li id="reserve-ing" class="reserve-item <c:if test="${reserve_state eq 'in_use'}">active</c:if>">예약중</li>
            <li id="reserve-cancel" class="reserve-item <c:if test="${reserve_state eq 'cancel'}">active</c:if>">취소</li>
            <li id="reserve-end" class="reserve-item <c:if test="${reserve_state eq 'end'}">active</c:if>">종료</li>
          </ul>
        </section>
        <!-- END reserve-header -->

        <section class="reserveWrap">
          <div class="custom-table">
            <div class="ct-header reserve">
              <div class="ct-header-cell reserve">예약 날짜</div>
              <div class="ct-header-cell reserve">진행 여부</div>
              <div class="ct-header-cell reserve">예약 공간</div>
              <div class="ct-header-cell reserve">예약자</div>
              <div class="ct-header-cell reserve">예약자 연락처</div>
              <div class="ct-header-cell reserve">예약자 이메일</div>
              <div class="ct-header-cell reserve">예약 금액</div>
              <div class="ct-header-cell reserve">결제 방식</div>
            </div>
            <!-- END ct-header -->

            <div class="ct-body">
	            <c:forEach var="vos" items="${r_vos}">
	              <!-- START ct-body-row reserve -->
	              <div class="ct-body-row reserve">
	                <div class="ct-body-cell">
	                  ${vos.reserve_sdate} ~ <br />${vos.reserve_edate}
	                </div>
	                <div class="ct-body-cell">
	                  <button class="ct-body-btn 
	                  	<c:if test="${vos.reserve_state eq 'begin'}">reserve-ing</c:if>
	                  	<c:if test="${vos.reserve_state eq 'in_use'}">reserve-doing</c:if>
	                  	<c:if test="${vos.reserve_state eq 'end'}">reserve-cancel</c:if>
	                  	<c:if test="${vos.reserve_state eq 'cancel'}">reserve-end</c:if>
	               	  ">
	                  	<c:if test="${vos.reserve_state eq 'begin'}">이용전</c:if>
	                  	<c:if test="${vos.reserve_state eq 'in_use'}">이용중</c:if>
	                  	<c:if test="${vos.reserve_state eq 'end'}">이용완료</c:if>
	                  	<c:if test="${vos.reserve_state eq 'cancel'}">취소</c:if>
	                  </button>
	                </div>
	                <div class="ct-body-cell">${vos.room_name}</div>
	                <div class="ct-body-cell">${vos.user_name}</div>
	                <div class="ct-body-cell">${vos.user_tel}</div>
	                <div class="ct-body-cell">${vos.user_email}</div>
	                <div class="ct-body-cell reserve-price">${vos.actual_payment}</div>
	                <div class="ct-body-cell">
	                	<c:if test="${vos.payment_state eq 'T'}">선결제</c:if>
	                	<c:if test="${vos.payment_state eq 'F'}">후불제</c:if>
	                </div>
	              </div>
	              <!-- END ct-body-row reserve -->
	            </c:forEach>
            </div>
            <!-- END ct-body -->

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
          <!-- END custom-table -->
        </section>
        <!-- END reserveWrap -->
      </div>
      <!-- boardWrap reserve -->