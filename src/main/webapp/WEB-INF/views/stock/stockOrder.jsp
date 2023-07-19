<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/stockInsert.css"
      rel="stylesheet" type="text/css">

<main>
    <!-- 재고주문 -->
    <div class="insert-container">
        <div class="insert-header-wrapper">
            <div class="insert-header">
                <h1>재고 주문</h1>
            </div>
        </div>
        <div class="insert-body-wrapper">
            <div class="insert-wrapper">
                <form action="/stock/order" method="post">
                    <br> <br>
                    <div class="insert-stockName">
                        <h3>재고명</h3>
                        <div class="input-group mb-3">
                            <input type="hidden" id="stockID" name="stockID" value="${stockVO.getStockID()}">
                            <input type="text" class="form-control" aria-label="Username" id="stockName"
                                   name="stockName" value="${stockVO.getStockName()}" readonly>
                        </div>
                    </div>
                    <br> <br>
                    <div class="insert-quantity">
                        <h3>재고 수량</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" aria-label="Username" id="quantity"
                                   name="quantity"
                                   onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))">
                        </div>
                    </div>
                    <br> <br>
                    <div class="insert-price">
                        <h3>재고 가격</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" aria-label="Username" id="price"
                                   name="price"
                                   onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))"
                                   value="${stockVO.getPrice()}" readonly>
                        </div>
                    </div>
                    <br> <br>
                    <div class="insert-stockDate">
                        <h3>재고 입고일</h3>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" aria-label="Username"
                                   name="stockDate" value="${stockVO.getStockDate()}" readonly>
                        </div>
                    </div>
                    <br> <br>
                    <!-- 등록 버튼 -->
                    <div class="insert-button">
                        <div class="input-button">
                            <input type="submit" class="btn btn-secondary btn-lg btn-block"
                                   value="주문"></input>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>