/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.NewEntities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Entity
@Table(name = "follow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Follow.findAll", query = "SELECT f FROM Follow f"),
    @NamedQuery(name = "Follow.findById", query = "SELECT f FROM Follow f WHERE f.id = :id"),
    @NamedQuery(name = "Follow.findByStatus", query = "SELECT f FROM Follow f WHERE f.status = :status"),
    @NamedQuery(name = "Follow.findByCreatDate", query = "SELECT f FROM Follow f WHERE f.creatDate = :creatDate")})
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creat_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatDate;
    @JoinColumn(name = "id_account_fl", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idAccountFl;
    @JoinColumn(name = "id_account_fled", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idAccountFled;

    public Follow() {
    }

    public Follow(Integer id) {
        this.id = id;
    }

    public Follow(Integer id, String status, Date creatDate) {
        this.id = id;
        this.status = status;
        this.creatDate = creatDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Account getIdAccountFl() {
        return idAccountFl;
    }

    public void setIdAccountFl(Account idAccountFl) {
        this.idAccountFl = idAccountFl;
    }

    public Account getIdAccountFled() {
        return idAccountFled;
    }

    public void setIdAccountFled(Account idAccountFled) {
        this.idAccountFled = idAccountFled;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Follow)) {
            return false;
        }
        Follow other = (Follow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.Follow[ id=" + id + " ]";
    }
    
}
