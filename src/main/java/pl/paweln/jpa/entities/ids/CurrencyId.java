package pl.paweln.jpa.entities.ids;

import pl.paweln.jpa.entities.Currency;

import java.io.Serializable;

public class CurrencyId implements Serializable  {
    private String NAME;
    private String countryName;

    public CurrencyId() {

    }
    public CurrencyId(String NAME, String countryName) {
        this.NAME = NAME;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
