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
@Table(name = "elternteil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elternteil.findAll", query = "SELECT e FROM Elternteil e"),
    @NamedQuery(name = "Elternteil.findByIdent", query = "SELECT e FROM Elternteil e WHERE e.ident = :ident"),
    @NamedQuery(name = "Elternteil.findByFamiliengroesse", query = "SELECT e FROM Elternteil e WHERE e.familiengroesse = :familiengroesse"),
    @NamedQuery(name = "Elternteil.findByAdresse", query = "SELECT e FROM Elternteil e WHERE e.adresse = :adresse"),
    @NamedQuery(name = "Elternteil.findByNettoeinkommen", query = "SELECT e FROM Elternteil e WHERE e.nettoeinkommen = :nettoeinkommen")})
public class Elternteil implements Serializable {
    @Column(name = "facebook_id")
    private String facebookId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident")
    private Long ident;
    @Basic(optional = false)
    @Lob
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "familiengroesse")
    private long familiengroesse;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "nettoeinkommen")
    private long nettoeinkommen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elternteilid")
    private Collection<Kind> kindCollection;

    public Elternteil() {
    }

    public Elternteil(Long ident) {
        this.ident = ident;
    }

    public Elternteil(Long ident, String name, long familiengroesse, String adresse, long nettoeinkommen) {
        this.ident = ident;
        this.name = name;
        this.familiengroesse = familiengroesse;
        this.adresse = adresse;
        this.nettoeinkommen = nettoeinkommen;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFamiliengroesse() {
        return familiengroesse;
    }

    public void setFamiliengroesse(long familiengroesse) {
        this.familiengroesse = familiengroesse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getNettoeinkommen() {
        return nettoeinkommen;
    }

    public void setNettoeinkommen(long nettoeinkommen) {
        this.nettoeinkommen = nettoeinkommen;
    }

    @XmlTransient
    public Collection<Kind> getKindCollection() {
        return kindCollection;
    }

    public void setKindCollection(Collection<Kind> kindCollection) {
        this.kindCollection = kindCollection;
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
        if (!(object instanceof Elternteil)) {
            return false;
        }
        Elternteil other = (Elternteil) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
    
}
