 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 84985
 */
public class JdbcHelper {

    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// Driver
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=EduSys";//Lấy Đường dẫn CSDL
    static String user = "sa";//Tên Tài khoản CSDL
    static String pass = "020914";//Mật khẩu CSDL

    static {
        try {
            Class.forName(driver);// Nạp Driver
        } catch (Exception e) {
            throw new RuntimeException(); //throw các lỗi khi chạy CT, VD không có return khi try bị lỗi
        }
    }

    public static PreparedStatement getPrepare(String sql, Object... args) throws SQLException { 
        //Mở Kết Nối 
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps;
        //Nếu câu lệnh sql bắt đầu bằng dấu { thì câu lệnh Này là Thủ Tục ProC
        //Ngược lại thì câu lệnh SQl này là câu lệnh bình thường
        if (sql.startsWith("{")) {
              //có thể gán biến kiểu PreparedStatement là prepareCall (CallableStatement)
            ps = con.prepareCall(sql);
        } else {
            ps = con.prepareStatement(sql);
        }
        //Set giá trị điều kiện vào sql
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps;
    }

    //Xây dựng Hàm Query trả về 1 tập dữ liệu 
    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement ps = getPrepare(sql, args);
        return ps.executeQuery();
    }

    //Xây dựng Hàm Update  trả về số bản ghi được thay đổi
    public static int update(String sql, Object... args) {
        try {
            PreparedStatement ps = getPrepare(sql, args);
            try {
                return ps.executeUpdate();
            }finally{
                ps.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e); //throw các lỗi khi chạy CT, VD không có return khi try bị lỗi
        }
    }

    //Xây dựng hàm Values khi Giá trị trả về lại 1 giá trị
    public static Object Value(String sql, Object... args) {
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e); //throw các lỗi khi chạy CT, VD không có return khi try bị lỗi
        }
    }
}
