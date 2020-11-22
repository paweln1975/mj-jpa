package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "stock")
public class Stock extends Investment {

    @Column(name = "QUANTITY", nullable = false)
    private Long QUANTITY;

    @Column(name = "SHARE_PRICE", nullable = false)
    private BigDecimal sharePrice;

    public void setQUANTITY(Long QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public Long getQUANTITY() {
        return QUANTITY;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ISSUER=" + ISSUER + '\'' +
                "NAME=" + NAME + '\'' +
                "portfolioId=" + getPortfolio().getPortfolioId() + '\'' +
                "purchaseDate=" + purchaseDate + '\'' +
                "QUANTITY=" + QUANTITY + '\'' +
                "sharePrice=" + sharePrice + '\'' +
                "investmentId=" + investmentId + '\'' +
                '}';
    }
}
