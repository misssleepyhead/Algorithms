package Module11;

public class IntervalST <Key extends Comparable<Key>, Value>{
    class IntervalNode{
        Key lo;
        Value hi;
        Value maxEndpoint;

        public IntervalNode(Key lo, Value hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    IntervalNode root;

}
