package Theater.Ticket;

import java.util.List;

import Theater.Ticket.Ticket;
import Theater.Ticket.TicketRepository;

public class TicketController implements ITicketService {

	TicketRepository ticketRep;
	
	
	public TicketController() {
		super();
	}

	
	public TicketController(TicketRepository ticketRepository) {
		// TODO Auto-generated constructor stub
		
		super();
		this.ticketRep = ticketRepository;
	}
	

	@Override
	public List<Ticket> GetAllSold() {
		// TODO Auto-generated method stub
		return ticketRep.getTickets();
		
		
	}
	
	public Ticket GetTicket(int id) {
		// TODO Auto-generated method stub
		return ticketRep.getTicket(id);
		
		
	}
	
	public int  InsertTicket(Ticket newTicket) {
		// TODO Auto-generated method stub
		return ticketRep.insertTicket(newTicket);
		
		
	}
	
	public int  UpdateTicket(int id, Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRep.updateTicket(id, ticket);
		
		
	}

	public int  DeleteTicket(int id) {
		// TODO Auto-generated method stub
		return ticketRep.deleteTicket(id);
		
		
	}
}
