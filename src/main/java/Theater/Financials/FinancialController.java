package Theater.Financials;

import java.util.List;

import Theater.Financials.FinancialStats;
import Theater.FoodItem.FoodItem;
import Theater.FoodItem.FoodItemRepository;
import Theater.Ticket.Ticket;
import Theater.Ticket.TicketRepository;

public class FinancialController implements FinancialsService {

	TicketRepository ticketRep;
	FoodItemRepository foodItemRep;
	
	
	public FinancialController(TicketRepository ticketRep, FoodItemRepository foodItemRep) {
		super();
		this.ticketRep = ticketRep;
		this.foodItemRep = foodItemRep;
	}


	@Override
	public FinancialStats GetStats() {
		// TODO Auto-generated method stub

		List<FoodItem> foodItems = foodItemRep.getFoodItems();
		List<Ticket> tickets = ticketRep.getTickets();
		double totalFoodProfit = 0.0;
		double avgFoodProfit = 0.0;
		for (FoodItem fooditem : foodItems ) {
			totalFoodProfit+=fooditem.Profit();
		}
		
		if (foodItems.size() > 0) {
			avgFoodProfit = totalFoodProfit/foodItems.size();
		}
		
		
		
		double totalTicketProfit = 0;
		double avgTicketProfit = 0.0;
		for (Ticket ticket : tickets ) {
			totalTicketProfit+=ticket.Profit();
		}
		
		if (tickets.size() > 0) {
			avgTicketProfit=totalTicketProfit/tickets.size();
		}
		
		
		
		
		FinancialStats mystats = new FinancialStats(avgTicketProfit, avgFoodProfit);
		System.out.println(mystats.toString());
		
		
		return mystats;
	}

}
