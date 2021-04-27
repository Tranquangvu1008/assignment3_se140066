/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CarDTO;
import dtos.CartDTO;
import dtos.CodeDTO;
import dtos.FeedbackDTO;
import dtos.HistoryDTO;
import dtos.OrderDTO;
import dtos.RenterDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author SE140066
 */
public class CarDAO {

    //Find the detail information for menu of car
    public List<String> getListCategory() {
        List<String> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT categoryID FROM tblCategory";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);

                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(category);
            }
        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    /*-------------Count Page--------------*/
    public int getNumberPage(String carName, String category) {
        int total = 0;
        int countPage = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT COUNT(carID) FROM tblCars "
                        + "WHERE quantity >= 0 AND carName LIKE ? AND categoryID LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carName + "%");
                stm.setString(2, "%" + category + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    total = rs.getInt(1);
                    int size = 8;
                    countPage = total / size;

                    if (total % size != 0) {
                        countPage++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return countPage;
    }

    //Get paging list car
    public ArrayList<CarDTO> getPaging(int index, String search, String category, int quantity) {
        ArrayList<CarDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT carID, carBrand, carName, color, slot, image, price, categoryID, quantity "
                        + "from tblCars "
                        + "WHERE carName LIKE ? AND categoryID LIKE ? AND quantity >= ? "
                        + "order by categoryID "
                        + "offset ? rows "
                        + "fetch first 8 rows only";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "%" + category + "%");
                stm.setInt(3, quantity);
                stm.setInt(4, (index - 1) * 8);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString(1);
                    String carBrand = rs.getString(2);
                    String carName = rs.getString(3);
                    String color = rs.getString(4);
                    int slot = rs.getInt(5);
                    String image = rs.getString(6);
                    float price = rs.getFloat(7);
                    category = rs.getString(8);
                    quantity = rs.getInt(9);

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new CarDTO(carID, carBrand, carName, color, slot, image, price, category, quantity, null, null, null));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    /*-------------Confirm Order--------------*/
    public static int checkQuantityOfCar(String itemID) {
        //Kiem tra so luong XE 
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT quantity FROM tblCars WHERE carID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemID);
            rs = stm.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return quantity;
    }

    public static void editQuantityOfCar(int number, String carID, int quantity) {
        Connection conn = null;
        PreparedStatement stm = null;

        int amount = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblCars SET quantity = ? WHERE carID = ?;";
            stm = conn.prepareStatement(sql);
            amount = quantity - number;

            stm.setInt(1, amount);
            stm.setString(2, carID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public List<String> getListCarID() {
        List<String> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT carID FROM tblCars;";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString(1);

                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(itemID);
            }
        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    /*Check discount code*/
    public static List<CodeDTO> checkDiscountCode(String carBrand) {
        List<CodeDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT discountCode, carBrand, discount, startDay, endDay FROM tblDiscount WHERE carBrand = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, carBrand);
            rs = stm.executeQuery();
            while (rs.next()) {
                String discountCode = rs.getString(1);
                carBrand = rs.getString(2);
                int discount = rs.getInt(3);
                Date startDay = rs.getDate(4);
                Date endDay = rs.getDate(5);

                if (list == null) {
                    list = new ArrayList<CodeDTO>();
                }
                list.add(new CodeDTO(discountCode, carBrand, discount, startDay, endDay));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    //create order
    public boolean createRent(CartDTO cart, RenterDTO rent, String code, int totalDay) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        float totalPrice = 0;
        Date orderDate = Date.valueOf(LocalDate.now());

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "INSERT INTO tblRent(userID, totalPrice, createDate, status, renter, phoneNumber, email) "
                        + "VALUES(?,?,?,?,?,?,?)";
                String orderID[] = {"rentID"};
                for (CarDTO dto : cart.getCart().values()) {
                    totalPrice += dto.getQuantity() * dto.getPrice();
                }
                stm = conn.prepareStatement(sql, orderID);
                stm.setString(1, cart.getCustomName());
                stm.setFloat(2, totalDay * totalPrice);
                stm.setDate(3, orderDate);
                stm.setBoolean(4, true);
                stm.setString(5, rent.getRenter());
                stm.setString(6, rent.getPhoneNumber());
                stm.setString(7, rent.getEmail());

                stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    String orderDetailSql = "INSERT INTO tblRentDetail (rentID, carID, carBrand, carName, price, quantity, startDay, endDay, discountCode) "
                            + "VALUES (?,?,?,?,?,?,?,?,?)";
                    for (CarDTO dto : cart.getCart().values()) {
                        stm = conn.prepareStatement(orderDetailSql);
                        stm.setInt(1, rs.getInt(1));
                        stm.setString(2, dto.getCarID());
                        stm.setString(3, dto.getCarBrand());
                        stm.setString(4, dto.getCarName());
                        stm.setFloat(5, dto.getQuantity() * dto.getPrice());
                        stm.setInt(6, dto.getQuantity());
                        stm.setString(7, rent.getStartDay());
                        stm.setString(8, rent.getEndDay());
                        stm.setString(9, code);
                        stm.executeUpdate();
                    }
                    result = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    //Lay ngay tra xe
    public String getEndDayofCar(String carID) {
        //Kiem tra so luong XE 
        String date = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT endDay FROM tblRentDetail WHERE carID = ? ORDER BY endDay DESC OFFSET 1 ROWS "
                    + "FETCH NEXT 1 ROWS ONLY;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, carID);
            rs = stm.executeQuery();
            if (rs.next()) {
                date = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return date;
    }

    public String getEndDayofCar2(String carID) {
        //Kiem tra so luong XE 
        String date = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT TOP(1) endDay FROM tblRentDetail WHERE carID = ? ORDER BY endDay DESC";
            stm = conn.prepareStatement(sql);
            stm.setString(1, carID);
            rs = stm.executeQuery();
            if (rs.next()) {
                date = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return date;
    }

    public int getQuantityRentalCars(String startDay, String carID, String endDay) {
        //Kiem tra so luong XE 
        int num = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT quantity FROM tblRentDetail INNER JOIN tblRent ON tblRentDetail.rentID = tblRent.rentID "
                    + "WHERE ? <= endDay AND  endDay <= ? AND carID = ? AND tblRent.status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, startDay);
            stm.setString(2, endDay);
            stm.setString(3, carID);
            rs = stm.executeQuery();
            while (rs.next()) {
                num += rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return num;
    }

    public int getQuantityRentalCars2(String date, String carID) {
        //Kiem tra so luong XE 
        int num = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT quantity FROM tblRentDetail INNER JOIN tblRent ON tblRentDetail.rentID = tblRent.rentID "
                    + "WHERE startDay = ? AND carID = ? AND tblRent.status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, date);
            stm.setString(2, carID);
            rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return num;
    }

    //Lay lich su don hang 
    public ArrayList<HistoryDTO> getHistory(String carName, String userID, int rentID, String startDay) {
        ArrayList<HistoryDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT carID, carBrand, carName, price, quantity, startDay, endDay, discountCode "
                        + "from tblRentDetail INNER JOIN tblRent ON tblRentDetail.rentID = tblRent.rentID "
                        + "WHERE carName LIKE ? AND tblRent.userID = ? AND tblRent.rentID = ? AND CONVERT(VARCHAR(10),tblRentDetail.startDay,120) = ? "
                        + "order by tblRent.createDate";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carName + "%");
                stm.setString(2, userID);
                stm.setInt(3, rentID);
                stm.setString(4, startDay);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString(1);
                    String carBrand = rs.getString(2);
                    carName = rs.getString(3);
                    Float price = rs.getFloat(4);
                    int quantity = rs.getInt(5);
                    startDay = rs.getString(6);
                    String endDay = rs.getString(7);
                    String discountCode = rs.getString(8);

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new HistoryDTO(carID, carBrand, carName, price, quantity, startDay, endDay, discountCode));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public ArrayList<HistoryDTO> getHistory2(String carName, String userID, int rentID, String startDay) {
        ArrayList<HistoryDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT carID, carBrand, carName, price, quantity, startDay, endDay, discountCode "
                        + "from tblRentDetail INNER JOIN tblRent ON tblRentDetail.rentID = tblRent.rentID "
                        + "WHERE carName LIKE ? AND tblRent.userID = ? AND tblRent.rentID = ? AND tblRentDetail.startDay LIKE ? "
                        + "order by tblRent.createDate";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + carName + "%");
                stm.setString(2, userID);
                stm.setInt(3, rentID);
                stm.setString(4, "%" + startDay + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString(1);
                    String carBrand = rs.getString(2);
                    carName = rs.getString(3);
                    Float price = rs.getFloat(4);
                    int quantity = rs.getInt(5);
                    startDay = rs.getString(6);
                    String endDay = rs.getString(7);
                    String discountCode = rs.getString(8);

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new HistoryDTO(carID, carBrand, carName, price, quantity, startDay, endDay, discountCode));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    //Lay lich su don hang 2
    public ArrayList<OrderDTO> getOrder(String startDay, String userID) throws ParseException {
        ArrayList<OrderDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT rentID, totalPrice, createDate "
                        + "FROM tblRent "
                        + "WHERE CONVERT(VARCHAR(10),createDate,120) = ? AND userID = ? AND status = '1' "
                        + "ORDER BY createDate";
                stm = con.prepareStatement(sql);
                stm.setString(1, startDay);
                stm.setString(2, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int rentID = rs.getInt(1);
                    float price = rs.getFloat(2);

                    String date = rs.getString(3);

                    Calendar c = Calendar.getInstance();
                    c.setTime(sdf.parse(date));
                    c.add(Calendar.DATE, 2);
                    date = sdf.format(c.getTime());

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new OrderDTO(rentID, price, date));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public ArrayList<OrderDTO> getOrder2(String startDay, String userID) throws ParseException {
        ArrayList<OrderDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT rentID, totalPrice, createDate "
                        + "FROM tblRent "
                        + "WHERE createDate LIKE ? AND userID = ? AND status = '1' "
                        + "ORDER BY createDate";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + startDay + "%");
                stm.setString(2, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int rentID = rs.getInt(1);
                    float price = rs.getFloat(2);

                    String date = rs.getString(3);

                    Calendar c = Calendar.getInstance();
                    c.setTime(sdf.parse(date));
                    c.add(Calendar.DATE, 2);
                    date = sdf.format(c.getTime());

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new OrderDTO(rentID, price, date));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public void createFeedback(String userID, int detailID, String content, int point) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "INSERT INTO tblFeedback(userID, detailID, content, rating) "
                        + "VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, userID);
                stm.setInt(2, detailID);
                stm.setString(3, content);
                stm.setInt(4, point);
                stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                int feedbackID = 0;
                if (rs.next()) {
                    feedbackID = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getDetailID(int rentID, String carID) {
        //Kiem tra so luong XE 
        int detailID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT detailID FROM tblRentDetail WHERE rentID = ? AND carID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, rentID);
            stm.setString(2, carID);
            rs = stm.executeQuery();
            if (rs.next()) {
                detailID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return detailID;
    }

    public boolean checkFeedback(int detailID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "Select feedbackID FROM tblFeedback WHERE detailID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, detailID);
                rs = stm.executeQuery();
                result = rs.next();
            }
        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return result;
    }

    //Lay lich su feedback
    public ArrayList<FeedbackDTO> getFeedback(int detailID) {
        ArrayList<FeedbackDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT detailID, content, rating "
                        + "FROM tblFeedback "
                        + "WHERE detailID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, detailID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    detailID = rs.getInt(1);
                    String content = rs.getString(2);
                    int point = rs.getInt(3);

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new FeedbackDTO(detailID, content, point));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    //delete order
    public void deleteOrder(int rentID) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "UPDATE tblRent SET status = '0' WHERE rentID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, rentID);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getMaxQuantity(String carID, String startDate, String endDate) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = utils.DBUtils.getCon();
            if (conn != null) {
                String sql = "select c.carID, (c.quantity - ISNULL(SUM(s.quantity), 0)) as 'quantity' \n"
                        + "from tblCars c  left join (select d.carID, d.quantity, d.startDay,d.endDay \n"
                        + "from tblRentDetail d join tblRent o on d.rentID = o.rentID \n"
                        + "where ((d.startDay  between ? and ?) or (d.endDay  between ? and ?)) and o.[status] = 1) \n"
                        + "s on c.carID = s.carID \n"
                        + "group by c.carID, c.quantity \n"
                        + "having  c.carID = ?;";
                ps = conn.prepareStatement(sql);
                ps.setString(1, startDate);
                ps.setString(2, endDate);
                ps.setString(3, startDate);
                ps.setString(4, endDate);
                ps.setString(5, carID);

                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(2);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return -1;
    }
    
    public int calculateQuantityOrder(Date checkin, Date checkout, String carID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total = 0;
        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "SELECT SUM(d.quantity) AS TOTAL FROM tblRent O JOIN tblRentDetail D ON O.rentID = D.rentID \n" +
"WHERE ((d.startDay BETWEEN ? AND ?) OR d.startDay <=?) AND ((d.endDay BETWEEN ? AND ?) OR d.endDay >=?) AND d.carID= ? AND o.status=1 GROUP BY d.carID";
                stm = conn.prepareStatement(sql);
                stm.setDate(1, checkin);
                stm.setDate(2, checkout);
                stm.setDate(3, checkin);
                stm.setDate(4, checkin);
                stm.setDate(5, checkout);
                stm.setDate(6, checkout);
                stm.setString(7, carID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    total = rs.getInt(1);
                }
                return total;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return 0;
    }
}
