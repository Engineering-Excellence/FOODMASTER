<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>--%>

<!-- <nav> -->
<!--     <div class="nav-container"> -->
<!--         <div class="nav-wrapper"> -->
<!--             <div class="nav-menu"> -->
<!--                 <a href="/member/search?page=1"> -->
<!--                     회원정보 -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="nav-wrapper"> -->
<!--             <div class="nav-menu">탈퇴회원</div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </nav> -->

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/stockInsert.css"
      rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/productInsert.js" defer></script>

<nav>nav</nav>

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
                                   value="${stockDTO.getStockID()}"
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
                                   value="${stockDTO.getStockName()}"
                                   readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="amount">재고수량</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="number" class="form-control" id="amount" name="amount"
                                   value="${stockDTO.getAmount()}">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="unitPrice">단가</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="number" class="form-control" id="unitPrice" name="unitPrice"
                                   value="${stockDTO.getUnitPrice()}">
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
                                   value="${stockDTO.getStockDate()}" readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="productID">상품 ID</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="number" class="form-control" id="productID" name="productID"
                                   value="${stockDTO.getProductID()}" readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" class="btn btn-primary" value="수정">
                    <input type="button" class="btn btn-danger" value="취소" onclick="history.back()">
                </td>
            </tr>
        </table>
    </form>
</main>
