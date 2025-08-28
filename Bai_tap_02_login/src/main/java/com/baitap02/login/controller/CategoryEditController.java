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
@WebServlet(urlPatterns = "/category/edit")
public class CategoryEditController extends HttpServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("account");
        if (obj == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        User user = (User) obj;
        int cate_id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryService.get(cate_id, user.getId());
        
        req.setAttribute("category", category);
        req.getRequestDispatcher("/views/category/edit-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("account");
        if (obj == null) {
            resp.sendRedirect(req.getContextPath() + "/login?message=session-timeout");
            return;
        }
        
        User user = (User) obj;
        Category category = new Category();
        category.setUserid(user.getId());

        JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();

        try {
            List<? extends FileItem> items = upload.parseRequest(req);
            Category oldCategory = null;

            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString(Charset.forName("UTF-8"));
                    if ("cate_id".equals(fieldName)) {
                        category.setCate_id(Integer.parseInt(value));
                        // Lấy thông tin category cũ để biết tên ảnh cũ
                        oldCategory = categoryService.get(category.getCate_id(), user.getId());
                    }
                    if ("cate_name".equals(fieldName)) {
                        category.setCate_name(value);
                    }
                } else {
                    // Xử lý file upload
                    if (item.getSize() > 0) { // Người dùng có chọn file mới
                        // Xóa ảnh cũ nếu tồn tại
                        if (oldCategory != null && oldCategory.getIcons() != null && !oldCategory.getIcons().isEmpty()) {
                            String oldImageName = oldCategory.getIcons();
                            String realPath = req.getServletContext().getRealPath("/" + Constant.UPLOAD_DIR);
                            File oldFile = new File(realPath + File.separator + oldImageName);
                            if (oldFile.exists()) {
                                oldFile.delete();
                            }
                        }

                        // Tải ảnh mới lên
                        String originalFileName = new File(item.getName()).getName();
                        String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
                        
                        String realPath = req.getServletContext().getRealPath("/" + Constant.UPLOAD_DIR);
                        File uploadDir = new File(realPath);
                         if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        File storeFile = new File(realPath + File.separator + uniqueFileName);
                        item.write(storeFile.toPath());
                        
                        category.setIcons(uniqueFileName);
                    } else {
                        // Người dùng không chọn file mới, giữ lại ảnh cũ
                        if (oldCategory != null) {
                            category.setIcons(oldCategory.getIcons());
                        }
                    }
                }
            }
            
            categoryService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/category/edit?id=" + category.getCate_id() + "&error=update-failed");
        }
    }
}