package by.htp.jd2.bean;

public class Drink {

	private int drinkId;
	private int fortress;
	private int typeOfCoffee;
	private int volumeOfDrink;
	private int countOfSugar;
	private int countOfSyrup;
	private int typeOfSyrup;
	private double cost;

	public Drink() {

	}

	public Drink(int drinkId, int fortress, int typeOfCoffee, int volumeOfDrink, int countOfSugar, int countOfSyrup,
			int typeOfSyrup, double cost) {

		this.drinkId = drinkId;
		this.fortress = fortress;
		this.typeOfCoffee = typeOfCoffee;
		this.volumeOfDrink = volumeOfDrink;
		this.countOfSugar = countOfSugar;
		this.countOfSyrup = countOfSyrup;
		this.typeOfSyrup = typeOfSyrup;
		this.cost = cost;
	}

	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public int getFortress() {
		return fortress;
	}

	public void setFortress(int fortress) {
		this.fortress = fortress;
	}

	public int getTypeOfCoffee() {
		return typeOfCoffee;
	}

	public void setTypeOfCoffee(int typeOfCoffee) {
		this.typeOfCoffee = typeOfCoffee;
	}

	public int getVolumeOfDrink() {
		return volumeOfDrink;
	}

	public void setVolumeOfDrink(int volumeOfDrink) {
		this.volumeOfDrink = volumeOfDrink;
	}

	public int getCountOfSugar() {
		return countOfSugar;
	}

	public void setCountOfSugar(int countOfSugar) {
		this.countOfSugar = countOfSugar;
	}

	public int getCountOfSyrup() {
		return countOfSyrup;
	}

	public void setCountOfSyrup(int countOfSyrup) {
		this.countOfSyrup = countOfSyrup;
	}

	public int getTypeOfSyrup() {
		return typeOfSyrup;
	}

	public void setTypeOfSyrup(int typeOfSyrup) {
		this.typeOfSyrup = typeOfSyrup;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + countOfSugar;
		result = prime * result + countOfSyrup;
		result = prime * result + drinkId;
		result = prime * result + fortress;
		result = prime * result + typeOfCoffee;
		result = prime * result + typeOfSyrup;
		result = prime * result + volumeOfDrink;
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
		Drink other = (Drink) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (countOfSugar != other.countOfSugar)
			return false;
		if (countOfSyrup != other.countOfSyrup)
			return false;
		if (drinkId != other.drinkId)
			return false;
		if (fortress != other.fortress)
			return false;
		if (typeOfCoffee != other.typeOfCoffee)
			return false;
		if (typeOfSyrup != other.typeOfSyrup)
			return false;
		if (volumeOfDrink != other.volumeOfDrink)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [drinkId=" + drinkId + ", fortress=" + fortress + ", typeOfCoffee="
				+ typeOfCoffee + ", volumeOfDrink=" + volumeOfDrink + ", countOfSugar=" + countOfSugar
				+ ", countOfSyrup=" + countOfSyrup + ", typeOfSyrup=" + typeOfSyrup + ", cost=" + cost + "]";
	}
}
