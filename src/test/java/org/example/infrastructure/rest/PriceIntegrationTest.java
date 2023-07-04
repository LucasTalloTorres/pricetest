package org.example.infrastructure.rest;

import org.example.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PriceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPriceNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T10:00:00+00:00")
                        .param("productId", "99999")
                        .param("brandId", "9")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetMethodArgumentNotValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "nosoyunafecha")
                        .param("productId", "99999")
                        .param("brandId", "9")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetPrice1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T10:00:00+00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPrice2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T16:00:00+00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPrice3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-14T21:00:00+00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPrice4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-15T10:00:00+00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-15T11:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPrice5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2020-06-16T21:00:00+00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }
}