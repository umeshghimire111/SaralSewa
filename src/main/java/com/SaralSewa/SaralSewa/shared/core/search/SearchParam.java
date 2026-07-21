package com.SaralSewa.SaralSewa.shared.core.search;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SearchParam extends ModelBase {
    private Integer firstRow;
    private Integer pageSize;
    private List<SearchFieldParam> searchFieldParams;
    private Map<String, Object> param = new HashMap<>();

    public Integer getFirstRow() {
        if (firstRow == null) {
            return 0;
        }
        return firstRow;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 10;
        }
        if (pageSize == -1) {
            return Integer.MAX_VALUE;
        }
        return pageSize;
    }
}