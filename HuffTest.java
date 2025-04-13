import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Check{
    Check(String f1, String f2){
        String[] book = codebook();
        /** Test Codebook
        String[] book = {Integer.toString((int) 'a'),
        Integer.toString((int) 's'),
        Integer.toString((int) 'd'),
        Integer.toString((int) 'f'),
        Integer.toString((int) 'g'),
        Integer.toString((int) 'h'),
        Integer.toString((int) 'j'),
        Integer.toString((int) 'k'), 
        Integer.toString((int) ' '), 
        Integer.toString((int) '\n')};
        */
        String[] arr1 = scan(f1); // Input file
        String[] arr2 = scan(f2); // Output file
        int v1 = 0; // index for Input file
        int v2 = 0; // index for Input file
        boolean fail = false;
        boolean check = false; // Any end is reached
        try{
            while(v1 < arr1.length && v2 < arr2.length && !fail){ // Loops not fail and has not reach any end
                if(contain((arr1[v1]),book) && contain((arr2[v2]),book)){
                    if(check){ // If end is reach and no match is found
                        fail = true;
                        break;
                    }
                    if(arr1[v1].equals(arr2[v2])){ // If match is found
                        if(v1+1 == arr1.length && v2+1 == arr2.length){
                            v1++;
                            v2++;
                        }else if(v1+1 == arr1.length && !(v2+1 == arr2.length)){
                            v2++;
                            check = true;
                        }else if(v2+1 == arr2.length && !(v1+1 == arr1.length)){
                            v1++;
                            check = true;
                        }else{
                            v1++;
                            v2++;
                            check = false;
                        }
                    }else{
                        fail = true;
                        break;
                    }
                }else if(v1+1 == arr1.length || v2+1 == arr2.length){ // If end is reached but no match was found for a char
                    if((contain((arr1[v1]),book) || contain((arr2[v2]),book)) && !check){
                        check = true;
                        break;
                    }
                    check = false;
                    break;
                }else if(!contain((arr1[v1]),book) && contain((arr2[v2]),book)){
                    v1++;
                }else if(!contain((arr2[v2]),book) && contain((arr1[v1]),book)){
                    v2++;
                }else{
                    v1++;
                    v2++;
                }
            }  
            if(fail || check){
                failcheck(arr1, arr2, v1, v2);
            }else{
                System.out.println("PASS");
            }
        }catch(IndexOutOfBoundsException e){
            System.err.println("ERROR");
        }
    }

    public void failcheck(String[] arr1, String[] arr2, int v1, int v2){
        if((arr1[v1].equals("10"))){
            System.out.println("FAIL input " + "\\n" + " @ " + v1 + " output " + (char)Integer.parseInt(arr2[v2]) + " @ " + v2);
        }else if((arr2[v2].equals("10"))){
            System.out.println("FAIL input " + (char)Integer.parseInt(arr1[v1]) + " @ " + v1 + " output " + "\\n" + " @ " + v2);
        }else{
            System.out.println("FAIL input " + (char)Integer.parseInt(arr1[v1]) + " @ " + v1 + " output " + (char)Integer.parseInt(arr2[v2]) + " @ " + v2);
        }
    }

    public boolean contain(String val, String[] list){
        for(int i = 0 ; i < list.length ; i++){
            if(list[i].equals(val)){
                return true;
            }
        }
        return false;
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
            String[] nums = new String[lines];
            for(int i = 0 ; i < lines ; i++) {
                c = reader.read();
                if (c == '\n') {
                    nums[i] = Integer.toString((int) '\n');
                } else {
                    nums[i] = Integer.toString((int) c);
                }
            }
            reader.close();
            return nums;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] codebook(){ // Changes codebook to list of String
        int i = 0;
        int lines = 0;
        try{
            File myObj = new File("codebook"); 
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                myReader.nextLine();
                lines++;
            }
            myReader = new Scanner(myObj);
            String[] book = new String[lines];
            while(myReader.hasNextLine()){
                String[] temp = myReader.nextLine().split(":");
                book[i] = temp[0];
                i++;
            }
            
            myReader.close();
            return book;
        }catch(FileNotFoundException e){
            System.err.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }
}

class HuffTest{
    static public void main(String[] args){
        if(args.length == 2){
            new Check(args[0], args[1]);    
        }else{
            System.err.println("ERROR");
        }
    }
}