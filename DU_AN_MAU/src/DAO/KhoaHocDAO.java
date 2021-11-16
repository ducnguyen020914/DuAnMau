/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import MoDel.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84985
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{
    private static KhoaHoc getModel(ResultSet rs) throws SQLException{
        KhoaHoc kh = new KhoaHoc();
        kh.setMaKH(rs.getInt("MaKH"));
        kh.setMaCD(rs.getString("MaCD"));
        kh.setHocPhi(rs.getFloat("HocPhi"));
        kh.setThoiLuong(rs.getInt("ThoiLuong"));
        kh.setNgayKG(rs.getDate("NgayKG"));
        kh.setGhiChu(rs.getString("GhiChu"));
        kh.setMaNV(rs.getString("MaNV"));
        kh.setNgayTao(rs.getDate("NgayTao"));
        return kh;
    }
    @Override
    public void insert(KhoaHoc entity) {
        String sql = "Insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) "
                + "Values(?,?,?,?,?,?,?)";
        int i = JdbcHelper.update(sql, entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),
                entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao());
    }

    @Override 
    public void delete(Integer entity) {
       String sql = "Delete from KhoaHOc where MaKH=?";
       int i = JdbcHelper.update(sql, entity);
    }

    @Override
    public void update(KhoaHoc entity) {
      String sql = "Update KHoaHOc set MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG=?,GhiChu=?,MaNV=?,NgayTao=? where MaKH=?";
      int i = JdbcHelper.update(sql,entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),
                entity.getNgayKG(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao(),entity.getMaKH());
    }

    @Override
    public List<KhoaHoc> SelectALL() {
       String sql = "select * from KhoaHOc";
       return selectBySQL(sql);
    }

    @Override
    public KhoaHoc SelectByID(Integer entity) {
        try {
            String sql ="select * from KhoaHOc where MaKH = ?";
             List<KhoaHoc> list = selectBySQL(sql, entity);
             return list.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<KhoaHoc> selectBySQL(String sql, Object... args) {
        try {
            List<KhoaHoc> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                KhoaHoc kh = KhoaHocDAO.getModel(rs);
                list.add(kh);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<KhoaHoc> selectByChuyenDe(String MaCD){
        String sql = "select * from KhoaHoc where MaCD = ?";
        return selectBySQL(sql, MaCD);
    }
    public List<Integer> selectbyYear(){
        String sql = "Select Distinct year(NgayKG) from KhoaHoc order by year(NgayKG) desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            System.out.println(list.size());
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
