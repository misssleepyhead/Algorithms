import java.util.Comparator;
import java.util.Date;

/**3.4.25 Caching, maintain an instance variable hash, so that hashCode() can save the hash value the first time it is called
 * for each object, and subsequent calls on hashCall() simply return the value of hash.*/

public class Transaction implements Comparable<Transaction>{
    private final String who;
    private final Date when;
    private final double amount;
    private int hash=-1;

    /**
     * Initializes a new transaction from the given arguments
     * */
    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    /**
     * Initializes a new transaction by parsing a string of the form NAME DATE AMOUNT
     * */
    public Transaction(String transaction){
        String[] a = transaction.split("\\s+");
        who=a[0];
        when = new Date(a[1]);
        amount=Double.parseDouble(a[2]);
        if(Double.isNaN(amount)||Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        }
    }

    public int hashCode(){
        if(this.hash==-1) { // first time call hashCode()
            int h = 1;
            h = 31 * h + who.hashCode();
            h = 31 * h + when.hashCode();
            h = 31 * h + ((Double) amount).hashCode();
            hash = h; // store in the field
        }
        return hash;
    }
    @Override
    public boolean equals(Object other){
        if(other==this) return true;
        if(other==null) return false;
        if(other.getClass()!=this.getClass()) return false;
        Transaction that = (Transaction) other;
        return (this.amount== that.amount) && (this.who.equals(that.who)) && (this.when.equals(that.when));
    }
    @Override
    public int compareTo(Transaction that) {
        return Double.compare(that.amount, that.amount);
    }

    // compare two transactions by customer's name
    public static class WhoOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w){
            return v.who.compareTo(w.who);
        }
    }
    // compare two transactions by date
    public static class WhenOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w){
            return v.when.compareTo(w.when);
        }
    }
    // compare two transactions by amount
    public static class AmountOrder implements Comparator<Transaction>{
        @Override
        public int compare(Transaction v, Transaction w){
            return Double.compare(v.amount,w.amount);
        }
    }


}
