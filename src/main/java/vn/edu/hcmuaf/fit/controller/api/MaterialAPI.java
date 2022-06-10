package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private final Gson GSON = new GsonBuilder().create();
    private CommonService materialService;

    @Override
    public void init() throws ServletException {
        materialService = new CommonServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        AppServiceResult<List<MaterialDto>> result = materialService.getMaterials();
        if (result.isSuccess()) {
            response.setStatus(200);
            response.getWriter().println(GSON.toJson(result));
        } else {
            response.sendError(result.getErrorCode(), result.getMessage());
        }
    }
}