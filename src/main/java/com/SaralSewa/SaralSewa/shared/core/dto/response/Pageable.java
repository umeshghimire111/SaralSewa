package com.SaralSewa.SaralSewa.shared.core.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Pageable<T> extends ModelBase{
    private Integer total;
    private List<T> records;
}
