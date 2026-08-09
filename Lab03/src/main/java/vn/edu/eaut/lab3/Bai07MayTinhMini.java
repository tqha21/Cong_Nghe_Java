package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai07MayTinhMini extends JFrame {

    private JTextField txtSo1;
    private JTextField txtSo2;
    private JTextField txtKetQua;
    private JTextArea txtLichSu;

    private JButton btnCong;
    private JButton btnTru;
    private JButton btnNhan;
    private JButton btnChia;
    private JButton btnClear;

    public Bai07MayTinhMini() {
        setTitle("Bài 7 - Máy tính mini");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addEvents();
    }

    private void initComponents() {
        JLabel lblSo1 = new JLabel("Số thứ nhất:");
        JLabel lblSo2 = new JLabel("Số thứ hai:");
        JLabel lblKetQua = new JLabel("Kết quả:");

        txtSo1 = new JTextField();
        txtSo2 = new JTextField();

        txtKetQua = new JTextField();
        txtKetQua.setEditable(false);

        btnCong = new JButton("Cộng");
        btnTru = new JButton("Trừ");
        btnNhan = new JButton("Nhân");
        btnChia = new JButton("Chia");
        btnClear = new JButton("Clear");

        txtLichSu = new JTextArea();
        txtLichSu.setEditable(false);

        JPanel panelNhap = new JPanel(new GridLayout(3, 2, 10, 10));
        panelNhap.setBorder(
                BorderFactory.createTitledBorder("Nhập dữ liệu")
        );

        panelNhap.add(lblSo1);
        panelNhap.add(txtSo1);

        panelNhap.add(lblSo2);
        panelNhap.add(txtSo2);

        panelNhap.add(lblKetQua);
        panelNhap.add(txtKetQua);

        JPanel panelButton = new JPanel(
                new GridLayout(1, 5, 10, 10)
        );

        panelButton.add(btnCong);
        panelButton.add(btnTru);
        panelButton.add(btnNhan);
        panelButton.add(btnChia);
        panelButton.add(btnClear);

        JScrollPane scrollPane = new JScrollPane(txtLichSu);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Lịch sử phép tính")
        );

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        mainPanel.add(panelNhap, BorderLayout.NORTH);
        mainPanel.add(panelButton, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addEvents() {
        btnCong.addActionListener(e -> tinhToan("+"));
        btnTru.addActionListener(e -> tinhToan("-"));
        btnNhan.addActionListener(e -> tinhToan("*"));
        btnChia.addActionListener(e -> tinhToan("/"));

        btnClear.addActionListener(e -> clear());
    }

    private void tinhToan(String phepToan) {
        try {
            double so1 = Double.parseDouble(txtSo1.getText().trim());
            double so2 = Double.parseDouble(txtSo2.getText().trim());

            double ketQua;

            switch (phepToan) {
                case "+":
                    ketQua = so1 + so2;
                    break;

                case "-":
                    ketQua = so1 - so2;
                    break;

                case "*":
                    ketQua = so1 * so2;
                    break;

                case "/":
                    if (so2 == 0) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Không thể chia cho 0!",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    ketQua = so1 / so2;
                    break;

                default:
                    return;
            }

            txtKetQua.setText(String.valueOf(ketQua));

            String lichSu = so1 + " " + phepToan + " "
                    + so2 + " = " + ketQua;

            txtLichSu.append(lichSu + "\n");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập số hợp lệ!\nVí dụ: 10 hoặc 10.5",
                    "Lỗi dữ liệu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clear() {
        txtSo1.setText("");
        txtSo2.setText("");
        txtKetQua.setText("");

        txtSo1.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai07MayTinhMini form = new Bai07MayTinhMini();
            form.setVisible(true);
        });
    }
}