package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ACCOUNT_ID", nullable = false)
    private Account account;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal AMOUNT;

    @Column(name = "CLOSING_BALANCE", nullable = false)
    private BigDecimal closingBalance;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "LAST_UPDATED_BY", nullable = false)
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_DATE", nullable = false)
    private Date lastUpdatedDate;

    @Column(name = "NOTES")
    private String NOTES;

    @Column(name = "TITLE", nullable = false)
    private String TITLE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID", nullable = false)
    private Long transactionId;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    public void setAMOUNT(BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public BigDecimal getAMOUNT() {
        return AMOUNT;
    }

    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }

    public BigDecimal getClosingBalance() {
        return closingBalance;
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

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }

    public String getNOTES() {
        return NOTES;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "AMOUNT=" + AMOUNT + '\'' +
                "closingBalance=" + closingBalance + '\'' +
                "createdBy=" + createdBy + '\'' +
                "createdDate=" + createdDate + '\'' +
                "initialBalance=" + initialBalance + '\'' +
                "lastUpdatedBy=" + lastUpdatedBy + '\'' +
                "lastUpdatedDate=" + lastUpdatedDate + '\'' +
                "NOTES=" + NOTES + '\'' +
                "TITLE=" + TITLE + '\'' +
                "transactionId=" + transactionId + '\'' +
                "transactionType=" + transactionType + '\'' +
                '}';
    }
}
