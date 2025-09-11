package com.votrihieu.BAI04.dao;

import com.votrihieu.BAI04.model.User;
import com.votrihieu.BAI04.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAO {

   
    public User checkLogin(String username, String password) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Sử dụng JPQL (Java Persistence Query Language) để truy vấn
            String jpql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            // getSingleResult() sẽ ném ra NoResultException nếu không tìm thấy kết quả
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    // Bạn có thể thêm các phương thức CRUD khác ở đây nếu cần (findById, insert, update, delete)
}