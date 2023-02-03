package com.example.rewards.pojo.dto;

import com.example.rewards.pojo.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponseDTO {
    private List<CustomerDTO> customerDTOList;

    @Data
    @AllArgsConstructor
    @Builder
    public static class CustomerDTO {
        private Long id;
        private String userName;
        private String email;
//        private double rewards;

        public CustomerDTO(Customer c) {
            this.id = c.getId();
            this.email = c.getEmail();
            this.userName = c.getUserName();
//            this.rewards = c.getRewards();
        }
    }
}
