<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" defer></script>
<script src="${pageContext.request.contextPath}/js/productIngredient.js" type="text/javascript" defer></script>

<!-- c:redirect가 안먹혀서 일단 이거로 -->
<c:if test="${adminInfo eq null}">
    <script>
        window.location.href = "/";
    </script>
</c:if>

<main>
    <div class="search-container">
        <div class="search-input-container for-ingredient">
            <div class="search-input-wrapper input-group for-ingredient">
                <input type="text" class="form-control" placeholder="재고 이름 입력"
                       id="keyword" name="keyword">
            </div>
        </div>
        <div class="search-result-container for-ingredient">
            <div class="search-result-wrapper">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">이름</th>
                    </tr>
                    </thead>
                    <tbody id="search-table-body">

                    </tbody>
                </table>
            </div>
        </div>
        <div class="select-ingredient-result-count-container">
            재료가 0가지 선택되었습니다
        </div>
        <div class="select-ingredient-container">
            <div class="select-ingredient-result-container">
                <div class="select-ingredient-result-wrapper">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">이름</th>
                            <th scope="col">수량</th>
                        </tr>
                        </thead>
                        <tbody id="select-table-body">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="control-container">
            <button type="button" class="btn btn-secondary" id="close-window">닫기</button>
            <button type="button" class="btn btn-primary" id="confirm-window">확인</button>
        </div>
    </div>
</main>
