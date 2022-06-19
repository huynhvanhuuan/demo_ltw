package vn.edu.hcmuaf.fit.dto.userinfo;

import vn.edu.hcmuaf.fit.entity.UserInfo;

import java.util.Date;

public class UserInfoForAdminDtoResponse {
    private String fullName;
    private Date dateOfBirth;
    private boolean isMale;
    private String imageUrl;

    public UserInfoForAdminDtoResponse() {
    }

    public UserInfoForAdminDtoResponse(String fullName, Date dateOfBirth,
                                   boolean isMale, String imageUrl) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.isMale = isMale;
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static UserInfoForAdminDtoResponse createFromEntity(UserInfo src) {
        UserInfoForAdminDtoResponse dest = new UserInfoForAdminDtoResponse();

        dest.fullName = src.getFullName();
        dest.dateOfBirth = src.getDateOfBirth();
        dest.isMale = src.isMale();
        dest.imageUrl = src.getImageUrl();

        return dest;
    }
}
