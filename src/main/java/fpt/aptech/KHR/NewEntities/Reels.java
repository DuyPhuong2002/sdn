/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.NewEntities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Entity
@Table(name = "reels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reels.findAll", query = "SELECT r FROM Reels r"),
    @NamedQuery(name = "Reels.findById", query = "SELECT r FROM Reels r WHERE r.id = :id"),
    @NamedQuery(name = "Reels.findByTitle", query = "SELECT r FROM Reels r WHERE r.title = :title"),
    @NamedQuery(name = "Reels.findByContent", query = "SELECT r FROM Reels r WHERE r.content = :content"),
    @NamedQuery(name = "Reels.findByVideo", query = "SELECT r FROM Reels r WHERE r.video = :video"),
    @NamedQuery(name = "Reels.findByCreateDate", query = "SELECT r FROM Reels r WHERE r.createDate = :createDate")})
public class Reels implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "video")
    private String video;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReels")
    private List<ReelsComment> reelsCommentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReels")
    private List<ReelsLike> reelsLikeList;
    @JoinColumn(name = "id_account", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idAccount;

    public Reels() {
    }

    public Reels(Integer id) {
        this.id = id;
    }

    public Reels(Integer id, String title, String content, String video, Date createDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.video = video;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @XmlTransient
    public List<ReelsComment> getReelsCommentList() {
        return reelsCommentList;
    }

    public void setReelsCommentList(List<ReelsComment> reelsCommentList) {
        this.reelsCommentList = reelsCommentList;
    }

    @XmlTransient
    public List<ReelsLike> getReelsLikeList() {
        return reelsLikeList;
    }

    public void setReelsLikeList(List<ReelsLike> reelsLikeList) {
        this.reelsLikeList = reelsLikeList;
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
        if (!(object instanceof Reels)) {
            return false;
        }
        Reels other = (Reels) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.Reels[ id=" + id + " ]";
    }
    
}
