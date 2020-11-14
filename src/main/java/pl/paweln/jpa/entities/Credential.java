package pl.paweln.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name="CREDENTIAL")
@Access(value= AccessType.FIELD)
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CREDENTIAL_ID")
    private Long credentialId;

    //unidirectional one to one association between Entities
    // this is owing Entity - it contains a join key
    @OneToOne(cascade=CascadeType.ALL) // persist User when Credential is created
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
