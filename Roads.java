package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Roads extends WeightedGraph{
    static Hashtable<String,Integer>cityToNum= new Hashtable<>();
    Graph graph= new Graph(300);
    static Hashtable<Integer,String>numToCity=new Hashtable<>();
    //Reads the roads csv and stores them onto the Hashmaps for cityToNum and numToCity
    public void read_csv_file(String filename) throws Exception {
        int i=0;
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        int city1Num=0;

        while(line!=null){
            String[]fields = line.split(",");
            String city1 = fields[0];
            String city2 = fields[1];

            if(!cityToNum.containsKey(city1)){
                cityToNum.put(city1,city1Num++);
                numToCity.put(city1Num-1,city1);

            }
            if(!cityToNum.containsKey(city2)){
                cityToNum.put(city2,city1Num++);
                numToCity.put(city1Num-1,city2);
            }
            //adds the cities the with the weights to the graph.
            graph.addEdge(cityToNum.get(city1),cityToNum.get(city2),Integer.parseInt(fields[2]));
            line = br.readLine();
        }
        br.close();
    }
    public Hashtable<String,Integer> getCityToNum(){
        return cityToNum;
    }
    public Hashtable<Integer,String> getNumToCity(){
        return numToCity;
    }
    public Graph returnGraph(){
        return graph;
    }
    public int getTotalMiles(){
        return totalmiles;
    }
}