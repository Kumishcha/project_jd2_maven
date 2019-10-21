package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.InfoAboutUserAndAccount;
import by.htp.jd2.bean.User;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;



public interface UserService {
	
	User findUserByLogin(String login) throws ServiceException;

	User authorization(String login, String password) throws ServiceException;

	User registration(String name, String surname, String e_mail, String login, String password)
			throws ServiceException, ValidationServiceException;

	List<InfoAboutUserAndAccount> viewAllUsers() throws ServiceException;
	
	InfoAboutUserAndAccount takeInfo(int idUser) throws ServiceException;

	boolean changeRole(int idUser) throws ServiceException;

	boolean isBlockedAccount(int idUser) throws ServiceException;

	boolean isUnblockedAccount(int idUser) throws ServiceException;
	
	Account take (int idUser) throws ServiceException;
	
	Account addAmount(int idUser, String count1) throws ServiceException, ValidationServiceException;
	
	boolean orderPayment(int idUser,int priceResult, int numberOrder) throws ServiceException;
	

}
