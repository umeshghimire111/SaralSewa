package com.SaralSewa.SaralSewa.shared.core.dto.response;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> extends ModelBase {
    private Integer total;
    private List<T> records;

    public static <T> PageResponse<T> from(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setTotal((int) page.getTotalElements());
        response.setRecords(page.getContent());
        return response;
    }
}