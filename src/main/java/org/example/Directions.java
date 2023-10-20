package org.example;

public enum Directions {
    DOWN,
    UP,
    STAY;

    public Directions opposite(){
        return switch (this){
            case UP -> DOWN;
            case DOWN -> UP;
            case STAY -> STAY;
        };

    }

    public int directionToInt(){
        return switch (this){
            case UP -> 1;
            case DOWN -> -1;
            case STAY -> 0;
        };
    }
}


