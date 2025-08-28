package com.baitap02.login.controller;

import java.io.File;
import java.io.IOException;
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
@WebServlet(urlPatterns = "/category/delete")
public class CategoryDeleteController extends HttpServlet {
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
        
        // Xóa file ảnh trước khi xóa record trong DB
        Category category = categoryService.get(cate_id, user.getId());
        if(category != null && category.getIcons() != null){
             File file = new File(Constant.UPLOAD_DIR + File.separator + category.getIcons());
             if(file.exists()) file.delete();
        }
        
        categoryService.delete(cate_id, user.getId());
        resp.sendRedirect(req.getContextPath() + "/category/list");
    }
}