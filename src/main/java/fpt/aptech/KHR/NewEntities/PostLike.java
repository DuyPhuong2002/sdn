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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Entity
@Table(name = "post_like")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostLike.findAll", query = "SELECT p FROM PostLike p"),
    @NamedQuery(name = "PostLike.findById", query = "SELECT p FROM PostLike p WHERE p.id = :id"),
    @NamedQuery(name = "PostLike.findByCreatDate", query = "SELECT p FROM PostLike p WHERE p.creatDate = :creatDate"),
    @NamedQuery(name = "PostLike.findByTotalScore", query = "SELECT p FROM PostLike p WHERE p.totalScore = :totalScore")})
public class PostLike implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creat_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_score")
    private int totalScore;
    @JoinColumn(name = "id_posts", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Posts idPosts;
    @JoinColumn(name = "id_account", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idAccount;

    public PostLike() {
    }

    public PostLike(Integer id) {
        this.id = id;
    }

    public PostLike(Integer id, Date creatDate, int totalScore) {
        this.id = id;
        this.creatDate = creatDate;
        this.totalScore = totalScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Posts getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(Posts idPosts) {
        this.idPosts = idPosts;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
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
        if (!(object instanceof PostLike)) {
            return false;
        }
        PostLike other = (PostLike) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.PostLike[ id=" + id + " ]";
    }
    
}
