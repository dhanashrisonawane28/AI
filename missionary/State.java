/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heisenberg
 */
public class State {

    int parentState;
    int MissionaryOnLeft;
    int MissionaryOnRight;
    int CannibalOnLeft;
    int CannibalOnRight;
    int boatCapacity;
    int side;
    

    public State(int MissionaryOnLeft, int CannibalOnLeft, int MissionaryOnRight, int CannibalOnRight,
            int boatCapacity, int side) {

        this.MissionaryOnLeft = MissionaryOnLeft;
        this.CannibalOnLeft = CannibalOnLeft;

        this.MissionaryOnRight = MissionaryOnRight;
        this.CannibalOnRight = CannibalOnRight;

        this.boatCapacity = boatCapacity;

        this.side = side;
    }

    public boolean isThisAValidState() {
        
        if (MissionaryOnLeft >= 0 && CannibalOnLeft >= 0 && MissionaryOnRight >= 0 && CannibalOnRight >= 0
                && (MissionaryOnLeft == 0 || MissionaryOnLeft >= CannibalOnLeft)
                && (MissionaryOnRight == 0 || MissionaryOnRight >= CannibalOnRight)) {
            return true;
        }
        
//        System.out.println(this);
        
        return false;
    }

    public boolean isItTheGoalState() {
        if (MissionaryOnLeft == 0 && CannibalOnLeft == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }

        State s = (State) obj;
        return (s.CannibalOnLeft == CannibalOnLeft && s.MissionaryOnLeft == MissionaryOnLeft
                && s.side == side && s.CannibalOnRight == CannibalOnRight
                && s.MissionaryOnRight == MissionaryOnRight);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.MissionaryOnLeft;
        hash = 61 * hash + this.MissionaryOnRight;
        hash = 61 * hash + this.CannibalOnLeft;
        hash = 61 * hash + this.CannibalOnRight;
        hash = 61 * hash + this.side;
        return hash;
    }

    

   

    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();
        generateSuccessors(successors);
//        printAll(successors);
        return successors;
    }

    public void generateSuccessors(List<State> successors) {
        if (side == Constants.LEFT) {
            for (int i = 0; i <= MissionaryOnLeft; i++) {
                for (int j = 0; j <= CannibalOnLeft; j++) {
                    // (i == 0 || i >= j) ---> if i is 0, no check is needed. otherwise, i >= j is a must
                    if ((i + j) != 0 && ((i + j) <= boatCapacity) && (i == 0 || i >= j)) {
                        State tem = new State(MissionaryOnLeft - i, CannibalOnLeft - j, MissionaryOnRight + i,
                                CannibalOnRight + j, boatCapacity, Constants.RIGHT);
                        if (tem.isThisAValidState()) {
                            successors.add(tem);
//                            System.out.println(tem);
                        }
                    }
                }
            }
        } else if (side == Constants.RIGHT) {
            for (int i = 0; i <= MissionaryOnRight; i++) {
                for (int j = 0; j <= CannibalOnRight; j++) {

                    if ((i + j) != 0 && ((i + j) <= boatCapacity)) {
                        State tem = new State(MissionaryOnLeft + i, CannibalOnLeft + j, MissionaryOnRight - i,
                                CannibalOnRight - j, boatCapacity, Constants.LEFT);

                        if (tem.isThisAValidState()) {
                            successors.add(tem);
//                            System.out.println(tem);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        if (side == Constants.LEFT) {
            return "(" + MissionaryOnLeft + "," + CannibalOnLeft + ",Left,"
                    + MissionaryOnRight + "," + CannibalOnRight + ")";
        } else {
            return "(" + MissionaryOnLeft + "," + CannibalOnLeft + ",Right,"
                    + MissionaryOnRight + "," + CannibalOnRight + ")";
        }
    }

    public void printAll(List<State> l) {
        for (State s : l) {
            System.out.println(s);
        }
    }

    public int getParentState() {
        return parentState;
    }

    public void setParentState(int parentState) {
        this.parentState = parentState;
    }

    public int getMissionaryOnLeft() {
        return MissionaryOnLeft;
    }

    public void setMissionaryOnLeft(int MissionaryOnLeft) {
        this.MissionaryOnLeft = MissionaryOnLeft;
    }

    public int getMissionaryOnRight() {
        return MissionaryOnRight;
    }

    public void setMissionaryOnRight(int MissionaryOnRight) {
        this.MissionaryOnRight = MissionaryOnRight;
    }

    public int getCannibalOnLeft() {
        return CannibalOnLeft;
    }

    public void setCannibalOnLeft(int CannibalOnLeft) {
        this.CannibalOnLeft = CannibalOnLeft;
    }

    public int getCannibalOnRight() {
        return CannibalOnRight;
    }

    public void setCannibalOnRight(int CannibalOnRight) {
        this.CannibalOnRight = CannibalOnRight;
    }

    public int getBoatCapacity() {
        return boatCapacity;
    }

    public void setBoatCapacity(int boatCapacity) {
        this.boatCapacity = boatCapacity;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }



}