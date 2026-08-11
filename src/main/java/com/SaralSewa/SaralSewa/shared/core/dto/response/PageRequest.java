package com.SaralSewa.SaralSewa.shared.core.dto.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageRequest {
    private int page = 0;
    private int size = 10;
    private String sortBy = "id";
    private String sortDirection = "ASC";

    public org.springframework.data.domain.Pageable toPageable() {
        Sort.Direction direction = "DESC".equalsIgnoreCase(sortDirection)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return org.springframework.data.domain.PageRequest.of(page, size, Sort.by(direction, sortBy));
    }

    public static PageRequest of(int page, int size) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(page);
        pageRequest.setSize(size);
        return pageRequest;
    }
}
