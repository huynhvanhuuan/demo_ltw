package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.purchase.Purchase;
import vn.edu.hcmuaf.fit.dto.purchase.PurchaseResponse;

public interface CheckoutService {
    AppServiceResult<PurchaseResponse> createOrder(Purchase purchase);
}
