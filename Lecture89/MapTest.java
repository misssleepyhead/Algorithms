import edu.princeton.cs.algs4.ST;

public class MapTest {
    public static void main(String[] args) {
        ST<Character, Integer> st = new ST<Character,Integer>();
        String str = "SEARCHEXAMPLE";
        for(int i=0;i<str.length();i++){
            Character c = str.charAt(i);
            st.put(c,i);
        }

        for(Character c:st.keys()){
            System.out.println(c);
        }
    }
}
