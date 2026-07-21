package com.SaralSewa.SaralSewa.shared.repository.searchRepo;

import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import java.util.List;

public interface SearchRepository<T> {
    Long count(SearchParam searchParam);
    List<T> getAll(SearchParam searchParam);
}