import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPQ {

    @Test
    public void testBasicMinPQ(){
        MinPQ pq = new MinPQ<>(10);
        pq.insert(9);
        pq.insert(10);
        pq.insert(3);
        pq.insert(1);
        pq.insert(2);
        assertEquals(1,pq.min());
        assertEquals(1,pq.delMin());
        assertEquals(2,pq.min());
    }

    @Test
    public void testBasicMaxPQ(){
        MaxPQ pq=new MaxPQ<>(10);
        pq.insert(9);
        pq.insert(10);
        pq.insert(3);
        pq.insert(1);
        pq.insert(2);
        assertEquals(10,pq.max());
        pq.delMax();
        assertEquals(9,pq.max());
    }
}
