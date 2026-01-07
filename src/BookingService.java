import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {
    //Booking Service

    private List<Train> trainList = new ArrayList<>();

    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService() {
        trainList.add(new Train(12702, "Hussainsagar Exp", "Mumbai", "Hyderabad", 50));
        trainList.add(new Train(17057, "Devagiri Exp", "Mumbai", "Secunderabad", 40));
        trainList.add(new Train(12026, "Shatabdi Exp", "Pune", "Secunderabad", 60));
        trainList.add(new Train(11020, "Konark Exp", "Mumbai", "Secunderabad", 45));
        trainList.add(new Train(12730, "Nanded-Hadapsar Exp", "Nanded", "Pune", 30));
        trainList.add(new Train(12701, "Vande Bharat", "Secunderabad", "Pune", 75));
        trainList.add(new Train(17614, "Panvel-Huzur Sahib Exp", "Panvel", "Nanded", 25));

    }

    public List<Train> searchTrain(String source, String destination) {
        List<Train> res = new ArrayList<>();
        for (Train train : trainList) {
            if (train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)) {
                res.add(train);
            }
        }
        return res;
    }

    private Ticket bookTicket(User user, int trainId, int seatCount){

        for(Train train: trainList){
            if(train.getTrainId()==trainId)
            {
                if(train.bookSeats(seatCount))
                {
                    Train ticket = new Ticket(user, train, seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }
                else {
                    System.out.println("No enogh seats available");
                    return null;
                }
            }
        }
        System.out.println("Train ID not found.");
        return null;
    }

    private List<Ticket> getTicketByUesr(User user)
    {
        List<Ticket> res = new ArrayList<>();
        for(Ticket ticket : ticketList){
            if(ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                res.add(ticket);
            }
        }
        return res;
    }

    private boolean cancleTicket(int ticketId, User user){
        Iterator<Ticket> iterator= ticketList.listIterator();
        while(iterator.hasNext()){
            Ticket ticket=iterator.next();
            if(ticket.getTicketId()==ticketId && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername()))
            {
                Train train=ticket.getTran();
                train.cancleSeats(ticket.getSeatBooked());
                iterator.remove();
                System.out.println("Ticket " + ticketId + " cancelled Successfully");
            }
        }
        System.out.println("Ticket Not Fount or does not belong to current user");
        return false;
    }

    public void listAllTrains(){
        System.out.println("List of all train: ");
        for(Train train : trainList){
            System.out.println(train);
        }
    }





}