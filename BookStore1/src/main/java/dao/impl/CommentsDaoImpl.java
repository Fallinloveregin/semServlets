package dao.impl;

import actions.AddComment;
import dao.CommentsDao;
import dao.factory.JDBCTemplate;
import model.Comment;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CommentsDaoImpl extends JDBCTemplate implements CommentsDao {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH.mm");
    private static Logger log = Logger.getLogger(CommentsDaoImpl.class);


    public void add(Comment comment) {
        try {
            String querystring = "INSERT INTO comments(user_id, item_id, text, date) VALUES (?, ?, ?, ?)";
            String querytest = "INSERT INTO test(id) VALUES (1)";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ptmt.setLong(1, comment.getUser_id());
            ptmt.setLong(2, comment.getItem_id());
            ptmt.setString(3, comment.getText());
            ptmt.setTimestamp(4, timestamp);

            ptmt.executeUpdate();
            PreparedStatement ps = con.prepareStatement(querytest);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void delete(Long id) {
        try {
            String querystring = "DELETE FROM comments WHERE comment_id =?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            ptmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

    }

    public Comment findByPrimaryKey(Long id) {
        Comment comment = null;
        try {
            String querystring = "SELECT * FROM comments JOIN users ON comments.comment_id = users.user_id WHERE item_id = ?";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                comment = new Comment();
                setFields(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return comment;
    }

    @Override
    public List findByItemId(Long id) {
        List comments = new ArrayList();
        Comment comment;
        try {
            String querystring = "SELECT * FROM comments JOIN users ON comments.user_id = users.user_id WHERE item_id = ? ORDER BY date";
            con = getConnection();
            ptmt = con.prepareStatement(querystring);
            ptmt.setLong(1, id);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                comment = new Comment();
                setFields(comment);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        log.info("comments size is " + comments.size());
        return comments;
    }

    private void setFields(Comment comment) throws SQLException {
        comment.setComment_id(rs.getLong("comment_id"));
        comment.setText(rs.getString("text"));
        comment.setUser_id(rs.getLong("user_id"));
        comment.setItem_id(rs.getLong("item_id"));
        comment.setDate(sdf.format(rs.getTimestamp("date")));
        comment.setLogin(rs.getString("login"));
    }
}
