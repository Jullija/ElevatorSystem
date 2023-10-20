package org.example;

import java.util.List;

public class Request {

    private int myFloor;
    private int dest;

    private boolean taken;

    public Request(int myFloor, int dest){
        this.myFloor = myFloor;
        this.dest = dest;
    }

    public int getPriority(){
        if (taken){
            return this.dest;
        }
        return this.myFloor;
    }

    public int getMyFloor(){
        return this.myFloor;
    }

    public int getDest(){
        return this.dest;
    }

    public boolean getTaken(){
        return this.taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
