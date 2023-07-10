<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/insert.css" rel="stylesheet" type="text/css">

<nav>nav</nav>

<main>
    <!-- 상품등록창 -->
    <div class="insert-container">
        <!-- 상품등록 제목 -->
        <div class="insert-header">
            <h1>상품등록</h1>
        </div>
        <hr>
        <br>
        <!-- 상품등록 카테고리 -->
        <div class="insert-category">
            <h3>카테고리</h3>
            <select class="custom-select">
                <option value="-1" selected>선택</option>
                <option value="라면">라면</option>
                <option value="음료수">음료</option>
                <option value="패스트푸드">패스트푸드</option>
                <option value="간식">간식</option>
                <option value="스낵">스낵</option>
                <option value="기타">기타</option>
            </select>
        </div>
        <br><br>
        <!-- 상품명 -->
        <div class="insert-name">
            <h3>상품명</h3>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="상품명을 입력해주세요"
                       aria-label="Username" aria-describedby="basic-addon1">
            </div>
        </div>
        <br><br>
        <!-- 상품가격 -->
        <div class="insert-price">
            <h3>상품가격</h3>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="상품가격을 입력해주세요"
                       aria-label="Username" aria-describedby="basic-addon1">
            </div>
        </div>
        <br><br>
        <!-- 상품이미지 등록 -->
        <div class="insert-images">
            <h3>상품이미지</h3>
        </div>
        <div class="input-group mb-3">
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="inputGroupFile02">
                <label class="custom-file-label" for="inputGroupFile02"
                       aria-describedby="inputGroupFileAddon02">상품 이미지를 업로드 해주세요</label>
            </div>
        </div>
        <br><br>
        <!-- 등록 버튼 -->
        <div class="insert-button">
            <div class="input-button">
                <button type="button" class="btn btn-secondary btn-lg btn-block">등록</button>
            </div>
        </div>
    </div>
</main>
