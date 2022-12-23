package com.luxoft.service.impl;

import com.luxoft.dto.ExportDataDto;
import com.luxoft.exception.RiservaNotFoundException;
import com.luxoft.mapper.MyMapper;
import com.luxoft.model.RiservaNetta;
import com.luxoft.repo.RiservaNettaRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RiservaNettaServiceImplTest {
    @Mock
    private RiservaNettaRepository repository;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletOutputStream outputStream;
    @Mock
    private RiservaNetta riservaNetta;
    @Mock
    private MyMapper mapper;
    @Mock
    private ExportDataDto exportDataDto;
    @InjectMocks
    private RiservaNettaServiceImpl service;


    @BeforeEach
    void init() {
        riservaNetta = RiservaNetta.builder()
                .date(LocalDate.of(2022, 10, 10))
                .corpNr(20d)
                .corpPr(35d)
                .build();
    }

    @Test
    void exportShouldExportSuccessfullyWhenDateExists() throws IOException {
        doReturn(Optional.of(riservaNetta)).when(repository)
                .findByDate(riservaNetta.getDate());
        doReturn(outputStream).when(response).getOutputStream();
        service.export(riservaNetta.getDate(), response);

        verify(repository, times(1)).findByDate(riservaNetta.getDate());
        verify(response, times(1)).getOutputStream();
        verifyNoMoreInteractions(repository, response);
    }

    @Test
    void exportShouldThrowErrorWhenDateNotFound() {
        doThrow(new RiservaNotFoundException(riservaNetta.getDate()))
                .when(repository).findByDate(riservaNetta.getDate());

        assertThrows(RiservaNotFoundException.class, () ->
                service.export(riservaNetta.getDate(), response));
        verify(repository).findByDate(riservaNetta.getDate());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getDataShouldGetDataSuccessfulyWhenDateExists() {
        doReturn(Optional.of(riservaNetta)).when(repository)
                .findByDate(riservaNetta.getDate());
        doReturn(exportDataDto).when(mapper).riservaNettaToExportDataDto(riservaNetta);

        service.getData(riservaNetta.getDate());

        verify(repository, times(1)).findByDate(riservaNetta.getDate());
        verify(mapper, times(1)).riservaNettaToExportDataDto(riservaNetta);
        verifyNoMoreInteractions(repository, mapper);
    }
}