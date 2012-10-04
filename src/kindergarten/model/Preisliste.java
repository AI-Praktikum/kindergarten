/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author egon
 */
@Entity
@Table(name = "PREISLISTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preisliste.findAll", query = "SELECT p FROM Preisliste p"),
    @NamedQuery(name = "Preisliste.findByIdent", query = "SELECT p FROM Preisliste p WHERE p.ident = :ident"),
    @NamedQuery(name = "Preisliste.findByFamiliengroesse", query = "SELECT p FROM Preisliste p WHERE p.familiengroesse = :familiengroesse"),
    @NamedQuery(name = "Preisliste.findByNettoeinkommen", query = "SELECT p FROM Preisliste p WHERE p.nettoeinkommen = :nettoeinkommen"),
    @NamedQuery(name = "Preisliste.findByPreis", query = "SELECT p FROM Preisliste p WHERE p.preis = :preis")})
public class Preisliste implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "FAMILIENGROESSE")
    private BigInteger familiengroesse;
    @Basic(optional = false)
    @Column(name = "NETTOEINKOMMEN")
    private BigInteger nettoeinkommen;
    @Basic(optional = false)
    @Column(name = "PREIS")
    private BigInteger preis;

    public Preisliste() {
    }

    public Preisliste(BigDecimal ident) {
        this.ident = ident;
    }

    public Preisliste(BigDecimal ident, BigInteger familiengroesse, BigInteger nettoeinkommen, BigInteger preis) {
        this.ident = ident;
        this.familiengroesse = familiengroesse;
        this.nettoeinkommen = nettoeinkommen;
        this.preis = preis;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        this.ident = ident;
    }

    public BigInteger getFamiliengroesse() {
        return familiengroesse;
    }

    public void setFamiliengroesse(BigInteger familiengroesse) {
        this.familiengroesse = familiengroesse;
    }

    public BigInteger getNettoeinkommen() {
        return nettoeinkommen;
    }

    public void setNettoeinkommen(BigInteger nettoeinkommen) {
        this.nettoeinkommen = nettoeinkommen;
    }

    public BigInteger getPreis() {
        return preis;
    }

    public void setPreis(BigInteger preis) {
        this.preis = preis;
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
        if (!(object instanceof Preisliste)) {
            return false;
        }
        Preisliste other = (Preisliste) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Preisliste[ ident=" + ident + " ]";
    }
    
}
