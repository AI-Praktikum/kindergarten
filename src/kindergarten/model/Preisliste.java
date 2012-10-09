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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "PREISLISTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preisliste.findAll", query = "SELECT p FROM Preisliste p"),
    @NamedQuery(name = "Preisliste.findByIdent", query = "SELECT p FROM Preisliste p WHERE p.ident = :ident"),
    @NamedQuery(name = "Preisliste.findByNettoeinkommen", query = "SELECT p FROM Preisliste p WHERE p.nettoeinkommen = :nettoeinkommen"),
    @NamedQuery(name = "Preisliste.findByPreis2pers", query = "SELECT p FROM Preisliste p WHERE p.preis2pers = :preis2pers"),
    @NamedQuery(name = "Preisliste.findByPreis3pers", query = "SELECT p FROM Preisliste p WHERE p.preis3pers = :preis3pers"),
    @NamedQuery(name = "Preisliste.findByPreis4pers", query = "SELECT p FROM Preisliste p WHERE p.preis4pers = :preis4pers"),
    @NamedQuery(name = "Preisliste.findByPreis5pers", query = "SELECT p FROM Preisliste p WHERE p.preis5pers = :preis5pers"),
    @NamedQuery(name = "Preisliste.findByPreis6pers", query = "SELECT p FROM Preisliste p WHERE p.preis6pers = :preis6pers")})
public class Preisliste implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "NETTOEINKOMMEN")
    private BigInteger nettoeinkommen;
    @Basic(optional = false)
    @Column(name = "PREIS2PERS")
    private BigInteger preis2pers;
    @Basic(optional = false)
    @Column(name = "PREIS3PERS")
    private BigInteger preis3pers;
    @Basic(optional = false)
    @Column(name = "PREIS4PERS")
    private BigInteger preis4pers;
    @Basic(optional = false)
    @Column(name = "PREIS5PERS")
    private BigInteger preis5pers;
    @Basic(optional = false)
    @Column(name = "PREIS6PERS")
    private BigInteger preis6pers;
    @JoinColumn(name = "PREISMODELL_ID", referencedColumnName = "IDENT")
    @ManyToOne(optional = false)
    private Preismodell preismodellId;

    public Preisliste() {
    }

    public Preisliste(BigDecimal ident) {
        this.ident = ident;
    }

    public Preisliste(BigDecimal ident, BigInteger nettoeinkommen, BigInteger preis2pers, BigInteger preis3pers, BigInteger preis4pers, BigInteger preis5pers, BigInteger preis6pers) {
        this.ident = ident;
        this.nettoeinkommen = nettoeinkommen;
        this.preis2pers = preis2pers;
        this.preis3pers = preis3pers;
        this.preis4pers = preis4pers;
        this.preis5pers = preis5pers;
        this.preis6pers = preis6pers;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        this.ident = ident;
    }

    public BigInteger getNettoeinkommen() {
        return nettoeinkommen;
    }

    public void setNettoeinkommen(BigInteger nettoeinkommen) {
        this.nettoeinkommen = nettoeinkommen;
    }

    public BigInteger getPreis2pers() {
        return preis2pers;
    }

    public void setPreis2pers(BigInteger preis2pers) {
        this.preis2pers = preis2pers;
    }

    public BigInteger getPreis3pers() {
        return preis3pers;
    }

    public void setPreis3pers(BigInteger preis3pers) {
        this.preis3pers = preis3pers;
    }

    public BigInteger getPreis4pers() {
        return preis4pers;
    }

    public void setPreis4pers(BigInteger preis4pers) {
        this.preis4pers = preis4pers;
    }

    public BigInteger getPreis5pers() {
        return preis5pers;
    }

    public void setPreis5pers(BigInteger preis5pers) {
        this.preis5pers = preis5pers;
    }

    public BigInteger getPreis6pers() {
        return preis6pers;
    }

    public void setPreis6pers(BigInteger preis6pers) {
        this.preis6pers = preis6pers;
    }

    public Preismodell getPreismodellId() {
        return preismodellId;
    }

    public void setPreismodellId(Preismodell preismodellId) {
        this.preismodellId = preismodellId;
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
