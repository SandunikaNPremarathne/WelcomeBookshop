/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author omeg
 */
public class PurchItems {
    
    private int purchNum;
    private String itemNo, itemCode;
    private int quantity;
    private double price;

    /**
     * @return the purchNum
     */
    public int getPurchNum() {
        return purchNum;
    }

    /**
     * @param purchNum the purchNum to set
     */
    public void setPurchNum(int purchNum) {
        this.purchNum = purchNum;
    }

    /**
     * @return the itemNo
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * @param itemNo the itemNo to set
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
}
