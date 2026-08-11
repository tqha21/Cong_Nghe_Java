package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;

public class PrimeSumFrame extends JFrame {

    private JTextField txtN;
    private JButton btnCalculate;
    private JLabel lblResult;
    private JProgressBar progressBar;

    public PrimeSumFrame() {
        initComponents();
        addEvents();
    }

    // Khởi tạo giao diện
    private void initComponents() {

        setTitle("Tính tổng số nguyên tố");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Khởi tạo các component
        txtN = new JTextField(15);
        btnCalculate = new JButton("Tính");
        lblResult = new JLabel("Kết quả sẽ hiển thị ở đây");
        progressBar = new JProgressBar(0, 100);

        // Hiển thị phần trăm trên ProgressBar
        progressBar.setStringPainted(true);

        // Panel nhập N
        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Nhập N:"));
        inputPanel.add(txtN);
        inputPanel.add(btnCalculate);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(progressBar);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(lblResult);

        add(mainPanel);
    }

    // Khởi tạo sự kiện
    private void addEvents() {

        btnCalculate.addActionListener(e -> calculatePrimeSum());
    }

    // Kiểm tra số nguyên tố
    private boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Tính tổng số nguyên tố nhỏ hơn N
    private void calculatePrimeSum() {

        int n;

        try {

            n = Integer.parseInt(txtN.getText().trim());

            if (n <= 2) {

                JOptionPane.showMessageDialog(
                        this,
                        "N phải lớn hơn 2"
                );

                return;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập số nguyên hợp lệ"
            );

            return;
        }

        // Khóa nút trong khi tính
        btnCalculate.setEnabled(false);

        // Reset ProgressBar
        progressBar.setValue(0);

        lblResult.setText("Đang tính...");

        SwingWorker<Long, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Long doInBackground() {

                        long sum = 0;

                        for (int i = 2; i < n; i++) {

                            if (isPrime(i)) {
                                sum += i;
                            }

                            int progress =
                                    (int) ((i * 100.0) / n);

                            setProgress(progress);
                        }

                        return sum;
                    }

                    @Override
                    protected void done() {

                        try {

                            long result = get();

                            lblResult.setText(
                                    "Tổng các số nguyên tố nhỏ hơn "
                                            + n + " = " + result
                            );

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Có lỗi khi tính toán"
                            );
                        }

                        progressBar.setValue(100);
                        btnCalculate.setEnabled(true);
                    }
                };

        // Cập nhật ProgressBar
        worker.addPropertyChangeListener(evt -> {

            if ("progress".equals(evt.getPropertyName())) {

                progressBar.setValue(
                        (int) evt.getNewValue()
                );
            }
        });

        worker.execute();
    }

    // Chạy chương trình
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            PrimeSumFrame frame = new PrimeSumFrame();

            frame.setVisible(true);
        });
    }
}

