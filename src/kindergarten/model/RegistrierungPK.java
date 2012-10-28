/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author andy
 */
@Embeddable
public class RegistrierungPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "kind_id")
    private long kindId;
    @Basic(optional = false)
    @Column(name = "warteliste_id")
    private long wartelisteId;

    public RegistrierungPK() {
    }

    public RegistrierungPK(long kindId, long wartelisteId) {
        this.kindId = kindId;
        this.wartelisteId = wartelisteId;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public long getWartelisteId() {
        return wartelisteId;
    }

    public void setWartelisteId(long wartelisteId) {
        this.wartelisteId = wartelisteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) kindId;
        hash += (int) wartelisteId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrierungPK)) {
            return false;
        }
        RegistrierungPK other = (RegistrierungPK) object;
        if (this.kindId != other.kindId) {
            return false;
        }
        if (this.wartelisteId != other.wartelisteId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.RegistrierungPK[ kindId=" + kindId + ", wartelisteId=" + wartelisteId + " ]";
    }
    
}
