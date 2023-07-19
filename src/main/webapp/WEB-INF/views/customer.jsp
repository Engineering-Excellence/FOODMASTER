<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>PC Master</title>
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/customer.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js" defer></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/customer.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js" defer></script>

	<c:if test="${info eq null}">
		<c:redirect url="/" />
	</c:if>

<script>
	// el 정렬 하지 말것
	var memberID = ${info.getMemberID()};
</script>
</head>

<body>
	<div class="customer-container">
		<div class="customer-menu-container">
			<div class="customer-menu-header-container">
				<div
					class="btn btn-light customer-menu-header-category-wrapper selected-category">
					<div class="customer-menu-header-category">전체</div>
				</div>
				<div class="btn btn-light customer-menu-header-category-wrapper">
					<div class="customer-menu-header-category">음료</div>
				</div>
				<div class="btn btn-light customer-menu-header-category-wrapper">
					<div class="customer-menu-header-category">라면</div>
				</div>
				<div class="btn btn-light customer-menu-header-category-wrapper">
					<div class="customer-menu-header-category">패스트푸드</div>
				</div>
				<div class="btn btn-light customer-menu-header-category-wrapper">
					<div class="customer-menu-header-category">간식</div>
				</div>
				<div class="btn btn-light customer-menu-header-category-wrapper">
					<div class="customer-menu-header-category">기타</div>
				</div>
				<div class="customer-menu-header-search">
					<input class="search-input form-control" id="search-input"
						type="text" placeholder="상품명 검색"> <img class="search-img"
						src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
				</div>
			</div>
			<div class="customer-menu-body-container">
				<%--            <c:forEach var="number" begin="1" end="30">--%>
				<%--                <div class="customer-menu">--%>
				<%--                    <img class="menu-img" alt="상품" src="${pageContext.request.contextPath}/images/${number}.jpg">--%>
				<%--                    <div class="menu-info-container">--%>
				<%--                        <div class="menu-name">신라면</div>--%>
				<%--                        <div class="menu-price">5000원</div>--%>
				<%--                    </div>--%>
				<%--                    <div class="menu-shop-btn-wrapper hide">--%>
				<%--                        <button class="shopping-btn btn btn-warning">담기</button>--%>
				<%--                    </div>--%>
				<%--                </div>--%>
				<%--            </c:forEach>--%>
			</div>
		</div>
		<div class="customer-info-container">
			<div class="customer-info-header-container">
				<div class="customer-info-header-wrapper">
					<div class="customer-info-header-logo">FOOD MASTER</div>
				</div>
			</div>
			<div class="customer-info-body-container">
				<div class="customer-info-user-wrapper">
						<div class="modal-header">
							<h5 class="modal-title">장바구니</h5>
						</div>
						<div class="modal-body" id="shopping-modal-body">
<%--						<div class="card border-secondary mb-3" style="max-width: 100%;">--%>
<%--							<div class="card-header">--%>
<%--								<div class="product-name-wrapper">신라면</div>--%>
<%--								<div class="product-remove-btn-wrapper">--%>
<%--									<button class="product-remove-btn">&times;</button>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<div class="card-body text-secondary">--%>
<%--								<div class="shopping-count-wrapper">--%>
<%--									<button class="btn btn-outline-secondary shopping-product-count-minus">-</button>--%>
<%--									<input type="number" class="form-control shopping-product-count" readonly="readonly" value="1" style="text-align: center">--%>
<%--									<button class="btn btn-outline-secondary shopping-product-count-plus">+</button>--%>
<%--								</div>--%>
<%--								<div class="shopping-price-wrapper">--%>
<%--									<div class="shopping-price">5000원</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--						</div>--%>
						</div>
						<div class="modal-footer">
							<div class="shopping-total-wrapper">
								<div class="shopping-total-text first-text">총 주문금액</div>
								<div class="shopping-total-text price" id="total-price">
									0원</div>
							</div>
							<div class="shopping-modal-control-wrapper">
								<button type="button" class="btn btn-primary"
										id="shopping-make-order" data-dismiss="modal">주문</button>
							</div>
						</div>
				</div>
				<div class="customer-info-button-container">
					<div class="customer-info-button-row-wrapper">
						<!-- Button modal -->
						<button type="button" class="btn btn-secondary user-button"
							data-toggle="modal" data-target="#info">회원정보</button>
						<button type="button" class="btn btn-secondary user-button"
							data-toggle="modal" data-target="#update"
							id="open-update-modal">회원수정</button>
					</div>

					<div class="customer-info-button-row-wrapper">
						<button type="button" class="btn btn-secondary user-button"
							id="resign-modal-btn" data-toggle="modal" data-target="#resign">회원탈퇴</button>
						<a href="/auth/logout" class="btn btn-danger quit-button">사용종료</a>
					</div>
					<div class="customer-info-button-row-wrapper">

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 회원정보 Modal -->
	<div class="modal fade" id="info" data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">회원정보</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="close-update-modal">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>이름</label> <input type="text" class="form-control"
								readonly value="${info.getName()}">
						</div>
						<div class="form-group">
							<label>이메일</label> <input type="text" class="form-control"
								readonly value="${info.getEmail()}">
						</div>
						<div class="form-group">
							<label>생년월일</label> <input type="text" class="form-control"
								readonly value="${info.getBirthDate()}">
						</div>
						<div class="form-group">
							<label>연락처</label> <input type="text" class="form-control"
								readonly value="${info.getContact()}" id="info-contact">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 회원탈퇴 Modal -->
	<div class="modal fade" id="resign" data-backdrop="static"
		tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">회원탈퇴</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>이메일</label> <input type="email" class="form-control"
							id="resign-email" readonly value="${info.getEmail()}">
					</div>
					<div class="form-group">
						<label>비밀번호</label> <input type="password" class="form-control"
							id="resign-password" >
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="resign-button" data-dismiss="modal">탈퇴</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 회원정보 Modal -->
	<div class="modal fade" id="update" data-backdrop="static" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">회원수정</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<label for="update-input-name">이름</label>
					<div class="input-group">
						<input type="text" class="form-control"
							   readonly value="${info.getName()}"
								id="update-input-name">
					</div>
					<br>

					<label for="update-input-email">이메일</label>
					<div class="input-group">
						<input type="text" class="form-control"
							readonly value="${info.getEmail()}"
							id="update-input-email">
					</div>
					<br>

					<label for="update-input-birthdate">생년월일</label>
					<div class="input-group">
						<input type="text" class="form-control"
							readonly value="${info.getBirthDate()}"
						   	id="update-input-birthdate">
					</div>
					<br>

					<label for="update-input-contact">연락처</label>
					<div class="input-group">
						<input type="text" class="form-control" readonly="readonly"
							id="update-input-contact" value="${info.getContact()}">
						<button class="btn btn-outline-secondary" type="button" id="contact-update-btn">수정
						</button>
					</div>
					<br>

					<button id="password-update-btn">비밀번호 수정 ▶</button>
					<div class="password-update-wrapper">
						<label for="update-input-password">비밀번호</label>
						<div class="input-group">
							<input type="password" class="form-control" id="update-input-password">
						</div>
						<br>
						<label for="update-input-confirm-password">비밀번호 확인</label>
						<div class="input-group">
							<input type="password" class="form-control" id="update-input-confirm-password">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="update-button"
						data-dismiss="modal">제출</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>