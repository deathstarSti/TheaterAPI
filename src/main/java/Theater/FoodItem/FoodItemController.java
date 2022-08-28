package Theater.FoodItem;

import java.util.List;

import Theater.FoodItem.FoodItem;
import Theater.FoodItem.FoodItemRepository;
import Theater.Ticket.Ticket;


public class FoodItemController implements IFoodService {
	
	FoodItemRepository foodItemRep;
	
	
	public FoodItemController() {
		super();
	}

	
	public FoodItemController(FoodItemRepository foodItemRepository) {
		// TODO Auto-generated constructor stub
		
		super();
		this.foodItemRep = foodItemRepository;
	}
	

	@Override
	public List<FoodItem> GetAllSold() {
		// TODO Auto-generated method stub
		return foodItemRep.getFoodItems();
		
		
	}
	
	public FoodItem GetFoodItem(int id) {
		// TODO Auto-generated method stub
		return foodItemRep.getFoodItem(id);
		
		
	}
	
	public int  InsertFoodItem(FoodItem newFoodItem) {
		// TODO Auto-generated method stub
		return foodItemRep.insertFoodItem(newFoodItem);
		
		
	}
	
	public int  UpdateFoodItem(int id, FoodItem FoodItem) {
		// TODO Auto-generated method stub
		return foodItemRep.updateFoodItem(id, FoodItem);
		
		
	}

	public int  DeleteFoodItem(int id) {
		// TODO Auto-generated method stub
		return foodItemRep.deleteFoodItem(id);
		
		
	}

}
