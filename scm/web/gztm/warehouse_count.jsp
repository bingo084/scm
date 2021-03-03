<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>库存盘点</title>

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
                    <td nowrap class="title1">仓储管理 -- 库存盘点</td>
                </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbar">
            </table>
            <div id="dataList">
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <td class="title1">产品编号</td>
                        <td class="title1">产品名称</td>
                        <td class="title1">当前库存</td>
                        <td class="title1">采购在途数</td>
                        <td class="title1">预销售数</td>
                        <td class="title1">操作</td>
                    </tr>
                    <c:forEach items="${productPage.dataList}" var="product">
                        <tr>
                            <td class="productCode" align="center">${product.productCode}</td>
                            <td class="name" align="center">${product.name}</td>
                            <td class="num" align="center">${product.num}</td>
                            <td class="poNum" align="center">${product.poNum}</td>
                            <td class="soNum" align="center">${product.soNum}</td>
                            <td align="center">
                                <a href="javascript:void(0);" onclick="update(this)">更新</a>
                                <script type="text/javascript">
                                    function update(obj) {
                                        $("#update_div").show();
                                        $("#productCode").val($(obj).parent().parent().children(".productCode").html());
                                        $("#name").val($(obj).parent().parent().children(".name").html());
                                        $("#originNum").val($(obj).parent().parent().children(".num").html());
                                    }
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                </table>


                <div class="pageDiv">
                    当前第
                    <select id="productCurrentPage" onchange="productPageJump()">
                        <c:forEach begin="1" end="${productPage.totalPage}" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    页
                    <input type="button" value="首页" onclick="firstProduct()"/>
                    <c:if test="${productPage.currentPage==1}">
                        <input id="pre_btn" type="button" value="上一页" disabled onclick="preProduct()"/>
                    </c:if>
                    <c:if test="${productPage.currentPage!=1}">
                        <input id="pre_btn" type="button" value="上一页" onclick="preProduct()"/>
                    </c:if>
                    <c:if test="${productPage.currentPage==productPage.totalPage || productPage.totalPage==0}">
                        <input id="next_btn" type="button" value="下一页" disabled onclick="nextProduct()"/>
                    </c:if>
                    <c:if test="${productPage.currentPage!=productPage.totalPage && productPage.totalPage!=0}">
                        <input id="next_btn" type="button" value="下一页" onclick="nextProduct()"/>
                    </c:if>
                    <input type="button" value="末页" onclick="lastProduct()"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#productCurrentPage option[value=${productPage.currentPage}]").attr("selected", true);
                        })

                        function firstProduct() {
                            location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentProductPage=1&currentStockPage=${checkStockPage.currentPage}';
                        }

                        function preProduct() {
                            location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentProductPage=${productPage.currentPage-1}&currentStockPage=${checkStockPage.currentPage}';
                        }

                        function nextProduct() {
                            location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentProductPage=${productPage.currentPage+1}&currentStockPage=${checkStockPage.currentPage}';
                        }

                        function lastProduct() {
                            location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentProductPage=${productPage.totalPage}&currentStockPage=${checkStockPage.currentPage}';
                        }

                        function productPageJump() {
                            location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentProductPage=' + $("#productCurrentPage option:selected").val() + '&currentStockPage=${checkStockPage.currentPage}';
                        }
                    </script>
                    共${productPage.totalPage}页 ${productPage.totalRecord}条记录
                </div>


            </div>
        </div>


        <div id="m">
            <table width="100%" border="0" align="center" cellspacing="1" id="detailTable">
                <tr>
                    <td class="title2" colspan="8">盘点记录</td>
                </tr>
                <tr>
                    <td class="toolbar" colspan="8"></td>
                </tr>
                <tr>
                    <td class="title1">序列号</td>
                    <td class="title1">产品编号</td>
                    <td class="title1">原始数量</td>
                    <td class="title1">实际数量</td>
                    <td class="title1">盘点时间</td>
                    <td class="title1">经手人</td>
                    <td class="title1">损益原因</td>
                    <td class="title1">损益类型</td>
                </tr>
                <c:forEach items="${checkStockPage.dataList}" var="stock">
                    <tr>
                        <td align="center">${stock.stockId}</td>
                        <td align="center">${stock.productCode}</td>
                        <td align="center">${stock.originNum}</td>
                        <td align="center">${stock.realNum}</td>
                        <td align="center">${stock.stockTime}</td>
                        <td align="center">${stock.createUser}</td>
                        <td align="center">${stock.description}</td>
                        <td align="center">${stock.type}</td>
                    </tr>
                </c:forEach>
            </table>
            <table width="100%" border="0" align="center" cellspacing="1">
                <tr>
                    <td class="title2"></td>
                </tr>
            </table>
            <div class="pageDiv">
                当前第
                <select id="checkStockCurrentPage" onchange="checkStockPageJump()">
                    <c:forEach begin="1" end="${checkStockPage.totalPage}" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
                页
                <input type="button" value="首页" onclick="firstStock()"/>
                <c:if test="${checkStockPage.currentPage==1}">
                    <input id="pre_btn" type="button" value="上一页" disabled onclick="preStock()"/>
                </c:if>
                <c:if test="${checkStockPage.currentPage!=1}">
                    <input id="pre_btn" type="button" value="上一页" onclick="preStock()"/>
                </c:if>
                <c:if test="${checkStockPage.currentPage==checkStockPage.totalPage || checkStockPage.totalPage==0}">
                    <input id="next_btn" type="button" value="下一页" disabled onclick="nextStock()"/>
                </c:if>
                <c:if test="${checkStockPage.currentPage!=checkStockPage.totalPage && checkStockPage.totalPage!=0}">
                    <input id="next_btn" type="button" value="下一页" onclick="nextStock()"/>
                </c:if>
                <input type="button" value="末页" onclick="lastStock()"/>
                <script type="text/javascript">
                    $(function () {
                        $("#checkStockCurrentPage option[value=${checkStockPage.currentPage}]").attr("selected", true);
                    })

                    function firstStock() {
                        location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentStockPage=1&currentProductPage=${productPage.currentPage}';
                    }

                    function preStock() {
                        location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentStockPage=${checkStockPage.currentPage-1}&currentProductPage=${productPage.currentPage}';
                    }

                    function nextStock() {
                        location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentStockPage=${checkStockPage.currentPage+1}&currentProductPage=${productPage.currentPage}';
                    }

                    function lastStock() {
                        location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentStockPage=${checkStockPage.totalPage}&currentProductPage=${productPage.currentPage}';
                    }

                    function checkStockPageJump() {
                        location.href = '${pageContext.request.contextPath}/warehouse/getAll?currentStockPage=' + $("#checkStockCurrentPage option:selected").val() + '&currentProductPage=${productPage.currentPage}';
                    }
                </script>
                共${checkStockPage.totalPage}页 ${checkStockPage.totalRecord}条记录
            </div>
            <div id="update_div" style="display: none">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="toolbar">
                    <tr>
                        <td class="title2">库存更新</td>
                    </tr>
                </table>
                <form action="${pageContext.request.contextPath}/warehouse/update" method="get"
                      onsubmit="return confirm('您确定要更新库存吗？');">
                    <table id="headTable" width="100%" border="0" align="center" class="a1">
                        <tr align="justify">
                            <td>产品编号</td>
                            <td><input type="text" name="productCode" id="productCode" size="15" readonly/></td>
                            <td>产品名称</td>
                            <td><input name="name" id="name" type="text" size="15" readonly></td>
                            <td>当前库存</td>
                            <td><input type="text" name="originNum" size="15" id="originNum" readonly/></td>
                            <td>变化数量</td>
                            <td><input type="text" name="realNum" size="15" id="productTotal" required/></td>
                            <td>变化类型</td>
                            <td>
                                <select id="payType" name="type" onchange="chgPayType(this)">
                                    <option value="损耗">损耗</option>
                                    <option value="盘余">盘余</option>
                                </select>
                            </td>
                            <td>损益原因</td>
                            <td><input type="text" id="remark" name="reason" size="100"/></td>
                        </tr>
                    </table>
                    <div align="center" class="wrong" id="wrong" style="padding:10px;"></div>
                    <div align="center">
                        <input type="submit" id="bc" value="保存" onclick="savePomain(true)"/>
                        <input type="button" id="gb" value="取消" onclick="cancel()"/>
                        <script type="text/javascript">
                            function cancel() {
                                $("#update_div").hide();
                            }
                        </script>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
