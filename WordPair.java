//package labProject;

public class WordPair <T extends Comparable<T>>{
    protected T word;
    protected T meaning;
    protected WordPair<T> left, right;


    public WordPair() {
        left = right = null;
    }

    public WordPair(T word,T meaning) {
        this(word, meaning,null,null);
    }

    public WordPair(T word,T meaning, WordPair<T> left, WordPair<T> right) {
        this.word = word;
        this.meaning= meaning;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return word+" "+meaning;
    }
}
