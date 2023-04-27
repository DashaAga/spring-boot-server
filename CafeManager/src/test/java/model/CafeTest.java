package model;

import static org.junit.Assert.assertEquals;

import net.codejava.CafeManager.model.Cafe;
import org.junit.Test;

public class CafeTest {

    @Test
    public void testGettersAndSetters() {
        Cafe cafe = new Cafe();
        cafe.setId(1);
        cafe.setName("Tiramisu");
        cafe.setDescription("Italian coffee-flavoured dessert");
        cafe.setPrice(250);

        assertEquals(Integer.valueOf(1), cafe.getId());
        assertEquals("Tiramisu", cafe.getName());
        assertEquals("Italian coffee-flavoured dessert", cafe.getDescription());
        assertEquals(Integer.valueOf(250), cafe.getPrice());
    }
}