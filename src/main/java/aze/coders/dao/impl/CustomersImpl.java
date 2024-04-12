package aze.coders.dao.impl;

import aze.coders.dao.Customers;
import aze.coders.entity.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.List;

@Repository
public class CustomersImpl implements Customers {
    //    @Autowired
//    JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate jdbcTemplate;
//    private final JdbcTemplate jdbcTemplate;

    public CustomersImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Customer customer) {
        String query = "insert into customers(customer_id, customer_name, address) values(:customerId,:customerName,:address)";
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
//                .addValue("name", customer.getCustomerName())
//                .addValue("id", customer.getCustomerId())
//                .addValue("address", customer.getAddress());
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(customer);
        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void update(int id, Customer customer) {
        try {
            Customer findCustomer = findById(id);
            if (customer.getCustomerName() != null && !customer.getCustomerName().isEmpty())
                findCustomer.setCustomerName(customer.getCustomerName());
            if (customer.getAddress() != null && !customer.getAddress().isEmpty())
                findCustomer.setAddress(customer.getAddress());
            String query = "update customers set customer_name = ?, address=? where customer_id=?";
           // jdbcTemplate.update(query, findCustomer.getCustomerName(), findCustomer.getAddress(), findCustomer.getCustomerId());
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("Bele mushteri yoxdur");
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from customers where customer_id = ?";
        //jdbcTemplate.update(query, id);
    }

    @Override
    public Customer findById(int id) {
        String query = "select * from customers where customer_id = ?";
        return null; //jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(), id);
    }

    @Override
    public List<Customer> findAll() {
        String query = "select * from customers ";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Customer.class));
    }

    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(rs.getInt("customer_id"), rs.getString("customer_name"), rs.getString("address"));
        }
    }
}
