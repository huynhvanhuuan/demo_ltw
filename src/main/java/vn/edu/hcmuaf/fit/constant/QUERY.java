package vn.edu.hcmuaf.fit.constant;

public class QUERY {
    /* PRODUCT */
    public static class PRODUCT {
        public static final String FIND_ALL = "select * from product";
        public static final String FIND_BY_ID = "select * from product where id = ?";
        public static final String CREATE = "insert into product(name, description, trademark_id, category_id) values(?,?,?,?)";
        public static final String UPDATE = "update product set name = ?, description = ?, trademark_id = ?, category_sku = ?, active = ? where id = ?";
        public static final String DELETE = "delete from product where id = ?";
        public static final String FIND_WITH_LIMIT = "select * from product limit ?";
        public static final String FIND_BY_STATUS = "select * from product where active = ?";
    }

    /* PRODUCT DETAIL */
    public static class PRODUCT_DETAIL {
        public static final String FIND_ALL = "select * from product_detail";
        public static final String FIND_BY_ID = "select * from product_detail where id = ?";
        public static final String FIND_BY_PRODUCT_ID = "select * from product_detail where product_id = ?";
        public static final String FIND_BY_SKU = "select * from product_detail where sku = ?";
        public static final String CREATE = "insert into product_detail(sku, product_id, image, color_id, material_sku, unit_price, unit_in_stock, discount) values(?,?,?,?,?,?,?,?)";
        public static final String UPDATE = "update product_detail set sku = ?, product_id = ?, image = ?, color_id = ?, material_sku = ?, unit_price = ?, unit_in_stock = ?, discount = ? where sku = ? and date_created = ?";
        public static final String DELETE = "delete from product_detail where sku = ? and date_created = ?";
    }

    /* COLOR */
    public static class COLOR {
        public static final String FIND_ALL = "select * from color";
        public static final String FIND_BY_ID = "select * from color where id = ?";
    }

    /* MATERIAL */
    public static class MATERIAL {
        public static final String FIND_ALL = "select * from material";
        public static final String FIND_BY_ID = "select * from material where id = ?";
    }

    /* CATEGORY */
    public static class CATEGORY {
        public static final String FIND_ALL = "select * from category";
        public static final String FIND_BY_ID = "select * from category where id = ?";
        public static final String FIND_BY_SKU = "select * from category where sku like ?";
        public static final String FIND_BY_NAME = "select * from category where name like ?";
        public static final String CREATE = "insert into category(sku, name) values(?,?)";
        public static final String UPDATE = "update category set sku = ?, name = ?, active = ? where id = ?";
        public static final String DELETE = "delete from category where id = ?";
    }

    /* TRADEMARK */
    public static class TRADEMARK {
        public static final String FIND_ALL = "select * from trademark";
        public static final String FIND_ALL_NAME_HAS_PRODUCT = "select distinct trademark_id from product";
        public static final String FIND_BY_ID = "select * from trademark where id = ?";
        public static final String FIND_BY_NAME = "select * from trademark where website like ?";
        public static final String FIND_BY_WEBSITE = "select * from trademark where website like ?";
        public static final String CREATE = "insert into trademark(name, website) values(?,?)";
        public static final String UPDATE = "update trademark set name = ?, website = ?, active = ? where id = ?";
        public static final String DELETE = "delete from trademark where id = ?";
        public static final String CREATE_ADDRESS = "insert into trademark_address(trademark_id, address_id) values(?,?)";
    }

    /* APP USER */
    public static class APP_USER {
        public static final String FIND_ALL = "select * from app_user";
        public static final String FIND_BY_ROLE_ID = "select u.* from app_user u inner join app_user_role ur on u.id = ur.user_id where role_id = ?";
        public static final String FIND_BY_ID = "select * from app_user where id = ?";
        public static final String FIND_BY_USERNAME = "select * from app_user where username = ?";
        public static final String FIND_BY_EMAIL = "select * from app_user where email = ?";
        public static final String FIND_BY_PHONE = "select * from app_user where phone = ?";
        public static final String CREATE = "insert into app_user(username, email, phone, password, userinfo_id) value(?,?,?,?,?)";
        public static final String UPDATE = "update app_user set password = ?, not_locked = ?, enabled = ? where id = ?";
        public static final String DELETE = "delete from app_user where id = ?";
        public static final String CREATE_ADDRESS = "insert into user_address(user_id, address_id) values(?,?)";
    }

    /* USER_INFO */
    public static class USER_INFO {
        public static final String FIND_ALL = "select * from user_info";
        public static final String FIND_BY_ID = "select * from user_info where id = ?";
        public static final String CREATE = "insert into user_info(last_name, first_name, full_name, is_male, image_url) value(?,?,?,?,?)";
        public static final String UPDATE = "update user_info set last_name = ?, first_name = ?, full_name = ?, is_male = ?, image_url = ?, date_of_birth = ? where id = ?";
        public static final String DELETE = "delete from user_info where id = ?";
    }

    /* ROLE */
    public static class APP_ROLE {
        public static final String FIND_ALL = "select * from app_role";
        public static final String FIND_BY_USER_ID = "select r.* from app_role r inner join app_user_role ur on r.id = ur.role_id where user_id = ?";
        public static final String FIND_BY_ID = "select * from app_role where id = ?";
        public static final String FIND_BY_NAME = "select * from app_role where name = ?";
    }

    /* APP_USER_ROLE */
    public static class APP_USER_ROLE {
        public static final String FIND_ALL = "select * from app_user_role";
        public static final String CREATE = "insert into app_user_role(user_id, role_id) values(?,?)";
        public static final String DELETE = "delete from app_user_role where user_id = ? and role_id = ?";
        public static final String DELETE_BY_USER_ID = "delete from app_user_role where user_id = ?";
    }

    /* VERIFICATION TOKEN */
    public static class VERIFICATION_TOKEN {
        public static final String FIND_ALL = "select * from verification_token";
        public static final String FIND_BY_ID = "select * from verification_token where id = ?";
        public static final String FIND_BY_USER_ID = "select * from verification_token where user_id = ?";
        public static final String FIND_BY_TOKEN = "select * from verification_token where token = ?";
        public static final String CREATE = "insert into verification_token(token, user_id) value(?,?)";
        public static final String UPDATE_TOKEN = "update verification_token set token = ? where id = ?";
        public static final String UPDATE_VERIFIED = "update verification_token set is_verified = ?, verified_at = ? where id = ?";
    }

    /* ADDRESS */
    public static class ADDRESS {
        public static final String FIND_ALL = "select * from address";
        public static final String FIND_BY_TRADEMARK_ID = "select a.* from address a inner join trademark_address ta on a.id = ta.address_id where trademark_id = ?";
        public static final String FIND_BY_USER_ID = "select a.* from address a inner join user_address ua on a.id = ua.address_id where user_id = ?";
        public static final String FIND_BY_ID = "select * from address where id = ?";
        public static final String FIND_BY_PATH = "select * from address where path like ?";
        public static final String CREATE = "insert into address(number, street, ward_id, district_id, path) values(?,?,?,?,?)";
        public static final String UPDATE = "update address set number = ?, street = ?, ward_id = ?, district_id = ?, path = ? where id = ?";
        public static final String DELETE = "delete from address where id = ?";
    }

    /* PROVINCE */
    public static class PROVINCE {
        public static final String FIND_ALL = "select * from province";
        public static final String FIND_BY_ID = "select * from province where id = ?";
    }

    /* DISTRICT */
    public static class DISTRICT {
        public static final String FIND_ALL = "select * from district";
        public static final String FIND_BY_ID = "select * from district where id = ?";
        public static final String FIND_BY_PROVINCE_ID = "select * from district where province_id = ?";
    }

    /* WARD */
    public static class WARD {
        public static final String FIND_ALL = "select * from ward";
        public static final String FIND_BY_ID = "select * from ward where id = ?";
        public static final String FIND_BY_DISTRICT_ID = "select * from ward where district_id = ?";
    }

    /* CART */
    public static class CART {
        public static final String FIND_ALL = "select * from cart where user_id = ?";
        public static final String ADD_TO_CART = "insert into cart values(?,?,?)";
        public static final String UPDATE_PRODUCT_QUANTITY = "update cart set quantity = ? where user_id = ? and product_sku like ?";
        public static final String REMOVE = "delete from cart where user_id like ? and product_sku like ?";
        public static final String REMOVE_FROM_CART = "delete from cart where user_id = ? and trademark_id = ?";
        public static final String REMOVE_BY_USER_ID = "delete from cart where user_id = ?";
        public static final String REMOVE_BY_PRODUCT_SKU = "select * from cart where product_sku like ?";
    }

    /* WISHLIST */
    public static class WISHLIST {
        public static final String FIND_BY_USER_ID = "select * from wishlist where user_id = ?";
        public static final String ADD_TO_WISHLIST = "insert into wishlist values(?,?)";
        public static final String REMOVE_BY_PRODUCT_SKU = "delete from wishlist where user_id = ? and product_sku like ?";
        public static final String REMOVE_BY_USER_ID = "delete from wishlist where user_id like ?";
    }

    /* ORDER */
    public static class ORDER {
        public static final String FIND_BY_USER_ID = "select * from order where user_id = ?";
    }

    /* ORDER DETAIL */
    public static class ORDER_DETAIL {
        public static final String FIND_BY_ORDER_ID = "select * from order_detail where order_id like ?";
    }
}
