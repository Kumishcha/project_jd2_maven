package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.InfoAboutUserAndAccount;
import by.htp.jd2.bean.User;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;



public interface UserService {
	
	/**
	 * 
	 * @param login as a String
	 * @return an object of type User
	 * @throws ServiceException if DAOException occurs
	 */
	User findUserByLogin(String login) throws ServiceException;

	/**
	 * 
	 * @param login as a String
	 * @param password as a String
	 * @return the authorized user
	 * @throws ServiceException if DAOException occurs
	 */
	User authorization(String login, String password) throws ServiceException;

	/**
	 * 
	 * @param name as a String
	 * @param surname as a String
	 * @param e_mail as a String
	 * @param login as a String
	 * @param password as a String
	 * @return the registered user
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	User registration(String name, String surname, String e_mail, String login, String password)
			throws ServiceException, ValidationServiceException;

	/**
	 * 
	 * @return list an object of type InfoAboutUserAndAccount
	 * @throws ServiceException if DAOException occurs
	 */
	List<InfoAboutUserAndAccount> viewAllUsers() throws ServiceException;
	
	/**
	 * 
	 * @param idUser as a int
	 * @return an object of type InfoAboutUserAndAccount
	 * @throws ServiceException if DAOException occurs
	 */
	InfoAboutUserAndAccount takeInfo(int idUser) throws ServiceException;

	/**
	 * 
	 * @param idUser as a int
	 * @return true, if user's role has changed
	 * @throws ServiceException if DAOException occurs
	 */
	boolean changeRole(int idUser) throws ServiceException;

	/**
	 * 
	 * @param idUser as a int
	 * @return true, if user's account has blocked
	 * @throws ServiceException if DAOException occurs
	 */
	boolean isBlockedAccount(int idUser) throws ServiceException;

	/**
	 * 
	 * @param idUser as a int
	 * @return true, if user's account has unblocked
	 * @throws ServiceException if DAOException occurs
	 */
	boolean isUnblockedAccount(int idUser) throws ServiceException;
	
	/**
	 * 
	 * @param idUser as a int
	 * @return an object of type Account
	 * @throws ServiceException if DAOException occurs
	 */
	Account take (int idUser) throws ServiceException;
	
	/**
	 * 
	 * @param idUser as a int
	 * @param count1 as a String
	 * @return an object of type Account
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	Account addAmount(int idUser, String count1) throws ServiceException, ValidationServiceException;
	
	/**
	 * 
	 * @param idUser as a int
	 * @param priceResult as a int
	 * @param numberOrder as a int
	 * @return true, if the order has been paid
	 * @throws ServiceException if DAOException occurs
	 */
	boolean orderPayment(int idUser,int priceResult, int numberOrder) throws ServiceException;
	

}
