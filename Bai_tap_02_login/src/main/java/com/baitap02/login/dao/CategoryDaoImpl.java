package com.baitap02.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.baitap02.login.connection.DBConnection;
import com.baitap02.login.model.Category;

public class CategoryDaoImpl implements CategoryDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO Category(cate_name, icons, userid) VALUES (?, ?, ?)";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCate_name());
            ps.setString(2, category.getIcons());
            ps.setInt(3, category.getUserid());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE Category SET cate_name = ?, icons = ? WHERE cate_id = ? AND userid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCate_name());
            ps.setString(2, category.getIcons());
            ps.setInt(3, category.getCate_id());
            ps.setInt(4, category.getUserid()); // Đảm bảo user chỉ sửa được category của mình
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int cate_id, int userid) {
        String sql = "DELETE FROM Category WHERE cate_id = ? AND userid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cate_id);
            ps.setInt(2, userid); // Đảm bảo user chỉ xóa được category của mình
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public Category get(int cate_id, int userid) {
        String sql = "SELECT * FROM Category WHERE cate_id = ? AND userid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cate_id);
            ps.setInt(2, userid);
            rs = ps.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setCate_id(rs.getInt("cate_id"));
                category.setCate_name(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));
                category.setUserid(rs.getInt("userid"));
                return category;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Category> getAll(int userid) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE userid = ?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCate_id(rs.getInt("cate_id"));
                category.setCate_name(rs.getString("cate_name"));
                category.setIcons(rs.getString("icons"));
                category.setUserid(rs.getInt("userid"));
                categories.add(category);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return categories;
    }
}