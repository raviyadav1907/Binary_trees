class binary_tree_{

    public static class node{
        int data;
        node left = null;   // node* left = null;
        node right = null;  // node* right = null;

        node(int data){
            this.data=data;
            this.left=this.right=null;
        }
    }

    static int idx = 0;

    public static node construct_Tree(int[] arr){
        if(idx==arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        node node = new node(arr[idx++]);
        node.left=construct_Tree(arr);
        node.right=construct_Tree(arr);
        
        return node;
    }

    public static void display_tree(node root){
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

    public static void main(String[] args){
        int[] arr = {10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-170,110,-1,-1,120,-1,-1};
        node root = construct_Tree(arr);
        display_tree(root);
    }
    
}