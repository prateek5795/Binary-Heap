/*
 *	Nirbhay Sibal - nxs180002	
 *	Prateek Sarna - pxs180012
 */

// Starter code for bounded-size Binary Heap implementation
// Changed signature of heapSort changing <T> to <T extends Comparable<? super T>>
// poll returns null if pq is empty (not false)

package nxs180002;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinaryHeap<T extends Comparable<? super T>> {
	T[] pq;
	Comparator<T> comp;
	int size = 0;

	// Constructor for building an empty priority queue using natural ordering of T
	public BinaryHeap(T[] q) {
		// Use a lambda expression to create comparator from compareTo
		this(q, (T a, T b) -> a.compareTo(b));
	}

	// Constructor for building an empty priority queue with custom comparator
	public BinaryHeap(T[] q, Comparator<T> c) {
		pq = q;
		comp = c;
	}

	public void add(T x) { /* throw exception if pq is full */
		if (pq.length == size) {
			throw new IllegalStateException("Queue Full");
		} else {
			pq[size] = x;
			percolateUp(size);
			size++;
		}
	}

	public boolean offer(T x) { /* return false if pq is full */
		if (pq.length == size) {
			return false;
		} else {
			// add(x);
			pq[size] = x;
			percolateUp(size);
			size++;
			return true;
		}
	}

	public T remove() { /* throw exception if pq is empty */
		if (size == 0) {
			throw new NoSuchElementException("Queue Empty");
		} else {
			T min = pq[0];
			pq[0] = pq[size - 1];
			size--;
			percolateDown(0);
			return min;
		}
	}

	public T poll() { /* return null if pq is empty */
		if (size == 0) {
			return null;
		} else {
			// return remove();
			T min = pq[0];
			pq[0] = pq[size - 1];
			size--;
			percolateDown(0);
			return min;
		}
	}

	public T peek() { /* return null if pq is empty */
		if (size == 0) {
			return null;
		} else {
			return pq[0];
		}
	}

	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) {
		/* to be implemented */
		T x = pq[i];
		while (i > 0 && pq[parent(i)].compareTo(x) > 0) {
			pq[i] = pq[parent(i)];
			i = parent(i);
		}
		move(i, x);
	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) {
		/* to be implemented */
		T x = pq[i];
		int c = leftChild(i);
		while (c <= size - 1) {
			if (c < size - 1 && pq[c].compareTo(pq[c + 1]) > 0) {
				c = c + 1;
			}
			if (pq[c].compareTo(x) >= 0) {
				break;
			}
			pq[i] = pq[c];
			i = c;
			c = leftChild(i);
		}
		move(i, x);
	}

	// Assign x to pq[i]. Indexed heap will override this method
	void move(int i, T x) {
		pq[i] = x;
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	int leftChild(int i) {
		return 2 * i + 1;
	}

	// end of functions for team project

	void printQ() {
		System.out.print("Queue: ");
		for (int i = 0; i < size; i++) {
			System.out.print(pq[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Integer q[] = new Integer[n];
		BinaryHeap<Integer> qObj = new BinaryHeap<>(q);

		Scanner in = new Scanner(System.in);
		whileloop: while (in.hasNext()) {
			int com = in.nextInt();
			switch (com) {
			case 1: // OFFER
				System.out.print("Enter element to add: ");
				int x = in.nextInt();
				System.out.println(qObj.offer(x));
				qObj.printQ();
				break;
			case 2: // ADD
				System.out.print("Enter element to add: ");
				int y = in.nextInt();
				qObj.add(y);
				qObj.printQ();
				break;
			case 3: // POLL
				System.out.println("Element removed: " + qObj.poll());
				qObj.printQ();
				break;
			case 4: // REMOVE
				System.out.println("Element removed: " + qObj.remove());
				qObj.printQ();
				break;
			case 5: // PEEK
				System.out.println("Peek: " + qObj.peek());
				break;
			default: // Exit loop
				break whileloop;
			}
		}
	}

}
