package net.codejava.CafeManager.service;

import net.codejava.CafeManager.model.Cafe;
import net.codejava.CafeManager.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;

    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public Cafe findById(Integer id){
        return cafeRepository.getOne(id);
    }
    public List<Cafe> findAll(){
        return cafeRepository.findAll();
    }

    public List<Cafe> findAll(String column){
        return cafeRepository.findAll(Sort.by(column));
    }
    public Cafe saveCafe(Cafe cafe){
        return cafeRepository.save(cafe);
    }

    public void deleteById(Integer id){
        cafeRepository.deleteById(id);
    }
    public List<Cafe> searchCafe(String searchline) {
        return cafeRepository.searchCafe(searchline);
    }
}