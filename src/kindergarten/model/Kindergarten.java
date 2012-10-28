/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

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
import javax.persistence.ManyToMany;
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
@Table(name = "kindergarten")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kindergarten.findAll", query = "SELECT k FROM Kindergarten k"),
    @NamedQuery(name = "Kindergarten.findByIdent", query = "SELECT k FROM Kindergarten k WHERE k.ident = :ident"),
    @NamedQuery(name = "Kindergarten.findByAdresse", query = "SELECT k FROM Kindergarten k WHERE k.adresse = :adresse"),
    @NamedQuery(name = "Kindergarten.findByMaxplaetze", query = "SELECT k FROM Kindergarten k WHERE k.maxplaetze = :maxplaetze"),
    @NamedQuery(name = "Kindergarten.findByKontonummer", query = "SELECT k FROM Kindergarten k WHERE k.kontonummer = :kontonummer"),
    @NamedQuery(name = "Kindergarten.findByBlz", query = "SELECT k FROM Kindergarten k WHERE k.blz = :blz")})
public class Kindergarten implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident")
    private Long ident;
    @Basic(optional = false)
    @Lob
    @Column(name = "bezeichnung")
    private String bezeichnung;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "maxplaetze")
    private long maxplaetze;
    @Basic(optional = false)
    @Column(name = "kontonummer")
    private long kontonummer;
    @Basic(optional = false)
    @Column(name = "blz")
    private long blz;
    @ManyToMany(mappedBy = "kindergartenCollection")
    private Collection<Preismodell> preismodellCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kindergartenId")
    private Collection<Gruppe> gruppeCollection;

    public Kindergarten() {
    }

    public Kindergarten(Long ident) {
        this.ident = ident;
    }

    public Kindergarten(Long ident, String bezeichnung, String adresse, long maxplaetze, long kontonummer, long blz) {
        this.ident = ident;
        this.bezeichnung = bezeichnung;
        this.adresse = adresse;
        this.maxplaetze = maxplaetze;
        this.kontonummer = kontonummer;
        this.blz = blz;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getMaxplaetze() {
        return maxplaetze;
    }

    public void setMaxplaetze(long maxplaetze) {
        this.maxplaetze = maxplaetze;
    }

    public long getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(long kontonummer) {
        this.kontonummer = kontonummer;
    }

    public long getBlz() {
        return blz;
    }

    public void setBlz(long blz) {
        this.blz = blz;
    }

    @XmlTransient
    public Collection<Preismodell> getPreismodellCollection() {
        return preismodellCollection;
    }

    public void setPreismodellCollection(Collection<Preismodell> preismodellCollection) {
        this.preismodellCollection = preismodellCollection;
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
        if (!(object instanceof Kindergarten)) {
            return false;
        }
        Kindergarten other = (Kindergarten) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Kindergarten[ ident=" + ident + " ]";
    }
    
}
