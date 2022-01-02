package com.example.deliveryApp.security.entity;

import com.example.deliveryApp.security.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Integer id;
    @Column(name = "NAME")
    String name;

    public Role(RoleEnum roleEnum) {
        super();
        this.name = roleEnum.getRoleName();
    }

//    @ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
//    private Set<User> users=new HashSet<>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
