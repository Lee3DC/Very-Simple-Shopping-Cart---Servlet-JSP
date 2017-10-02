<%-- 
    Document   : cart
    Created on : Sep 26, 2017, 2:34:03 PM
    Author     : DC
--%>

<%@page import="model.Customer"%>
<%@page import="controller.SQLControl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>

        <h1>Your Shopping Cart</h1>
        <%
            SQLControl control = new SQLControl();
            ArrayList<Customer> arrCus = control.selectAllCustomer();
        %>
        <form action="ShoppingCardServlet" method="POST">
            <%--
                Load all Customer form data
            --%>          
        <p>Customer: <select name="slCustomer">
                <%
                    for (int i = 0; i < arrCus.size(); i++) {
                %>
                <option value="<%=arrCus.get(i).getcID()%>"> <%= arrCus.get(i).getcName() %></option>
                <%
                    }
                %>

            </select>
        </p>
        <p>Payment Method: <input type="text" name="txtMethod" value="Cash" /></p>
        <table border="0">
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%--
                    - Get all product that added to cart
                    - Caculate total cash
                --%>   
                <%
                    int totalOrder = 0;
                    HttpSession newSession = request.getSession();
                    if (newSession.getAttribute("cart") != null) {
                        ArrayList<Cart> arrC = (ArrayList<Cart>) newSession.getAttribute("cart");
                        for (int i = 0; i < arrC.size(); i++) {
                            totalOrder += (arrC.get(i).getQuantity() * arrC.get(i).getpPrice());
                %>   
                <tr>
                    <td><%= arrC.get(i).getpName()%></td>
                    <td><%= arrC.get(i).getQuantity()%></td>
                    <td><%= arrC.get(i).getpPrice()%></td>
                </tr>
                <%      }
                } else {
                %>
            <h1>YOU DONT BUY ANYTHING</h1>
            <%
                }

            %>
        </tbody>
    </table>

    <p>Total: <%= totalOrder%></p>
    <p><input type="submit" value="Confirm" value ="confirm" /></p>
    <form>
     <p><a href="product.jsp">Back to buy</a></p>
</body>
</html>
