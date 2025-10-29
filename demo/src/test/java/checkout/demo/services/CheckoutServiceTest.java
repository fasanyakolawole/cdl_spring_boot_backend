package checkout.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CheckoutService Tests")
class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
    }

    @Test
    @DisplayName("Test example case: A, A, A, C, D, B, B")
    void testExampleCase() {
        // Given: items from the example request
        List<String> items = Arrays.asList("A", "A", "A", "C", "D", "B", "B");
        
        // When: calculateTotal is called
        int total = checkoutService.calculateTotal(items);
        
        // Then: total should be 210
        // (3*A = 130) + (1*C = 20) + (1*D = 15) + (2*B = 45) = 210
        assertEquals(210, total);
    }

    @Test
    @DisplayName("Test single item A")
    void testSingleItemA() {
        List<String> items = Arrays.asList("A");
        int total = checkoutService.calculateTotal(items);
        assertEquals(50, total);
    }

    @Test
    @DisplayName("Test two items A")
    void testTwoItemsA() {
        List<String> items = Arrays.asList("A", "A");
        int total = checkoutService.calculateTotal(items);
        assertEquals(100, total);
    }

    @Test
    @DisplayName("Test three items A (special offer)")
    void testThreeItemsA() {
        List<String> items = Arrays.asList("A", "A", "A");
        int total = checkoutService.calculateTotal(items);
        assertEquals(130, total);
    }

    @Test
    @DisplayName("Test four items A (3 + 1)")
    void testFourItemsA() {
        List<String> items = Arrays.asList("A", "A", "A", "A");
        int total = checkoutService.calculateTotal(items);
        // 130 (for 3) + 50 (for 1) = 180
        assertEquals(180, total);
    }

    @Test
    @DisplayName("Test six items A (two special offers)")
    void testSixItemsA() {
        List<String> items = Arrays.asList("A", "A", "A", "A", "A", "A");
        int total = checkoutService.calculateTotal(items);
        // 130 (for 3) + 130 (for 3) = 260
        assertEquals(260, total);
    }

    @Test
    @DisplayName("Test single item B")
    void testSingleItemB() {
        List<String> items = Arrays.asList("B");
        int total = checkoutService.calculateTotal(items);
        assertEquals(30, total);
    }

    @Test
    @DisplayName("Test two items B (special offer)")
    void testTwoItemsB() {
        List<String> items = Arrays.asList("B", "B");
        int total = checkoutService.calculateTotal(items);
        assertEquals(45, total);
    }

    @Test
    @DisplayName("Test three items B (2 + 1)")
    void testThreeItemsB() {
        List<String> items = Arrays.asList("B", "B", "B");
        int total = checkoutService.calculateTotal(items);
        // 45 (for 2) + 30 (for 1) = 75
        assertEquals(75, total);
    }

    @Test
    @DisplayName("Test single item C")
    void testSingleItemC() {
        List<String> items = Arrays.asList("C");
        int total = checkoutService.calculateTotal(items);
        assertEquals(20, total);
    }

    @Test
    @DisplayName("Test multiple items C")
    void testMultipleItemsC() {
        List<String> items = Arrays.asList("C", "C", "C");
        int total = checkoutService.calculateTotal(items);
        // 20 * 3 = 60
        assertEquals(60, total);
    }

    @Test
    @DisplayName("Test single item D")
    void testSingleItemD() {
        List<String> items = Arrays.asList("D");
        int total = checkoutService.calculateTotal(items);
        assertEquals(15, total);
    }

    @Test
    @DisplayName("Test multiple items D")
    void testMultipleItemsD() {
        List<String> items = Arrays.asList("D", "D", "D");
        int total = checkoutService.calculateTotal(items);
        // 15 * 3 = 45
        assertEquals(45, total);
    }

    @Test
    @DisplayName("Test empty list")
    void testEmptyList() {
        List<String> items = Collections.emptyList();
        int total = checkoutService.calculateTotal(items);
        assertEquals(0, total);
    }

    @Test
    @DisplayName("Test unknown item (should be ignored)")
    void testUnknownItem() {
        List<String> items = Arrays.asList("E", "E");
        int total = checkoutService.calculateTotal(items);
        assertEquals(0, total);
    }

    @Test
    @DisplayName("Test mixed items with unknown items")
    void testMixedItemsWithUnknown() {
        List<String> items = Arrays.asList("A", "E", "B", "F");
        int total = checkoutService.calculateTotal(items);
        // A = 50, B = 30, E and F ignored = 80
        assertEquals(80, total);
    }

    @Test
    @DisplayName("Test all item types together")
    void testAllItemTypes() {
        List<String> items = Arrays.asList("A", "B", "C", "D");
        int total = checkoutService.calculateTotal(items);
        // A = 50, B = 30, C = 20, D = 15 = 115
        assertEquals(115, total);
    }

    @Test
    @DisplayName("Test complex scenario with multiple deals")
    void testComplexScenario() {
        List<String> items = Arrays.asList("A", "A", "A", "B", "B", "C", "C", "D", "D", "D");
        int total = checkoutService.calculateTotal(items);
        // A: 130 (for 3)
        // B: 45 (for 2)
        // C: 40 (for 2)
        // D: 45 (for 3)
        // Total = 260
        assertEquals(260, total);
    }
}

