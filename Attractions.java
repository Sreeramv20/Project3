package com.company;

import java.io.*;
import java.util.Hashtable;

public class Attractions {
    static Hashtable<String,String> Attractions= new Hashtable<>();
   //This reads the attractions from the csv file.
    public void readAttractions(String name) throws IOException {

        File file = new File(name);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        int city1Num = 0;
        while (line != null) {
            String[] fields = line.split(",");
            Attractions.put(fields[0],fields[1]);
            line = br.readLine();
        }
        br.close();
    }
    //getter for the attractions hashmap
    public Hashtable<String, String> getAttractions(){
        return Attractions;
    }

}
