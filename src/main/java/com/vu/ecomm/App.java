package com.vu.ecomm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Configure Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Perform CRUD Operations
        // Create
        createProduct(sessionFactory);

        // Read
//        readProduct(sessionFactory, 1); // Replace '1' with the ID to retrieve

        // Update
//        updateProduct(sessionFactory, 1, 150.0); // Replace '1' and '150.0' with ID and new price

        // Delete
//        deleteProduct(sessionFactory, 1); // Replace '1' with the ID to delete

        // Close session factory
        sessionFactory.close();
    }

    // Create
    public static void createProduct(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Add a new product
        Product product = new Product();
        product.setName("Sample Product");
        product.setDescription("This is a sample product");
        product.setPrice(100.0);
        product.setInventory(10);

        session.persist(product); // Use persist instead of save
        transaction.commit();
        session.close();

        System.out.println("Product created: " + product.getName());
    }

    // Read
    public static void readProduct(SessionFactory sessionFactory, int productId) {
        Session session = sessionFactory.openSession();

        // Retrieve product by ID
        Product product = session.find(Product.class, productId); // Use find instead of get
        if (product != null) {
            System.out.println("Retrieved Product: " + product.getName() + ", Price: " + product.getPrice());
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }

        session.close();
    }

    // Update
    public static void updateProduct(SessionFactory sessionFactory, int productId, double newPrice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Retrieve and update product
        Product product = session.find(Product.class, productId); // Use find instead of get
        if (product != null) {
            product.setPrice(newPrice);
            session.merge(product); // Use merge instead of update
            transaction.commit();
            System.out.println("Product updated: " + product.getName() + ", New Price: " + product.getPrice());
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }

        session.close();
    }

    // Delete
    public static void deleteProduct(SessionFactory sessionFactory, int productId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Retrieve and delete product
        Product product = session.find(Product.class, productId); // Use find instead of get
        if (product != null) {
            session.remove(product); // Use remove instead of delete
            transaction.commit();
            System.out.println("Product deleted: " + product.getName());
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }

        session.close();
    }
}
