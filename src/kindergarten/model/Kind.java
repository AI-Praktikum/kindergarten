/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author egon
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
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigInteger ident;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "kind")
    private KindWarteliste kindWarteliste;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kindId")
    private Collection<KindGruppe> kindGruppeCollection;
    @JoinColumn(name = "ELTERNTEILID", referencedColumnName = "IDENT")
    @ManyToOne
    private Elternteil elternteilid;

    public Kind() {
    }

    public Kind(BigInteger ident) {
        this.ident = ident;
    }

    public Kind(BigInteger ident, String vorname, String nachname, Date geburtsdatum, BigInteger hashvalue) {
        this.ident = ident;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.hashvalue = hashvalue;
    }

    public BigInteger getIdent() {
        return ident;
    }

    public void setIdent(BigInteger ident) {
        this.ident = ident;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public BigInteger getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(BigInteger hashvalue) {
        this.hashvalue = hashvalue;
    }

    public KindWarteliste getKindWarteliste() {
        return kindWarteliste;
    }

    public void setKindWarteliste(KindWarteliste kindWarteliste) {
        this.kindWarteliste = kindWarteliste;
    }

    @XmlTransient
    public Collection<KindGruppe> getKindGruppeCollection() {
        return kindGruppeCollection;
    }

    public void setKindGruppeCollection(Collection<KindGruppe> kindGruppeCollection) {
        this.kindGruppeCollection = kindGruppeCollection;
    }

    public Elternteil getElternteilid() {
        return elternteilid;
    }

    public void setElternteilid(Elternteil elternteilid) {
        this.elternteilid = elternteilid;
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
        return "kindergarten.model.Kind[ ident=" + ident + " ]";
    }
    
}
