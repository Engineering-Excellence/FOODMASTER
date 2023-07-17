<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/order.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/order.js" defer></script>

<main>
    <div class="order-container">
        <c:forEach begin="1" end="4">
            <!-- 주문 화면 박스 -->
            <div class="order-box">
                <!-- 주문 화면 박스 앞에 내용-->
                <div class="box-front">
                    <div class="front-number">1</div>
                </div>

                <!-- 주문 화면 박스 메인 내용 -->
                <div class="box-main">
                    <div class="main-font">짜파게티 매운맛, 델몬트 오렌지 쥬스</div>
                    <div class="main-control-container">
                        <div class="font-pay">7,500원</div>
                        <button type="button" class="btn btn-outline-success">판매취소</button>
                        <button type="button" class="btn btn-outline-success">판매하기</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
