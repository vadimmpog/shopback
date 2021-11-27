package com.example.shopback.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String login;
    private String name;
    private String password;
    private Integer age;
    private String gender;
    private String role;
    private Boolean enabled = true;

    public void update(User u){
        if (this.login == null || this.login.equals("")) this.setLogin(u.getLogin());
        if (this.name == null || this.name.equals("")) this.setName(u.getName());
        if (this.password == null || this.password.equals("")) this.setPassword(u.getPassword());
        if (this.age == null || this.age == 0) this.setAge(u.getAge());
        if (this.gender == null || this.gender.equals("")) this.setGender(u.getGender());
        if (this.role == null || this.role.equals("")) this.setRole(u.getRole());
        if (this.enabled == null) this.setEnabled(u.getEnabled());
    }
    public boolean passwordIsEmpty(){
        return this.password.equals("");
    }
}
