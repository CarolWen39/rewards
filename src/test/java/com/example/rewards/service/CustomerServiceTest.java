package com.example.rewards.service;


import com.example.rewards.pojo.dto.CustomerResponseDTO.*;
import com.example.rewards.pojo.entity.Customer;
import com.example.rewards.pojo.entity.Transaction;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;


    @Test
    public void shouldReturnAll() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1, "111", "111@gmail.com"));
        customerList.add(new Customer(2, "222", "22@gmail.com"));
        customerList.add(new Customer(3, "333", "3333@gmail.com"));

        given(customerRepository.findAll()).willReturn(customerList);

        List<Customer> expected = customerService.getAllCustomers().getCustomerDTOList()
                .stream().map(c -> new Customer(c)).collect(Collectors.toList());

        assertEquals(expected, customerList);

    }

    @Test
    public void findCustomerById() {
        final Customer customer = new Customer(1, "Test", "test@gmail.com");
        given(customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));
        final CustomerDTO customerdto = customerService.getCustomerById(customer.getId());
        assertThat(customerdto).isNotNull();
    }

    @Test
    public void shouldSavedCustomerSuccessfully() {
        final Customer customer = new Customer(1, "Test", "test@gmail.com");
        given(customerRepository.save(customer)).willAnswer(invocation -> invocation.getArgument(0));
        Long customerId = customerService.insertCustomer(customer);
        assertThat(customerId).isNotNull();


    }

//    @Test
//    public void shouldUpdateSuccessfully() {
//        final Customer customer = new Customer(1, "before", "before@mail.com");
//        given(customerRepository.save(customer)).willReturn(customer);
//        final Long id = customerService.updateCustomer(customer.getId(), customer);
//        assertEquals(id, customer.getId());
//
//
//    }

    @Test
    public void shouldBeDelete() {
        final long id = 1;
        customerService.deleteCustomer(id);
        customerService.deleteCustomer(id);

        verify(customerRepository, times(2)).deleteById(id);
    }

    private List<Transaction> generateTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction((long)1, new Customer(1, "111", "111@gmail.com"), 200, 1));
        transactions.add(new Transaction((long)2, new Customer(1, "111", "111@gmail.com"), 99, 1));
//        transactions.add(new Transaction((long)3, new Customer(2, "222", "22@gmail.com"), 101, 1));
//        transactions.add(new Transaction((long)4, new Customer(3, "333", "3333@gmail.com"), 50, 1));
//        transactions.add(new Transaction((long)5, new Customer(2, "111", "22@gmail.com"), 50, 2));
        transactions.add(new Transaction((long)6, new Customer(1, "111", "111@gmail.com"), 99, 2));
//        transactions.add(new Transaction((long)7, new Customer(3, "333", "3333@gmail.com"), 101, 3));
        transactions.add(new Transaction((long)7, new Customer(1, "111", "111@gmail.com"), 50, 3));
        transactions.add(new Transaction((long)7, new Customer(1, "111", "111@gmail.com"), 120, 4));
        return transactions;
    }
    @Test
    public void calculateReward() {
        List<Transaction> transactions = generateTransactions();
        assertEquals(customerService.calculateReward(transactions), 438);
    }

}
