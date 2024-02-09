package service;

import entity.Account;
import entity.AccountType;
import entity.Person;
import shared.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class AccountingService {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test3");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        try {
            AccountType accountType = getAccountTypeByCode(1, entityManager);
//            AccountType accountType = createAccountType(1, 2000000.0, entityManager);
            Person person1 = createPerson("Soheil", "1234");
            Person person2 = createPerson("Neda", "123456");
            Account account = createAccount(accountType, "1.3.4.4", 300.0);
            Set<Person> personSet = new HashSet<>();
            personSet.add(person1);
            personSet.add(person2);
            account.setOwners(personSet);

            entityManager.getTransaction().begin();
//            entityManager.remove(accountType);
            accountType.getAccounts().add(account);
            entityManager.persist(person1);//insert into Person values...
            entityManager.persist(person2);
            entityManager.persist(account);
//            entityManager.detach(person1);
//            //....
//            entityManager.merge(person1);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
//            entityManagerFactory.close();
        }
    }

    private static Person createPerson(String name, String nationalCode) {
        return Person.builder().name(name).nationalCode(nationalCode).build();
    }

    private static Account createAccount(AccountType accountType, String accountNumber, Double amount) {
        return Account.builder().accountNumber(accountNumber).amount(amount).insertDate(new Date()).
                accountType(accountType).build();
    }

    private static AccountType getAccountTypeByCode(Integer code, EntityManager entityManager) {
        return (AccountType) entityManager.createQuery("select accType from AccountType accType where accType.code = :code")
        .setParameter("code", code).getSingleResult();
    }

    private static AccountType createAccountType(Integer code, Double maxAmount, EntityManager entityManager) {
        AccountType accountType = AccountType.builder().code(code).maxAmount(maxAmount).build();
        entityManager.getTransaction().begin();
        entityManager.persist(accountType);
        entityManager.getTransaction().commit();
        return accountType;
    }
}
