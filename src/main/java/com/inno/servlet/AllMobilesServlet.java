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
import java.util.Collection;
import java.util.List;

@WebServlet(urlPatterns = "/allmobiles", name = "Mobiles")
public class AllMobilesServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Mobile> mobiles = mobileDao.getAllMobile();

        req.setAttribute("mobiles", mobiles);
        req.getRequestDispatcher("WEB-INF/jsp/allmobiles.jsp").forward(req, resp);
    }
}
