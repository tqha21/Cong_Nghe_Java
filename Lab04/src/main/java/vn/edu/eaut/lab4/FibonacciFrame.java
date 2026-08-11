package vn.edu.eaut.lab4;
import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciFrame extends JFrame {

    private JTextField txtN;
    private JButton btnFind;
    private JProgressBar progressBar;
    private JLabel lblResult;

    public FibonacciFrame() {
        setTitle("Tính Fibonacci");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addEvents();
    }

    private void initComponents() {
        txtN = new JTextField(15);
        btnFind = new JButton("Tìm Fibonacci");
        progressBar = new JProgressBar();
        lblResult = new JLabel("Kết quả sẽ hiển thị ở đây");

        progressBar.setStringPainted(true);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nhập N:"));
        inputPanel.add(txtN);
        inputPanel.add(btnFind);

        panel.add(inputPanel);
        panel.add(progressBar);
        panel.add(lblResult);

        add(panel);
    }

    private void addEvents() {
        btnFind.addActionListener(e -> findFibonacci());
    }

    private BigInteger fibonacci(int n, Map<Integer, BigInteger> memo) {

        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        BigInteger value = fibonacci(n - 1, memo)
                .add(fibonacci(n - 2, memo));

        memo.put(n, value);

        return value;
    }

    private void findFibonacci() {

        int n;

        try {
            n = Integer.parseInt(txtN.getText().trim());

            if (n < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "N phải >= 0"
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

        btnFind.setEnabled(false);

        progressBar.setIndeterminate(true);

        lblResult.setText("Đang tính Fibonacci...");

        SwingWorker<BigInteger, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected BigInteger doInBackground() {

                        Map<Integer, BigInteger> memo =
                                new HashMap<>();

                        return fibonacci(n, memo);
                    }

                    @Override
                    protected void done() {

                        try {

                            BigInteger result = get();

                            lblResult.setText(
                                    "Fibonacci(" + n + ") = " + result
                            );

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Có lỗi khi tính Fibonacci"
                            );
                        }

                        progressBar.setIndeterminate(false);
                        progressBar.setValue(100);

                        btnFind.setEnabled(true);
                    }
                };

        worker.execute();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            FibonacciFrame frame = new FibonacciFrame();
            frame.setVisible(true);
        });
    }
}