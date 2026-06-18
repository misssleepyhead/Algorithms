import java.util.Arrays;

public class MyLCP {
    public String lrs(String s) {
        int n = s.length();

        String[] suffixes = new String[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i, n);
        }

        Arrays.sort(suffixes);
        String lrs = "";
        for (int i = 0; i < n - 1; i++) {
            // find lcp between adjacent suffixes in sorted order
            int len = lcp(suffixes[i], suffixes[i + 1]);
            if (len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }
        return lrs;
    }

    private static int lcp(String s, String t){
        int n = Math.min(s.length(), t.length());
        for(int i=0;i<n;i++){
            if(s.charAt(i)!=t.charAt(i)){
                return i; // substring of s (0,i)
            }
        }
        return n;
    }
}
