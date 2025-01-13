package ex2;
// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    private int order;
    private String CellName;

    public SCell(String s, String g) {
        setData(s);
        this.CellName = g;
    }
    public String getName(){
        return this.CellName;
    }

    @Override
    public int getOrder() {
        // Add your code here

        return 0;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
public void setData(String s) {
        line = s;
        setType(whichType(s));
        //type= whichType(s);
    }


    @Override
    public String getData() {
        return line;
    }


    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here

    }
    public static boolean isNumber(String text) { // checks if the given string is Number
        try {
            Double.parseDouble(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isForm(String text) { //checks if the given String  in form
        if (text == null || text.isEmpty() || text.charAt(0) != '=') {
            return false;
        }
        if (!validateOperators(text)) {
            return false;
        }

        boolean ans = checkFormat(text.substring(1));
        return ans;


    }

    private static boolean checkFormat(String str) { // specific format
        String regex = "0123456789";
        String a = "()";
        String opertors = "*+/-.";
        int b = 0;
        if ("/*)".contains(String.valueOf(str.charAt(0)))) {

        }
        for (int i = 0; i < str.length(); i++) {
            String temp = String.valueOf(str.charAt(i));
            if (!regex.contains(temp) && !opertors.contains(temp) && !a.contains(temp)) {
                if (Character.isLetter(str.charAt(i)) && i + 1 < str.length() && Character.isDigit(str.charAt(i +1))) {
                    continue;
                }

                return false;
            }
//
            if (str.charAt(i) == '(') {
                b++;
            }
            if (str.charAt(i) == ')') {
                b--;
            }
        }
        if (b != 0) {
            return false;
        }
        return true;
    }

    private static boolean validateOperators(String str) {
        String operators = "+-*/";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (operators.indexOf(c) != -1) { // if its an operator
                // make sures there is a number ot "()"
                if (i == 0 || i == str.length() - 1) return false; //
                char prev = str.charAt(i - 1);
                char next = str.charAt(i + 1);
                if (!Character.isDigit(prev) && prev != ')' || !Character.isDigit(next) && next != '(') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean IsText(String JustText) {
        if(!isForm(JustText)|| !isNumber(JustText)) {
            String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHI";
            String Numbers = "1234567890";
            String Operators = "=*/+-().";

            try {
                for (int i = 0; i < JustText.length(); i++) {
                    String Temp = String.valueOf(JustText.charAt(i));
                    if (!letters.contains(Temp) && !Numbers.contains(Temp) && !Operators.contains(Temp)) {
                        return false;
                    }
                    return true;
                }

            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
    private int whichType(String s) {


        if (isNumber(s)){
            return 2;
        }
        else if (isForm(s)){

            return 3;
        }
       else if (IsText(s)){


            return 1;
        }
        return -2;
    }

}
