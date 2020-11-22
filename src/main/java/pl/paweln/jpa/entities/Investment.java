package pl.paweln.jpa.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public abstract class Investment {
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
}
