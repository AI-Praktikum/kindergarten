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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "KIND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kind.findAll", query = "SELECT k FROM Kind k"),
    @NamedQuery(name = "Kind.findByIdent", query = "SELECT k FROM Kind k WHERE k.ident = :ident"),
    @NamedQuery(name = "Kind.findByVorname", query = "SELECT k FROM Kind k WHERE k.vorname = :vorname"),
    @NamedQuery(name = "Kind.findByNachname", query = "SELECT k FROM Kind k WHERE k.nachname = :nachname"),
    @NamedQuery(name = "Kind.findByGeburtsdatum", query = "SELECT k FROM Kind k WHERE k.geburtsdatum = :geburtsdatum"),
    @NamedQuery(name = "Kind.findByHashvalue", query = "SELECT k FROM Kind k WHERE k.hashvalue = :hashvalue")})
public class Kind implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "VORNAME")
    private String vorname;
    @Basic(optional = false)
    @Column(name = "NACHNAME")
    private String nachname;
    @Basic(optional = false)
    @Column(name = "GEBURTSDATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date geburtsdatum;
    @Basic(optional = false)
    @Column(name = "HASHVALUE")
    private BigInteger hashvalue;
    @ManyToMany(mappedBy = "kindCollection")
    private Collection<Gruppe> gruppeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kind")
    private Collection<Registrierung> registrierungCollection;
    @JoinColumn(name = "PREISMODELL_ID", referencedColumnName = "IDENT")
    @ManyToOne
    private Preismodell preismodellId;
    @JoinColumn(name = "ELTERNTEIL_ID", referencedColumnName = "IDENT")
    @ManyToOne
    private Elternteil elternteilId;

    public Kind() {
    }

    public Kind(BigDecimal ident) {
        this.ident = ident;
    }

    public Kind(BigDecimal ident, String vorname, String nachname, Date geburtsdatum, BigInteger hashvalue) {
        this.ident = ident;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.hashvalue = hashvalue;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        BigDecimal oldIdent = this.ident;
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

    public BigInteger getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(BigInteger hashvalue) {
        BigInteger oldHashvalue = this.hashvalue;
        this.hashvalue = hashvalue;
        changeSupport.firePropertyChange("hashvalue", oldHashvalue, hashvalue);
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

    public Elternteil getElternteilId() {
        return elternteilId;
    }

    public void setElternteilId(Elternteil elternteilId) {
        Elternteil oldElternteilId = this.elternteilId;
        this.elternteilId = elternteilId;
        changeSupport.firePropertyChange("elternteilId", oldElternteilId, elternteilId);
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
        return this.nachname+","+this.vorname;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
