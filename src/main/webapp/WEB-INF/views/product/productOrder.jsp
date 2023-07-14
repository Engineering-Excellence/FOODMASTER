<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/order.css" rel="stylesheet" type="text/css">

<main>
    <c:forEach begin="1" end="30">
        <!-- 주문 화면 박스 -->
        <div class="order-box">
            <!-- 주문 화면 박스 앞에 내용-->
            <div class="box-front">
                <font class="front-number">1</font><br>
                <br> <font class="front-font">김혁진</font>
            </div>

            <!-- 주문 화면 박스 메인 내용 -->
            <div class="box-main">
                <font class="main-font">짜파게티 매운맛, 델몬트 오렌지 쥬스</font><br>
                <font class="font-demand">맛있게 만들어 주세요~</font>
                <hr>
                <font class="font-pay">7,500 (카드)</font>
                <!-- 판매버튼 -->
                <button type="button" class="btn btn-outline-success">판매취소</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-outline-success">판매하기</button>
            </div>
        </div>
    </c:forEach>
</main>
