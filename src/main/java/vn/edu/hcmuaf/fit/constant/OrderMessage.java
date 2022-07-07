package vn.edu.hcmuaf.fit.constant;

public enum OrderMessage {
    Pending(null,"Chờ xác nhận"),
    Confirm("Đã xác nhận","Chờ lấy hàng"),
    Delivering("Đang giao hàng","Đang giao"),
    Completed("Giao hàng thành công","Đang giao"),
    Cancelled(null,"Đã hủy");

    private final String infoMessage;
    private final String systemMessage;

    OrderMessage(String infoMessage, String systemMessage) {
        this.infoMessage = infoMessage;
        this.systemMessage = systemMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public String getSystemMessage() {
        return systemMessage;
    }
}
