package aze.coders.dao;

import aze.coders.entity.Customer;

import java.util.List;

public interface Customers {
    void insert(Customer customer);
    void update(int id, Customer customer);
    void delete(int id);
    Customer findById(int id);
    List<Customer> findAll();
}
