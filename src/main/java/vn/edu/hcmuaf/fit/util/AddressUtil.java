package vn.edu.hcmuaf.fit.util;

import vn.edu.hcmuaf.fit.entity.District;
import vn.edu.hcmuaf.fit.entity.Ward;

public class AddressUtil {

    public static String formatAddress(String number, String street, Ward ward, District district) {
        return number + " " + street + ", " + ward.getPrefix() + " " + ward.getName() + ", " + district.getPrefix()
                + " " + district.getName() + ", " + district.getProvince().getPrefix() + " " + district.getProvince().getName();
    }

    public static String formatAddressWithoutWard(String number, String street, District district) {
        return number + " " + street + ", " + district.getPrefix() + " " + district.getName() + ", " +
                district.getProvince().getPrefix() + " " + district.getProvince().getName();
    }
}
