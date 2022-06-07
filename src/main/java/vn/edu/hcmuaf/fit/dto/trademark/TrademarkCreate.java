package vn.edu.hcmuaf.fit.dto.trademark;

public class TrademarkCreate {
    private String name;
    private String website;

    public TrademarkCreate(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
