<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
table td{
padding:20px;
}
</style>
</head>
<body>
    <h1>This website was created by students. So here is an error page.</h1>
    <table>
        <tr>
            <td>Error</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${status}</td>
        </tr>
    </table>
</body>
</html>