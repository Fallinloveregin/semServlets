package actions;

import dao.ItemsDao;
import dao.impl.ItemsDaoImpl;
import model.Item;
import org.apache.log4j.Logger;
import servlets.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bucket extends HttpServlet {

    private static Logger log = Logger.getLogger(Bucket.class);
    private int newCost;
    private Object cost;
    private List cart;
    private Item item;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        newCost = 0;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("cost") != null) {
            newCost = (int) session.getAttribute("cost");
        }
        req.getSession().setAttribute("cost", newCost);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int newCost = 0;
        cost = session.getAttribute("cost");
        String id = req.getParameter("id");

        ItemsDao impl = new ItemsDaoImpl();
        item = impl.findByPrimaryKey(Long.parseLong(id));

        if (cost != null) {
            newCost = (int) cost;
        }

        cart = (List) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList();
            cart.add(item);
        } else {
            cart.add(item);
        }

        newCost += item.getPrice();
        log.info("Final cost:" + newCost);

        req.getSession().setAttribute("cost", newCost);
        req.getSession().setAttribute("cart", cart);
        resp.sendRedirect("/");
    }
}
