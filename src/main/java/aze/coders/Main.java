package aze.coders;

import aze.coders.config.SpringConfig;
import aze.coders.dao.Customers;
import aze.coders.entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
                //new AnnotationConfigApplicationContext(SpringConfig.class);
        Customers customers = context.getBean("customersImpl", Customers.class);
//        System.out.println("insert db");
//        customers.insert(new Customer(98, "TestUser", "address"));
//        System.out.println("get by id only name");
//        System.out.println(customers.findById(93));
//        System.out.println("get all");
        System.out.println(customers.findAll());
//        System.out.println("update");
//        customers.update(93, new Customer(93, "user1", null));
//        customers.delete(93);

    }
}