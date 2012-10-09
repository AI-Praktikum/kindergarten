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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "GRUPPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruppe.findAll", query = "SELECT g FROM Gruppe g"),
    @NamedQuery(name = "Gruppe.findByIdent", query = "SELECT g FROM Gruppe g WHERE g.ident = :ident"),
    @NamedQuery(name = "Gruppe.findByGruppengroesse", query = "SELECT g FROM Gruppe g WHERE g.gruppengroesse = :gruppengroesse"),
    @NamedQuery(name = "Gruppe.findByBezeichnung", query = "SELECT g FROM Gruppe g WHERE g.bezeichnung = :bezeichnung")})
public class Gruppe implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "GRUPPENGROESSE")
    private BigInteger gruppengroesse;
    @Basic(optional = false)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @JoinTable(name = "KIND_GRUPPE", joinColumns = {
        @JoinColumn(name = "GRUPPE_ID", referencedColumnName = "IDENT")}, inverseJoinColumns = {
        @JoinColumn(name = "KIND_ID", referencedColumnName = "IDENT")})
    @ManyToMany
    private Collection<Kind> kindCollection;
    @JoinColumn(name = "WARTELISTE_ID", referencedColumnName = "IDENT")
    @ManyToOne(optional = false)
    private Warteliste wartelisteId;
    @JoinColumn(name = "KINDERGARTEN_ID", referencedColumnName = "IDENT")
    @ManyToOne(optional = false)
    private Kindergarten kindergartenId;

    public Gruppe() {
    }

    public Gruppe(BigDecimal ident) {
        this.ident = ident;
    }

    public Gruppe(BigDecimal ident, BigInteger gruppengroesse, String bezeichnung) {
        this.ident = ident;
        this.gruppengroesse = gruppengroesse;
        this.bezeichnung = bezeichnung;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        this.ident = ident;
    }

    public BigInteger getGruppengroesse() {
        return gruppengroesse;
    }

    public void setGruppengroesse(BigInteger gruppengroesse) {
        this.gruppengroesse = gruppengroesse;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @XmlTransient
    public Collection<Kind> getKindCollection() {
        return kindCollection;
    }

    public void setKindCollection(Collection<Kind> kindCollection) {
        this.kindCollection = kindCollection;
    }

    public Warteliste getWartelisteId() {
        return wartelisteId;
    }

    public void setWartelisteId(Warteliste wartelisteId) {
        this.wartelisteId = wartelisteId;
    }

    public Kindergarten getKindergartenId() {
        return kindergartenId;
    }

    public void setKindergartenId(Kindergarten kindergartenId) {
        this.kindergartenId = kindergartenId;
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
        if (!(object instanceof Gruppe)) {
            return false;
        }
        Gruppe other = (Gruppe) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Gruppe[ ident=" + ident + " ]";
    }
    
}
