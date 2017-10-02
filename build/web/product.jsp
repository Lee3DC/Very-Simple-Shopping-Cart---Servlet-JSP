<%-- 
    Document   : product
    Created on : Sep 26, 2017, 2:17:25 PM
    Author     : DC
--%>

<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.SQLControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List products</title>
    </head>
    <body>
        <%
            SQLControl control = new SQLControl();
            ArrayList<Product> arrP = control.selectAllProduct();

        %>
        <h1>Select thing to buy</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%  if (arrP != null) {
                        for (int i = 0; i < arrP.size(); i++) {
                %>
                <tr>
                    <td><%= arrP.get(i).getpId()%></td>
                    <td><%= arrP.get(i).getpName()%></td>
                    <td><%= arrP.get(i).getpPrice()%></td>
                    <td>
                        <a href="ProductServlet?id=<%=arrP.get(i).getpId()%>"><input type="submit" value="Buy" /></a> 
                    </td>
                </tr>
                <% }
                    }%>
            </tbody>
        </table>

        <p><a href="cart.jsp">View Shopping Cart</a></p>

    </body>
</html>
