package com.rewards.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Data
public class RewardResponse {
    private String customerId;
    private Map<String,Integer> monthlyPoints;
    private Integer totalPoints;

    public RewardResponse(String customerId, Map<String, Integer> monthlyPoints, Integer totalPoints) {
        this.customerId = customerId;
        this.monthlyPoints = monthlyPoints;
        this.totalPoints = totalPoints;
    }
}
