package net.codejava.CafeManager.service;

import net.codejava.CafeManager.model.Delivery;
import net.codejava.CafeManager.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery findById(Integer id){
        return deliveryRepository.getOne(id);
    }
    public List<Delivery> findAll(){
        return deliveryRepository.findAll();
    }

    public List<Delivery> findAll(String column){
        return deliveryRepository.findAll(Sort.by(column));
    }
    public Delivery saveDelivery(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    public void deleteById(Integer id){
        deliveryRepository.deleteById(id);
    }
    public List<Delivery> searchDelivery(String searchline) {
        return deliveryRepository.searchDelivery(searchline);
    }
}