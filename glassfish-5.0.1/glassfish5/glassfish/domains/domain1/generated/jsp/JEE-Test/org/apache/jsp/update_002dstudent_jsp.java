package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class update_002dstudent_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html xmlns:jsp=\"http://java.sun.com/JSP/Page\">\n");
      out.write("    <head>\n");
      out.write("        <title>Actualizare student</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h3>Formular actualizare student</h3>\n");
      out.write("        ");
      beans.StudentBean studentBean = null;
      synchronized (_jspx_page_context) {
        studentBean = (beans.StudentBean) _jspx_page_context.getAttribute("studentBean", PageContext.PAGE_SCOPE);
        if (studentBean == null){
          studentBean = new beans.StudentBean();
          _jspx_page_context.setAttribute("studentBean", studentBean, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "nume",

            request.getAttribute("nume") );
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "prenume",

            request.getAttribute("prenume") );
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("studentBean"), "varsta",

            request.getAttribute("varsta") );
      out.write("\n");
      out.write("        Introduceti datele despre student:\n");
      out.write("        <form action=\"./process-student\" method=\"post\">\n");
      out.write("            Nume: <input type=\"text\" name=\"nume\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getNume())));
      out.write("\" />\n");
      out.write("            <br />\n");
      out.write("            Prenume: <input type=\"text\" name=\"prenume\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getPrenume())));
      out.write("\" />\n");
      out.write("            <br />\n");
      out.write("            Varsta: <input type=\"number\" name=\"varsta\" value=\"");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((beans.StudentBean)_jspx_page_context.findAttribute("studentBean")).getVarsta())));
      out.write("\" />\n");
      out.write("            <br />\n");
      out.write("            <br />\n");
      out.write("            <button type=\"submit\" name=\"submit\">Trimite</button>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
