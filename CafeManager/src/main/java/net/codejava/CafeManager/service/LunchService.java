package net.codejava.CafeManager.service;

import net.codejava.CafeManager.model.Lunch;
import net.codejava.CafeManager.repository.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LunchService {

    private final LunchRepository lunchRepository;

    @Autowired
    public LunchService(LunchRepository lunchRepository) {
        this.lunchRepository = lunchRepository;
    }

    public Lunch findById(Integer id){
        return lunchRepository.getOne(id);
    }
    public List<Lunch> findAll(){
        return lunchRepository.findAll();
    }

    public List<Lunch> findAll(String column){
        return lunchRepository.findAll(Sort.by(column));
    }
    public Lunch saveLunch(Lunch lunch){
        return lunchRepository.save(lunch);
    }

    public void deleteById(Integer id){
        lunchRepository.deleteById(id);
    }
    public List<Lunch> searchLunch(String searchline) {
        return lunchRepository.searchLunch(searchline);
    }
}