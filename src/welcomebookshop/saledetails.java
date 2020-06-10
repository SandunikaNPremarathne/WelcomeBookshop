/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcomebookshop;

import java.util.Date;

/**
 *
 * @author Sandu
 */
public class saledetails {
    private String saleNo;
    private String date;
    private String user;
    private String cust;
    private String total;

    /**
     * @return the saleNo
     */
    public String getSaleNo() {
        return saleNo;
    }

    /**
     * @param saleNo the saleNo to set
     */
    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the cust
     */
    public String getCust() {
        return cust;
    }

    /**
     * @param cust the cust to set
     */
    public void setCust(String cust) {
        this.cust = cust;
    }

    public void setDate(Date datef) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
