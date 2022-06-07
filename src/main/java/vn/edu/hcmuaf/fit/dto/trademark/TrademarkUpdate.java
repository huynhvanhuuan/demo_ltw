package vn.edu.hcmuaf.fit.dto.trademark;

public class TrademarkUpdate {
    private Long id;
    private String name;
    private String website;

    public TrademarkUpdate(Long id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
