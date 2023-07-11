<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/main.css"
      rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/stock.css"
      rel="stylesheet" type="text/css">

<nav>
    <div class="nav-container">
        <div class="nav-wrapper">
            <div class="nav-menu">
                <a href="/member/stock?page=1"> 회원정보 </a>
            </div>
        </div>
        <div class="nav-wrapper">
            <div class="nav-menu">탈퇴회원</div>
        </div>
    </div>
</nav>

<main>
    <div class="stock-container">
        <div class="stock-input-container">
            <form action="/stock/list" method="get" name="stockForm" 
                  id="stock-form">
                <div class="stock-input-wrapper input-group">
                    <select class="custom-select" id="stockOption" name="stockOption">
                        <option value="-1" selected>선택</option>
                        <option value="1">재고 ID</option>
                        <option value="2">품목명</option>
                    </select> <input type="text" class="form-control" placeholder="검색어 입력"
                                     id="keyword" name="keyword"> <input
                        class="btn btn-outline-secondary" type="submit" value="검색"
                        id="stock-btn">
                </div>
            </form>
            <div class="stock-select-type-wrapper btn-group btn-group-toggle"
                 data-toggle="buttons">
                <label class="btn btn-outline-secondary" id="edit">재고 수정</label>
                <label class="btn btn-outline-secondary" id="delete">재고 삭제</label>
            </div>

            <!-- //////////////////////////////재고 추가 모달 시작/////////////////////////////////////////////   -->



            <!-- //////////////////////////////재고 추가 모달 끝/////////////////////////////////////////////////////////////////////////////  -->


            <!-- //////////////////////// 재고 수정 페이지 //////////////////////////////////////////////////////////////////////////////////// -->


            <div class="stock-result-into-container">
                ${stockList.size()}개의 상품이 검색되었습니다
                <!-- 재고 리스트 필요함 -->
            </div>
        </div>
        <div class="stock-result-container">
            <div class="stock-result-wrapper">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">재고 ID</th>
                        <th scope="col">재고명</th>
                        <th scope="col">재고수량</th>
                        <th scope="col">단가</th>
                        <th scope="col">최근입고일자</th>
                        <th scope="col">상품 ID</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- stockList DB가 필요함. 현재 stock DB 누락으로 데이터 출력 불가능-->
                    <c:if test="${stockList.size() > 0}">
                        <%-- 1페이지에 10개씩, 1페이지면 0번 인덱스부터, 9번 인덱스까지의 데이터를 담게 된다. --%>
                        <%-- n페이지일 경우 (10*(n - 1))번 인덱스부터, (10*(n - 1) + 9)번 인덱스까지의 데이터를 담게 된다. --%>
                        <%-- 단, 데이터가 10개를 모두 채우지 못하고 이전에 끝난다면, 거기까지만 나오게 조절 --%>
                        <c:forEach var="i" begin="${(page - 1) * 10}"
                                   end="${Math.min(stockList.size() - 1, (page - 1) * 10 + 9)}">
                            <tr class="stock-data" id="stockData${i}">

                                <td>${stockList.get(i).getStockID()}</td>
                                <td>${stockList.get(i).getStockName()}</td>
                                <td>${stockList.get(i).getAmount()}</td>
                                <td>${stockList.get(i).getUnitPrice()}</td>
                                <td>${stockList.get(i).getRegDate()}</td> <!-- 재고입고일 -->
                                <td>${stockList.get(i).getProductID()}</td>

                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${stockList.size() == 0}">
                        <td colspan="8">검색결과가 없습니다</td>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="stock-page-container">
            <div class="stock-page-wrapper">
                <ul class="pagination">
                    <c:if test="${stockList.size() > 0}">
                        <%-- 일단, 이전 버튼은 11페이지 이상부터 나와야 하므로 지금 페이지가 10을 초과하는 경우에만 달아줌 --%>
                        <c:if test="${page > 10}">
                            <li class="page-item">
                                    <%-- 페이지가 1, 11, 21, 31 등에서부터 시작할 수 있게 조절하는 부분 --%> <c:if
                                    test="${keyword != null}">
                                <a class="page-link"
                                   href="/stock/list?stockOption=${stockOption}&keyword=${keyword}&page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
                                   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if> <c:if test="${keyword == null}">
                                <a class="page-link"
                                   href="/stock/list?page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
                                   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                            </li>
                        </c:if>
                        <%-- 이전과 다음 버튼 사이에 들어갈 각 페이지 버튼들, 데이터를 담는 것과 어느정도 비슷한 방식 --%>
                        <c:forEach var="i" begin="${Math.floor((page - 1) / 10) * 10}"
                                   end="${Math.min(Math.floor(stockList.size() / 10), Math.floor((page - 1) / 10) * 10 + 9)}">
                            <c:if test="${page == i + 1}">
                                <c:if test="${keyword != null}">
                                    <li class="page-item"><a class="page-link current-page"
                                                             href="/stock/list?stockOption=${stockOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${keyword == null}">
                                    <li class="page-item"><a class="page-link current-page"
                                                             href="/stock/list?page=${i + 1}">${i + 1}</a></li>
                                </c:if>
                            </c:if>
                            <c:if test="${page != i + 1}">
                                <c:if test="${keyword != null}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/stock/list?stockOption=${stockOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${keyword == null}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/stock/list?page=${i + 1}">${i + 1}</a></li>
                                </c:if>
                            </c:if>
                        </c:forEach>
                        <%-- 아까 이전 페이지때처럼, 다음 페이지 버튼은 마지막인 경우에는 추가하지 않는다 --%>
                        <c:if
                                test="${Math.floor((page - 1) / 10) < Math.floor(stockList.size() / 100)}">
                            <li class="page-item">
                                    <%-- 페이지가 1, 11, 21, 31 등에서부터 시작할 수 있게 조절하는 부분 --%> <c:if
                                    test="${keyword != null}">
                                <a class="page-link"
                                   href="/stock/list?stockOption=${stockOption}&keyword=${keyword}&page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
                                   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if> <c:if test="${keyword == null}">
                                <a class="page-link"
                                   href="/stock/list?page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
                                   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</main>
