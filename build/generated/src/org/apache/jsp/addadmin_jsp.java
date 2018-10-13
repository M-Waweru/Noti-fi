package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import databaseconnect.ConnectionManager;

public final class addadmin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


                        private Connection conn = null;
                        private Statement stmt = null;
                        private ResultSet rs = null;
                        ConnectionManager conman = new ConnectionManager();
                    
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Add admin</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/materialize.min.css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-3.1.1.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/materialize.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"blue-grey\">\n");
      out.write("            <div class=\"nav-wrapper\">\n");
      out.write("                <a href=\"#\" class=\"brand-logo\">Admin Module</a>\n");
      out.write("                <ul id=\"nav-mobile\" class=\"right hide-on-med-and-down\">\n");
      out.write("                    <li><a href=\"badges.html\">About</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <table>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>No</td>\n");
      out.write("                        <td>Name</td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
      out.write("\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        conn  = conman.getConnection();
                        try {
                            String adminsql = "select * from admins";
                            stmt = conn.createStatement();

                            rs = stmt.executeQuery(adminsql);
                            out.println("<div class=\"container\">\n"
                                    + "			<table>\n"
                                    + "				<thead>\n"
                                    + "					<tr>\n"
                                    + "						<td>No</td>\n"
                                    + "						<td>Name</td>\n"
                                    + "                                               <td>Description</td>\n"
                                    + "					</tr>\n"
                                    + "				</thead>\n"
                                    + "				<tbody>\n");
                            while (rs.next()) {
                                int adminno = rs.getInt(1);
                                String adminname = rs.getString(2);
                                String description = rs.getString(3);

                                out.println("	<tr>\n"
                                        + "						<td>" + adminno + "</td>\n"
                                        + "						<td>" + adminname + "</td>\n"
                                        + "                                               <td>" + description + "</td>\n"
                                        + "					</tr>\n");
                            }
                            out.println("</tbody>\n"
                                    + "			</table>\n"
                                    + "		</div>");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("        <button class=\"btn waves-effect waves-light\" type=\"submit\" name=\"action\">Submit</button>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
