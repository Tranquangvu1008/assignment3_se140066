/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author SE140066
 */
public class CodeDTO {
    private String discountCode;
    private String carBrand;
    private int discount;
    private Date startDay;
    private Date endDay;

    public CodeDTO() {
    }

    public CodeDTO(String discountCode, String carBrand, int discount, Date startDay, Date endDay) {
        this.discountCode = discountCode;
        this.carBrand = carBrand;
        this.discount = discount;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
    
    
}
