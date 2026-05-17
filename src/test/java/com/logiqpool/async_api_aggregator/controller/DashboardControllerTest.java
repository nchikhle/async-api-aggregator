package com.logiqpool.async_api_aggregator.controller;

import com.logiqpool.async_api_aggregator.dto.DashboardResponse;
import com.logiqpool.async_api_aggregator.dto.WeatherResponse;
import com.logiqpool.async_api_aggregator.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(DashboardController.class)
class DashboardControllerTest {
    @MockBean
    DashboardService dashboardService;

    @InjectMocks
    DashboardController dashboardController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnDashboardResponse() throws Exception{
        // Arrange
        WeatherResponse weather =
                new WeatherResponse();

        weather.setCity("London");

        DashboardResponse dashboardResponse =
                new DashboardResponse();

        dashboardResponse.setWeather(weather);

        when(dashboardService.getDashboardResponse()).thenReturn(dashboardResponse);

        // Act
        // Assert
        mockMvc.perform(get("/api/dashboard"))
                .andExpect(status().isOk())
                        .andExpect(
                                jsonPath(
                                        "$.weather.city")
                                        .value("London")
                        );
    }
}