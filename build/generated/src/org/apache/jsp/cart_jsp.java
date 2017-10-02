package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Customer;
import controller.SQLControl;
import java.util.ArrayList;
import model.Cart;

public final class cart_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Shopping Cart</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <h1>Your Shopping Cart</h1>\n");
      out.write("        ");

            SQLControl control = new SQLControl();
            ArrayList<Customer> arrCus = control.selectAllCustomer();
        
      out.write("\n");
      out.write("        <form action=\"ShoppingCardServlet\" method=\"POST\">\n");
      out.write("        <p>Customer: <select name=\"slCustomer\">\n");
      out.write("                ");

                    for (int i = 0; i < arrCus.size(); i++) {
                
      out.write("\n");
      out.write("                <option value=\"");
      out.print(arrCus.get(i).getcID());
      out.write("\"> ");
      out.print( arrCus.get(i).getcName() );
      out.write("</option>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("\n");
      out.write("            </select>\n");
      out.write("        </p>\n");
      out.write("        <p>Payment Method: <input type=\"text\" name=\"txtMethod\" value=\"Cash\" /></p>\n");
      out.write("        <table border=\"0\">\n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Product</th>\n");
      out.write("                    <th>Quantity</th>\n");
      out.write("                    <th>Price</th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("            <tbody>\n");
      out.write("                ");

                    int totalOrder = 0;
                    HttpSession newSession = request.getSession();
                    if (newSession.getAttribute("cart") != null) {
                        ArrayList<Cart> arrC = (ArrayList<Cart>) newSession.getAttribute("cart");
                        for (int i = 0; i < arrC.size(); i++) {
                            totalOrder += (arrC.get(i).getQuantity() * arrC.get(i).getpPrice());
                
      out.write("   \n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( arrC.get(i).getpName());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( arrC.get(i).getQuantity());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( arrC.get(i).getpPrice());
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");
      }
                } else {
                
      out.write("\n");
      out.write("            <h1>YOU DONT BUY ANYTHING</h1>\n");
      out.write("            ");

                }

            
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("\n");
      out.write("    <p>Total: ");
      out.print( totalOrder);
      out.write("</p>\n");
      out.write("    <p><input type=\"submit\" value=\"Confirm\" value =\"confirm\" /></p>\n");
      out.write("    <form>\n");
      out.write("</body>\n");
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
