package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(value= AccessType.FIELD)
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUDGET_ID", nullable = false)
    private Long budgetId;


    // Optional one to many relationship, not every transaction is assigned to budget
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="BUDGET_TRANSACTION", joinColumns = @JoinColumn(name="BUDGET_ID"),
        inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    @Column(name = "GOAL_AMOUNT", nullable = false)
    private BigDecimal goalAmount;

    @Column(name = "NAME", nullable = false)
    private String NAME;

    @Column(name = "PERIOD", nullable = false)
    private String PERIOD;

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setGoalAmount(BigDecimal goalAmount) {
        this.goalAmount = goalAmount;
    }

    public BigDecimal getGoalAmount() {
        return goalAmount;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setPERIOD(String PERIOD) {
        this.PERIOD = PERIOD;
    }

    public String getPERIOD() {
        return PERIOD;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId + '\'' +
                "goalAmount=" + goalAmount + '\'' +
                "NAME=" + NAME + '\'' +
                "PERIOD=" + PERIOD + '\'' +
                '}';
    }
}
