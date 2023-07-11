<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">

<nav>nav</nav>

<main>
    <div class="main-container">
        <c:forEach var="number" begin="1" end="60">
            <div class="main-box">
                <b>${ number }</b>
                <p class="box-font">
                    김혁진<br>05:00남음<br>오버워치
                </p>
            </div>
        </c:forEach>
    </div>
</main>
