<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<div class="tableWrap">
	<section class="host-apply-list">
		<div class="custom-table">
			<div class="ct-header">
				<div class="ct-header-cell">신청 일자</div>
				<div class="ct-header-cell">상호명</div>
				<div class="ct-header-cell">사업자 이름</div>
				<div class="ct-header-cell">사업자 번호</div>
				<div class="ct-header-cell">사업체 이름</div>
				<div class="ct-header-cell">사업자 전화번호</div>
				<div class="ct-header-cell">사업자 이메일</div>
				<div class="ct-header-cell">수락/거절</div>
			</div>
			<!-- END ct-header -->

			<div class="ct-body">
				<c:forEach var="vo" items="${ vos }">
					<div class="ct-body-row">
						<div id="backoffice_no" class="ct-body-cell blind">${ vo.backoffice_no}</div>
						<div id="apply_date" class="ct-body-cell">${ vo.apply_date }</div>
						<div id="backoffice_name" class="ct-body-cell">${ vo.backoffice_name }</div>
						<div id="owner_name" class="ct-body-cell">${ vo.owner_name }</div>
						<div id="backoffice_id" class="ct-body-cell">${ vo.backoffice_id }</div>
						<div id="company_name" class="ct-body-cell">${ vo.company_name }</div>
						<div id="backoffice_tel" class="ct-body-cell">${ vo.backoffice_tel }</div>
						<div id="backoffice_email" class="ct-body-cell">${ vo.backoffice_email }</div>
						<div class="ct-body-cell">
							<div class="btn-group">
								<button id="btn-grant-host">수락</button>
								<button id="btn-refuse-host">거절</button>
							</div>
							<!-- END btn-group -->
						</div>
					</div>
					<!-- END ct-body-row -->
				</c:forEach>
			</div>
			<!-- END ct-body -->
		</div>
		<!-- END custom-table -->
	</section>
</div>