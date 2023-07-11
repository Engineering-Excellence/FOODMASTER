<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>PC Master</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/customer.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" defer></script>
</head>
<body>
    <div class="customer-container">
        <div class="customer-menu-container">
            <div class="customer-menu-header-container">
                <c:forEach begin="1" end="6">
                    <div class="btn btn-light customer-menu-header-category-wrapper">
                        <div class="customer-menu-header-category">
                            라면
                        </div>
                    </div>
                </c:forEach>
                <div class="customer-menu-header-search">
                    <input class="search-input form-control" type="text" placeholder="상품명 검색">
                    <img class="search-img"
                         src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
                </div>
            </div>
            <div class="customer-menu-body-container">
                <c:forEach var="number" begin="1" end="30">
                    <div class="customer-menu">
                        <img class="menu-img" alt="상품" src="${pageContext.request.contextPath}/images/${number}.jpg">
                        <div class="menu-info-container">
                            <div class="menu-name">신라면</div>
                            <div class="menu-price">5000원</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="customer-info-container">
            <div class="customer-info-header-container">
                <div class="customer-info-header-wrapper">
                    <div class="customer-info-header-logo">
                        PC MASTER
                    </div>
                </div>
            </div>
            <div class="customer-info-body-container">
                <div class="customer-info-number-wrapper">
                    <div class="customer-info-number">
                        No. 77
                    </div>
                </div>
                <div class="customer-info-user-wrapper">
                    <div class="customer-info-user-row">
                        <div class="customer-info-user-head">사용요금</div>
                        <div class="customer-info-user-data">3000</div>
                    </div>
                    <div class="customer-info-user-row">
                        <div class="customer-info-user-head">사용시간</div>
                        <div class="customer-info-user-data">01:23</div>
                    </div>
                    <div class="customer-info-user-row">
                        <div class="customer-info-user-head">시작시간</div>
                        <div class="customer-info-user-data">03:45</div>
                    </div>
                    <div class="customer-info-user-row">
                        <div class="customer-info-user-head">남은시간</div>
                        <div class="customer-info-user-data">01:37</div>
                    </div>
                </div>
                <div class="customer-info-button-container">
                    <div class="customer-info-button-row-wrapper">
                        <button class="btn btn-secondary user-button">회원정보</button>
                        <button class="btn btn-secondary user-button">메세지</button>
                    </div>
                    <div class="customer-info-button-row-wrapper">
                        <button class="btn btn-secondary user-button">자리이동</button>
                        <button class="btn btn-secondary user-button">시간충전</button>
                    </div>
                    <div class="customer-info-button-row-wrapper">
                        <button class="btn btn-danger quit-button">사용종료</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>