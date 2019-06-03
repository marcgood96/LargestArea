public class Line {
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;

    Line(int xs , int ys, int xe, int ye ){
        this.xStart = xs;
        this.yStart = ys;
        this.xEnd = xe;
        this.yEnd = ye;

    }
    public  String toString(){
        return "("+this.xStart +" , " +this.yStart + " ) , ("+this.xEnd +" , " +this.yEnd + " )\n";
    }


    public boolean equals(Line obj) {
        System.out.println(obj.xStart +", " +this.xStart +", " + obj.yStart  +", " + this.yStart  +", " + obj.xEnd  +", " + this.xEnd  +", " + obj.yEnd  +", " + this.yEnd);
        System.out.println(obj.xStart == this.xStart && obj.yStart == this.yStart && obj.xEnd == this.xEnd && obj.yEnd == this.yEnd);
        return obj.xStart == this.xStart && obj.yStart == this.yStart && obj.xEnd == this.xEnd && obj.yEnd == this.yEnd;
    }
}
