package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.ProvinceDto;
import vn.edu.hcmuaf.fit.service.CommonService;
import vn.edu.hcmuaf.fit.service.impl.CommonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-province", urlPatterns = "/api/province/*")
public class ProvinceAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private CommonService commonService;

    @Override
    public void init() throws ServletException {
        commonService = new CommonServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String path = request.getPathInfo();
        if ((path == null || path.equals("/")) && request.getMethod().equals("GET")) {
            getProvinces(response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Lỗi. Không tìm thấy đường dẫn!");
            response.getWriter().println(GSON.toJson(result));
        }
    }

    private void getProvinces(HttpServletResponse response) throws IOException {
        AppServiceResult<List<ProvinceDto>> result = commonService.getProvinces();
        response.getWriter().println(GSON.toJson(result));
    }
}
