/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static java.lang.Math.log;
import static java.lang.StrictMath.log;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author SE140066
 */
public class DBUtils {
//    public static Connection getCon() throws SQLException, ClassNotFoundException {
//        Connection conn = null;
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment1_TranQuangVu;user=sa;password=123;";
//        conn = DriverManager.getConnection(url);
//
//        return conn;
//    }
    public static Connection getCon(){
        Connection conn = null;
        try {
            Context context = (Context) new InitialContext();
            Context end = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) end.lookup("dbCon");
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            System.out.println("Error at DBUtils: " + ex);
        }
        return conn;
    }
    
    public static boolean checkDateBetween(String startDate, String endDate, String startBetween, String endBetween) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sdate = sdf.parse(startDate);
        Date edate = sdf.parse(endDate);
        Date sbdate = sdf.parse(startBetween);
        Date ebdate = sdf.parse(endBetween);

        if (sbdate.getTime() >= sdate.getTime() && sbdate.getTime() <= edate.getTime()) {
            return true;
        } else if (ebdate.getTime() >= sdate.getTime() && ebdate.getTime() <= edate.getTime()) {
            return true;
        } else if (sdate.getTime() >= sbdate.getTime() && sdate.getTime() <= ebdate.getTime()) {
            return true;
        } else if (edate.getTime() >= sbdate.getTime() && edate.getTime() <= ebdate.getTime()) {
            return true;
        } else {
            return false;
        }
    }
}
