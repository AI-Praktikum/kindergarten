/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "registrierung")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registrierung.findAll", query = "SELECT r FROM Registrierung r"),
    @NamedQuery(name = "Registrierung.findByKindId", query = "SELECT r FROM Registrierung r WHERE r.registrierungPK.kindId = :kindId"),
    @NamedQuery(name = "Registrierung.findByWartelisteId", query = "SELECT r FROM Registrierung r WHERE r.registrierungPK.wartelisteId = :wartelisteId"),
    @NamedQuery(name = "Registrierung.findByDatumRegistrierung", query = "SELECT r FROM Registrierung r WHERE r.datumRegistrierung = :datumRegistrierung")})
public class Registrierung implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistrierungPK registrierungPK;
    @Basic(optional = false)
    @Column(name = "datum_registrierung")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumRegistrierung;
    @JoinColumn(name = "warteliste_id", referencedColumnName = "ident", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Gruppe gruppe;
    @JoinColumn(name = "kind_id", referencedColumnName = "ident", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kind kind;

    public Registrierung() {
    }

    public Registrierung(RegistrierungPK registrierungPK) {
        this.registrierungPK = registrierungPK;
    }

    public Registrierung(RegistrierungPK registrierungPK, Date datumRegistrierung) {
        this.registrierungPK = registrierungPK;
        this.datumRegistrierung = datumRegistrierung;
    }

    public Registrierung(long kindId, long wartelisteId) {
        this.registrierungPK = new RegistrierungPK(kindId, wartelisteId);
    }

    public RegistrierungPK getRegistrierungPK() {
        return registrierungPK;
    }

    public void setRegistrierungPK(RegistrierungPK registrierungPK) {
        this.registrierungPK = registrierungPK;
    }

    public Date getDatumRegistrierung() {
        return datumRegistrierung;
    }

    public void setDatumRegistrierung(Date datumRegistrierung) {
        this.datumRegistrierung = datumRegistrierung;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrierungPK != null ? registrierungPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrierung)) {
            return false;
        }
        Registrierung other = (Registrierung) object;
        if ((this.registrierungPK == null && other.registrierungPK != null) || (this.registrierungPK != null && !this.registrierungPK.equals(other.registrierungPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.Registrierung[ registrierungPK=" + registrierungPK + " ]";
    }
    
}
