package basic.algorithm;

import java.util.Stack;

/**
 * Implement Queue using Stacks
 * @author yudazhi
 */
public class MyQueueByStack {

    private Stack<Integer> input;
    private Stack<Integer> output;

    public MyQueueByStack() {
        this.input = new Stack<>();
        this.output = new Stack<>();
    }

    public void enqueue(int x) {
        input.push(x);
    }

    public int dequeue() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    public static void main(String[] args) {
        MyQueueByStack demo = new MyQueueByStack();
        demo.enqueue(1);
        demo.dequeue();
        demo.dequeue();
        demo.enqueue(2);
        demo.enqueue(3);
        demo.dequeue();
        demo.dequeue();
        demo.enqueue(4);
        demo.dequeue();
    }
}
