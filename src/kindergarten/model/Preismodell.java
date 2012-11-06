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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "preismodell")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preismodell.findAll", query = "SELECT p FROM Preismodell p"),
    @NamedQuery(name = "Preismodell.findByIdent", query = "SELECT p FROM Preismodell p WHERE p.ident = :ident"),
    @NamedQuery(name = "Preismodell.findByDauer", query = "SELECT p FROM Preismodell p WHERE p.dauer = :dauer")})
public class Preismodell implements Serializable {
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
    @Column(name = "dauer")
    private long dauer;
    @JoinTable(name = "kindergarten_preismodell", joinColumns = {
        @JoinColumn(name = "preismodell_id", referencedColumnName = "ident")}, inverseJoinColumns = {
        @JoinColumn(name = "kindergarten_id", referencedColumnName = "ident")})
    @ManyToMany
    private Collection<Kindergarten> kindergartenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preismodellId")
    private Collection<Preisliste> preislisteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preismodellId")
    private Collection<Kind> kindCollection;

    public Preismodell() {
    }

    public Preismodell(Long ident) {
        this.ident = ident;
    }

    public Preismodell(Long ident, String bezeichnung, long dauer) {
        this.ident = ident;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
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

    public long getDauer() {
        return dauer;
    }

    public void setDauer(long dauer) {
        this.dauer = dauer;
    }

    @XmlTransient
    public Collection<Kindergarten> getKindergartenCollection() {
        return kindergartenCollection;
    }

    public void setKindergartenCollection(Collection<Kindergarten> kindergartenCollection) {
        this.kindergartenCollection = kindergartenCollection;
    }

    @XmlTransient
    public Collection<Preisliste> getPreislisteCollection() {
        return preislisteCollection;
    }

    public void setPreislisteCollection(Collection<Preisliste> preislisteCollection) {
        this.preislisteCollection = preislisteCollection;
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
        if (!(object instanceof Preismodell)) {
            return false;
        }
        Preismodell other = (Preismodell) object;
        if ((this.ident == null && other.ident != null) || (this.ident != null && !this.ident.equals(other.ident))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Preismodell[ ident=" + ident + " ]";
    }
    
}
