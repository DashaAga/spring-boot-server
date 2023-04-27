package model;

import net.codejava.CafeManager.model.Lunch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LunchTest {

    @Test
    public void testLunchClass() {
        Lunch lunch = new Lunch();
        lunch.setId(1);
        lunch.setName("Test Lunch");
        lunch.setStructure("Test Structure");
        lunch.setDescription("Test Description");
        lunch.setPrice(100);

        assertEquals(1, lunch.getId());
        assertEquals("Test Lunch", lunch.getName());
        assertEquals("Test Structure", lunch.getStructure());
        assertEquals("Test Description", lunch.getDescription());
        assertEquals(100, lunch.getPrice());
    }
}