<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/views/header.jsp" %>

<side>
    <div class="side-container">
        <div class="side-wrapper">
            <dlv class="side-menu">근태</dlv>
        </div>
        <div class="side-wrapper">
            <dlv class="side-menu">출근/퇴근</dlv>
            <dlv class="side-menu">휴가신청</dlv>
            <dlv class="side-menu">휴가관리</dlv>
        </div>
        <div class="side-wrapper">
            <dlv class="side-menu">급여</dlv>
        </div>
        <div class="side-wrapper">
            <dlv class="side-menu">급여조회</dlv>
            <dlv class="side-menu">급여관리</dlv>
            <dlv class="side-menu">급여지급</dlv>
        </div>
        <div class="side-wrapper">
            <dlv class="side-menu">인사</dlv>
        </div>
        <div class="side-wrapper">
            <dlv class="side-menu">직원검색</dlv>
            <dlv class="side-menu">직원관리</dlv>
            <dlv class="side-menu">부서확인</dlv>
            <dlv class="side-menu">부서관리</dlv>
        </div>
    </div>
</side>
<nav>nav</nav>
<main>
    <c:if test='${pageContext.request.getParameter("redirect") == "true"}'>
        <c:redirect url="/home"/>
    </c:if>
    main<br>
    로그인 성공시 보여질 Home 페이지입니다.<br>
</main>

<%@include file="/WEB-INF/views/footer.jsp" %>