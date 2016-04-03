<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body bgcolor="#dcdcdc">
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Insert route</b></caption>
    <form name="insertStation" action="insertStation.do" method="post">
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Enter route number:</b></td>
            <td><input type="text" name="routeNumber" style="width: 250px"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Enter station:</b></td>
            <td><input type="text" name="station" style="width: 250px"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Enter arrival time:</b></td>
            <td>&nbsp;&nbsp;<select name="arrivalHH">
                <% for (int i = 0; i < 24; i++) { %>
                <option value="<%=i%>"><%=i%>
                </option>
                <% } %>
            </select> : <select name="arrivalMM">
                <% for (int i = 0; i < 60; i++) { %>
                <option value="<%=i%>"><%=i%>
                </option>
                <% } %>
            </select></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Enter leaving time:</b></td>
            <td>&nbsp;&nbsp;<select name="leavingHH">
                <% for (int i = 0; i < 24; i++) { %>
                <option value="<%=i%>"><%=i%>
                </option>
                <% } %>
            </select> : <select name="leavingMM">
                <% for (int i = 0; i < 60; i++) { %>
                <option value="<%=i%>"><%=i%>
                </option>
                <% } %>
            </select></td>
        </tr>
        <TABLE border="0" align="center">
            <tr>
                <td><input type="submit" value="Add stations"></td>
            </tr>
        </TABLE>
    </form>
    <TABLE border="0" align="center">
        <tr>
            <td><b>${info}</b></td>
        </tr>
    </TABLE>
    <TABLE border="0" align="center">
        <tr>
            <td><p><b><a href="insertTrain.jsp">Insert train</a></b></p></td>
        </tr>
    </TABLE>
    <TABLE border="0" align="center">
        <tr>
            <td><p><b><a href="main.jsp">Main page</a></b></p></td>
        </tr>
    </TABLE>
</table>
</body>
</html>
