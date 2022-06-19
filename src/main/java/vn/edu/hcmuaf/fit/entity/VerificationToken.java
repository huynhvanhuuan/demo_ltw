package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class VerificationToken implements Serializable {
    private Long id;
    private UUID token;
    private Date dateCreated;
    private Date lastSent;
    private Date verifiedAt;
    private Boolean isVerified;
    private AppUser appUser;

    public VerificationToken() {
    }

    public VerificationToken(Long id, UUID token, Date dateCreated, Date lastSent, Date verifiedAt, Boolean isVerified, AppUser appUser) {
        this.id = id;
        this.token = token;
        this.dateCreated = dateCreated;
        this.lastSent = lastSent;
        this.verifiedAt = verifiedAt;
        this.isVerified = isVerified;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastSent() {
        return lastSent;
    }

    public void setLastSent(Date lastSent) {
        this.lastSent = lastSent;
    }

    public Date getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Date verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
