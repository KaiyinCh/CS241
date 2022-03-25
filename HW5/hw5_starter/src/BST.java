import com.sun.source.tree.Tree;

public class BST<AnyType extends Comparable<AnyType>>
{
    private TreeNode root;
    
    public BST()
    {
        root = null;
    }

    public BST(TreeNode root)
    {
        this.root = root;
    }

    public boolean isEmpty()
    {
        return root == null;
    }
    
    // initial add method, looking ahead
    public void add(AnyType value)
    {
        if (isEmpty())
            root = new TreeNode(value,null,null);
        else
            addHelper(root,value);
    }
    
    private void addHelper(TreeNode rt, AnyType value)
    {
        if (value.compareTo(rt.data) < 0)
            if (rt.left == null)
                rt.left = new TreeNode(value,null,null);
            else
                addHelper(rt.left,value);
        else if (value.compareTo(rt.data) > 0)
            if (rt.right == null)
                rt.right = new TreeNode(value,null,null);
            else
                addHelper(rt.right,value);
        else
            throw new IllegalStateException("duplicate value in tree " + value);
    }
    
    // a cleaner, more compact add method
    public void add2(AnyType value)
    {
        root = addHelper2(root,value);
    }
    
    private TreeNode addHelper2(TreeNode rt, AnyType value)
    {
        if (rt == null)
            return new TreeNode(value,null,null);
        
        if (value.compareTo(rt.data) < 0)
            rt.left = addHelper2(rt.left,value);
        else if (value.compareTo(rt.data) > 0)
            rt.right = addHelper2(rt.right,value);
        else
            throw new IllegalStateException("duplicate value in tree " + value);
        
        return rt;  // returns the changed (and unchanged) rt's since
        // you have to (re-)assign values all the way back up the call stack!
    }
            
    public void inOrder()
    {
        inOrder(root);
    }
    
    private void inOrder(TreeNode rt)
    {
        if (rt != null)
        {
            inOrder(rt.left);
            System.out.print(rt.data + " ");
            inOrder(rt.right);
        }
    }
    
    public void preOrder()
    {
        preOrder(root);
    }
    
    private void preOrder(TreeNode rt)
    {
        if (rt != null)
        {
            System.out.print(rt.data + " ");
            preOrder(rt.left);
            preOrder(rt.right);
        }
    }
    
    public int size()
    {
        return size(root);
    }
    
    private int size(TreeNode rt)
    {
        if (rt == null)
            return 0;
        return 1 + size(rt.left) + size(rt.right);
    }
    
    public boolean contains(AnyType value)
    {
        TreeNode rt = root;
        while (rt != null)
        {
            if (value.compareTo(rt.data) == 0)
                return true;
            else if (value.compareTo(rt.data) < 0)
                rt = rt.left;
            else
                rt = rt.right;
        }
        return false;
    }

    // returns a String that prints tree top to bottom, right to left in a 90-degree rotated level view
    public String toString()
    {
        StringBuilder result =  new StringBuilder();
        return toString(result,-1,root).toString();
    }
    
    public StringBuilder toString(StringBuilder res, int height, TreeNode rt)
    {
        if (rt != null)
        {
            height++;
            res = toString(res,height,rt.right);
            for (int i = 0; i < height; i++)
                res.append("\t");
            res.append(rt.data + "\n");
            res = toString(res,height,rt.left);
        }
        return res;
    }
    
    // the TreeNode class is a private inner class used (only) by the BST class
    private class TreeNode
    {
        private AnyType data;
        private TreeNode left, right;
        
        private TreeNode(AnyType data, TreeNode left, TreeNode right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        private TreeNode(TreeNode node) {
            this.data = node.data;
            this.left = node.left;
            this.right = node.right;
        }
    }

    //--------Your HW5 functions start here ---------------//
    private int leftHeightHelper (TreeNode node){
        int leftHeight = 0;
        if (node.left != null){
            leftHeight = leftHeightHelper(node.left);
        }
        return leftHeight+1;
    }

    private int rightHeightHelper (TreeNode node){
        int rightHeight = 0;
        if (node.right != null){
            rightHeight = rightHeightHelper(node.right);
        }
        return rightHeight + 1;
    }

    public int height(){
        if (root == null){
            return -1;
        }
        else {
            TreeNode node = root;
            if(leftHeightHelper(node) >= rightHeightHelper(node)){
                return leftHeightHelper(node)-1;
            }else{
                return rightHeightHelper(node)-1;
            }
        }
    }

    public boolean isBalance(){
        TreeNode node = root;
        if (node == null){
            return true;
        }

        int l = leftHeightHelper(node);
        int r = rightHeightHelper(node);
        if (l == r || l == r + 1 || l == r - 1){
            return true;
        }

        return false;
    }

    public boolean equals(Object tree2) {
        //TODO: your code here
        //hint: implement private boolean equals_helper(TreeNode n1, TreeNode n2)
        TreeNode treeOne = root;
        BST<AnyType> tree = (BST<AnyType>) tree2;
        TreeNode treeTwo = tree.root;
        return equals_helper(treeOne, treeTwo);
    }

    private boolean equals_helper(TreeNode n1, TreeNode n2){
        if (n1 == null && n2 == null){
            return true;
        }
        if (n1 != null && n2!= null){
            return (n1.data == n2.data && equals_helper(n1.left, n2.left) && equals_helper(n1.right, n2.right));
        }

        return false;
    }

    public BST<AnyType> mirrorTree(){
        //TODO: your code here
        //hint: implement private TreeNode mirrorTree(TreeNode node)
        BST<AnyType> tree = new BST<AnyType> ();
        if (root == null){
            return tree;
        }else if (root.left == null && root.right == null){
            tree.add(root.data);
        }else{
            tree.add(root.data);
            TreeNode l = root.left;
            TreeNode r = root.right;
            while (l != null){
                AnyType a = l.data;
                tree.mirrorTree(tree.root, a);
                if(l.right != null){
                    a = l.right.data;
                    tree.mirrorTree(tree.root, a);
                }
                l = l.left;
            }
            while (r != null){
                AnyType a = r.data;
                tree.mirrorTree(tree.root, a);
                if(r.left != null){
                    a = r.left.data;
                    tree.mirrorTree(tree.root, a);
                }
                r = r.right;
            }

        }
        return tree;
    }

    private TreeNode mirrorTree(TreeNode node, AnyType a){
        if (node == null){
            return new TreeNode(a, null, null);
        }
        if (a.compareTo(node.data) > 0){
            node.left = mirrorTree(node.left, a);
        }else{
            node.right = mirrorTree(node.right, a);
        }
        return node;
    }

    public static BST<Integer> skewedTree(int n) {
        //TODO: your code here
        //hint: to measure time, use System.nanoTime();
        //to capture start time and end time.
        int i = 1;
        BST<Integer> tree = new BST<Integer>();
        while (i <= n){
            tree.add2(i);
            i++;
        }

        long start = System.nanoTime();
        tree.contains(n);
        long stop = System.nanoTime();
        long time = stop - start;
        System.out.println("The runtime for " + n + " is " + time);

        return tree;
    }

    public static BST<Integer> balancedTree(int n) {
        //TODO: your code here
        //hint: the only difference from skewedTree is the order of
        //insertion. implement the insertion function:
        //static private void seqinsert(BST<Integer> tree, int low, int high)
        //following pseudo code in the hw5 document, which means
        // if you have input 7, and thus you have a sequence of number
        //1,2,3,4,5,6,7 then the insertion order is
        //4,2,1,3,6,5,7 which creates a balanced BST.
        //throw new UnsupportedOperationException();
        BST<Integer> tree = new BST<Integer>();
        int low = 1;
        int high = n;
        seqinsert(tree, low, high);

        long start = System.nanoTime();
        tree.contains(n);
        long stop = System.nanoTime();
        long time = stop - start;
        System.out.println("The runtime for " + n + " is " + time);

        return tree;
    }

    static private void seqinsert(BST<Integer> tree, int low, int high){
        if (low <= high) {
            int mid = (low + high) / 2;
            tree.add2(mid);
            seqinsert(tree, low, mid - 1);
            seqinsert(tree, mid + 1, high);
        }
    }

    public boolean recursiveContains(AnyType value){
        return recursiveContains(root, value);
    }

    public boolean recursiveContains(TreeNode node, AnyType value){
        int i = value.compareTo(node.data);
        if (i == 0)
            return true;
        if (node.left != null && i<0)
            return recursiveContains(node.left, value);
        if (node.right != null && i>0)
            return recursiveContains(node.right, value);

        return false;
    }

    //--------Your HW5 fundtions end here ------------------//
    public static void main(String[] args)
    {
        BST<Integer> treeTest = new BST<Integer>();
        treeTest.add2(7);
        treeTest.add2(5);
        treeTest.add2(4);
        treeTest.add2(10);
        treeTest.add2(6);
        treeTest.add2(8);
        treeTest.inOrder();
        System.out.println();
        treeTest.preOrder();
        System.out.println(treeTest.size());
        System.out.println();
        System.out.println(treeTest.contains(6));
        System.out.println(treeTest.contains(112));
        System.out.println(treeTest.contains(7));
        System.out.println(treeTest.contains(10));
        System.out.println();
        System.out.println(treeTest);

    }
    
}

