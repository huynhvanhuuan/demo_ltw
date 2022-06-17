package vn.edu.hcmuaf.fit.util;

import vn.edu.hcmuaf.fit.entity.ProductDetail;

import java.util.*;

public class ProductUtil {
    public static String createProductSku(ProductDetail item) {
        return "";
    }

    public static List<String> splitImageToList(String imageUrl) {
        return new ArrayList<>(Arrays.asList(imageUrl.split(",")));
    }
}
