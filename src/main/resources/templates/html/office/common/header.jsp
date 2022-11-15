<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<%
	Cookie[] cookies = request.getCookies(); //client에서 쿠키를 받아옴
	
	String user_image = "";
	
	if(cookies!=null){
	    for(int i=0;i<cookies.length;i++){
	        if(cookies[i].getName().equals("user_image")){
	        	user_image = cookies[i].getValue();
	        }
	    }
	}
%>
    
 <section class ="headerWrap">
     <div class = "logo-section">
         <img src="${path}/resources/IMG/common/RENCE.svg" class="logo-mku">
     </div>
     <div class = "searchBar-section">
         <div class="searchBar-wrap">
             <div id="type" class = "sb_1 type">
                 <span>타입</span>
             </div>
             <div id="location" class = "sb_1">
                 <span id="location_val">장소</span>
             </div>
             <div class = "sb_2">
                 <input type="text" placeholder="검색어를 입력하세요." id="input_searchBar"/>
             </div>
             <div class = "searchBar-btnWrap">
                 <img src="${path}/resources/IMG/header/Search Button.svg" alt="searchBar-btn" class="searchBar-btn"/>
             </div>

             <!-- CUSTOM SELECT -->
             <section>
                 <!-- START TYPE SELECT -->
                 <div id = "custom-type-select" class="type-select-wrap blind">
                     <ul class="type-select">
                        <li id = "type-list-desk" class="type-select-list" val="desk">데스크</li>
                        <li id = "type-list-metting-room" class="type-select-list" val="meeting_room">회의실</li>
                        <li id = "type-list-office" class="type-select-list" val="office">오피스</li>
                     </ul>
                 </div>
                 <!-- END TYPE SELECT -->

                 <!-- START Location SELECT -->
                 <div id = "custom-location-select" class="location-select-wrap blind">
                     <ul id = "location-city" class="location-select">
                         <li class="location-select-list sample blind">데스크</li>
                     </ul>
                     <ul id = "location-town" class="location-select blind">
                         <li class="location-select-list sample blind">데스크</li>
                     </ul>
                 </div>
                 <!-- END TYPE SELECT -->
             </section>
         </div>
     </div>
     <div class="userMenu-section ">
     	<c:if test="${user_id eq null}">
	         <!-- 로그인 전 유저 메뉴 -->
	         <section id="before_login" class="">
	             <div id = "before_userMenu" class ="userMenu">
	                 <img src="${path}/resources/IMG/header/user_menu.svg" alt="user_menu_img" class="user_menu_img"/>
	                 <img src="${path}/resources/IMG/header/bx_user-circle.png" alt="user_profile_img" class="user_profile_img"/>
	             </div>
	             
	             <!-- CUSTOM SELECT -->
	             <div class = "custom-select-user blind">
	                 <ul class="user-select-wrap">
	                     <li id = "go-login" class="user-select-list">로그인</li>
	                     <li id = "go-join" class="user-select-list">회원가입</li>
	                     <li id = "go-backOffice" class="user-select-list">공간등록신청</li>
	                 </ul>
	             </div>
	         </section>
     	</c:if>
         
       	<c:if test="${user_id ne null}">
	         <!-- 로그인 후 유저 메뉴 -->
	         <section id="after_login"  class="">
	             <div id = "after_userMenu" class ="userMenu">
	                 <img src="${path}/resources/IMG/header/user_menu.svg" alt="user_menu_img" class="user_menu_img"/>
	                 <img src="${path}/resources/upload/<%= user_image %>" alt="user_profile_img" class="user_profile_img"/>
	             </div>
	
	             <!-- CUSTOM SELECT -->
	             <div class = "custom-select-user blind">
	                 <ul class="user-select-wrap">
	                     <li id = "go-myPage" class="user-select-list">마이페이지</li>
	                     <li id = "go-backOffice" class="user-select-list">공간등록신청</li>
	                     <li id = "go-logOut" class="user-select-list">로그아웃</li>
	                 </ul>
	             </div>
	         </section>
         </c:if>
     </div>
 </section>