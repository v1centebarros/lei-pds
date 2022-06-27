package lab03.VOOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class Main {

    static Map<String, Flight> codesMap = new HashMap<String, Flight>();

    public static void main(String[] args) {
        System.out.println(help());
        Scanner sc = new Scanner(System.in);
        boolean fl = true;
        while (fl) {
           
            System.out.println("Pick an option: (H for help)");
            String[] option = sc.nextLine().split(" ");
            switch (option[0].toLowerCase()) {
            case "i":
                String filename = option[1];
                Flight flight = optionI(filename);
                System.out.println(flight);
                break;
            case "m":
                String codeM = option[1];
                optionM(codeM);
                break;
            case "f":
                String codeF = option[1];
                String num_seats_E = option[2];
                String num_seats_T = option[3];
                optionF(codeF, num_seats_E, num_seats_T);
                break;
            case "r":
                String codeR = option[1];
                TypeReservations classe;
                if (Arrays.asList(option[2]).contains("T")) {
                    classe = TypeReservations.TURISTICA;
                } else if (Arrays.asList(option[2]).contains("E")) {
                    classe = TypeReservations.EXECUTIVA;
                } else {
                    System.err.println("ERROR: The class in the input must be T or E");
                    break;
                }
                int number_seats = Integer.parseInt(option[3]);
                optionR(codeR, classe, number_seats);
                break;
            case "c":
                String reservation_code = option[1];
                optionC(reservation_code);
                break;
            case "h":
                System.out.println(help());
                break;
            case "q":
                System.out.println("bye ...");
                fl = false;
                break;
            default:
                System.err.println("ERROR: Option is not supported");
                break;
            }
        }
        for (String key : codesMap.keySet()) {
            System.out.printf("%s\n", codesMap.get(key));
        }
        sc.close();
    }

   
    private static Flight optionI(String filename){
        try{
            Scanner myReader = new Scanner(new File("./lab03/VOOS/" + filename));
            String[] input = myReader.nextLine().split(" ");
            String flightCode = input[0];
            flightCode = flightCode.replaceAll(">", "");

            // check the input
            if(input.length>3 || input.length<2){
                System.err.println("ERROR: Invalid type of input file. ");
                return null;   
            }
            Plane plane;
            if (input.length == 3) {
                plane = new Plane(input[2], input[1]);
                plane.setConfAviao(TypeReservations.TURISTICA_EXECUTIVA);
            } else {
                plane = new Plane(input[1], "");
                plane.setConfAviao(TypeReservations.TURISTICA);
            }
            
            Flight flight = new Flight(flightCode, plane);
            codesMap.put(flightCode, flight);
            


            String[] ocupiedSeats;       // array with the ocupied seats
            while(myReader.hasNextLine()){
                ocupiedSeats = myReader.nextLine().split(" ");
                if(ocupiedSeats[0].toCharArray()[0] == 'T'){           //check if the type of reservation is TURISTIC
                    Reservation reservation = new Reservation(TypeReservations.TURISTICA, Integer.valueOf(ocupiedSeats[1]));
                    flight.addReservation(reservation);
                } else if (ocupiedSeats[0].toCharArray()[0] == 'E') { //check if the type of reservation is EXECUTIVE
                    if (plane.getClassAviao() == TypeReservations.TURISTICA) {
                        break;
                    }
                    Reservation reservation = new Reservation(TypeReservations.EXECUTIVA, Integer.valueOf(ocupiedSeats[1]));
                    flight.addReservation(reservation);
                }  
            }
            return flight;
        }

        catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found.");
            return null; 
        }
   }

    public static void optionM(String flight_code) {
        
        System.out.println("Incompleto, a logica funciona os prints nao, motivo falta de tempo");
    }

    
    public static void optionF(String flight_code, String num_seatsE, String num_seatsT) {
        
        Plane plane = new Plane(num_seatsT, num_seatsE); //create a new objetc plane
        Flight flight = new Flight(flight_code, plane); //create a new object flighr
        codesMap.put(flight_code, flight); //insert the code flight in the map
       
        System.out.println("The flight was inserted with success\n");
        System.out.printf("%s\n", codesMap.get(flight_code));
        
    }

    
    public static void optionR(String flight_code, TypeReservations classe, int number_seats) {

        for (String code : codesMap.keySet()) { // go through the map codes 
            if (code.equals(flight_code)) { // match the code with the map one 
                Flight flight = codesMap.get(code); 
                Reservation reservation = new Reservation(classe, number_seats); //create a reservation
                flight.addReservation(reservation); //add the reservation to the flight
                int reservationNumber = flight.getAviao().getReservationNumber() - 1; //create the reservation number
                String reservation_code = flight_code + ":" + reservationNumber; //create the reservation code
                System.out.println(reservation_code);
            }
        }
    }

    
    public static void optionC(String reservation_code) {
        String[] array = reservation_code.split(":");  //split the code and store it in a array
        for (String code : codesMap.keySet()) { //go trhow all the existing codes
            if (array[0].equals(code)) { //match the code you want to delete with the ones stored in the map
                Flight flight = codesMap.get(code); //match the flight with the code flight
                flight.rmvReservation(array); //remove reservations 
                codesMap.put(code, flight);

            }
        }
    }

    
    public static String help() {
        return "H : Help\n" + "I <filename> -  Read Flight Information\n"
                + "M <flight_code> - Consult Reservation Map\n"
                + "F <flight_code> <num_seats_executive> <num_seats_tourist> - Add a new flight\n"
                + "R <flight_code> <class> <number_seats> - Add a new revervation to a flight\n"
                + "C <reservation_code> - Cancel a revervation\n" + "Q - end Program";
        
    }
}
