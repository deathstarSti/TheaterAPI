package Theater.Financials;

public class FinancialStats {
	public double averageTicketProfit ;
    public double averageFoodItemProfit;
    
    
	public FinancialStats() {
		super();
	}


	public FinancialStats(double averageTicketProfit, double averageFoodItemProfit) {
		super();
		this.averageTicketProfit = averageTicketProfit;
		this.averageFoodItemProfit = averageFoodItemProfit;
	}


	public double getAverageTicketProfit() {
		return averageTicketProfit;
	}


	public void setAverageTicketProfit(double averageTicketProfit) {
		this.averageTicketProfit = averageTicketProfit;
	}


	public double getAverageFoodItemProfit() {
		return averageFoodItemProfit;
	}


	public void setAverageFoodItemProfit(double averageFoodItemProfit) {
		this.averageFoodItemProfit = averageFoodItemProfit;
	}


	@Override
	public String toString() {
		return "FinancialStats [averageTicketProfit=" + averageTicketProfit + ", averageFoodItemProfit="
				+ averageFoodItemProfit + "]";
	}
    
	

}
