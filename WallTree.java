import java.lang.reflect.Array;
import java.util.ArrayList;

public class WallTree {
    public Line rootValue;
    public ArrayList<WallTree> parents;
    public ArrayList<WallTree> children;
    public WallTree(WallTree parent, Line rootData) {
        this.parents =(ArrayList<WallTree>) parent.parents.clone();
        this.parents.add(parent);
        rootValue = rootData;
        children = new ArrayList<WallTree>();
    }
    public WallTree(WallTree parent, Line rootData, ArrayList<WallTree> children) {
        this.parents =(ArrayList<WallTree>) parent.parents.clone();
        this.parents.add(parent);
        rootValue = rootData;
        this.children = children;
    }
    public WallTree(Line rootData) {
        this.parents =  new ArrayList<WallTree>();
        rootValue = rootData;
        children = new ArrayList<WallTree>();
    }


    public void addChild(Line line) {
        this.children.add(new WallTree(line));
    }
    public void addChild(ArrayList<WallTree> lines) {
       this.children = lines;
    }
    public WallTree setChildren(ArrayList<WallTree> lines) {
        this.children = lines;
        return this;
    }
    public String toString(){
        return ""+this.rootValue +" , "+ this.children;
    }


}

