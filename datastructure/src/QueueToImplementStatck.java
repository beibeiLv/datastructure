

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author belv use 2 stacks to implement the queue
 */
public class QueueToImplementStatck {
	private static Queue<Integer> queue1 = new ArrayDeque<Integer>();
	private static Queue<Integer> queue2 = new ArrayDeque<Integer>();

	public static void push(int code) {
		queue1.add(code);
	}

	public static int pop() {
		while (queue1.size() > 1) {
			queue2.add(queue1.poll());
		}

		int head = queue1.poll();
		queue1.addAll(queue2);
		return head;
	}

	public static void main(String args[]) {
		push(1);
		push(2);
		push(3);
		push(4);
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());

	}
}
