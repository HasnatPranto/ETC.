import java.util.ArrayList;
import java.util.List;

public class Block {
    String max="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";;
    int deg=21,nodes;
    ArrayList<String> keys= new ArrayList<String>(21);
    Block parent;
    ArrayList <Block> child=new ArrayList<Block>(21);

    public Block(){
        nodes=0;
        parent=null;
        for(int i=0;i<deg;i++){
            child.add(i,null);
            if(i<21)
            keys.add(i, max);
        }
    }
}
