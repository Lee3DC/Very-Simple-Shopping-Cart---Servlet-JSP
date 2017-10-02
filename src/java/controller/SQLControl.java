/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Customer;
import model.Product;

/**
 *
 * @author DC
 */
public class SQLControl {

    Connection con;
    Statement st;
    ResultSet rs;

    public SQLControl() {
    }

    public void loadDB() {
        try {
            con = DBContext.getConnection();
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Cant load DB");
        }

    }

    public ArrayList<Product> selectAllProduct() throws SQLException {
        ArrayList<Product> pArrList = new ArrayList<>();
        loadDB();
        String sql = "select * from ProductTBL";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String pId = rs.getString("ProductID");
                String pName = rs.getString("ProductName");
                int pPrice = Integer.parseInt(rs.getString("ProductPrice"));
                pArrList.add(new Product(pId, pName, pPrice));
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error");
        } finally {
            //disconnect db
            rs.close();
            st.close();
            con.close();
        }
        return pArrList;
    }

    public ArrayList<Product> selectProduct(String id) throws SQLException {
        ArrayList<Product> arrCart = new ArrayList<>();
        loadDB();
        String xSQL = "select top 1 * from ProductTBL where ProductID = " + id + " ";
        try {
            rs = st.executeQuery(xSQL);
            while (rs.next()) {
                String pId = rs.getString("ProductID");
                String pName = rs.getString("ProductName");
                int pPrice = Integer.parseInt(rs.getString("ProductPrice"));
                arrCart.add(new Product(pId, pName, pPrice));
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error");
        } finally {
            //disconnect db
            rs.close();
            st.close();
            con.close();
        }
        return arrCart;
    }

    public ArrayList<Customer> selectAllCustomer() throws SQLException {
        ArrayList<Customer> arrCus = new ArrayList<>();
        loadDB();

        try {
            String sql = "select * from CustomerTBL";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String cID = rs.getString("CustomerID");
                String cName = rs.getString("CustomerName");
                arrCus.add(new Customer(cID, cName));
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error");
        } finally {
            //disconnect db
            rs.close();
            st.close();
            con.close();
        }
        return arrCus;
    }

    public void insertOrder(String date, String payment, String cusID) throws SQLException {
        loadDB();

        try {
            //Assume a valid connection object conn
            con.setAutoCommit(false);

            //transaction insert order to database
            String sql = "insert into OrderTBL (OrderDate,PaymentMethod,CustomerID) "
                    + "values ('" + date + "','" + payment + "','" + cusID + "')";
            st.executeUpdate(sql);
            // If there is no error.
            con.commit();

        } catch (SQLException ex) {
            System.out.println("SQL Error");
            //if have any error
            con.rollback();
        } finally {
            st.close();
            con.close();
        }

    }

    public int getlastedOrID() throws SQLException {
        loadDB();
        int orID = -1;
        try {
            String sql = "select top 1 OrderID from OrderTBL order by OrderID DESC ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                orID = rs.getInt("OrderID");
            }

        } catch (SQLException ex) {
            System.out.println("SQL Error");
        } finally {
            rs.close();
            st.close();
            con.close();
        }

        return orID;
    }

    public void insertOrderLine(int orderID, String pID, int quantity, int price) throws SQLException {
        loadDB();
        //Assume a valid connection object conn

        try {
            con.setAutoCommit(false);

            //transaction insert order line to database
            String sql = "insert into OrderLineTBL "
                    + "values ('" + orderID + "','" + pID + "','" + quantity + "','" + price + "')";
            st.executeUpdate(sql);
            // If there is no error.
            con.commit();
        } catch (SQLException ex) {
            System.out.println("SQL Error");
            //if have any error
            con.rollback();
        } finally {
            st.close();
            con.close();
        }
    }

}
