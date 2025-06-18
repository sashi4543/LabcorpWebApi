package com.labcorp.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OrderRequest {
    private Customer customer;
    private List<Item> items;
    private Payment payment;

    public static class Customer {
        private String name;
        private String email;
        private Address address;

        public static class Address {
            private String street;
            private String city;
            private String state;
            private String zipcode;
            private String country;

            // Getters and Setters
            public String getStreet() { return street; }
            public void setStreet(String street) { this.street = street; }
            public String getCity() { return city; }
            public void setCity(String city) { this.city = city; }
            public String getState() { return state; }
            public void setState(String state) { this.state = state; }
            public String getZipcode() { return zipcode; }
            public void setZipcode(String zipcode) { this.zipcode = zipcode; }
            public String getCountry() { return country; }
            public void setCountry(String country) { this.country = country; }
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }
    }

    public static class Item {
        @JsonProperty("product_id")
        private String productId;
        private String name;
        private int quantity;
        private double price;

        // Getters and Setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }

    public static class Payment {
        private String method;
        @JsonProperty("card_number")
        private String cardNumber;
        private String expiry;
        private double amount;
        private String currency;

        // Getters and Setters
        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }
        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
        public String getExpiry() { return expiry; }
        public void setExpiry(String expiry) { this.expiry = expiry; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
    }

    // Getters and Setters for main class
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
} 