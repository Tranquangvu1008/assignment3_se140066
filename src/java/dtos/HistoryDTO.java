/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author SE140066
 */
public class HistoryDTO {

    private String carID;
    private String carBrand;
    private String carName;
    private Float price;
    private int quantity;
    private String startDay;
    private String endDay;
    private String discountCode;

    public HistoryDTO() {
    }

    public HistoryDTO(String carID, String carBrand, String carName, Float price, int quantity, String startDay, String endDay, String discountCode) {
        this.carID = carID;
        this.carBrand = carBrand;
        this.carName = carName;
        this.price = price;
        this.quantity = quantity;
        this.startDay = startDay;
        this.endDay = endDay;
        this.discountCode = discountCode;
    }
    

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    
    

}
