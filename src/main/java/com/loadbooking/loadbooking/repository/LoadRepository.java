package com.loadbooking.loadbooking.repository;

import com.loadbooking.loadbooking.model.Load;
import com.loadbooking.loadbooking.model.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {

    List<Load> findByShipperIdAndTruckTypeAndStatus(String shipperId, String truckType, LoadStatus status);

    List<Load> findByFacilityLoadingPointAndFacilityUnloadingPoint(String loadingPoint, String unloadingPoint);
}
