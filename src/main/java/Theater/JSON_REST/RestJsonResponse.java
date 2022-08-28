package Theater.JSON_REST;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import Theater.Ticket.Ticket;
import Theater.Ticket.TicketController;
import Theater.Ticket.TicketRepository;
import Theater.Financials.FinancialController;
import Theater.Financials.FinancialStats;
import Theater.FoodItem.FoodItem;
import Theater.FoodItem.FoodItemRepository;
import Theater.FoodItem.FoodItemController;



@RestController
@RequestMapping(path = "/", produces = "application/json")
@CrossOrigin(origins = "*")
public class RestJsonResponse {

	@GetMapping("*")
	public ResponseEntity<String> other() {

		return ResponseEntity.badRequest()
	            .body("Wrong Path");
	}
	
	@GetMapping("/theater/*")
	public ResponseEntity<String> theater() {

		return ResponseEntity.badRequest()
	            .body("Wrong Path");
	}
	
	@GetMapping("")
	public ResponseEntity<String> empty() {

		return ResponseEntity.badRequest()
	            .body("Wrong Path");
	}
	
	@GetMapping("/theater/stats")
	public FinancialStats getStats() {

		TicketRepository ticketRepository = new TicketRepository();
		FoodItemRepository foodItemRepository = new FoodItemRepository();
		FinancialController financialController = new FinancialController(ticketRepository, foodItemRepository);

		return financialController.GetStats();

	}

	@GetMapping("/theater/tickets")
	public List<Ticket> getTickets() {

		TicketRepository ticketRepository = new TicketRepository();
		TicketController ticketController = new TicketController(ticketRepository);

		List<Ticket> tickets = ticketController.GetAllSold();

		return tickets;

	}

	

	@GetMapping("/theater/tickets/{id}")
	public ResponseEntity<String> getTicket(@PathVariable("id") String id) {

		TicketRepository ticketRepository = new TicketRepository();
		TicketController ticketController = new TicketController(ticketRepository);

		Ticket ticket = ticketController.GetTicket(Integer.parseInt(id));

		if (ticket != null) {
			HttpHeaders headers = new HttpHeaders();
			ResponseEntity<String> entity = new ResponseEntity<String>(new Gson().toJson(ticket), headers, HttpStatus.CREATED);
			return entity;

		}

//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return ResponseEntity.badRequest()
	            .body("Ticket ID not present");

	}
	
	@PostMapping(path = "/theater/tickets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket newTicket) throws ServerException  {
		TicketRepository ticketRepository = new TicketRepository();
		TicketController ticketController = new TicketController(ticketRepository);

		if (newTicket == null) {
			
			throw new ServerException("no Ticket defined");
		} else {
			int newID = ticketController.InsertTicket(newTicket);
			if ( newID > 0) {
				newTicket.setId(newID);
				return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
			}
				
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping(path = "/theater/tickets/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateTicket(@PathVariable("id") String id, @RequestBody Ticket updateTicket) throws ServerException  {
		TicketRepository ticketRepository = new TicketRepository();
		TicketController ticketController = new TicketController(ticketRepository);

		if (updateTicket == null) {
			
			return ResponseEntity.badRequest()
		            .body("No Ticket Defined to update");
		} else {
			
			if (ticketController.GetTicket(Integer.parseInt(id)) == null) {
				return ResponseEntity.badRequest()
			            .body("Ticket ID not present");
			}
			int updatedID = ticketController.UpdateTicket(Integer.parseInt(id), updateTicket);
			if ( updatedID > 0) {
				ResponseEntity<String> entity = ResponseEntity.ok().body("Ticket updated");
				return entity;
			}
				
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}
	
	
	@DeleteMapping("/theater/tickets/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable("id") String id) {

		TicketRepository ticketRepository = new TicketRepository();
		TicketController ticketController = new TicketController(ticketRepository);
		
		if (ticketController.DeleteTicket(Integer.parseInt(id)) > 0) {
			ResponseEntity<String> entity = ResponseEntity.ok().body("Ticket deleted");
			return entity;
		}
		return ResponseEntity.badRequest()
	            .body("Ticket ID not present");

	}

	
	
	@GetMapping("/theater/fooditems")
	public List<FoodItem> getFoodItems() {

		FoodItemRepository foodItemRepository = new FoodItemRepository();
		FoodItemController foodItemController = new FoodItemController(foodItemRepository);

		List<FoodItem> foodItems = foodItemController.GetAllSold();

		return foodItems;

	}
	
	@GetMapping("/theater/fooditems/{id}")
	public ResponseEntity<String> getFoodItem(@PathVariable("id") String id) {

		FoodItemRepository foodItemRepository = new FoodItemRepository();
		FoodItemController foodItemController = new FoodItemController(foodItemRepository);

		FoodItem foodItem = foodItemController.GetFoodItem(Integer.parseInt(id));

		if (foodItem != null) {
			HttpHeaders headers = new HttpHeaders();
			ResponseEntity<String> entity = new ResponseEntity<String>(new Gson().toJson(foodItem), headers, HttpStatus.CREATED);
			return entity;

		}

//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return ResponseEntity.badRequest()
	            .body("FoodItem ID not present");

	}
	
	@PostMapping(path = "/theater/fooditems", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem newFoodItem) throws ServerException  {
		
		FoodItemRepository foodItemRepository = new FoodItemRepository();
		FoodItemController foodItemController = new FoodItemController(foodItemRepository);

		if (newFoodItem == null) {
			
			throw new ServerException("no FoodItem defined");
		} else {
			int newID = foodItemController.InsertFoodItem(newFoodItem);
			if ( newID > 0) {
				newFoodItem.setId(newID);
				return new ResponseEntity<>(newFoodItem, HttpStatus.CREATED);
			}
				
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping(path = "/theater/fooditems/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateFoodItem(@PathVariable("id") String id, @RequestBody FoodItem updateFoodItem) throws ServerException  {
		
		FoodItemRepository foodItemRepository = new FoodItemRepository();
		FoodItemController foodItemController = new FoodItemController(foodItemRepository);

		if (updateFoodItem == null) {
			
			return ResponseEntity.badRequest()
		            .body("No FoodItem Defined to update");
		} else {
			
			if (foodItemController.GetFoodItem(Integer.parseInt(id)) == null) {
				return ResponseEntity.badRequest()
			            .body("FoodItem ID not present");
			}
			int updatedID = foodItemController.UpdateFoodItem(Integer.parseInt(id), updateFoodItem);
			if ( updatedID > 0) {
				ResponseEntity<String> entity = ResponseEntity.ok().body("FoodItem updated");
				return entity;
			}
				
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
	}
	
	
	@DeleteMapping("/theater/fooditems/{id}")
	public ResponseEntity<String> deleteFoodItem(@PathVariable("id") String id) {

		FoodItemRepository foodItemRepository = new FoodItemRepository();
		FoodItemController foodItemController = new FoodItemController(foodItemRepository);
		
		if (foodItemController.DeleteFoodItem(Integer.parseInt(id)) > 0) {
			ResponseEntity<String> entity = ResponseEntity.ok().body("FoodItem deleted");
			return entity;
		}
		return ResponseEntity.badRequest()
	            .body("FoodItem ID not present");

	}
	
}
