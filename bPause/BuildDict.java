import java.io.*;
import java.util.*;

public class BuildDict {
    Set<String>set=new HashSet<String>();
    static int count=1;
    Block root;
    String max="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public void splitNonLeaf(Block curbloc) {

        Block rb = new Block();
        rb.nodes = 11;
        curbloc.nodes = 10;

        rb.parent = curbloc.parent;
        int t;
        for (int i = 10, j = 0; i<=20; i++, j++)
        {
            if(i<20)
                t=i+1;
            else t=i;

            rb.keys.set(j,curbloc.keys.get(i));
            rb.child.set(j,curbloc.child.get(t));
            curbloc.keys.set(i,max);
            curbloc.child.set(t,null);
        }

        for(int i=0;curbloc.child.get(i)!=null;i++)
            curbloc.child.get(i).parent=curbloc;
        for(int i=0;rb.child.get(i)!=null;i++)
            rb.child.get(i).parent=rb;

        if(curbloc.parent==null){
            Block pb= new Block();
            pb.nodes=1;
            pb.keys.set(0,rb.keys.get(0));
            pb.child.set(0,curbloc);
            pb.child.set(1,rb);

            curbloc.parent=rb.parent=pb;
            root=pb;
        }
        else {
            curbloc=curbloc.parent;

            curbloc.keys.add(rb.keys.get(0));
            //curbloc.child.add(rb);
            curbloc.nodes++;

            Collections.sort(curbloc.keys);
            /*Block tmp;
            int c=0;
            while (curbloc.child.get(c)!=null) c++;

            for(int i=0;i<c-1;i++){
                for(int j=i+1;j<c;j++){
                    if(curbloc.child.get(i).keys.get(0).compareTo(curbloc.child.get(j).keys.get(0))>0)
                    {
                      tmp=curbloc.child.get(i);
                      curbloc.child.set(i,curbloc.child.get(j));
                      curbloc.child.set(j,tmp);
                    }
                }
            }*/
            for(int i=0;i<curbloc.nodes;i++){
                if(curbloc.keys.get(i).compareTo(rb.keys.get(0))==0){
                    curbloc.child.add(i+1,rb);
                }
            }

            for(int i=0;curbloc.child.get(i)!=null;i++){
                curbloc.child.get(i).parent=curbloc;
            }
        }
    }

    public void splitLeaf(Block curbloc){

        Block rb= new Block();

        curbloc.nodes=10;
        rb.nodes=11;
        int t;
        for(int i=10,j=0; i<=20;i++,j++){
            if(i<20) t=i+1;
            else t=i;

            rb.keys.set(j,curbloc.keys.get(i));
            rb.child.set(j,curbloc.child.get(t));
            curbloc.keys.set(i,max);
            curbloc.child.set(t,null);
        }

        if(curbloc.parent==null){
            Block pb=new Block();
            pb.nodes=1;

            pb.keys.set(0,rb.keys.get(0));
            pb.child.set(0,curbloc);
            pb.child.set(1,rb);
            curbloc.parent=rb.parent=pb;
            root=pb;
        }
        else{
            curbloc=curbloc.parent;

            curbloc.keys.add(rb.keys.get(0));
            //curbloc.child.add(rb);
            Collections.sort(curbloc.keys);

            curbloc.nodes++;

            /*Block tmp;
            int c=0;
            while(curbloc.child.get(c)!=null) c++;

            for(int i=0;i<c-1;i++){
                for(int j=i+1;j<c;j++){
                    if(curbloc.child.get(i).keys.get(0).compareTo(curbloc.child.get(j).keys.get(0))>0)
                    {
                        tmp=curbloc.child.get(i);
                        curbloc.child.set(i,curbloc.child.get(j));
                        curbloc.child.set(j,tmp);
                    }
                }
            }*/
             for(int i=0;i<curbloc.nodes;i++){
                if(curbloc.keys.get(i).equals(rb.keys.get(0)))
                    curbloc.child.add(i+1,rb);
            }

            for(int i=0;curbloc.child.get(i)!=null;i++)
                curbloc.child.get(i).parent=curbloc;
        }
    }

    public void insertKey(Block curbloc, String word){

        for(int i=0;i<curbloc.deg-1;i++) {

            if (word.compareTo(curbloc.keys.get(i)) < 0 && curbloc.child.get(i) != null) {
                insertKey(curbloc.child.get(i), word);
                if (curbloc.nodes == curbloc.deg-1)
                    splitNonLeaf(curbloc);
                return;
            }
            else if (word.compareTo(curbloc.keys.get(i)) >= 0 && curbloc.child.get(i) != null) {
               if(i==curbloc.deg-2){
                   insertKey(curbloc.child.get(i+1),word);
               }
                else continue;
            }
            else if (curbloc.keys.get(i).compareTo(word) > 0 && curbloc.child.get(i) == null) {
                curbloc.keys.add(i, word);
                Collections.sort(curbloc.keys);
                curbloc.nodes++;
                break;
            }

            if (curbloc.nodes == curbloc.deg-1)
                splitLeaf(curbloc);
        }
    }

    public void print(Block muBloc) throws IOException {

        for(int i=0;i<muBloc.deg;i++) {
            if (muBloc.child.get(i) != null) {
                System.out.println("// "+ muBloc.keys.get(i) +" //");
                print(muBloc.child.get(i));
            }
            else {
                if(muBloc.keys.get(i)!=max)
                System.out.println(count++ +"  "+ muBloc.keys.get(i));
            }
        }
    }

    public void searchWord(Block nBloc, String word){

        for(int i=0;i<nBloc.nodes;i++){
            String[] wom=nBloc.keys.get(i).split(" ",2);
            if(word.equals(wom[0]) && nBloc.child.get(i)==null) {
                    set.add(wom[1]);
            }
            else if(word.compareTo(wom[0])<0 && nBloc.child.get(i)!=null){
                searchWord(nBloc.child.get(i),word);
            }
            else if(word.compareTo(wom[0])>=0 && nBloc.child.get(i+1)!=null) {
                searchWord(nBloc.child.get(i+1),word);
            }

        }
    }
    public void dictMain() throws IOException {
        root = new Block();
        File file = new File("C:\\Users\\Pranto\\IdeaProjects\\bPlus\\src\\eng_ban1.txt");
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Pranto\\IdeaProjects\\bPlus\\src\\eng_ban1.txt"));
        String tw = br.readLine();

        while (tw != null) {
            insertKey(root, tw);
            tw = br.readLine();
        }
        br.close();
        print(root);
        while (true) {
            System.out.println("Search a word for its meaning-> ");
            String s = sc.next();
            searchWord(root, s);

            for (String it : set) {
                System.out.println(it);
            }
            set.clear();
        }
    }
}

