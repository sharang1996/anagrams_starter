package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    HashSet<String> wordSet = new HashSet<>();
    HashMap<String,ArrayList<String>> lettersToWord = new HashMap<>();
    ArrayList<String> wordlist;

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordSet.add(word);

           if( lettersToWord.containsKey(getAlphabetical(word)) )
           {
               ArrayList<String> str = lettersToWord.get(getAlphabetical(word));
               str.add(word);
               lettersToWord.put(getAlphabetical(word), str);
           }
           else
           {
              ArrayList<String> str = new ArrayList<>();
               str.add(word);
               lettersToWord.put(getAlphabetical(word),str);
           }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(getAlphabetical(word)) && !word.contains(base))  //(wordSet.contains(getAlphabetical(word)) && word.contains(base)==false)

        {

            Log.e(getClass().getSimpleName(),"true");

               return true;
        }
        else {
            Log.e(getClass().getSimpleName(),"false");
            return false;

        }
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> allCombinations = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<>();
        for(char c=97;c<=122;c++)
        {
            String n = word + c;
            allCombinations.add(getAlphabetical(n));
            if(lettersToWord.containsKey(getAlphabetical(n)))
            {
                ArrayList<String> anagram = new ArrayList<>();
                anagram=lettersToWord.get(getAlphabetical(n));

                for(String a:anagram)
                {
                    result.add(a);
                }
            }
        }

        return result;
    }


    //this is our hash function to be applied to all words...
    public String getAlphabetical(String unSortedString)
    {

        char unSortedCharArray[] = new char[unSortedString.length()];

        //Copy contents of the String to Char Array for easy manipulations
        for(int i=0;i<unSortedString.length();i++)
            unSortedCharArray[i] = unSortedString.charAt(i);

        char temp;

        //Classic Bubble Sort - We are comparing the ASCII values here
        for(int j=0;j<unSortedString.length()-1;j++)
        {
            for(int k=0;k<unSortedString.length()-1-j;k++)
            {
                if(unSortedCharArray[k]>unSortedCharArray[k+1])
                {
                    temp=unSortedCharArray[k];
                    unSortedCharArray[k]=unSortedCharArray[k+1];
                    unSortedCharArray[k+1]=temp;
                }
            }
        }

        String sortedString = new String(unSortedCharArray);
        return sortedString;
    }


    public String pickGoodStarterWord() {
        return "foo";
    }
}
