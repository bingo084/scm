<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>采购单详情</title>

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
                    <td nowrap class="title1">采购单详情</td>
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
                    <td>采购单编号</td>
                    <td>${poMainVendor.poId}</td>
                    <td>创建时间</td>
                    <td>${poMainVendor.createTime}</td>
                    <td>供应商</td>
                    <td>${poMainVendor.vendorName}</td>
                    <td>创建用户</td>
                    <td>${poMainVendor.account}</td>
                    <td>附加费用</td>
                    <td>${poMainVendor.tipFee}</td>
                </tr>
                <tr align="justify">
                    <td>产品总价</td>
                    <td>${poMainVendor.productTotal}</td>
                    <td>采购单总价格</td>
                    <td>${poMainVendor.poTotal}</td>
                    <td>付款方式</td>
                    <c:if test="${poMainVendor.payType==1}">
                        <td>货到付款</td>
                    </c:if>
                    <c:if test="${poMainVendor.payType==2}">
                        <td>款到发货</td>
                    </c:if>
                    <c:if test="${poMainVendor.payType==3}">
                        <td>预付款到发货</td>
                    </c:if>
                    <td>最低预付款金额</td>
                    <td>${poMainVendor.prePayFee}</td>
                    <td>处理状态</td>
                    <c:if test="${poMainVendor.status==1}">
                        <td>新增</td>
                    </c:if>
                    <c:if test="${poMainVendor.status==2}">
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
                    <td nowrap class="title1">采购单明细</td>
                </tr>
            </table>
            <table width="100%" border="0" align="center" cellspacing="1" id="detailTable">
                <tr>
                    <td class="toolbar">采购单编号</td>
                    <td class="toolbar">产品编号</td>
                    <td class="toolbar">产品名称</td>
                    <td class="toolbar">数量单位</td>
                    <td class="toolbar">产品数量</td>
                    <td class="toolbar">采购单价</td>
                    <td class="toolbar">明细总价</td>
                </tr>
                <c:forEach items="${poItemProducts}" var="poItemProduct">
                    <tr align="center">
                        <td>${poItemProduct.poId}</td>
                        <td>${poItemProduct.productCode}</td>
                        <td>${poItemProduct.productName}</td>
                        <td>${poItemProduct.unitName}</td>
                        <td>${poItemProduct.num}</td>
                        <td>${poItemProduct.unitPrice}</td>
                        <td>${poItemProduct.itemPrice}</td>
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
            <c:if test="${poMainVendor.payType==1}">
                <input type="hidden" name="pay_price" value="${poMainVendor.poTotal}">
            </c:if>
            <c:if test="${poMainVendor.payType==2}">
                <input type="hidden" name="pay_price" value="${poMainVendor.poTotal}">
            </c:if>
            <c:if test="${poMainVendor.payType==3}">
                <c:if test="${poMainVendor.status==1}">
                    <input type="hidden" name="pay_price" value="${poMainVendor.prePayFee}">
                </c:if>
                <c:if test="${poMainVendor.status==2}">
                    <input type="hidden" name="pay_price" value="${poMainVendor.poTotal-poMainVendor.prePayFee}">
                </c:if>
            </c:if>
            <input type="hidden" name="orderCode" value="${poMainVendor.poId}">
            <c:if test="${poMainVendor.payType==1}">
                <input type="hidden" name="pay_type" value="2">
            </c:if>
            <c:if test="${poMainVendor.payType==2}">
                <input type="hidden" name="pay_type" value="2">
            </c:if>
            <c:if test="${poMainVendor.payType==3}">
                <c:if test="${poMainVendor.status==1}">
                    <input type="hidden" name="pay_type" value="4">
                </c:if>
                <c:if test="${poMainVendor.status==2}">
                    <input type="hidden" name="pay_type" value="2">
                </c:if>
            </c:if>
            <div class="pageDiv">
                <input type="submit" value="付款登记" onclick="return confirm('确定要进行付款登记吗？');">
                <input type="button" value="返回" onclick="javascript:history.go(-1)">
            </div>
        </form>
    </body>
</html>
