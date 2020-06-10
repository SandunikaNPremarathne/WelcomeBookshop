/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcomebookshop;

/**
 *
 * @author omeg
 */
public class stockDetails {
    private String itemcode;
    private String name;
    private String category;
    private String supId;
    private int quantity;
    private String unitPrice;
    private String buyingPrice;

    /**
     * @return the item code
     */
    public String getItemcode() {
        return itemcode;
    }

    /**
     * @param itemcode the item code to set
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the supId
     */
    public String getSupId() {
        return supId;
    }

    /**
     * @param supId the supId to set
     */
    public void setSupId(String supId) {
        this.supId = supId;
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
     * @return the unitPrice
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the buyingPrice
     */
    public String getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * @param buyingPrice the buyingPrice to set
     */
    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
    
}
