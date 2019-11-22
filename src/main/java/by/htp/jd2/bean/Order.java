package by.htp.jd2.bean;

public class Order {
	
	private int numberOrder;
	private int userId;
	private double priceResult;
	private boolean status;
	
	public Order() {
	}

	public Order(int numberOrder, int userId, double priceResult, boolean status) {
		
		this.numberOrder = numberOrder;
		this.userId = userId;
		this.priceResult = priceResult;
		this.status = status;
	}

	public int getNumberOrder() {
		return numberOrder;
	}

	public void setNumberOrder(int numberOrder) {
		this.numberOrder = numberOrder;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getPriceResult() {
		return priceResult;
	}

	public void setPriceResult(double priceResult) {
		this.priceResult = priceResult;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOrder;
		long temp;
		temp = Double.doubleToLongBits(priceResult);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + userId;
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
		Order other = (Order) obj;
		if (numberOrder != other.numberOrder)
			return false;
		if (Double.doubleToLongBits(priceResult) != Double.doubleToLongBits(other.priceResult))
			return false;
		if (status != other.status)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [numberOrder=" + numberOrder + ", userId=" + userId + ", priceResult=" + priceResult
				+ ", status=" + status + "]";
	}
}
