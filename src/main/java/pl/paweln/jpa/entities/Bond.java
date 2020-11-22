package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "bond")
public class Bond extends Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOND_ID", nullable = false)
    private Long bondId;

    @Column(name = "INTEREST_RATE", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "MATURITY_DATE", nullable = false)
    private Date maturityDate;

    @Column(name = "PORTFOLIO_ID")
    private Long portfolioId;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal VALUE;

    public void setBondId(Long bondId) {
        this.bondId = bondId;
    }

    public Long getBondId() {
        return bondId;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setVALUE(BigDecimal VALUE) {
        this.VALUE = VALUE;
    }

    public BigDecimal getVALUE() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "bondId=" + bondId + '\'' +
                "interestRate=" + interestRate + '\'' +
                "ISSUER=" + ISSUER + '\'' +
                "maturityDate=" + maturityDate + '\'' +
                "NAME=" + NAME + '\'' +
                "portfolioId=" + portfolioId + '\'' +
                "purchaseDate=" + purchaseDate + '\'' +
                "VALUE=" + VALUE + '\'' +
                '}';
    }
}
