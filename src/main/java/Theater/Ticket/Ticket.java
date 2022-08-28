package Theater.Ticket;

public class Ticket {

	public int id;
	public String movieName;
	public double salePrice;
	public double studioCutPercentage;
	public int quantity;
	
	
	public Ticket() {
		super();
	}
	

	public Ticket(String movieName, double salePrice, double studioCutPercentage, int quantity) {
		super();
		this.movieName = movieName;
		this.salePrice = salePrice;
		this.studioCutPercentage = studioCutPercentage;
		this.quantity = quantity;
	}


	


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", movieName=" + movieName + ", salePrice=" + salePrice + ", studioCutPercentage="
				+ studioCutPercentage + ", quantity=" + quantity + "]";
	}


	public Ticket(int ID, String movieName, double salePrice, double studioCutPercentage, int quantity) {
		super();
		id = ID;
		this.movieName = movieName;
		this.salePrice = salePrice;
		this.studioCutPercentage = studioCutPercentage;
		this.quantity = quantity;
	}
	
	public double Profit() {
		return (quantity * salePrice) - (studioCutPercentage * (quantity * salePrice));
	}
	
	public double ProfitPerItem() {
		return salePrice - (studioCutPercentage * salePrice);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getStudioCutPercentage() {
		return studioCutPercentage;
	}

	public void setStudioCutPercentage(double studioCutPercentage) {
		this.studioCutPercentage = studioCutPercentage;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
}
