package org.revo.jsonser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.revo.jsonser.View;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue
    @JsonView(View.BasicUser.class)
    private Long id;
    @JsonView(View.BasicUser.class)
    private String name;
    @JsonView(View.BasicUser.class)
    private Status status = Status.ACTIVE;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonView(View.DetailedUser.class)
    private List<Phone> phones = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    @JsonView(View.DetailedUser.class)
    private Set<Email> emails = new HashSet<>();
    @JsonIgnore
    @Transient
    private Class cacheView;
}
