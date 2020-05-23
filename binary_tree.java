import java.util.Scanner;

class binary_tree{

    private class Node{
        int data;
        Node left;
        Node right;
    }

    private Node root;
    private int size;

    public binary_tree(){
        this.root= this.Constructor(new Scanner(System.in),null,false);
    }

    // expectation -> create left or right of parent .
    private Node Constructor(Scanner scn,Node parent,boolean is_left_child){
        if(parent==null){
            System.out.println("Enter data for root");
        }else{
        if(is_left_child)
            System.out.println("Enter the data for the left child "+parent.data);
        else
            System.out.println("Enter the data for the right child "+parent.data);
        }
        int cdata = scn.nextInt();

        Node child = new Node();
        child.data = cdata;
        this.size++;

        System.out.println("Do you have a left child for "+child.data);
        boolean have_left_child = scn.nextBoolean();
        if(have_left_child)
            child.left=Constructor(scn,child,true); // faith

        System.out.println("Do you have a right child for "+ child.data);
        boolean have_right_child = scn.nextBoolean();
        if(have_right_child)
            child.left=Constructor(scn,child,true); // faith

        return child;
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size()==0;
    }

    public static void input_tree(){
        binary_tree tree = new binary_tree();
        System.out.println(tree.size());
    }

    private void display(Node node){
        if(node == null)
            return ;

        String str ="";
        str+= ((node.left != null)? node.left.data : ".");
        str+= "->"+node.data+"<-";
        str+= ((node.right != null)? node.right.data : ".");

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public void display(){
        display(root);
    }

    public static void main(String[] args){
        binary_tree tree = new binary_tree();
        System.out.println(tree.size());
        tree.display();    
    }
}