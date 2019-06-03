package pair;

public class Pair {
    public int x ;
    public int y ;
    public Pair(int x , int y){
        this.x = x ;
        this.y = y;
    }
    public boolean equals(Pair p){
        if(p.x == this.x && p.y == this.y ){
            return true;
        }else {
            return false;
        }
    }

}

