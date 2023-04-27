package net.codejava.CafeManager.repository;

import net.codejava.CafeManager.model.Breakfast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Репозиторий классов завтрака
 */

public interface BreakfastRepository extends JpaRepository<Breakfast, Integer> {
    /**
     * Запрос для БД для вывода строк, в которых содержится searchline_breakfast
     */
    @Query("SELECT c FROM Breakfast c WHERE " +
            "c.id LIKE CONCAT('%',:searchline_breakfast, '%')" +
            "Or c.name LIKE CONCAT('%',:searchline_breakfast, '%')" +
            "Or c.price LIKE CONCAT('%',:searchline_breakfast, '%')" +
            "Or c.structure LIKE CONCAT('%',:searchline_breakfast, '%')" +
            "Or c.description LIKE CONCAT('%', :searchline_breakfast, '%')")
    List<Breakfast> searchBreakfast(@Param("searchline_breakfast") String searchline_breakfast);
}
