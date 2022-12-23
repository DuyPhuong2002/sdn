/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.NewEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Entity
@Table(name = "groupchat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupchat.findAll", query = "SELECT g FROM Groupchat g"),
    @NamedQuery(name = "Groupchat.findById", query = "SELECT g FROM Groupchat g WHERE g.id = :id"),
    @NamedQuery(name = "Groupchat.findByGroupName", query = "SELECT g FROM Groupchat g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "Groupchat.findByType", query = "SELECT g FROM Groupchat g WHERE g.type = :type"),
    @NamedQuery(name = "Groupchat.findBySatus", query = "SELECT g FROM Groupchat g WHERE g.satus = :satus")})
public class Groupchat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "group_name")
    private String groupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "satus")
    private String satus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGroup")
    private List<ChatPeople> chatPeopleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGroup")
    private List<Message> messageList;

    public Groupchat() {
    }

    public Groupchat(Integer id) {
        this.id = id;
    }

    public Groupchat(Integer id, String groupName, String type, String satus) {
        this.id = id;
        this.groupName = groupName;
        this.type = type;
        this.satus = satus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    @XmlTransient
    public List<ChatPeople> getChatPeopleList() {
        return chatPeopleList;
    }

    public void setChatPeopleList(List<ChatPeople> chatPeopleList) {
        this.chatPeopleList = chatPeopleList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
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
        if (!(object instanceof Groupchat)) {
            return false;
        }
        Groupchat other = (Groupchat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.Groupchat[ id=" + id + " ]";
    }
    
}
