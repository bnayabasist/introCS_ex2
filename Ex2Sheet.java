package ex2;
import java.io.IOException;
import java.util.ArrayList;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    // Add your code here

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1) {
            for(int j=0;j<y;j=j+1) {
                table[i][j] = new SCell("");
            }
        }
        eval();
    }
    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }

    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;
        // Add your code here

        Cell c = get(x,y);
        if(c!=null) {ans = eval(x,y);}

        /////////////////////
        return ans;
    }

    @Override
    public Cell get(int x, int y) {
        return table[x][y];
    }

    @Override
    public Cell get(String cords) {
        Cell ans = null;
        // Add your code here

        /////////////////////
        return ans;
    }

    @Override
    public int width() {
        return table.length;
    }
    @Override
    public int height() {
        return table[0].length;
    }
    @Override
    public void set(int x, int y, String s) {
        Cell c = new SCell(s);
        table[x][y] = c;

    }
    @Override
    public void eval() {
        int[][] dd = depth();
        // Add your code here

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) {
        boolean ans = xx>=0 && yy>=0;
//        if (xx > width())

        return ans;
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        // Add your code here

        // ///////////////////
        return ans;
    }

    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public void save(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        String exp = get(x, y).getData();
        if(get(x,y)!=null) {ans = get(x,y).toString();}

        if (SCell.isForm(exp)){
            double result = evaluateExpression(exp);
            return String.valueOf(result);
        }
        if (SCell.isNumber(exp)){
            double j =Double.parseDouble(exp);
            return String.valueOf(j);
        }
        if (SCell.IsText(exp)){
            return exp;
        }




        return ans;
        }
    public static double evaluateExpression(String expression) {
        // הסרת רווחים מיותרים מהביטוי
        expression = expression.replaceAll(" ", "");

        // רשימות לאחסון אופרנדים ואופרטורים
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        int i = 0;
        while (i < expression.length()) {
            char current = expression.charAt(i);

            // אם זה מספר, נקרא את כולו
            if (Character.isDigit(current) || current == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                values.add(Double.parseDouble(sb.toString()));
                continue; // נמשיך ללולאה הבאה (כבר הגדלנו את i)
            }

            // אם זה סוגריים פתוחים
            if (current == '(') {
                operators.add(current);
            }
            // אם זה סוגריים סגורים
            else if (current == ')') {
                // בצע חישובים עד שנסגור את הסוגריים
                while (!operators.isEmpty() && operators.get(operators.size() - 1) != '(') {
                    processOperation(values, operators);
                }
                operators.remove(operators.size() - 1); // הסר את '('
            }
            // אם זה אופרטור
            else if (isOperator(current)) {
                // בצע חישובים עבור אופרטורים עם קדימות גבוהה יותר
                while (!operators.isEmpty() &&
                        hasPrecedence(current, operators.get(operators.size() - 1))) {
                    processOperation(values, operators);
                }
                operators.add(current);
            }
            i++;
        }

        // בצע חישובים על כל מה שנותר
        while (!operators.isEmpty()) {
            processOperation(values, operators);
        }

        // הערך האחרון ברשימה הוא התוצאה
        return values.get(0);
    }

    // פונקציה שבודקת האם תו הוא אופרטור
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // פונקציה שבודקת קדימות בין אופרטורים
    private static boolean hasPrecedence(char current, char previous) {
        if (previous == '(' || previous == ')') {
            return false;
        }
        return (current == '+' || current == '-') && (previous == '*' || previous == '/');
    }

    // פונקציה שמבצעת חישוב של אופרטור בין שני ערכים
    private static void processOperation(ArrayList<Double> values, ArrayList<Character> operators) {
        if (values.size() < 2 || operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        double b = values.remove(values.size() - 1); // ערך שני
        double a = values.remove(values.size() - 1); // ערך ראשון
        char operator = operators.remove(operators.size() - 1); // האופרטור

        double result = applyOperator(a, b, operator);
        values.add(result);
    }

    // פונקציה שמבצעת פעולה מתמטית בין שני ערכים
    private static double applyOperator(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }


    }
}
