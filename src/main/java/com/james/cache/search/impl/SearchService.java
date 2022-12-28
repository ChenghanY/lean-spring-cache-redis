package com.james.cache.search.impl;

import com.james.cache.search.ISearchService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {

    @Override
    @Cacheable(cacheNames="helloCache", key="#key")
    public String search(String key) {
        return "hello";
    }
}
