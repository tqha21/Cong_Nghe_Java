package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienRepository {

    private static final List<SinhVien> data = new ArrayList<>();

    private static int autoId = 3;

    static {
        data.add(new SinhVien(
                1,
                "20240001",
                "Nguyễn Văn An",
                "an@gmail.com",
                "DCCNTT15.10.1"
        ));

        data.add(new SinhVien(
                2,
                "20240002",
                "Trần Thị Bình",
                "binh@gmail.com",
                "DCCNTT15.10.2"
        ));
    }

    // Lấy toàn bộ sinh viên
    public List<SinhVien> findAll() {
        return data;
    }

    // Thêm sinh viên
    public void add(SinhVien sv) {
        sv.setId(autoId++);
        data.add(sv);
    }

    // Tìm sinh viên theo ID
    public SinhVien findById(int id) {

        for (SinhVien sv : data) {

            if (sv.getId() == id) {
                return sv;
            }
        }

        return null;
    }

    // Cập nhật sinh viên
    public boolean update(SinhVien sinhVien) {

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getId() == sinhVien.getId()) {

                data.set(i, sinhVien);

                return true;
            }
        }

        return false;
    }

    // Xóa sinh viên
    public void delete(int id) {

        data.removeIf(sv -> sv.getId() == id);
    }
}