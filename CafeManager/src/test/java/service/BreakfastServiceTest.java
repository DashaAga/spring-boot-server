package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.codejava.CafeManager.model.Breakfast;
import net.codejava.CafeManager.repository.BreakfastRepository;
import net.codejava.CafeManager.service.BreakfastService;
import org.springframework.data.domain.Sort;

public class BreakfastServiceTest {

    @Mock
    private BreakfastRepository breakfastRepository;

    @InjectMocks
    private BreakfastService breakfastService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Breakfast breakfast = new Breakfast();
        breakfast.setId(1);
        breakfast.setName("Eggs and Bacon");
        breakfast.setPrice(799);

        when(breakfastRepository.getOne(1)).thenReturn(breakfast);

        Breakfast result = breakfastService.findById(1);

        assertEquals(1, result.getId());
        assertEquals("Eggs and Bacon", result.getName());
        assertEquals(799, result.getPrice());
    }

    @Test
    public void testFindAll() {
        List<Breakfast> breakfasts = new ArrayList<>();
        Breakfast breakfast1 = new Breakfast();
        breakfast1.setId(1);
        breakfast1.setName("Eggs and Bacon");
        breakfast1.setPrice(799);
        Breakfast breakfast2 = new Breakfast();
        breakfast2.setId(2);
        breakfast2.setName("Pancakes");
        breakfast2.setPrice(599);

        breakfasts.add(breakfast1);
        breakfasts.add(breakfast2);

        when(breakfastRepository.findAll()).thenReturn(breakfasts);

        List<Breakfast> result = breakfastService.findAll();

        assertEquals(2, result.size());
        assertEquals("Eggs and Bacon", result.get(0).getName());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Pancakes", result.get(1).getName());
        assertEquals(599, result.get(1).getPrice());
    }

    @Test
    public void testFindAllWithSort() {
        List<Breakfast> breakfasts = new ArrayList<>();
        Breakfast breakfast1 = new Breakfast();
        breakfast1.setId(1);
        breakfast1.setName("Eggs and Bacon");
        breakfast1.setPrice(799);
        Breakfast breakfast2 = new Breakfast();
        breakfast2.setId(2);
        breakfast2.setName("Pancakes");
        breakfast2.setPrice(599);

        breakfasts.add(breakfast1);
        breakfasts.add(breakfast2);

        when(breakfastRepository.findAll(Sort.by("name"))).thenReturn(breakfasts);

        List<Breakfast> result = breakfastService.findAll("name");

        assertEquals(2, result.size());
        assertEquals("Eggs and Bacon", result.get(0).getName());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Pancakes", result.get(1).getName());
        assertEquals(599, result.get(1).getPrice());
    }
    @Test
    public void saveBreakfast_ShouldReturnSavedBreakfast() {
        List<Breakfast> breakfasts = new ArrayList<>();
        Breakfast breakfast1 = new Breakfast();
        breakfast1.setId(1);
        breakfast1.setName("Eggs and Bacon");
        breakfast1.setPrice(799);
        Breakfast breakfast2 = new Breakfast();
        breakfast2.setId(2);
        breakfast2.setName("Pancakes");
        breakfast2.setPrice(599);

        // Arrange
        when(breakfastRepository.save(any(Breakfast.class))).thenReturn(breakfast1);

        // Act
        Breakfast savedBreakfast = breakfastService.saveBreakfast(breakfast1);

        // Assert
        assertNotNull(savedBreakfast);
        assertEquals(breakfast1, savedBreakfast);
        verify(breakfastRepository, times(1)).save(breakfast1);
    }

    @Test
    public void deleteById_ShouldDeleteBreakfastById() {
        // Arrange
        int id = 1;

        // Act
        breakfastService.deleteById(id);

        // Assert
        verify(breakfastRepository, times(1)).deleteById(id);
    }
//ShouldReturnMatchingBreakfasts_WhenSearchLineMatches
    @Test
    void searchBreakfast() {
        // Arrange
        String searchLine = "pancake";
        List<Breakfast> breakfasts = new ArrayList<>();
        Breakfast breakfast1 = new Breakfast();
        breakfast1.setId(1);
        breakfast1.setName("Pancake");
        breakfast1.setDescription("A delicious pancake");
        breakfast1.setPrice(399);
        Breakfast breakfast2 = new Breakfast();
        breakfast2.setId(2);
        breakfast2.setName("Blueberry Pancake");
        breakfast2.setDescription("A pancake with blueberries");
        breakfast2.setPrice(499);
        List<Breakfast> breakfastList = new ArrayList<>();
        breakfastList.add(breakfast1);
        breakfastList.add(breakfast2);

        when(breakfastRepository.searchBreakfast(searchLine)).thenReturn(breakfastList);

        // Act
        List<Breakfast> foundBreakfasts = breakfastService.searchBreakfast(searchLine);

        // Assert
        assertEquals(2, foundBreakfasts.size());
        assertEquals("Pancake", foundBreakfasts.get(0).getName());
        assertEquals("Blueberry Pancake", foundBreakfasts.get(1).getName());
    }
//ShouldReturnEmptyList_WhenSearchLineDoesNotMatch
    @Test
    void searchBreakfast_Empty() {
        // Arrange
        String searchLine = "muffin";
        List<Breakfast> breakfastList = new ArrayList<>();

        when(breakfastRepository.searchBreakfast(searchLine)).thenReturn(breakfastList);

        // Act
        List<Breakfast> foundBreakfasts = breakfastService.searchBreakfast(searchLine);

        // Assert
        assertEquals(0, foundBreakfasts.size());
    }
}