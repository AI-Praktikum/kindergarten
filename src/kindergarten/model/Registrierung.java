/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
import kindergarten.helper.DBKind;

/**
 *
 * @author andy
 */
@Entity
@Table(name = "REGISTRIERUNG")
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
    @Column(name = "DATUM_REGISTRIERUNG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumRegistrierung;
    @JoinColumn(name = "WARTELISTE_ID", referencedColumnName = "IDENT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Warteliste warteliste;
    @JoinColumn(name = "KIND_ID", referencedColumnName = "IDENT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Kind kind;

    public Registrierung() {
    }

    public Registrierung(RegistrierungPK registrierungPK) {
        this.registrierungPK = registrierungPK;
    }

    public Registrierung(BigInteger kindId, BigInteger wartelisteId) {
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

    public Warteliste getWarteliste() {
        return warteliste;
    }

    public void setWarteliste(Warteliste warteliste) {
        this.warteliste = warteliste;
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
        Kind k = this.kind;
        return k.getNachname()+","+k.getVorname();
    }
    
}
