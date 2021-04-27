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
public class RenterDTO {
    private String renter;
    private String phoneNumber;
    private String email;
    private String startDay;
    private String endDay;

    public RenterDTO() {
    }

    public RenterDTO(String renter, String phoneNumber, String email, String startDay, String endDay) {
        this.renter = renter;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    

    
    
}
