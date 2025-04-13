import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Test {
    static public void main(String[] args){
        /**
        char[] str1;
        int lines1 = 0;
        int i = 0;
        try{
            File Obj1 = new File("test1.txt");
            Scanner myReader = new Scanner(Obj1);
            while(myReader.hasNext()){
                myReader.next();
                lines1++;
            }
            myReader = new Scanner(Obj1);
            str1 = new char[lines1];
            while(myReader.hasNext()){
                str1[i] = myReader.next().charAt(0);
                System.out.println(str1[i]);
                i++;
                
            }
            myReader.close();
            
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        */
        //System.out.println((char)Integer.parseInt("15"));
        try (
        FileInputStream fis = new FileInputStream("test1.txt");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr)
        ) {

        String str = "";
        String s;
        while ((s = reader.readLine()) != null) {
            str += s;
        }
        
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        for(int i = 0; i < bytes.length ; i++){
            System.out.println(bytes[i]);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
