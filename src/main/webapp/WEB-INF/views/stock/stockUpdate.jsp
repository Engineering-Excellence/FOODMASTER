<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/stockInsert.css"
      rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/productInsert.js" defer></script>

<main>
    <h1 class="mt-4">재고 정보 수정</h1>
    <form action="/stock/update" method="post">
        <table class="table mt-4">
            <tr>
                <th>
                    <label for="stockID">재고 ID</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="stockID" name="stockID"
                                   value="${stockVO.getStockID()}"
                                   readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="stockName">재고명</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="stockName" name="stockName"
                                   value="${stockVO.getStockName()}"
                                   readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="quantity">재고수량</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="number" class="form-control" id="quantity" name="quantity"
                                   value="${stockVO.getQuantity()}">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="price">단가</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="number" class="form-control" id="price" name="price"
                                   value="${stockVO.getPrice()}">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="stockDate">최근입고일자</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="date" class="form-control" id="stockDate" name="stockDate"
<<<<<<< Updated upstream
                                   value="${stockVO.getStockDate()}" readonly>

=======
                                   value="${stockDTO.getStockDate()}" readonly>
>>>>>>> Stashed changes
                        </div>
                    </div>
                </td>
            </tr>
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" class="btn btn-primary" value="수정">
                    <input type="button" class="btn btn-danger" value="취소" onclick="history.back()">
                </td>
            </tr>
        </table>
    </form>
</main>
