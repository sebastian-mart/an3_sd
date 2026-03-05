<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %> <!-- asta e ca sa pot sa citesc variabile transmise din servlet ca dolar{} -->
<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Informatii student</title>
    </head>
    <body>
        <h3>Informatii student</h3>
        <table border=1>
        <tr>
            <th>Nume</th>
            <th>Prenume</th>
            <th>Varsta</th>
            <th>An nastere</th>
            <th>Oras</th>
            <th>Adresa</th>
        </tr>

        <c:set var="anCurent" value="${anCurent}" />
        <!-- asta e loop pentru afisare info -->
        <c:forEach items="${students}" var="s">
           <tr>
            <td><c:out value="${s.nume}"/></td>
            <td><c:out value="${s.prenume}"/></td>
            <td><c:out value="${s.varsta}"/></td>
            <td>${anCurent-s.varsta}</td>
            <td><c:out value="${s.oras}"/></td>
            <td><c:out value="${s.adresa}"/></td>
            <td>
            <form action="./update-student" method="get">
                  <input type="hidden" name="nume" value="${s.nume}" />
                  <input type="hidden" name="prenume" value="${s.prenume}" />
                  <input type="hidden" name="varsta" value="${s.varsta}" />
                  <input type="hidden" name="oras" value="${s.oras}" />
                  <input type="hidden" name="adresa" value="${s.adresa}" />
                  <input type="hidden" name="id" value="${s.id}" />
                  <button type="actualizare">Update</button>
                </form>
            </td>
           </tr>
        </c:forEach>
        </table>
        <a href="./index">Home</a>
    </body>
</html>