/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "preisliste")
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident")
    private Long ident;
    @Basic(optional = false)
    @Column(name = "nettoeinkommen")
    private long nettoeinkommen;
    @Basic(optional = false)
    @Column(name = "preis2pers")
    private long preis2pers;
    @Basic(optional = false)
    @Column(name = "preis3pers")
    private long preis3pers;
    @Basic(optional = false)
    @Column(name = "preis4pers")
    private long preis4pers;
    @Basic(optional = false)
    @Column(name = "preis5pers")
    private long preis5pers;
    @Basic(optional = false)
    @Column(name = "preis6pers")
    private long preis6pers;
    @JoinColumn(name = "preismodell_id", referencedColumnName = "ident")
    @ManyToOne(optional = false)
    private Preismodell preismodellId;

    public Preisliste() {
    }

    public Preisliste(Long ident) {
        this.ident = ident;
    }

    public Preisliste(Long ident, long nettoeinkommen, long preis2pers, long preis3pers, long preis4pers, long preis5pers, long preis6pers) {
        this.ident = ident;
        this.nettoeinkommen = nettoeinkommen;
        this.preis2pers = preis2pers;
        this.preis3pers = preis3pers;
        this.preis4pers = preis4pers;
        this.preis5pers = preis5pers;
        this.preis6pers = preis6pers;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

    public long getNettoeinkommen() {
        return nettoeinkommen;
    }

    public void setNettoeinkommen(long nettoeinkommen) {
        this.nettoeinkommen = nettoeinkommen;
    }

    public long getPreis2pers() {
        return preis2pers;
    }

    public void setPreis2pers(long preis2pers) {
        this.preis2pers = preis2pers;
    }

    public long getPreis3pers() {
        return preis3pers;
    }

    public void setPreis3pers(long preis3pers) {
        this.preis3pers = preis3pers;
    }

    public long getPreis4pers() {
        return preis4pers;
    }

    public void setPreis4pers(long preis4pers) {
        this.preis4pers = preis4pers;
    }

    public long getPreis5pers() {
        return preis5pers;
    }

    public void setPreis5pers(long preis5pers) {
        this.preis5pers = preis5pers;
    }

    public long getPreis6pers() {
        return preis6pers;
    }

    public void setPreis6pers(long preis6pers) {
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
