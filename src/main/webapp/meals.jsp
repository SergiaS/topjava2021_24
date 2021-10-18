<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <hr>
    <h2>Meals</h2>

    <a href="${pageContext.request.contextPath}/meals/new">Add meal</a>
    <table>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>

        <c:forEach var="meal" items="${mealsTo}">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'exceeded' : 'notExceeded'}">
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate value="${parsedDateTime}" pattern="dd.MM.yyyy HH:mm"/>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="${pageContext.request.contextPath}/meals/update?id=${meal.id}">✏️</a></td>
                <td><a href="${pageContext.request.contextPath}/meals/delete?id=${meal.id}">❌️</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
