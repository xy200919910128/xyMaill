package com.xy.maill.search.service;


import com.xy.maill.search.vo.SearchParam;
import com.xy.maill.search.vo.SearchResult;

public interface ProductSearchService {
    public SearchResult getSearchResult(SearchParam searchParam);
}
