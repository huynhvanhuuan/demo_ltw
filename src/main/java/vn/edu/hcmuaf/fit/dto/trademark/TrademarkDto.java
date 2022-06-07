package vn.edu.hcmuaf.fit.dto.trademark;

import vn.edu.hcmuaf.fit.dto.address.AddressDto;
import vn.edu.hcmuaf.fit.entity.Address;
import vn.edu.hcmuaf.fit.entity.Trademark;

import java.util.HashSet;
import java.util.Set;

public class TrademarkDto {
    private Long id;
    private String name;
    private String website;
    private Set<AddressDto> addresses = new HashSet<>();

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

    public Set<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDto> addresses) {
        this.addresses = addresses;
    }

    public static TrademarkDto createFromEntity(Trademark src) {
        TrademarkDto dest = new TrademarkDto();

        dest.id = src.getId();
        dest.name = src.getName();
        dest.website = src.getWebsite();

        if (src.getAddresses() != null)
            for (Address address : src.getAddresses()) {
                dest.addresses.add(AddressDto.createFromEntity(address));
            }

        return dest;
    }
}
