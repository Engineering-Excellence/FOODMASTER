<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<nav>
    <div class="nav-container">
        <div class="nav-wrapper">
            <div class="nav-menu">
                <a href="/member/search?page=1">
                    회원정보
                </a>
            </div>
        </div>
        <div class="nav-wrapper">
            <div class="nav-menu">탈퇴회원</div>
        </div>
    </div>
</nav>

<main>
    <h1 class="mt-4">재고 정보 수정</h1>
    <form action="/stock/update" method="post">
        <table class="table mt-4">
            <tr>
                <th>
                    <label for="">번호</label></th>
                <td>
                    <input type="text" id="" name="" value="" readonly>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="">자재구분</label></th>
                <td>
                    <input type="text" id="" name="" value="" readonly>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="">품목명</label></th>
                <td>
                    <input type="text" id="" name="" value="">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="">적정재고량 (고정)</label></th>
                <td>
                    <input type="text" id="" name="" value="">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="">현재고량 (변동)</label></th>
                <td>
                    <input type="text" id="" name="" value=""
                           readonly>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="">차이 (현재고량 - 적정재고량)</label></th>
                <td>
                    <input type="text" id="" name="" value="" readonly>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="">조치사항</label></th>
                <td>
                    <input type="text" id="" name="" value="">
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
