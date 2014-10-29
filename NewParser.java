package com.pvl.newparser;

import java.io.*;
import java.util.*;



public class NewParser {

   static Map<String, Integer> listOfWords = new HashMap<String, Integer>();

   public static void main(String[] args) {

        String fullBook = "";


        try (FileInputStream myFile = new FileInputStream("/home/pvl/Desktop/new3.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(myFile, "UTF8");
             BufferedReader reader = new BufferedReader(inputStreamReader);) {

            boolean eof = false;
            while (!eof) {
                fullBook += reader.read();
                if (reader.read() == -1) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        String[] words = fullBook.split(" ");

        for (String i : words) {
            fullBook = i.trim();
            fullBook = i.toLowerCase();

            processWord(i);

            listOfWords = sortByValue(listOfWords);

        }

       try {
           FileOutputStream myFile = new FileOutputStream("/home/pvl/Desktop/output.txt");
           Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));
           out.write(listOfWords.entrySet().toString());   //(String.valueOf(listOfWords));

       } catch (IOException e) {
           System.out.println(e);
       }
   }

        public static void processWord(String word) {

            for(int i = 0; i < listOfWords.size(); i++) {

                if (listOfWords.containsKey(word)) {
                    int value = listOfWords.get(word).intValue();
                    //value = value + 1;
                    listOfWords.put(word, (value + 1));

                } else {
                    listOfWords.put(word, 1);
                }
            }
        }

        public static Map sortByValue(Map unsortedMap){
            List list = new LinkedList(unsortedMap.entrySet());

            Collections.sort(list, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((Comparable) ((Map.Entry) (o1)).getValue())
                            .compareTo(((Map.Entry) (o2)).getValue());
                }
            });
            Map sortedMap = new LinkedHashMap();
            for (Iterator it = list.iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            return sortedMap;
        }




}
