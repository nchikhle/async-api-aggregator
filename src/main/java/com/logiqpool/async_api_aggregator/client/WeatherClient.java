package com.logiqpool.async_api_aggregator.client;

import com.logiqpool.async_api_aggregator.dto.WeatherResponse;

public interface WeatherClient {
    public WeatherResponse getWeather();
}
