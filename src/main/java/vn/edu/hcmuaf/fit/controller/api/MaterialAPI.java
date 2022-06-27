package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.product.MaterialDto;
import vn.edu.hcmuaf.fit.service.CommonService;
import vn.edu.hcmuaf.fit.service.impl.CommonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-material", urlPatterns = "/api/material/*")
public class MaterialAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private CommonService commonService;

    @Override
    public void init() throws ServletException {
        commonService = new CommonServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getPathInfo();
        if ((path == null || path.equals("/")) && request.getMethod().equals("GET")) {
            getMaterials(response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Lỗi. Không tìm thấy đường dẫn!");
            response.getWriter().println(GSON.toJson(result));
        }
    }

    private void getMaterials(HttpServletResponse response) throws IOException {
        AppServiceResult<List<MaterialDto>> result = commonService.getMaterials();
        response.getWriter().println(GSON.toJson(result));
    }
}
