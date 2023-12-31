<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/validation.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/memberUpdate.js" defer></script>

<main>
    <h1 class="mt-4">회원 정보 수정</h1>
    <form action="/member/update" method="post" id="updateMember">
        <table class="table mt-4">
            <tr>
                <th>
                    <label for="memberID">ID</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="memberID" name="memberID"
                                   value="${memberDTO.getMemberID()}"
                                   readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="name">이름</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="name" name="name" value="${memberDTO.getName()}"
                                   readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="email">계정(이메일)</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="email" name="email"
                                   value="${memberDTO.getEmail()}" readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="contact">연락처</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="contact" name="contact"
                                   value="${memberDTO.getContact()}">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="birthDate">생일</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="birthDate" name="birthDate"
                                   value="${memberDTO.getBirthDate()}"
                                   readonly>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="regDate">등록일</label></th>
                <td>
                    <div class="input-container">
                        <div class="input-group">
                            <input type="text" class="form-control" id="regDate" name="regDate"
                                   value="${memberDTO.getRegDate()}" readonly>
                        </div>
                    </div>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" class="btn btn-primary" value="수정">
                    <input type="button" class="btn btn-danger" value="취소" onclick="history.back()">
                </td>
            </tr>
        </table>
    </form>
</main>
