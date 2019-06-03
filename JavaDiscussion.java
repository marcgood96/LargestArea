import pair.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JavaDiscussion {

        //  int[][] grid = new int[5][5];
        public static void main(String[] args) {
            int[][] grid = new int[6][6];
            List<Pair> visited = new ArrayList<Pair>();

            //3 values 0 = empty block
            //         1 = wall
            //         2 = outside

            populateGrid(grid);


            // printGrid(grid);
            findRooms(grid, visited);//turns grid 0s to 3 if in a room surrounded by ones
            System.out.println(" ");

            printGrid(grid);
        }
        public static void populateGrid (int [][] grid){
//            for(int i = 0; i<grid.length; i++){
//                for(int j = 0; j<grid.length; j++){
//                    if(i == 0 || j == 0 || i == 5 || j == 5){
//                        grid[i][j] = 2;
//                    }
//                    else if( (i <5 && i>0) &&(j <4 && j>0)){
//                        grid[i][j] = 1;
//                    } else{
//                        grid[i][j] = 0;
//                    }
//                }
//            }
//            grid [2][2]=0;
//            grid [3][2]=0;

        }
        public static void findRooms(int[][]grid, List<Pair> visited){
            printGrid(grid);
            for(int i = 0; i<grid.length; i++){
                for(int j = 0; j<grid.length; j++){
                    if(grid[i][j] == 0){
                        if(checkOutside(grid, i, j, new ArrayList<Pair>() )){
                            grid[i][j] = 3;
                        }
                    }
                }
            }
        }
        public static boolean checkOutside(int[][] grid , int x , int y , List<Pair> visited){
            boolean isRoom = false;
            if(grid[x][y] == 1 || contains(visited , x,y)){
                return false;
            }else if(grid[x][y] == 2){
                return true;
            }else if(grid[x][y] == 0){
                visited.add(new Pair(x,y));
                return checkOutside(grid , x,y-1,visited) || checkOutside(grid , x+1,y,visited) || checkOutside(grid , x-1,y,visited) ||checkOutside(grid ,x,y+1,visited);
            }
            return false;
        }
        public static boolean isBlockOutside(int i ){
            if(i == 2){
                return true;
            }else{
                return false;
            }
        }

        public static void printGrid(int [][] grid){

            for(int i = 0; i<grid.length; i++){
                for(int j = 0; j<grid.length; j++){
                    System.out.print(grid[j][i]+ "  ");
                }
                System.out.println(" ");
            }
            System.out.println(" ");

        }
        public static boolean contains(List<Pair> visited ,int  x,int  y){
            for(int i = 0 ; i<visited.size();i++){
                if(visited.get(i).equals(new Pair (x,y))){
                    return true;
                }
            }
            return false;
        }



}
