/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import daos.CarDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SE140066
 */
public class CartDTO {

    private String customName;
    private Map<String, CarDTO> cart;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Map<String, CarDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CarDTO> cart) {
        this.cart = cart;
    }

    public CartDTO(String customName, Map<String, CarDTO> cart) {
        this.customName = customName;
        this.cart = cart;
    }

    public CartDTO() {
    }

    public void add(CarDTO car) {
        if (this.cart == null) {
            this.cart = new HashMap<>();

        }
        if (this.cart.containsKey(car.getCode())) {
            int quantity = this.cart.get(car.getCode()).getQuantity();
            car.setQuantity(quantity + car.getQuantity());
        }
        cart.put(car.getCode(), car);
    }
    public void delete(String code){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(code)){
            this.cart.remove(code);
        }
    }
    public void update(CarDTO car){
        if(this.cart!=null){
            if(this.cart.containsKey(car.getCode())){
                this.cart.replace(car.getCode(), car);
            }
        }
    } 
    
    public int getMaxQuantity(String id, String startDate, String endDate) throws SQLException, ParseException {
        CarDAO dao = new CarDAO();
        int maxQuantity = dao.getMaxQuantity(id, startDate, endDate);
        if (cart != null) {
            if (!cart.isEmpty()) {
                for (Map.Entry<String, CarDTO> entry : cart.entrySet()) {
                    String key = entry.getKey();
                    CarDTO value = entry.getValue();
                    if (value.getCarID().equals(id)) {
                        String carCartID = id + startDate + endDate;
                        if (utils.DBUtils.checkDateBetween(startDate, endDate, value.getStartDay(), value.getEndDay()) && key.equals(carCartID)) {
                            maxQuantity -= value.getQuantity();
                        }
                    }
                }
            }
        }

        return maxQuantity;
    }
}
