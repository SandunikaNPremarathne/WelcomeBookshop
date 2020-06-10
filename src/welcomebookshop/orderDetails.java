/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcomebookshop;

import java.util.Date;

/**
 *
 * @author omeg
 */
public class orderDetails {
    
    private String orderNo;
    private Date date;
    private String cusName;
    private String tele;
    private Date subDate;
    private String total;
    private String status;
    

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the cusName
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * @param cusName the cusName to set
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * @return the tele
     */
    public String getTele() {
        return tele;
    }

    /**
     * @param tele the tele to set
     */
    public void setTele(String tele) {
        this.tele = tele;
    }

    /**
     * @return the subDate
     */
    public Date getSubDate() {
        return subDate;
    }

    /**
     * @param subDate the subDate to set
     */
    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
}

