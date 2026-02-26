<html xmlns:jsp="http://java.sun.com/JSP/Page">
<%@ page isELIgnored="false" %>
    <head>
        <title>Actualizare student</title>
        <meta charset="UTF-8">
    </head>
    <body>
    <form action="./update-student" method="post">
        <h3>Actualizare student</h3>
        <label>
          <input type="checkbox" name="delete" value="false" unchecked>
          Stergere student gasit
        </label>
        <br />
        <br />
        <h6>Introduceti datele ce doresc schimbate:</h6>
            Nume: <input type="text" name="nume" value="${nume}" />
            <br />
            Prenume: <input type="text" name="prenume" value="${prenume}" />
            <br />
            Varsta: <input type="number" name="varsta" value="${varsta}" />
            <br />
            Oras: <input type="text" name="oras" value="${oras}" />
            <br />
            Adresa: <input type="text" name="adresa" value="${adresa}"/>
            id: <input type="hidden" name="id" value="${id}" />
            <br />
            <br />
            <button type="submit" name="submit">Actualizare</button>
        </form>
        <a href="./index">Home</a>
    </body>
</html>