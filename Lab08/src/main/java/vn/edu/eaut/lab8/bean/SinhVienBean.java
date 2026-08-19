package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import vn.edu.eaut.lab8.model.SinhVien;
import vn.edu.eaut.lab8.repository.SinhVienRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("sinhVienBean")
@SessionScoped
public class SinhVienBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private SinhVienRepository repository = new SinhVienRepository();

    private SinhVien sinhVien = new SinhVien();

    private boolean editMode = false;

    private String keyword = "";

    // =========================
    // LẤY DANH SÁCH
    // =========================

    public List<SinhVien> getDsSinhVien() {
        return repository.findAll();
    }

    // =========================
    // LẤY DANH SÁCH SAU KHI TÌM KIẾM
    // =========================

    public List<SinhVien> getFilteredSinhVien() {

        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }

        String key = keyword.trim().toLowerCase();

        return repository.findAll()
                .stream()
                .filter(sv ->
                        (sv.getHoTen() != null
                                && sv.getHoTen().toLowerCase().contains(key))
                                ||
                                (sv.getLop() != null
                                        && sv.getLop().toLowerCase().contains(key))
                )
                .collect(Collectors.toList());
    }

    // =========================
    // THÊM / CẬP NHẬT
    // =========================

    public String save() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (editMode) {

            boolean updated = repository.update(sinhVien);

            if (updated) {

                context.addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Thành công",
                                "Cập nhật sinh viên thành công!"
                        )
                );

                editMode = false;
                sinhVien = new SinhVien();

                return "sinhvien-list?faces-redirect=true";
            }

        } else {

            repository.add(sinhVien);

            context.addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Thành công",
                            "Thêm sinh viên thành công!"
                    )
            );

            sinhVien = new SinhVien();

            return "sinhvien-list?faces-redirect=true";
        }

        return null;
    }

    // =========================
    // SỬA SINH VIÊN
    // =========================

    public String edit(int id) {

        SinhVien found = repository.findById(id);

        if (found != null) {

            sinhVien = new SinhVien(
                    found.getId(),
                    found.getMaSinhVien(),
                    found.getHoTen(),
                    found.getEmail(),
                    found.getLop()
            );

            editMode = true;

            return "sinhvien-form";
        }

        return "sinhvien-list";
    }

    // =========================
    // XÓA
    // =========================

    public String delete(int id) {

        repository.delete(id);

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Thành công",
                        "Xóa sinh viên thành công!"
                )
        );

        return "sinhvien-list?faces-redirect=true";
    }

    // =========================
    // RESET FORM
    // =========================

    public String newStudent() {

        sinhVien = new SinhVien();

        editMode = false;

        return "sinhvien-form?faces-redirect=true";
    }

    // =========================
    // GETTER / SETTER
    // =========================

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}