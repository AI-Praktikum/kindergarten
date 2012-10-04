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
@Table(name = "KIND_WARTELISTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KindWarteliste.findAll", query = "SELECT k FROM KindWarteliste k"),
    @NamedQuery(name = "KindWarteliste.findByKindId", query = "SELECT k FROM KindWarteliste k WHERE k.kindId = :kindId")})
public class KindWarteliste implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "KIND_ID")
    private BigDecimal kindId;
    @JoinColumn(name = "WARTELISTE_ID", referencedColumnName = "IDENT")
    @ManyToOne(optional = false)
    private Warteliste wartelisteId;
    @JoinColumn(name = "KIND_ID", referencedColumnName = "IDENT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Kind kind;

    public KindWarteliste() {
    }

    public KindWarteliste(BigDecimal kindId) {
        this.kindId = kindId;
    }

    public BigDecimal getKindId() {
        return kindId;
    }

    public void setKindId(BigDecimal kindId) {
        this.kindId = kindId;
    }

    public Warteliste getWartelisteId() {
        return wartelisteId;
    }

    public void setWartelisteId(Warteliste wartelisteId) {
        this.wartelisteId = wartelisteId;
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
        hash += (kindId != null ? kindId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KindWarteliste)) {
            return false;
        }
        KindWarteliste other = (KindWarteliste) object;
        if ((this.kindId == null && other.kindId != null) || (this.kindId != null && !this.kindId.equals(other.kindId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kindergarten.model.KindWarteliste[ kindId=" + kindId + " ]";
    }
    
}
