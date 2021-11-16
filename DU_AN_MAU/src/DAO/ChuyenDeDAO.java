/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import MoDel.ChuyenDe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84985
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    private static ChuyenDe getModel(ResultSet rs) throws SQLException {
        ChuyenDe cd = new ChuyenDe();
        cd.setMaCD(rs.getString("maCD"));
        cd.setTenCD(rs.getString("TenCD"));
        cd.setHocPhi(rs.getFloat("HocPhi"));
        cd.setThoiLuong(rs.getInt("ThoiLuong"));
        cd.setHinh(rs.getString("hinh"));
        cd.setMoTa(rs.getString("Mota"));
        return cd;
    }

    @Override
    public void insert(ChuyenDe entity) {
        String sql = "Insert into ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,hinh,MoTa) "
                + "Values(?,?,?,?,?,?)";
        int i = JdbcHelper.update(sql, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(),
                entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void delete(String entity) {
        String sql = "delete from ChuyenDe where MaCD = ?";
        int i = JdbcHelper.update(sql, entity);
    }

    @Override
    public void update(ChuyenDe entity) {
        String sql = "Update ChuyenDe set TenCD = ?,HocPhi = ?,ThoiLuong = ?,hinh = ?,MoTa = ?"
                + " where maCD=?";
        int i = JdbcHelper.update(sql, entity.getTenCD(), entity.getHocPhi(),
                entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public List<ChuyenDe> SelectALL() {
        String sql = "Select * from ChuyenDe";
        return selectBySQL(sql);
    }

    @Override
    public ChuyenDe SelectByID(String entity) {
        try {
            ChuyenDe cd = null;
            String sql = "Select * from ChuyenDe where MaCD = ?";
            ResultSet rs = JdbcHelper.query(sql, entity);
            if (rs.next()) {
                cd = ChuyenDeDAO.getModel(rs);
            }
            rs.getStatement().close();
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ChuyenDe> selectBySQL(String sql, Object... args) {
        try {
            List<ChuyenDe> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ChuyenDe cd = ChuyenDeDAO.getModel(rs);
                list.add(cd);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
