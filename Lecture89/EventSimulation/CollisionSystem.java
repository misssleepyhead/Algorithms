package EventSimulation;

import edu.princeton.cs.algs4.MinPQ;

public class CollisionSystem {
    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particles

    public CollisionSystem(Particle[] particles) {
    }

    private void predict(Particle a) {
        // add to PQ all particle-wall and particle - particle collisions involving this particle
        if (a == null) return;
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }
        pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

    private void redraw() {
    }

    public void simulate() {
        // init PQ withh collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) predict(particles[i]);
        pq.insert(new Event(0, null, null));


        while (!pq.isEmpty()) {
            // get next event
            Event event = pq.delMin();
            if (!event.isValid()) continue;
            Particle a = event.getA();
            Particle b = event.getB();

            // update positions and time
            for (int i = 0; i < particles.length; i++)
                particles[i].move(event.getTime() - t);
            t = event.getTime();

            // process event
            if (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) a.bounceOffVerticalWall();
            else if (a == null && b != null) b.bounceOffHorizontalWall();
            else if (a == null && b == null) redraw();

            // predict new events based on changes
            predict(a);
            predict(b);
        }
    }

    public static void main(String[] args) {

    }
}
