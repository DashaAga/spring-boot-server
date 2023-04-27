package net.codejava.CafeManager.service;

import net.codejava.CafeManager.model.Breakfast;
import net.codejava.CafeManager.repository.BreakfastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreakfastService {

    private final BreakfastRepository breakfastRepository;

    @Autowired
    public BreakfastService(BreakfastRepository breakfastRepository) {
        this.breakfastRepository = breakfastRepository;
    }

    public Breakfast findById(Integer id){
        return breakfastRepository.getOne(id);
    }
    public List<Breakfast> findAll(){
        return breakfastRepository.findAll();
    }

    public List<Breakfast> findAll(String column){
        return breakfastRepository.findAll(Sort.by(column));
    }
    public Breakfast saveBreakfast(Breakfast breakfast){
        return breakfastRepository.save(breakfast);
    }

    public void deleteById(Integer id){
        breakfastRepository.deleteById(id);
    }
    public List<Breakfast> searchBreakfast(String searchline) {
        return breakfastRepository.searchBreakfast(searchline);
    }
}