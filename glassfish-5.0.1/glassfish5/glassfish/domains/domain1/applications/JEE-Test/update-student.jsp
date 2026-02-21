<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Actualizare student</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h3>Formular actualizare student</h3>
        <jsp:useBean id="studentBean" class="beans.StudentBean" />
        <jsp:setProperty name="studentBean" property="nume" value='<%=
            request.getAttribute("nume") %>'/>
        <jsp:setProperty name="studentBean" property="prenume" value='<%=
            request.getAttribute("prenume") %>'/>
        <jsp:setProperty name="studentBean" property="varsta" value='<%=
            request.getAttribute("varsta") %>'/>
        Introduceti datele despre student:
        <form action="./process-student" method="post">
            Nume: <input type="text" name="nume" value="<jsp:getProperty name='studentBean' property='nume' />" />
            <br />
            Prenume: <input type="text" name="prenume" value="<jsp:getProperty name='studentBean' property='prenume' />" />
            <br />
            Varsta: <input type="number" name="varsta" value="<jsp:getProperty name='studentBean' property='varsta' />" />
            <br />
            <br />
            <button type="submit" name="submit">Trimite</button>
        </form>
    </body>
</html>