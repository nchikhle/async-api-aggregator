package com.logiqpool.async_api_aggregator.client;

import com.logiqpool.async_api_aggregator.dto.NewsResponse;

public interface NewsClient {
    public NewsResponse getNews();
}
