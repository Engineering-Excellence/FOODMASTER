<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<script src="${pageContext.request.contextPath}/js/product.js" type="text/javascript" defer></script>

<%-- 페이지가 범위를 벗어날 경우 (0이하 혹은 전체 데이터 개수를 넘어감) 다시 1페이지로 리다이렉트 --%>
<%-- page는 1부터 시작하며, 한 페이지에 총 10개의 데이터가 담긴다 => 가능한 최대 페이지 수는 (전체 데이터 개수) / 10 를 올림한 값과 같다--%>
<c:if test="${page <= 0 || Math.ceil(productList.size() / 10) < page && productList.size() > 0}">
    이때, keyword 값이 null이 아니면, 검색을 통한 데이터이므로, 검색 현황을 유지시킨 상태에서 1페이지로 리다이렉트
    <c:if test="${keyword != null}">
        <c:redirect url="/product/list?searchOption=${searchOption}&keyword=${keyword}&page=1"/>
    </c:if>
    <c:if test="${keyword == null}">
        <c:redirect url="/product/list?page=1"/>
    </c:if>
</c:if>

<main>
    <div class="search-container">
        <div class="search-input-container">
            <form action="/product/list" method="get" name="searchForm" id="search-form">
                <div class="search-input-wrapper input-group">
                    <select class="custom-select" id="searchOption" name="searchOption">
                        <option value="-1" selected>선택</option>
                        <option value="1">번호</option>
                        <option value="2">카테고리</option>
                        <option value="3">상품명</option>
                    </select>
                    <input type="text" class="form-control" placeholder="검색어 입력" id="keyword"
                           name="keyword">
                    <input type="text" id="page" name="page" value="1" hidden>
                    <input class="btn btn-outline-secondary" type="submit" value="검색" id="search-btn">
                </div>
            </form>
            <div class="search-select-type-wrapper btn-group btn-group-toggle" data-toggle="buttons">
                <label class="btn btn-outline-secondary active">
                    <input type="radio" name="select-type" id="edit" value="1" checked> 상품수정
                </label>
                <label class="btn btn-outline-secondary">
                    <input type="radio" name="select-type" id="delete" value="2"> 상품삭제
                </label>
                <label class="btn btn-outline-secondary">
                    <input type="radio" name="select-type" id="view" value="3"> 재료등록
                </label>
            </div>
            <div class="search-result-into-container">
                ${productList.size()}개 검색되었습니다
            </div>
        </div>
        <div class="search-result-container">
            <div class="search-result-wrapper">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">카테고리</th>
                        <th scope="col">상품명</th>
                        <th scope="col">상품가격</th>
                        <th scope="col">재고</th>
                        <th scope="col">재료등록</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 1페이지에 10개씩 페이징할 것 / 생년월일이랑 최근 방문일은 나중에 추가할 것-->
                    <c:if test="${productList.size() > 0}">
                        <%-- 1페이지에 10개씩, 1페이지면 0번 인덱스부터, 9번 인덱스까지의 데이터를 담게 된다. --%>
                        <%-- n페이지일 경우 (10 * (n - 1))번 인덱스부터, (10 * (n - 1) + 9)번 인덱스까지의 데이터를 담게 된다. --%>
                        <%-- 단, 데이터가 10개를 모두 채우지 못하고 이전에 끝난다면, 거기까지만 나오게 조절 --%>
                        <c:forEach var="i" begin="${(page - 1) * 10}"
                                   end="${Math.min(productList.size() - 1, (page - 1) * 10 + 9)}">
                            <tr class="product-data" id="productData${i}">
                                <td>${productList.get(i).getProductID()}</td>
                                <td>${productList.get(i).getCategory()}</td>
                                <td>${productList.get(i).getProductName()}</td>
                                <td>${productList.get(i).getPrice()}</td>
                                <td>${productList.get(i).getQuantity()}</td>
                                <c:if test="${productList.get(i).isRecipe()}">
                                    <td>완료</td>
                                </c:if>
                                <c:if test="${!productList.get(i).isRecipe()}">
                                    <td>미완료</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${productList.size() == 0}">
                        <td colspan="8">검색결과가 없습니다</td>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="search-page-container">
            <div class="search-page-wrapper">
                <ul class="pagination">
                    <c:if test="${productList.size() > 0}">
                        <%-- 일단, 이전 버튼은 11페이지 이상부터 나와야 하므로 지금 페이지가 10을 초과하는 경우에만 달아줌 --%>
                        <c:if test="${page > 10}">
                            <li class="page-item">
                                    <%-- 페이지가 1, 11, 21, 31 등에서부터 시작할 수 있게 조절하는 부분 --%>
                                <c:if test="${keyword != null}">
                                    <a class="page-link"
                                       href="/product/list?searchOption=${searchOption}&keyword=${keyword}&page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </c:if>
                                <c:if test="${keyword == null}">
                                    <a class="page-link"
                                       href="/product/list?page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </c:if>
                            </li>
                        </c:if>
                        <%-- 이전과 다음 버튼 사이에 들어갈 각 페이지 버튼들, 데이터를 담는 것과 어느정도 비슷한 방식 --%>
                        <c:forEach var="i" begin="${Math.floor((page - 1) / 10) * 10}"
                                   end="${Math.min(Math.floor(productList.size() / 10), Math.floor((page - 1) / 10) * 10 + 9)}">
                            <c:if test="${page == i + 1}">
                                <c:if test="${keyword != null}">
                                    <li class="page-item"><a class="page-link current-page"
                                                             href="/product/list?searchOption=${searchOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${keyword == null}">
                                    <li class="page-item"><a class="page-link current-page"
                                                             href="/product/list?page=${i + 1}">${i + 1}</a></li>
                                </c:if>
                            </c:if>
                            <c:if test="${page != i + 1}">
                                <c:if test="${keyword != null}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/product/list?searchOption=${searchOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
                                    </li>
                                </c:if>
                                <c:if test="${keyword == null}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/product/list?page=${i + 1}">${i + 1}</a>
                                    </li>
                                </c:if>
                            </c:if>

                        </c:forEach>
                        <%-- 아까 이전 페이지때처럼, 다음 페이지 버튼은 마지막인 경우에는 추가하지 않는다 --%>
                        <c:if test="${Math.floor((page - 1) / 10) < Math.floor(productList.size() / 100)}">
                            <li class="page-item">
                                    <%-- 페이지가 1, 11, 21, 31 등에서부터 시작할 수 있게 조절하는 부분 --%>
                                <c:if test="${keyword != null}">
                                    <a class="page-link"
                                       href="/product/list?searchOption=${searchOption}&keyword=${keyword}&page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:if>
                                <c:if test="${keyword == null}">
                                    <a class="page-link"
                                       href="/product/list?page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
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

<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>
