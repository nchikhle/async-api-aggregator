package com.logiqpool.async_api_aggregator.service;

import com.logiqpool.async_api_aggregator.client.NewsClient;
import com.logiqpool.async_api_aggregator.client.StockClient;
import com.logiqpool.async_api_aggregator.client.WeatherClient;
import com.logiqpool.async_api_aggregator.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DashboardService {
    public final NewsClient newsClient;
    public final WeatherClient weatherClient;
    public final StockClient stockClient;

    public DashboardResponse getDashboardResponse(){
        DashboardResponse response = new DashboardResponse();
        response.setWeather(weatherClient.getWeather());
        response.setNews(newsClient.getNews());
        response.setStock(stockClient.getStock());
        return response;
    }
}
