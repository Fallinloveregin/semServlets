package actions;

import dao.ItemsDao;
import dao.impl.ItemsDaoImpl;
import model.Item;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteFromCart extends HttpServlet {

    private static Logger log = Logger.getLogger(DeleteFromCart.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        ItemsDao impl = new ItemsDaoImpl();
        Item newItem = impl.findByPrimaryKey(Long.parseLong(id));

        HttpSession session = req.getSession(true);
        int cost = (int) session.getAttribute("cost");
        cost -= newItem.getPrice();

        ArrayList cart = (ArrayList) session.getAttribute("cart");
        cart.remove(newItem);

        log.info("removed:" + newItem.getName());

        req.getSession().setAttribute("cost", cost);
        req.getSession().setAttribute("cart", cart);
        getServletConfig().getServletContext().getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
