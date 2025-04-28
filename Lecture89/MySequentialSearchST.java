/**Use linked list to imp sequence search symbol table*/
public class MySequentialSearchST <Key,Value>{
    private class Node{
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    private Node first;
    private int size;

    public MySequentialSearchST(Node first) {
        this.first = first;
        this.size=1;
    }

    public Value get(Key key){
        // scan through the list, using equal() to compare key, return value if we found target key otherwise return null
        for(Node x=first;x!=null;x=x.next){
            if(key.equals(x.key)){
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val){
        // search for the key, update val if found, create a new node if not found
        for(Node x=first;x!=null;x=x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        // not found, create a new node
        first = new Node(key,val,first);
        size++;
    }

    public int size(){
        return size;
    }


}
