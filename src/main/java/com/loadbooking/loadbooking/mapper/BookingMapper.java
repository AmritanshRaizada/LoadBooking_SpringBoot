package com.loadbooking.loadbooking.mapper;

import com.loadbooking.loadbooking.dto.BookingDTO;
import com.loadbooking.loadbooking.model.Booking;
import com.loadbooking.loadbooking.model.Load;

public class BookingMapper {

    public static BookingDTO toBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setLoadId(booking.getLoad().getId());
        bookingDTO.setTransporterId(booking.getTransporterId());
        bookingDTO.setProposedRate(booking.getProposedRate());
        bookingDTO.setComment(booking.getComment());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setRequestedAt(booking.getRequestedAt());
        return bookingDTO;
    }

    public static Booking toBooking(BookingDTO bookingDTO, Load load) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setLoad(load);
        booking.setTransporterId(bookingDTO.getTransporterId());
        booking.setProposedRate(bookingDTO.getProposedRate());
        booking.setComment(bookingDTO.getComment());
        booking.setStatus(bookingDTO.getStatus());
        booking.setRequestedAt(bookingDTO.getRequestedAt());
        return booking;
    }
}
