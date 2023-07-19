import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text);

    int calculateFrequencyForWord(String text, String word);

    List<WordFrequency> calculateMostFrequentNWords(String text, int n);
}

class Analyzer implements WordFrequencyAnalyzer {

    public int calculateHighestFrequency(String text) {
        List<WordFrequency> wfs = countWords(text);
        List<Integer> freqs = new ArrayList<Integer>();
        //obtain a list of frequencies from the WordFrequency list
        for (WordFrequency wf : wfs) {
            freqs.add(wf.getFrequency());
        }
        //Sort these frequencies in descending order
        //and return the first and heighest frequency
        Collections.sort(freqs, Collections.reverseOrder());
        return freqs.get(0);
    }

    public int calculateFrequencyForWord(String text, String word) {
        List<WordFrequency> wfs = countWords(text);
        for (WordFrequency wf : wfs) {
            if (wf.getWord().equals(word)) return wf.getFrequency();
        }
        return 0;
    }

    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        List<WordFrequency> wfs = countWords(text);
        List<Integer> freqs = new ArrayList<Integer>();
        List<WordFrequency> results = new ArrayList<WordFrequency>();
        //obtain a list of all frequencies in Descending order
        for (WordFrequency wf : wfs) {
            freqs.add(wf.getFrequency());
        }
        Collections.sort(freqs, Collections.reverseOrder());
        int i = 0;
        int f = freqs.get(i);
        //Find the WordFrequencies belonging to the 'n' highest frequencies
        //Since the Wordfrequency list is alphabetically the results will be too
        for (int j = 0; j < freqs.size(); j++) {
            if (wfs.get(j).getFrequency() == freqs.get(i)) {
                results.add(wfs.get(j));
                i++;
                if (freqs.get(i) != f) {
                    f = freqs.get(i);
                    j = -1;
                }

            }
            if (i == n) break;
        }

        return results;
    }

    //Count the words of given text, returns a list of WordFrequencies
    public List<WordFrequency> countWords(String text) {
        //Conver all characters to lower case and split text into words
        text = text.toLowerCase();
        String[] words = text.split("[^a-zA-Z]");
        List<String> collection = new ArrayList<>(
                Arrays.asList(words));
        collection.removeAll(Arrays.asList(""));
        //Sort alphabetically
        Collections.sort(collection);
        String word = collection.get(0);
        int count = 1;
        List<WordFrequency> wfs = new ArrayList<WordFrequency>();
        for (int i = 1; i < collection.size(); i++) {
            if (!collection.get(i).equals(word)) {

                wfs.add(new TestClass(word, count));
                word = collection.get(i);
                count = 1;
            }
            else count++;
            if (i == collection.size() - 1)
                wfs.add(new TestClass(word, count));
        }
        return wfs;


    }

}

class Tester {
    public static void main(String[] args) {
        //String text = StdIn.readAll();
        String text = "This ,is. a :  very good, very 1  awesome and a very good testing Text";
        Analyzer a = new Analyzer();
        StdOut.println("The highest frequency is " + a.calculateHighestFrequency(text));
        String word = "a";
        int f = a.calculateFrequencyForWord(text, word);
        StdOut.println("The word: '" + word + "' occurs " + f + " times in the text");
        int n = 6;
        List<WordFrequency> results = a.calculateMostFrequentNWords(text, n);
        StdOut.println("The " + n + " most occurring words are:");
        StdOut.println(results);
    }
}
