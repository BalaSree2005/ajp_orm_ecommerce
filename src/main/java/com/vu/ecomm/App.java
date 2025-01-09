package com.vu.ecomm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Add a new product
        Product product = new Product();
        product.setName("Sample Product");
        product.setDescription("This is a sample product");
        product.setPrice(100.0);
        product.setInventory(10);
        session.save(product);

        // Retrieve product
        Product retrievedProduct = session.get(Product.class, product.getId());
        System.out.println("Retrieved Product: " + retrievedProduct.getName());

        // Update product
        retrievedProduct.setPrice(120.0);
        session.update(retrievedProduct);

        // Delete product
        session.delete(retrievedProduct);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
