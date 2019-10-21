package by.htp.jd2.dao.impl;

public final class FinalStringInsert {

	private FinalStringInsert() {
	}

	public final static String qFIND_ALL_USERS = "SELECT * FROM users";
	public final static String qFIND_USER_BY_ID = "SELECT * FROM users WHERE  id=?";
	public final static String qFIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";
	public final static String qFIND_USER_BY_LOGIN = "SELECT * FROM users WHERE  login=?";
	public final static String qFIND_ACCOUNT_BY_ID ="SELECT * FROM account WHERE  id=?"; 
	public final static String qFIND_PRODUCT_BY_NAME = "SELECT * FROM price_of_products WHERE  product=?";
	public final static String qFIND_TYPE_OF_COFFEE_BY_TYPE = "SELECT * FROM type_of_coffee WHERE type=?";
	public final static String qFIND_TYPE_OF_COFFEE_BY_ID = "SELECT * FROM type_of_coffee WHERE id=?";
	public final static String qFIND_COFFEE_OPTIONS_IN_PRODUCT_TABLE = "SELECT * FROM price_of_products JOIN type_of_coffee ON  price_of_products.id = type_of_coffee.price_of_products_id WHERE type_of_coffee.id =?";
	public final static String qFIND_MILK_BY_ID = "SELECT * FROM milk WHERE id=?";
	public final static String qFIND_MILK_OPTIONS_IN_PRODUCT_TABLE  = "SELECT * FROM price_of_products JOIN milk ON  price_of_products.id = milk.price_of_products_id WHERE milk.id =?";
	public final static String qFIND_WATER_BY_ID = "SELECT * FROM water WHERE id=?";
	public final static String qFIND_WATER_OPTIONS_IN_PRODUCT_TABLE  = "SELECT * FROM price_of_products JOIN water ON  price_of_products.id = water.price_of_products_id WHERE water.id =?";
	public final static String qFIND_SUGAR_BY_ID = "SELECT * FROM sugar WHERE id=?";
	public final static String qFIND_SUGAR_OPTIONS_IN_PRODUCT_TABLE  = "SELECT * FROM price_of_products JOIN sugar ON  price_of_products.id = sugar.price_of_products_id WHERE sugar.id =?";
	public final static String qFIND_TYPE_OF_SYRUP_BY_TYPE = "SELECT * FROM type_of_syrup WHERE type=?";
	public final static String qFIND_TYPE_OF_SYRUP_BY_ID = "SELECT * FROM type_of_syrup WHERE id=?";
	public final static String qFIND_TYPE_OF_SYRUP_OPTIONS_IN_PRODUCT_TABLE  = "SELECT * FROM price_of_products JOIN type_of_syrup ON  price_of_products.id = type_of_syrup.price_of_products_id WHERE type_of_syrup.id =?";
	public final static String qFIND_DRINK_BY_CRITERIA = "SELECT * FROM drink WHERE  fortress=? AND type_of_coffee_id=?  AND volume_of_drink =? AND count_of_sugar=? AND count_of_syrup=? AND type_of_syrup_id=?";
	public final static String qFIND_DRINK_BY_ID = "SELECT * FROM drink WHERE id=?";
	public final static String qINSERT_REGISTRATION_INFO = "INSERT INTO users (login, password, email, name, surname, role, account_id) VALUES(?,?,?,?,?,?,?)";
	public final static String qINSERT_ACCOUNT_INFO = "INSERT INTO account (balance, isBlocked) VALUES(?,?)";
	//public final static String INSERT_ADD_DRINK_TO_ORDER = "INSERT INTO orders_has_drink (drink_id, price,count) VALUES(?,?,?)";
	public final static String qINSERT_ORDERS = "INSERT INTO orders (users_id, status) VALUES(?,?)";
	public final static String qINSERT_ADD_DRINK_TO_ORDER ="INSERT INTO orders_has_drink (drink_id, price, count, orders_number_order) VALUES(?,?,?,?)";
	public final static String qSELECT_ACCOUNT_LAST_INSERT_ID = "SELECT * FROM account WHERE id = LAST_INSERT_ID()";
	public final static String qSELECT_USER_LAST_INSERT_ID = "SELECT * FROM users WHERE id = LAST_INSERT_ID()";
	public final static String qSELECT_LIST_OF_PRODUCTS = "SELECT * FROM price_of_products";
	public final static String qSELECT_ORDERS_HAS_DRINK = "SELECT * FROM orders_has_drink WHERE orders_number_order =?";
	public final static String qSELECT_ORDERS = "SELECT * FROM orders WHERE  users_id=? AND status=?";
	public final static String qSELECT_ORDERS_WHERE_NUMBER_ORDER ="SELECT * FROM orders WHERE number_order=?";
	public final static String qSELECT_TYPE_OF_COFFEE_JOIN_DRINK = "SELECT * FROM type_of_coffee JOIN drink ON  type_of_coffee.id = drink.type_of_coffee_id WHERE drink.id =?";
	public final static String qSELECT_TYPE_OF_COFFEE = "SELECT * FROM type_of_coffee WHERE  id=?";
	public final static String qUPDATE_USER_ROLE = "UPDATE users SET role=? WHERE  id=?";
	public final static String qUPDATE_ACCOUNT_BLOCK = "UPDATE account SET isBlocked=? WHERE  id=?";
	public final static String qUPDATE_ACCOUNT_BALANCE = "UPDATE account SET balance=? WHERE id=?";
	public final static String qUPDATE_QUANTITY_IN_STOCK = "UPDATE price_of_products SET quantity_in_stock=? WHERE  product=?";
	public final static String qUPDATE_PRICE_IN_STOCK = "UPDATE price_of_products SET price=? WHERE  product=?";
	public final static String qUPDATE_PRODUCT_IN_STOCK = "UPDATE price_of_products SET quantity_in_stock=? WHERE  id=?";
	public final static String qUPDATE_ORDERS = "UPDATE orders SET price_result=? WHERE number_order=?";
	public final static String qUPDATE_ORDERS_STATUS = "UPDATE orders SET status=? WHERE number_order=?";
	public final static String qDELETE_ORDERS_HAS_DRINK ="DELETE FROM orders_has_drink WHERE orders_number_order=?";

}
