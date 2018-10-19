package midterm2018;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import java.io.BufferedReader;
public class Main {

    public static void main(String[] args) {
        String question1and2 = "GET /add?a=3&b=4 HTTP/1.1\n"
            + "Host: localhost:1298\n"
            + "Connection: keep-alive\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\n"
            + "Accept: image/webp,image/apng,image/*,*/*;q=0.8\n"
            + "Referer: http://localhost:1298/\n"
            + "Accept-Encoding: gzip, deflate, br\n"
            + "Accept-Language: en-US,en;q=0.9,es;q=0.8\n";

        String question3 = "{\n"
            + "    “task” : “inc”,\n"
            + "    “num” : 3\n"
            + "}\n";

        String question4and5 = "To opt out, you’ll need to enter the Settings menu by clicking the three vertical dots, all the way in the upper right corner of the browser. From there, you’ll need to enter the Advanced settings at the very bottom and find the “Allow Chrome sign in” toggle, then turn it to off. Doing so lets you sign into Google services like Gmail and Maps without signing into the Chrome browser itself.";


        //System.out.println(question1and2);
        //System.out.println(question3);
        //System.out.println(question4and5);

        // print each answer at the end

        // Question 1
        // Return the Host       "localhost:1298"
        String[] split = question1and2.split("\\r?\\n");
        String[] split1;
        String answer1 ="";
        for (int i =0; i < split.length; i++){
            if (split[i].startsWith("Host")){
                split1 = split[i].split(": ");
                answer1 = split1[1];
            }
        }
        System.out.println(answer1);
        //-----------------------------------------------------------------------------------------------------

        // Question 2
        // return sum of a and b       add?a=3&b=4  --> 7 extract a, b
        String[] split2;
        int a = 0,b = 0;
        for (int i =0; i < split.length; i++){
            if (split[i].startsWith("GET")){
                split2 = split[i].split(" /|\\?|&| ");
                for (int j=0; j<split2.length; j++){
                    if (split2[j].startsWith("a")&&(!split2[j].equalsIgnoreCase("add"))){
                        String[] num = split2[j].split("=");
                        a = Integer.parseInt(num[1]);
                    }
                    else if (split2[j].startsWith("b")){
                        String[] num = split2[j].split("=");
                        b = Integer.parseInt(num[1]);
                    }
                }
            }
        }
        System.out.println(a+b);
        //-----------------------------------------------------------------------------------------------------

        // Question 3
        // convert to java object, increment num, convert back to json and return       num+1 --> return jason
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(question3, map.getClass());
        double num = (double)map.get("“num”");
        num = num + 1;
        map.put("“num”", num);
        
        System.out.println(map);
        //-----------------------------------------------------------------------------------------------------

        // Question 4
        // return unique words        how many unique words
        String[] words = question4and5.split(", |\\. | “|” | ");
        Map<String, Integer> occurrences = new HashMap<String, Integer>();

        for ( String word : words ) {
            Integer oldCount = occurrences.get(word);
            if ( oldCount == null ) {
                oldCount = 0;
            }
            occurrences.put(word, oldCount + 1);
        }
        System.out.println(occurrences.size());
        //-----------------------------------------------------------------------------------------------------

        // Question 5
        // return 2nd most common word        return a word
        int first, second;
        String second_largest ="";
        first = second = 0;
        for ( String word : occurrences.keySet() ) {
            if (occurrences.get(word) > first)
            {
                second = first;
                first = occurrences.get(word);

            }
            else if (occurrences.get(word) > second && occurrences.get(word) != first) {
                second = occurrences.get(word);
            }
        }
        for (String word : occurrences.keySet()) {
            //System.out.println(word);
            if (occurrences.get(word).equals(second)) {
                second_largest = word;
            }
        }
        System.out.println(second_largest);
    }
}
