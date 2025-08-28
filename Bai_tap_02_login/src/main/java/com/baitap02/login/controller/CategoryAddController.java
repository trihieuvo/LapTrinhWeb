package com.baitap02.login.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;

import com.baitap02.login.model.Category;
import com.baitap02.login.model.User;
import com.baitap02.login.service.CategoryService;
import com.baitap02.login.service.CategoryServiceImpl;
import com.baitap02.login.util.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/category/add")
public class CategoryAddController extends HttpServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Luôn kiểm tra đăng nhập trước
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/views/category/add-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("account");
        if (obj == null) {
            // Nếu session hết hạn giữa chừng, không xử lý
            resp.sendRedirect(req.getContextPath() + "/login?message=session-timeout");
            return;
        }
        
        User user = (User) obj;
        Category category = new Category();
        category.setUserid(user.getId());

        JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();

        try {
            List<? extends FileItem> items = upload.parseRequest(req);
            
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Xử lý các trường text trong form
                    if ("cate_name".equals(item.getFieldName())) {
                        category.setCate_name(item.getString(Charset.forName("UTF-8")));
                    }
                } else {
                    // Xử lý trường upload file
                    if (item.getSize() > 0) {
                        // Lấy tên file gốc một cách an toàn
                        String originalFileName = new File(item.getName()).getName();
                        String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

                        // Lấy đường dẫn thực tế đến thư mục uploads
                        String realPath = req.getServletContext().getRealPath("/" + Constant.UPLOAD_DIR);
                        File uploadDir = new File(realPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs(); // Tự động tạo thư mục nếu chưa có
                        }

                        // Ghi file
                        File storeFile = new File(realPath + File.separator + uniqueFileName);
                        item.write(storeFile.toPath());
                        
                        category.setIcons(uniqueFileName);
                    }
                }
            }
            
            categoryService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            // Có thể chuyển hướng đến trang lỗi
            resp.sendRedirect(req.getContextPath() + "/category/add?error=upload-failed");
        }
    }
}