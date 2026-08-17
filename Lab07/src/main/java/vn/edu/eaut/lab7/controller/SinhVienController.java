package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.eaut.lab7.model.SinhVien;
import vn.edu.eaut.lab7.repository.SinhVienRepository;

import java.io.IOException;

@WebServlet("/sinh-vien")
public class SinhVienController extends HttpServlet {

    private final SinhVienRepository repo =
            new SinhVienRepository();


    // =====================================================
    // XỬ LÝ GET
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action =
                req.getParameter("action");


        // =================================================
        // THÊM SINH VIÊN
        // /sinh-vien?action=new
        // =================================================

        if ("new".equals(action)) {

            req.getRequestDispatcher(
                    "/views/sinhvien/form.jsp"
            ).forward(req, resp);

            return;
        }


        // =================================================
        // SỬA SINH VIÊN
        // /sinh-vien?action=edit&id=1
        // =================================================

        if ("edit".equals(action)) {

            int id = Integer.parseInt(
                    req.getParameter("id")
            );

            SinhVien sv =
                    repo.findById(id);

            req.setAttribute(
                    "sv",
                    sv
            );

            req.getRequestDispatcher(
                    "/views/sinhvien/form.jsp"
            ).forward(req, resp);

            return;
        }


        // =================================================
        // CHI TIẾT SINH VIÊN
        // /sinh-vien?action=detail&id=1
        // =================================================

        if ("detail".equals(action)) {

            int id = Integer.parseInt(
                    req.getParameter("id")
            );

            SinhVien sv =
                    repo.findById(id);

            req.setAttribute(
                    "sv",
                    sv
            );

            req.getRequestDispatcher(
                    "/views/sinhvien/detail.jsp"
            ).forward(req, resp);

            return;
        }


        // =================================================
        // XÓA SINH VIÊN
        // /sinh-vien?action=delete&id=1
        // =================================================

        if ("delete".equals(action)) {

            int id = Integer.parseInt(
                    req.getParameter("id")
            );

            repo.delete(id);

            resp.sendRedirect(
                    req.getContextPath()
                            + "/sinh-vien"
            );

            return;
        }


        // =================================================
        // DANH SÁCH + TÌM KIẾM
        // /sinh-vien
        // /sinh-vien?keyword=Nguyen
        // =================================================

        String keyword =
                req.getParameter("keyword");

        req.setAttribute(
                "dsSinhVien",
                repo.search(keyword)
        );

        req.getRequestDispatcher(
                "/views/sinhvien/list.jsp"
        ).forward(req, resp);
    }


    // =====================================================
    // XỬ LÝ POST
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");


        // =================================================
        // LẤY ID
        // =================================================

        String id =
                req.getParameter("id");


        // =================================================
        // TẠO ĐỐI TƯỢNG SINH VIÊN
        // =================================================

        SinhVien sv = new SinhVien(

                id == null || id.isBlank()
                        ? 0
                        : Integer.parseInt(id),

                req.getParameter("maSinhVien"),

                req.getParameter("hoTen"),

                req.getParameter("email"),

                req.getParameter("lop")
        );


        // =================================================
        // THÊM HOẶC CẬP NHẬT
        // =================================================

        if (sv.getId() == 0) {

            // Thêm mới
            repo.add(sv);

        } else {

            // Cập nhật
            repo.update(sv);
        }


        // =================================================
        // QUAY VỀ DANH SÁCH
        // =================================================

        resp.sendRedirect(
                req.getContextPath()
                        + "/sinh-vien"
        );
    }
}