/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author egon
 */
@Entity
@Table(name = "KINDERGARTENTYP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kindergartentyp.findAll", query = "SELECT k FROM Kindergartentyp k"),
    @NamedQuery(name = "Kindergartentyp.findByIdent", query = "SELECT k FROM Kindergartentyp k WHERE k.ident = :ident"),
    @NamedQuery(name = "Kindergartentyp.findByKindergartentyp", query = "SELECT k FROM Kindergartentyp k WHERE k.kindergartentyp = :kindergartentyp")})
public class Kindergartentyp implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kindergartentypId")
    private Collection<KindergartenKindergartentyp> kindergartenKindergartentypCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigInteger ident;
    @Column(name = "KINDERGARTENTYP")
    private String kindergartentyp;

    public Kindergartentyp() {
    }

    public Kindergartentyp(BigInteger ident) {
        this.ident = ident;
    }

    public BigInteger getIdent() {
        return ident;
    }

    public void setIdent(BigInteger ident) {
        this.ident = ident;
    }

    public String getKindergartentyp() {
        return kindergartentyp;
    }

    public void setKindergartentyp(String kindergartentyp) {
        this.kindergartentyp = kindergartentyp;
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
        if (!(object instanceof Kindergartentyp)) {
            return false;
        }
        Kindergartentyp other = (Kindergartentyp) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Kindergartentyp[ ident=" + ident + " ]";
    }

    @XmlTransient
    public Collection<KindergartenKindergartentyp> getKindergartenKindergartentypCollection() {
        return kindergartenKindergartentypCollection;
    }

    public void setKindergartenKindergartentypCollection(Collection<KindergartenKindergartentyp> kindergartenKindergartentypCollection) {
        this.kindergartenKindergartentypCollection = kindergartenKindergartentypCollection;
    }
    
}
