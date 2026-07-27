package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewReviewResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateReviewRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListReviewResponse;
import com.SaralSewa.SaralSewa.shared.entity.Booking;
import com.SaralSewa.SaralSewa.shared.entity.Review;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.repository.BookingRepository;
import com.SaralSewa.SaralSewa.shared.repository.ServiceProviderRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReviewMapper {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ViewReviewResponse viewDetails(Review review) {
        if (review == null) return null;

        ViewReviewResponse response = new ViewReviewResponse();
        response.setBookingId(review.getBooking() != null ? review.getBooking().getId() : null);
        response.setBookingCode(review.getBooking() != null ? review.getBooking().getBookingCode() : null);
        response.setCustomerId(review.getCustomer() != null ? review.getCustomer().getId() : null);
        response.setCustomerName(review.getCustomer() != null ?
                review.getCustomer().getFirstName() + (review.getCustomer().getLastName() != null ? " " + review.getCustomer().getLastName() : "") : null);
        response.setCustomerEmail(review.getCustomer() != null ? review.getCustomer().getEmail() : null);
        response.setProviderId(review.getProvider() != null ? review.getProvider().getId() : null);
        response.setProviderName(review.getProvider() != null ? review.getProvider().getProfession() : null);
        response.setProviderProfession(review.getProvider() != null ? review.getProvider().getProfession() : null);
        response.setRating(review.getRating());
        response.setReviewTitle(review.getReviewTitle());
        response.setReviewMessage(review.getReviewMessage());
        response.setIsActive(review.getIsActive());
        response.setCreatedAt(review.getCreatedAt());
        response.setUpdatedAt(review.getUpdatedAt());

        return response;
    }

    public abstract ListReviewResponse entityToResponse(Review review);

    public List<ListReviewResponse> listReviews(List<Review> reviews) {
        if (reviews == null) return null;
        return reviews.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Review create(CreateReviewRequest request) {
        if (request == null) return null;

        Booking booking = bookingRepository.findById(request.getBookingId()).orElse(null);
        User customer = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + request.getCustomerId()));
        ServiceProvider provider = serviceProviderRepository.findByUserId(request.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found: " + request.getProviderId()));

        Review review = new Review();
        review.setBooking(booking);
        review.setCustomer(customer);
        review.setProvider(provider);
        review.setRating(request.getRating());
        review.setReviewTitle(request.getReviewTitle());
        review.setReviewMessage(request.getReviewMessage());
        review.setIsActive(true);
        review.setIsDeleted(false);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());

        return review;
    }

    public Review update(UpdateReviewRequest request, Review review) {
        if (request == null || review == null) return review;

        if (request.getRating() != null) {
            review.setRating(request.getRating());
        }
        if (request.getReviewTitle() != null) {
            review.setReviewTitle(request.getReviewTitle());
        }
        if (request.getReviewMessage() != null) {
            review.setReviewMessage(request.getReviewMessage());
        }
        review.setUpdatedAt(LocalDateTime.now());
        return review;
    }

    public Review deactivate(Review review) {
        if (review == null) return review;
        review.setIsActive(false);
        review.setUpdatedAt(LocalDateTime.now());
        return review;
    }

    public Review activate(Review review) {
        if (review == null) return review;
        review.setIsActive(true);
        review.setUpdatedAt(LocalDateTime.now());
        return review;
    }

    public Review softDelete(Review review) {
        if (review == null) return review;
        review.setIsDeleted(true);
        review.setUpdatedAt(LocalDateTime.now());
        return review;
    }
}