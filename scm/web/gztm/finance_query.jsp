<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>收支查询</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <script language="javascript" src="../script/common.js"></script>
        <script type="text/javascript" src="../script/productDiv.js"></script>
        <script type="text/javascript" src="../script/My97DatePicker/WdatePicker.js"></script>

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
                    <td nowrap class="title1">收支查询</td>
                </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="30px" nowrap class="toolbar">&nbsp;</td>
                    <td nowrap class="toolbar">&nbsp;</td>
                    <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)">&nbsp;
                    </td>
                    <td width="20px" nowrap class="toolbar">&nbsp;</td>
                    <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)">&nbsp;
                    </td>
                    <td width="20px" nowrap class="toolbar">&nbsp;</td>
                    <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)">&nbsp;
                    </td>
                    <td width="20px" nowrap class="toolbar">&nbsp;</td>
                    <td width="60px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)">&nbsp;
                    </td>
                    <td width="20px" nowrap class="toolbar">&nbsp;</td>
                </tr>
            </table>
            <div class="query_div">
                <form action="${pageContext.request.contextPath}/finance/getPayRecords">
                    创建日期：
                    <input class="Wdate" type="text" id="startDate"
                           onFocus="WdatePicker({isShowClear:true,readOnly:true})" name="startDate"
                           value="${startDate}"/>
                    -
                    <input class="Wdate" type="text" id="endDate"
                           onFocus="WdatePicker({isShowClear:true,readOnly:true})"
                           name="endDate" value="${endDate}"/>
                    收支类型：
                    <c:if test="${incomeType=='receive'}">
                        <select name="incomeType" required="required">
                            <option value="">请选择</option>
                            <option value="receive" selected>收入</option>
                            <option value="pay">支出</option>
                        </select>
                    </c:if>
                    <c:if test="${incomeType=='pay'}">
                        <select name="incomeType" required="required">
                            <option value="">请选择</option>
                            <option value="receive">收入</option>
                            <option value="pay" selected>支出</option>
                        </select>
                    </c:if>
                    <c:if test="${empty incomeType}">
                        <select name="incomeType" required="required">
                            <option value="" selected>请选择</option>
                            <option value="receive">收入</option>
                            <option value="pay">支出</option>
                        </select>
                    </c:if>
                    单据付款方式：
                    <c:if test="${payType == 1}">
                        <select id="payType" name="payType">
                            <option value="">请选择</option>
                            <option value="1" selected>货到付款</option>
                            <option value="2">款到发货</option>
                            <option value="3">预付款到发货</option>
                        </select>
                    </c:if>
                    <c:if test="${payType == 2}">
                        <select id="payType" name="payType">
                            <option value="">请选择</option>
                            <option value="1">货到付款</option>
                            <option value="2" selected>款到发货</option>
                            <option value="3">预付款到发货</option>
                        </select>
                    </c:if>
                    <c:if test="${payType == 3}">
                        <select id="payType" name="payType">
                            <option value="">请选择</option>
                            <option value="1">货到付款</option>
                            <option value="2">款到发货</option>
                            <option value="3" selected>预付款到发货</option>
                        </select>
                    </c:if>
                    <c:if test="${empty payType}">
                        <select id="payType" name="payType">
                            <option value="" selected>请选择</option>
                            <option value="1">货到付款</option>
                            <option value="2">款到发货</option>
                            <option value="3">预付款到发货</option>
                        </select>
                    </c:if>
                    相关单据号：<input type="text" id="poId" name="orderCode" value="${orderCode}"/>
                    <input type="submit" value="查询"/>
                </form>
            </div>

            <div id="dataList">
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <c:if test="${incomeType == 'receive' || empty incomeType}">
                            <td class="title1">收款日期</td>
                            <td class="title1">相关单据号</td>
                            <td class="title1">收款金额</td>
                            <td class="title1">经手人</td>
                            <td class="title1">收款类型</td>
                        </c:if>
                        <c:if test="${incomeType == 'pay'}">
                            <td class="title1">付款日期</td>
                            <td class="title1">相关单据号</td>
                            <td class="title1">付款金额</td>
                            <td class="title1">经手人</td>
                            <td class="title1">付款类型</td>
                        </c:if>
                        <td class="title1">单据付款方式</td>
                    </tr>
                    <c:forEach items="${payRecordPayTypePage.dataList}" var="payRecordPayType">
                        <tr>
                            <td align="center">${payRecordPayType.payTime}</td>
                            <td align="center">${payRecordPayType.orderCode}</td>
                            <td align="center">${payRecordPayType.payPrice}</td>
                            <td align="center">${payRecordPayType.account}</td>
                            <c:if test="${payRecordPayType.payType==1}">
                                <td align="center">收款</td>
                            </c:if>
                            <c:if test="${payRecordPayType.payType==2}">
                                <td align="center">付款</td>
                            </c:if>
                            <c:if test="${payRecordPayType.payType==3}">
                                <td align="center">收预付款</td>
                            </c:if>
                            <c:if test="${payRecordPayType.payType==4}">
                                <td align="center">付预付款</td>
                            </c:if>
                            <c:if test="${payRecordPayType.mainPayType==1}">
                                <td align="center">货到付款</td>
                            </c:if>
                            <c:if test="${payRecordPayType.mainPayType==2}">
                                <td align="center">款到发货</td>
                            </c:if>
                            <c:if test="${payRecordPayType.mainPayType==3}">
                                <td align="center">预付款到发货</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>

                <div class="pageDiv">
                    当前第
                    <select id="payRecordPayTypeCurrentPage" onchange="payRecordPayTypePageJump()">
                        <c:forEach begin="1" end="${payRecordPayTypePage.totalPage}" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    页
                    <input type="button" value="首页" onclick="firstProduct()"/>
                    <c:if test="${payRecordPayTypePage.currentPage==1 || empty payRecordPayTypePage}">
                        <input id="pre_btn" type="button" value="上一页" disabled onclick="preProduct()"/>
                    </c:if>
                    <c:if test="${payRecordPayTypePage.currentPage!=1 && not empty payRecordPayTypePage}">
                        <input id="pre_btn" type="button" value="上一页" onclick="preProduct()"/>
                    </c:if>
                    <c:if test="${payRecordPayTypePage.currentPage==payRecordPayTypePage.totalPage || payRecordPayTypePage.totalPage==0}">
                        <input id="next_btn" type="button" value="下一页" disabled onclick="nextProduct()"/>
                    </c:if>
                    <c:if test="${payRecordPayTypePage.currentPage!=payRecordPayTypePage.totalPage && payRecordPayTypePage.totalPage!=0}">
                        <input id="next_btn" type="button" value="下一页" onclick="nextProduct()"/>
                    </c:if>
                    <input type="button" value="末页" onclick="lastProduct()"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#payRecordPayTypeCurrentPage option[value=${payRecordPayTypePage.currentPage}]").attr("selected", true);
                        })

                        function firstProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getPayRecords?currentPage=1&startDate=${payRecordQuery.startDate}&endDate=${payRecordQuery.endDate}&incomeType=${payRecordQuery.incomeType}&payType=${payRecordQuery.payType}&orderCode=${payRecordQuery.orderCode}';
                        }

                        function preProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getPayRecords?currentPage=${payRecordPayTypePage.currentPage-1}&startDate=${payRecordQuery.startDate}&endDate=${payRecordQuery.endDate}&incomeType=${payRecordQuery.incomeType}&payType=${payRecordQuery.payType}&orderCode=${payRecordQuery.orderCode}';
                        }

                        function nextProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getPayRecords?currentPage=${payRecordPayTypePage.currentPage+1}&startDate=${payRecordQuery.startDate}&endDate=${payRecordQuery.endDate}&incomeType=${payRecordQuery.incomeType}&payType=${payRecordQuery.payType}&orderCode=${payRecordQuery.orderCode}';
                        }

                        function lastProduct() {
                            location.href = '${pageContext.request.contextPath}/finance/getPayRecords?currentPage=${payRecordPayTypePage.totalPage}&startDate=${payRecordQuery.startDate}&endDate=${payRecordQuery.endDate}&incomeType=${payRecordQuery.incomeType}&payType=${payRecordQuery.payType}&orderCode=${payRecordQuery.orderCode}';
                        }

                        function productPageJump() {
                            location.href = '${pageContext.request.contextPath}/finance/getPayRecords?currentPage=' + $("#payRecordPayTypeCurrentPage option:selected").val() + '&startDate=${payRecordQuery.startDate}&endDate=${payRecordQuery.endDate}&incomeType=${payRecordQuery.incomeType}&payType=${payRecordQuery.payType}&orderCode=${payRecordQuery.orderCode}';
                        }
                    </script>
                    共${payRecordPayTypePage.totalPage}页 ${payRecordPayTypePage.totalRecord}条记录
                </div>
            </div>
        </div>
    </body>
</html>
