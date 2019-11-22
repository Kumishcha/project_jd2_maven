package by.htp.jd2.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserDataValidator {

	// !!!!!!!!валидаторы не выбрасывают исключений
	private static final UserDataValidator instance = new UserDataValidator();
	
	public UserDataValidator() {
	}

	public boolean check(String email, String password) {

		String regexEmail;
		String regexPass;
		Pattern patternEmail;
		Pattern patternPass;
		Matcher matcherEmail;
		Matcher matcherPass;

		regexEmail = "^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		regexPass = "^[a-zA-Z0-9]+$";

		patternEmail = Pattern.compile(regexEmail);
		matcherEmail = patternEmail.matcher(email);

		patternPass = Pattern.compile(regexPass);
		matcherPass = patternPass.matcher(password);
	
		if (matcherEmail.matches() && matcherPass.matches()) {
			
			return true;
		} else {
			
			return false;
		}
	}

	public boolean check(String count1) {

		String regexCount;
		Pattern patternCount;
		Matcher matcherCount;
		double count;
		
		regexCount ="^\\d*[.,]?\\d{1,2}$";
		
		patternCount = Pattern.compile(regexCount);
		matcherCount = patternCount.matcher(count1);
	
		if (matcherCount.matches()) {
			count = Double.parseDouble(count1);
	
			if (count > 10000 || count < 0) {
			
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	public boolean checkPrice(String newPrice) {

		String regexCount;
		Pattern patternCount;
		Matcher matcherCount;
		double count;

		regexCount = "^\\d*[.,]?\\d{1,2}$";
		patternCount = Pattern.compile(regexCount);
		matcherCount = patternCount.matcher(newPrice);

		if (matcherCount.matches()) {
			count = Double.parseDouble(newPrice);
			if (count > 10 || count < 0) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkCountOfProduct(String productCount) {

		String regexCount;
		Pattern patternCount;
		Matcher matcherCount;
		int count;
	
		regexCount = "^\\d*$";
		patternCount = Pattern.compile(regexCount);
		matcherCount = patternCount.matcher(productCount);

		if (matcherCount.matches()) {
			count = Integer.parseInt(productCount);
			if (count > 10000 || count < 0) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	
	public boolean checkAmountOfDrink(String count1) {

		String regexCount;
		Pattern patternCount;
		Matcher matcherCount;
		int count;

		regexCount = "^\\d*{1,2}$";
		patternCount = Pattern.compile(regexCount);
		matcherCount = patternCount.matcher(count1);

		if (matcherCount.matches()) {
			count = Integer.parseInt(count1);
			if (count > 3|| count < 0) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean check(String typeOfCoffee, int volumeOfDrink) {

		if (typeOfCoffee.equals("espresso") && volumeOfDrink == 50) {
			return true;
		} else if (typeOfCoffee.equals("espresso") && volumeOfDrink == 100) {
			return true;
		} else if (typeOfCoffee.equals("cappuccino") && volumeOfDrink == 300) {
			return true;
		} else if (typeOfCoffee.equals("cappuccino") && volumeOfDrink == 400) {
			return true;
		} else if (typeOfCoffee.equals("latte") && volumeOfDrink == 300) {
			return true;
		} else if (typeOfCoffee.equals("latte") && volumeOfDrink == 400) {
			return true;
		}
		return false;
	}

	public static UserDataValidator getInstance() {
		return instance;
	}

}
