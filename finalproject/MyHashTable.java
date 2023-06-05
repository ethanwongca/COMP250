package finalproject;
//FINAL UPDATED
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<MyPair<K,V>> {
	// num of entries to the table
	private int size;
	// num of buckets
	private int capacity = 16;
	// load factor needed to check for rehashing
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K, V>>> buckets;


	// constructors
	public MyHashTable() {
		this.size = 0;
		this.capacity = 16;
		this.buckets = new ArrayList<LinkedList<MyPair<K, V>>>();
		for (int i = 0; i < this.capacity; i++) {
			this.buckets.add(new LinkedList<MyPair<K, V>>());
		}
	}

	public MyHashTable(int initialCapacity) {
		this.size = 0;
		this.capacity = initialCapacity;
		this.buckets = new ArrayList<LinkedList<MyPair<K, V>>>();
		for (int i = 0; i < this.capacity; i++) {
			this.buckets.add(new LinkedList<MyPair<K, V>>());
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList<MyPair<K, V>>> getBuckets() {
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key.
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode()) % this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		//like a chaining hash-map (lazy implementation)
		int bucketIndex = hashFunction(key);
		LinkedList<MyPair<K, V>> bucket = this.buckets.get(bucketIndex);
		for (MyPair<K, V> pairs : bucket) {
			if (pairs != null) { //pair isn't null check
				if (pairs.getKey().equals(key)) {
					V oldValue = pairs.getValue();
					pairs.setValue(value); //overwritting an old value with a new one
					return oldValue;
				}
			}
		}
		//collision is necessary here
		bucket.add(new MyPair(key, value));
		this.size++;
		if (this.size >= this.capacity * this.MAX_LOAD_FACTOR) { 
			this.rehash();
		}
		return null; 

	}



	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		int bucketIndex = hashFunction(key);
		//chaining with hashmap methods, typically O(1) as each index shouldn't have many elements
		//double check the vals later
		LinkedList<MyPair<K, V>> bucket = this.buckets.get(bucketIndex);
		for(MyPair<K, V> pairs: bucket){
			if(pairs != null) { //double checks null
				if (pairs.getKey().equals(key)) {
					return pairs.getValue();
				}
			}
		}
		return null;

	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1)
	 */
	public V remove(K key) {
		//a key as input and removes from the table the entry
		//similar to get method
		int bucketIndex = hashFunction(key);

		LinkedList<MyPair<K, V>> bucket = this.buckets.get(bucketIndex);
		for(MyPair<K,V> pairs: bucket){
			if(pairs != null) {
				if (pairs.getKey().equals(key)) {
					// set temp so value could be used later
					V value = pairs.getValue();
					bucket.remove(pairs);
					this.size--;
					return value;
				}
			}
		}
		return null;

	}


	/**
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		ArrayList<LinkedList<MyPair<K,V>>> tempBuckets = this.buckets; //size and capcity temp is redundant
		this.buckets = new ArrayList<LinkedList<MyPair<K,V>>>();
		this.capacity = this.capacity * 2;
		for(int i = 0; i < this.capacity; i++) {
			this.buckets.add(new LinkedList<MyPair<K, V>>());
		}
		for(LinkedList<MyPair<K, V>> bucket: tempBuckets){ // running through the old lists O(n * m)
			if(bucket != null){ //just an extra parameter since my implementation is lazy
				for(MyPair<K, V> pair: bucket) {
					if (pair != null) { 
						int bucketIndex = hashFunction(pair.getKey()); 
						LinkedList<MyPair<K,V>> linkedlist = this.buckets.get(bucketIndex);
						linkedlist.add(pair);
					}
				}
			}
		}

	}



	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		ArrayList<K> keySet = new ArrayList<K>();
		//iterates through all the elements and place all the keys in each
		for(LinkedList<MyPair<K, V>> bucket: this.buckets){
			if(bucket != null){ //checks null statement
				for(MyPair<K, V> pair: bucket){
					if(pair != null){
						keySet.add(pair.getKey());
					}
				}
			}
		}
		return keySet;
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		ArrayList<V> valueSet = new ArrayList<V>();

		for(LinkedList<MyPair<K, V>> bucket: this.buckets){
			if(bucket != null){
				for(MyPair<K, V> pair: bucket){
					if(pair != null){
						if(!valueSet.contains(pair.getValue())) { //this is probably a no go since O(n)
							//need to add contains in O(1) somehow
							valueSet.add(pair.getValue());
						}

					}
				}
			}
		}


		return valueSet;

	}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		ArrayList<MyPair<K, V>> entries = new ArrayList<MyPair<K, V>>();
		for(LinkedList<MyPair<K, V>> bucket: this.buckets){
			if(bucket != null){
				for(MyPair<K, V> pair: bucket){
					if(pair != null) {
						entries.add(pair);
					}
				}
			}
		}
		//I think this runs in O(m), since we assume the run of O(1) for data

		return entries;
	}



	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<MyPair<K,V>> {
		private int indexEntries;
		private ArrayList<MyPair<K, V>> entries;
		private MyHashIterator() {
			this.indexEntries = 0;
			this.entries = getEntries(); //might be an error here
		}

		@Override
		public boolean hasNext() {
			return indexEntries < entries.size();
		}

		@Override
		public MyPair<K,V> next() {
			if(this.hasNext()){
				return entries.get(indexEntries ++);
			}
			return null;
		}

	}

}
