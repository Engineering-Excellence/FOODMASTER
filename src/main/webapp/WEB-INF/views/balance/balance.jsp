<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/balance.css">
<script src="${pageContext.request.contextPath}/js/balance.js"
	type="text/javascript" defer></script>

<%-- 페이지가 범위를 벗어날 경우 (0이하 혹은 전체 데이터 개수를 넘어감) 다시 1페이지로 리다이렉트 --%>
<%-- page는 1부터 시작하며, 한 페이지에 총 10개의 데이터가 담긴다 => 가능한 최대 페이지 수는 (전체 데이터 개수) / 10 를 올림한 값과 같다--%>
<c:if
	test="${(page <= 0 || Math.ceil(balanceList.size() / 10) < page) && balanceList.size() > 0}">
	<%-- 이때, keyword 값이 null이 아니면, 검색을 통한 데이터이므로, 검색 현황을 유지시킨 상태에서 1페이지로 리다이렉트 --%>
	<c:if test="${keyword != null}">
		<c:redirect
			url="/balance/list?searchOption=${searchOption}&keyword=${keyword}&page=1" />
	</c:if>
	<c:if test="${keyword == null}">
		<c:redirect url="/balance/list?page=1" />
	</c:if>
</c:if>

<main>
	<div class="search-container">
		<div class="search-input-container">


			<table>
				<tr>
					<td class="balance" style="width: 10%"><b>보유금</b></td>
					<td><fmt:formatNumber value="${balance.getAssets()}"
							pattern="###,###,###" /> 원</td>
				</tr>
				<tr>
					<td class="income"><b>누적 판매액</b></td>
					<td><fmt:formatNumber value="${balance.getAccIncome()}"
							pattern="###,###,###" /> 원</td>
				</tr>
				<tr>
					<td class="expense"><b>누적 지출액</b></td>
					<td><fmt:formatNumber value="${balance.getAccExpense()}"
							pattern="###,###,###" /> 원</td>
				</tr>
				<tr>
					<td class="profit"><b>(+)수익 / (-) 적자</b></td>
					<td><fmt:formatNumber
							value="${balance.getAccIncome()-balance.getAccExpense()}"
							pattern="###,###,###" /> 원</td>
				</tr>
			</table>




			<div class="search-result-into-container">
				${balanceList.size()}개 검색되었습니다</div>
		</div>


		<div class="search-result-container">
			<div class="search-result-wrapper">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th scope="col">계좌ID</th>
							<th scope="col">보유금</th>
							<th scope="col">비용</th>
							<th scope="col">변동일시</th>
						</tr>
					</thead>
					<tbody>
						<!-- 1페이지에 10개씩 페이징할 것 / 생년월일이랑 최근 방문일은 나중에 추가할 것-->
						<c:if test="${balanceList.size() > 0}">
							<%-- 1페이지에 10개씩, 1페이지면 0번 인덱스부터, 9번 인덱스까지의 데이터를 담게 된다. --%>
							<%-- n페이지일 경우 (10 * (n - 1))번 인덱스부터, (10 * (n - 1) + 9)번 인덱스까지의 데이터를 담게 된다. --%>
							<%-- 단, 데이터가 10개를 모두 채우지 못하고 이전에 끝난다면, 거기까지만 나오게 조절 --%>
							<c:forEach var="i" begin="${(page - 1) * 10}"
								end="${Math.min(balanceList.size() - 1, (page - 1) * 10 + 9)}">
								<tr class="balance-data" id="balanceData${i}">
									<td>${balanceList.get(i).get("ACCOUNTID")}</td>
									<td><fmt:formatNumber
											value="${balanceList.get(i).get('ASSETS')}"
											pattern="###,###,###" /></td>

									<td><fmt:formatNumber
											value="${balanceList.get(i).get('COST')}"
											pattern="###,###,###" var="formattedCost" /> <c:if
											test='${balanceList.get(i).get("FLAG") eq 0}'>


											<span style="color: red"> +${formattedCost} </span>


										</c:if> <c:if test='${balanceList.get(i).get("FLAG") eq 1}'>

											<span style="color: blue"> -${formattedCost} </span>
										</c:if></td>
									<td>${balanceList.get(i).get("DAY")}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${balanceList.size() == 0}">
							<td colspan="8">검색결과가 없습니다</td>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="search-page-container">
			<div class="search-page-wrapper">
				<ul class="pagination">
					<c:if test="${balanceList.size() > 0}">
						<%-- 일단, 이전 버튼은 11페이지 이상부터 나와야 하므로 지금 페이지가 10을 초과하는 경우에만 달아줌 --%>
						<c:if test="${page > 10}">
							<li class="page-item">
								<%-- 페이지가 1, 11, 21, 31 등에서부터 시작할 수 있게 조절하는 부분 --%> <c:if
									test="${keyword != null}">
									<a class="page-link"
										href="/balance/list?searchOption=${searchOption}&keyword=${keyword}&page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a>
								</c:if> <c:if test="${keyword == null}">
									<a class="page-link"
										href="/balance/list?page=${page - (page % 10) - (page % 10 == 0 ? 19 : 9)}"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a>
								</c:if>
							</li>
						</c:if>
						<%-- 이전과 다음 버튼 사이에 들어갈 각 페이지 버튼들, 데이터를 담는 것과 어느정도 비슷한 방식 --%>
						<c:forEach var="i" begin="${Math.floor((page - 1) / 10) * 10}"
							end="${Math.min(Math.floor(balanceList.size() / 10), Math.floor((page - 1) / 10) * 10 + 9)}">
							<c:if test="${page == i + 1}">
								<c:if test="${keyword != null}">
									<li class="page-item"><a class="page-link current-page"
										href="/balance/list?searchOption=${searchOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
									</li>
								</c:if>
								<c:if test="${keyword == null}">
									<li class="page-item"><a class="page-link current-page"
										href="/balance/list?page=${i + 1}">${i + 1}</a></li>
								</c:if>
							</c:if>
							<c:if test="${page != i + 1}">
								<c:if test="${keyword != null}">
									<li class="page-item"><a class="page-link"
										href="/balance/list?searchOption=${searchOption}&keyword=${keyword}&page=${i + 1}">${i + 1}</a>
									</li>
								</c:if>
								<c:if test="${keyword == null}">
									<li class="page-item"><a class="page-link"
										href="/balance/list?page=${i + 1}">${i + 1}</a></li>
								</c:if>
							</c:if>

						</c:forEach>
						<%-- 아까 이전 페이지때처럼, 다음 페이지 버튼은 마지막인 경우에는 추가하지 않는다 --%>
						<c:if
							test="${Math.floor((page - 1) / 10) < Math.floor(balanceList.size() / 100)}">
							<li class="page-item">
								<%-- 페이지가 1, 11, 21, 31 등에서부터 시작할 수 있게 조절하는 부분 --%> <c:if
									test="${keyword != null}">
									<a class="page-link"
										href="/balance/list?searchOption=${searchOption}&keyword=${keyword}&page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
									</a>
								</c:if> <c:if test="${keyword == null}">
									<a class="page-link"
										href="/balance/list?page=${page - (page % 10) + (page % 10 == 0 ? 1 : 11)}"
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

<%@ include file="/WEB-INF/views/layouts/footer.jsp"%>
