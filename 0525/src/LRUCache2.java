import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache2 {
    /**
     * 使用一个双向链表来维护这个map的插入顺序，map的k为输入的key,v为双向链表中的节点。
     */
    public static class Node {
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private HashMap<Integer,Node> lru;
    private int size;
    private Node head;
    private Node tail;
    public LRUCache2(int capacity) {
        lru = new HashMap<>();
        size = capacity;
    }

    public int get(int key) {
        Node temp = lru.get(key);
        //如果没有这个key对应的值，说明不存在，返回-1
        if(temp == null){
            return -1;
        }else {
            //说明存在，将它在双向链表的位置插入到最前面。
            int res = temp.val;
            remove(temp);
            add(temp);
            return res;
        }
    }

    public void put(int key, int value) {
        Node temp = lru.get(key);
        //检查是否存在，如果存在就更新它的值，然后将它从双向链表中放到队头位置。
        if(temp != null){
            temp.val = value;
            remove(temp);
            add(temp);
        }else {
            temp = new Node(key,value);
            //如果已经溢出了，删除队尾的元素
            if (lru.size() >= size) {
                lru.remove(tail.key);
                remove(tail);
            }
            add(temp);
            lru.put(key,temp);
        }
    }

    /**
     * 使用头插法，维护双向链表
     * @param node
     */
    private void add(Node node){
        if(head == null){
            head = tail = node;
        }else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    /**
     * 移除分三种情况，队列本身只有一个元素，移除的元素在队头、队尾、对中
     * @param node
     */
    private void remove(Node node){
        if(head == tail){
            head = tail = null;
        }else {
            if(head == node){
                head = head.next;
                head.prev = null;
                node.next = null;
            }else if(node == tail){
                tail = tail.prev;
                tail.next = null;
                node.prev = null;
            }else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
            }
        }
    }
}
