package com.project.demo.models;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class User {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String telephone;
    private String password;


    public static final class UserBuilder {

        private Long id;
        private String name;
        private String lastName;
        private String email;
        private String telephone;
        private String password;

        private UserBuilder() {
        }


        @Contract(value = " -> new", pure = true)
        public static @NotNull UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder witId(Long id) {
            this.id = id;
            return this;
        }
        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.name = this.name;
            user.telephone = this.telephone;
            user.email = this.email;
            user.lastName = this.lastName;
            user.password = this.password;
            return user;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }
}
