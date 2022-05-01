//package labProject;

import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);

        String filePath= "C:\\Users\\user\\IdeaProjects\\ICS202\\src\\labProject\\dictionary.txt";
        Dictionary<String> dd = new Dictionary<>(filePath);
        Boolean running= true;
        String word, meaning;
        int chice = 0;
        int runTime=0;
        String choice;
        System.out.println(
                "Choose one option from the following:\n" +
                        "1.\tInsert a new word with its meanings\n" +
                        "2.\tSearch for a word\n" +
                        "3.\tDelete a word and its meanings\n" +
                        "4.\tModify the meanings of a word\n" +
                        "5.\tPrint all words with a given suffix and their meanings\n" +
                        "6.\tPrint the contents of the dictionary sorted in lexicographic order\n" +
                        "7.\tExit\n"
        );
        while (running) {
            System.out.print("Enter integer number match your choice #to Exit enter 7 :");
            choice = scanner.next();
            if (choice.equals("1")) {
                System.out.println("Enter the word: ");
                word = scanner1.nextLine();
                System.out.println("Enter the meaning:");
                meaning = scanner1.nextLine();
                dd.insert(new WordPair(word.toLowerCase(), meaning.toLowerCase()));
                runTime++;
            } else if (choice.equals("2")) {
                word = scanner1.nextLine();
                dd.find(word);
            } else if (choice.equals("3")) {
                System.out.println("Enter the word you want to delete: ");
                word = scanner1.nextLine();
                dd.delete(word);
                runTime++;
            } else if(choice.equals("4")){
                System.out.println("enter a word:");
                word=scanner1.nextLine();
                System.out.println("enter the new meaning:");
                meaning=scanner1.nextLine();
                dd.modifyWord(word.toLowerCase(),meaning.toLowerCase());
                runTime++;
            } else if(choice.equals("5")){
                System.out.println("enter a prefix:");
                word=scanner1.nextLine();
                dd.printAll(word);
            } else if(choice.equals("6")){
                    dd.printSorted();
            } else if (choice.equals("7")) {
                    running = false;
            } else System.out.println("out of range Input, try Again"+"\nTry again!:");
            System.out.println("Exited successfully. #Bye bye");
        }
        if(runTime>0);
        dd.DictionaryClose();

    }
}
