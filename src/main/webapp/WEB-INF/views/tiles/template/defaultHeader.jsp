
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<div class="menuBorderBox">
    <div class="menuBox">
        <div class="b" style="font-size:20px;padding-left:5px;">N코인</div>
    </div>
    <div class="border tl"></div>
    <div class="border tr"></div>
    <div class="border bl"></div>
    <div class="border br"></div>
</div>--%>
<%--<div class="menuBorderBox" style="margin-top:5px;">
    <div class="menuBox"
        <script>
            treeMenu1 = new dTree("treeMenu1");
            treeMenu1.add(0, -1, "관리자");

            treeMenu1.add(100, 0, "지갑 관리");
            treeMenu1.add(101, 100, "지갑 목록", "<c:url value='/walletList.do' />");
            treeMenu1.add(102, 100, "송금하기", "<c:url value='/sendMoney.do' />");
            treeMenu1.add(103, 100, "송금 로그", "<c:url value='/tradeLog.do' />");
            treeMenu1.add(104, 100, "지갑 순위", "<c:url value='/walletList.do?mode=ranking' />");
            
            treeMenu1.add(1, 0, "블록 목록", "<c:url value='/ledgerList.do' />");

            treeMenu1.add(2, 0, "노드 목록", "<c:url value='/nodeList.do' />");
            treeMenu1.add(3, 0, "일반 설정", "<c:url value='/setting.do' />");
            document.write(treeMenu1);
        </script>
    </div>
    <div class="border tl"></div>
    <div class="border tr"></div>
    <div class="border bl"></div>
    <div class="border br"></div>
</div>--%>

<%--
<div>
    <ul>
        <li>
            <div class="main_menu_btn" onclick="location.href='/send'">
                보내기
            </div>
        </li>
        <li>
            <div class="main_menu_btn" onclick="location.href='/receive'">
                받기
            </div>
        </li>
        <li>
            <div class="main_menu_btn" onclick="location.href='/listtransactions'">
                거래내역
            </div>
        </li>
        <li>
            <div class="main_menu_btn" onclick="location.href='/member/modify'">
                정보수정
            </div>
        </li>
        <li>
            <div class="main_menu_btn" onclick="location.href='/security'">
                보안설정
            </div>
        </li>
        <li>
            <div class="main_menu_btn" onclick="location.href='/security/sPw'">
                2차 비밀번호
            </div>
        </li>
    </ul>
</div>
<div class="menuBorderBox" style="margin-top:5px;">
    <div class="menuBox">
        <strong>관리자 : 샘플 </strong>[<a href="<c:url value='/login.do' />">로그아웃</a>]<br><br>
    </div>
    <div class="border tl"></div>
    <div class="border tr"></div>
    <div class="border bl"></div>
    <div class="border br"></div>
</div>--%>
<style>
    #mask{
        position: fixed;
        top: 0px;
        right: 0px;
        bottom: 0px;
        left: 0px;
        z-index: 3;
        background-color: rgb(0, 0, 0);
        opacity: 0.5;
        display: none;
    }

    #sideNav{
        height: 100%;
        width: 0;
        position: fixed;
        z-index: 3;
        top: 0;
        right: 0;
        background: #f5f5f5;
        transition: 0.5s;
        overflow-y: auto;
        overflow-x: hidden;
        box-sizing: border-box;

    }

    #sideNav .navMenu{
        padding: 0 0 0 30px;
        font: 16px/54px NGBold;
        height: 54px;
        line-height: 54px;
        font-size: 17px;
        cursor: pointer;
        flex-shrink: 0;
        min-width: 250px;

    }
    #sideNav .navMenu:hover{
        color: #fff;
        opacity: 0.5;
        background: #ddd;

    }
</style>
<script>

    function openNav() {
        document.getElementById("sideNav").style.width = "250px";
        //document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
        document.getElementById("mask").style.display = "block";
    }

    function closeNav() {
        document.getElementById("sideNav").style.width = "0";
        //document.body.style.backgroundColor = "white";
        document.getElementById("mask").style.display = "none";
    }
</script>

<div id="mask" onclick="closeNav()">
</div>

<div id="sideNav"  class="primary_content_column">
    <div class="primary_content_row" style="align-items: center; background: #F08B15; min-width: 250px;
    justify-content: space-between; height: 56px;">
        <img src="../../../../resources/img/close_x.png" onclick="closeNav()"
             style="margin: 12px 0px 12px 12px; width: auto; height: 50%; cursor:pointer;" />
        <button class="button_dark" style="width: auto; display: inline; margin-right: 12px;
        margin-bottom: 0px;" onclick="location.href='/logout'">로그아웃</button>
    </div>
    <div style="color: #F08B15; padding: 10px 0 10px 30px; font-size: 24px;">Services</div>
    <nav class="primary_content_column">
        <a class="navMenu" onclick="location.href='/send'">
            <div class="primary_content_row" style="align-items: center">
                <img src="../../../../resources/img/icon_send.png" style=" margin-right: 27px;">
                <span>보내기</span>
            </div>
        </a>
        <a class="navMenu" onclick="location.href='/receive'">
            <div class="primary_content_row" style="align-items: center">
                <img src="../../../../resources/img/icon_receive.png" style=" margin-right: 27px;">
                <span>받기</span>
            </div>
        </a>
        <a class="navMenu" onclick="location.href='/listtransactions'">
            <div class="primary_content_row" style="align-items: center">
                <img src="../../../../resources/img/icon_listtransactions.png" style=" margin-right: 27px;">
                <span>거래내역</span>
            </div>
        </a>
        <a class="navMenu" onclick="location.href='/member/modify'">
            <div class="primary_content_row" style="align-items: center">
                <img src="../../../../resources/img/icon_modify.png" style=" margin-right: 27px;">
                <span>정보수정</span>
            </div>
        </a>
        <a class="navMenu" onclick="location.href='/security'">
            <div class="primary_content_row" style="align-items: center">
                <img src="../../../../resources/img/icon_security.png" style=" margin-right: 27px;">
                <span>보안설정</span>
            </div>
        </a>
        <a class="navMenu" onclick="location.href='/security/sPw'">
            <div class="primary_content_row" style="align-items: center">
                <img src="../../../../resources/img/icon_sPw.png" style=" margin-right: 27px;">
                <span>2차 비밀번호</span>
            </div>
        </a>
    </nav>
</div>

<div id="default_header">
    <span onclick="location.href='/'" style="cursor: pointer; font-weight: bold; font-size: 1.5em; color: white; ">NCoin</span>
    <img id="openNavBtn" src="../../../../resources/img/menu.png" style="cursor: pointer" onclick="openNav()"/>

</div>
