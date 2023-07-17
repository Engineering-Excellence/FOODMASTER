<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layouts/header.jsp" %>
<%@ include file="/WEB-INF/views/layouts/side.jsp" %>
<%@ include file="/WEB-INF/views/layouts/nav.jsp" %>

<c:set var="paths" value="${fn:split(path, '/')}"/>
<c:if test='${pageContext.request.getParameter("redirect") eq "true"}'>
    <c:redirect url="/home"/>
</c:if>
<c:if test="${info eq null}">
    <c:redirect url="/" />
</c:if>

<%-- fn 태그의 split은 놀랍게도 제일 앞의 빈 문자열이 없고 그 뒤부터 생겨서 길이가 2짜리로 생김에 주의 --%>
<c:if test="${paths[0] eq 'member'}">
    <c:if test="${paths[1] eq 'search'}">
        <%@ include file="/WEB-INF/views/member/member.jsp" %>
    </c:if>
    <c:if test="${paths[1] eq 'update'}">
        <%@ include file="/WEB-INF/views/member/memberUpdate.jsp" %>
    </c:if>
</c:if>
<c:if test="${paths[0] eq 'stock'}">
    <c:if test="${paths[1] eq 'list'}">
        <%@ include file="/WEB-INF/views/stock/stockList.jsp" %>
    </c:if>
    <c:if test="${paths[1] eq 'insert'}">
        <%@ include file="/WEB-INF/views/stock/stockInsert.jsp" %>
    </c:if>
    <c:if test="${paths[1] eq 'update'}">
        <%@ include file="/WEB-INF/views/stock/stockUpdate.jsp" %>
    </c:if>
</c:if>
<c:if test="${paths[0] eq 'product'}">
    <c:if test="${paths[1] eq 'order'}">
        <%@ include file="/WEB-INF/views/product/productOrder.jsp" %>
    </c:if>
    <c:if test="${paths[1] eq 'insert'}">
        <%@ include file="/WEB-INF/views/product/productInsert.jsp" %>
    </c:if>
    <c:if test="${paths[1] eq 'list'}">
        <%@ include file="/WEB-INF/views/product/productList.jsp" %>
    </c:if>
</c:if>

<c:if test="${paths[0] eq 'balance'}">
    <c:if test="${paths[1] eq 'list'}">
        <%@ include file="/WEB-INF/views/balance/balance.jsp" %>
    </c:if>
</c:if>

<c:if test="${paths[0] eq 'home'}">
    <%@ include file="/WEB-INF/views/main.jsp" %>
</c:if>

<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>
