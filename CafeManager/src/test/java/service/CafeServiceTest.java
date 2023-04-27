package service;
import net.codejava.CafeManager.model.Cafe;
import net.codejava.CafeManager.repository.CafeRepository;
import net.codejava.CafeManager.service.CafeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CafeServiceTest {

    private CafeService cafeService;

    @Mock
    private CafeRepository cafeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cafeService = new CafeService(cafeRepository);
    }

    @Test
    public void findById() {
        Cafe cafe = new Cafe();
        cafe.setId(1);
        cafe.setName("Test Cafe");

        when(cafeRepository.getOne(1)).thenReturn(cafe);

        Cafe foundCafe = cafeService.findById(1);

        Assertions.assertEquals(cafe, foundCafe);
    }

    @Test
    public void findAll_noParameter() {
        Cafe cafe1 = new Cafe();
        cafe1.setId(1);
        cafe1.setName("Cafe 1");

        Cafe cafe2 = new Cafe();
        cafe2.setId(2);
        cafe2.setName("Cafe 2");

        List<Cafe> cafes = Arrays.asList(cafe1, cafe2);

        when(cafeRepository.findAll()).thenReturn(cafes);

        List<Cafe> foundCafes = cafeService.findAll();

        Assertions.assertEquals(cafes, foundCafes);
    }

    @Test
    public void findAll_sortByColumn() {
        Cafe cafe1 = new Cafe();
        cafe1.setId(1);
        cafe1.setName("Cafe 1");

        Cafe cafe2 = new Cafe();
        cafe2.setId(2);
        cafe2.setName("Cafe 2");

        List<Cafe> cafes = Arrays.asList(cafe1, cafe2);

        when(cafeRepository.findAll(Sort.by("name"))).thenReturn(cafes);

        List<Cafe> foundCafes = cafeService.findAll("name");

        Assertions.assertEquals(cafes, foundCafes);
    }

    @Test
    public void saveCafe() {
        Cafe cafe = new Cafe();
        cafe.setName("Test Cafe");

        when(cafeRepository.save(any(Cafe.class))).thenReturn(cafe);

        Cafe savedCafe = cafeService.saveCafe(cafe);

        Assertions.assertEquals(cafe, savedCafe);
    }

    @Test
    public void deleteById() {
        Cafe cafe = new Cafe();
        cafe.setId(1);
        cafe.setName("Test Cafe");

        doNothing().when(cafeRepository).deleteById(1);

        cafeService.deleteById(1);

        verify(cafeRepository, times(1)).deleteById(1);
    }
    @Test
    public void testSearchCafe() {
        // Arrange
        List<Cafe> expectedCafes = new ArrayList<>();
        Cafe cafe1 = new Cafe();
        cafe1.setId(1);
        cafe1.setName("Cafe 1");

        Cafe cafe2 = new Cafe();
        cafe2.setId(2);
        cafe2.setName("Cafe 2");
        when(cafeRepository.searchCafe(anyString())).thenReturn(expectedCafes);

        // Act
        List<Cafe> actualCafes = cafeService.searchCafe("Cafe");

        // Assert
        Assertions.assertEquals(expectedCafes.size(), actualCafes.size());
        for (int i = 0; i < expectedCafes.size(); i++) {
            Assertions.assertEquals(expectedCafes.get(i).getId(), actualCafes.get(i).getId());
            Assertions.assertEquals(expectedCafes.get(i).getName(), actualCafes.get(i).getName());
        }
    }
}