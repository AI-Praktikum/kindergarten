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
@Table(name = "KIND_GRUPPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KindGruppe.findAll", query = "SELECT k FROM KindGruppe k"),
    @NamedQuery(name = "KindGruppe.findByGruppeId", query = "SELECT k FROM KindGruppe k WHERE k.gruppeId = :gruppeId")})
public class KindGruppe implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "GRUPPE_ID")
    private BigDecimal gruppeId;
    @JoinColumn(name = "KIND_ID", referencedColumnName = "IDENT")
    @ManyToOne(optional = false)
    private Kind kindId;
    @JoinColumn(name = "GRUPPE_ID", referencedColumnName = "IDENT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Gruppe gruppe;

    public KindGruppe() {
    }

    public KindGruppe(BigDecimal gruppeId) {
        this.gruppeId = gruppeId;
    }

    public BigDecimal getGruppeId() {
        return gruppeId;
    }

    public void setGruppeId(BigDecimal gruppeId) {
        this.gruppeId = gruppeId;
    }

    public Kind getKindId() {
        return kindId;
    }

    public void setKindId(Kind kindId) {
        this.kindId = kindId;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruppeId != null ? gruppeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KindGruppe)) {
            return false;
        }
        KindGruppe other = (KindGruppe) object;
        if ((this.gruppeId == null && other.gruppeId != null) || (this.gruppeId != null && !this.gruppeId.equals(other.gruppeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.KindGruppe[ gruppeId=" + gruppeId + " ]";
    }
    
}
