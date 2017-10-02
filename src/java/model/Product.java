/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DC
 */
public class Product {
    private String pId;
    private String pName;
    private int pPrice;

    public Product() {
    }

    public Product(String pId, String pName, int pPrice) {
        this.pId = pId;
        this.pName = pName;
        this.pPrice = pPrice;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }
   
    
}
