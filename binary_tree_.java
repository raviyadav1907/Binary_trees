import java.util.ArrayList;
import java.util.Stack;

class binary_tree_{

    public static class Node{
        int data;
        Node left = null;   // node* left = null;
        Node right = null;  // node* right = null;

        Node(int data){
            this.data=data;
            this.left=this.right=null;
        }

        Node(){

        }
    };

    static int idx = 0;

    public static Node construct_Tree(int[] arr){
        if(idx==arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        Node node = new Node(arr[idx++]);
        node.left=construct_Tree(arr);
        node.right=construct_Tree(arr);
        
        return node;
    }

    // Binary_tree basics =======================================

    public static void display_tree(Node root){
        if(root==null){
            return;
        }
        String str="";
        str+=((root.left!=null)?root.left.data:".");
        str+= "<-"+root.data+"->";
        str+=((root.right!=null)?root.right.data:".");
        System.out.println(str);
        display_tree(root.left);
        display_tree(root.right);
    }

    public static int size(Node node){
        if(node == null)return 0;
        int left_tree_size = size(node.left);
        int right_tree_size = size(node.right);
        return Math.max(left_tree_size,right_tree_size)+1;
    }

    public static int height(Node node){
        if(node==null)return -1;
        return Math.max(height(node.left),height(node.right))+1;
    }

    public static int max(Node node){
        if(node==null)
            return (int)-1e8;
        int mymax = node.data;
        if(node.left!=null)
            mymax=Math.max(mymax,max(node.left));
        if(node.right!=null)
            mymax=Math.max(mymax,max(node.right));
        return mymax;
    }

    public static int min(Node node){
        if(node==null)
            return (int)1e8;
        int mymin=node.data;
        if(node.left!=null)
            mymin=Math.min(mymin,min(node.left));
        if(node.right!=null)
            mymin=Math.min(mymin,min(node.right));
        return mymin;
    }

    public static boolean find(Node node,int data){
        if(node==null)return false;
        if(node.data==data)return true;
        return find(node.left,data)||find(node.right,data);
    }

    public static void basic(Node root){
        // display_tree(root);
        // size(root);
        // height(root);
        // max(root);
        // min(root);
        // find(root,10);
    }

    // Binarytree_traversal ======================================

    public static void preOrder(Node node){
        if(node==null)        
            return;
        System.out.print(node.data+", ");
        if(node.left!=null)
            preOrder(node.left);
        if(node.right!=null)
            preOrder(node.right);
    }

    public static void inOrder(Node node){
        if(node==null)
            return;
        if(node.left!=null)
            inOrder(node.left);
        System.out.print(node.data+", ");
        if(node.right!=null)
            inOrder(node.right);
    }

    public static void postOrder(Node node){
        if(node==null)
            return;
        if(node.left!=null)
            postOrder(node.left);
        if(node.right!=null)
            postOrder(node.right);
        System.out.print(node.data+", ");
    }

    public static void preOrder_iterative(Node node){
        ArrayList<Node> curr_list = new ArrayList<>();
        curr_list.add(node);
        while(curr_list.size()>0){
            Node curr_node = curr_list.remove(curr_list.size()-1);
            System.out.print(curr_node.data+" -> ");
            if(curr_node.right!=null)
                curr_list.add(curr_node.right);
            if(curr_node.left!=null)
                curr_list.add(curr_node.left);
        }
    }

    public static void InOrder_iterative(Node node){
        if(node==null)
            return;
        Stack<Node> curr_list = new Stack<>();
        Node curr_node = node;
        while(curr_node!=null || curr_list.size()>0){
            while(curr_node!=null){
                curr_list.push(curr_node);
                curr_node=curr_node.left;
            }
            curr_node=curr_list.pop();
            System.out.print(curr_node.data + " -> ");
            curr_node=curr_node.right;
        }
    }

    static Stack<Node> parent_list,child_list;
    public static void postOrder_Iterative(Node node){
        parent_list=new Stack<>();
        child_list=new Stack<>();
        if(node==null)
            return;
        parent_list.push(node);
        while(parent_list.size()>0){
            Node curr_node = parent_list.pop();
            child_list.push(curr_node);
            if(curr_node.left!=null)
                parent_list.push(node.left);
            if(curr_node.right!=null)
                parent_list.push(node.right);
        }
        while(child_list.size()>0){
            Node curr_node=child_list.pop();
            System.out.print(curr_node.data+"->");
        }
    }

    public static void Binarytree_traversal(Node root){
        // display_tree(root);
        // System.out.println();
        // preOrder(root);
        // System.out.println();
        // preOrder_iterative(root);
        // System.out.println();
        // inOrder(root);
        // System.out.println();
        // InOrder_iterative(root);
        // System.out.println();
        //postOrder(root);
        // System.out.println();
        // postOrder_Iterative(root);
    }

    // root_to_node_path =========================================

    public static boolean root_to_node_path(Node node, int data, ArrayList<Node>path){
        if(node==null)return false;
        if(node.data==data){
            path.add(node);
            return true;
        }
        boolean res = root_to_node_path(node.left, data, path) || root_to_node_path(node.right, data, path);
        if(res) path.add(node);
        return res;    
    }

    public static ArrayList<Node> root_to_node_path_01(Node node,int data){
        if(node==null){
            return new ArrayList<>();
        }
        if(node.data==data){
            ArrayList<Node> baseAns = new ArrayList<>();
            baseAns.add(node);
            return baseAns;
        }
        ArrayList<Node> left = root_to_node_path_01(node.left, data);
        if(left.size()!=0){
            left.add(node);
            return left;
        }
        ArrayList<Node> right = root_to_node_path_01(node.right, data);
        if(right.size()!=0){
            right.add(node);
            return right;
        }
        return new ArrayList<>();
    }

    public static ArrayList<Integer> root_to_node_path_02(Node node,int data){
        if(node==null){
            return new ArrayList<>();
        }
        if(node.data==data){
            ArrayList<Integer> baseAns = new ArrayList<>();
            baseAns.add(node.data);
            return baseAns;
        }
        ArrayList<Integer> left = root_to_node_path_02(node.left, data);
        if(left.size()!=0){
            left.add(node.data);
            return left;
        }
        ArrayList<Integer> right = root_to_node_path_02(node.right, data);
        if(right.size()!=0){
            right.add(node.data);
            return right;
        }
        return new ArrayList<>();
    }

    public static void root_to_node_path_sol(Node root){
        // root_to_node_path boolean solution;
        ArrayList<Node> path = new ArrayList<>();
        System.out.println(root_to_node_path(root, -170, path));
        for(Node n:path){
            System.out.print(n.data+"->");
        }
        // root_to_node_path_01 arraylist solution;
        System.out.println();
        System.out.println(root_to_node_path_01(root, 120));
        // root_to_node_path_02 arraylist solution which show the data;
        System.out.println(root_to_node_path_02(root, 120));
    }

    // Lowest_common_Ancestor =============================

    public static Node lowestCommonAncestor(Node node, int p, int q){
        ArrayList<Node> path1=new ArrayList<>();
        ArrayList<Node> path2=new ArrayList<>();
        root_to_node_path(node, p, path1);
        root_to_node_path(node, q, path2);
        Node previousNode = null;
        int i = path1.size()-1;
        int j = path2.size()-1;
        while(i>=0 && j>=0){
            if(path1.get(i).data != path2.get(j).data) break;
            previousNode=path1.get(i);
            i--;
            j--;
        }
        return previousNode; // for data make function int and return previousnode.data;
    }

    static Node LCAnode = null;

    public static boolean Lowest_common_Ancestor(Node node, int p, int q){
        if(node==null)return false;
        boolean SelfDone=false;
        if(node.data==p || node.data==q)SelfDone=true;
        boolean leftDone = Lowest_common_Ancestor(node.left, p, q);
        if(LCAnode!=null)return true;
        boolean rightDone = Lowest_common_Ancestor(node.right, p, q);
        if(LCAnode!=null)return true;
        if((SelfDone&&leftDone)||(SelfDone&&rightDone)||(leftDone&&rightDone)){
            LCAnode=node;
            //System.out.println(LCAnode.data); //to display the data at the node;
        }
        return SelfDone||leftDone||rightDone;
    }

    public static void LCA(Node root){
        System.out.println(lowestCommonAncestor(root,10,120));
        System.out.println(Lowest_common_Ancestor(root, 10, 120));
    }

    // K near element ============================================

    

    public static void main(String[] args){
        int[] arr = {10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-170,110,-1,-1,120,-1,-1};
        Node root = construct_Tree(arr);
        basic(root);
        Binarytree_traversal(root);
        //root_to_node_path_sol(root);
        LCA(root);
    }  
}