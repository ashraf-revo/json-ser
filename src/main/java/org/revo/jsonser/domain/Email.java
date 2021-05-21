package org.revo.jsonser.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.revo.jsonser.View;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {
    @Id
    @GeneratedValue
    @JsonView(View.BasicEmail.class)
    private Long id;
    @JsonView(View.BasicEmail.class)
    private String name;
    @ManyToOne
    @JoinColumn
    @JsonView(View.DetailedEmail.class)
    private User user;

}
