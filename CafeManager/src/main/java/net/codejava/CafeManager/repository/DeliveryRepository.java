package net.codejava.CafeManager.repository;

import net.codejava.CafeManager.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Репозиторий классов доставки
 */

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    /**
     * Запрос для БД для вывода строк, в которых содержится searchline_delivery
     */
    @Query("SELECT c FROM Delivery c WHERE " +
            "c.id LIKE CONCAT('%',:searchline_delivery, '%')" +
            "Or c.fio LIKE CONCAT('%',:searchline_delivery, '%')" +
            "Or c.price LIKE CONCAT('%',:searchline_delivery, '%')" +
            "Or c.num LIKE CONCAT('%',:searchline_delivery, '%')" +
            "Or c.structure LIKE CONCAT('%',:searchline_delivery, '%')" +
            "Or c.address LIKE CONCAT('%', :searchline_delivery, '%')")
    List<Delivery> searchDelivery(@Param("searchline_delivery") String searchline_delivery);
}