package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {

    //insert in BST with Recursive
    public static void insertNode1(Node r, Node n) {
        if (r.value < n.value) {
            if (r.right == null) {
                r.right = n;
            } else {
                insertNode1(r.right, n);
            }
        } else {
            if (r.left == null) {
                r.left = n;
            } else {
                insertNode1(r.left, n);
            }
        }
    }

    //insert in BST with Iterative
    public static void insertNode2(Node r, Node n) {
        while (r != null) {
            if (n.value > r.value) {
                if (r.right == null) {
                    r.right = n;
                    return;
                } else {
                    r = r.right;
                }
            } else {
                if (r.left == null) {
                    r.left = n;
                    return;
                } else {
                    r = r.left;
                }
            }
        }
    }

    //search in BST with Recursive
    public static boolean searchNode1(Node r, Node n) {
        if (r.value == n.value) {
            return true;
        }
        if (r.value < n.value) {
            if (r.right != null) {
                return searchNode1(r.right, n);
            }
        }
        if (r.value > n.value) {
            if (r.left != null) {
                return searchNode1(r.left, n);
            }
        }
        return false;

    }

    //search in BST with Iterative
    public static void searchNode2(Node r, int e) {
        while (r != null) {
            if (e == r.value) {
                System.out.println("true");
                return;
            }
            if (e > r.value) {
                if (r.right != null) {
                    r = r.right;
                } else {
                    System.out.println("false");
                    return;
                }
            } else {
                if (r.left != null) {
                    r = r.left;
                } else {
                    System.out.println("false");
                    return;
                            
                }
            }
        }
        System.out.println("false");
    }

    //access to Max Node
    public static int maxNode(Node r) {
        if (r.right == null) {
            return r.value;
        }
        return maxNode(r.right);
    }

    //access to Min Node
    public static int minNode(Node r) {
        if (r.left == null) {
            return r.value;
        }
        return minNode(r.left);
    }

    //print Max and Min Node
    public static void maxMinNode(Node r) {
        int[] t = new int[numOfNode(r)];
        inOrder(r, t);
        System.out.println("MIN Node:" + t[0]);
        System.out.println("MAX Node:" + t[t.length - 1]);

    }

    //return num of node
    public static int numOfNode(Node r) {
        if (r == null) {
            return 0;
        }
        int l1 = numOfNode(r.left);
        int r1 = numOfNode(r.right);
        return l1 + r1 + 1;
    }

    public static Node nextNode(Node r, Node n) {
        if (r.left == null) {
            if (r.value > n.value) {
                return r;
            }
        }
        if (r.left != null) {
            if (r.value > n.value) {
                return nextNode(r.left, n);
            }
        }
        if (r.right != null) {
            return nextNode(r.right, n);
        }
        return null;
    }

    public static Node deleteLeave(Node r, Node n) {
        if (r.left == null && r.right == null) {
            return null;
        }
        if (r.value < n.value) {
            if (r.right != null) {
                deleteLeave(r.right, n);
            }
        }
        if (r.value > n.value) {
            if (r.left != null) {
                deleteLeave(r.left, n);
            }
        }
        return null;
    }

    public static int[] showTree(Node r, int h) {
        int n = (int) (Math.pow(2, (h + 1)) - 1);
        int[] tree = new int[n];
        tree[0] = r.value;
        for (int i = 0; i < h; i++) {
            if (r.left != null) {
                tree[2 * i + 1] = r.left.value;
            } else {
                tree[2 * i + 1] = 0;
            }
            if (r.right != null) {
                tree[2 * i + 2] = r.right.value;
            } else {
                tree[2 * i + 2] = 0;
            }
        }
        return tree;
    }

    public static void preOrder(Node r) {
        if (r != null) {
            System.out.print(r.value + " ");
            preOrder(r.left);
            preOrder(r.right);
        }
    }
    static int inIndex = 0;

    public static void inOrder(Node r, int[] t) {
        if (r != null) {
            inOrder(r.left, t);
            t[inIndex++] = r.value;
            inOrder(r.right, t);
        }
    }

    public static void postOrder(Node r) {
        if (r != null) {
            postOrder(r.left);
            postOrder(r.right);
            System.out.print(r.value + " ");
        }
    }
    static int preIndex = 0;

    public static int searchArray(int[] in, int n) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == n) {
                return i;
            }
        }
        return 0;
    }

    public static Node creatTree(int[] in, int[] pre, int instrt, int inend) {
        if (instrt > inend) {
            return null;
        }
        Node tnode = new Node(pre[preIndex++]);
        if (instrt == inend) {
            return tnode;
        }
        int inIndex = searchArray(in, tnode.value);
        tnode.left = creatTree(in, pre, instrt, inIndex - 1);
        tnode.right = creatTree(in, pre, inIndex + 1, inend);
        return tnode;
    }
    //cheakBst Recursion
    public static boolean checkBST1(Node r) {
        if (r == null) {
            return true;
        }
        if (r.left != null && r.left.value > r.value) {
            return false;
        }
        if (r.right != null && r.right.value < r.value) {
            return false;
        }
        return checkBST1(r.left) && checkBST1(r.right);
    }
    //cheakBST Iterative
    public static boolean checkBST2(Node r) {
        Queue<Node> q = new LinkedList<>();
        q.add(r);
        while (!q.isEmpty()) {
            if (q.peek().left != null) {
                if(q.peek().left.value<q.peek().value){
                q.add(q.peek().left);}
                else{
                    return false;
                }
            }
            if (q.peek().right != null) {
                if(q.peek().right.value>q.peek().value){
                q.add(q.peek().right);}
                else{
                    return false;
                }
            }
            q.poll();
        }
        return true;
    }
    

    public static void printLeevs(Node r) {
        if (r == null) {
            return;
        }
        if (r.left == null && r.right == null) {
            System.out.print(r.value + " ");
        } else {
            printLeevs(r.left);
            printLeevs(r.right);
        }
    }

    public static void printInternalNode(Node r) {
        if (r == null) {
            return;
        }
        if (r.left != null || r.right != null) {
            System.out.print(r.value + " ");
        }
        printInternalNode(r.left);
        printInternalNode(r.right);

    }

    public static void printFullNode(Node r) {
        if (r == null) {
            return;
        }
        if (r.left != null && r.right != null) {
            System.out.print(r.value + " ");
        }
        printFullNode(r.left);
        printFullNode(r.right);
    }

    public static void printWithBFS(Node r) {

    }

    public static void printWithDFS(Node r) {
        Stack<Node> s = new Stack<>();
        Stack<Node> visited = new Stack<>();
        s.push(r);
        while (!s.isEmpty()) {
            Node t = s.peek();
            if (!visited.contains(t)) {
                visited.add(s.peek());
                if (t.left != null) {
                    s.push(t.left);
                }
                if (t.right != null) {
                    s.push(t.right);
                }
                if (s.peek().left == null && s.peek().right == null) {
                    System.out.print(s.pop().value + " ");
                }
            }
            if (visited.contains(s.peek())) {
                System.out.print(s.pop().value + " ");
            }
        }
    }

    //delete with min
    public static Node delete1(Node r, int n) {
        if (r == null) {
            return r;
        }
        if (n > r.value) {
            r.right = delete1(r.right, n);
        } else if (n < r.value) {
            r.left = delete1(r.left, n);
        } else {
            if (r.left == null) {
                return r.right;
            } else if (r.right == null) {
                return r.left;
            }
            r.value = minNode(r.right);
            r.right = delete1(r.right, r.value);
        }
        return r;
    }

    //delete with max
    public static Node delete2(Node r, int n) {
        if (r == null) {
            return r;
        }
        if (n > r.value) {
            r.right = delete2(r.right, n);
        } else if (n < r.value) {
            r.left = delete2(r.left, n);
        } else {
            if (r.right == null) {
                return r.left;
            }
            if (r.left == null) {
                return r.right;
            }
            r.value = maxNode(r.left);
            r.left = delete2(r.left, r.value);
        }
        return r;
    }

    //delete with next node
    public static void deleteNode(Node r, Node n) {
        if (n.left == null && n.right == null) {
            n = null;
            return;
        }
        if (n.left != null && n.right != null) {
            n = nextNode(r, n);
            deleteNode(r, nextNode(r, n));
        }
        if (n.left == null & n.right != null) {
            n = n.right;
            deleteNode(r, n.right);
        } else {
            n = n.left;
            deleteNode(r, n.left);
        }
    }

    //delete wiht iterative
    public static void deleteNode2(Node r, int n) {
        Node temp=null;
        while (r != null) {
            temp=r;
            if (n > r.value) {
                r = r.right;
                
            } else if (n < r.value) {
                r = r.left;
                
            } else {
                if (r.right == null && r.left == null) {
                    temp.left=null;
                    temp.right=null;
                    return;
                }
                if (r.left != null && r.right == null) {
                    r = r.left;
                    
                }
                if (r.right != null && r.left == null) {
                    r = r.right;
                    
                }
                r.value = maxNode(r.left);
                r = r.left;
                
            }
        }
    }

    //return height of tree
    public static int height(Node r) {
        if (r == null) {
            return -1;
        }
        return Math.max(height(r.left) + 1, height(r.right) + 1);
    }

    //check if BST is AVL or Not
    public static boolean checkAVL(Node r) {
        if (r == null) {
            return true;
        }
        if (height(r.left) - height(r.right) != 0
                && height(r.left) - height(r.right) != -1
                && height(r.left) - height(r.right) != 1) {
            return false;
        }
        return checkAVL(r.left) && checkAVL(r.right);
    }

    //return Node not AVL in Tree
    public static Node notAVL(Node r) {
        if (r == null) {
            return null;
        }
        if (height(r.left) - height(r.right) > 1) {
            return r;
        } else if (height(r.left) - height(r.right) < -1) {
            return r;
        }
        return null;
    }

    public static void insertWithArray(Node r, Node b[]) {
        for (Node b1 : b) {
            insertNode1(r, b1);
        }
    }

    public static Node llAVL(Node r) {
        if (r == null) {
            return null;
        }
        if (r.left.right == null) {
            r.left.right = r;
        }
        return r.left;
    }

    public static Node lrAVL(Node r) {
        if (r == null) {
            return null;
        }
        r.left.right.left = new Node(r.left.value);
        r.left = r.left.right;
        return llAVL(r);
    }

    public static Node rrAVL(Node r) {
        if (r == null) {
            return null;
        }
        r.right.left = new Node(r.value);
        return r.right;
    }

    public static Node rlAVL(Node r) {
        if (r == null) {
            return null;
        }
        r.right.left.right = new Node(r.right.value);
        r.right = r.right.left;
        return rrAVL(r);
    }
  
    
    public static void main(String[] args) {
        Node r = new Node(12);
        Node t1[] = {new Node(8), new Node(18), new Node(5),
            new Node(11), new Node(17), new Node(4),
            new Node(7), new Node(2)};
        insertWithArray(r, t1);
        Node r3 = new Node(6);
        Node t3[] = {new Node(4), new Node(10), new Node(7),
            new Node(12), new Node(11), new Node(13)};
        insertWithArray(r3, t3);
        Node r4 = new Node(50);
        Node t4[] = {new Node(40), new Node(60), new Node(30), new Node(45), new Node(20), new Node(35)};
        insertWithArray(r4, t4);
        int[] in = {1, 5, 10, 13, 15, 17, 20};
        int[] pre = {15, 10, 5, 1, 13, 20, 17};
        Node r1 = creatTree(in, pre, 0, 6);
        preIndex = 0;
        //PrintTree.print(r4);
        //PrintTree.print(llAVL(r4));
        /*PrintTree.print(r4);
        Node b=new Node(35);       
        delete2(r4, 20);       
        PrintTree.print(r4);*/
               int space = (int) Math.pow(2,3), w = 1;
               int[] tr={50,40,0,30,0,0,0,20,35,0,0,0,0,0,0};
        for (int i = 0; i <4; i++) {
            for (int j = 1; j < space; j++) {
                System.out.print(" ");
            }
            for (int l = 0; l < w; l++) {
                if (tr[l + w - 1] == 0) {
                    System.out.print("�");
                } else {
                    System.out.print(tr[l + w - 1]);
                }
                for (int k = 1; k < space * 2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            w *= 2;
            space /= 2;
        }
    }
}
