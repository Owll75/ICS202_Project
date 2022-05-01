//package labProject;
import java.io.*;
import java.util.Scanner;

public class Dictionary <T extends Comparable<T>> extends BinaryTree<T> {
    protected WordPair<T> root;
    private String FilePath;
    String word, meaning;
    Scanner scanner;
    FileWriter writer;
    public Dictionary() {}
    public Dictionary(WordPair root) {
        this.root = root;
    }
    public Dictionary(String filePath) {
        this.FilePath=filePath;
        try {
            scanner =new Scanner(new File(FilePath));
            while (scanner.hasNext()){
                word=scanner.next();
                meaning=scanner.nextLine();
                insert(new WordPair(word,meaning));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
        finally {
            scanner.close();
        }
    }

    public void purge() {root = null;}
    public boolean isEmpty() {return root == null;}

    public int getSize(){
        //returns and displays how many words are currently stored in the dictionary
        return getSize(root);
    }
    private int getSize(WordPair wordPair){
        if (wordPair == null)
            return 0;
        else
            return 1+(getSize(wordPair.left)+ getSize(wordPair.right));
    }

    public boolean insert (WordPair wordpair){
        //inserts word pair into dictionary if not already present. Returns true if the insertion is successful; otherwise, it returns false if the word exists in the dictionary.
        WordPair<T> p = root, prev = null;
        while (p != null) {  // find a place for inserting new node;
            prev = p;
            int result = wordpair.word.compareTo(p.word);

            if(result == 0){
                System.out.println(wordpair.word+" is Duplicated ");
                return false;

            }

            else if (result < 0)
                p = p.left;
            else
                p = p.right;
        }
        if (root == null){
            root = new WordPair<T>((T)wordpair.word, (T)wordpair.meaning);
            return true;
        }
        else if (wordpair.word.compareTo(prev.word) < 0) {
            prev.left = new WordPair<T>((T) wordpair.word, (T) wordpair.meaning);
            return true;
        }
        else {
            prev.right = new WordPair<T>((T) wordpair.word, (T) wordpair.meaning);
            return true;
        }
    }

    public WordPair find(String word){
        //returns and displays a WordPair if the word is present; otherwise, it returns null and displays the message: "Word not in dictionary".
        WordPair<T> p = root;
        while (p != null)
            if (word.equals(p.word)) {
                System.out.println(p);
                return p;
            }
            else if (word.compareTo((String) p.word) < 0)
                p = p.left;
            else
                p = p.right;
        System.out.println("Word not in dictionary");
        return null;

    }

    public boolean delete(String word){
        //deletes the wordPair associated with word if the word exists. It returns true if the word exists; otherwise it returns false.
        WordPair<T> node, p = root, prev = null;
        while (p != null && !p.word.equals(word)) {  // find the node p
            prev = p;                           // with element el;
            if (word.compareTo((String) p.word) < 0)
                p = p.left;
            else
                p = p.right;
        }
        node = p;
        if (p != null && p.word.equals(word)) {
            if (node.right == null)             // node has no right child;
                node = node.left;
            else if (node.left == null)         // no left child for node;
                node = node.right;
            else {
                WordPair<T> tmp = node.left;    // node has both children;
                WordPair<T> previous = node;    // 1.
                while (tmp.right != null) {    // 2. find the rightmost
                    previous = tmp;            //    position in the
                    tmp = tmp.right;//    left subtree of node;
                }
                node.word = tmp.word;
                node.meaning=tmp.meaning;
                // 3. overwrite the reference
                //    to the element being deleted;
                if (previous == node)          // if node's left child's
                    previous.left  = tmp.left; // right subtree is null;
                else
                    previous.right = tmp.left; // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
            return true;
        }
        else if (root != null)
            throw new java.util.NoSuchElementException("el " + word + " is not in the dictionary");
        else
            throw new UnsupportedOperationException("the tree is empty");
    }

    public boolean modifyWord(String word, String newMeanings){
        //modifies a word to a new meaning, if the word exists in the dictionary. Returns true if the word exits; false otherwise.
        WordPair<T> p = root;
        while (p != null)
            if (word.equals(p.word)) {
                p.meaning= (T) newMeanings;
                return true;
            }
            else if (word.compareTo((String) p.word) < 0)
                p = p.left;
            else
                p = p.right;
        System.out.println("Word not in dictionary");
        return false;

    }

    public void printAll(String prefix){
        //prints all words that start with prefix, together with their meanings. Prints the message: "No word starts with this prefix" if no such word exists.
        WordPair<T> p = root;
        while (p != null) {
            if (((String) (p.word)).startsWith(prefix)) {
                System.out.println(p);
            }
            p = p.right;
        }
        if(p ==null)
            System.out.println("Word not in dictionary");

    }
    public void printSorted(){
//        //prints the words in the data structure together with their meanings in lexicographic order.
        inorderTraversal(root);
    }
    public String saveString(){
        return saveString(root,"",true);
    }
    private String saveString(WordPair wordPair,String str,boolean state){
        if (wordPair == null){
            state=false;
            return "";
        } else if(state)
            return saveString(wordPair.left, str, state)+wordPair.toString() + "\n"+saveString(wordPair.right, str, state);

        return "";
    }
    public void DictionaryClose(){

        try {
            writer =new FileWriter(new File((FilePath)));
            writer.write(saveString());
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


