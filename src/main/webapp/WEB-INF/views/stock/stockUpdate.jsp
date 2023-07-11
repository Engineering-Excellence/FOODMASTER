<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> --%>

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


<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<link href="${pageContext.request.contextPath}/css/stockInsert.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/productInsert.js" defer></script>

<nav>nav</nav>



<main>
	<!-- 상품등록창 -->
	<div class="insert-container">
		<div class="insert-header-wrapper">
			<div class="insert-header">
				<h1>재고 수정</h1>
			</div>
		</div>
		<div class="insert-body-wrapper">
			<div class="insert-wrapper">
				<form action="/stock/update" method="post">
					<br><br>
					<div class="insert-name">
						<h3>재고명</h3>
						<div class="input-group mb-3">
							<input type="text" class="form-control" placeholder=""
								aria-label="Username" name="stockName" readonly="readonly">
						</div>
					</div>
					<br> <br>
					<!-- 재고수량 -->
					<div class="insert-price">
						<h3>재고수량</h3>
						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="" aria-label="Username" name="amount">
						</div>
					</div>
					<br> <br>
					<div class="insert-price">
						<h3>재고 단가</h3>
						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="" aria-label="Username" name="unitPrice">
						</div>
					</div>
					<br> <br>
					<div class="insert-price">
						<h3>재고 입고일</h3>
						<div class="input-group mb-3">
							<input type="text" class="form-control"
								placeholder="" aria-label="Username" name="stockDate">
						</div>
					</div>
					<br> <br>
					<!-- 등록 버튼 -->
					<div class="insert-button">
						<div class="input-button"> 
							<input type="submit" class="btn btn-secondary btn-lg btn-block" value="수정"></input>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</main>





<!-- <main> -->
<!--     <h1 class="mt-4">재고 정보 수정</h1> -->
<!--     <form action="/stock/update" method="post"> -->
<!--         <table class="table mt-4"> -->
<!--             <tr> -->
<!--                 <th> -->
<!--                     <label for="">재고명</label></th> -->
<!--                 <td> -->
<!--                     <input type="text" id="" name="" value="" readonly> -->
<!--                 </td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <th> -->
<!--                     <label for="">재고 수량</label></th> -->
<!--                 <td> -->
<!--                     <input type="text" id="" name="" value=""> -->
<!--                 </td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <th> -->
<!--                     <label for="">재고 단가</label></th> -->
<!--                 <td> -->
<!--                     <input type="text" id="" name="" value=""> -->
<!--                 </td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <th> -->
<!--                     <label for="">재고 입고일</label></th> -->
<!--                 <td> -->
<!--                     <input type="text" id="" name="" value="" -->
<!--                            readonly> -->
<!--                 </td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td colspan="2" align="center"> -->
<!--                     <input type="submit" class="btn btn-primary" value="수정"> -->
<!--                     <input type="button" class="btn btn-danger" value="취소" onclick="history.back()"> -->
<!--                 </td> -->
<!--             </tr> -->
<!--         </table> -->
<!--     </form> -->
<!-- </main> -->
