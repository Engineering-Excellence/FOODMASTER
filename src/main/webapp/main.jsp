<%@ page import="kr.or.sw.util.MyBatisUtil"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/header.jsp"%>
<link href="./css/main.css" rel="stylesheet" type="text/css">

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
	<div class="main-container">
		<c:forEach var="number" begin="1" end="60">
			<div class="main-box">
			<b>${ number }</b>
			<p class="box-font">김혁진<br>05:00남음<br>오버워치</p>
			</div>
		</c:forEach>
	</div>
</main>

<%@include file="/WEB-INF/views/footer.jsp"%>