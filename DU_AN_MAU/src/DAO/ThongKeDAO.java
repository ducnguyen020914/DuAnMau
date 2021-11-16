/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 84985
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql,String[] cols,Object... args){
           List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vail = new Object[cols.length];
                for(int i = 0;i< cols.length;i++){
                    vail[i] = rs.getObject(cols[i]);
                   
                }
                 list.add(vail);
            }
                rs.getStatement().getConnection().close();
        } catch (Exception e) {
         throw new RuntimeException(e);
        }
        return list;
    }
    public List<Object[]> getBangDiem(Integer maKH){
        String sql = "{Call sp_BangDiem(?)}";
        String[] cols = {"MaNH","HoTen","Diem"};
        return this.getListOfArray(sql, cols, maKH);
    }public List<Object[]> getLuongNguoiHoc(){
        String sql = "{Call sp_ThongkeNguoiHoc}";
        String[] cols = {"Nam","SoNH","DauTien","CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDiemChuyenDe(){
        String sql = "{Call sp_ThongkeDiem}";
        String[] cols = {"TenCD","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThu(int nam){
        String sql = "{Call sp_ThongKeDoanhThu(?)}";
        String[] cols = {"ChuyenDe","SoKH","SoHV","HocPhi","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
}

