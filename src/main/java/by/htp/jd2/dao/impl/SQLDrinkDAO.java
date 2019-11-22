package by.htp.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import by.htp.jd2.bean.Drink;
import by.htp.jd2.dao.DrinkDAO;
import by.htp.jd2.dao.pool.PoolConnection;

public class SQLDrinkDAO implements DrinkDAO {

	private static final PoolConnection pc = PoolConnection.getInstance();

	@Override
	public Drink find(int fortress, String typeOfCoffee, int volumeOfDrink, int countOfSugar, String typeOfSyrup)
			throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		
		ResultSet rs = null;
		int typeOfCoffeeId;
		int typeOfSyrupId;
		int countOfSyrup;
		

		Drink newDrink = new Drink();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_TYPE_OF_COFFEE_BY_TYPE);
			
			ps.setString(1, typeOfCoffee);
			rs = ps.executeQuery();

			rs.next();
			typeOfCoffeeId = rs.getInt(1);

			ps1 = con.prepareStatement(FinalStringInsert.qFIND_TYPE_OF_SYRUP_BY_TYPE);
			
			ps1.setString(1, typeOfSyrup);
			rs = ps1.executeQuery();

			rs.next();
			typeOfSyrupId = rs.getInt(1);

			if (typeOfSyrup.equals("without")) {
				countOfSyrup = 0;
			} else {
				countOfSyrup = 1;
			}

			ps2 = con.prepareStatement(FinalStringInsert.qFIND_DRINK_BY_CRITERIA);
		
			ps2.setInt(1, fortress);
			ps2.setInt(2, typeOfCoffeeId);
			ps2.setInt(3, volumeOfDrink);
			ps2.setInt(4, countOfSugar);
			ps2.setInt(5, countOfSyrup);
			ps2.setInt(6, typeOfSyrupId);
			rs = ps2.executeQuery();

			if (!rs.next()) {
				return null;
			}

			newDrink.setDrinkId(rs.getInt(1));
			newDrink.setFortress(rs.getInt(2));
			newDrink.setTypeOfCoffee(rs.getInt(3));
			newDrink.setVolumeOfDrink(rs.getInt(4));
			newDrink.setCountOfSugar(rs.getInt(9));
			newDrink.setCountOfSyrup(rs.getInt(11));
			newDrink.setTypeOfSyrup(rs.getInt(12));

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
			if (ps2 != null) {
				try {
					ps2.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return newDrink;

	}
	@SuppressWarnings("null")
	@Override
	public double drinkPrice(int drinkId) throws DAOException {

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
		double priceCoffee;
		int baseAmountOfMilk;
		double priceMilk;
		int baseAmountOfWater;
		double priceWater;
		int baseAmountOfSugar;
		double priceSugar;
		int baseAmountOfSyrup;
		double priceSyrup;
		double price;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_DRINK_BY_ID);
			
			ps.setInt(1, drinkId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return (Integer) null;
			}

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
			priceCoffee = price(typeOfCoffeeId,FinalStringInsert.qFIND_COFFEE_OPTIONS_IN_PRODUCT_TABLE);
			baseAmountOfMilk = baseAmount(milkId, FinalStringInsert.qFIND_MILK_BY_ID);
			priceMilk = price(milkId, FinalStringInsert.qFIND_MILK_OPTIONS_IN_PRODUCT_TABLE );
			baseAmountOfWater = baseAmount(waterId, FinalStringInsert.qFIND_WATER_BY_ID);
			priceWater = price(waterId,FinalStringInsert.qFIND_WATER_OPTIONS_IN_PRODUCT_TABLE );
			baseAmountOfSugar = baseAmount(sugarId,FinalStringInsert.qFIND_SUGAR_BY_ID );
			priceSugar = price(sugarId,FinalStringInsert.qFIND_SUGAR_OPTIONS_IN_PRODUCT_TABLE );
			baseAmountOfSyrup = baseAmount(typeOfSyrupId, FinalStringInsert.qFIND_TYPE_OF_SYRUP_BY_ID);
			priceSyrup = price(typeOfSyrupId,FinalStringInsert.qFIND_TYPE_OF_SYRUP_OPTIONS_IN_PRODUCT_TABLE );

			price = (fortress * baseAmountOfCoffee * priceCoffee) + (volumeOfMilk * baseAmountOfMilk * priceMilk)
					+ (volumeOfWater * baseAmountOfWater * priceWater) + (countOfSugar * baseAmountOfSugar * priceSugar)
					+ (countOfSyrup * baseAmountOfSyrup * priceSyrup);

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
		return price;
	}

	protected int baseAmount(int id, String a) throws DAOException {

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

	protected double price(int id, String b) throws DAOException {

		double price;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = pc.take();

			ps = con.prepareStatement(b);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			rs.next();
					
			price = rs.getDouble(4);

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
		return price;
	}
	
}
