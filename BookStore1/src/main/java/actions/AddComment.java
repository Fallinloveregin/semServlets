package actions;

import dao.CommentsDao;
import dao.ItemsDao;
import dao.UsersDao;
import dao.impl.CommentsDaoImpl;
import dao.impl.ItemsDaoImpl;
import dao.impl.UsersDaoImpl;
import model.Comment;
import model.Item;
import model.User;
import org.apache.log4j.Logger;
import servlets.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddComment extends HttpServlet {

    private static Logger log = Logger.getLogger(AddComment.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comment = req.getParameter("comment");
        String login = req.getParameter("username");
        Long item_id = Long.valueOf(req.getParameter("item_id"));

        UsersDao implUser = new UsersDaoImpl();
        User user = implUser.findByLogin(login);

        Comment newComment = new Comment();
        newComment.setText(comment);
        newComment.setLogin(login);
        newComment.setItem_id(item_id);
        newComment.setUser_id(user.getId());

        ItemsDao implItems = new ItemsDaoImpl();
        Item item = implItems.findByPrimaryKey(item_id);

        log.info("Text is: " + newComment.getText());

        CommentsDao implComm = new CommentsDaoImpl();
        implComm.add(newComment);

        ArrayList comments = (ArrayList) implComm.findByItemId(item_id);

        req.setAttribute("item", item);
        req.setAttribute("comments", comments);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/item.jsp").forward(req, resp);
    }
}
