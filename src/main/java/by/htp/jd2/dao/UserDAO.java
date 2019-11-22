package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.InfoAboutUserAndAccount;
import by.htp.jd2.bean.RegistrationInfo;
import by.htp.jd2.bean.User;
import by.htp.jd2.dao.impl.DAOException;

public interface UserDAO {

	/**
	 * 
	 * @param unknownId as a int
	 * @return the user with the given id
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	User findUserById(int unknownId) throws DAOException;

	/**
	 * 
	 * @param login as a String
	 * @return the user with the given login
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	User findUserByLogin(String login) throws DAOException;

	/**
	 * 
	 * @param user as a RegistrationInfo
	 * @return the registered user
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	User registration(RegistrationInfo user) throws DAOException;

	/**
	 * 
	 * @param login as a String
	 * @param password as a String
	 * @return the authorized user
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	User authorization(String login, String password) throws DAOException;

	/**
	 * 
	 * @return a list of all users
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	List<InfoAboutUserAndAccount> viewAllUsers() throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @return information about the user and his account
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	InfoAboutUserAndAccount takeInfo(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @return true, if user's role has changed
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean changeRole(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @return true, if user's account has blocked
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean isBlockedAccount(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @return true, if user's account has unblocked
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean isUnblockedAccount(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @return user's account with the given user's id
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	Account take(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @param count as a double
	 * @return an account with a modified value
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	Account addAmount(int idUser, double count) throws DAOException;

	/**
	 * 
	 * @param idUser as a int
	 * @param priceResult as a double
	 * @param numberOrder as a int
	 * @return true, if order has been paid
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean orderPayment(int idUser, double priceResult, int numberOrder) throws DAOException;
}
