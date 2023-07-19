interface WordFrequency {
    String getWord();

    int getFrequency();
}


class TestClass implements WordFrequency {
    String Word;
    int Frequency;

    //create new TestClass instance
    public TestClass(String word, int freq) {
        Word = word;
        Frequency = freq;
    }

    //obtain word part
    public String getWord() {
        return Word;
    }

    //obtain Frequency part
    public int getFrequency() {
        return Frequency;
    }

    //Convert to String for printing
    public String toString() {
        return "(\"" + Word + "\"," + Frequency + ")";
    }

}

class Test {
    public static void main(String[] args) {
        TestClass wf = new TestClass("jump", 2);
        System.out.print("the frequency of " + wf.getWord() + " is " + wf.getFrequency());

    }
}





