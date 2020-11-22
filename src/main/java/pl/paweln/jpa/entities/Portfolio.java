package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PORTFOLIO_ID", nullable = false)
    private Long portfolioId;

    @Column(name = "NAME", nullable = false)
    private String NAME;

    @OneToMany(mappedBy = "portfolio")

    private List<Investment> investments = new ArrayList<>();

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "portfolioId=" + portfolioId + '\'' +
                "NAME=" + NAME + '\'' +
                '}';
    }
}
