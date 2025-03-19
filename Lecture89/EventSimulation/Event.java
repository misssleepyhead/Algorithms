package EventSimulation;

public class Event implements Comparable<Event> {
    private double time; // time of event
    private Particle a, b; // particles involved in event
    private int countA, countB; // collision count for a and b

    // create event
    public Event(double time, Particle a, Particle b) {
        this.time = time;
        this.a = a;
        this.b = b;
    }

    // ordered by time
    @Override
    public int compareTo(Event that) {
        return (int) (this.time - that.time);
    }

    // invalid if intervening collide
    public boolean isValid() {
        return a == null && b == null;
    }

    public Particle getA(){
        return a;
    }

    public Particle getB(){
        return b;
    }

    public double getTime(){
        return time;
    }
}
