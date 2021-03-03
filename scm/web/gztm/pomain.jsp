<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <title>新添采购单</title>

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
                    <td nowrap class="title1">采购单管理 -- 新添采购单</td>
                </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="30px" nowrap class="toolbar">&nbsp;</td>
                    <td width="40px" nowrap class="toolbar" onMouseOver="OMO(event)" onMouseOut="OMOU(event)"
                        onclick="location.href='pomainform.jsp'"><img src="../images/new.gif">新增
                    </td>
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
                采购单编号：<input type="text" id="poId"/>
                供应商：
                <select id="venderCode">
                    <option value="">请选择</option>
                </select>
                付款方式：
                <select id="payType">
                    <option value="">请选择</option>
                    <option value="货到付款">货到付款</option>
                    <option value="款到发货">款到发货</option>
                    <option value="预付款到发货">预付款到发货</option>
                </select>
                创建日期：
                <input class="Wdate" type="text" id="startDate"
                       onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
                -
                <input class="Wdate" type="text" id="endDate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
                <input type="button" value="查询" onclick="goPage(1)"/>
            </div>

            <div id="dataList">
                <table width="100%" border="0" align="center" cellspacing="1" class="c">
                    <tr>
                        <td class="title1">采购单编号</td>
                        <td class="title1">创建时间</td>
                        <td class="title1">供应商名称</td>
                        <td class="title1">创建用户</td>
                        <td class="title1">附加费用</td>
                        <td class="title1">采购产品总价</td>
                        <td class="title1">采购单总价格</td>
                        <td class="title1">付款方式</td>
                        <td class="title1">最低预付款金额</td>
                        <td class="title1">操作</td>
                    </tr>

                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20181221142653</a></td>
                        <td align="center">2018-12-21 14:26:53</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">0.0</td>
                        <td align="center">6.0</td>
                        <td align="center">6.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>

                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20180622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>

                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                    <tr>
                        <td align="center"><a href="finance_receive_detail.jsp">20170622142425</a></td>
                        <td align="center">2017-06-22 14:24:25</td>
                        <td align="center">京东商城</td>
                        <td align="center">lisi</td>
                        <td align="center">12.0</td>
                        <td align="center">72.0</td>
                        <td align="center">60.0</td>
                        <td align="center">货到付款</td>
                        <td align="center">0.0</td>

                        <td align="center">
                            <a href="javascript:void(0)">修改</a>
                            <a href="javascript:void(0)">删除</a>
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td colspan="10" name="detail"></td>
                    </tr>
                </table>


                <div class="pageDiv">
                    当前第<span id="currentPage">1</span>页

                    <input type="button" value="首页" disabled="disabled"/>
                    <input type="button" value="上一页" disabled="disabled"/>


                    <input type="button" value="下一页" onclick="goPage(2)"/>
                    <input type="button" value="末页" onclick="goPage(2)"/>

                    共2页 3条记录
                </div>


            </div>
        </div>


    </body>
</html>
