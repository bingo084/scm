<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>收款登记</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <script language="javascript" src="../script/common.js"></script>
        <script type="text/javascript" src="../script/productDiv.js"></script>
        <script type="text/javascript" src="../script/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="../script/jquery-3.4.1.min.js"></script>
        <script type="text/javascript">
            $(function () {
                if (${not empty msg}) {
                    alert("${msg}");
                }
            });
        </script>
        <style type="text/css">
            div.product {
                position: absolute;
                top: 2px;
                left: 2px;
                width: 100%;
                height: 98%;
                background-color: #fffffe;
            }
        </style>
    </head>
    <body>
        <div id="m">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
                <tr>
                    <c:if test="${payType==1}">
                        <td nowrap class="title1">收款登记-货到付款</td>
                    </c:if>
                    <c:if test="${payType==2}">
                        <td nowrap class="title1">收款登记-款到发货</td>
                    </c:if>
                    <c:if test="${payType==3}">
                        <td nowrap class="title1">收款登记-预付款到发货</td>
                    </c:if>
                    <c:if test="${empty payType}">
                        <td nowrap class="title1">收款登记</td>
                    </c:if>
                </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td nowrap="" class="toolbar">&nbsp;</td>
                    <td width="60px" nowrap="" class="toolbar" onmouseover="OMO(event)" onmouseout="OMOU(event)">
                        &nbsp;
                    </td>
                    <td width="20px" nowrap="" class="toolbar">|</td>
                    <td width="60px" nowrap="" class="P" onmouseover="OMO(event)" onmouseout="OMOU(event)"
                        onclick="get(this)">款到发货
                    </td>
                    <td width="20px" nowrap="" class="toolbar">|</td>
                    <td width="80px" nowrap="" class="P" onmouseover="OMO(event)" onmouseout="OMOU(event)"
                        onclick="get(this)">预付款到发货
                    </td>
                    <td width="20px" nowrap="" class="toolbar">|</td>
                    <td width="60px" nowrap="" class="toolbar" onmouseover="OMO(event)" onmouseout="OMOU(event)"
                        onclick="get(this)">货到付款
                    </td>
                    <td width="20px" nowrap="" class="toolbar">|</td>
                </tr>
            </table>
            <div id="dataList">
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <td class="title1">销售单编号</td>
                        <td class="title1">创建时间</td>
                        <td class="title1">客户</td>
                        <td class="title1">创建用户</td>
                        <td class="title1">附加费用</td>
                        <td class="title1">产品总价</td>
                        <td class="title1">销售单总价格</td>
                        <td class="title1">付款方式</td>
                        <td class="title1">最低预付款金额</td>
                        <td class="title1">处理状态</td>
                    </tr>
                    <c:forEach items="${soMainCustomerPage.dataList}" var="soMainCustomer">
                        <tr>
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/finance/getSoItem?soId=${soMainCustomer.soId}">${soMainCustomer.soId}</a>
                            </td>
                            <td align="center">${soMainCustomer.createTime}</td>
                            <td align="center">${soMainCustomer.customerName}</td>
                            <td align="center">${soMainCustomer.account}</td>
                            <td align="center">${soMainCustomer.tipFee}</td>
                            <td align="center">${soMainCustomer.productTotal}</td>
                            <td align="center">${soMainCustomer.poTotal}</td>
                            <c:if test="${soMainCustomer.payType==1}">
                                <td align="center">货到付款</td>
                            </c:if>
                            <c:if test="${soMainCustomer.payType==2}">
                                <td align="center">款到发货</td>
                            </c:if>
                            <c:if test="${soMainCustomer.payType==3}">
                                <td align="center">预付款到发货</td>
                            </c:if>
                            <td align="center">${soMainCustomer.prePayFee}</td>
                            <c:if test="${soMainCustomer.status==1}">
                                <td align="center">新增</td>
                            </c:if>
                            <c:if test="${soMainCustomer.status==2}">
                                <td align="center">已收货</td>
                            </c:if>
                        </tr>
                        <tr style="display:none">
                            <td colspan="10" name="detail"></td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="pageDiv">
                    当前第
                    <select id="soMainCustomerCurrentPage" onchange="soMainCustomerPageJump()">
                        <c:forEach begin="1" end="${soMainCustomerPage.totalPage}" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    页
                    <input type="button" value="首页" onclick="firstProduct()"/>
                    <c:if test="${soMainCustomerPage.currentPage==1}">
                        <input id="pre_btn" type="button" value="上一页" disabled onclick="preProduct()"/>
                    </c:if>
                    <c:if test="${soMainCustomerPage.currentPage!=1}">
                        <input id="pre_btn" type="button" value="上一页" onclick="preProduct()"/>
                    </c:if>
                    <c:if test="${soMainCustomerPage.currentPage==soMainCustomerPage.totalPage || soMainCustomerPage.totalPage==0}">
                        <input id="next_btn" type="button" value="下一页" disabled onclick="nextProduct()"/>
                    </c:if>
                    <c:if test="${soMainCustomerPage.currentPage!=soMainCustomerPage.totalPage && soMainCustomerPage.totalPage!=0}">
                        <input id="next_btn" type="button" value="下一页" onclick="nextProduct()"/>
                    </c:if>
                    <input type="button" value="末页" onclick="lastProduct()"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#soMainCustomerCurrentPage option[value=${soMainCustomerPage.currentPage}]").attr("selected", true);
                        })

                        function firstProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?currentSoMainPage=1&payType=${payType}';
                        }

                        function preProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?currentSoMainPage=${soMainCustomerPage.currentPage-1}&payType=${payType}';
                        }

                        function nextProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?currentSoMainPage=${soMainCustomerPage.currentPage+1}&payType=${payType}';
                        }

                        function lastProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?currentSoMainPage=${soMainCustomerPage.totalPage}&payType=${payType}';
                        }

                        function soMainCustomerPageJump() {
                            location.href = '${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?currentSoMainPage=' + $("#soMainCustomerCurrentPage option:selected").val() + '&payType=${payType}';
                        }

                        function get(obj) {
                            if ($(obj).html().indexOf("款到发货") === 0) {
                                location.href = "${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?payType=2";
                            } else if ($(obj).html().indexOf("预付款到发货") === 0) {
                                location.href = "${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?payType=3";
                            } else if ($(obj).html().indexOf("货到付款") === 0) {
                                location.href = "${pageContext.request.contextPath}/finance/getAllSoMainsByPayType?payType=1";
                            }
                        }
                    </script>
                    共${soMainCustomerPage.totalPage}页 ${soMainCustomerPage.totalRecord}条记录
                </div>
            </div>
        </div>
    </body>
</html>
