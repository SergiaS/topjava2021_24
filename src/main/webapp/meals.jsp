<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        table {
            border: 3px solid darkblue;
            border-spacing: 3px;
        }

        th {
            color: whitesmoke;
            background-color: darkblue;
        }

        tr {
            background-color: lightcyan;
        }

        th, td {
            padding: 2px 5px;
        }

        .exceeded {
            color: red;
        }

        .notExceeded {
            color: green;
        }
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>

    <table>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>

        <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${mealsTo}">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'exceeded' : 'notExceeded'}">
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate value="${parsedDateTime}" pattern="dd.MM.yyyy HH:mm"/>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
