/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unicycler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ljanssen
 */
public class UniCycler {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int k = 107; //Integer.parseInt(args[0]);
        double k1 = k+1;
        int dd = k-1;
        String d = Integer.toString(dd);
        
        BufferedReader br = new BufferedReader(new FileReader("/home/ljanssen/Documents/Unicycler_sample_data/Assembly.Unicycler/004_final_clean.gfa"));
        String line = null;
        
        
        
        //System.out.println("H	VN:Z:1.0");
        System.out.println("digraph adj {"+"\n"+"graph [k="+k+"]"+"\n"+"edge [d=-"+d+"]");
        while ((line=br.readLine()) != null) {
            String cols[] = line.split("\t");
            String recordType = cols[0];
            
            //System.out.println(line);
            if (recordType.equals("S")) {
                String id = cols[1];
                //System.out.println(id);
                String lengthwith = cols[3];
                //System.out.println(lengthwith);
                String length1 = lengthwith.replaceAll("LN:i:", "");
                //System.out.println(length1);
                long length = Long.parseLong(length1);
                
                
                String kmerwith = cols[4]; //k;
                //System.out.println(kmerwith);
                String kmerNumb = kmerwith.replaceAll("dp:f:", "");
                //System.out.println(kmerNumb);
                double kmer = Double.parseDouble(kmerNumb);
                //System.out.println(kmer);
                long KC = (long) Math.ceil(kmer * (length - kmer + 1));
                if (length < kmer){
                    KC = 0;
                }
                
                
                System.out.println(('"'+id+"+"+'"')+" "+"[l="+length1+" "+"C="+KC+"]"+"\n"
                                +('"'+id+"-"+'"')+" "+"[l="+length1+" "+"C="+KC+"]");
            }
            else if (recordType.equals("L")){
                String id2 = cols[1];
                String orientation = cols[2];
                String start = cols[3];
                String orientation2 = cols[4];
                String overlapwith = cols[5];
                
                String overlap = overlapwith.replaceAll("[^0-9]", "");
                System.out.println(('"'+id2+orientation+'"')+" "+"-> "+('"'+start+orientation2+'"')+" "+"\n"
                                        +'"'+start+flip(orientation2)+'"'+" -> "+'"'+id2+flip(orientation)+'"');
//                if(overlap.equals(d)){
//                    System.out.println(('"'+id2+orientation+'"')+" "+"-> "+('"'+start+orientation2+'"')+" "+"\n"
//                                        +'"'+start+"-"+'"'+" -> "+'"'+id2+"-"+'"');
//                }
//                else {
//                    System.out.println(('"'+id2+orientation+'"')+" "+"-> "+('"'+start+orientation2+'"')+" "+"[d="+overlap+"]"+"\n"
//                                        +'"'+start+"-"+'"'+" -> "+'"'+id2+"-"+'"'+"[d="+overlap+"]");
//                }

            }
            
        }
        System.out.println("}");

    }
    
    public static String flip(String orientation){
        if (orientation.equals("+")){
            return "-";
        }
        else if (orientation.equals("-")){
            return "+";
        }
        return orientation;
        
        
    }
   
    
    

    
}
