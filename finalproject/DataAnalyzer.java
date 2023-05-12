package finalproject;
//FINAL UPDATED
import java.util.ArrayList;

public abstract class DataAnalyzer {

    protected MyHashTable<String, MyHashTable<String, Integer>> nestedHash;
    protected MyHashTable<String, MyHashTable<String, Integer>> nestedHashTwo;

    Parser parser;

    public DataAnalyzer(Parser p) {
        parser = p;
        nestedHash = new MyHashTable();
        nestedHashTwo = new MyHashTable();
        extractInformation();
    }

    public abstract MyHashTable<String, Integer> getDistByKeyword(String keyword);

    public abstract void extractInformation();


}