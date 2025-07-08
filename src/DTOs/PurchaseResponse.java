package DTOs;

public class PurchaseResponse {
    private String message;
    private double totalAmount;
    private boolean success;

    public PurchaseResponse(String message, double totalAmount, boolean success) {
        this.message = message;
        this.totalAmount = totalAmount;
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return String.format("PurchaseResponse{message='%s', totalAmount=%.2f, success=%b}",
                message, totalAmount, success);
    }
}
