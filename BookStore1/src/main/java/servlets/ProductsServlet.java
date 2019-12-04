package servlets;

import dao.ItemsDao;
import dao.impl.ItemsDaoImpl;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Cookie[] cookies = req.getCookies();

        String username = (String) session.getAttribute("session_uname");
        if (username == null && cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cookuser"))
                    session.setAttribute("session_uname", cookies[i].getValue());
            }

        int page = 1;
        int recordsPerPage = 3;

        String pageFromRequest = req.getParameter("page");
        String genre = req.getParameter("genre");
        if (pageFromRequest != null)
            page = Integer.parseInt(pageFromRequest);

        ItemsDao itemsDao = new ItemsDaoImpl();
        List<Item> productsSub;

        if (genre == null) {
            productsSub = itemsDao.viewAllItems((page - 1) * recordsPerPage, recordsPerPage);
        } else {
            productsSub = itemsDao.viewItemsByGenre((page - 1) * recordsPerPage, recordsPerPage, genre);
        }

        int noOfRecords = itemsDao.size();

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("products", productsSub);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
