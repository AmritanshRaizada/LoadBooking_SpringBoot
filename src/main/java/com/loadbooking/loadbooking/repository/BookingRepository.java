package com.loadbooking.loadbooking.repository;

import com.loadbooking.loadbooking.model.Booking;
import com.loadbooking.loadbooking.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByLoadIdAndTransporterIdAndStatus(UUID loadId, String transporterId, BookingStatus status);
}
