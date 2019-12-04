package admin;

import dao.CommentsDao;
import dao.ItemsDao;
import dao.impl.CommentsDaoImpl;
import dao.impl.ItemsDaoImpl;
import model.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ItemsDao implItems = new ItemsDaoImpl();
        CommentsDao implCom = new CommentsDaoImpl();

        ArrayList<Comment> comments = (ArrayList<Comment>) implCom.findByItemId(id);
        for (Comment com : comments) {
            implCom.delete(com.getComment_id());
        }

        implItems.delete(id);
        resp.sendRedirect("/");
    }
}
