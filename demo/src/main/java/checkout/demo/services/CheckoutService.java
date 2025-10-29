package checkout.demo.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {
    public int calculateTotal(List<String> items) {
        Map<String, Integer> counts = new HashMap<>();
        for (String sku : items) {
            counts.put(sku, counts.getOrDefault(sku, 0) + 1);
        }

        int total = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String sku = entry.getKey();
            int qty = entry.getValue();

            switch (sku) {
                case "A":
                    total += (qty / 3) * 130 + (qty % 3) * 50;
                    break;
                case "B":
                    total += (qty / 2) * 45 + (qty % 2) * 30;
                    break;
                case "C":
                    total += qty * 20;
                    break;
                case "D":
                    total += qty * 15;
                    break;
                default:
                    break;
            }
        }
        return total;
    }
}
