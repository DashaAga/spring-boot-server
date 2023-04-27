package net.codejava.CafeManager.repository;

import net.codejava.CafeManager.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторий классов дессертов
 */

public interface CafeRepository extends JpaRepository<Cafe, Integer> {
    /**
     * Запрос для БД для вывода строк, в которых содержится searchline
     */
    @Query("SELECT c FROM Cafe c WHERE " +
            "c.id LIKE CONCAT('%',:searchline, '%')" +
            "Or c.name LIKE CONCAT('%',:searchline, '%')" +
            "Or c.price LIKE CONCAT('%',:searchline, '%')" +
            "Or c.description LIKE CONCAT('%', :searchline, '%')")
    List<Cafe> searchCafe(@Param("searchline") String searchline);
}