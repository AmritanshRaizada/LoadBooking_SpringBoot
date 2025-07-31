package com.loadbooking.loadbooking.service;

import com.loadbooking.loadbooking.dto.BookingDTO;
import com.loadbooking.loadbooking.mapper.BookingMapper;
import com.loadbooking.loadbooking.model.Booking;
import com.loadbooking.loadbooking.model.BookingStatus;
import com.loadbooking.loadbooking.model.Load;
import com.loadbooking.loadbooking.model.LoadStatus;
import com.loadbooking.loadbooking.repository.BookingRepository;
import com.loadbooking.loadbooking.repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LoadRepository loadRepository;

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Load load = loadRepository.findById(bookingDTO.getLoadId()).orElse(null);
        if (load == null || load.getStatus() == LoadStatus.CANCELLED) {
            return null;
        }
        Booking booking = BookingMapper.toBooking(bookingDTO, load);
        booking.setStatus(BookingStatus.PENDING);
        return BookingMapper.toBookingDTO(bookingRepository.save(booking));
    }

    public List<BookingDTO> getBookings(UUID loadId, String transporterId, BookingStatus status) {
        return bookingRepository.findByLoadIdAndTransporterIdAndStatus(loadId, transporterId, status)
                .stream()
                .map(BookingMapper::toBookingDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(UUID id) {
        return bookingRepository.findById(id).map(BookingMapper::toBookingDTO).orElse(null);
    }

    public BookingDTO updateBooking(UUID id, BookingDTO bookingDTO) {
        return bookingRepository.findById(id).map(booking -> {
            if (bookingDTO.getStatus() == BookingStatus.ACCEPTED) {
                booking.setStatus(BookingStatus.ACCEPTED);
                Load load = booking.getLoad();
                load.setStatus(LoadStatus.BOOKED);
                loadRepository.save(load);
            } else {
                booking.setStatus(bookingDTO.getStatus());
            }
            booking.setTransporterId(bookingDTO.getTransporterId());
            booking.setProposedRate(bookingDTO.getProposedRate());
            booking.setComment(bookingDTO.getComment());
            booking.setRequestedAt(bookingDTO.getRequestedAt());
            return BookingMapper.toBookingDTO(bookingRepository.save(booking));
        }).orElse(null);
    }

    public void deleteBooking(UUID id) {
        bookingRepository.findById(id).ifPresent(booking -> {
            bookingRepository.deleteById(id);
            Load load = booking.getLoad();
            if (load.getStatus() == LoadStatus.BOOKED) {
                List<Booking> bookings = bookingRepository.findByLoadIdAndTransporterIdAndStatus(load.getId(), null, null);
                if (bookings.isEmpty()) {
                    load.setStatus(LoadStatus.POSTED);
                    loadRepository.save(load);
                }
            }
        });
    }
}
