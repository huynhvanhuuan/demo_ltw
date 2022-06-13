package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.WardDto;
import vn.edu.hcmuaf.fit.service.CommonService;
import vn.edu.hcmuaf.fit.service.impl.CommonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-ward", urlPatterns = "/api/ward/*")
public class WardAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private final CommonService wardService = CommonServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                String districtId = request.getParameter("districtId");
                AppServiceResult<List<WardDto>> result = wardService.getWards(districtId == null ? 0 : Long.parseLong(districtId));
                if (result.isSuccess()) {
                    response.setStatus(200);
                    response.getWriter().println(GSON.toJson(result));
                } else {
                    response.sendError(result.getErrorCode(), result.getMessage());
                }
            } else {
                Long id = Long.parseLong(pathInfo.substring(1));
                AppServiceResult<WardDto> result = wardService.getWard(id);
                if (result.isSuccess()) {
                    response.setStatus(200);
                    response.getWriter().println(GSON.toJson(result));
                } else {
                    response.sendError(result.getErrorCode(), result.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }
}
