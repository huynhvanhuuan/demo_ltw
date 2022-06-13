package vn.edu.hcmuaf.fit.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class TrademarkAddress {
    private Trademark trademark;
    private Set<Address> addresses = new LinkedHashSet<>();

    public TrademarkAddress() {}

    public TrademarkAddress(Trademark trademark, Set<Address> addresses) {
        this.trademark = trademark;
        this.addresses = addresses;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
