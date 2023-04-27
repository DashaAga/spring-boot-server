package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.codejava.CafeManager.model.Delivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.codejava.CafeManager.repository.DeliveryRepository;
import net.codejava.CafeManager.service.DeliveryService;
import org.springframework.data.domain.Sort;

public class DeliveryServiceTest {

    @Mock
    private DeliveryRepository DeliveryRepository;

    @InjectMocks
    private DeliveryService DeliveryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Delivery Delivery = new Delivery();
        Delivery.setId(1);
        Delivery.setStructure("Delivery");
        Delivery.setPrice(399);

        when(DeliveryRepository.getOne(1)).thenReturn(Delivery);

        Delivery result = DeliveryService.findById(1);

        assertEquals(1, result.getId());
        assertEquals("Delivery", result.getStructure());
        assertEquals(399, result.getPrice());
    }

    @Test
    public void testFindAll() {
        List<Delivery> deliveries = new ArrayList<>();
        Delivery Delivery1 = new Delivery();
        Delivery1.setId(1);
        Delivery1.setStructure("Delivery1");
        Delivery1.setPrice(799);
        Delivery Delivery2 = new Delivery();
        Delivery2.setId(2);
        Delivery2.setStructure("Delivery2");
        Delivery2.setPrice(499);

        deliveries.add(Delivery1);
        deliveries.add(Delivery2);

        when(DeliveryRepository.findAll()).thenReturn(deliveries);

        List<Delivery> result = DeliveryService.findAll();

        assertEquals(2, result.size());
        assertEquals("Delivery1", result.get(0).getStructure());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Delivery2", result.get(1).getStructure());
        assertEquals(499, result.get(1).getPrice());
    }

    @Test
    public void testFindAllWithSort() {
        List<Delivery> deliveries = new ArrayList<>();
        Delivery Delivery1 = new Delivery();
        Delivery1.setId(1);
        Delivery1.setStructure("Delivery1");
        Delivery1.setPrice(799);
        Delivery Delivery2 = new Delivery();
        Delivery2.setId(2);
        Delivery2.setStructure("Delivery2");
        Delivery2.setPrice(499);

        deliveries.add(Delivery1);
        deliveries.add(Delivery2);

        when(DeliveryRepository.findAll(Sort.by("structure"))).thenReturn(deliveries);

        List<Delivery> result = DeliveryService.findAll("structure");

        assertEquals(2, result.size());
        assertEquals("Delivery1", result.get(0).getStructure());
        assertEquals(799, result.get(0).getPrice());
        assertEquals("Delivery2", result.get(1).getStructure());
        assertEquals(499, result.get(1).getPrice());
    }
    @Test
    public void saveDelivery() {
        List<Delivery> deliveries = new ArrayList<>();
        Delivery Delivery1 = new Delivery();
        Delivery1.setId(1);
        Delivery1.setStructure("Delivery1");
        Delivery1.setPrice(799);
        Delivery Delivery2 = new Delivery();
        Delivery2.setId(2);
        Delivery2.setStructure("Delivery2");
        Delivery2.setPrice(499);

        // Arrange
        when(DeliveryRepository.save(any(Delivery.class))).thenReturn(Delivery1);

        // Act
        Delivery savedDelivery = DeliveryService.saveDelivery(Delivery1);

        // Assert
        assertNotNull(savedDelivery);
        assertEquals(Delivery1, savedDelivery);
        verify(DeliveryRepository, times(1)).save(Delivery1);
    }

    @Test
    public void deleteById() {
        // Arrange
        int id = 1;

        // Act
        DeliveryService.deleteById(id);

        // Assert
        verify(DeliveryRepository, times(1)).deleteById(id);
    }
    //ShouldReturnMatchingBreakfasts_WhenSearchLineMatches
    @Test
    void searchDelivery() {
        // Arrange
        String searchLine = "Delivery";
        Delivery breakfast1 = new Delivery();
        breakfast1.setId(1);
        breakfast1.setStructure("Delivery");
        breakfast1.setPrice(399);
        Delivery breakfast2 = new Delivery();
        breakfast2.setId(2);
        breakfast2.setStructure("Blueberry Delivery");
        breakfast2.setPrice(499);
        List<Delivery> breakfastList = new ArrayList<>();
        breakfastList.add(breakfast1);
        breakfastList.add(breakfast2);

        when(DeliveryRepository.searchDelivery(searchLine)).thenReturn(breakfastList);

        // Act
        List<Delivery> foundBreakfasts = DeliveryService.searchDelivery(searchLine);

        // Assert
        assertEquals(2, foundBreakfasts.size());
        assertEquals("Delivery", foundBreakfasts.get(0).getStructure());
        assertEquals("Blueberry Delivery", foundBreakfasts.get(1).getStructure());
    }
    //ShouldReturnEmptyList_WhenSearchLineDoesNotMatch
    @Test
    void searchDelivery_Empty() {
        // Arrange
        String searchLine = "muffin";
        List<Delivery> deliveriesList = new ArrayList<>();

        when(DeliveryRepository.searchDelivery(searchLine)).thenReturn(deliveriesList);

        // Act
        List<Delivery> founddeliveries = DeliveryService.searchDelivery(searchLine);

        // Assert
        assertEquals(0, founddeliveries.size());
    }
}