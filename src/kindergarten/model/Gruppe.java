/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author klaus
 */
@Entity
@Table(name = "GRUPPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruppe.findAll", query = "SELECT g FROM Gruppe g"),
    @NamedQuery(name = "Gruppe.findByIdent", query = "SELECT g FROM Gruppe g WHERE g.ident = :ident"),
    @NamedQuery(name = "Gruppe.findByGruppengroesse", query = "SELECT g FROM Gruppe g WHERE g.gruppengroesse = :gruppengroesse")})
public class Gruppe implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "GRUPPENGROESSE")
    private BigInteger gruppengroesse;
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

    public Gruppe(BigDecimal ident, BigInteger gruppengroesse) {
        this.ident = ident;
        this.gruppengroesse = gruppengroesse;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        BigDecimal oldIdent = this.ident;
        this.ident = ident;
        changeSupport.firePropertyChange("ident", oldIdent, ident);
    }

    public BigInteger getGruppengroesse() {
        return gruppengroesse;
    }

    public void setGruppengroesse(BigInteger gruppengroesse) {
        BigInteger oldGruppengroesse = this.gruppengroesse;
        this.gruppengroesse = gruppengroesse;
        changeSupport.firePropertyChange("gruppengroesse", oldGruppengroesse, gruppengroesse);
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
        Warteliste oldWartelisteId = this.wartelisteId;
        this.wartelisteId = wartelisteId;
        changeSupport.firePropertyChange("wartelisteId", oldWartelisteId, wartelisteId);
    }

    public Kindergarten getKindergartenId() {
        return kindergartenId;
    }

    public void setKindergartenId(Kindergarten kindergartenId) {
        Kindergarten oldKindergartenId = this.kindergartenId;
        this.kindergartenId = kindergartenId;
        changeSupport.firePropertyChange("kindergartenId", oldKindergartenId, kindergartenId);
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

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
