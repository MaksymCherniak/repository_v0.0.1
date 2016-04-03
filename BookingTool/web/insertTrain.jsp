<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Tool</title>
</head>
<body bgcolor="#dcdcdc">
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Insert train</b></caption>
    <form name="insertTrain" action="insertTrain.do" method="post">
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Enter route number:</b></td>
            <td><input type="text" name="routeNumber" style="width: 206px"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Enter start cruising date:</b></td>
            <td><select name="startDD">
                <% for (int i = 1; i <= 31; i++) { %>
                <option value="<%=i%>"><%=i%>
                </option>
                <% } %>
            </select> - <select name="startMM">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select> - <select name="startYY">
                <option value="2016">2016</option>
                <option value="2017">2017</option>
            </select>
            </td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 200px"><b>&nbsp;&nbsp;Select days cruising:</b></td>
            <td><select size="8" multiple name="days">
                <option value="allDays">All days</option>
                <option value="monday">Monday</option>
                <option value="tuesday">Tuesday</option>
                <option value="wednesday">Wednesday</option>
                <option value="thursday">Thursday</option>
                <option value="friday">Friday</option>
                <option value="saturday">Saturday</option>
                <option value="sunday">Sunday</option>
            </select>
            </td>
        </tr>
        <TABLE border="0" align="center">
            <tr>
                <td><input type="submit" value="Enter"></td>
            </tr>
        </TABLE>
    </form>
    <TABLE border="0" align="center">
        <tr>
            <td><p><b><a href="main.jsp">Main page</a></b></p></td>
        </tr>
    </TABLE>
</table>
</body>
</html>
