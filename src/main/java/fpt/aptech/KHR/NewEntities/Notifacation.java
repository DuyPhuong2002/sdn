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
@Table(name = "notifacation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifacation.findAll", query = "SELECT n FROM Notifacation n"),
    @NamedQuery(name = "Notifacation.findById", query = "SELECT n FROM Notifacation n WHERE n.id = :id"),
    @NamedQuery(name = "Notifacation.findByTitle", query = "SELECT n FROM Notifacation n WHERE n.title = :title"),
    @NamedQuery(name = "Notifacation.findByConntent", query = "SELECT n FROM Notifacation n WHERE n.conntent = :conntent"),
    @NamedQuery(name = "Notifacation.findByCreateDate", query = "SELECT n FROM Notifacation n WHERE n.createDate = :createDate"),
    @NamedQuery(name = "Notifacation.findBySeen", query = "SELECT n FROM Notifacation n WHERE n.seen = :seen"),
    @NamedQuery(name = "Notifacation.findByDatatran", query = "SELECT n FROM Notifacation n WHERE n.datatran = :datatran")})
public class Notifacation implements Serializable {

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
    @Column(name = "conntent")
    private String conntent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seen")
    private int seen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "datatran")
    private String datatran;
    @JoinColumn(name = "id_account", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idAccount;

    public Notifacation() {
    }

    public Notifacation(Integer id) {
        this.id = id;
    }

    public Notifacation(Integer id, String title, String conntent, Date createDate, int seen, String datatran) {
        this.id = id;
        this.title = title;
        this.conntent = conntent;
        this.createDate = createDate;
        this.seen = seen;
        this.datatran = datatran;
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

    public String getConntent() {
        return conntent;
    }

    public void setConntent(String conntent) {
        this.conntent = conntent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public String getDatatran() {
        return datatran;
    }

    public void setDatatran(String datatran) {
        this.datatran = datatran;
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
        if (!(object instanceof Notifacation)) {
            return false;
        }
        Notifacation other = (Notifacation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.Notifacation[ id=" + id + " ]";
    }
    
}
