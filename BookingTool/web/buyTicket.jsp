<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking tool</title>
</head>
<body bgcolor="#dcdcdc">
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Buy ticket</b></caption>
<form name="buyTicket" action="buyTicket.do" method="post">
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td style="width: 200px"><b>&nbsp;&nbsp;Enter date:</b></td>
        <td><select name="dateDD">
            <% for (int i = 1; i <= 31; i++) { %>
            <option value="<%=i%>"><%=i%></option>
            <% } %>
        </select> - <select name="dateMM">
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
        </select> - <select name="dateYY">
            <option value="2016">2016</option>
            <option value="2017">2017</option>
        </select>
        </td>
    </tr>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td style="width: 200px"><b>&nbsp;&nbsp;Enter train number:</b></td>
        <td><input type="text" name="trainNumber" style="width: 250px"></td>
    </tr>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td style="width: 200px"><b>&nbsp;&nbsp;Enter wagon number:</b></td>
        <td><input type="text" name="wagonNumber" style="width: 250px"></td>
    </tr>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td style="width: 200px"><b>&nbsp;&nbsp;Enter seat number:</b></td>
        <td><input type="text" name="seatNumber" style="width: 250px"></td>
    </tr>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td style="width: 200px"><b>&nbsp;&nbsp;Enter your name:</b></td>
        <td><input type="text" name="firstName" style="width: 250px"></td>
    </tr>
    <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
        <td style="width: 200px"><b>&nbsp;&nbsp;Enter your surname:</b></td>
        <td><input type="text" name="lastName" style="width: 250px"></td>
    </tr>
    <TABLE border="0" align="center">
        <tr><td><input type="submit" value="Enter"></td></tr>
    </TABLE>
</form>
    <TABLE border="0" align="center">
        <tr><td><p><b><a href="main.jsp">Main page</a></b></p></td></tr>
    </TABLE>
</table>
</body>
</html>
