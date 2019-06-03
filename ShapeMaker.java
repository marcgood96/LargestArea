import pair.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShapeMaker {
    public static void main(String[] args) {
       // System.out.print("Hello world");
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(new Line(4,10,4 ,4));
        lines.add(new Line(10, 10, 7,10));
        lines.add(new Line(7,10, 4, 10));
        lines.add(new Line(4 ,4, 7,4));
        lines.add(new Line(7,4, 10, 4));
        lines.add(new Line(10,4, 10, 10));
        lines.add(new Line(7,10, 7, 4));

       //  = new LinkedList<Line>();
        //First Link Them
        LinkedList<Line> linkedLines = linkLines(lines);
      //   System.out.print(linkedLines);
        double area = getArea(linkedLines);
        System.out.println(area);
        //Do algorithm for area
        WallTree wallTree = createTree(lines);

        System.out.println(wallTree);
        ArrayList<WallTree> leaves = getLeaves(wallTree, new ArrayList<WallTree>());
        leaves.removeIf(l ->(!connected(l.rootValue, lines.get(0))));
        ArrayList<ArrayList<Pair>> lines2 = leavesToLines(leaves);

        System.out.println(getLargestArea(lines2));
    }

    private static ArrayList<ArrayList<Pair>> leavesToLines(ArrayList<WallTree> leaves) {
        ArrayList<ArrayList<Pair>> lines = new ArrayList<>();
        for(WallTree leaf:leaves){
            lines.add(leafToLines(leaf));
        }
        return lines;
    }

    private static ArrayList<Pair> leafToLines(WallTree leaf) {
        ArrayList<Pair> lines = new ArrayList<>();

        for(int i = 0 ; i<leaf.parents.size()-1;i++){
            if(leaf.parents.get(i).rootValue.xStart == leaf.parents.get(i+1).rootValue.xStart&&
                    leaf.parents.get(i).rootValue.yStart == leaf.parents.get(i+1).rootValue.yStart){
                    lines.add(new Pair(leaf.parents.get(i).rootValue.xStart,leaf.parents.get(i).rootValue.yStart));
            }else if(leaf.parents.get(i).rootValue.xEnd == leaf.parents.get(i+1).rootValue.xEnd&&
                    leaf.parents.get(i).rootValue.yEnd == leaf.parents.get(i+1).rootValue.yEnd) {

                lines.add(new Pair(leaf.parents.get(i).rootValue.xEnd,leaf.parents.get(i).rootValue.yEnd));

            }else if(leaf.parents.get(i).rootValue.xEnd == leaf.parents.get(i+1).rootValue.xStart&&
                    leaf.parents.get(i).rootValue.yEnd == leaf.parents.get(i+1).rootValue.yStart) {
                lines.add(new Pair(leaf.parents.get(i).rootValue.xEnd,leaf.parents.get(i).rootValue.yEnd));
            }
            else if(leaf.parents.get(i).rootValue.xStart == leaf.parents.get(i+1).rootValue.xEnd&&
                    leaf.parents.get(i).rootValue.yStart == leaf.parents.get(i+1).rootValue.yEnd) {
                lines.add(new Pair(leaf.parents.get(i).rootValue.xStart,leaf.parents.get(i).rootValue.yStart));
            }
        }

        if(leaf.parents.get(leaf.parents.size()-1).rootValue.xStart == leaf.rootValue.xStart&&
                leaf.parents.get(leaf.parents.size()-1).rootValue.yStart == leaf.rootValue.yStart){
            lines.add(new Pair(leaf.parents.get(leaf.parents.size()-1).rootValue.xStart,leaf.parents.get(leaf.parents.size()-1).rootValue.yStart));
            lines.add(new Pair(leaf.rootValue.xEnd,leaf.rootValue.yEnd));

        }else if(leaf.parents.get(leaf.parents.size()-1).rootValue.xEnd == leaf.rootValue.xEnd&&
                leaf.parents.get(leaf.parents.size()-1).rootValue.yEnd == leaf.rootValue.yEnd) {

            lines.add(new Pair(leaf.parents.get(leaf.parents.size()-1).rootValue.xEnd,leaf.parents.get(leaf.parents.size()-1).rootValue.yEnd));
            lines.add(new Pair(leaf.rootValue.xStart,leaf.rootValue.yStart));
        }else if(leaf.parents.get(leaf.parents.size()-1).rootValue.xEnd == leaf.rootValue.xStart&&
                leaf.parents.get(leaf.parents.size()-1).rootValue.yEnd == leaf.rootValue.yStart) {
            lines.add(new Pair(leaf.parents.get(leaf.parents.size()-1).rootValue.xEnd,leaf.parents.get(leaf.parents.size()-1).rootValue.yEnd));
            lines.add(new Pair(leaf.rootValue.xEnd,leaf.rootValue.yEnd));
        }
        else if(leaf.parents.get(leaf.parents.size()-1).rootValue.xStart == leaf.rootValue.xEnd&&
                leaf.parents.get(leaf.parents.size()-1).rootValue.yStart == leaf.rootValue.yEnd) {
            lines.add(new Pair(leaf.parents.get(leaf.parents.size()-1).rootValue.xStart,leaf.parents.get(leaf.parents.size()-1).rootValue.yStart));
            lines.add(new Pair(leaf.rootValue.xStart,leaf.rootValue.yStart));
        }




        return lines;

    }

    public static double getLargestArea(ArrayList<ArrayList<Pair>> leaves){
        double largestArea = 0;
        for(ArrayList<Pair> leaf: leaves){
            double area = getArea(leaf);
            if(area >largestArea){
                largestArea = area;
            }
        }
        return largestArea;
    }

    public static ArrayList<WallTree> getLeaves(WallTree wallTree, ArrayList<WallTree> leaves ){
        if(wallTree.children.size() != 0){

            for(WallTree child: wallTree.children){
                getLeaves(child, leaves);
            }

        }else{
            leaves.add(wallTree);
        }

        return leaves;
    }

    public static WallTree createTree(ArrayList<Line> lines){
        WallTree wallTree = new WallTree(lines.get(0));


            wallTree.addChild(getLines(wallTree, lines.get(0), lines));

        return wallTree;
    }
    public static ArrayList<WallTree> getLines(WallTree parent, Line line, ArrayList<Line> lines){
        ArrayList<WallTree> links = new ArrayList<WallTree>();
        for(Line l : lines){
            if(isChild(line , l , parent)){

                    WallTree wallTree = new WallTree(parent , l);
                    links.add(wallTree.setChildren( getLines(wallTree, l, lines)));

            }
        }
        if(links.size() == 0){

        }

        return links;
    }

    private static boolean isChild(Line line, Line l, WallTree parent) {
        if((l.xStart == line.xEnd && l.yStart == line.yEnd) ||
          ((l.xStart == line.xStart && l.yStart == line.yStart)&&
                l.xEnd != line.xEnd && l.yEnd != line.yEnd) ||
          ((l.xEnd == line.xEnd && l.yEnd == line.yEnd)&&
                l.xStart != line.xStart && l.yStart != line.yStart)) {
                        if(!parent.rootValue.equals(l)){

                            for(WallTree p : parent.parents){
                                if(p.rootValue.equals(l)){

                                    return false;
                                }
                            }
                            return true;

                        }
                }
        System.out.println("NOT MATCHING");
        return false;
    }
    public static boolean connected(Line line1, Line line2){
        if((line1.xStart == line2.xEnd && line1.yStart == line2.yEnd) ||
                (line2.xStart == line1.xEnd && line2.yStart == line1.yEnd) ||
                ((line1.xStart == line2.xStart && line1.yStart == line2.yStart)&&
                        line1.xEnd != line2.xEnd && line1.yEnd != line2.yEnd) ||
                ((line1.xEnd == line2.xEnd && line1.yEnd == line2.yEnd)&&
                        line1.xStart != line2.xStart && line1.yStart != line2.yStart)) {
            return true;

        }
        return false;
    }

    public static  double getArea(LinkedList<Line> linkedLines){
        double area = 0;
        for(int i = 0 ; i<linkedLines.size()-1; i++){
            Line line =  linkedLines.get(i);
           area = area + ((line.xStart * line.yEnd)-(line.yStart * line.xEnd));

        }
        area = area/2;
        return area;
    }
    public static  double getArea(ArrayList<Pair> leaf){
        double area = 0;//(	 x	1 	 y	2 	−	 y	1 	 x	2 	) 	 	+	 	 (	 x	2 	 y	3 	−	 y	2 	 x	3 	)
       for(int i =0 ; i<leaf.size()-1 ;i++){
           area += leaf.get(i).x * leaf.get(i+1).y - leaf.get(i).y * leaf.get(i+1).x;
       }
        area += leaf.get(leaf.size()-1).x * leaf.get(0).y - leaf.get(leaf.size()-1).y * leaf.get(0).x;
        area = area/2;
        return area;
    }

    //First Link Them
    public static LinkedList<Line> linkLines(ArrayList<Line> lines){
        LinkedList<Line> linkedLines = new LinkedList<Line>();

        linkedLines.add(lines.get(0));
        for(int i = 0; i<lines.size(); i++){
            linkedLines.add(getLine(linkedLines.getLast(), lines));
        }

        return linkedLines;
    }
    public static Line getLine(Line line, ArrayList<Line> lines){
        for(Line l : lines){
            if(l.xStart == line.xEnd && l.yStart == line.yEnd){

                return l;
            }
        }

        return line;
    }
    //Do algorithm for area
}
