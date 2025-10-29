package checkout.demo.dto;

public class CheckoutResponse {
    private int total; // in pence

    public CheckoutResponse(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
