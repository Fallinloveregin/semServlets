package dao.impl;


import dao.UsersDao;
import dao.factory.JDBCTemplate;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsersDaoImpl extends JDBCTemplate implements UsersDao {

    public void add(User user) {

        try {
            String querystring = "INSERT INTO users(login, password, email) VALUES (?, ?, ?)";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setString(1, user.getLogin());
            ptmt.setString(2, user.getPassword());
            ptmt.setString(3, user.getEmail());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void update(User user) {
        try {
            String querystring = "UPDATE users SET login =?,password =?, email = ? WHERE user_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setString(1, user.getLogin());
            ptmt.setString(2, user.getPassword());
            ptmt.setString(3, user.getEmail());
            ptmt.setLong(4, user.getId());
            ptmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public List findAll() {
        List users = new ArrayList();
        User user;
        try {
            String querystring = "SELECT * FROM users";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("user_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return users;
    }

    public User findByPrimaryKey(Long id) {
        User user = null;
        try {
            String querystring = "SELECT * FROM users WHERE user_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }

    public User findByLogin(String login) {
        User user = null;
        try {
            String querystring = "SELECT * FROM users WHERE login = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setString(1, login);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setId(rs.getLong("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }
}
