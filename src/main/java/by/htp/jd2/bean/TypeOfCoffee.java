package by.htp.jd2.bean;

public class TypeOfCoffee {
	
	private int typeOfCoffeeId;
    private String type;
    private int baseAmountOfProduct;
    private int productId;
    
	public TypeOfCoffee() {

	}

	public TypeOfCoffee(int typeOfCoffeeId, String type, int baseAmountOfProduct, int productId) {
	
		this.typeOfCoffeeId = typeOfCoffeeId;
		this.type = type;
		this.baseAmountOfProduct = baseAmountOfProduct;
		this.productId = productId;
	}

	public int getTypeOfCoffeeId() {
		return typeOfCoffeeId;
	}

	public void setTypeOfCoffeeId(int typeOfCoffeeId) {
		this.typeOfCoffeeId = typeOfCoffeeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBaseAmountOfProduct() {
		return baseAmountOfProduct;
	}

	public void setBaseAmountOfProduct(int baseAmountOfProduct) {
		this.baseAmountOfProduct = baseAmountOfProduct;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseAmountOfProduct;
		result = prime * result + productId;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeOfCoffeeId;
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
		TypeOfCoffee other = (TypeOfCoffee) obj;
		if (baseAmountOfProduct != other.baseAmountOfProduct)
			return false;
		if (productId != other.productId)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeOfCoffeeId != other.typeOfCoffeeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [typeOfCoffeeId=" + typeOfCoffeeId + ", type=" + type + ", baseAmountOfProduct="
				+ baseAmountOfProduct + ", productId=" + productId + "]";
	}
}
