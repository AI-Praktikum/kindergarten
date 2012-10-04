/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author egon
 */
@Entity
@Table(name = "KINDERGARTEN_KINDERGARTENTYP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KindergartenKindergartentyp.findAll", query = "SELECT k FROM KindergartenKindergartentyp k"),
    @NamedQuery(name = "KindergartenKindergartentyp.findByKindergartenId", query = "SELECT k FROM KindergartenKindergartentyp k WHERE k.kindergartenId = :kindergartenId")})
public class KindergartenKindergartentyp implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "KINDERGARTEN_ID")
    private BigDecimal kindergartenId;
    @JoinColumn(name = "KINDERGARTENTYP_ID", referencedColumnName = "IDENT")
    @ManyToOne(optional = false)
    private Kindergartentyp kindergartentypId;
    @JoinColumn(name = "KINDERGARTEN_ID", referencedColumnName = "IDENT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Kindergarten kindergarten;

    public KindergartenKindergartentyp() {
    }

    public KindergartenKindergartentyp(BigDecimal kindergartenId) {
        this.kindergartenId = kindergartenId;
    }

    public BigDecimal getKindergartenId() {
        return kindergartenId;
    }

    public void setKindergartenId(BigDecimal kindergartenId) {
        this.kindergartenId = kindergartenId;
    }

    public Kindergartentyp getKindergartentypId() {
        return kindergartentypId;
    }

    public void setKindergartentypId(Kindergartentyp kindergartentypId) {
        this.kindergartentypId = kindergartentypId;
    }

    public Kindergarten getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kindergartenId != null ? kindergartenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KindergartenKindergartentyp)) {
            return false;
        }
        KindergartenKindergartentyp other = (KindergartenKindergartentyp) object;
        if ((this.kindergartenId == null && other.kindergartenId != null) || (this.kindergartenId != null && !this.kindergartenId.equals(other.kindergartenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.KindergartenKindergartentyp[ kindergartenId=" + kindergartenId + " ]";
    }
    
}
