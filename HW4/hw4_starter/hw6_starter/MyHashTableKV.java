import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTableKV<T1 extends Comparable<T1> , T2> {
    private class Pair {
        private T1 key;
        private T2 value;
        public Pair(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }

    private int numBuckets = 10;
    private ArrayList<Pair>[] buckets;
    private int count = 0;

    public MyHashTableKV() {
        //TODO: your code here
        buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void put(T1 key,  T2 value) {
        //TODO: your code here
        Pair a = new Pair(key, value);
        int bucket = Math.abs(key.hashCode() % numBuckets);
        buckets[bucket].add(a);
        count ++;
        double loadFactor = (double) count / numBuckets;
        if (loadFactor >= 0.5){
            rehash();
        }
    }

    public boolean contains(T1 key) {
        //TODO: your code here
        int bucket = Math.abs(key.hashCode() % numBuckets);
        ArrayList<Pair> a = buckets[bucket];
        for (Pair p: a){
            if (key.equals(p.key)){
                return true;
            }
        }
        return false;
    }

    public T2 get(T1 key) {
        //TODO: your code here
        //throw new UnsupportedOperationException();
        if (contains(key)){
            int bucket = Math.abs(key.hashCode() % numBuckets);
            ArrayList<Pair> a = buckets[bucket];
            for(Pair p: a){
                if (key.equals(p.key)){
                    return p.value;
                }
            }
        }
        return null;
    }

    private void rehash() {
        //TODO: your code here
        numBuckets = numBuckets * 2;
        count = 0;
        ArrayList<Pair> [] temp = buckets;
        buckets = new ArrayList[numBuckets];

        for (int i=0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<Pair>();
        }
        for (int j = 0; j < temp.length; j++){
            ArrayList<Pair> data = temp[j];
            for(Pair p: data){
                int bucket = Math.abs(p.key.hashCode() % numBuckets);
                buckets[bucket].add(p);
                count++;
            };
        }

    }

    public void remove(T1 key) {
        //TODO: your code here
        if(contains(key) == false){
            throw new NoSuchElementException();
        }else{
            int bucket = Math.abs(key.hashCode() % numBuckets);
            int idx = buckets[bucket].indexOf(key);
            buckets[bucket].remove(idx);
        }
        count--;
    }

    public int getNumBuckets () {
        return numBuckets;
    }

    @Override
    public String toString() {
        return "{" +
                "numBuckets=" + numBuckets +
                ", buckets=" + Arrays.toString(buckets) +
                '}';
    }
}
