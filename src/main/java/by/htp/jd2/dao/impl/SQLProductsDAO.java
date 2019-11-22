package by.htp.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.htp.jd2.bean.Product;
import by.htp.jd2.dao.ProductsDAO;
import by.htp.jd2.dao.pool.PoolConnection;

public class SQLProductsDAO implements ProductsDAO {

	private static final PoolConnection pc = PoolConnection.getInstance();

	@Override
	public Product findProductByName(String productName) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Product newProduct = new Product();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_PRODUCT_BY_NAME);

			ps.setString(1, productName);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			newProduct.setProductId(rs.getInt(1));
			newProduct.setProductName(rs.getString(2));
			newProduct.setQuantityInStock(rs.getInt(3));
			newProduct.setPrice(rs.getDouble(4));

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return newProduct;
	}

	@Override
	public List<Product> viewStock() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Product> products = new ArrayList<>();

		try {

			con = pc.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.qSELECT_LIST_OF_PRODUCTS);

			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setQuantityInStock(rs.getInt(3));
				product.setPrice(rs.getDouble(4));
				product.setUserRole(rs.getString(5));
				products.add(product);

			}

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}

		return products;
	}

	@Override
	public boolean increaseQuantityInStock(String productName, int count) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		Product product;

		try {
			con = pc.take();

			product = findProductByName(productName);

			if (null == product) {
				return false;
			}

			ps = con.prepareStatement(FinalStringInsert.qUPDATE_QUANTITY_IN_STOCK);

			ps.setInt(1, count);
			ps.setString(2, productName);
			ps.executeUpdate();

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return true;

	}

	@Override
	public boolean changePriceOfProducts(String productName, double price) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		Product product;

		try {
			con = pc.take();

			product = findProductByName(productName);

			if (null == product) {
				return false;
			}

			ps = con.prepareStatement(FinalStringInsert.qUPDATE_PRICE_IN_STOCK);

			ps.setDouble(1, price);
			ps.setString(2, productName);
			ps.executeUpdate();

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return true;
	}

	public void changeQuantityOfProducts(int count, int drinkId) throws DAOException {

		int fortress;
		int typeOfCoffeeId;
		int volumeOfMilk;
		int milkId;
		int volumeOfWater;
		int waterId;
		int countOfSugar;
		int sugarId;
		int countOfSyrup;
		int typeOfSyrupId;
		int baseAmountOfCoffee;
		int quantityCoffee;
		int baseAmountOfMilk;
		int quantityMilk;
		int baseAmountOfWater;
		int quantityWater;
		int baseAmountOfSugar;
		int quantitySugar;
		int baseAmountOfSyrup;
		int quantitySyrup;
		int newQuantityCoffee;
		int newQuantityMilk;
		int newQuantityWater;
		int newQuantitySugar;
		int newQuantitySyrup;
		int coffeeIdInProductsTable;
		int milkIdInProductsTable;
		int waterIdInProductsTable;
		int sugarIdInProductsTable;
		int syrupIdInProductsTable;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_DRINK_BY_ID);

			ps.setInt(1, drinkId);
			rs = ps.executeQuery();

			rs.next();

			fortress = rs.getInt(2);
			typeOfCoffeeId = rs.getInt(3);
			volumeOfMilk = rs.getInt(5);
			milkId = rs.getInt(6);
			volumeOfWater = rs.getInt(7);
			waterId = rs.getInt(8);
			countOfSugar = rs.getInt(9);
			sugarId = rs.getInt(10);
			countOfSyrup = rs.getInt(11);
			typeOfSyrupId = rs.getInt(12);

			baseAmountOfCoffee = baseAmount(typeOfCoffeeId, FinalStringInsert.qFIND_TYPE_OF_COFFEE_BY_ID);
			quantityCoffee = quantityInStock(typeOfCoffeeId, FinalStringInsert.qFIND_COFFEE_OPTIONS_IN_PRODUCT_TABLE);
			baseAmountOfMilk = baseAmount(milkId, FinalStringInsert.qFIND_MILK_BY_ID);
			quantityMilk = quantityInStock(milkId, FinalStringInsert.qFIND_MILK_OPTIONS_IN_PRODUCT_TABLE);
			baseAmountOfWater = baseAmount(waterId, FinalStringInsert.qFIND_WATER_BY_ID);
			quantityWater = quantityInStock(waterId, FinalStringInsert.qFIND_WATER_OPTIONS_IN_PRODUCT_TABLE);
			baseAmountOfSugar = baseAmount(sugarId, FinalStringInsert.qFIND_SUGAR_BY_ID);
			quantitySugar = quantityInStock(sugarId, FinalStringInsert.qFIND_SUGAR_OPTIONS_IN_PRODUCT_TABLE);
			baseAmountOfSyrup = baseAmount(typeOfSyrupId, FinalStringInsert.qFIND_TYPE_OF_SYRUP_BY_ID);
			quantitySyrup = quantityInStock(typeOfSyrupId,
					FinalStringInsert.qFIND_TYPE_OF_SYRUP_OPTIONS_IN_PRODUCT_TABLE);

			coffeeIdInProductsTable = findIdInProductsTable(typeOfCoffeeId,
					FinalStringInsert.qFIND_TYPE_OF_COFFEE_BY_ID);
			milkIdInProductsTable = findIdInProductsTable(milkId, FinalStringInsert.qFIND_MILK_BY_ID);
			waterIdInProductsTable = findIdInProductsTable(waterId, FinalStringInsert.qFIND_WATER_BY_ID);
			sugarIdInProductsTable = findIdInProductsTable(sugarId, FinalStringInsert.qFIND_SUGAR_BY_ID);
			syrupIdInProductsTable = findIdInProductsTable(typeOfSyrupId, FinalStringInsert.qFIND_TYPE_OF_SYRUP_BY_ID);

			newQuantityCoffee = quantityCoffee - count * (fortress * baseAmountOfCoffee);
			newQuantityMilk = quantityMilk - count * (volumeOfMilk * baseAmountOfMilk);
			newQuantityWater = quantityWater - count * (volumeOfWater * baseAmountOfWater);
			newQuantitySugar = quantitySugar - count * (countOfSugar * baseAmountOfSugar);
			newQuantitySyrup = quantitySyrup - count * (countOfSyrup * baseAmountOfSyrup);

			updateProductsTable(newQuantityCoffee, coffeeIdInProductsTable, FinalStringInsert.qUPDATE_PRODUCT_IN_STOCK);
			updateProductsTable(newQuantityMilk, milkIdInProductsTable, FinalStringInsert.qUPDATE_PRODUCT_IN_STOCK);
			updateProductsTable(newQuantityWater, waterIdInProductsTable, FinalStringInsert.qUPDATE_PRODUCT_IN_STOCK);
			updateProductsTable(newQuantitySugar, sugarIdInProductsTable, FinalStringInsert.qUPDATE_PRODUCT_IN_STOCK);
			updateProductsTable(newQuantitySyrup, syrupIdInProductsTable, FinalStringInsert.qUPDATE_PRODUCT_IN_STOCK);

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}

	}

	private void updateProductsTable(int a, int b, String c) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = pc.take();
			ps = con.prepareStatement(c);

			ps.setInt(1, a);
			ps.setInt(2, b);
			ps.executeUpdate();

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
	}

	private int findIdInProductsTable(int id, String a) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInProductsTable;

		try {

			con = pc.take();

			ps = con.prepareStatement(a);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			rs.next();

			idInProductsTable = rs.getInt(4);

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}

		return idInProductsTable;
	}

	private int baseAmount(int id, String a) throws DAOException {

		int baseAmount;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = pc.take();

			ps = con.prepareStatement(a);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			rs.next();

			baseAmount = rs.getInt(3);

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return baseAmount;
	}

	private int quantityInStock(int id, String b) throws DAOException {

		int quantity;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = pc.take();

			ps = con.prepareStatement(b);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			rs.next();
			
			quantity = rs.getInt(3);

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return quantity;

	}

}
