package model;

import net.codejava.CafeManager.model.Delivery;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryTest {

    @Test
    public void testDelivery() {
        Delivery delivery = new Delivery();
        delivery.setId(1);
        delivery.setFio("John Smith");
        delivery.setStructure("Pizza");
        delivery.setAddress("123 Main St, Anytown USA");
        delivery.setPrice(20);
        delivery.setNum(1);

        assertEquals(1, delivery.getId());
        assertEquals("John Smith", delivery.getFio());
        assertEquals("Pizza", delivery.getStructure());
        assertEquals("123 Main St, Anytown USA", delivery.getAddress());
        assertEquals(20, delivery.getPrice());
        assertEquals(1, delivery.getNum());
    }
}