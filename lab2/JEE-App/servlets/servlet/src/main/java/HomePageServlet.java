import ejb.StudentsDBImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String alerte = (String) getServletContext().getAttribute("codHtmlAlerte");
        String numeA = (String) getServletContext().getAttribute("numeA");
        if (numeA == null){
            StringBuilder responseText = new StringBuilder();
            responseText.append("<h2>Hello!</h2>");
            responseText.append("<br/><br/><h5>Introduceti parametrii dupa care va fi monitorizata baza de date</h5>");
            responseText.append("<form action=\"./index\" method=\"post\">\n" +
                    "            Lungime nume:\n" +
                    "            <input type=\"number\" name=\"numeA\" />\n" +
                    "            <input type=\"number\" name=\"numeB\" />\n" +
                    "            <br />\n" +
                    "\n" +
                    "            Varsta:\n" +
                    "            <input type=\"number\" name=\"varstaA\"/>\n" +
                    "            <input type=\"number\" name=\"varstaB\"/>\n" +
                    "            <br />"+

                    "<button type=\"submit\" name=\"submit\">Trimite</button></form>");

            // trimitere raspuns la client
            response.setContentType("text/html");
            response.getWriter().print(responseText.toString());

        }else if(alerte==null)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        else{
            response.setContentType("text/html");
            response.getWriter().print(alerte);
            getServletContext().setAttribute("codHtmlAlerte","");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("numeA", req.getParameter("numeA"));
        getServletContext().setAttribute("numeB", req.getParameter("numeB"));
        getServletContext().setAttribute("varstaA", req.getParameter("varstaA"));
        getServletContext().setAttribute("varstaB", req.getParameter("varstaB"));


        resp.sendRedirect("./index");
    }
}
