<!-- ------------ -->
<!-- @author 김예은 -->
<!--------------- -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="homeWrap">
    <div class="homeTitle-wrap">
        <span class="homeTitle">어떤 공간을 찾으시나요?</span>
    </div>
    <div class="type-list-wrap">
        <div class="type-list type-desk">
            <section class="type-img-section">
                <img src="${path}/resources/IMG/home/desk.svg" alt="type-desk-img" class="type-img" />
            </section>
            <section class="type-title-section">
                <span>데스크</span>
            </section>
            <section class="type-txt-section">
                <p class="type-txt">
                    혼자 무언가를 할 곳이 필요하신가요?
                </p>
            </section>
        </div>

        <div class="type-list type-meeting-room">
            <section class="type-img-section">
                <img src="${path}/resources/IMG/home/meeting-room.svg" alt="type-metting-room-img" class="type-img" />
            </section>
            <section class="type-title-section">
                <span>회의실</span>
            </section>
            <section class="type-txt-section">
                <p class="type-txt">
                    &nbsp;여러 사람과 함께 할<br>
                    장소를 찾고 계신가요?
                </p>
            </section>
        </div>

        <div class="type-list type-office">
            <section class="type-img-section">
                <img src="${path}/resources/IMG/home/office-building.svg" alt="type-office-img" class="type-img" />
            </section>
            <section class="type-title-section">
                <span>오피스</span>
            </section>
            <section class="type-txt-section">
                <p class="type-txt">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    장기간 많은 사람과<br>
                    일을 할 공간을 찾아보세요
                </p>
            </section>
        </div>
    </div>
</div>