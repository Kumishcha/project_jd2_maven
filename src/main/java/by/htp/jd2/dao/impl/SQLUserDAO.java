package by.htp.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.InfoAboutUserAndAccount;
import by.htp.jd2.bean.RegistrationInfo;
import by.htp.jd2.bean.User;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.dao.pool.PoolConnection;


public class SQLUserDAO implements UserDAO {

	private static final PoolConnection pc = PoolConnection.getInstance();


	public User findUserById(int unknownId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User newUser = new User();

		try {
			
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_USER_BY_ID);

			ps.setInt(1, unknownId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			newUser.setUserId(rs.getInt(1));
			newUser.setEmail(rs.getString(4));
			newUser.setName(rs.getString(5));
			newUser.setSurname(rs.getString(6));
			newUser.setRole(rs.getString(7));
			newUser.setAccountId(rs.getInt(8));

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
		return newUser;
	}

	@Override
	public User findUserByLogin(String login) throws DAOException { 
																						
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User newUser = new User();

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_USER_BY_LOGIN);

			ps.setString(1, login);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			while (rs.next()) {

				newUser.setUserId(rs.getInt(1));
				newUser.setEmail(rs.getString(4));
				newUser.setName(rs.getString(5));
				newUser.setSurname(rs.getString(6));
				newUser.setRole(rs.getString(7));
				newUser.setAccountId(rs.getInt(8));
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
		return newUser;
	}

	@Override
	public User registration(RegistrationInfo info) throws DAOException {

		Connection con = null;
		PreparedStatement psAccount = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Account account = new Account();
		User newUser = new User();

		try {

			con = pc.take();

			psAccount = con.prepareStatement(FinalStringInsert.qINSERT_ACCOUNT_INFO);

			psAccount.setInt(1, 0);
			psAccount.setBoolean(2, false);

			psAccount.executeUpdate();

			rs = psAccount.executeQuery(FinalStringInsert.qSELECT_ACCOUNT_LAST_INSERT_ID);

			if (rs.next()) {
				account.setAccountId(rs.getInt(1));
				account.setBalance(rs.getInt(2));
				account.setBlocked(rs.getBoolean(3));
			}

			ps = con.prepareStatement(FinalStringInsert.qINSERT_REGISTRATION_INFO);

			ps.setString(1, info.getLogin());
			ps.setString(2, info.getPassword());
			ps.setString(3, info.getEmail());
			ps.setString(4, info.getName());
			ps.setString(5, info.getSurname());
			ps.setString(6, "client");
			ps.setInt(7, account.getAccountId());
			ps.executeUpdate();

			rs = ps.executeQuery(FinalStringInsert.qSELECT_USER_LAST_INSERT_ID);

			if (rs.next()) {
				newUser.setUserId(rs.getInt(1));
				newUser.setEmail(rs.getString(4));
				newUser.setName(rs.getString(5));
				newUser.setSurname(rs.getString(6));
				newUser.setRole(rs.getString(7));
				newUser.setAccountId(rs.getInt(8));
			}

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			System.err.println();

		} finally {
			if (psAccount != null) {
				try {
					psAccount.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}

		return newUser;
	}

	@Override
	public User authorization(String login, String password) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User newUser = new User();
        
		try {
			con = pc.take();
		
			ps = con.prepareStatement(FinalStringInsert.qFIND_USER_BY_LOGIN_AND_PASSWORD);
			
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				newUser.setUserId(rs.getInt(1));
				newUser.setEmail(rs.getString(4));
				newUser.setName(rs.getString(5));
				newUser.setSurname(rs.getString(6));
				newUser.setRole(rs.getString(7));
				newUser.setAccountId(rs.getInt(8));
			} else {
				return null;
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
		return newUser;
	}

	@Override
	public boolean changeRole(int idUser) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		User user;

		try {
			con = pc.take();

			user = findUserById(idUser);
			
			if (null == user) {
				return false;
			}
			
			if(user.getRole().equals("client")) {
				ps = con.prepareStatement(FinalStringInsert.qUPDATE_USER_ROLE);

				ps.setString(1, "admin");
				ps.setInt(2, idUser);
				ps.executeUpdate();
			}else if(user.getRole().equals("admin")) {
				ps = con.prepareStatement(FinalStringInsert.qUPDATE_USER_ROLE);

				ps.setString(1, "client");
				ps.setInt(2, idUser);
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

			pc.release(con);
		}
		return true;

	}

	@Override
	public InfoAboutUserAndAccount takeInfo(int idUser) throws DAOException {
		
		User user;
		Account account;
				
		user = findUserById(idUser);
		account = take(idUser);
		
		InfoAboutUserAndAccount info = new InfoAboutUserAndAccount();
		
		info.setUserId(user.getUserId());
		info.setName(user.getName());
		info.setSurname(user.getSurname());
		info.setEmail(user.getEmail());
		info.setRole(user.getRole());
		info.setAccountId(account.getAccountId());
		info.setBalance(account.getBalance());
		info.setBlocked(account.isBlocked());
		
		return info;
	}
	
	@Override
	public List<InfoAboutUserAndAccount> viewAllUsers() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		List<InfoAboutUserAndAccount> users = new ArrayList<>();

		try {
			con = pc.take();

			st = con.createStatement();
			rs = st.executeQuery(FinalStringInsert.qFIND_ALL_USERS);

			while (rs.next()) {
				InfoAboutUserAndAccount info = new InfoAboutUserAndAccount();
				info.setUserId(rs.getInt(1));
				info.setEmail(rs.getString(4));
				info.setName(rs.getString(5));
				info.setSurname(rs.getString(6));
				info.setRole(rs.getString(7));
				info.setAccountId(rs.getInt(8));

				users.add(info);
			}

			for (InfoAboutUserAndAccount info : users) {
		
				if(info.getAccountId() != 0) {
					ps = con.prepareStatement(FinalStringInsert.qFIND_ACCOUNT_BY_ID);
					ps.setInt(1, info.getAccountId());
					rs1 = ps.executeQuery();
					rs1.next();
					info.setBalance(rs1.getInt(2));
					info.setBlocked(rs1.getBoolean(3));
				} 
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
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			pc.release(con);
		}
		return users;
	}

	@Override
	public boolean isBlockedAccount(int idUser) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psAccount = null;
		ResultSet rs = null;

		int accountId;

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_USER_BY_ID);

			ps.setInt(1, idUser);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return false;
			}

			accountId = rs.getInt(8);

			psAccount = con.prepareStatement(FinalStringInsert.qUPDATE_ACCOUNT_BLOCK);

			psAccount.setBoolean(1, true);
			psAccount.setInt(2, accountId);
			psAccount.executeUpdate();

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (psAccount != null) {
				try {
					psAccount.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
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
	public boolean isUnblockedAccount(int idUser) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psAccount = null;
		ResultSet rs = null;

		int accountId;

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_USER_BY_ID);

			ps.setInt(1, idUser);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return false;
			}

			accountId = rs.getInt(8);

			psAccount = con.prepareStatement(FinalStringInsert.qUPDATE_ACCOUNT_BLOCK);

			psAccount.setBoolean(1, false);
			psAccount.setInt(2, accountId);
			psAccount.executeUpdate();

		} catch (InterruptedException e) {

			throw new DAOException(e);

		} catch (SQLException e) {

			throw new DAOException(e);

		} finally {
			if (psAccount != null) {
				try {
					psAccount.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
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
	public Account take(int idUser) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;

		Account newAccount = new Account();
		int idAccount;

		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qFIND_USER_BY_ID);

			ps.setInt(1, idUser);
			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			idAccount = rs.getInt(8);

			ps1 = con.prepareStatement(FinalStringInsert.qFIND_ACCOUNT_BY_ID);

			ps1.setInt(1, idAccount);
			rs = ps1.executeQuery();

			if (!rs.next()) {
				return null;
			}

			newAccount.setAccountId(rs.getInt(1));
			newAccount.setBalance(rs.getInt(2));
			newAccount.setBlocked(rs.getBoolean(3));

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
		return newAccount;
	}

	@Override
	public Account addAmount(int idUser, int count) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		int newBalance;

		Account account;

		try {
			con = pc.take();

			account = take(idUser);
			newBalance = account.getBalance() + count;

			ps = con.prepareStatement(FinalStringInsert.qUPDATE_ACCOUNT_BALANCE);

			ps.setInt(1, newBalance);
			ps.setInt(2, account.getAccountId());
			ps.executeUpdate();

			ps1 = con.prepareStatement(FinalStringInsert.qFIND_ACCOUNT_BY_ID);

			ps1.setInt(1, account.getAccountId());
			rs = ps1.executeQuery();

			if (!rs.next()) {
				return null;
			}

			account.setAccountId(rs.getInt(1));
			account.setBalance(rs.getInt(2));
			account.setBlocked(rs.getBoolean(3));

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
		return account;
	}

	@Override
	public boolean orderPayment(int idUser,int priceResult, int numberOrder) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		
		Account newAccount = new Account();
		int balance;

		newAccount = take(idUser);
		balance = newAccount.getBalance();
		
		if(balance < priceResult) {
			return false;
		}
		
		addAmount(idUser,-priceResult);
		
		try {
			con = pc.take();

			ps = con.prepareStatement(FinalStringInsert.qUPDATE_ORDERS_STATUS);

			ps.setBoolean(1, true);
			ps.setInt(2, numberOrder);
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

	

}
