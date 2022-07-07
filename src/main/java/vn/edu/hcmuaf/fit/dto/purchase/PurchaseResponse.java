package vn.edu.hcmuaf.fit.dto.purchase;

import java.util.UUID;

public class PurchaseResponse {
    private UUID orderTrackingNumber;

    public PurchaseResponse(UUID orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public UUID getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(UUID orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
