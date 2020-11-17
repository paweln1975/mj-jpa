package pl.paweln.jpa.entities;

import pl.paweln.jpa.entities.ids.CurrencyId;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@IdClass(CurrencyId.class)
public class Currency {

    @Id
    @Column(name = "NAME", nullable = false)
    private String NAME;

    @Id
    @Column(name = "COUNTRY_NAME", nullable = false)
    private String countryName;

    @Column(name = "SYMBOL")
    private String SYMBOL;

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setSYMBOL(String SYMBOL) {
        this.SYMBOL = SYMBOL;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "NAME=" + NAME + '\'' +
                "countryName=" + countryName + '\'' +
                "SYMBOL=" + SYMBOL + '\'' +
                '}';
    }
}
