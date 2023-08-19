package org.example;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Reader {

    //Implement the reading of a text file. You will turn the contents of the file
    //into a List of strings, which do not contain any white-space, where the
    //order of Strings in the list is a left-to-right; top-to-bottom ordering of the
    //“Words"

    static final String dataFile = "wordFile.txt";



    public static String Reversed(String str) {
        char[] Reversed = new char[str.length()];
        //Declare a stack type of character
        Stack<Character> stack = new Stack<Character>();

        //Traverse through the string and push the character one by one into the stack
        for (int i = 0; i < str.length(); i++) {
            //push each character into the stack
            stack.push(str.charAt(i));
        }

        //Now, pop the characters from the stack until it is empty

        int i = 0;
        while (!stack.isEmpty()) {
            //popping the element until the stack becomes empty.
            Reversed[i++] = stack.pop();
        }
        //return string object
        return new String(Reversed);
    }
    public static void main(String[] args) {

        String path = "C:\\CP2561 Java Programming II\\Project\\Project\\src\\main\\java\\org\\example\\wordFile.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String input;
            Map<String, String> wordDefinitions = new HashMap<>();
            while ((input = reader.readLine()) != null){

                String[] array = input.split(" ");

                Stack<Object> ObjStack = new Stack<>();

                boolean makeString = false;

                boolean makeDef = false;

                String Word = "";

                String code = "";

                Scanner scanInput = new Scanner(System.in);

                String string = "";

                for (int i = 0; i < array.length; i++) {
                    String arrayChar = array[i];

                    if (arrayChar.equals(":")) {
                        if (wordDefinitions.containsKey(Word)) {
                            String definition = wordDefinitions.get(Word);
                            execute(definition, wordDefinitions, ObjStack);
                        } else {
                            throw new RuntimeException("Error: Word " + Word + "Is not defined!");
                        }






//                    if (arrayChar.equals(":") && !makeDef){
//                        makeDef = true;
//                    } else if (makeDef && !arrayChar.equals(":")){
//                        if (Word.isEmpty()) {
//                            Word = arrayChar;
//                        } else {
//                            code = code.concat(" " + arrayChar);
//                        }
//                string = string.concat(" " + arrayChar);

                    } else if (makeDef) {
                        makeDef = false;
                        string = "";
                        System.out.println("Def: " + Word);
                        System.out.println("Code: " + code);
                        System.out.println("Definition " + Word + code);

                        wordDefinitions.put(Word, code);

                        Word = "";
                        code = "";

                    }




                System.out.println(string);

                    if( arrayChar.equals("'") && !makeString){
                        makeString = true;
                    } else if (makeString && !arrayChar.equals("'")){
                        string = string.concat(" " + arrayChar);

                    } else if (makeString){
                        ObjStack.push(string);
                        makeString = false;
                        string = "";

                    }
                    else {
                        if (arrayChar.equals("in")) {
                            String ScannedString = scanInput.next();
                            System.out.println(arrayChar);
                            ObjStack.push(ScannedString);
                        }
                        if (arrayChar.equals("*") || arrayChar.equals("+") || arrayChar.equals("dup") || arrayChar.equals("-") || arrayChar.equals("pop") || arrayChar.equals("swap") || arrayChar.equals("out"))  {
                            String Objclass = ObjStack.peek().getClass().getName();
                            System.out.println("Found stack operator: " + arrayChar);
                            if (Objclass.equals("java.lang.Integer")) {
                                if (arrayChar.equals("*")) {


                                    int num1 = Integer.parseInt(ObjStack.pop().toString());
                                    int num2 = Integer.parseInt(ObjStack.pop().toString());
                                    int num3 = num1 * num2;
                                    ObjStack.push(num3);

                                }
                                if (arrayChar.equals("+")) {
                                    int num1 = Integer.parseInt(ObjStack.pop().toString());
                                    int num2 = Integer.parseInt(ObjStack.pop().toString());
                                    int num3 = num1 + num2;
                                    ObjStack.push(num3);

                                }
                                if (arrayChar.equals("-")) {
                                    int num1 = Integer.parseInt(ObjStack.pop().toString());
                                    ObjStack.push(-num1);

                                }
                                if (arrayChar.equals("dup")) {
                                    int num1 = Integer.parseInt(ObjStack.pop().toString());
                                    ObjStack.push(num1);
                                    ObjStack.push(num1);

                                }
                                if (arrayChar.equals("swap")) {
                                    int num1 = Integer.parseInt(ObjStack.pop().toString());
                                    int num2 = Integer.parseInt(ObjStack.pop().toString());
                                    ObjStack.push(num1);
                                    ObjStack.push(num2);

                                }

                            } else {
                                if (arrayChar.equals("-")) {
                                    String str1 = String.valueOf(ObjStack.pop());
                                    ObjStack.push(Reversed(str1));

                                } else if (arrayChar.equals("+")) {
                                    String str1 = String.valueOf(ObjStack.pop());
                                    String str2 = String.valueOf(ObjStack.pop());
                                    String str3 = str2 + " " + str1;
                                    ObjStack.push(str3);
                                } else if (arrayChar.equals("*")) {
                                    String str1 = String.valueOf(ObjStack.pop());
                                    String str2 = String.valueOf(ObjStack.pop());
                                    System.out.println(str1);
                                    str1 = str1.replaceAll("\\s+", "");
                                    System.out.println(str2);
                                    str2 = str2.replaceAll("\\s+", "");
                                    String regex = str1; //regex to match the substring from the character
                                    Pattern pattern = Pattern.compile(regex);
                                    int k;
                                    for (k = 0; k < str2.length(); k++) {
                                        char regexChar = str2.charAt(k);
                                        Matcher matcher = pattern.matcher(String.valueOf(regexChar));
                                        if (matcher.matches()) {
                                            break;

                                        }
                                    }
                                    String substring = str2.substring(k);
                                    System.out.println(k);
                                    ObjStack.push(substring);


                                } else if (arrayChar.equals("dup")) {
                                    String str1 = String.valueOf(ObjStack.pop());
                                    ObjStack.push(str1);
                                    ObjStack.push(str1);
                                } else if (arrayChar.equals("swap")) {
                                    String str1 = String.valueOf(ObjStack.pop());
                                    String str2 = String.valueOf(ObjStack.pop());
                                    ObjStack.push(str1);
                                    ObjStack.push(str2);
                                } else if (arrayChar.equals("pop")) {
                                    String str1 = String.valueOf(ObjStack.pop());
                                    System.out.println(ObjStack.isEmpty());
                                }

                            }

                            if (arrayChar.equals("pop")) {
                                ObjStack.pop();

                            }
                            if (arrayChar.equals("out")) {
                                System.out.println(ObjStack.peek());
                            }




                        

                        } else if (!arrayChar.equals("in")){
                            ObjStack.push(Integer.parseInt(arrayChar));
                        } else if (arrayChar.equals(":")){
                            if (wordDefinitions.containsKey(Word)) {
                                String definition = wordDefinitions.get(Word);
                                ObjStack.push(definition);
                            } else {
                                throw new RuntimeException("Error: Word " + Word + " Is not defined.");
                            }
                        }
                        //do an if else here for the colon. Check to see if the word has already been defined, if it has
                        //execute, if not throw an execution.

                    }




                }



            }

        } catch (IOException e){
            e.printStackTrace();
        }





    }

    private static void execute(String code, Map<String, String> wordDefinitions, Stack<Object> ObjStack) {
        String[] array = code.split(" ");
        for (int i = 0; i < array.length; i++) {
            String codeChar = array[i];
        }
    }






}
