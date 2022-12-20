package com.luxoft.controller;

import com.luxoft.service.RiservaNettaService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/riserva-netta")
@RequiredArgsConstructor
public class RiservaNettaController {
    private final RiservaNettaService nettaService;

    @GetMapping("/{date}")
    public ResponseEntity<HttpStatus> getReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=riserva-htc-htcs_%d-%d-%d.xlsx",
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        response.setHeader(headerKey, headerValue);
        nettaService.export(date, response);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
