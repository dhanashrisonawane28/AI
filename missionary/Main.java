/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author Heisenberg
 */
public class Main {

    private static int initialMissionaryOnLeft;
    private static int initialCannibalOnLeft;
    private static int boatCapacity;
    private static int choinceNo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter initial number of Missionaries on the left bank: ");
            initialMissionaryOnLeft = sc.nextInt();
            System.out.println("Enter initial number of Cannibals on the left bank: ");
            initialCannibalOnLeft = sc.nextInt();
            System.out.println("Enter the capcity of the boat: ");
            boatCapacity = sc.nextInt();

            System.out.println("Which algorithm do you want to use?: ");
            System.out.println("1. BFS\n2. DFS");
            System.out.println("Enter your choice: ");

            choinceNo = sc.nextInt();

            State initialState = new State(initialMissionaryOnLeft, initialCannibalOnLeft, 0, 0, boatCapacity, Constants.LEFT);
            initialState.setParentState(-1);

            if (choinceNo == 1) {
                missionaryBFS bfs = new missionaryBFS(initialState);
                bfs.printPath();
            } else if (choinceNo == 2) {
                DFS dfs = new DFS(initialState);
                dfs.printPath();
            } else {
                System.out.println("You entered an invalid choice. Try again.");
            }
        }
    }
}