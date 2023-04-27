package net.codejava.CafeManager.service;

import net.codejava.CafeManager.model.Coffe;
import net.codejava.CafeManager.repository.CoffeTeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoffeTeaService {

    private final CoffeTeaRepository coffeTeaRepository;

    @Autowired
    public CoffeTeaService(CoffeTeaRepository coffeTeaRepository) {
        this.coffeTeaRepository = coffeTeaRepository;
    }

    public Coffe findById(Integer id){
        return coffeTeaRepository.getOne(id);
    }
    public List<Coffe> findAll(){
        return coffeTeaRepository.findAll();
    }

    public List<Coffe> findAll(String column){
        return coffeTeaRepository.findAll(Sort.by(column));
    }
    public Coffe saveCoffeTea(Coffe Coffe){
        return coffeTeaRepository.save(Coffe);
    }

    public void deleteById(Integer id){
        coffeTeaRepository.deleteById(id);
    }
    public List<Coffe> searchCoffe(String searchline) {
        return coffeTeaRepository.searchCoffe(searchline);
    }
}