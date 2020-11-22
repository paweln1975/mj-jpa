package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "stock")
public class Stock extends Investment {

    @Column(name = "PORTFOLIO_ID")
    private Long portfolioId;

    @Column(name = "QUANTITY", nullable = false)
    private Long QUANTITY;

    @Column(name = "SHARE_PRICE", nullable = false)
    private BigDecimal sharePrice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID", nullable = false)
    private Long stockId;

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

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

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getStockId() {
        return stockId;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ISSUER=" + ISSUER + '\'' +
                "NAME=" + NAME + '\'' +
                "portfolioId=" + portfolioId + '\'' +
                "purchaseDate=" + purchaseDate + '\'' +
                "QUANTITY=" + QUANTITY + '\'' +
                "sharePrice=" + sharePrice + '\'' +
                "stockId=" + stockId + '\'' +
                '}';
    }
}
