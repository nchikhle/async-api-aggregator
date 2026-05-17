package com.logiqpool.async_api_aggregator.service;

import com.logiqpool.async_api_aggregator.client.NewsClient;
import com.logiqpool.async_api_aggregator.client.StockClient;
import com.logiqpool.async_api_aggregator.client.WeatherClient;
import com.logiqpool.async_api_aggregator.dto.DashboardResponse;
import com.logiqpool.async_api_aggregator.dto.NewsResponse;
import com.logiqpool.async_api_aggregator.dto.StockResponse;
import com.logiqpool.async_api_aggregator.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class DashboardService {
    public final NewsClient newsClient;
    public final WeatherClient weatherClient;
    public final StockClient stockClient;

    public DashboardResponse getDashboardResponse(){

        CompletableFuture<WeatherResponse> weatherFuture = CompletableFuture.supplyAsync(weatherClient::getWeather);
        CompletableFuture<NewsResponse> newsFuture = CompletableFuture.supplyAsync(newsClient::getNews);
        CompletableFuture<StockResponse> stockFuture = CompletableFuture.supplyAsync(stockClient::getStock);

        CompletableFuture.allOf(weatherFuture,newsFuture, stockFuture).join();

        DashboardResponse response = new DashboardResponse();

        response.setWeather(weatherFuture.join());
        response.setNews(newsFuture.join());
        response.setStock(stockFuture.join());
        return response;
    }
}
