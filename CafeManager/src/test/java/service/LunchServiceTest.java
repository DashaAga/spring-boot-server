package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.codejava.CafeManager.model.Lunch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.codejava.CafeManager.repository.LunchRepository;
import net.codejava.CafeManager.service.LunchService;
import org.springframework.data.domain.Sort;

public class LunchServiceTest {

    @Mock
    private LunchRepository LunchRepository;

    @InjectMocks
    private LunchService LunchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Lunch Lunch = new Lunch();
        Lunch.setId(1);
        Lunch.setName("Lunch");
        Lunch.setPrice(399);

        when(LunchRepository.getOne(1)).thenReturn(Lunch);

        Lunch result = LunchService.findById(1);

        assertEquals(1, result.getId());
        assertEquals("Lunch", result.getName());
        assertEquals(399, result.getPrice());
    }

    @Test
    public void testFindAll() {
        List<Lunch> drinks = new ArrayList<>();
        Lunch Lunch1 = new Lunch();
        Lunch1.setId(1);
        Lunch1.setName("Lunch1");
        Lunch1.setPrice(799);
        Lunch Lunch2 = new Lunch();
        Lunch2.setId(2);
        Lunch2.setName("Lunch2");
        Lunch2.setPrice(499);

        drinks.add(Lunch1);
        drinks.add(Lunch2);

        when(LunchRepository.findAll()).thenReturn(drinks);

        List<Lunch> result = LunchService.findAll();

        assertEquals(2, result.size());
        assertEquals("Lunch1", result.get(0).getName());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Lunch2", result.get(1).getName());
        assertEquals(499, result.get(1).getPrice());
    }

    @Test
    public void testFindAllWithSort() {
        List<Lunch> drinks = new ArrayList<>();
        Lunch Lunch1 = new Lunch();
        Lunch1.setId(1);
        Lunch1.setName("Lunch1");
        Lunch1.setPrice(799);
        Lunch Lunch2 = new Lunch();
        Lunch2.setId(2);
        Lunch2.setName("Lunch2");
        Lunch2.setPrice(499);

        drinks.add(Lunch1);
        drinks.add(Lunch2);

        when(LunchRepository.findAll(Sort.by("name"))).thenReturn(drinks);

        List<Lunch> result = LunchService.findAll("name");

        assertEquals(2, result.size());
        assertEquals("Lunch1", result.get(0).getName());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Lunch2", result.get(1).getName());
        assertEquals(499, result.get(1).getPrice());
    }
    @Test
    public void saveLunch() {
        List<Lunch> drinks = new ArrayList<>();
        Lunch Lunch1 = new Lunch();
        Lunch1.setId(1);
        Lunch1.setName("Lunch1");
        Lunch1.setPrice(799);
        Lunch Lunch2 = new Lunch();
        Lunch2.setId(2);
        Lunch2.setName("Lunch2");
        Lunch2.setPrice(499);

        // Arrange
        when(LunchRepository.save(any(Lunch.class))).thenReturn(Lunch1);

        // Act
        Lunch savedLunch = LunchService.saveLunch(Lunch1);

        // Assert
        assertNotNull(savedLunch);
        assertEquals(Lunch1, savedLunch);
        verify(LunchRepository, times(1)).save(Lunch1);
    }

    @Test
    public void deleteById() {
        // Arrange
        int id = 1;

        // Act
        LunchService.deleteById(id);

        // Assert
        verify(LunchRepository, times(1)).deleteById(id);
    }
    //ShouldReturnMatchingBreakfasts_WhenSearchLineMatches
    @Test
    void searchLunch() {
        // Arrange
        String searchLine = "Lunch";
        Lunch breakfast1 = new Lunch();
        breakfast1.setId(1);
        breakfast1.setName("Lunch");
        breakfast1.setDescription("A delicious Lunch");
        breakfast1.setPrice(399);
        Lunch breakfast2 = new Lunch();
        breakfast2.setId(2);
        breakfast2.setName("Blueberry Lunch");
        breakfast2.setDescription("A Lunch with blueberries");
        breakfast2.setPrice(499);
        List<Lunch> breakfastList = new ArrayList<>();
        breakfastList.add(breakfast1);
        breakfastList.add(breakfast2);

        when(LunchRepository.searchLunch(searchLine)).thenReturn(breakfastList);

        // Act
        List<Lunch> foundBreakfasts = LunchService.searchLunch(searchLine);

        // Assert
        assertEquals(2, foundBreakfasts.size());
        assertEquals("Lunch", foundBreakfasts.get(0).getName());
        assertEquals("Blueberry Lunch", foundBreakfasts.get(1).getName());
    }
    //ShouldReturnEmptyList_WhenSearchLineDoesNotMatch
    @Test
    void searchLunch_Empty() {
        // Arrange
        String searchLine = "muffin";
        List<Lunch> drinksList = new ArrayList<>();

        when(LunchRepository.searchLunch(searchLine)).thenReturn(drinksList);

        // Act
        List<Lunch> foundDrinks = LunchService.searchLunch(searchLine);

        // Assert
        assertEquals(0, foundDrinks.size());
    }
}