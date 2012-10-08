/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author klaus
 */
@Embeddable
public class RegistrierungPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "KIND_ID")
    private BigInteger kindId;
    @Basic(optional = false)
    @Column(name = "WARTELISTE_ID")
    private BigInteger wartelisteId;

    public RegistrierungPK() {
    }

    public RegistrierungPK(BigInteger kindId, BigInteger wartelisteId) {
        this.kindId = kindId;
        this.wartelisteId = wartelisteId;
    }

    public BigInteger getKindId() {
        return kindId;
    }

    public void setKindId(BigInteger kindId) {
        this.kindId = kindId;
    }

    public BigInteger getWartelisteId() {
        return wartelisteId;
    }

    public void setWartelisteId(BigInteger wartelisteId) {
        this.wartelisteId = wartelisteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kindId != null ? kindId.hashCode() : 0);
        hash += (wartelisteId != null ? wartelisteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrierungPK)) {
            return false;
        }
        RegistrierungPK other = (RegistrierungPK) object;
        if ((this.kindId == null && other.kindId != null) || (this.kindId != null && !this.kindId.equals(other.kindId))) {
            return false;
        }
        if ((this.wartelisteId == null && other.wartelisteId != null) || (this.wartelisteId != null && !this.wartelisteId.equals(other.wartelisteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.RegistrierungPK[ kindId=" + kindId + ", wartelisteId=" + wartelisteId + " ]";
    }
    
}
