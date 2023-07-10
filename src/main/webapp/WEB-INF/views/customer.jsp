<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="${pageContext.request.contextPath}/css/info.css" rel="stylesheet" type="text/css">

<!-- menu -->
<div class="info-menu">
    <!-- header -->
    <div class="menu-header">
        <c:forEach begin="1" end="6">
            <div class="menu-title">
                <h4 class="title-font">라면</h4>
            </div>
        </c:forEach>
        <!-- 상품 검색 버튼 -->
        <div class="search">
            <input class="search-input" type="text" placeholder="상품명 검색">
            <img class="search-img"
                 src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
        </div>
    </div>
    <!-- body -->
    <div class="menu-body">
        <c:forEach var="number" begin="1" end="12">
            <div class="menu-box">
                <img class="menu-img" alt="상품" src="${pageContext.request.contextPath}/images/${number}.jpg">
                <hr class="menu-line">
                <h5 class="menu-text">
                    신라면<br>
                    <br>5000원
                </h5>
            </div>
        </c:forEach>
    </div>
</div>

<!-- 회원 정보 -->
<div class="info-main">
    <!-- header -->
    <div class="main-header">
        <font class="header-font">PCMASTER</font>
    </div>
    <br><br>
    <!-- 회원 번호 -->
    <font class="main-number">NO. 77</font>
    <!-- 회원 시간 정보 -->
    <div class="main-info">
        <table class="info-table" border="1">
            <tr>
                <td>사용요금</td>
                <td>3,000</td>
            </tr>
            <tr>
                <td>사용시간</td>
                <td>01:23</td>
            </tr>
            <tr>
                <td>시작시간</td>
                <td>03:45</td>
            </tr>
            <tr>
                <td>남은시간</td>
                <td>01:37</td>
            </tr>
        </table>
    </div>
    <br><br>
    <!-- 버튼 -->
    <div class="main-button">
        <button class="button1">회원정보</button>
        <button class="button2">메세지</button>
        <button class="button3">자리이동</button>
        <button class="button4">시간충전</button>
        <button class="button5">시간종료</button>
    </div>
</div>
