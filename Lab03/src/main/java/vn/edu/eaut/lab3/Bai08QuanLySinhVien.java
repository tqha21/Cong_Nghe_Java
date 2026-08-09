package vn.edu.eaut.lab3;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bai08QuanLySinhVien extends JFrame {

    private JTextField txtMaSinhVien;
    private JTextField txtHoTen;
    private JTextField txtDiemTrungBinh;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    private JTable table;
    private DefaultTableModel tableModel;

    private List<Student> danhSachSinhVien;

    public Bai08QuanLySinhVien() {
        setTitle("Bài 8 - Quản lý sinh viên");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        danhSachSinhVien = new ArrayList<>();

        initComponents();
        addEvents();
    }

    private void initComponents() {

        JLabel lblMaSinhVien = new JLabel("Mã sinh viên:");
        JLabel lblHoTen = new JLabel("Họ tên:");
        JLabel lblDiemTrungBinh = new JLabel("Điểm trung bình:");

        txtMaSinhVien = new JTextField();
        txtHoTen = new JTextField();
        txtDiemTrungBinh = new JTextField();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        JPanel panelInput = new JPanel(
                new GridLayout(3, 2, 10, 10)
        );

        panelInput.setBorder(
                BorderFactory.createTitledBorder(
                        "Thông tin sinh viên"
                )
        );

        panelInput.add(lblMaSinhVien);
        panelInput.add(txtMaSinhVien);

        panelInput.add(lblHoTen);
        panelInput.add(txtHoTen);

        panelInput.add(lblDiemTrungBinh);
        panelInput.add(txtDiemTrungBinh);

        JPanel panelButton = new JPanel(
                new FlowLayout()
        );

        panelButton.add(btnThem);
        panelButton.add(btnSua);
        panelButton.add(btnXoa);
        panelButton.add(btnLamMoi);

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.add(panelInput, BorderLayout.CENTER);
        panelTop.add(panelButton, BorderLayout.SOUTH);

        String[] columns = {
                "Mã sinh viên",
                "Họ tên",
                "Điểm trung bình",
                "Xếp loại"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Danh sách sinh viên"
                )
        );

        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addEvents() {

        btnThem.addActionListener(e -> themSinhVien());

        btnSua.addActionListener(e -> suaSinhVien());

        btnXoa.addActionListener(e -> xoaSinhVien());

        btnLamMoi.addActionListener(e -> lamMoi());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();

                if (row >= 0) {
                    txtMaSinhVien.setText(
                            tableModel.getValueAt(row, 0).toString()
                    );

                    txtHoTen.setText(
                            tableModel.getValueAt(row, 1).toString()
                    );

                    txtDiemTrungBinh.setText(
                            tableModel.getValueAt(row, 2).toString()
                    );
                }
            }
        });
    }

    private void themSinhVien() {

        Student student = layThongTinTuForm();

        if (student == null) {
            return;
        }

        for (Student sv : danhSachSinhVien) {
            if (sv.getMaSinhVien()
                    .equalsIgnoreCase(student.getMaSinhVien())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Mã sinh viên đã tồn tại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        danhSachSinhVien.add(student);

        hienThiDanhSach();

        JOptionPane.showMessageDialog(
                this,
                "Thêm sinh viên thành công!"
        );

        lamMoi();
    }

    private void suaSinhVien() {

        int row = table.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn sinh viên cần sửa!"
            );
            return;
        }

        Student student = layThongTinTuForm();

        if (student == null) {
            return;
        }

        int modelRow = table.convertRowIndexToModel(row);

        Student oldStudent = danhSachSinhVien.get(modelRow);

        oldStudent.setMaSinhVien(
                student.getMaSinhVien()
        );

        oldStudent.setHoTen(
                student.getHoTen()
        );

        oldStudent.setDiemTrungBinh(
                student.getDiemTrungBinh()
        );

        hienThiDanhSach();

        JOptionPane.showMessageDialog(
                this,
                "Cập nhật sinh viên thành công!"
        );

        lamMoi();
    }

    private void xoaSinhVien() {

        int row = table.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn sinh viên cần xóa!"
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa sinh viên này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int modelRow = table.convertRowIndexToModel(row);

        danhSachSinhVien.remove(modelRow);

        hienThiDanhSach();

        lamMoi();

        JOptionPane.showMessageDialog(
                this,
                "Xóa sinh viên thành công!"
        );
    }

    private Student layThongTinTuForm() {

        String maSinhVien =
                txtMaSinhVien.getText().trim();

        String hoTen =
                txtHoTen.getText().trim();

        String diemText =
                txtDiemTrungBinh.getText().trim();

        if (maSinhVien.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập mã sinh viên!"
            );
            txtMaSinhVien.requestFocus();
            return null;
        }

        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập họ tên!"
            );
            txtHoTen.requestFocus();
            return null;
        }

        if (diemText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập điểm trung bình!"
            );
            txtDiemTrungBinh.requestFocus();
            return null;
        }

        try {

            double diem =
                    Double.parseDouble(diemText);

            if (diem < 0 || diem > 10) {
                JOptionPane.showMessageDialog(
                        this,
                        "Điểm phải nằm trong khoảng từ 0 đến 10!"
                );
                return null;
            }

            return new Student(
                    maSinhVien,
                    hoTen,
                    diem
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Điểm trung bình phải là số hợp lệ!"
            );

            txtDiemTrungBinh.requestFocus();

            return null;
        }
    }

    private void hienThiDanhSach() {

        tableModel.setRowCount(0);

        for (Student student : danhSachSinhVien) {

            Object[] row = {
                    student.getMaSinhVien(),
                    student.getHoTen(),
                    student.getDiemTrungBinh(),
                    student.getXepLoai()
            };

            tableModel.addRow(row);
        }
    }

    private void lamMoi() {

        txtMaSinhVien.setText("");
        txtHoTen.setText("");
        txtDiemTrungBinh.setText("");

        table.clearSelection();

        txtMaSinhVien.requestFocus();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Bai08QuanLySinhVien form =
                    new Bai08QuanLySinhVien();

            form.setVisible(true);
        });
    }
}