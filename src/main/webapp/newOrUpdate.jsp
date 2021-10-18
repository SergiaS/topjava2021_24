<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <hr>
    <h2>${meal == null ? 'New' : 'Update'} meal</h2>

    <form action="${meal == null ? 'new' : 'update'}" method="post">
        <input type="hidden" name="id" value="${meal.id}">

        <table>
            <tr>
                <td><label>DateTime:</label></td>
                <td><input type="datetime-local" name="dateTime" value="${meal == null ? "" : meal.dateTime}" required></td>
            </tr>
            <tr>
                <td><label>Description:</label></td>
                <td><input type="text" name="description" value="${meal == null ? "" : meal.description}" size="30" required></td>
            </tr>
            <tr>
                <td><label>Calories:</label></td>
                <td><input type="number" name="calories" value="${meal == null ? "" : meal.calories}" required></td>
            </tr>
        </table>
        <input type="submit" value="Save"/>
        <input type="button" value="Cancel" onclick="window.history.back()"/>
    </form>
</body>
</html>
