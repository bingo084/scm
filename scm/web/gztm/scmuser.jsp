<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>用户管理</title>

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

            function check(obj) {
                $.get("${pageContext.request.contextPath}/scmUser/checkExist", {account: $(obj).val()}, function (data) {
                    if (data === "true") {
                        $("#check").html("账号已存在").css("color", "red");
                        $("#add_save").attr("disabled", true);
                        $("#add_password").val("");
                    } else if (data === "false") {
                        $("#check").html("账号可用").css("color", "green");
                        $("#add_save").attr("disabled", false);
                        $("#add_password").val($(obj).val());
                    } else if (data === "empty") {
                        $("#check").html("");
                        $("#add_save").attr("disabled", false);
                    }
                }, "text");
            }

            function update() {
                document.getElementById("edit").style.display = "none";
                document.getElementById("add").style.display = "block";
                $("#add_account").val("");
                $("#check").html("");
                $("#add_name").val("");
                $("#add_password").val("");
                $("#add_createDate").val("<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>");
                $("input[name='status']").prop("checked", false);
                $("input[name='status'][value='0']").prop("checked", true);
                $("input[name='authority']").prop("checked", false);
                $("input[name='authority'][value='0']").prop("checked", true);
            }

            function edit(obj) {
                document.getElementById("add").style.display = "none";
                document.getElementById("edit").style.display = "block";
                $.get("${pageContext.request.contextPath}/scmUser/getScmUserModel", {account: $(obj).parent().parent().children(".account").html()}, function (data) {
                    $("#edit_account").val(data[0].account);
                    $("#edit_name").val(data[0].name);
                    $("#edit_password").val(data[0].password);
                    $("#edit_createDate").val(data[0].createDate);
                    if (data[0].status === 1) {
                        $("input[name='status'][value='1']").prop("checked", true);
                        $("input[name='status'][value='0']").prop("checked", false);
                    } else if (data[0].status === 0) {
                        $("input[name='status'][value='0']").prop("checked", true);
                        $("input[name='status'][value='1']").prop("checked", false);
                    }
                    $("input[name='authority']").prop("checked", false);
                    for (var i = 0; i < data[1].length; i++) {
                        $("input[name='authority'][value='" + data[1][i].modelCode + "']").prop("checked", true);
                    }
                }, "json");
            }
        </script>
    </head>

    <body>
        <div id="m">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
                <tr>
                    <td nowrap class="title1">您的位置：系统管理－－用户管理</td>
                </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="30px" nowrap class="toolbar">&nbsp;</td>
                    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"
                        onClick="update()"><img src="../images/new.gif">新增
                    </td>
                    <td nowrap class="toolbar">&nbsp;</td>
                </tr>
            </table>
            <table width="100%" border="0" align="center" cellspacing="1" class="c">
                <tr>
                    <td class="title1">用户账号</td>
                    <td class="title1">用户姓名</td>
                    <td class="title1">添加日期</td>
                    <td class="title1">锁定状态</td>
                    <td class="title1">用户权限列表</td>
                    <td class="title1">操作</td>
                </tr>
                <c:forEach items="${scmUserModelPage.dataList}" var="scmUserModel">
                    <tr>
                        <td align="center" class="account">${scmUserModel.account}</td>
                        <td align="center" class="name">${scmUserModel.name}</td>
                        <td align="center" class="createDate">${scmUserModel.createDate}</td>
                        <c:if test="${scmUserModel.status==0}">
                            <td align="center" class="status">未锁定</td>
                        </c:if>
                        <c:if test="${scmUserModel.status==1}">
                            <td align="center" class="status">已锁定</td>
                        </c:if>
                        <td align="center" class="model">${scmUserModel.modelName}</td>
                        <td align="center">
                            <a href="#" onclick="edit(this)">修改</a>
                            <a href="${pageContext.request.contextPath}/scmUser/deleteScmUser?account=${scmUserModel.account}"
                               onclick="return confirm('确定删除?');">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <div id="add" class="hidd">
            <form action="${pageContext.request.contextPath}/scmUser/addScmUser" method="get">
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <th class="title1" colspan="2">新增</th>
                    </tr>
                    <tr>
                        <td align="center" width="400px">用户账号</td>
                        <td style="padding-left: 400px">
                            <input type="text" name="account" id="add_account" required onblur="check(this)"/>
                            <span id="check"></span>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">用户姓名</td>
                        <td style="padding-left: 400px"><input type="text" name="name" id="add_name" required/></td>
                    </tr>
                    <tr>
                        <td align="center">用户密码</td>
                        <td style="padding-left: 400px">
                            <input type="password" id="add_password" name="password" required/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">添加日期</td>
                        <td style="padding-left: 400px">
                            <input type="text" name="createDate" id="add_createDate" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">锁定状态</td>
                        <td style="padding-left: 450px">
                            <label><input type="radio" name="status" value="1"/>是</label>
                            <label><input type="radio" name="status" value="0"/>否</label>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">用户权限</td>
                        <td style="padding-left: 370px">
                            <label><input type="checkbox" name="authority" value="0"/>管理员</label>
                            <label><input type="checkbox" name="authority" value="1"/>采购</label>
                            <label><input type="checkbox" name="authority" value="3"/>仓管</label>
                            <label><input type="checkbox" name="authority" value="4"/>财务</label>
                            <label><input type="checkbox" name="authority" value="2"/>销售</label>
                        </td>
                    </tr>
                    <tr>
                        <td height="18" colspan="2" align="center">
                            <input type="reset"/>
                            <input type="submit" value="保存" id="add_save"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="edit" class="hidd">
            <form action="${pageContext.request.contextPath}/scmUser/editScmUser" method="get">
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <th class="title1" colspan="2">修改</th>
                    </tr>
                    <tr>
                        <td align="center" width="400px">用户账号</td>
                        <td style="padding-left: 400px"><input type="text" name="account" id="edit_account" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">用户姓名</td>
                        <td style="padding-left: 400px"><input type="text" name="name" id="edit_name"/></td>
                    </tr>
                    <tr>
                        <td align="center">用户密码</td>
                        <td style="padding-left: 400px"><input type="password" name="password" id="edit_password"/></td>
                    </tr>
                    <tr>
                        <td align="center">添加日期</td>
                        <td style="padding-left: 400px"><input type="text" name="createDate" id="edit_createDate"
                                                               readonly/></td>
                    </tr>
                    <tr>
                        <td align="center">锁定状态</td>
                        <td style="padding-left: 450px">
                            <label><input type="radio" name="status" value="1"/>是</label>
                            <label><input type="radio" name="status" value="0"/>否</label>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">用户权限</td>
                        <td style="padding-left: 370px">
                            <label><input type="checkbox" name="authority" value="0"/>管理员</label>
                            <label><input type="checkbox" name="authority" value="1"/>采购</label>
                            <label><input type="checkbox" name="authority" value="3"/>仓管</label>
                            <label><input type="checkbox" name="authority" value="4"/>财务</label>
                            <label><input type="checkbox" name="authority" value="2"/>销售</label>
                        </td>
                    </tr>
                    <tr>
                        <td height="18" colspan="2" align="center">
                            <input type="reset"/>
                            <input type="submit" value="保存"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="pageDiv">
            当前第
            <select id="scmUserModelCurrentPage" onchange="scmUserModelPageJump()">
                <c:forEach begin="1" end="${scmUserModelPage.totalPage}" var="i">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            页
            <input type="button" value="首页" onclick="firstProduct()"/>
            <c:if test="${scmUserModelPage.currentPage==1 || empty scmUserModelPage}">
                <input id="pre_btn" type="button" value="上一页" disabled onclick="preProduct()"/>
            </c:if>
            <c:if test="${scmUserModelPage.currentPage!=1 && not empty scmUserModelPage}">
                <input id="pre_btn" type="button" value="上一页" onclick="preProduct()"/>
            </c:if>
            <c:if test="${scmUserModelPage.currentPage==scmUserModelPage.totalPage || scmUserModelPage.totalPage==0}">
                <input id="next_btn" type="button" value="下一页" disabled onclick="nextProduct()"/>
            </c:if>
            <c:if test="${scmUserModelPage.currentPage!=scmUserModelPage.totalPage && scmUserModelPage.totalPage!=0}">
                <input id="next_btn" type="button" value="下一页" onclick="nextProduct()"/>
            </c:if>
            <input type="button" value="末页" onclick="lastProduct()"/>
            <script type="text/javascript">
                $(function () {
                    $("#scmUserModelCurrentPage option[value=${scmUserModelPage.currentPage}]").attr("selected", true);
                })

                function firstProduct() {
                    location.href = '${pageContext.request.contextPath}/scmUser/getAllScmUser?currentScmUserPage=1';
                }

                function preProduct() {
                    location.href = '${pageContext.request.contextPath}/scmUser/getAllScmUser?currentScmUserPage=${scmUserModelPage.currentPage-1}';
                }

                function nextProduct() {
                    location.href = '${pageContext.request.contextPath}/scmUser/getAllScmUser?currentScmUserPage=${scmUserModelPage.currentPage+1}';
                }

                function lastProduct() {
                    location.href = '${pageContext.request.contextPath}/scmUser/getAllScmUser?currentScmUserPage=${scmUserModelPage.totalPage}';
                }

                function scmUserModelPageJump() {
                    location.href = '${pageContext.request.contextPath}/scmUser/getAllScmUser?currentScmUserPage=' + $("#scmUserModelCurrentPage option:selected").val();
                }
            </script>
            共${scmUserModelPage.totalPage}页 ${scmUserModelPage.totalRecord}条记录
        </div>
    </body>
</html>
