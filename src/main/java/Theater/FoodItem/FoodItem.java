package Theater.FoodItem;

public class FoodItem {
	public int id;
	public String name;
	public double salePrice;
	public double unitPrice;
	public int quantity;
	
	
	public FoodItem() {
		super();
	}

	public FoodItem(String name, double salePrice, double unitPrice, int quantity) {
		super();
		this.name = name;
		this.salePrice = salePrice;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	public FoodItem(int id, String name, double salePrice, double unitPrice, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.salePrice = salePrice;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}


	public double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double Profit() {
		return (salePrice * quantity) - (unitPrice * quantity);
	}


	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", salePrice=" + salePrice + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + "]";
	}


	

	
	
	
}
