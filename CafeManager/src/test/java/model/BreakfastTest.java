package model;

import net.codejava.CafeManager.model.Breakfast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BreakfastTest {

    @Test
    public void testBreakfast() {
        Breakfast breakfast = new Breakfast();
        breakfast.setId(1);
        breakfast.setName("Omelette");
        breakfast.setStructure("Eggs, cheese, tomatoes");
        breakfast.setDescription("A classic breakfast dish");
        breakfast.setPrice(100);

        Assertions.assertEquals(1, breakfast.getId());
        Assertions.assertEquals("Omelette", breakfast.getName());
        Assertions.assertEquals("Eggs, cheese, tomatoes", breakfast.getStructure());
        Assertions.assertEquals("A classic breakfast dish", breakfast.getDescription());
        Assertions.assertEquals(100, breakfast.getPrice());
    }
}