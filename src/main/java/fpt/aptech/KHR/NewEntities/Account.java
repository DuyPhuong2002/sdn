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
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
    @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password"),
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
    @NamedQuery(name = "Account.findByRole", query = "SELECT a FROM Account a WHERE a.role = :role"),
    @NamedQuery(name = "Account.findByToken", query = "SELECT a FROM Account a WHERE a.token = :token"),
    @NamedQuery(name = "Account.findByRecovery", query = "SELECT a FROM Account a WHERE a.recovery = :recovery"),
    @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone"),
    @NamedQuery(name = "Account.findByDob", query = "SELECT a FROM Account a WHERE a.dob = :dob"),
    @NamedQuery(name = "Account.findByFulllName", query = "SELECT a FROM Account a WHERE a.fulllName = :fulllName"),
    @NamedQuery(name = "Account.findByAvatar", query = "SELECT a FROM Account a WHERE a.avatar = :avatar"),
    @NamedQuery(name = "Account.findByGender", query = "SELECT a FROM Account a WHERE a.gender = :gender"),
    @NamedQuery(name = "Account.findByStatus", query = "SELECT a FROM Account a WHERE a.status = :status")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "role")
    private String role;
    @Size(max = 255)
    @Column(name = "token")
    private String token;
    @Size(max = 10)
    @Column(name = "recovery")
    private String recovery;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phone")
    private String phone;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 255)
    @Column(name = "fulll_name")
    private String fulllName;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 50)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<ReelsComment> reelsCommentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<PostComment> postCommentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccout")
    private List<ChatPeople> chatPeopleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<PostLike> postLikeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<Notifacation> notifacationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<ReelsLike> reelsLikeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccountFl")
    private List<Follow> followList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccountFled")
    private List<Follow> followList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<Reels> reelsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccount")
    private List<Posts> postsList;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, String username, String password, String role, String phone, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRecovery() {
        return recovery;
    }

    public void setRecovery(String recovery) {
        this.recovery = recovery;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFulllName() {
        return fulllName;
    }

    public void setFulllName(String fulllName) {
        this.fulllName = fulllName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<ReelsComment> getReelsCommentList() {
        return reelsCommentList;
    }

    public void setReelsCommentList(List<ReelsComment> reelsCommentList) {
        this.reelsCommentList = reelsCommentList;
    }

    @XmlTransient
    public List<PostComment> getPostCommentList() {
        return postCommentList;
    }

    public void setPostCommentList(List<PostComment> postCommentList) {
        this.postCommentList = postCommentList;
    }

    @XmlTransient
    public List<ChatPeople> getChatPeopleList() {
        return chatPeopleList;
    }

    public void setChatPeopleList(List<ChatPeople> chatPeopleList) {
        this.chatPeopleList = chatPeopleList;
    }

    @XmlTransient
    public List<PostLike> getPostLikeList() {
        return postLikeList;
    }

    public void setPostLikeList(List<PostLike> postLikeList) {
        this.postLikeList = postLikeList;
    }

    @XmlTransient
    public List<Notifacation> getNotifacationList() {
        return notifacationList;
    }

    public void setNotifacationList(List<Notifacation> notifacationList) {
        this.notifacationList = notifacationList;
    }

    @XmlTransient
    public List<ReelsLike> getReelsLikeList() {
        return reelsLikeList;
    }

    public void setReelsLikeList(List<ReelsLike> reelsLikeList) {
        this.reelsLikeList = reelsLikeList;
    }

    @XmlTransient
    public List<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Follow> followList) {
        this.followList = followList;
    }

    @XmlTransient
    public List<Follow> getFollowList1() {
        return followList1;
    }

    public void setFollowList1(List<Follow> followList1) {
        this.followList1 = followList1;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlTransient
    public List<Reels> getReelsList() {
        return reelsList;
    }

    public void setReelsList(List<Reels> reelsList) {
        this.reelsList = reelsList;
    }

    @XmlTransient
    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KHR.NewEntities.Account[ id=" + id + " ]";
    }
    
}
