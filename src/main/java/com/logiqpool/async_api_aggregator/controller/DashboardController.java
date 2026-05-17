package com.logiqpool.async_api_aggregator.controller;

import com.logiqpool.async_api_aggregator.dto.DashboardResponse;
import com.logiqpool.async_api_aggregator.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    public final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(){
        DashboardResponse dashboardResponse =dashboardService.getDashboardResponse();
        return ResponseEntity.status(HttpStatus.OK).body(dashboardResponse);
    }
}
