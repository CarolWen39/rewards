package com.example.rewards.pojo.entity;

import com.example.rewards.pojo.dto.CustomerResponseDTO.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String email;


    @OneToMany(mappedBy = "id", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    List<Transaction> customerTransactions;

    public Customer(long id, String name, String email) {
        this.id = id;
        this.userName = name;
        this.email = email;
    }
    public Customer(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.email = customerDTO.getEmail();
        this.userName = customerDTO.getUserName();
    }
}
