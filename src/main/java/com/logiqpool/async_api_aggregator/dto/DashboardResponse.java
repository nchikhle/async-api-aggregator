package com.logiqpool.async_api_aggregator.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private NewsResponse news;
    private StockResponse stock;
    private WeatherResponse weather;
}
