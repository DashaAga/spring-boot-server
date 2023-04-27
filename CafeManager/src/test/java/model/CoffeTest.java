package model;

import net.codejava.CafeManager.model.Coffe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeTest {
    @Test
    public void testGetId() {
        Coffe coffe = new Coffe();
        coffe.setId(1);
        assertEquals(1, coffe.getId());
    }

    @Test
    public void testGetName() {
        Coffe coffe = new Coffe();
        coffe.setName("Espresso");
        assertEquals("Espresso", coffe.getName());
    }

    @Test
    public void testGetDescription() {
        Coffe coffe = new Coffe();
        coffe.setDescription("Strong coffee");
        assertEquals("Strong coffee", coffe.getDescription());
    }

    @Test
    public void testGetPrice() {
        Coffe coffe = new Coffe();
        coffe.setPrice(150);
        assertEquals(150, coffe.getPrice());
    }
}