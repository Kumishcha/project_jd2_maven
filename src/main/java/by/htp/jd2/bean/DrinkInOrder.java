package by.htp.jd2.bean;

public class DrinkInOrder {
	
	private int ordersHasDrinkId;
	private int drinkId;
	private String typeOfCoffee;
	private double price;
	private int count;
	private int numberOrder;
	
	
	public DrinkInOrder() {
	}

	public DrinkInOrder(int ordersHasDrinkId, int drinkId, String typeOfCoffee, double price, int count, int numberOrder) {
		
		this.ordersHasDrinkId = ordersHasDrinkId;
		this.drinkId = drinkId;
		this.typeOfCoffee = typeOfCoffee;
		this.price = price;
		this.count = count;
		this.numberOrder = numberOrder;
	}

	public int getOrdersHasDrinkId() {
		return ordersHasDrinkId;
	}

	public void setOrdersHasDrinkId(int ordersHasDrinkId) {
		this.ordersHasDrinkId = ordersHasDrinkId;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public String getTypeOfCoffee() {
		return typeOfCoffee;
	}

	public void setTypeOfCoffee(String typeOfCoffee) {
		this.typeOfCoffee = typeOfCoffee;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNumberOrder() {
		return numberOrder;
	}

	public void setNumberOrder(int numberOrder) {
		this.numberOrder = numberOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + drinkId;
		result = prime * result + numberOrder;
		result = prime * result + ordersHasDrinkId;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((typeOfCoffee == null) ? 0 : typeOfCoffee.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrinkInOrder other = (DrinkInOrder) obj;
		if (count != other.count)
			return false;
		if (drinkId != other.drinkId)
			return false;
		if (numberOrder != other.numberOrder)
			return false;
		if (ordersHasDrinkId != other.ordersHasDrinkId)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (typeOfCoffee == null) {
			if (other.typeOfCoffee != null)
				return false;
		} else if (!typeOfCoffee.equals(other.typeOfCoffee))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" [ordersHasDrinkId=" + ordersHasDrinkId + ", drinkId=" + drinkId + ", typeOfCoffee="
				+ typeOfCoffee + ", price=" + price + ", count=" + count + ", numberOrder=" + numberOrder + "]";
	}

}
