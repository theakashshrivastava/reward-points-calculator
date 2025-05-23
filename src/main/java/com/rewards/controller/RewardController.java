package com.rewards.controller;

import com.rewards.exception.NoTransactionFoundException;
import com.rewards.model.RewardResponse;
import com.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /* POST API to calculate reward points for provided transaction data */
    @PostMapping("/calculate")
    public ResponseEntity<List<RewardResponse>> calculateRewardsForInputData(@RequestBody Map<String,String> request) {
        String customerId = request.get("customerId");
        if(customerId == null || customerId.isEmpty()) {
            throw new NoTransactionFoundException("Transaction list is empty");
        }
        List<RewardResponse> rewardResponses = rewardService.calculateRewards(customerId);
        return ResponseEntity.ok(rewardResponses);
    }
}