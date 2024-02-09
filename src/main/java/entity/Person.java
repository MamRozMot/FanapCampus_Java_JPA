package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nationalCode;
    @ManyToMany
    @JoinTable(name = "person_account",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;
}
