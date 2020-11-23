package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bond")
public class Bond extends Investment {

    @Column(name = "INTEREST_RATE", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "MATURITY_DATE", nullable = false)
    private Date maturityDate;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal VALUE;

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

    public void setVALUE(BigDecimal VALUE) {
        this.VALUE = VALUE;
    }

    public BigDecimal getVALUE() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "investmentId=" + investmentId + '\'' +
                "interestRate=" + interestRate + '\'' +
                "ISSUER=" + ISSUER + '\'' +
                "maturityDate=" + maturityDate + '\'' +
                "NAME=" + NAME + '\'' +
                "portfolioId=" + getPortfolio().getPortfolioId() + '\'' +
                "purchaseDate=" + purchaseDate + '\'' +
                "VALUE=" + VALUE + '\'' +
                '}';
    }
}
