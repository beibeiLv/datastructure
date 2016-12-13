

import java.util.Stack;

/**
 * @author belv use 2 stacks to implement the queue
 */
public class StacktoImplementQueue {
	private static Stack<Integer> stack1 = new Stack<Integer>();
	private static Stack<Integer> stack2 = new Stack<Integer>();

	public static void enqueue(int code) {
		stack1.push(code);
	}

	public static int dequeue() {
		if (stack2.empty()) {
			if (!stack1.empty()) {
				stack2.push(stack1.pop());
			}

		}
		if (stack2.empty()) {
			try {
				throw new Exception("queue is empty.");
			} catch (Exception e) {
			}
		}
		int head = stack2.pop();
		return head;
	}

	public static void main(String args[]) {
		enqueue(1);
		enqueue(2);
		int i = dequeue();
		System.out.println(i);
		int j = dequeue();
		System.out.println(j);
	}
}
