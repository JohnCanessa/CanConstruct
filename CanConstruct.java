import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


/**
 * 
 */
public class CanConstruct {


    /**
     * Return a boolean indicating whether or not the
     * `target` can be constructed by concatenating elements of the
     * `wordBank` array.
     * You may reuse elements of `wordBank` as many times as needed.
     * 
     * Recursive call without memoization.
     * 
     * m = target.length
     * n = wordBank.length
     * 
     * Time: O(n^m * m)  Space: O(m^2)
     */
    static boolean canConstruct(String target, String[] wordBank) {

        // **** end cases(s) ****
        if (target.length() == 0) return true;

        // **** iterate through all the words in the word bank ****
        for (int i = 0; i < wordBank.length; i++) {

            // **** get the prefix from the word bank ****
            String prefix = wordBank[i];

            // **** check if this is a prefix in target ****
            if (target.indexOf(prefix) == 0) {

                // **** extract the suffix from target ****
                String suffix = target.substring(prefix.length());

                // **** make recursive call with the suffix ****
                if (canConstruct(suffix, wordBank))
                    return true;
            }
        }

        // **** cannot construct ****
        return false;
    }


    /**
     * Return a boolean indicating whether or not the
     * `target` can be constructed by concatenating elements of the
     * `wordBank` array.
     * You may reuse elements of `wordBank` as many times as needed.
     * 
     * Entry point with memoization.
     * 
     * m = target.length
     * n = wordBank.length
     * 
     * Time: O(n * m^2)  Space: O(m^2)
     */
    static boolean canConstructMemo(String target, String[] wordBank) {

        // **** sanity check(s) ****
        if (target.length() == 0) return true;

        // **** initialization ****
        HashMap<String, Boolean> memo = new HashMap<>();

        // **** recursive call with memoization ****
        return canConstructMemo(target, wordBank, memo);
    }


    /**
     * Return a boolean indicating whether or not the
     * `target` can be constructed by concatenating elements of the
     * `wordBank` array.
     * You may reuse elements of `wordBank` as many times as needed.
     * 
     * Recursive call with memoization.
     */
    static private boolean canConstructMemo(String target,
                                            String[] wordBank,
                                            HashMap<String, Boolean> memo) {

        // **** end cases(s) ****
        if (target.length() == 0) return true;
        if (memo.containsKey(target)) return memo.get(target);

        // **** iterate through all the words in the word bank ****
        for (int i = 0; i < wordBank.length; i++) {

            // **** get the prefix from the word bank ****
            String prefix = wordBank[i];

            // **** check if this is a prefix in target ****
            if (target.indexOf(prefix) == 0) {

                // **** extract the suffix from target ****
                String suffix = target.substring(prefix.length());

                // **** make recursive call with the suffix ****
                boolean ans = canConstructMemo(suffix, wordBank, memo);

                // **** insert key value pair into memo ****
                memo.put(target, ans);

                // **** check if done ****
                if (ans)
                    return true;
            }
        }

        // **** insert key value pair into memo ****
        memo.put(target, false);

        // **** cannot construct ****
        return false;
    }

    
    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** initialization ****
        boolean ans = false;
        long start  = 0;
        long end    = 0;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        // **** read target string ****
        String target = br.readLine().trim();

        // **** read String[] word bank ****
        String[] wordBank = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< target ==>" + target + "<==");
        System.out.println("main <<< wordBank: " + Arrays.toString(wordBank));

        // **** call function of interest and display result ****
        start = System.currentTimeMillis();
        ans = canConstruct(target, wordBank);
        end = System.currentTimeMillis();
        System.out.println("main <<< canConstruct: " + ans + " time: " + (end - start) + " ms.");
        

        // **** call function of interest and display result ****
        start = System.currentTimeMillis();
        ans = canConstructMemo(target, wordBank);
        end = System.currentTimeMillis();
        System.out.println("main <<< canConstructMemo: " + ans + " time: " + (end - start) + " ms.");
    }
}