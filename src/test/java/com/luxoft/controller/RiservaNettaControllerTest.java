package com.luxoft.controller;

import com.luxoft.service.RiservaNettaService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = {RiservaNettaController.class})
@ExtendWith(MockitoExtension.class)
class RiservaNettaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RiservaNettaService riservaNettaService;
    @MockBean
    private HttpServletResponse response;

    private String contentType;
    private String headerKey;
    private String headerValue;
    private LocalDate date;

    @BeforeEach
    void init() {
        date = LocalDate.of(2021, 1, 1);
        contentType = "application/octet-stream";
        headerKey = "Content-Disposition";
        headerValue = String.format("attachment; filename=riserva-htc-htcs_%d-%d-%d.xlsx",
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    @Test
    void getReportShouldBeSuccessfulWhenDateExists() throws Exception {
        doNothing().when(riservaNettaService).export(date, response);

        mockMvc.perform(get("/api/v1/riserva-netta/{date}", date))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.header().string(headerKey, headerValue));

    }
}