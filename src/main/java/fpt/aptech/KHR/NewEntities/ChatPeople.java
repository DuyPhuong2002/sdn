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
@Table(name = "chat_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatPeople.findAll", query = "SELECT c FROM ChatPeople c"),
    @NamedQuery(name = "ChatPeople.findById", query = "SELECT c FROM ChatPeople c WHERE c.id = :id"),
    @NamedQuery(name = "ChatPeople.findByType", query = "SELECT c FROM ChatPeople c WHERE c.type = :type"),
    @NamedQuery(name = "ChatPeople.findByCreateDate", query = "SELECT c FROM ChatPeople c WHERE c.createDate = :createDate")})
public class ChatPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "id_accout", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idAccout;
    @JoinColumn(name = "id_group", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Groupchat idGroup;

    public ChatPeople() {
    }

    public ChatPeople(Integer id) {
        this.id = id;
    }

    public ChatPeople(Integer id, String type, Date createDate) {
        this.id = id;
        this.type = type;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Account getIdAccout() {
        return idAccout;
    }

    public void setIdAccout(Account idAccout) {
        this.idAccout = idAccout;
    }

    public Groupchat getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Groupchat idGroup) {
        this.idGroup = idGroup;
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
        if (!(object instanceof ChatPeople)) {
            return false;
        }
        ChatPeople other = (ChatPeople) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.ChatPeople[ id=" + id + " ]";
    }
    
}
