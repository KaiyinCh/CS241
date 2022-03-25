import java.util.NoSuchElementException;

/** Represents a linked list of integer data. */
public class LinkedIntList {

    public ListNode front;
    private static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this(data, null);
        }

        ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    /** An empty LinkedIntList. */
    public LinkedIntList() {
        // Empty LinkedIntList
    }

    /** Returns a new LinkedIntList containing the ints in args. */
    public LinkedIntList(int... args) {
        if (args.length != 0) {
            front = new ListNode(args[0]);
            ListNode back = front;
            for (int i = 1; i < args.length; i += 1) {
                back.next = new ListNode(args[i]);
                back = back.next;
            }
        }
    }

    /** Return a new LinkedIntList with the given ListNode. */
    private LinkedIntList(ListNode front) {
        this.front = front;
    }

    // Note that lowercase variable names are ListNodes while uppercase
    // variable names are LinkedIntLists.

    /** Squares all of the integers in the list L. Destructive. */
    public static void square(LinkedIntList L) {
        if (L != null) {
            ListNode p = L.front;
            while (p != null) {
                p.data = p.data * p.data;
                p = p.next;
            }
        }
    }

    /** Returns a list equal to L with all integers squared. Non-destructive. */
    public static LinkedIntList iterativeSquared(LinkedIntList L) {
        if (L == null) {
            return null;
        } else if (L.front == null) {
            return new LinkedIntList();
        }
        ListNode p = L.front;
        ListNode front = new ListNode(p.data * p.data);
        ListNode back = front;
        p = p.next;
        while (p != null) {
            back.next = new ListNode(p.data * p.data);
            back = back.next;
            p = p.next;
        }
        return new LinkedIntList(front);
    }

    /** Returns a list equal to L with all integers squared. Non-destructive. */
    public static LinkedIntList recursiveSquared(LinkedIntList L) {
        if (L == null) {
            return null;
        } else if (L.front == null) {
            // Will the code still work if we remove this case?
            return new LinkedIntList();
        } else {
            return new LinkedIntList(recursiveSquaredHelper(L.front));
        }
    }

    /** Helper method to do the actual recursion for recursiveSquared. */
    private static ListNode recursiveSquaredHelper(ListNode p) {
        if (p == null) {
            return null;
        } else {
            ListNode squaredRest = recursiveSquaredHelper(p.next);
            return new ListNode(p.data * p.data, squaredRest);
        }
    }

    public int size(){
        ListNode finger = front;
        int i=0;
        while (finger != null){
            i++;
            finger = finger.next;
        }
        return i;
    }

    /** Recursively add an element to the idx-th of the list */
   public void add(int idx, int data) {
       ListNode finger = front;
       ListNode node = new ListNode(data);
       if (front == null){
           front = node;
       }else if (idx == 0){
           node.next = finger;
           finger = node;
           front = finger;
       }else if (idx == 1){
           node.next = finger.next;
           finger.next = node;
           front = finger;
       }else{
           front = front.next;
           add(idx-1, data);
           front = finger;
       }
    }

    /**  Recursively Retrieves the idx-th element of the list.*/
    public int getByIndex(int idx) {
        if (idx >= size() || idx < 0){
            throw new IndexOutOfBoundsException("Number out of index.");
        }

        if (idx == 0){
            return front.data;
        }else{
            front = front.next;
            return getByIndex(idx -1);
        }
    }

    /**  Recursively removes the first element of the list which equals val.*/
    public void removeByValue(int val) {
        ListNode finger = front;
        if (front == null){
            throw new NoSuchElementException("Can not find the element.");
        }
        else if (finger.data == val){
            front.data = finger.next.data;
            front.next = front.next.next;
        }else if (finger.next.data == val){
            finger.next = finger.next.next;
        }else{
            front = front.next;
            removeByValue(val);
        }
    }

    /**  Recursively reverse the list in place without using new.*/
    public void reverseInPlace() {
        ListNode finger = front;
        if (finger != null && finger.next != null) {
            front = front.next;
            reverseInPlace();
            finger.next.next = finger;
            finger.next = null;
        }
    }

    /* Non-recursive, works like insertionSort to maintain a sorted linked list*/
    public void sortedAdd(int val) {
        ListNode finger = front;
        ListNode newNode = new ListNode(val);
        if (front == null){
            front = new ListNode(val);
        }else{
            if (front.data >= val){
                newNode.next = front;
                front = newNode;
            }else{
                while(finger.next != null && finger.next.data < val){
                    finger = finger.next;
                }
                newNode.next = finger.next;
                finger.next = newNode;
        }
      }
    }

    /**
     Question: Can we run binary search at O(logn) on a sorted linked list maintained by sortedAdd()? Why or why not?
     Answer: No, is not possible to run binary search at O(logn) on a sorted linked list maintained by sortedAdd().
             Because linked list can not randomly access all the element in a short period. Binary search need to find the meddle
             element multiple times and required the the length of the list. If using binary search in linked list then required
             longer runtime and lager space.
    */

    // You don't need to look at or understand the methods below this comment.

    /**
     * Returns true if and only if the other list contains the same sequence of
     * ints. Cannot handle LinkedIntLists with cycles. You are not expected to
     * read or understand this method.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LinkedIntList)) {
            return false;
        }
        ListNode l = ((LinkedIntList) other).front;
        ListNode p = this.front;
        while (p != null && l != null) {
            if (p.data != l.data) {
                return false;
            }
            p = p.next;
            l = l.next;
        }
        return p == null && l == null;
    }

    /** You are not expected to read or understand this method. */
    @Override
    public int hashCode() {
        if (front == null) {
            return 0;
        } else {
            return front.data;
        }
    }

    /**
     * If a cycle exists in the LinkedIntList, this method returns an integer equal
     * to the item number of the location where the cycle is detected.
     *
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an LinkedIntList into a String and that LinkedIntList has a loop, your
     * computer don't get stuck in an infinite loop.
     */
    private int detectCycles() {
        if (front == null) {
            return 0;
        }
        ListNode tortoise = front;
        ListNode hare = front;
        int position = 0;
        while (true) {
            position += 1;
            if (hare.next != null) {
                hare = hare.next.next;
            } else {
                return 0;
            }
            tortoise = tortoise.next;
            if (tortoise == null || hare == null) {
                return 0;
            } else if (hare == tortoise) {
                return position;
            }
        }
    }

    /**
     * Return a string representation of the integers in the list. You are not
     * expected to read or understand this method.
     */
    @Override
    public String toString() {
        if (front == null) {
            return "()";
        }
        int cycleLocation = detectCycles();
        int position = 0;
        String result = "(" + front.data;
        for (ListNode p = front.next; p != null; p = p.next) {
            result += ", " + p.data;
            position += 1;
            if (cycleLocation > 0 && position > cycleLocation) {
                result += "... cycle exists ...";
                break;
            }
        }
        result += ")";
        return result;
    }
}
