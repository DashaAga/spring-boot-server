package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.codejava.CafeManager.model.Coffe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.codejava.CafeManager.repository.CoffeTeaRepository;
import net.codejava.CafeManager.service.CoffeTeaService;
import org.springframework.data.domain.Sort;

public class CoffeTeaServiceTest {

    @Mock
    private CoffeTeaRepository coffeteaRepository;

    @InjectMocks
    private CoffeTeaService coffeteaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Coffe coffe = new Coffe();
        coffe.setId(1);
        coffe.setName("Hot americano");
        coffe.setPrice(399);

        when(coffeteaRepository.getOne(1)).thenReturn(coffe);

        Coffe result = coffeteaService.findById(1);

        assertEquals(1, result.getId());
        assertEquals("Hot americano", result.getName());
        assertEquals(399, result.getPrice());
    }

    @Test
    public void testFindAll() {
        List<Coffe> drinks = new ArrayList<>();
        Coffe coffe1 = new Coffe();
        coffe1.setId(1);
        coffe1.setName("Latte strawberry");
        coffe1.setPrice(799);
        Coffe coffe2 = new Coffe();
        coffe2.setId(2);
        coffe2.setName("Latte");
        coffe2.setPrice(499);

        drinks.add(coffe1);
        drinks.add(coffe2);

        when(coffeteaRepository.findAll()).thenReturn(drinks);

        List<Coffe> result = coffeteaService.findAll();

        assertEquals(2, result.size());
        assertEquals("Latte strawberry", result.get(0).getName());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Latte", result.get(1).getName());
        assertEquals(499, result.get(1).getPrice());
    }

    @Test
    public void testFindAllWithSort() {
        List<Coffe> drinks = new ArrayList<>();
        Coffe coffe1 = new Coffe();
        coffe1.setId(1);
        coffe1.setName("Latte strawberry");
        coffe1.setPrice(799);
        Coffe coffe2 = new Coffe();
        coffe2.setId(2);
        coffe2.setName("Latte");
        coffe2.setPrice(499);

        drinks.add(coffe1);
        drinks.add(coffe2);

        when(coffeteaRepository.findAll(Sort.by("name"))).thenReturn(drinks);

        List<Coffe> result = coffeteaService.findAll("name");

        assertEquals(2, result.size());
        assertEquals("Latte strawberry", result.get(0).getName());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Latte", result.get(1).getName());
        assertEquals(499, result.get(1).getPrice());
    }
    @Test
    public void saveCoffe() {
        List<Coffe> drinks = new ArrayList<>();
        Coffe coffe1 = new Coffe();
        coffe1.setId(1);
        coffe1.setName("Latte strawberry");
        coffe1.setPrice(799);
        Coffe coffe2 = new Coffe();
        coffe2.setId(2);
        coffe2.setName("Latte");
        coffe2.setPrice(499);

        // Arrange
        when(coffeteaRepository.save(any(Coffe.class))).thenReturn(coffe1);

        // Act
        Coffe savedCoffe = coffeteaService.saveCoffeTea(coffe1);

        // Assert
        assertNotNull(savedCoffe);
        assertEquals(coffe1, savedCoffe);
        verify(coffeteaRepository, times(1)).save(coffe1);
    }

    @Test
    public void deleteById() {
        // Arrange
        int id = 1;

        // Act
        coffeteaService.deleteById(id);

        // Assert
        verify(coffeteaRepository, times(1)).deleteById(id);
    }
    //ShouldReturnMatchingBreakfasts_WhenSearchLineMatches
    @Test
    void searchCoffe() {
        // Arrange
        String searchLine = "Coffe";
        Coffe breakfast1 = new Coffe();
        breakfast1.setId(1);
        breakfast1.setName("Coffe");
        breakfast1.setDescription("A delicious coffe");
        breakfast1.setPrice(399);
        Coffe breakfast2 = new Coffe();
        breakfast2.setId(2);
        breakfast2.setName("Blueberry Coffe");
        breakfast2.setDescription("A Coffe with blueberries");
        breakfast2.setPrice(499);
        List<Coffe> breakfastList = new ArrayList<>();
        breakfastList.add(breakfast1);
        breakfastList.add(breakfast2);

        when(coffeteaRepository.searchCoffe(searchLine)).thenReturn(breakfastList);

        // Act
        List<Coffe> foundBreakfasts = coffeteaService.searchCoffe(searchLine);

        // Assert
        assertEquals(2, foundBreakfasts.size());
        assertEquals("Coffe", foundBreakfasts.get(0).getName());
        assertEquals("Blueberry Coffe", foundBreakfasts.get(1).getName());
    }
    //ShouldReturnEmptyList_WhenSearchLineDoesNotMatch
    @Test
    void searchCoffe_Empty() {
        // Arrange
        String searchLine = "muffin";
        List<Coffe> drinksList = new ArrayList<>();

        when(coffeteaRepository.searchCoffe(searchLine)).thenReturn(drinksList);

        // Act
        List<Coffe> foundDrinks = coffeteaService.searchCoffe(searchLine);

        // Assert
        assertEquals(0, foundDrinks.size());
    }
}