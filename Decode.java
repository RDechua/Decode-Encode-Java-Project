import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

class Tree{
    class TreeNode{
        String value;
        TreeNode left;
        TreeNode right;

        TreeNode(String v){
            value = v;
            left = null;
            right = null;
        }

        String value(){
            return value;
        }
    }
    TreeNode root;
    String string = "";

    public Tree() {
        root = null;
    }

    public void make(String args0, String args1){ // Makes and writes the result into the output file
        String[] book = codebook();
        TreeNode root = new TreeNode("");
        for(int i = 0 ; i < book.length ; i+=2){ // Create the binary tree from codebook
            add(root, book, i, 0);
        }
        String binary = scan(args0);
        print(root, binary, 0, root);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(args1, true));
            writer.append(string);
            writer.close();
        }catch(IOException e){
            System.err.println("ERROR");
        }
    }

    public void print(TreeNode root, String binary, int index, TreeNode top){ // Makes final string
        if(index <= binary.length()){
            if(root.value().equals("")){
                if(binary.charAt(index) == '0'){ // Utilize the binary tree
                    print(root.left, binary, index+1, top);
                }else{
                    print(root.right, binary, index+1, top);
                }
            }else{
                if((char)Integer.parseInt(root.value()) != (char)4){ // If EOT isn't reached
                    string+= ((char)Integer.parseInt(root.value())); // Appends string with characters
                    if(index == binary.length()){ // If end is reached
                        return; 
                    }else{
                        root = top; // Resets root for next character
                        print(root, binary, index, top);
                    }
                }
            }
        }else{
            return;
        }
    }

    public void add(TreeNode top, String[] book, int index, int charind){ // Creates the binary tree
        if(!(charind >= book[index+1].length())){
            TreeNode empty = new TreeNode(""); // Fillers
            TreeNode n = new TreeNode(book[index]);
            if(charind < (book[index+1].length())-1){ // When end of huffman code is not reached
                if((book[index+1].charAt(charind)) == '0'){
                    if(top.left == null){ // Fills empty nodes with fillers
                        top.left = empty;
                    }
                    add(top.left, book, index, charind+1); // Fills nodes with filler until end is reached
                }else if((book[index+1].charAt(charind)) == '1'){
                    if(top.right == null){
                        top.right = empty;   
                    }
                    add(top.right, book, index, charind+1);
                }
            }else if(charind+1 == book[index+1].length()){ // When end of huffman code is reached and assigns the node with its value
                if((book[index+1].charAt(charind)) == '0'){
                    top.left = n;
                }else if((book[index+1].charAt(charind)) == '1'){
                    top.right = n;
                }
            }   
        }else{
            return;
        }
    }

    public String[] codebook(){ // Changes codebook to list of String
        int x = 0;
        int lines = 0;
        try{
            File myObj = new File("codebook"); 
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                myReader.nextLine();
                lines++;
            }
            myReader = new Scanner(myObj);
            String[] book = new String[lines*2];
            while(myReader.hasNextLine()){
                String[] temp = myReader.nextLine().split(":");
                book[x] = temp[0]; // Index 0 and evens contains char value
                book[x+1] = temp[1]; // Odd indexes contains its huffman code
                x+=2;
            }
            myReader.close();
            return book;
        }catch(FileNotFoundException e){
            System.err.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }

    public String scan(String f){
        try{
            File myObj = new File(f); 
            Scanner myReader = new Scanner(myObj);
            String binary = "";
            binary = myReader.nextLine();
            myReader.close();
            return binary;
        }catch(FileNotFoundException e){
            System.err.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }
}

class Decode {
    static public void main(String[] args) throws Exception {
		if(args.length == 2){
            Tree myTree = new Tree();
            myTree.make(args[0], args[1]);
        }else{
            System.err.println("ERROR");
        }
        
        
        
    }
}
