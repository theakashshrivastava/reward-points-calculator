package com.rewards.service;

import com.rewards.model.Customer_Transaction;
import com.rewards.repository.TransactionRepository;
import com.rewards.exception.NoTransactionFoundException;
import com.rewards.model.RewardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
    public class RewardService {

        @Autowired
        private TransactionRepository transactionRepository;
        private static final int monthsToSubtract = 3;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<RewardResponse> calculateRewards(String customerId) {
            List<Customer_Transaction> transactions = transactionRepository.findByCustomerId(customerId);

            if (transactions == null || transactions.isEmpty()) {
                throw new NoTransactionFoundException("No transactions found for customerId: " + customerId);
            }

            Map<String, Map<String, Integer>> customerMonthlyPoints = new HashMap<>();
            LocalDate threeMonthAgo = LocalDate.now().minusMonths(monthsToSubtract);

            for (Customer_Transaction transaction : transactions) {
                if (transaction.getTransactionDate().isBefore(threeMonthAgo)) {
                    continue;
                }

                int points = calculatePoints(transaction.getAmount());
                String month = transaction.getTransactionDate().withDayOfMonth(1).toString();

                customerMonthlyPoints
                        .computeIfAbsent(customerId, k -> new HashMap<>())
                        .merge(month, points, Integer::sum);
            }

            List<RewardResponse> rewardResponses = new ArrayList<>();
            for (Map.Entry<String, Map<String, Integer>> entry : customerMonthlyPoints.entrySet()) {
                String cid = entry.getKey();
                Map<String, Integer> monthlyPoints = entry.getValue();
                int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

                rewardResponses.add(new RewardResponse(cid, monthlyPoints, totalPoints));
            }

            rewardResponses.sort(Comparator.comparing(RewardResponse::getCustomerId));
            return rewardResponses;
        }

        private int calculatePoints(double amount) {
            int points = 0;
            if (amount > 100) {
                points += (int) ((amount - 100) * 2);
                points += 50;
            } else if (amount > 50) {
                points += (int) (amount - 50);
            }
            return points;
        }
    }