<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
        <title>Cautare student</title>
        <meta charset="UTF-8">
    </head>
    <body>
            <h3>Cautare student</h3>
            <form action="./read-student" method="get">
                    <label for="option">Alegeti criteriu cautare:</label>
                    <select name="option" id="option">
                      <option value="nume">Nume</option>
                      <option value="prenume">Prenume</option>
                      <option value="varsta">Varsta</option>
                      <option value="oras">Oras</option>
                    </select>
            <br />
            Search: <input type="text" name="item_cautat"/>
            <button type="submit" name="submit">Cautare</button>
            </form>
            <a href="./index">Home</a>
    </body>
    </html>