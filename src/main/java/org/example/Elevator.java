package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;

public class Elevator {

    private int freePlaces = 4;
    private int id;
    private Directions direction;

    private int currFloor;

    Comparator<Request> descendingComparator = new Comparator<Request>() {
        @Override
        public int compare(Request o1, Request o2) {
            return Integer.compare(o2.getPriority(), o1.getPriority());
        }
    };

    PriorityQueue<Request> taskQueue = new PriorityQueue<>(descendingComparator);
    public Elevator(int freePlaces, Directions direction, int currFloor, int id){
        this.freePlaces = freePlaces;
        this.direction = direction;
        this.currFloor = currFloor;
        this.id = id;
    }

    public Elevator(Directions direction, int currFloor, int id){
        this(4, direction, currFloor, id);
    }

    public Directions getDirection() {
        return direction;
    }

    public void addToTaskQueue(Request req){
        if (this.freePlaces - 1 >= 0){
            this.freePlaces -= 1;
            taskQueue.add(req);
        }

    }


    public void move(){
        int curr = this.currFloor;
        if (this.taskQueue.peek() == null) return;
        int dest = this.taskQueue.peek().getPriority();


        if (dest - curr > 0){
            this.direction = Directions.UP;
        }
        else if (dest - curr < 0){
            this.direction = Directions.DOWN;
        }else{
            this.direction = Directions.STAY;
        }


        while (this.taskQueue.peek() != null && this.taskQueue.peek().getPriority() == curr){
            Request req = taskQueue.remove();
            if (!req.getTaken()){
                req.setTaken(true);
                taskQueue.add(req);
            }else{
                this.freePlaces += 1;
            }

        }

        this.currFloor += this.direction.directionToInt();
    }

    public int getID(){
        return this.id;
    }

    public int getCurrFloor() {
        return currFloor;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

}
