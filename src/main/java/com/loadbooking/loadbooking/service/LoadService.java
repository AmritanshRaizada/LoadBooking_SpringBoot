package com.loadbooking.loadbooking.service;

import com.loadbooking.loadbooking.dto.LoadDTO;
import com.loadbooking.loadbooking.mapper.LoadMapper;
import com.loadbooking.loadbooking.model.Load;
import com.loadbooking.loadbooking.model.LoadStatus;
import com.loadbooking.loadbooking.repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public LoadDTO createLoad(LoadDTO loadDTO) {
        Load load = LoadMapper.toLoad(loadDTO);
        load.setStatus(LoadStatus.POSTED);
        return LoadMapper.toLoadDTO(loadRepository.save(load));
    }

    public Page<LoadDTO> getLoads(String shipperId, String truckType, LoadStatus status, String origin, String destination, Pageable pageable) {
        if (origin != null && destination != null) {
            return new org.springframework.data.domain.PageImpl<>(loadRepository.findByFacilityLoadingPointAndFacilityUnloadingPoint(origin, destination).stream().map(LoadMapper::toLoadDTO).collect(java.util.stream.Collectors.toList()));
        }
        return loadRepository.findAll(pageable).map(LoadMapper::toLoadDTO);
    }

    public LoadDTO getLoadById(UUID id) {
        return loadRepository.findById(id).map(LoadMapper::toLoadDTO).orElse(null);
    }

    public LoadDTO updateLoad(UUID id, LoadDTO loadDTO) {
        return loadRepository.findById(id).map(load -> {
            load.setShipperId(loadDTO.getShipperId());
            load.setFacility(LoadMapper.toFacility(loadDTO.getFacility()));
            load.setProductType(loadDTO.getProductType());
            load.setTruckType(loadDTO.getTruckType());
            load.setNoOfTrucks(loadDTO.getNoOfTrucks());
            load.setWeight(loadDTO.getWeight());
            load.setComment(loadDTO.getComment());
            load.setDatePosted(loadDTO.getDatePosted());
            load.setStatus(loadDTO.getStatus());
            return LoadMapper.toLoadDTO(loadRepository.save(load));
        }).orElse(null);
    }

    public void deleteLoad(UUID id) {
        loadRepository.deleteById(id);
    }
}
