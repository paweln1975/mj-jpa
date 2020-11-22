package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_gen")
    @TableGenerator(name="table_gen", table = "IFINANCES_KEYS",
            pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE")
    @Column(name = "INVESTMENT_ID")
    protected Long investmentId;

    @JoinColumn(name="PORTFOLIO_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @Column(name = "ISSUER", nullable = false)
    protected String ISSUER;
    @Column(name = "NAME", nullable = false)
    protected String NAME;
    @Column(name = "PURCHASE_DATE", nullable = false)
    protected Date purchaseDate;

    public void setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
    }

    public String getISSUER() {
        return ISSUER;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Long getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(Long investmentId) {
        this.investmentId = investmentId;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
