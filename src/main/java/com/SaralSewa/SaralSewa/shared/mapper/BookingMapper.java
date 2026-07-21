package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.constant.BookingStatusConstant;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateBookingRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListBookingResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewBookingResponse;
import com.SaralSewa.SaralSewa.shared.entity.*;
import com.SaralSewa.SaralSewa.shared.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookingMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private AvailabilitySlotRepository availabilitySlotRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    public ViewBookingResponse viewDetails(Booking booking) {
        if (booking == null) return null;

        ViewBookingResponse response = new ViewBookingResponse();
        response.setBookingCode(booking.getBookingCode());
        response.setCustomerId(Long.valueOf(booking.getCustomer() != null ? booking.getCustomer().getId() : null));
        response.setCustomerName(booking.getCustomer() != null ?
                booking.getCustomer().getFirstName() + (booking.getCustomer().getLastName() != null ? " " + booking.getCustomer().getLastName() : "") : null);
        response.setCustomerEmail(booking.getCustomer() != null ? booking.getCustomer().getEmail() : null);
        response.setCustomerPhone(booking.getCustomer() != null ? booking.getCustomer().getPhone() : null);
        response.setProviderId(Long.valueOf(booking.getProvider() != null ? booking.getProvider().getId() : null));
        response.setProviderName(booking.getProvider() != null ? booking.getProvider().getProfession() : null);
        response.setProviderProfession(booking.getProvider() != null ? booking.getProvider().getProfession() : null);
        response.setSlotId(Long.valueOf(booking.getSlot() != null ? booking.getSlot().getId() : null));
        response.setSlotDateTime(booking.getSlot() != null ?
                booking.getSlot().getAvailableDate() + " " + booking.getSlot().getStartTime() : null);
        response.setServiceDescription(booking.getServiceDescription());
        response.setCustomerAddress(booking.getCustomerAddress());
        response.setBookingStatusName(booking.getBookingStatus() != null ? booking.getBookingStatus().getName() : null);
        response.setBookingStatusCode(booking.getBookingStatus() != null ? booking.getBookingStatus().getCode() : null);
        response.setTotalAmount(booking.getTotalAmount());
        response.setIsActive(booking.getIsActive());
        response.setCreatedAt(booking.getCreatedAt());
        response.setUpdatedAt(booking.getUpdatedAt());

        return response;
    }

    public abstract ListBookingResponse entityToResponse(Booking booking);

    public List<ListBookingResponse> listBookings(List<Booking> bookings) {
        if (bookings == null) return null;
        return bookings.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Booking create(CreateBookingRequest request) {
        if (request == null) return null;

        User customer = userRepository.findByUserId(request.getCustomerId());
        ServiceProvider provider = serviceProviderRepository.findByUserId(request.getProviderId());
        AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId()).orElse(null);

        Booking booking = new Booking();
        booking.setBookingCode(request.getBookingCode());
        booking.setCustomer(customer);
        booking.setProvider(provider);
        booking.setSlot(slot);
        booking.setServiceDescription(request.getServiceDescription());
        booking.setCustomerAddress(request.getCustomerAddress());
        booking.setTotalAmount(request.getTotalAmount());

        Status defaultStatus = bookingStatusRepository.findByCode(BookingStatusConstant.PENDING.getName());
        booking.setBookingStatus(defaultStatus);

        booking.setIsActive(true);
        booking.setIsDeleted(false);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        return booking;
    }

    public Booking update(UpdateBookingRequest request, Booking booking) {
        if (request == null || booking == null) return booking;

        if (request.getSlotId() != null) {
            AvailabilitySlot slot = availabilitySlotRepository.findById(request.getSlotId()).orElse(null);
            booking.setSlot(slot);
        }
        if (request.getServiceDescription() != null) {
            booking.setServiceDescription(request.getServiceDescription());
        }
        if (request.getCustomerAddress() != null) {
            booking.setCustomerAddress(request.getCustomerAddress());
        }
        if (request.getTotalAmount() != null) {
            booking.setTotalAmount(request.getTotalAmount());
        }
        if (request.getBookingStatusCode() != null) {
            Status status = bookingStatusRepository.findByCode(request.getBookingStatusCode());
            booking.setBookingStatus(status);
        }
        booking.setUpdatedAt(LocalDateTime.now());
        return booking;
    }

    public Booking updateStatus(Booking booking, String statusCode) {
        if (booking == null || statusCode == null) return booking;
        Status status = bookingStatusRepository.findByCode(statusCode);
        booking.setBookingStatus(status);
        booking.setUpdatedAt(LocalDateTime.now());
        return booking;
    }

    public Booking cancel(Booking booking) {
        if (booking == null) return booking;
        Status cancelledStatus = bookingStatusRepository.findByCode(BookingStatusConstant.CANCELLED.getName());
        booking.setBookingStatus(cancelledStatus);
        booking.setUpdatedAt(LocalDateTime.now());
        return booking;
    }

    public Booking complete(Booking booking) {
        if (booking == null) return booking;
        Status completedStatus = bookingStatusRepository.findByCode(BookingStatusConstant.COMPLETED.getName());
        booking.setBookingStatus(completedStatus);
        booking.setUpdatedAt(LocalDateTime.now());
        return booking;
    }

    public Booking softDelete(Booking booking) {
        if (booking == null) return booking;
        booking.setIsDeleted(true);
        booking.setUpdatedAt(LocalDateTime.now());
        return booking;
    }
}