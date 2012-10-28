/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "kind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kind.findAll", query = "SELECT k FROM Kind k"),
    @NamedQuery(name = "Kind.findByIdent", query = "SELECT k FROM Kind k WHERE k.ident = :ident"),
    @NamedQuery(name = "Kind.findByGeburtsdatum", query = "SELECT k FROM Kind k WHERE k.geburtsdatum = :geburtsdatum"),
    @NamedQuery(name = "Kind.findByHashValue", query = "SELECT k FROM Kind k WHERE k.hashValue = :hashValue")})
public class Kind implements Serializable {
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
    @Column(name = "Vorname")
    private String vorname;
    @Basic(optional = false)
    @Lob
    @Column(name = "Nachname")
    private String nachname;
    @Basic(optional = false)
    @Column(name = "Geburtsdatum")
    @Temporal(TemporalType.DATE)
    private Date geburtsdatum;
    @Basic(optional = false)
    @Column(name = "HashValue")
    private long hashValue;
    @ManyToMany(mappedBy = "kindCollection")
    private Collection<Gruppe> gruppeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kind")
    private Collection<Registrierung> registrierungCollection;
    @JoinColumn(name = "preismodell_id", referencedColumnName = "ident")
    @ManyToOne(optional = false)
    private Preismodell preismodellId;
    @JoinColumn(name = "Elternteil_id", referencedColumnName = "ident")
    @ManyToOne(optional = false)
    private Elternteil elternteilid;

    public Kind() {
    }

    public Kind(Long ident) {
        this.ident = ident;
    }

    public Kind(Long ident, String vorname, String nachname, Date geburtsdatum, long hashValue) {
        this.ident = ident;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.hashValue = hashValue;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        Long oldIdent = this.ident;
        this.ident = ident;
        changeSupport.firePropertyChange("ident", oldIdent, ident);
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        String oldVorname = this.vorname;
        this.vorname = vorname;
        changeSupport.firePropertyChange("vorname", oldVorname, vorname);
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        String oldNachname = this.nachname;
        this.nachname = nachname;
        changeSupport.firePropertyChange("nachname", oldNachname, nachname);
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        Date oldGeburtsdatum = this.geburtsdatum;
        this.geburtsdatum = geburtsdatum;
        changeSupport.firePropertyChange("geburtsdatum", oldGeburtsdatum, geburtsdatum);
    }

    public long getHashValue() {
        return hashValue;
    }

    public void setHashValue(long hashValue) {
        long oldHashValue = this.hashValue;
        this.hashValue = hashValue;
        changeSupport.firePropertyChange("hashValue", oldHashValue, hashValue);
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

    public Preismodell getPreismodellId() {
        return preismodellId;
    }

    public void setPreismodellId(Preismodell preismodellId) {
        Preismodell oldPreismodellId = this.preismodellId;
        this.preismodellId = preismodellId;
        changeSupport.firePropertyChange("preismodellId", oldPreismodellId, preismodellId);
    }

    public Elternteil getElternteilid() {
        return elternteilid;
    }

    public void setElternteilid(Elternteil elternteilid) {
        Elternteil oldElternteilid = this.elternteilid;
        this.elternteilid = elternteilid;
        changeSupport.firePropertyChange("elternteilid", oldElternteilid, elternteilid);
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
        if (!(object instanceof Kind)) {
            return false;
        }
        Kind other = (Kind) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getVorname()+","+this.getNachname();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
