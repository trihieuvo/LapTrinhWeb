package com.baitap02.login.controller;

import java.io.IOException;
import java.util.List;
import com.baitap02.login.model.Category;
import com.baitap02.login.model.User;
import com.baitap02.login.service.CategoryService;
import com.baitap02.login.service.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/category/list")
public class CategoryListController extends HttpServlet {
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
        List<Category> categories = categoryService.getAll(user.getId());
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/category/list-category.jsp").forward(req, resp);
    }
}