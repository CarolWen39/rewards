package com.example.rewards.pojo.entity;

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

//    @Column
//    private double rewards;

    @OneToMany(mappedBy = "id", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    List<Transaction> customerTransactions;

}
