import java.util.List;
import java.util.Scanner;

public class IRCTCApp {

    private final Scanner scanner = new Scanner(System.in);

    private final UserService userService = new UserService();

    private final BookingService bookingService=new BookingService();

    public static void main(String[] args) {

        new IRCTCApp().start();

    }

    public void start(){
        while(true){
            System.out.println("------ Welcome to IRCTC App ------");
            if(!userService.isLoggedIn()) {
                System.out.print("1, Register: ");
                System.out.print("2, Login: ");
                System.out.print("3, Exit: ");
                System.out.print("Enter Choice ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exitApp();
                    default -> System.out.println("invalid Choice");
                }
            }
            else{
                showUserMenu();
            }
        }
    }

    public void register()
    {
        System.out.print("Enter username: ");
        String username=scanner.next();
        System.out.print("Enter Password: ");
        String password=scanner.next();
        System.out.print("Enter full Name: ");
        String fullName=scanner.next();
        System.out.print("Enter Contact: ");
        String contact=scanner.next();

        userService.registerUser(username,password,fullName,contact);
    }


    public void login(){
        System.out.print("Enter username: ");
        String username=scanner.next();
        System.out.print("Enter Password: ");
        String password=scanner.next();
        userService.loginUser(username, password);
    }

    public void showUserMenu(){
        while(userService.isLoggedIn()){
            System.out.println("----- User Menu -----");
            System.out.print("1, Search Trains: ");
            System.out.print("2, Book Ticket: ");
            System.out.print("3, View My Ticket: ");
            System.out.print("4. Cancle Tickets: ");
            System.out.print("5. View All Trains:  ");
            System.out.print("6. Logout: ");

            int choice = scanner.nextInt();
            switch(choice)
            {
                case 1 -> searchTrain();
                case 1 -> bookTicket();
                case 1 -> viewMyTicket();
                case 1 -> cancelTicket();
                case 1 -> bookingService.listAllTrains();
                case 1 -> userService.logOutUser();
                default -> System.out.println("Invalid Choice");

            }

        }
    }

    public void searchTrain()
    {
        System.out.println("Enter source station: ");
        String source=scanner.next();
        System.out.println("Enter distination station");
        String destination=scanner.next();

        List<Train> trains = bookingService.searchTrain(source, destination);
        if(trains.isEmpty()){
            System.out.println("No Trains Found between " +source+" and "+ destination);
            return;
        }
        System.out.println("Trains Found: ");
        for(Train train:trains){
            System.out.println(train);
        }

        System.out.println("Do you want to book ticket ? (yes/no)");
        String choice = scanner.next();
        if(choice.equalsIgnoreCase("yes")){
            System.out.println("Enter train ID to book: ");
            int trainId= scanner.nextInt();
            System.out.println("Enter number of seats to book: ");
            int seats= scanner.nextInt();

            Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(),trainId,seats);
            if(ticket!==null){
                System.out.println("Booking Successful");
                System.out.println(ticket);
            }
        }
            System.out.println("Returning to user menu...");
    }


    private void bookTicket(){

        System.out.println("Enter train ID to book: ");
        int trainId= scanner.nextInt();
        System.out.println("Enter number of seats to book: ");
        int seats= scanner.nextInt();
        List<Train> trains = bookingService.searchTrain(source, destination);
        if(trains.isEmpty()){
            System.out.println("No Trains available for booking");
            return;
        }
        System.out.println("Available Trains: ");
        for(Train train:trains){
            System.out.println(train);
        }

        System.out.println("Enter train ID to book: ");
        int trainId= scanner.nextInt();
        System.out.println("Enter number of seats to book: ");
        int seats= scanner.nextInt();
        Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(),trainId,seats);
        if(ticket!==null){
            System.out.println("Booking Successful");
            System.out.println(ticket);
        }

    }

    private void viewMyTicket(){
        List<Ticket> ticketByUser = bookingService.getTicketByUser(userService.getCurrentUser());
        if(ticketByUser.isEmpty()){
            System.out.println("No ticket booking yet");
        }
        else{
            System.out.println("Your Ticket:");
            for(Ticket ticket : ticketByUser){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket(){
        System.out.println("Enter Ticket ID to cancle: ");
        int tickeID = scanner.nextInt();
        bookingService.cancelTicket(ticketID, userSrevice.getCurrentUer());
    }





    private void exitApp(){
        System.out.println("Thank you for using IRCTC App.");
        System.exit(0);
    }




}
