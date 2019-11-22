package by.htp.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.Order;
import by.htp.jd2.dao.OrdersDAO;
import by.htp.jd2.dao.pool.PoolConnection;

public class SQLOrdersDAO implements OrdersDAO {

	private static final PoolConnection pc = PoolConnection.getInstance();

	@Override
	public List<DrinkInOrder> takeDrinkInOrderList(int numberOrder) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		int drinkId;

		List<DrinkInOrder> listDrinksInOrder = new ArrayList<>();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qSELECT_ORDERS_HAS_DRINK);

			ps.setInt(1, numberOrder);
			rs = ps.executeQuery();

			while (rs.next()) {
				DrinkInOrder drinkInOrder = new DrinkInOrder();
				drinkInOrder.setDrinkId(rs.getInt(2));
				drinkInOrder.setPrice(rs.getDouble(3));
				drinkInOrder.setCount(rs.getInt(4));
				drinkInOrder.setNumberOrder(rs.getInt(5));
				listDrinksInOrder.add(drinkInOrder);
			}

			for (DrinkInOrder drinkInOrder : listDrinksInOrder) {
				drinkId = drinkInOrder.getDrinkId();

				ps1 = con.prepareStatement(FinalStringInsert.qSELECT_TYPE_OF_COFFEE_JOIN_DRINK);
				ps1.setInt(1, drinkId);
				rs = ps1.executeQuery();

				rs.next();
						
				drinkInOrder.setTypeOfCoffee(rs.getString(2));
			}
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
			if (ps1 != null) {
				try {
					ps1.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return listDrinksInOrder;
	}

	@Override
	public boolean addDrinkToOrderToCart(List<DrinkInOrder> listDrinksInOrder, int numberOrder) throws DAOException {

		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;

		try {

			con = pc.take();

			ps1 = con.prepareStatement(FinalStringInsert.qDELETE_ORDERS_HAS_DRINK);

			ps1.setInt(1, numberOrder);
			ps1.executeUpdate();

			for (DrinkInOrder drinkInOrder : listDrinksInOrder) {

				ps = con.prepareStatement(FinalStringInsert.qINSERT_ADD_DRINK_TO_ORDER);

				ps.setInt(1, drinkInOrder.getDrinkId());
				ps.setDouble(2, drinkInOrder.getPrice());
				ps.setInt(3, drinkInOrder.getCount());
				ps.setInt(4, drinkInOrder.getNumberOrder());
				ps.executeUpdate();

			}

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
			if (ps1 != null) {
				try {
					ps1.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return true;

	}

	@Override
	public Order findOrderIdByUserId(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Order newOrder = new Order();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qSELECT_ORDERS);

			ps.setInt(1, userId);
			ps.setBoolean(2, false);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}
			newOrder.setNumberOrder(rs.getInt(1));
			newOrder.setUserId(rs.getInt(2));
			newOrder.setPriceResult(rs.getInt(3));
			newOrder.setStatus(rs.getBoolean(4));

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
		return newOrder;
	}

	@Override
	public int createNewOrderAndExtractLastOrderId(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int numberOrder = 0;

		try {

			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qINSERT_ORDERS, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, userId);
			ps.setBoolean(2, false);
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			rs.next();
			numberOrder = rs.getInt(1);

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

		return numberOrder;
	}

	@Override
	public double calculateThePriceResult(int numberOrder) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		double priceResult = 0;
		double i = 0;

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qSELECT_ORDERS_HAS_DRINK);

			ps.setInt(1, numberOrder);
			rs = ps.executeQuery();

			while (rs.next()) {

				i = rs.getDouble(3) * rs.getInt(4);
				priceResult = priceResult + i;
			}

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

		return priceResult;
	}

	@Override
	public boolean addThePriceResultInOrder(double priceResult, int numberOrder) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {

			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qSELECT_ORDERS_WHERE_NUMBER_ORDER);

			ps.setInt(1, numberOrder);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return false;
			}

			ps1 = con.prepareStatement(FinalStringInsert.qUPDATE_ORDERS);

			ps1.setDouble(1, priceResult);
			ps1.setInt(2, numberOrder);

			ps1.executeUpdate();

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
			if (ps1 != null) {
				try {
					ps1.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}

		return true;
	}

	@Override
	public Order takeOrder(int numberOrder) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Order newOrder = new Order();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qSELECT_ORDERS_WHERE_NUMBER_ORDER);

			ps.setInt(1, numberOrder);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}
			newOrder.setNumberOrder(rs.getInt(1));
			newOrder.setUserId(rs.getInt(2));
			newOrder.setPriceResult(rs.getDouble(3));
			newOrder.setStatus(rs.getBoolean(4));

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
		return newOrder;
	}

	@Override
	public List<Order> takeOrdersList(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Order> orders = new ArrayList<>();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qSELECT_ORDERS);

			ps.setInt(1, userId);
			ps.setBoolean(2, true);
			rs = ps.executeQuery();

			while (rs.next()) {

				Order newOrder = new Order();
				newOrder.setNumberOrder(rs.getInt(1));
				newOrder.setUserId(rs.getInt(2));
				newOrder.setPriceResult(rs.getDouble(3));
				newOrder.setStatus(rs.getBoolean(4));
				orders.add(newOrder);
			}

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

		return orders;
	}

}
