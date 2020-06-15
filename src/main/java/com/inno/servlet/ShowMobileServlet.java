package com.inno.servlet;

import com.inno.dao.MobileDao;
import com.inno.pojo.Mobile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showmobile")
public class ShowMobileServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }

        Mobile mobile = mobileDao.getMobileById(Integer.valueOf(mobileId));
        if (mobile == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("WEB-INF/jsp/notfound.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("model", mobile);
        req.getRequestDispatcher("WEB-INF/jsp/showmobile.jsp").forward(req, resp);
    }
}
