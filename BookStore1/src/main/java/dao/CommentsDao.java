package dao;

import model.Comment;

import java.util.List;

public interface CommentsDao {
    void add(Comment comment);

    void delete(Long id);

    Comment findByPrimaryKey(Long id);

    List findByItemId(Long id);
}
