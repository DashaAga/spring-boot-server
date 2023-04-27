
package net.codejava.CafeManager.repository;

import net.codejava.CafeManager.model.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Репозиторий классов ланча
 */

public interface LunchRepository extends JpaRepository<Lunch, Integer> {
    /**
     * Запрос для БД для вывода строк, в которых содержится searchline_lunch
     */
    @Query("SELECT c FROM Lunch c WHERE " +
            "c.id LIKE CONCAT('%',:searchline_lunch, '%')" +
            "Or c.name LIKE CONCAT('%',:searchline_lunch, '%')" +
            "Or c.price LIKE CONCAT('%',:searchline_lunch, '%')" +
            "Or c.structure LIKE CONCAT('%',:searchline_lunch, '%')" +
            "Or c.description LIKE CONCAT('%', :searchline_lunch, '%')")
    List<Lunch> searchLunch(@Param("searchline_lunch") String searchline_lunch);
}
