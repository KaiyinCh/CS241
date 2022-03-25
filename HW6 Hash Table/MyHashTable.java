import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTable<DataType> {
	private int numBuckets = 10;
	private ArrayList<DataType>[] buckets;
	private int count = 0;
	public MyHashTable() {
		buckets = new ArrayList[numBuckets];
		for (int i = 0; i < numBuckets; i++) {
			buckets[i] = new ArrayList<DataType>();
		}
	}

	public void put(DataType item) {
		int bucket = Math.abs(item.hashCode() % numBuckets);
		buckets[bucket].add(item);
		count ++;
		//TODO: your code here to call rehash as needed
		double loadFactor = (double) count / numBuckets;
		if (loadFactor >= 0.5){
			this.rehash();
		}
	}

	public boolean contains(DataType item) {
		//TODO: your code here
		int bucket = Math.abs(item.hashCode() % numBuckets);
		return buckets[bucket].contains(item);
	}

	public void remove(DataType item) {
		//TODO: your code here
		//throw new NoSuchElementException();
		if(contains(item) == false){
			throw new NoSuchElementException();
		}
		int bucket = Math.abs(item.hashCode() % numBuckets);
		int idx = buckets[bucket].indexOf(item);
		buckets[bucket].remove(idx);
		count--;
	}

	private void rehash() {
		//TODO: your code here
		//throw new UnsupportedOperationException();
		int newNum = this.numBuckets * 2;
		int idx;
		ArrayList<DataType> [] temp = new ArrayList[newNum];
		for (int i=0; i < newNum; i++) {
			temp[i] = new ArrayList<DataType>();
		}
		for (int j = 0; j < numBuckets; j++){
			Iterator value = buckets[j].iterator();
			while (value.hasNext()){
				DataType data = (DataType) value.next();
				idx = Math.abs(data.hashCode() % newNum);
				temp[idx].add(data);
			}
		}
		buckets = temp;
		numBuckets = newNum;

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
