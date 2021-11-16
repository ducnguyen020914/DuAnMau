
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.NhanVienDAO;
import Helper.Auth;
import Helper.MsgBox;
import Helper.UtilityHelper;
import MoDel.NhanVien;
import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 84985
 */
public class NhanVienDialog extends javax.swing.JDialog {

    NhanVienDAO nvdao = new NhanVienDAO();
    int row = -1;

    public NhanVienDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.init();
    }

    void init() {
        this.setLocationRelativeTo(null);
        this.row = -1;
        this.filltable();
        this.updateStatus();
        this.rdonv.setSelected(true);
    }

    NhanVien getform() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(this.txtmanv.getText());
        nv.setMatKhau(this.txtmk.getText());
        nv.setHoTen(this.txthoten.getText());
        nv.setVaiTro(this.rdonv.isSelected() ? false : true);
        return nv;
    }

    void setform(NhanVien nv) {
        this.txtmanv.setText(nv.getMaNV());
        this.txtmk.setText(nv.getMatKhau());
        this.txtcfmk.setText(nv.getMatKhau());
        this.txthoten.setText(nv.getHoTen());
        if (nv.isVaiTro()) {
            this.rdotp.setSelected(true);
        } else {
            this.rdonv.setSelected(true);
        }
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == this.tblnv.getRowCount() - 1);
        //Cập nhật trang thái nut thêm sữa xóa
        this.txtmanv.setEditable(!edit);
        this.btnthem.setEnabled(!edit);
        this.btnsua.setEnabled(edit);
        this.btnxoa.setEnabled(edit);
        //Cập nhật trang thái nút điều hướng
        this.btnback.setEnabled(edit && !first);
        this.btnprevious.setEnabled(edit && !first);
        this.btnnext.setEnabled(edit && !last);
        this.btnlast.setEnabled(edit && !last);
    }

    void filltable() {
        try {
            DefaultTableModel mol = (DefaultTableModel) this.tblnv.getModel();
            mol.setRowCount(0);
            List<NhanVien> listnv = this.nvdao.SelectALL();
            for (NhanVien x : listnv) {
                mol.addRow(new Object[]{
                    x.getMaNV(), x.getHoTen(), x.getMatKhau(), x.isVaiTro() ? "Trưởng Phòng" : "Nhân Viên"
                });
            }
        } catch (Exception e) {
           MsgBox.alert(this, "Lỗi dữ liệu");
        }
    }

    void edit() {
        String manv = (String) this.tblnv.getValueAt(this.row, 0);
        this.tblnv.setRowSelectionInterval(row, row);
        NhanVien nv = this.nvdao.SelectByID(manv);
        this.setform(nv);
        this.tabs.setSelectedIndex(0);
        this.updateStatus();
    }

    void CLearForm() {
        NhanVien nv = new NhanVien();
        this.setform(nv);
        this.row = -1;
        this.updateStatus();
    }

    boolean checkform() {
        this.txtmanv.setBackground(Color.white);
        this.txtcfmk.setBackground(Color.white);
        this.txthoten.setBackground(Color.white);
        this.txtmk.setBackground(Color.white);
        if (UtilityHelper.CheckNull(this.txtmanv, "Mã Nhân viên")
                || UtilityHelper.CheckNull(this.txtmk, "Mật Khẩu")
                || UtilityHelper.CheckNull(this.txthoten, "Họ tên")) {
            return true;
        }
        return false;
    }

    void insert() {
        if (!this.checkform()) {
            if(nvdao.SelectByID(this.txtmanv.getText())!=null){
                MsgBox.alert(this, "Mã nhân viên đã tồn tại");
            }else{
            NhanVien nv = this.getform();
            String cfmk = this.txtcfmk.getText();
            if (!cfmk.equals(nv.getMatKhau())) {
                MsgBox.alert(this, "Mật Khẩu xác nhận không chính xác");
            } else {
                try {
                    this.nvdao.insert(nv);
                    this.filltable();
                    this.CLearForm();
                    MsgBox.alert(this, "Thêm Thành công");
                } catch (Exception e) {
                    MsgBox.alert(this, "Thêm Thất Bại");
                }
            }
        }
        }
    }

    void update() {
        if (!this.checkform()) {
            NhanVien nv = this.getform();
            String cfmk = this.txtcfmk.getText();
            if (!cfmk.equals(nv.getMatKhau())) {
                MsgBox.alert(this, "Mật Khẩu xác nhận không chính xác");
            } else {
                try {
                    this.nvdao.update(nv);
                    this.filltable();
                    MsgBox.alert(this, "Update Thành công");
                } catch (Exception e) {
                    MsgBox.alert(this, "Update Thất Bại");
                }
            }
        }
    }

    void delete() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Chỉ Trưởng phòng mới được xóa");
        } else {
            String manv = this.txtmanv.getText();
            if (manv.equals(Auth.user.getMaNV())) {
                MsgBox.alert(this, "Bạn không được xóa chính bạn");
            } else if (MsgBox.confirm(this, "Bạn có chắc chắn muốn xóa không?")) {
                try {
                    this.nvdao.delete(manv);
                    this.filltable();
                    this.CLearForm();
                    MsgBox.alert(this, "Xóa Thành công");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa Thất bại");
                }
            }
        }
    }

    void back() {
        this.row = 0;
        this.edit();
        this.updateStatus();
    }

    void previous() {
        if (this.row > 0) {
            this.row--;
            this.edit();
            this.updateStatus();
        }
    }

    void next() {
        if (this.row < this.tblnv.getRowCount() - 1) {
            this.row++;
            this.edit();
            this.updateStatus();
        }
    }

    void last() {
        this.row = this.tblnv.getRowCount() - 1;
        this.edit();
        this.updateStatus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtcfmk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnmoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnback = new javax.swing.JButton();
        btnprevious = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        rdonv = new javax.swing.JRadioButton();
        rdotp = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnv = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý Nhân Viên");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setText("Mã Nhân Viên");

        jLabel3.setText("Mật Khẩu");

        jLabel4.setText("Xác Nhân Mật Khẩu");

        jLabel5.setText("Họ tên");

        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add to basket.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });
        jPanel3.add(btnthem);

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Notes.png"))); // NOI18N
        btnsua.setText("Sữa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnsua);

        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnxoa);

        btnmoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Unordered list.png"))); // NOI18N
        btnmoi.setText("Mới");
        btnmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnmoi);

        btnback.setBackground(new java.awt.Color(255, 102, 0));
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dau.png"))); // NOI18N
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        jPanel4.add(btnback);

        btnprevious.setBackground(new java.awt.Color(0, 51, 255));
        btnprevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lui.png"))); // NOI18N
        btnprevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviousActionPerformed(evt);
            }
        });
        jPanel4.add(btnprevious);

        btnnext.setBackground(new java.awt.Color(0, 0, 255));
        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tien.png"))); // NOI18N
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });
        jPanel4.add(btnnext);

        btnlast.setBackground(new java.awt.Color(255, 153, 0));
        btnlast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cuoi.png"))); // NOI18N
        btnlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlastActionPerformed(evt);
            }
        });
        jPanel4.add(btnlast);

        jLabel6.setText("Vai trò");

        buttonGroup1.add(rdonv);
        rdonv.setText("Nhân Viên");

        buttonGroup1.add(rdotp);
        rdotp.setText("Trưởng Phòng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdonv)
                        .addGap(41, 41, 41)
                        .addComponent(rdotp))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txthoten)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtcfmk)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addComponent(txtmanv)
                                        .addComponent(jLabel3)
                                        .addComponent(txtmk, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel5)))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcfmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdonv)
                            .addComponent(rdotp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        tabs.addTab("CẬP NHẬT", new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png")), jPanel1); // NOI18N

        tblnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Mật Khẩu", "Vai Trò"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnvMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnv);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );

        tabs.addTab("DANH SÁCH", new javax.swing.ImageIcon(getClass().getResource("/icon/List.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblnvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnvMouseClicked
        if (evt.getClickCount() == 2) {
            this.row = this.tblnv.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblnvMouseClicked

    private void btnmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmoiActionPerformed
        this.CLearForm();
    }//GEN-LAST:event_btnmoiActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        this.insert();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        this.update();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.back();
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnpreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreviousActionPerformed
        this.previous();
    }//GEN-LAST:event_btnpreviousActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        this.next();
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlastActionPerformed
        this.last();
    }//GEN-LAST:event_btnlastActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhanVienDialog dialog = new NhanVienDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnmoi;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprevious;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdonv;
    private javax.swing.JRadioButton rdotp;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblnv;
    private javax.swing.JTextField txtcfmk;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtmk;
    // End of variables declaration//GEN-END:variables
}
