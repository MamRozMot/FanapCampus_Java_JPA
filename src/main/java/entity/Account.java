package entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accountType")
    private AccountType accountType;
    private String accountNumber;
    private Double amount;
    private Date insertDate;
    @ManyToMany
    @JoinTable(name = "person_account",
    joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> owners;
}
