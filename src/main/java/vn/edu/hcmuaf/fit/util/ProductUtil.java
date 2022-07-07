package vn.edu.hcmuaf.fit.util;

import vn.edu.hcmuaf.fit.entity.ProductDetail;

import java.util.Arrays;
import java.util.List;

public class ProductUtil {
    public static String createProductSku(ProductDetail item) {
        return "";
    }

    public static List<String> splitImageToList(String imageUrl) {
        List<String> imageList = Arrays.asList(imageUrl.split(","));
        return imageList.size() > 5 ? imageList.subList(0, 5) : imageList;
    }
}
