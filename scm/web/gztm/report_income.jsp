<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>月度收支登记表</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <script language="javascript" src="../script/common.js"></script>
        <script type="text/javascript" src="../script/productDiv.js"></script>
        <script type="text/javascript" src="../script/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="../script/jquery-3.4.1.min.js"></script>
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
                    <td nowrap class="title1">月度收支登记表</td>
                </tr>
            </table>
            <div class="query_div">
                <form action="${pageContext.request.contextPath}/report/getIncomeReport">
                    <select id="year" name="year" required="required">
                        <option value="">请选择</option>
                        <c:forEach begin="1980" end="2099" step="1" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    年&nbsp;
                    <select id="month" name="month" required="required">
                        <option value="">请选择</option>
                        <c:forEach begin="1" end="9" step="1" var="i">
                            <option value="0${i}">${i}</option>
                        </c:forEach>
                        <c:forEach begin="10" end="12" step="1" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    月&nbsp;
                    <input type="submit" value="查询"/>
                </form>
            </div>

            <div id="dataList">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
                    <tr>
                        <td nowrap class="title1">主信息</td>
                    </tr>
                </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbar">
                </table>
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <td class="title1">收款总金额</td>
                        <td class="title1">付款总金额</td>
                        <td class="title1">收款交易次数</td>
                        <td class="title1">付款交易次数</td>
                    </tr>
                    <tr>
                        <td align="center">${incomeMain.receiveSum}</td>
                        <td align="center">${incomeMain.paySum}</td>
                        <td align="center">${incomeMain.receiveCount}</td>
                        <td align="center">${incomeMain.payCount}</td>
                    </tr>
                </table>
            </div>
            <br>
            <div>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
                    <c:if test="${itemType=='receive' || empty itemType}">
                        <tr>
                            <td nowrap class="title1">明细信息-收款</td>
                        </tr>
                    </c:if>
                    <c:if test="${itemType=='pay'}">
                        <tr>
                            <td nowrap class="title1">明细信息-付款</td>
                        </tr>
                    </c:if>
                </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td nowrap class="toolbar">&nbsp;</td>
                        <td width="20px" nowrap="" class="toolbar">|</td>
                        <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"
                            onclick="get(this)">收款明细
                        </td>
                        <td width="20px" nowrap="" class="toolbar">|</td>
                        <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"
                            onclick="get(this)">付款明细
                        </td>
                        <td width="20px" nowrap="" class="toolbar">|</td>
                    </tr>
                </table>
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <c:if test="${itemType=='receive' || empty itemType}">
                        <tr>
                            <td class="title1">销售单号</td>
                            <td class="title1">销售单日期</td>
                            <td class="title1">收款日期</td>
                            <td class="title1">收款金额</td>
                            <td class="title1">经手人</td>
                            <td class="title1">处理状态</td>
                        </tr>
                    </c:if>
                    <c:if test="${itemType=='pay'}">
                        <tr>
                            <td class="title1">采购单号</td>
                            <td class="title1">采购日期</td>
                            <td class="title1">付款日期</td>
                            <td class="title1">付款金额</td>
                            <td class="title1">经手人</td>
                            <td class="title1">处理状态</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${incomeItemPage.dataList}" var="incomeItem">
                        <tr>
                            <td align="center">${incomeItem.id}</td>
                            <td align="center">${incomeItem.createTime}</td>
                            <td align="center">${incomeItem.payTime}</td>
                            <td align="center">${incomeItem.payPrice}</td>
                            <td align="center">${incomeItem.account}</td>
                            <c:if test="${incomeItem.status==1}">
                                <td align="center">新增</td>
                            </c:if>
                            <c:if test="${incomeItem.status==2}">
                                <td align="center">已收货</td>
                            </c:if>
                            <c:if test="${incomeItem.status==3}">
                                <td align="center">已付款</td>
                            </c:if>
                            <c:if test="${incomeItem.status==4}">
                                <td align="center">已了结</td>
                            </c:if>
                            <c:if test="${incomeItem.status==5}">
                                <td align="center">已预付</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="pageDiv">
                当前第
                <select id="incomeItemCurrentPage" onchange="incomeItemPageJump()">
                    <c:forEach begin="1" end="${incomeItemPage.totalPage}" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
                页
                <input type="button" value="首页" onclick="firstProduct()"/>
                <c:if test="${incomeItemPage.currentPage==1 || empty incomeItemPage}">
                    <input id="pre_btn" type="button" value="上一页" disabled onclick="preProduct()"/>
                </c:if>
                <c:if test="${incomeItemPage.currentPage!=1 && not empty incomeItemPage}">
                    <input id="pre_btn" type="button" value="上一页" onclick="preProduct()"/>
                </c:if>
                <c:if test="${incomeItemPage.currentPage==incomeItemPage.totalPage || incomeItemPage.totalPage==0}">
                    <input id="next_btn" type="button" value="下一页" disabled onclick="nextProduct()"/>
                </c:if>
                <c:if test="${incomeItemPage.currentPage!=incomeItemPage.totalPage && incomeItemPage.totalPage!=0}">
                    <input id="next_btn" type="button" value="下一页" onclick="nextProduct()"/>
                </c:if>
                <input type="button" value="末页" onclick="lastProduct()"/>
                <script type="text/javascript">
                    $(function () {
                        $("#year option[value=${year}]").attr("selected", true);
                        $("#month option[value=${month}]").attr("selected", true);
                        $("#incomeItemCurrentPage option[value=${incomeItemPage.currentPage}]").attr("selected", true);
                    })

                    function firstProduct() {
                        location.href = '${pageContext.request.contextPath}/report/getIncomeReport?currentPage=1&year=${year}&month=${month}&itemType=${itemType}';
                    }

                    function preProduct() {
                        location.href = '${pageContext.request.contextPath}/report/getIncomeReport?currentPage=${incomeItemPage.currentPage-1}&year=${year}&month=${month}&itemType=${itemType}';
                    }

                    function nextProduct() {
                        location.href = '${pageContext.request.contextPath}/report/getIncomeReport?currentPage=${incomeItemPage.currentPage+1}&year=${year}&month=${month}&itemType=${itemType}';
                    }

                    function lastProduct() {
                        location.href = '${pageContext.request.contextPath}/report/getIncomeReport?currentPage=${incomeItemPage.totalPage}&year=${year}&month=${month}&itemType=${itemType}';
                    }

                    function incomeItemPageJump() {
                        location.href = '${pageContext.request.contextPath}/report/getIncomeReport?currentPage=' + $("#incomeItemCurrentPage option:selected").val() + '&year=${year}&month=${month}&itemType=${itemType}';
                    }

                    function get(obj) {
                        if ($(obj).html().indexOf("收款明细") === 0) {
                            location.href = "${pageContext.request.contextPath}/report/getIncomeReport?&year=${year}&month=${month}&itemType=receive";
                        } else if ($(obj).html().indexOf("付款明细") === 0) {
                            location.href = "${pageContext.request.contextPath}/report/getIncomeReport?&year=${year}&month=${month}&itemType=pay";
                        }
                    }
                </script>
                共${incomeItemPage.totalPage}页 ${incomeItemPage.totalRecord}条记录
            </div>
        </div>
    </body>
</html>
