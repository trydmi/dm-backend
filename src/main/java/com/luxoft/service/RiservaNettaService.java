package com.luxoft.service;

import com.luxoft.dto.ExportDataDto;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public interface RiservaNettaService {
    void export(LocalDate date, HttpServletResponse response);
    ExportDataDto getData(LocalDate date);
}
