public class Ticket {

    private int ticketId;

    private User user;

    private Train train;

    private int seatBooked;

    private static int counter = 1001;

    public Ticket(int ticketId, User user, Train train, int seatBooked) {
        this.ticketId = counter++;
        this.user = user;
        this.train = train;
        this.seatBooked = seatBooked;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTran() {
        return train;
    }

    public void setTran(Train train) {
        this.train = train;
    }

    public int getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(int seatBooked) {
        this.seatBooked = seatBooked;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId+
                " | Train" + train.getName()+
                " | Route: " +train.getSource()+
                " -> "+ train.getDestination()
                +" | Seats: " + seatBooked+
                " | Booked By: "+user.getFullName();
    }
}
