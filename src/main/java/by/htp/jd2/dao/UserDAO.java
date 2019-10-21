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
	 * @param unknownId
	 * @return the user with the given id
	 * @throws DAOException
	 */
	User findUserById(int unknownId) throws DAOException;

	/**
	 * 
	 * @param login
	 * @return the user with the given login
	 * @throws DAOException
	 */
	User findUserByLogin(String login) throws DAOException;

	/**
	 * 
	 * @param user
	 * @return the registered user
	 * @throws DAOException
	 */
	User registration(RegistrationInfo user) throws DAOException;

	/**
	 * 
	 * @param login
	 * @param password
	 * @return the authorized user
	 * @throws DAOException
	 */
	User authorization(String login, String password) throws DAOException;

	/**
	 * 
	 * @return a list of all users
	 * @throws DAOException
	 */
	List<InfoAboutUserAndAccount> viewAllUsers() throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @return information about the user and his account
	 * @throws DAOException
	 */
	InfoAboutUserAndAccount takeInfo(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @return true, if user's role has changed
	 * @throws DAOException
	 */
	boolean changeRole(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @return true, if user's account has blocked
	 * @throws DAOException
	 */
	boolean isBlockedAccount(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @return true, if user's account has unblocked
	 * @throws DAOException
	 */
	boolean isUnblockedAccount(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @return user's account with the given user's id
	 * @throws DAOException
	 */
	Account take(int idUser) throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @param count
	 * @return an account with a modified value
	 * @throws DAOException
	 */
	Account addAmount(int idUser, int count) throws DAOException;

	/**
	 * 
	 * @param idUser
	 * @param priceResult
	 * @param numberOrder
	 * @return true, if order has been paid
	 * @throws DAOException
	 */
	boolean orderPayment(int idUser, int priceResult, int numberOrder) throws DAOException;
}
