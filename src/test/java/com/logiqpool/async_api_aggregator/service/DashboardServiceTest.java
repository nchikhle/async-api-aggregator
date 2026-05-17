package com.logiqpool.async_api_aggregator.service;

import com.logiqpool.async_api_aggregator.dto.DashboardResponse;
import com.logiqpool.async_api_aggregator.dto.NewsResponse;
import com.logiqpool.async_api_aggregator.dto.StockResponse;
import com.logiqpool.async_api_aggregator.dto.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    WeatherClient weatherClient;

    @Mock
    StockClient stockClient;

    @Mock
    NewsClient newsClient;

    @InjectMocks DashboardService dashboardService;

    @Test
    public void shouldReturnDashboardResponse(){

        //Arrange
        WeatherResponse weather =new WeatherResponse();
        weather.setCity("Sheffield");

        NewsResponse news = new NewsResponse();
        StockResponse stock = new StockResponse();

        when(WeatherClient.getWeather()).thenReturn(weather);
        when(NewsClient.getNews()).thenReturn(news);
        when(StockClient.getStock()).thenReturn(stock);

        //Act
        DashboardResponse response = dashboardService.getDashboardResponse();

        //Assert
        assertNotNull(response);
        assertEquals(response.getWeather().getCity(),"Sheffield");
        verify(WeatherClient);
        verify(NewsClient);
        verify(StockClient);
    }

}