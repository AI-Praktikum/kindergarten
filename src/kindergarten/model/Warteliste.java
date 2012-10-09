/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "WARTELISTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warteliste.findAll", query = "SELECT w FROM Warteliste w"),
    @NamedQuery(name = "Warteliste.findByIdent", query = "SELECT w FROM Warteliste w WHERE w.ident = :ident"),
    @NamedQuery(name = "Warteliste.findByWartelistentyp", query = "SELECT w FROM Warteliste w WHERE w.wartelistentyp = :wartelistentyp")})
public class Warteliste implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "WARTELISTENTYP")
    private String wartelistentyp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wartelisteId")
    private Collection<Gruppe> gruppeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warteliste")
    private Collection<Registrierung> registrierungCollection;

    public Warteliste() {
    }

    public Warteliste(BigDecimal ident) {
        this.ident = ident;
    }

    public Warteliste(BigDecimal ident, String wartelistentyp) {
        this.ident = ident;
        this.wartelistentyp = wartelistentyp;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        this.ident = ident;
    }

    public String getWartelistentyp() {
        return wartelistentyp;
    }

    public void setWartelistentyp(String wartelistentyp) {
        this.wartelistentyp = wartelistentyp;
    }

    @XmlTransient
    public Collection<Gruppe> getGruppeCollection() {
        return gruppeCollection;
    }

    public void setGruppeCollection(Collection<Gruppe> gruppeCollection) {
        this.gruppeCollection = gruppeCollection;
    }

    @XmlTransient
    public Collection<Registrierung> getRegistrierungCollection() {
        return registrierungCollection;
    }

    public void setRegistrierungCollection(Collection<Registrierung> registrierungCollection) {
        this.registrierungCollection = registrierungCollection;
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
        if (!(object instanceof Warteliste)) {
            return false;
        }
        Warteliste other = (Warteliste) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Warteliste[ ident=" + ident + " ]";
    }
    
}
