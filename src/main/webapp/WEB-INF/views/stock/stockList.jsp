<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/stock.css"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/stock.js"
	type="text/javascript" defer></script>


<c:if
	test="$ {( page <= 0 || Math.ceil(stockList.size() / 10 ) < page ) && stockList.size()>0 } ">

	<%-- 이때, keyword 값이 null이 아니면, 검색을 통한 데이터이므로, 검색 현황을 유지시킨 상태에서 1페이지로 리다이렉트 --%>
	<c:if test="${keyword != null}">
		<c:redirect
			url="/stock/list?searchOption=${searchOption}&keyword=${keyword}&page=1" />

	</c:if>
	<c:if test="${keyword == null}">
		<c:redirect url="/stock/list?page=1" />
	</c:if>
</c:if>

<main>
	<div class="stock-container">
		<div class="stock-input-container">
			<form action="/stock/list" method="get" name="stockForm"
				id="search-form">
				<div class="stock-input-wrapper input-group">
					<select class="custom-select" id="searchOption" name="searchOption">
						<option value="-1" selected>선택</option>
						<option value="1">재고 ID</option>
						<option value="2">상품 ID</option>
						<option value="3">재고명</option>

					</select> <input type="text" class="form-control" placeholder="검색어 입력"
						id="keyword" name="keyword"> <input
						class="btn btn-outline-secondary" type="submit" value="검색"
						id="stock-btn">
				</div>
			</form>
			<div class="stock-select-type-wrapper btn-group btn-group-toggle"
				data-toggle="buttons">
				<label class="btn btn-outline-secondary active"> 
				<input type="radio" name="select-type" id="order" value="1" checked>재고 주문</label> 
				<label class="btn btn-outline-secondary active"> 
				<input type="radio" name="select-type" id="edit" value="2">재고 수정</label> 
				<label class="btn btn-outline-secondary"> 
				<input type="radio" name="select-type" id="delete" value="3"> 재고 삭제</label>
			</div>
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
									<td>${stockList.get(i).getQuantity()}</td>
									<td>${stockList.get(i).getPrice()}</td>
									<td>${stockList.get(i).getStockDate()}</td>
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
										href="/stock/list?searchOption=${searchOption}&keyword=${keyword}&page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
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
										href="/stock/list?searchOption=${searchOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
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
										href="/stock/list?searchOption=${searchOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
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
										href="/stock/list?searchOption=${searchOption}&keyword=${keyword}&page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
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
