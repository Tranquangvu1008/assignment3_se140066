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
public class CarDTO {
    private String carID;
    private String carBrand;
    private String carName;
    private String color;
    private int slot;
    private String image;
    private float price;
    private String categoryID;
    private int quantity;
    private String startDay;
    private String endDay;
    private String code;

    public CarDTO() {
    }

    public CarDTO(String carID, String carBrand, String carName, String color, int slot, String image, float price, String categoryID, int quantity, String startDay, String endDay, String code) {
        this.carID = carID;
        this.carBrand = carBrand;
        this.carName = carName;
        this.color = color;
        this.slot = slot;
        this.image = image;
        this.price = price;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.startDay = startDay;
        this.endDay = endDay;
        this.code = code;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}
