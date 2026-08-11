package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileLineCounterFrame extends JFrame {

    // Các component
    private JButton btnChoose;
    private JButton btnCount;
    private JLabel lblFile;
    private JLabel lblResult;
    private JProgressBar progressBar;

    // File được chọn
    private File selectedFile;

    public FileLineCounterFrame() {
        initComponents();
        addEvents();
    }


    // KHỞI TẠO GIAO DIỆN

    private void initComponents() {

        setTitle("Đọc file lớn và đếm số dòng");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Khởi tạo các component
        btnChoose = new JButton("Chọn file");
        btnCount = new JButton("Đếm dòng");

        lblFile = new JLabel("File: Chưa chọn file");
        lblResult = new JLabel("Số dòng: ");

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnChoose);
        buttonPanel.add(btnCount);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFile.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(buttonPanel);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(lblFile);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(progressBar);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(lblResult);

        add(mainPanel);
    }


    // KHỞI TẠO SỰ KIỆN

    private void addEvents() {

        btnChoose.addActionListener(e -> chooseFile());

        btnCount.addActionListener(e -> countLines());
    }


    // CHỌN FILE

    private void chooseFile() {

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            selectedFile = chooser.getSelectedFile();

            lblFile.setText(
                    "File: " + selectedFile.getAbsolutePath()
            );

            lblResult.setText("Số dòng: ");
            progressBar.setValue(0);
        }
    }


    // ĐẾM SỐ DÒNG

    private void countLines() {

        // Chưa chọn file
        if (selectedFile == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng chọn file trước"
            );

            return;
        }

        // Khóa nút Đếm dòng
        btnCount.setEnabled(false);

        // Reset ProgressBar
        progressBar.setValue(0);

        // Hiển thị trạng thái
        lblResult.setText("Đang đọc file...");

        // SwingWorker
        SwingWorker<Long, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Long doInBackground() throws Exception {

                        // Tổng dung lượng file
                        long totalBytes =
                                Files.size(selectedFile.toPath());

                        long readBytes = 0;

                        long lines = 0;

                        // Mở file
                        try (BufferedReader reader =
                                     Files.newBufferedReader(
                                             selectedFile.toPath(),
                                             StandardCharsets.UTF_8
                                     )) {

                            String line;

                            // Đọc từng dòng
                            while ((line = reader.readLine()) != null) {

                                lines++;

                                /*
                                 * Ước lượng số byte đã đọc.
                                 * +1 đại diện cho ký tự xuống dòng.
                                 */
                                readBytes +=
                                        line.getBytes(
                                                StandardCharsets.UTF_8
                                        ).length + 1;

                                // Tính tiến trình
                                int progress;

                                if (totalBytes == 0) {

                                    progress = 100;

                                } else {

                                    progress =
                                            (int) Math.min(
                                                    100,
                                                    readBytes * 100
                                                            / totalBytes
                                            );
                                }

                                setProgress(progress);
                            }
                        }

                        return lines;
                    }

                    @Override
                    protected void done() {

                        try {

                            // Lấy kết quả
                            long lineCount = get();

                            lblResult.setText(
                                    "Số dòng: " + lineCount
                            );

                        } catch (Exception ex) {

                            lblResult.setText(
                                    "Lỗi khi đọc file"
                            );

                            JOptionPane.showMessageDialog(
                                    FileLineCounterFrame.this,
                                    "Không thể đọc file:\n"
                                            + ex.getMessage()
                            );
                        }

                        // Hoàn thành ProgressBar
                        progressBar.setValue(100);

                        // Cho phép đếm lại
                        btnCount.setEnabled(true);
                    }
                };

        // Lắng nghe tiến trình
        worker.addPropertyChangeListener(evt -> {

            if ("progress".equals(evt.getPropertyName())) {

                progressBar.setValue(
                        (int) evt.getNewValue()
                );
            }
        });

        // Chạy SwingWorker
        worker.execute();
    }

    // MAIN
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FileLineCounterFrame frame =
                    new FileLineCounterFrame();

            frame.setVisible(true);
        });
    }
}
