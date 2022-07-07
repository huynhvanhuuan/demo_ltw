package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.AddressDto;
import vn.edu.hcmuaf.fit.service.AddressService;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.impl.AddressServiceImpl;
import vn.edu.hcmuaf.fit.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-checkout", value = "/api/checkout")
public class CheckoutAPI extends HttpServlet {
    private Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private AddressService addressService;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        addressService = new AddressServiceImpl();
        cartService = new CartServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");

        AppServiceResult<List<AddressDto>> addresses = addressService.getAddressByUserId(userId);
    }
}
