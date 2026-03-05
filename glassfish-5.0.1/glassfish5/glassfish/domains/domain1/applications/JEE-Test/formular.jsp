<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Adaugare Student</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h3>Formular student nou</h3>
        Introduceti datele despre student:
        <form action="./process-student" method="post">
            Nume: <input type="text" name="nume" />
            <br />
            Prenume: <input type="text" name="prenume" />
            <br />
            Varsta: <input type="number" name="varsta" />
            <br />
            <br />
            Oras: <input type="text" name="oras" />
            <br />
            <br />
            Adresa: <input type="text" name="adresa" />
            <br />
            <br />
            <button type="submit" name="submit">Trimite</button>
        </form>
        <a href="./index">Home</a>
    </body>
</html>