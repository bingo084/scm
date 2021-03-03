<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>销售单详情</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <script language="javascript" src="../script/common.js"></script>
        <script type="text/javascript" src="../script/productDiv.js"></script>
        <script type="text/javascript" src="../script/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="../script/jquery-3.4.1.min.js"></script>
    </head>

    <body>
        <div id="m">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
                <tr>
                    <td nowrap class="title1">销售单详情</td>
                </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="30px" nowrap class="toolbar">&nbsp;</td>
                    <td nowrap class="toolbar">&nbsp;</td>
                </tr>
            </table>

            <table id="headTable" width="100%" border="0" align="center" class="a1">
                <tr align="justify">
                    <td>销售单编号</td>
                    <td>${soMainCustomer.soId}</td>
                    <td>创建时间</td>
                    <td>${soMainCustomer.createTime}</td>
                    <td>客户</td>
                    <td>${soMainCustomer.customerName}</td>
                    <td>创建用户</td>
                    <td>${soMainCustomer.account}</td>
                    <td>附加费用</td>
                    <td>${soMainCustomer.tipFee}</td>
                </tr>
                <tr align="justify">
                    <td>产品总价</td>
                    <td>${soMainCustomer.productTotal}</td>
                    <td>销售单总价格</td>
                    <td>${soMainCustomer.poTotal}</td>
                    <td>付款方式</td>
                    <c:if test="${soMainCustomer.payType==1}">
                        <td>货到付款</td>
                    </c:if>
                    <c:if test="${soMainCustomer.payType==2}">
                        <td>款到发货</td>
                    </c:if>
                    <c:if test="${soMainCustomer.payType==3}">
                        <td>预付款到发货</td>
                    </c:if>
                    <td>最低预付款金额</td>
                    <td>${soMainCustomer.prePayFee}</td>
                    <td>处理状态</td>
                    <c:if test="${soMainCustomer.status==1}">
                        <td>新增</td>
                    </c:if>
                    <c:if test="${soMainCustomer.status==2}">
                        <td>已收货</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="title2"></td>
                </tr>
            </table>
            <br>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
                <tr>
                    <td nowrap class="title1">销售单明细</td>
                </tr>
            </table>
            <table width="100%" border="0" align="center" cellspacing="1" id="detailTable">
                <tr>
                    <td class="toolbar">销售单编号</td>
                    <td class="toolbar">产品编号</td>
                    <td class="toolbar">产品名称</td>
                    <td class="toolbar">数量单位</td>
                    <td class="toolbar">产品数量</td>
                    <td class="toolbar">销售单价</td>
                    <td class="toolbar">明细总价</td>
                </tr>
                <c:forEach items="${soItemProducts}" var="soItemProduct">
                    <tr align="center">
                        <td>${soItemProduct.soId}</td>
                        <td>${soItemProduct.productCode}</td>
                        <td>${soItemProduct.productName}</td>
                        <td>${soItemProduct.unitName}</td>
                        <td>${soItemProduct.num}</td>
                        <td>${soItemProduct.unitPrice}</td>
                        <td>${soItemProduct.itemPrice}</td>
                    </tr>
                </c:forEach>
            </table>
            <table width="100%" border="0" align="center" cellspacing="1">
                <tr>
                    <td class="title2"></td>
                </tr>
            </table>
        </div>
        <form action="${pageContext.request.contextPath}/finance/addPayRecord">
            <c:if test="${soMainCustomer.payType==1}">
                <input type="hidden" name="pay_price" value="${soMainCustomer.poTotal}">
            </c:if>
            <c:if test="${soMainCustomer.payType==2}">
                <input type="hidden" name="pay_price" value="${soMainCustomer.poTotal}">
            </c:if>
            <c:if test="${soMainCustomer.payType==3}">
                <c:if test="${soMainCustomer.status==1}">
                    <input type="hidden" name="pay_price" value="${soMainCustomer.prePayFee}">
                </c:if>
                <c:if test="${soMainCustomer.status==2}">
                    <input type="hidden" name="pay_price" value="${soMainCustomer.poTotal-soMainCustomer.prePayFee}">
                </c:if>
            </c:if>
            <input type="hidden" name="orderCode" value="${soMainCustomer.soId}">
            <c:if test="${soMainCustomer.payType==1}">
                <input type="hidden" name="pay_type" value="1">
            </c:if>
            <c:if test="${soMainCustomer.payType==2}">
                <input type="hidden" name="pay_type" value="1">
            </c:if>
            <c:if test="${soMainCustomer.payType==3}">
                <c:if test="${soMainCustomer.status==1}">
                    <input type="hidden" name="pay_type" value="3">
                </c:if>
                <c:if test="${soMainCustomer.status==2}">
                    <input type="hidden" name="pay_type" value="1">
                </c:if>
            </c:if>
            <div class="pageDiv">
                <input type="submit" value="收款登记" onclick="return confirm('确定要进行收款登记吗？');">
                <input type="button" value="返回" onclick="javascript:history.go(-1)">
            </div>
        </form>
    </body>
</html>
