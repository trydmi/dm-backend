package com.luxoft.service.impl;

import com.luxoft.dto.ExportDataDto;
import com.luxoft.exception.ReportExportException;
import com.luxoft.exception.RiservaNotFoundException;
import com.luxoft.exporter.RiservaNettaExporter;
import com.luxoft.mapper.MyMapper;
import com.luxoft.model.RiservaNetta;
import com.luxoft.repo.RiservaNettaRepository;
import com.luxoft.service.RiservaNettaService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiservaNettaServiceImpl implements RiservaNettaService {
    private final RiservaNettaRepository repository;
    private final MyMapper mapper;

    public void export(LocalDate date, HttpServletResponse response) {
        RiservaNetta riservaByDate = repository.findByDate(date)
                .orElseThrow(() -> new RiservaNotFoundException(date));
        log.info("IN export - riserva found: {}", riservaByDate);
        RiservaNettaExporter exporter = new RiservaNettaExporter(riservaByDate);
        try (XSSFWorkbook workbook = exporter.export();
             var outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
            log.info("IN export - riserva exported successfully");
        } catch (IOException e) {
            throw new ReportExportException(e);
        }
    }

    @Override
    public ExportDataDto getData(LocalDate date) {
        RiservaNetta byDate = repository.findByDate(date)
                .orElseThrow(() -> new RiservaNotFoundException(date));
        log.info("IN getData - riserva found: {}", byDate);
        return mapper.riservaNettaToExportDataDto(byDate);
    }
}
