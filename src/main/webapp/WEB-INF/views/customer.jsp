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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/customer.js" defer></script>
</head>

<body>
<c:if test="${info eq null}">
    <c:redirect url="/"/>
</c:if>

<div class="customer-container">
    <div class="customer-menu-container">
        <div class="customer-menu-header-container">
            <div class="btn btn-light customer-menu-header-category-wrapper selected-category">
                <div class="customer-menu-header-category">
                    전체
                </div>
            </div>
            <div class="btn btn-light customer-menu-header-category-wrapper">
                <div class="customer-menu-header-category">
                    음료
                </div>
            </div>
            <div class="btn btn-light customer-menu-header-category-wrapper">
                <div class="customer-menu-header-category">
                    라면
                </div>
            </div>
            <div class="btn btn-light customer-menu-header-category-wrapper">
                <div class="customer-menu-header-category">
                    패스트푸드
                </div>
            </div>
            <div class="btn btn-light customer-menu-header-category-wrapper">
                <div class="customer-menu-header-category">
                    간식
                </div>
            </div>
            <div class="btn btn-light customer-menu-header-category-wrapper">
                <div class="customer-menu-header-category">
                    기타
                </div>
            </div>
            <div class="customer-menu-header-search">
                <input class="search-input form-control" id="search-input" type="text" placeholder="상품명 검색">
                <img class="search-img"
                     src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
            </div>
        </div>
        <div class="customer-menu-body-container">
<%--            <c:forEach var="number" begin="1" end="30">--%>
<%--                <div class="customer-menu">--%>
<%--                    <img class="menu-img" alt="상품" src="${pageContext.request.contextPath}/images/${number}.jpg">--%>
<%--                    <div class="menu-info-container">--%>
<%--                        <div class="menu-name">신라면</div>--%>
<%--                        <div class="menu-price">5000원</div>--%>
<%--                    </div>--%>
<%--                    <div class="menu-shop-btn-wrapper hide">--%>
<%--                        <button class="shopping-btn btn btn-warning">담기</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
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
                    No. ${pcNumber}
                </div>
            </div>
            <div class="customer-info-user-wrapper">
                <div class="customer-info-user-row">
                    <div class="customer-info-user-head">사용요금</div>
                    <div class="customer-info-user-data">0</div>
                </div>
                <div class="customer-info-user-row">
                    <div class="customer-info-user-head">사용시간</div>
                    <div class="customer-info-user-data">00:00</div>
                </div>
                <div class="customer-info-user-row">
                    <div class="customer-info-user-head">시작시간</div>
                    <div class="customer-info-user-data">${startDate}</div>
                </div>
                <div class="customer-info-user-row">
                    <div class="customer-info-user-head">남은시간</div>
                    <div class="customer-info-user-data">${info.getRemainTime()}</div>
                </div>
            </div>
            <div class="customer-info-button-container">
                <div class="customer-info-button-row-wrapper">
                    <!-- Button modal -->
                    <button type="button" class="btn btn-secondary user-button"
                            data-toggle="modal" data-target="#info">회원정보</button>
                    <button class="btn btn-secondary user-button">메세지</button>
                </div>

                <div class="customer-info-button-row-wrapper">
                    <button type="button" class="btn btn-secondary user-button"
                            data-toggle="modal" data-target="#shopping">장바구니</button>
                    <div class="customer-info-shopping-count-wrapper hide">
                        <div class="customer-info-shopping-count-text">0</div>
                    </div>
                    <button type="button" class="btn btn-secondary user-button"
                            data-toggle="modal" data-target="#time">시간충전</button>
                </div>
                <div class="customer-info-button-row-wrapper">
                    <a href="/auth/logout" class="btn btn-danger quit-button">사용종료</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 회원정보 Modal -->
<div class="modal fade" id="info" data-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">회원정보</h3>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label>이름</label> <input type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>이메일</label> <input type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>생년월일</label> <input type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>연락처</label> <input type="text" class="form-control">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<!-- 장바구니 Modal -->
<div class="modal fade" id="shopping" data-backdrop="static"
     data-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">...</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Understood</button>
            </div>
        </div>
    </div>
</div>

<!-- 시간충전 Modal -->
<div class="modal fade" id="time" data-backdrop="static"
     data-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">...</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Understood</button>
            </div>
        </div>
    </div>
</div>

</body>

</html>