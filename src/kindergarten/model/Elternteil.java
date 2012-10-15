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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "ELTERNTEIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elternteil.findAll", query = "SELECT e FROM Elternteil e"),
    @NamedQuery(name = "Elternteil.findByIdent", query = "SELECT e FROM Elternteil e WHERE e.ident = :ident"),
    @NamedQuery(name = "Elternteil.findByName", query = "SELECT e FROM Elternteil e WHERE e.name = :name"),
    @NamedQuery(name = "Elternteil.findByFamiliengroesse", query = "SELECT e FROM Elternteil e WHERE e.familiengroesse = :familiengroesse"),
    @NamedQuery(name = "Elternteil.findByAdresse", query = "SELECT e FROM Elternteil e WHERE e.adresse = :adresse"),
    @NamedQuery(name = "Elternteil.findByNettoeinkommen", query = "SELECT e FROM Elternteil e WHERE e.nettoeinkommen = :nettoeinkommen")})
public class Elternteil implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDENT")
    private BigDecimal ident;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "FAMILIENGROESSE")
    private BigInteger familiengroesse;
    @Basic(optional = false)
    @Column(name = "ADRESSE")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "NETTOEINKOMMEN")
    private BigInteger nettoeinkommen;

    public Elternteil() {
    }

    public Elternteil(BigDecimal ident) {
        this.ident = ident;
    }

    public Elternteil(BigDecimal ident, String name, BigInteger familiengroesse, String adresse, BigInteger nettoeinkommen) {
        this.ident = ident;
        this.name = name;
        this.familiengroesse = familiengroesse;
        this.adresse = adresse;
        this.nettoeinkommen = nettoeinkommen;
    }

    public BigDecimal getIdent() {
        return ident;
    }

    public void setIdent(BigDecimal ident) {
        BigDecimal oldIdent = this.ident;
        this.ident = ident;
        changeSupport.firePropertyChange("ident", oldIdent, ident);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public BigInteger getFamiliengroesse() {
        return familiengroesse;
    }

    public void setFamiliengroesse(BigInteger familiengroesse) {
        BigInteger oldFamiliengroesse = this.familiengroesse;
        this.familiengroesse = familiengroesse;
        changeSupport.firePropertyChange("familiengroesse", oldFamiliengroesse, familiengroesse);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        String oldAdresse = this.adresse;
        this.adresse = adresse;
        changeSupport.firePropertyChange("adresse", oldAdresse, adresse);
    }

    public BigInteger getNettoeinkommen() {
        return nettoeinkommen;
    }

    public void setNettoeinkommen(BigInteger nettoeinkommen) {
        BigInteger oldNettoeinkommen = this.nettoeinkommen;
        this.nettoeinkommen = nettoeinkommen;
        changeSupport.firePropertyChange("nettoeinkommen", oldNettoeinkommen, nettoeinkommen);
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
        return "kindergarten.model.Elternteil[ ident=" + ident + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
