package com.logiqpool.async_api_aggregator.service;

import com.logiqpool.async_api_aggregator.client.NewsClient;
import com.logiqpool.async_api_aggregator.client.StockClient;
import com.logiqpool.async_api_aggregator.client.WeatherClient;
import com.logiqpool.async_api_aggregator.dto.DashboardResponse;
import com.logiqpool.async_api_aggregator.dto.NewsResponse;
import com.logiqpool.async_api_aggregator.dto.StockResponse;
import com.logiqpool.async_api_aggregator.dto.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        when(weatherClient.getWeather()).thenReturn(weather);
        when(newsClient.getNews()).thenReturn(news);
        when(stockClient.getStock()).thenReturn(stock);

        //Act
        DashboardResponse response = dashboardService.getDashboardResponse();

        //Assert
        assertNotNull(response);
        assertEquals(response.getWeather().getCity(),"Sheffield");
        verify(weatherClient).getWeather();
        verify(newsClient).getNews();
        verify(stockClient).getStock();
    }
    @Test
    void shouldFetchDataAsynchronously() {

        //Arrange
        WeatherResponse weather =new WeatherResponse();
        weather.setCity("Sheffield");

        NewsResponse news = new NewsResponse();
        StockResponse stock = new StockResponse();

        //Mock
        when(weatherClient.getWeather())
                .thenAnswer(invocation -> {
                    Thread.sleep(1000);
                    return weather;
                });

        when(newsClient.getNews())
                .thenAnswer(invocation -> {
                    Thread.sleep(1000);
                    return news;
                });

        when(stockClient.getStock())
                .thenAnswer(invocation -> {
                    Thread.sleep(1000);
                    return stock;
                });

        //Act
        long start = System.currentTimeMillis();
        DashboardResponse response = dashboardService.getDashboardResponse();
        long end = System.currentTimeMillis();
        //Assert
        long duration = end - start;
        System.out.println(duration);
        assertTrue(duration < 2500);
    }

}