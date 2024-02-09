package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
//@Table(name = "account_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private Integer code;
    private Double maxAmount;
    @OneToMany
    @JoinColumn(name = "accountType")
    @OrderColumn(name = "acc_idx")
    private List<Account> accounts;
}
