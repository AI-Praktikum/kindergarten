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
import javax.persistence.Lob;
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
@Table(name = "warteliste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warteliste.findAll", query = "SELECT w FROM Warteliste w"),
    @NamedQuery(name = "Warteliste.findByIdent", query = "SELECT w FROM Warteliste w WHERE w.ident = :ident")})
public class Warteliste implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident")
    private Long ident;
    @Basic(optional = false)
    @Lob
    @Column(name = "wartelistentyp")
    private String wartelistentyp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wartelisteId")
    private Collection<Gruppe> gruppeCollection;

    public Warteliste() {
    }

    public Warteliste(Long ident) {
        this.ident = ident;
    }

    public Warteliste(Long ident, String wartelistentyp) {
        this.ident = ident;
        this.wartelistentyp = wartelistentyp;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        Long oldIdent = this.ident;
        this.ident = ident;
        changeSupport.firePropertyChange("ident", oldIdent, ident);
    }

    public String getWartelistentyp() {
        return wartelistentyp;
    }

    public void setWartelistentyp(String wartelistentyp) {
        String oldWartelistentyp = this.wartelistentyp;
        this.wartelistentyp = wartelistentyp;
        changeSupport.firePropertyChange("wartelistentyp", oldWartelistentyp, wartelistentyp);
    }

    @XmlTransient
    public Collection<Gruppe> getGruppeCollection() {
        return gruppeCollection;
    }

    public void setGruppeCollection(Collection<Gruppe> gruppeCollection) {
        this.gruppeCollection = gruppeCollection;
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
        return this.getWartelistentyp();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
