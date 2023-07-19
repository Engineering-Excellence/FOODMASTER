<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/stockInsert.css"
      rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/productInsert.js" defer></script>

<main>
    <!-- 상품등록창 -->
    <div class="insert-container">
        <div class="insert-header-wrapper">
            <div class="insert-header">
                <h1>재고 등록</h1>
            </div>
        </div>
        <div class="insert-body-wrapper">
            <div class="insert-wrapper">
                <form action="/stock/insert" method="post">
                    <br> <br>
                    <div class="insert-stockName">
                        <h3>재고명</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="재고명을 입력해주세요"
                                   aria-label="Username" name="stockName">
                        </div>
                    </div>
                    <br> <br>
                    <div class="insert-price">
                        <h3>재고 단가</h3>
                        <div class="input-group mb-3">
                            <input type="number" class="form-control"
                                   placeholder="단가를 입력해주세요" aria-label="Username"
                                   name="price">
                        </div>
                    </div>
                    <br> <br>
                    <!-- 등록 버튼 -->
                    <div class="insert-button">
                        <div class="input-button">
                            <input type="submit" class="btn btn-secondary btn-lg btn-block"></input>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>