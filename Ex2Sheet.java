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
                table[i][j] = new SCell("",Ex2Utils.ABC[j] + i);
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
        if(c!=null) {ans = eval(x,y);
            System.out.println(c.getType());
        }

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
         //Add your code here
//        if (cords == null || cords.isEmpty()) {
//            throw new IllegalArgumentException("Invalid cell reference: " + cords);
//        }
//
//        // הפרדת האותיות (עמודה) והמספרים (שורה)
//        String columnPart = cords.replaceAll("[^A-Z]", ""); // הוצאת אותיות בלבד
//        String rowPart = cords.replaceAll("[^0-9]", "");   // הוצאת מספרים בלבד
//
//        if (columnPart.isEmpty() || rowPart.isEmpty()) {
//            throw new IllegalArgumentException("Invalid cell reference format: " + cords);
//        }
//
//        int x = columnPart.charAt(0) - 'A'; // הפיכת אות לאינדקס עמודה (A -> 0, B -> 1)
//        int y = Integer.parseInt(rowPart) - 1; // הפיכת המספר לאינדקס שורה
//
//        if (!isIn(x, y)) {
//            throw new IndexOutOfBoundsException("Cell reference out of bounds: " + cords);
//        }
//
//        return get(x, y); // החזרת התא המתאים
//    }

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
        SCell a = (SCell) table[x][y];
        Cell c = new SCell(s,a.getName());
        table[x][y] = c;

    }
    @Override
    public void eval() {
        int[][] dd = depth();
        // Add your code here
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                Cell cell = get(i, j);
                if (cell.getData().startsWith("=")) {
                    if (dd[i][j] == Ex2Utils.ERR) {
                        cell.setType(Ex2Utils.ERR_CYCLE_FORM);
                    } else {
                        try {
                            eval(i, j);
                        } catch (Exception e) {
                            cell.setType(Ex2Utils.ERR_FORM_FORMAT);
                        }
                    }
                }
            }
        }

        // ///////////////////
    }

    @Override
    public boolean isIn(int xx, int yy) {
        boolean ans = xx>=0 && xx<width() && yy>=0 && yy<height();
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
        Cell cell = get(x,y);
        if(get(x,y)!=null) {ans = get(x,y).toString();}

        if (SCell.isForm(exp)){

                double result = evaluateExpression(exp.substring(1),0,cell); // חישוב ביטוי
                return String.valueOf(result);
            }

        if (SCell.isNumber(exp)){
            double j =Double.parseDouble(exp);
            return String.valueOf(j);
        }
        if (SCell.IsText(exp)){
            return exp;
        }


        return Ex2Utils.ERR_FORM;
    }
//

    public double evaluateExpression(String expression, int Recurse,Cell scell) {

        if (Recurse > width() * height()){
            return Ex2Utils.ERR_CYCLE_FORM;
        }
        // הסרת רווחים מיותרים מהביטוי
        expression = expression.replaceAll(" ", "");

        // רשימות לאחסון אופרנדים ואופרטורים
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        int i = 0;
        while (i < expression.length()) {
            char current = expression.charAt(i);
            if (Character.isLetter(current) && i + 1 < expression.length() &&
                    Character.isDigit(expression.charAt(i + 1))) {
                StringBuilder sb = new StringBuilder();
                // קרא את שם התא
                while (i < expression.length() &&
                        (Character.isLetter(expression.charAt(i)) ||
                                Character.isDigit(expression.charAt(i)))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                String cellRef = sb.toString();
                // מצא את ערך התא
                if (isValid(cellRef)) {
                    Cell referencedCell = find(cellRef);
                    if (referencedCell.getType() == 1){
                        scell.setData(Ex2Utils.ERR_FORM);
                        return 0;
                    }
                    if (referencedCell.getType() == 2){
                        values.add(Double.parseDouble(referencedCell.getData()));
                    }
                    if (referencedCell.getType() == 3){
                        values.add(evaluateExpression(cellRef,Recurse+1,referencedCell));

                    }
//                    if (referencedCell != null) {
//                        String cellValue = referencedCell.getData();
//                        if (cellValue.startsWith("=")) {
//                            // אם התא מכיל נוסחה, חשב אותה רקורסיבית
//                            double val = evaluateExpression(cellValue.substring(1));
//                            values.add(val);
//                        } else if (SCell.isNumber(cellValue)) {
//                            values.add(Double.parseDouble(cellValue));
//                        } else {
//                            throw new IllegalArgumentException("Invalid cell reference: " + cellRef);
//                        }
//                    }
                }
                continue;
            }
            if (Character.isLetter(current) && (i - 1 > 0 || !Character.isDigit(expression.charAt(i -1)) )){
                //if (Character.isDigit(expression.charAt(i + 2));
                String ops = "*/-+)";
                int s = i;
                int j = i;
                double cell = 0;
              while (!ops.contains(String.valueOf((expression.charAt(i))))){
                  j++;
                  i++;

                }
              String a = expression.substring(s, j+1);


            }


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

            if (current == '(') {
                operators.add(current);
            }

            else if (current == ')') {
                // בצע חישובים עד שנסגור את הסוגריים
                while (!operators.isEmpty() && operators.get(operators.size() - 1) != '(') {
                    processOperation(values, operators);
                }
                operators.remove(operators.size() - 1); // הסר את '('
            }
            // if its operator
            else if (isOperator(current)) {
                // compute bt math order
                while (!operators.isEmpty() &&
                        hasPrecedence(current, operators.get(operators.size() - 1))) {
                    processOperation(values, operators);
                }
                operators.add(current);
            }
            i++;
        }

        // comput what left
        while (!operators.isEmpty()) {
            processOperation(values, operators);
        }

        // value is last
        return values.get(0);
    }

    private boolean CellReference(String ref) {
        if (ref == null || ref.length() < 2) return false;
        char col = ref.charAt(0);
        return Character.isLetter(col) &&
                ref.substring(1).matches("\\d+") &&
                col >= 'A' && col <= 'Z';
    }

    public boolean isValid(String Val){
        for (int i= 0; i < this.table.length; i++){
            for (int j = 0; j < this.table[i].length; j++){
                SCell a = (SCell) this.table[i][j];
                if (a.getName().equals(Val)){
                    return true;
                }
            }

        }
        return false;
    }

    public Cell find(String s){

//            if (!CellReference(s)){
//                return null;
//            }
//
//            char col = s.charAt(0);
//            int row = Integer.parseInt(s.substring(1));
//            int x = col - 'A';
//
//            if (isIn(x, row)) {
//                return table[x][row];
//            }
//            return null;
     if (isValid(s)){
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                SCell a = (SCell) this.table[i][j];
                if (a.getName().equals(s)) {
                    return table[i][j];
                }
            }

            }

        }
        return null;
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
