<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/insert.css"
      rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/validation.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/productInsert.js" defer></script>

<main>
    <!-- 상품수정창 -->
    <div class="insert-container">
        <div class="insert-header-wrapper">
            <div class="insert-header">
                <h1>상품수정</h1>
            </div>
        </div>
        <div class="insert-body-wrapper">
            <div class="insert-image-preview-wrapper">
                <img src="${productDTO.image.getRelPath()}" class="rounded" alt="..." id="preview">
            </div>
            <div class="vertical-line"></div>
            <div class="insert-wrapper">
                <form action="/product/update" method="post"
                      enctype="multipart/form-data" id="product-update-form">
                    <br>
                    <!-- 상품명 -->
                    <div class="insert-name">
                        <h3>번호</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="productID"
                                   readonly="readonly" value="${productDTO.getProductID()}">
                        </div>
                    </div>
                    <br> <br>
                    <!-- 상품수정 카테고리 -->
                    <div class="insert-category">
                        <h3>카테고리</h3>
                        <select class="custom-select" name="category" id="category">
                            <option value="-1" selected>선택</option>
                            <option value="라면">라면</option>
                            <option value="음료">음료</option>
                            <option value="패스트푸드">패스트푸드</option>
                            <option value="간식">간식</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <br> <br>
                    <!-- 상품명 -->
                    <div class="insert-name">
                        <h3>상품명</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="상품명을 입력해주세요"
                                   aria-label="Username" name="productName" value="${productDTO.getProductName()}">
                        </div>
                    </div>
                    <br> <br>
                    <!-- 상품가격 -->
                    <div class="insert-price">
                        <h3>상품가격</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control"
                                   placeholder="상품가격을 입력해주세요" aria-label="Username" name="price" id="price"
                                   value="${productDTO.getPrice()}">
                        </div>
                    </div>
                    <br> <br>
                    <!-- 상품이미지 수정 -->
                    <div class="insert-images">
                        <h3>상품이미지</h3>
                    </div>
                    <div class="input-group">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="image-upload"
                                   name="productImg"> <label class="custom-file-label"
                                                             id="image-upload-label" for="image-upload">상품 이미지
                            선택</label>
                        </div>
                    </div>
                    <br> <br>
                    <!-- 수정 버튼 -->
                    <div class="insert-button">
                        <div class="input-button">
                            <input type="submit" class="btn btn-secondary btn-lg btn-block"
                                   value="수정">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
