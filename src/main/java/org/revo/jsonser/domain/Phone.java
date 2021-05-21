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
public class Phone implements Serializable {
    @Id
    @GeneratedValue
    @JsonView(View.BasicPhone.class)
    private Long id;
    @JsonView(View.BasicPhone.class)
    private String name;
    @ManyToOne
    @JoinColumn
    @JsonView(View.DetailedPhone.class)
    private User user;

}
