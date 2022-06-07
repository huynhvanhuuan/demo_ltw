package vn.edu.hcmuaf.fit.constant;

public class QUERY {
    /* PRODUCT */
    public static class PRODUCT {
        public static final String FIND_ALL = "select * from product";
        public static final String FIND_BY_ID = "select * from product where id = ?";
        public static final String FIND_LAST = "select * from product order by id desc limit 1";
        public static final String CREATE = "insert into product(name, description, trademark_id, category_id) values(?,?,?,?)";
        public static final String UPDATE = "update product set name = ?, description = ?, trademark_id = ?, category_sku = ?, active = ? where id = ?";
        public static final String UPDATE_STATUS = "update product set active = (case active when 1 then 0 when 0 then 1 end) where id = ?";
        public static final String DELETE = "delete from product where id = ?";
        public static final String FIND_WITH_LIMIT = "select * from product limit ?";
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
        public static final String UPDATE_STATUS = "update product_detail set active = (case active when 1 then 0 when 0 then 1 end) where sku = ?";
    }

    /* COLOR */
    public static class COLOR {
        public static final String FIND_ALL = "select * from color";
        public static final String FIND_BY_ID = "select * from color where id = ?";
    }

    /* MATERIAL */
    public static class MATERIAL {
        public static final String FIND_ALL = "select * from material";
        public static final String FIND_BY_ID = "select * from material where sku = ?";
    }

    /* CATEGORY */
    public static class CATEGORY {
        public static final String FIND_ALL = "select * from category";
        public static final String FIND_BY_ID = "select * from category where id = ?";
        public static final String FIND_BY_SKU = "select * from category where sku like ?";
        public static final String FIND_BY_NAME = "select * from category where name like ?";
        public static final String CREATE = "insert into category(sku, name) values(?,?)";
        public static final String UPDATE = "update category set sku = ?, name = ? where id = ?";
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
        public static final String UPDATE = "update trademark set name = ?, website = ? where id = ?";
        public static final String DELETE = "delete from trademark where id = ?";
        public static final String UPDATE_STATUS = "update trademark set active = (case active when 1 then 0 when 0 then 1 end) where id = ?";
        public static final String ADD_ADDRESS = "insert into trademark_address(trademark_id, address_id) values(?,?)";
    }

    /* USER */
    public static class USER {
        public static final String FIND_ALL = "select * from user";
        public static final String FIND_BY_ID = "select * from user where id = ?";
        public static final String FIND_BY_EMAIL = "select * from user where email = ?";
        public static final String CREATE = "insert into user(id, first_name, last_name, username, password, email, phone, female, profile_image_url, date_created, role) value(?,?,?,?,?,?,?,?,?,?,?)";
        public static final String UPDATE = "update user set first_name = ?, last_name = ?, password = ?, email = ?, phone = ? where id = ?";
        public static final String DELETE = "delete from user where id = ?";
        public static final String CHANGE_PASSWORD = "update user set password = ? where id = ?";
        public static final String UPDATE_LOCK_STATUS = "update user set locked = ? where id = ?";
        public static final String ADD_ADDRESS = "insert into user_address(user_id, address_id) values(?,?)";
    }

    /* ROLE */
    public static class ROLE {
        public static final String FIND_ALL = "select * from role";
        public static final String FIND_BY_ID = "select * from role where id = ?";
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
        public static final String FIND_LAST = "select * from address order by id desc limit 1";
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
