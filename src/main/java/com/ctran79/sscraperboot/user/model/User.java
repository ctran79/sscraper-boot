package com.ctran79.sscraperboot.user.model;

import com.ctran79.sscraperboot.base.IdEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ctran79
 */

@Entity
@Table(name = "app_user")
@Data
public class User extends IdEntity {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private boolean active;

    @Column
    private String fullName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role")
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();
}
