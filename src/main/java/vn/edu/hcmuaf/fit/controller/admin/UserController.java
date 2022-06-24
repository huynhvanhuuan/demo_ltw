package vn.edu.hcmuaf.fit.controller.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.appuser.AppUserForAdminDto;
import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.service.impl.AppUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin-user", value = "/admin/user")
public class UserController extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private AppUserService appUserService;

    @Override
    public void init() throws ServletException {
        appUserService = new AppUserServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String[] pathParts = path.split("/");
        String method = request.getMethod();
        switch (method) {
            case "GET":
                switch (pathParts[1]) {
                    case "list":
                        getUsers(response);
                        break;
                }
                break;
            case "POST":
                switch (pathParts[1]) {
                }
                break;
        }
    }

    public void getUsers(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        AppServiceResult<List<AppUserForAdminDto>> result = appUserService.getUsers();
        if (result.isSuccess()) {
            response.setStatus(200);
            response.getWriter().println(GSON.toJson(result));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void updateStatus(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}
