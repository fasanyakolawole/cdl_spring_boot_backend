package checkout.demo.controller;

import checkout.demo.dto.CheckoutRequest;
import checkout.demo.dto.CheckoutResponse;
import checkout.demo.services.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow my React frontend locally
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/total")
    public CheckoutResponse calculateTotal(@RequestBody CheckoutRequest request) {
        int total = checkoutService.calculateTotal(request.getItems());
        return new CheckoutResponse(total);
    }
}
