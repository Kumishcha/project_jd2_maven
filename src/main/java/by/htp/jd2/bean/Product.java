package by.htp.jd2.bean;

public class Product {
	
	private int productId;
    private String productName;
    private int quantityInStock;
    private double price;
    private String userRole;
    
	public Product() {
		
	}

	public Product(int productId, String productName, int quantityInStock, double price, String userRole) {
		
		this.productId = productId;
		this.productName = productName;
		this.quantityInStock = quantityInStock;
		this.price = price;
		this.userRole = userRole;
	}

	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantityInStock;
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
		Product other = (Product) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantityInStock != other.quantityInStock)
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [productId=" + productId + ", productName=" + productName + ", quantityInStock="
				+ quantityInStock + ", price=" + price + ", userRole=" + userRole + "]" + '\n';
	}
	
}
