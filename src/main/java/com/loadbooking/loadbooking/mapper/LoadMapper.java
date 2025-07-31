package com.loadbooking.loadbooking.mapper;

import com.loadbooking.loadbooking.dto.FacilityDTO;
import com.loadbooking.loadbooking.dto.LoadDTO;
import com.loadbooking.loadbooking.model.Facility;
import com.loadbooking.loadbooking.model.Load;

public class LoadMapper {

    public static LoadDTO toLoadDTO(Load load) {
        LoadDTO loadDTO = new LoadDTO();
        loadDTO.setId(load.getId());
        loadDTO.setShipperId(load.getShipperId());
        loadDTO.setFacility(toFacilityDTO(load.getFacility()));
        loadDTO.setProductType(load.getProductType());
        loadDTO.setTruckType(load.getTruckType());
        loadDTO.setNoOfTrucks(load.getNoOfTrucks());
        loadDTO.setWeight(load.getWeight());
        loadDTO.setComment(load.getComment());
        loadDTO.setDatePosted(load.getDatePosted());
        loadDTO.setStatus(load.getStatus());
        return loadDTO;
    }

    public static Load toLoad(LoadDTO loadDTO) {
        Load load = new Load();
        load.setId(loadDTO.getId());
        load.setShipperId(loadDTO.getShipperId());
        load.setFacility(toFacility(loadDTO.getFacility()));
        load.setProductType(loadDTO.getProductType());
        load.setTruckType(loadDTO.getTruckType());
        load.setNoOfTrucks(loadDTO.getNoOfTrucks());
        load.setWeight(loadDTO.getWeight());
        load.setComment(loadDTO.getComment());
        load.setDatePosted(loadDTO.getDatePosted());
        load.setStatus(loadDTO.getStatus());
        return load;
    }

    public static FacilityDTO toFacilityDTO(Facility facility) {
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setLoadingPoint(facility.getLoadingPoint());
        facilityDTO.setUnloadingPoint(facility.getUnloadingPoint());
        facilityDTO.setLoadingDate(facility.getLoadingDate());
        facilityDTO.setUnloadingDate(facility.getUnloadingDate());
        return facilityDTO;
    }

    public static Facility toFacility(FacilityDTO facilityDTO) {
        Facility facility = new Facility();
        facility.setLoadingPoint(facilityDTO.getLoadingPoint());
        facility.setUnloadingPoint(facilityDTO.getUnloadingPoint());
        facility.setLoadingDate(facilityDTO.getLoadingDate());
        facility.setUnloadingDate(facilityDTO.getUnloadingDate());
        return facility;
    }
}
