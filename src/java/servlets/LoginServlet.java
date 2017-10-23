package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = null;
        String logout = request.getParameter("logout");
        Cookie[] cookies = request.getCookies();
        if(logout != null){
            request.setAttribute("logoutMessage", "Logged Out");
             // forward the request back to index.jsp
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
             // stop other execution of code
             return;
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                     response.sendRedirect("home");
                    return;
                }
            }

        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        if (username == null || password == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        //validation
        if (username.isEmpty() || password.isEmpty()) {
            // set error message
            request.setAttribute("errorMessage", "Invalid username or password.");
            // forward the request back to index.jsp
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            // stop other execution of code
            return;
        }

        UserService us = new UserService();
        if (us.login(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.setAttribute("username", username);
            if(remember == null){
                response.sendRedirect("home");
                return;
            }
            if(remember.equalsIgnoreCase("on")){
                Cookie c = new Cookie("username", username);
                c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
                c.setPath("/");                      // allow entire app to access it
                response.addCookie(c);
                response.sendRedirect("home");            
            }
        }else{
           request.setAttribute("errorMessage", "Invalid username or password.");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
        }
    }

}
