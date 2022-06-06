package vn.edu.hcmuaf.fit.controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "admin-home", value = "/admin/dashboard")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                getDashboard(request, response);
            } else switch (action) {
                case "signin":
                    signin(request, response);
                    break;
                case "signout":
                    signout(request, response);
                    break;
                default:
                    getDashboard(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void getDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        request.setAttribute("register", userService.getList().size());
//        HttpSession session = request.getSession();
//        if (session.isNew() || session.getAttribute("user") == null) {
//            getSigninPage(request, response);
//        } else {
            //User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("title", "Chào mừng trở lại, " + "Huỳnh Văn Hữu Ân");
            request.getRequestDispatcher("/view/admin/home.jsp").forward(request, response);
//        }
    }

    private void getSigninPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/admin/signin.jsp").forward(request, response);
    }

    private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Map<String, String> errors = new HashMap<>();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email is required");
        } else {
            request.setAttribute("email", email);
        }
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "Password is required");
        } else {
            request.setAttribute("password", password);
        }
        if (errors.size() > 0) {
            errors.forEach(session::setAttribute);
            request.setAttribute("validate", "is-invalid");
            getSigninPage(request, response);
        } else {
            // get user
            session.setAttribute("email", email);
            request.getRequestDispatcher("/view/admin/home.jsp").forward(request, response);
        }
    }

    private void signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("USER");
        getSigninPage(request, response);
    }
}
