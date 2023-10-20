package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElevatorSystem {

    private int floors;
    private int elevatorsNum;
    private List<List<Elevator>> elevators2D = new ArrayList<>();
    private List<Elevator> elevators = new ArrayList<>();

    private List<Request> requests = new ArrayList<>();


    public ElevatorSystem(int floors, int elevatorsNum){
        this.floors = floors;
        this.elevatorsNum = elevatorsNum;
        for (int i = 0; i< floors; i++){
            elevators2D.add(0, new ArrayList<>());
        }
        for (int i = 0; i < elevatorsNum; i++){
            Elevator elevator = new Elevator(Directions.STAY, 0, i);
            elevators2D.get(0).add(elevator);
            elevators.add(elevator);
        }
    }

    public void pickup(Request request){
        Directions dir = Directions.STAY;

        int delta = request.getDest() - request.getMyFloor();

       if (delta > 0){
           dir = Directions.UP;
       }
       else if (delta < 0){
           dir = Directions.DOWN;
       }

       Elevator elevator = this.findElevator(request.getMyFloor(), dir);
       if (elevator == null){
           requests.add(request);
       }else{
           elevator.addToTaskQueue(request);
       }

    }

    private Elevator findingElevator(Directions notWhatIAmLookingFor, int comp){
        if (!elevators2D.get(comp).isEmpty()){
            for (Elevator elevator : elevators2D.get(comp)){
                if (!elevator.getDirection().equals(notWhatIAmLookingFor)){
                    return elevator;
                }
            }
        }
        return null;
    }
    private Elevator findElevator(int currFloor, Directions direction){
        Directions notWhatIAmLookingFor = direction.opposite();
        if (elevators2D.get(currFloor).isEmpty()){
            for (int i = 1; i < elevators2D.size(); i++){
                if (currFloor + i < floors && this.findingElevator(notWhatIAmLookingFor, currFloor + i) != null) {
                    return this.findingElevator(notWhatIAmLookingFor, currFloor + i);
                };

                if (currFloor - i >= 0 && this.findingElevator(notWhatIAmLookingFor, currFloor - i) != null ) {
                    return this.findingElevator(notWhatIAmLookingFor, currFloor - i);
                };


            }
        }else{
            return elevators2D.get(currFloor).get(0);
        }
        return null;
    }


    private void step(){
        for (Elevator elevator : elevators){
            elevator.move();
        }
    }

    private Request createRequests(){
        Random random = new Random();
        int cf = random.nextInt(floors);
        int df = random.nextInt(floors);

        while (cf == df){
            df = random.nextInt(floors);
        }

        return new Request(cf, df);

    }


    public void printStatus(){
        for (Elevator elevator : elevators){
            System.out.println("Status windy o id " + elevator.getID() + ": piętro -> " + elevator.getCurrFloor() + ", kierunek -> " + elevator.getDirection() + ", wolne miejsca -> " + elevator.getFreePlaces() );
        }
    }

    synchronized public void simulation() throws InterruptedException {
        while (true){
            this.printStatus();
            Request req = this.createRequests();
            this.pickup(req);
            List<Request> requests2 = new ArrayList<>();
            requests2.addAll(requests);
            requests.clear();
            for (Request request : requests2){
                this.pickup(request);
            }
            this.step();
            wait(1000);

        }
    }




}
