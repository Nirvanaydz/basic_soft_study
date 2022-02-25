package java01.basic.datastructure.basic.linkedlist;

/**
 * @author yudazhi
 */
public class LinkedList {

    /**
     * 合并有序链表
     *
     * @param n1
     * @param n2
     * @return
     */
    private static Node merge(Node n1, Node n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        if (n1.data < n2.data) {
            n1.next = merge(n1.next, n2);
            return n1;
        } else {
            n2.next = merge(n1, n2.next);
            return n2;
        }
    }

    /**
     * 反转链表
     *
     * @param root
     * @return
     */
    private static Node reverse(Node root) {
        Node prev = null;
        Node temp = root;
        while (temp != null) {
            //  保存要操作节点的下一个节点
            Node tempNext = temp.next;
            //  将当前节点指向prev
            temp.next = prev;
            //  将prev前移
            prev = temp;
            //  将当前操作点向后移动
            temp = tempNext;
        }
        return prev;
    }

    private static class Node {
        private int data;
        private Node next;

        public Node() {
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
