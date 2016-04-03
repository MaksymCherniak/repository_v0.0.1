<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking tool</title>
</head>
<body bgcolor="#dcdcdc">
<table align="center" bgcolor="#b0c4de" border="1" cellspacing="0" cellpadding="0" style="border-color: gray">
    <caption><b>Insert wagon</b></caption>
    <form name="insertWagon" action="insertWagon.do" method="post">
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 230px"><b>&nbsp;&nbsp;Enter route number:</b></td>
            <td><input type="text" name="routeNumber" style="width: 280px"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 230px"><b>&nbsp;&nbsp;Enter comfortable wagon numbers:</b></td>
            <td><input type="text" name="comfortableNumbers" style="width: 280px"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 230px"><b>&nbsp;&nbsp;Enter compartment wagon numbers:</b></td>
            <td><input type="text" name="compartmentNumbers" style="width: 280px"></td>
        </tr>
        <tr align="left" style="font-size: 8pt;font-family: Tahoma;color: black">
            <td style="width: 230px"><b>&nbsp;&nbsp;Enter economy wagon numbers:</b></td>
            <td><input type="text" name="economyNumbers" style="width: 280px"></td>
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
