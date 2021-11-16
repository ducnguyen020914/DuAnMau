/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import MoDel.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84985
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    private static NguoiHoc getResultSet(ResultSet rs) throws SQLException {
        NguoiHoc nh = new NguoiHoc();
        nh.setMaNH(rs.getString("MaNH"));
        nh.setHoTen(rs.getString("hoTen"));
        nh.setGioiTinh(rs.getBoolean("GioiTinh"));
        nh.setNgaySinh(rs.getDate("NgaySinh"));
        nh.setDienThoai(rs.getString("DienThoai"));
        nh.setEmail(rs.getString("Email"));
        nh.setGhiChu(rs.getString("GhiChu"));
        nh.setMaNV(rs.getString("MaNV"));
        nh.setNgayDK(rs.getDate("NgayDK"));
        return nh;
    }

    @Override
    public void insert(NguoiHoc entity) {
        String sql = "insert into NguoiHoc(MaNH,HoTen,GioiTinh,NgaySinh,DienThoai,Email,ghiChu,MaNV) "
                + "Values(?,?,?,?,?,?,?,?)";
        int i = JdbcHelper.update(sql, entity.getMaNH(), entity.getHoTen(), entity.isGioiTinh(),
                entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public void delete(String entity) {
        String sql = "delete from NguoiHoc where MaNH = ?";
        int i = JdbcHelper.update(sql, entity);
    }

    @Override
    public void update(NguoiHoc entity) {
        String sql = "update NguoiHoc set HoTen=?,GioiTinh=?,NgaySinh=?,DienThoai=?,"
                + "Email=?,ghiChu=?,MaNV=? where MaNH=?";
        int i = JdbcHelper.update(sql, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(),
                entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getMaNH());
    }

    @Override
    public List<NguoiHoc> SelectALL() {
        String sql = "select * from NguoiHoc";
        return selectBySQL(sql);
    }

    @Override
    public NguoiHoc SelectByID(String entity) {
        try {
            NguoiHoc nh = null;
              String sql = "select * from NguoiHoc where MaNh = ?";
              ResultSet rs = JdbcHelper.query(sql,entity);
              if(rs.next()){
                  nh = getResultSet(rs);
              }
              rs.getStatement().close();
              return nh;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NguoiHoc> selectBySQL(String sql, Object... args) {
        try {
            List<NguoiHoc> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc nh = getResultSet(rs);
                list.add(nh);
            }
            rs.getStatement().close();
            
            return list;
        } catch (Exception e) {
          
            throw new RuntimeException(e);
        }
    }

    public List<NguoiHoc> selectByHoTen(String keyword) {
        String sql = "select * from NguoiHoc where HoTen like ?";
        return selectBySQL(sql, "%" + keyword + "%");

    }
    public List<NguoiHoc> selectNotInCourse(int maKH,String keyword){
        String sql = "select * from NguoiHoc where HoTen like ? and "
                + " MaNH not in(select MaNH from HocVien where MaKH = ?)";
        return selectBySQL(sql, "%"+keyword+"%",maKH);
    }

}
