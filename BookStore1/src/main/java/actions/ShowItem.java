package actions;

import dao.CommentsDao;
import dao.ItemsDao;
import dao.impl.CommentsDaoImpl;
import dao.impl.ItemsDaoImpl;
import model.Comment;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        ItemsDao itemsDao = new ItemsDaoImpl();
        Item item = itemsDao.findByPrimaryKey(id);
        CommentsDao implComm = new CommentsDaoImpl();
        ArrayList<Comment> comments = (ArrayList) implComm.findByItemId(id);
        req.setAttribute("item", item);
        req.setAttribute("comments", comments);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
