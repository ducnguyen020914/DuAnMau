 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import MoDel.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84985
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    private NhanVien getmodel(ResultSet rs) throws SQLException{
       NhanVien model = new NhanVien();
       model.setMaNV(rs.getString("MaNV"));
       model.setMatKhau(rs.getString("MatKhau"));
       model.setHoTen(rs.getString("HoTen"));
       model.setVaiTro(rs.getBoolean("VaiTro"));
       return model;
    }
    @Override
    public void insert(NhanVien entity) {
        String sql = "Insert into NhanVien(MaNV,MatKhau,HoTen,VaiTro) "
                + "Values(?,?,?,?)";
        int i = JdbcHelper.update(sql, entity.getMaNV(),entity.getMatKhau(),
                entity.getHoTen(),entity.isVaiTro());
    }

    @Override
    public void delete(String entity) {
      String sql = "delete from NhanVien where MaNV = ?";
      int i = JdbcHelper.update(sql, entity);
    }

    @Override
    public void update(NhanVien entity) {
       String sql =  "Update NhanVien set MatKhau=?,HoTen=?,VaiTro=? where MaNV=?";
       int i = JdbcHelper.update(sql,entity.getMatKhau(),
                entity.getHoTen(),entity.isVaiTro(),entity.getMaNV());
    }

    @Override
    public List<NhanVien> SelectALL() {
        try {
            String sql = "Select * from NhanVien";
            return selectBySQL(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NhanVien SelectByID(String entity) {
         try {
            NhanVien nv = null;
              String sql = "select * from NhanVien where MaNV = ?";
              ResultSet rs = JdbcHelper.query(sql,entity);
              if(rs.next()){
                  nv = getmodel(rs);
              }
              rs.getStatement().close();
              return nv;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
         try {
            List<NhanVien> list = new ArrayList<>();           
              ResultSet rs = JdbcHelper.query(sql,args);
              while(rs.next()){
                 NhanVien nv = getmodel(rs);
                 list.add(nv);
              }
              rs.getStatement().close();
              return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
