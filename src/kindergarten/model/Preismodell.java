/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author klaus
 */
@Entity
@Table(name = "PREISMODELL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preismodell.findAll", query = "SELECT p FROM Preismodell p"),
    @NamedQuery(name = "Preismodell.findByIdent", query = "SELECT p FROM Preismodell p WHERE p.ident = :ident"),
    @NamedQuery(name = "Preismodell.findByBezeichnung", query = "SELECT p FROM Preismodell p WHERE p.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Preismodell.findByDauer", query = "SELECT p FROM Preismodell p WHERE p.dauer = :dauer")})
public class Preismodell implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @Basic(optional = false)
    @Column(name = "DAUER")
    private BigInteger dauer;
    @ManyToMany(mappedBy = "preismodellCollection")
    private Collection<Kindergarten> kindergartenCollection;
    @OneToMany(mappedBy = "preismodellId")
    private Collection<Kind> kindCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preismodellId")
    private Collection<Preisliste> preislisteCollection;

    public Preismodell() {
    }

    public Preismodell(BigDecimal ident) {
        this.ident = ident;
    }

    public Preismodell(BigDecimal ident, BigInteger dauer) {
        this.ident = ident;
        this.dauer = dauer;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        this.ident = ident;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public BigInteger getDauer() {
        return dauer;
    }

    public void setDauer(BigInteger dauer) {
        this.dauer = dauer;
    }

    @XmlTransient
    public Collection<Kindergarten> getKindergartenCollection() {
        return kindergartenCollection;
    }

    public void setKindergartenCollection(Collection<Kindergarten> kindergartenCollection) {
        this.kindergartenCollection = kindergartenCollection;
    }

    @XmlTransient
    public Collection<Kind> getKindCollection() {
        return kindCollection;
    }

    public void setKindCollection(Collection<Kind> kindCollection) {
        this.kindCollection = kindCollection;
    }

    @XmlTransient
    public Collection<Preisliste> getPreislisteCollection() {
        return preislisteCollection;
    }

    public void setPreislisteCollection(Collection<Preisliste> preislisteCollection) {
        this.preislisteCollection = preislisteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ident != null ? ident.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preismodell)) {
            return false;
        }
        Preismodell other = (Preismodell) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Preismodell[ ident=" + ident + " ]";
    }
    
}
