package ex2;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Ex2SheetTest {
        Ex2Sheet table = new Ex2Sheet(5,5);
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void value() {
    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void testGet() {
    }

    @org.junit.jupiter.api.Test
    void width() {
    }

    @org.junit.jupiter.api.Test
    void height() {
    }

    @org.junit.jupiter.api.Test
    void set() {
    }

    @org.junit.jupiter.api.Test
    void eval() {
    }

    @org.junit.jupiter.api.Test
    void isIn() {
    }

    @org.junit.jupiter.api.Test
    void depth() {
    }

    @org.junit.jupiter.api.Test
    void load() {
    }

    @org.junit.jupiter.api.Test
    void save() {
    }

    @org.junit.jupiter.api.Test
    void testEval() {
    }

    @org.junit.jupiter.api.Test
    void evaluateExpression() {
        table.set(0,0,"1");
        table.set(0,1,"=A0+2");
        assertEquals(2,table.get(0,0).getType());
        assertEquals(3,table.get(0,1).getType());

        assertEquals(3,table.evaluateExpression(table.get(0,1).getData(),0,table.get(0,1)));
    }

    @Test
    public void isValid() {
    }

    @Test
    public void find() {
        table.set(4,4,"A0");
        table.find("E4");
    }
}