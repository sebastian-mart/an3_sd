<%@ page isELIgnored="false" %> <!-- asta e ca sa pot sa citesc variabile transmise din servlet ca dolar{} -->
<html>
<body>
    <h2>Student management site</h2>
    <br />
    <p>
        <a href="./hello">Acces la primul servlet</a>
    </p>
    <p>
        <a href="./formular.jsp">Adaugare student</a>
    </p>
    <p>
        <a href="./read-student">Vizualizare studenti</a>
    </p>
    <p>
        <a href="./search-student.jsp">Cautare student</a>
    </p>
    <p>
        <a href="./export-json">Export baza date in format json</a>
    </p>

    <p>
        ${mesaj}
    </p>

</body>
</html>