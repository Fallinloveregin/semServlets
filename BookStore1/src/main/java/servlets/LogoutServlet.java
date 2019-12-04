package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Cookie cUserName = new Cookie("cookuser", null);
        Cookie cRemember = new Cookie("cookrem", null);
        cUserName.setMaxAge(0);
        cRemember.setMaxAge(0);
        response.addCookie(cUserName);
        response.addCookie(cRemember);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        getServletConfig().getServletContext().getRequestDispatcher("/login.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


