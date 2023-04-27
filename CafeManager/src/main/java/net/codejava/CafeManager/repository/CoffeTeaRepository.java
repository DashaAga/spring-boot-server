package net.codejava.CafeManager.repository;

import net.codejava.CafeManager.model.Coffe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторий классов напитков (чай и кофе)
 */

public interface CoffeTeaRepository extends JpaRepository<Coffe, Integer> {
        /**
         * Запрос для БД для вывода строк, в которых содержится searchline_coffe
         */
        @Query("SELECT c FROM Coffe c WHERE " +
                "c.id LIKE CONCAT('%',:searchline_coffe, '%')" +
                "Or c.name LIKE CONCAT('%',:searchline_coffe, '%')" +
                "Or c.price LIKE CONCAT('%',:searchline_coffe, '%')" +
                "Or c.description LIKE CONCAT('%', :searchline_coffe, '%')")
        List<Coffe> searchCoffe(@Param("searchline_coffe") String searchline_coffe);
}