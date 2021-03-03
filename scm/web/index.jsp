<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SCM</title>
    </head>

    <frameset rows="23,10,*" cols="*" frameborder="NO" border="0" framespacing="0" id="controlRv">
        <frame src="title.jsp" name="topFrame" scrolling="NO" noresize>
        <frame src="dynamic_bar_h.jsp" scrolling="no" name="sidebar_r" noresize>
        <frameset cols="120,10,*" frameborder="NO" border="0" framespacing="0" id="controlFv">
            <frame src="catalog.jsp" name="leftFrame" scrolling="NO" noresize>
            <frame src="dynamic_bar_v.jsp" scrolling="no" name="sidebar_v" noresize>
            <frame src="" name="mainFrame" scrolling="auto">
        </frameset>
    </frameset>
    <noframes></noframes>
</html>