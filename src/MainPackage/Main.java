package MainPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);

        System.out.println(search("Hello","Hello"));

        // \d matching any 3 digits from 0 to 9
        System.out.println(search("Agent \\d\\d\\d", "Agent 008"));


        //First task
        String landingText = "abcd2023111111102023";
        String input = scan.nextLine();
        if (match(input, landingText)) {
            System.out.println("Updated text : "+changeText(input,"2023","HappyNewYear"));
        }

        System.out.println("\nSecond task : ");
//        Writing the int in the file
//        writeTheFile("D:\\Java Projects\\Regex\\src\\MainPackage\\TestingText1.txt");
//
//        Reading the file
//        ArrayList<Integer> arrayList = readFile("D:\\Java Projects\\Regex\\src\\MainPackage\\TestingText1.txt");
//        for (int i = 0; i < arrayList.size(); i++) {
//            System.out.print(arrayList.get(i) +" ");
//        }
//
//        printPositiveNumbers(arrayList);


        //The following code block broken into pieces :
        String text = readFile("D:\\\\Java Projects\\\\Regex\\\\src\\\\MainPackage\\\\TestingText1.txt");
        List<Integer> numbers = extractNumbers(text);
        printNumbers(numbers);
//        System.out.println(printNumbers(extractNumbers(readFile("D:\\\\Java Projects\\\\Regex\\\\src\\\\MainPackage\\\\TestingText1.txt"))).toString());


        //Third task
        System.out.println("\nThird task :\n");
        String doubleText = readFile("D:\\Java Projects\\Regex\\src\\MainPackage\\Doubles.txt");
        List<Double> doubleNumbers = extractDoubles(doubleText);
        String outputText = printNumbers(doubleNumbers);
        System.out.println(outputText);

        System.out.println("\nSum of numbers : "+sumOfNumbers(doubleNumbers));
        getMax(doubleNumbers);

        //b) change each decimal into the word Number
        System.out.println("Changed text : \n"+changeText(outputText,"[+-]?(\\d+\\.?\\d*|\\d*\\.\\d+)","Number"));


        //4th task
        System.out.println("\n4th task: \n");
        List<String> colorHEX = createHEXColorArray(8);
        System.out.println();
        validateColor(colorHEX);

        //b)
        System.out.print("b) Enter a date : ");
        String date = scan.nextLine();
        System.out.println("Is a dd/mm/yy format : "+isFormatDDMMYY(date));

        //c)
        System.out.print("c) Enter an email : ");
        String email = scan.nextLine();
        System.out.print("Is  a valid email : "+isValidEmail(email));

        //d)
        System.out.print("d) Enter an IP address : ");
        String ipAddress = scan.nextLine();
        System.out.println("Is a valid IP Address : "+isValidIPAddress(ipAddress));
    }


    public static String search(String patternString, String matcherString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher match = pattern.matcher(matcherString);

        return match.find() + " : "+patternString + " | "+matcherString;
    }

    public static String changeText(String text, String partToChange, String partToChanegTo) {
        String changedText = text.replaceAll(partToChange,partToChanegTo);
        return changedText;
    }

    public static boolean match(String patternString, String matcherString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher match = pattern.matcher(matcherString);

        return match.find();
    }

//    public static void writeTheFile(String filePath) {
//
//        Random rnd = new Random();
//        File file = new File(filePath);
//        FileWriter fw = null;
//
//        int n = 100;
//        try {
//            fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//
//            int line;
//            while (n > 0) {
//                line = rnd.nextInt(-100,100);
//                bw.write(line + " ");
//                n--;
//            }
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(0);
//            throw new RuntimeException(e);
//        }
//    }

//    public static <T> ArrayList<T> readFile(String filePath) throws FileNotFoundException {
//        File file = new File(filePath);
//        ArrayList<T> arrayList = new ArrayList<>();
//
//        Scanner scan = new Scanner(file);
//        while (scan.hasNext()) {
//            arrayList.add((T) scan.next());
//        }
//        return arrayList;
//    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private static List<Integer> extractNumbers(String text) {
        List<Integer> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            numbers.add(number);
        }

        return numbers;
    }

    private static <T> String printNumbers(List<T> numbers) {
        return numbers.stream().map(Object::toString).collect(Collectors.joining(","));
    }


    //Better printing method, tho the first is better
    public static String printNumbers1(List<Integer> numbers) {
        String output = "";

        for (int i = 0; i < numbers.size(); i++) {
            output += numbers.get(i)+",";
        }

        return output;
    }


    //This method takes a lot of time
    private static StringBuilder printNumbers2(List<Integer> numbers) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numbers.size(); i++) {
            output.append(numbers.get(i));
            if (i < numbers.size() - 1) {
                output.append(",");
            }
        }

        return output;
    }

//    public static void printPositiveNumbers (ArrayList<Integer> arrayList) {
//        for (int i = 0; i < arrayList.size(); i++) {
//            if (arrayList.get(i) > 0) {
//                System.out.println(arrayList.get(i)+",");
//            }
//        }
//    }

    private static List<Double> extractDoubles(String text) {
        List<Double> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("[+-]?(\\d+\\.?\\d*|\\d*\\.\\d+)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            double number = Double.parseDouble(matcher.group().replace(",", "."));
            numbers.add(number);
        }

        return numbers;
    }

    public static double sumOfNumbers(List<Double> array) {
        double sum = 0.0;
        for (double number : array) {
            sum += number;
        }

        return sum;
    }

    public static void getMax(List<Double> array) {
        double maxNumber = Double.MIN_VALUE;
        int i = 0;

        for (double number : array) {
            if (number > maxNumber) {
                maxNumber = number;
                i = array.indexOf(number);
            }
        }

        System.out.println("Max number : "+maxNumber+", its ordinal number : "+i);
    }

    public static List<String> createHEXColorArray(int numberOFHEXColors) {
        Scanner scan= new Scanner(System.in);
        List<String> HEXColorArray = new ArrayList<>();

        while (numberOFHEXColors > 0) {
            System.out.print("Enter the HEX color : ");
            String HEXColor = scan.nextLine();
            HEXColorArray.add(HEXColor);
            numberOFHEXColors--;
        }

        return HEXColorArray;
    }

    public static void validateColor(List<String> HEXColorArray) {
        Pattern pattern = Pattern.compile("#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})");
        for (String color : HEXColorArray) {
            Matcher match = pattern.matcher(color);
            if (match.matches()) {
                System.out.println(color+" : Valid");
            } else {
                System.out.println(color+" : Not valid");
            }
        }
    }

    public static boolean isFormatDDMMYY (String date) {
        Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((?:16|17|18|19|20)\\d{2}|9999)$");
        Matcher match = pattern.matcher(date);
        return match.matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b$");
        Matcher match = pattern.matcher(email);
        return match.matches();
    }

    public static boolean isValidIPAddress(String ipAddress) {
        Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher match = pattern.matcher(ipAddress);
        return match.matches();
    }
}
