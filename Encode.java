import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;


class Code{
    Code(String f1, String f2){
        HashMap<String, String> book = codebook();
        String[] arr1 = scan(f1); // Input file
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f2, true));
            for(int i = 0 ; i < arr1.length ; i++){
                if(book.get(arr1[i]) != null){
                    writer.append(book.get(arr1[i])); // Write the huffman code for each character
                }
            }
            writer.close();
        }catch(IOException e){
            System.err.println("ERROR");
        }
    }

    public String[] scan(String f){ // Changes chars in text to list of string  
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
            int c;
            int lines = 0;
            while ((c = reader.read()) != -1) {
                lines++;
            }
            reader = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
            String[] nums = new String[lines+1];
            for(int i = 0 ; i < lines ; i++) {
                c = reader.read();
                if (c == '\n') {
                    nums[i] = Integer.toString((int) '\n');
                } else {
                    nums[i] = Integer.toString((int) c);
                }
            }
            nums[nums.length-1] = "4"; // Adds EOT
            reader.close();
            return nums;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public HashMap<String, String> codebook(){ // Changes codebook to list of int
        int lines = 0;
        HashMap<String, String> codebook = new HashMap<String, String>();
        try{
            File myObj = new File("codebook"); // Reads file
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                myReader.nextLine();
                lines++;
            }
            myReader = new Scanner(myObj);
            String[] book = new String[lines];
            while(myReader.hasNextLine()){
                String[] temp = myReader.nextLine().split(":");
                codebook.put(temp[0], temp[1]); //Inserts char value and its huffman code into a hashmap
            }
            myReader.close();
            return codebook;
        }catch(FileNotFoundException e){
            System.err.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }
}

class Encode{
    static public void main(String[] args){
        if(args.length == 2){
            new Code(args[0], args[1]);
        }else{
            System.err.println("ERROR");
        }
        
    }
}