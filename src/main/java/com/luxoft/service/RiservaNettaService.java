package com.luxoft.service;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public interface RiservaNettaService {
    void export(LocalDate date, HttpServletResponse response);
}
