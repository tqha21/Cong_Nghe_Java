package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai06LoginForm extends JFrame {

    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    private JComboBox<String> cboVaiTro;
    private JCheckBox chkHienThiMatKhau;
    private JButton btnDangNhap;

    public Bai06LoginForm() {
        setTitle("Bài 6 - Form đăng nhập");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addEvents();
    }

    private void initComponents() {
        JLabel lblTaiKhoan = new JLabel("Tài khoản:");
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        JLabel lblVaiTro = new JLabel("Vai trò:");

        txtTaiKhoan = new JTextField();
        txtMatKhau = new JPasswordField();

        cboVaiTro = new JComboBox<>(
                new String[]{"Admin", "User"}
        );

        chkHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");

        btnDangNhap = new JButton("Đăng nhập");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        panel.add(lblTaiKhoan);
        panel.add(txtTaiKhoan);

        panel.add(lblMatKhau);
        panel.add(txtMatKhau);

        panel.add(lblVaiTro);
        panel.add(cboVaiTro);

        panel.add(new JLabel(""));
        panel.add(chkHienThiMatKhau);

        panel.add(new JLabel(""));
        panel.add(btnDangNhap);

        add(panel);
    }

    private void addEvents() {
        btnDangNhap.addActionListener(e -> dangNhap());

        chkHienThiMatKhau.addActionListener(e -> {
            if (chkHienThiMatKhau.isSelected()) {
                txtMatKhau.setEchoChar((char) 0);
            } else {
                txtMatKhau.setEchoChar('•');
            }
        });
    }

    private void dangNhap() {
        String taiKhoan = txtTaiKhoan.getText().trim();
        String matKhau = new String(txtMatKhau.getPassword());
        String vaiTro = (String) cboVaiTro.getSelectedItem();

        if (taiKhoan.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập tài khoản!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
            txtTaiKhoan.requestFocus();
            return;
        }

        if (matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập mật khẩu!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
            txtMatKhau.requestFocus();
            return;
        }

        boolean dangNhapDung =
                (taiKhoan.equals("admin")
                        && matKhau.equals("123456")
                        && vaiTro.equals("Admin"))
                        ||
                        (taiKhoan.equals("user")
                                && matKhau.equals("123456")
                                && vaiTro.equals("User"));

        if (dangNhapDung) {
            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập thành công!\n"
                            + "Chào mừng " + taiKhoan
                            + " - Vai trò: " + vaiTro,
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập thất bại!\n"
                            + "Tài khoản, mật khẩu hoặc vai trò không đúng.",
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai06LoginForm form = new Bai06LoginForm();
            form.setVisible(true);
        });
    }
}