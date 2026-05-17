package com.logiqpool.async_api_aggregator.client;

import com.logiqpool.async_api_aggregator.dto.StockResponse;

public interface StockClient {
    public StockResponse getStock();
}
