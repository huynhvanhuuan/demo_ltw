package vn.edu.hcmuaf.fit.dto.trademark;

public class TrademarkUpdate {
    private Long id;
    private String name;
    private String website;
    private boolean active;

    public TrademarkUpdate(Long id, String name, String website, boolean active) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
