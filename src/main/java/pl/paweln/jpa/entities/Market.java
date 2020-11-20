package pl.paweln.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "market")
public class Market  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MARKET_ID", nullable = false)
    private Long marketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns(
            {
                    @JoinColumn(name="CURRENCY_NAME", referencedColumnName = "NAME"),
                    @JoinColumn(name="COUNTRY_NAME", referencedColumnName = "COUNTRY_NAME")
            })
    private Currency currency;

    @Column(name = "MARKET_NAME")
    private String marketName;

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketName() {
        return marketName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Market{" +
                "marketId=" + marketId + '\'' +
                "currencyName=" + this.getCurrency().getNAME() + '\'' +
                "countryName=" + this.getCurrency().getCountryName() + '\'' +
                "marketName=" + marketName + '\'' +
                '}';
    }
}
