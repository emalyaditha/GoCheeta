package com.example.GoCheeta.model;

import javax.persistence.*;
@Entity
@Table(name = "customer")
public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, unique = true, length = 45)
        private String email;

        @Column(length = 255, nullable = false)
        private String password;

        @Column(length = 45, nullable = false, name = "first_name")
        private String firstName;

        @Column(length = 45, nullable = false, name = "last_name")
        private String lastName;

        private boolean enabled;

    public Customer() {

    }

    public Customer(Integer id,String email,String password, String firstName, String lastName, Boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }

    public Integer getId() {
            return id;
        }

        public void setId(Integer id) { this.id = id; }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

}
