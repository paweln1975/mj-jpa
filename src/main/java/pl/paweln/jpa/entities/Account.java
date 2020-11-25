package pl.paweln.jpa.entities;

import pl.paweln.jpa.entities.enums.AccountType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "account")
@NamedQueries(
        @NamedQuery(name = "Account.largeDeposit", query = "select distinct t.account from Transaction t" +
                " where t.AMOUNT > :amount and t.transactionType = 'Deposit'")
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="account")
    private List<Transaction> transactionsList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="USER_ACCOUNT", joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
        inverseJoinColumns = @JoinColumn(name="USER_ID"))
    private Set<User> users = new HashSet<>();

    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @Column(name = "CLOSE_DATE", nullable = false)
    private Date closeDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CURRENT_BALANCE", nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "LAST_UPDATED_BY", nullable = false)
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "NAME")
    private String NAME;

    @Column(name = "OPEN_DATE", nullable = false)
    private Date openDate;

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setBankId(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> userSet) {
        this.users = userSet;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId + '\'' +
                "accountType=" + accountType + '\'' +
                "closeDate=" + closeDate + '\'' +
                "createdBy=" + createdBy + '\'' +
                "createdDate=" + createdDate + '\'' +
                "currentBalance=" + currentBalance + '\'' +
                "initialBalance=" + initialBalance + '\'' +
                "lastUpdatedBy=" + lastUpdatedBy + '\'' +
                "lastUpdatedDate=" + lastUpdatedDate + '\'' +
                "NAME=" + NAME + '\'' +
                "openDate=" + openDate + '\'' +
                '}';
    }
}
