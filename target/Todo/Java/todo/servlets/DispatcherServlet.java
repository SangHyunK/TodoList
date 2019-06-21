package todo.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        String path = request.getServletPath();

        if(path.equals("/list.do")) {

        } else if(path.equals("/view.do")) {

        } else if(path.equals("/add.do")) {

        }

        RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
        rd.include(request, response);
    }
}
