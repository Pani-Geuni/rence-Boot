<!-- ------------ -->
<!-- @author 전판근 -->
<!-- ------------ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="titleSection">
	<h1>환경 설정</h1>
</div>
<!-- END titleSection -->

<div class="boardWrap settings">
	<div class="titleSection settings">
		<h1>호스트 정보</h1>
		<button id="btn-update-pw" class="btn-update-pw">비밀번호 변경</button>
	</div>
	<!-- END titleSection settings -->

	<div class="host-info-list">
		<div class="host-item">
			<p>상호명</p>
			<span>${vo.backoffice_name}</span>
		</div>
		<div class="host-item">
			<p>대표</p>
			<span>${vo.owner_name}</span>
		</div>
		<div class="host-item">
			<p>사업자 등록 번호</p>
			<span>${vo.backoffice_id}</span>
		</div>
		<div class="host-item">
			<p>위치</p>
			<span>${vo.roadname_address} ${vo.number_address} ${vo.detail_address}</span>
		</div>
		<div class="host-item">
			<p>전화번호</p>
			<span>${vo.backoffice_tel}</span>
		</div>
		<div class="host-item">
			<p>이메일</p>
			<span>${vo.backoffice_email}</span>
		</div>
	</div>
	<!-- END host-info-list -->
</div>
<!-- END boardWrap -->
<div class="btn-group-settings">
	<button id="btn-host-delete" class="btn-host-delete">업체 삭제 요청</button>
	<button id="btn-host-logout" class="btn-host-delete">로그아웃</button>
</div>
<!-- END btn-group-settings -->