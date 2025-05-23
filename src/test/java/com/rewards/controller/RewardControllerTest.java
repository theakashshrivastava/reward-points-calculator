package com.rewards.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewards.model.Customer_Transaction;
import com.rewards.model.RewardResponse;
import com.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {

        transactionRepository.deleteAll();

        transactionRepository.saveAll(List.of(
                new Customer_Transaction("CUST001", 120.0, LocalDate.of(2025,04,10)),
                new Customer_Transaction("CUST001", 120.0, LocalDate.of(2024,12,22))
        ));
    }

    @Test
    public void testCalculateRewardsForInputData() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/rewards/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerId\":\"CUST001\", \"month\":\"2025-04\"}"))
                .andExpect(status().isOk())
                .andReturn();


        String responseContent = result.getResponse().getContentAsString();

        List<RewardResponse> actualResponses = objectMapper.readValue(
                responseContent,
                new TypeReference<>() {}
        );

        List<RewardResponse> expectedResponses = List.of(
                new RewardResponse("CUST001", Map.of(
                        "2025-04-01", 90),90
                ));
        assertThat(actualResponses).containsExactlyElementsOf(expectedResponses);
    }
}