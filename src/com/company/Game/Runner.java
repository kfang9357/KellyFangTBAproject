package com.company.Game;

import com.company.Areas.BearsDen;
import com.company.Characters.Person;
import com.company.Areas.Civilization;
import com.company.Areas.River;
import com.company.Areas.TreeArea;
import com.company.Characters.Person;

import java.util.Scanner;

    public class Runner {


        private static boolean gameOn = true;

        public static void main(String[] args)
        {
            Area[][] forest = new Area[5][5];

            //Fill the building with normal rooms
            for (int x = 0; x<forest.length; x++)
            {
                for (int y = 0; y < forest[x].length; y++)
                {
                    forest[x][y] = new Area(x,y);
                }
            }


            //Create a random winning room.
            int x = (int)(Math.random()*forest.length);
            int y = (int)(Math.random()*forest.length);
            forest[x][y] = new River(x,y);

            int w = (int)(Math.random()*forest.length);
            int z = (int)(Math.random()*forest.length);
            forest[w][z] = new BearsDen(w,z);

            //Setup player 1 and the input scanner
            Person player1 = new Person("FirstName", "FamilyName", 0,0);
            forest[0][0].enterRoom(player1);
            Scanner in = new Scanner(System.in);
            while(gameOn)
            {
                System.out.println("Where would you like to move? (Choose N, S, E, W)");
                String move = in.nextLine();
                if(validMove(move, player1, forest))
                {
                    System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());

                }
                else {
                    System.out.println("Please choose a valid move.");
                }


            }
            in.close();
        }

        /**
         * Checks that the movement chosen is within the valid game map.
         * @param move the move chosen
         * @param p person moving
         * @param map the 2D array of rooms
         * @return
         */
        public static boolean validMove(String move, Person p, Room[][] map)
        {
            move = move.toLowerCase().trim();
            switch (move) {
                case "n":
                    if (p.getxLoc() > 0)
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                case "e":
                    if (p.getyLoc()< map[p.getyLoc()].length -1)
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                case "s":
                    if (p.getxLoc() < map.length - 1)
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                case "w":
                    if (p.getyLoc() > 0)
                    {
                        map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
                        map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                default:
                    break;

            }
            return true;
        }
        public static void gameOff()
        {
            gameOn = false;
        }



    }