/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "gruppe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruppe.findAll", query = "SELECT g FROM Gruppe g"),
    @NamedQuery(name = "Gruppe.findByIdent", query = "SELECT g FROM Gruppe g WHERE g.ident = :ident"),
    @NamedQuery(name = "Gruppe.findByGruppengroesse", query = "SELECT g FROM Gruppe g WHERE g.gruppengroesse = :gruppengroesse")})
public class Gruppe implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident")
    private Long ident;
    @Basic(optional = false)
    @Column(name = "gruppengroesse")
    private long gruppengroesse;
    @Basic(optional = false)
    @Lob
    @Column(name = "bezeichnung")
    private String bezeichnung;
    @JoinTable(name = "kind_gruppe", joinColumns = {
        @JoinColumn(name = "gruppe_id", referencedColumnName = "ident")}, inverseJoinColumns = {
        @JoinColumn(name = "kind_id", referencedColumnName = "ident")})
    @ManyToMany
    private Collection<Kind> kindCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gruppe")
    private Collection<Registrierung> registrierungCollection;
    @JoinColumn(name = "warteliste_id", referencedColumnName = "ident")
    @ManyToOne(optional = false)
    private Warteliste wartelisteId;
    @JoinColumn(name = "kindergarten_id", referencedColumnName = "ident")
    @ManyToOne(optional = false)
    private Kindergarten kindergartenId;

    public Gruppe() {
    }

    public Gruppe(Long ident) {
        this.ident = ident;
    }

    public Gruppe(Long ident, long gruppengroesse, String bezeichnung) {
        this.ident = ident;
        this.gruppengroesse = gruppengroesse;
        this.bezeichnung = bezeichnung;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        Long oldIdent = this.ident;
        this.ident = ident;
        changeSupport.firePropertyChange("ident", oldIdent, ident);
    }

    public long getGruppengroesse() {
        return gruppengroesse;
    }

    public void setGruppengroesse(long gruppengroesse) {
        long oldGruppengroesse = this.gruppengroesse;
        this.gruppengroesse = gruppengroesse;
        changeSupport.firePropertyChange("gruppengroesse", oldGruppengroesse, gruppengroesse);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        String oldBezeichnung = this.bezeichnung;
        this.bezeichnung = bezeichnung;
        changeSupport.firePropertyChange("bezeichnung", oldBezeichnung, bezeichnung);
    }

    @XmlTransient
    public Collection<Kind> getKindCollection() {
        return kindCollection;
    }

    public void setKindCollection(Collection<Kind> kindCollection) {
        this.kindCollection = kindCollection;
    }

    @XmlTransient
    public Collection<Registrierung> getRegistrierungCollection() {
        return registrierungCollection;
    }

    public void setRegistrierungCollection(Collection<Registrierung> registrierungCollection) {
        this.registrierungCollection = registrierungCollection;
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
        return this.getBezeichnung();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
