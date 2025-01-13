package ex2;


import org.junit.Test;

import static junit.framework.TestCase.*;

public class SCellTests {

    @Test
    public void testIsNumber() {
        SCell cell = new SCell("","A0");
        assertTrue(SCell.isNumber("1"));
        assertTrue(SCell.isNumber("-1.1"));
        assertTrue(SCell.isNumber("0.0"));
        assertFalse(SCell.isNumber("abc"));
        assertFalse(SCell.isNumber("1.1.1"));
    }
    @Test
    public void testIsText() {
        SCell cell = new SCell("","A0");
        assertTrue(SCell.IsText("hello"));
        assertTrue(SCell.IsText("abc123"));
        assertTrue(SCell.IsText("A5"));


    }

    @Test
    public void testIsForm() {
        SCell cell = new SCell("","A0");
        assertTrue(SCell.isForm("=1"));
        assertTrue(SCell.isForm("=1+2*3"));
        assertTrue(SCell.isForm("=(1+2)"));
        assertTrue(SCell.isForm("=((1+2)*3)-1"));
        assertFalse(SCell.isForm("()"));
        assertFalse(SCell.isForm("5**"));
        assertFalse(SCell.isForm("1+"));
    }
    @Test
    public void testComputeFormValid() {
        SCell cell = new SCell("","A0");
        Ex2Sheet sheet = new Ex2Sheet();
        assertEquals(5.0, sheet.evaluateExpression("=1+2*2",0,cell),Ex2Utils.EPS2);
        assertEquals(5.0, sheet.evaluateExpression("=((1+2)*2)-1",0,cell));
        assertEquals(3.0, sheet.evaluateExpression("(1+2)/2+3-1.5",0,cell));
    }

    @Test
    public void testGetName(){
        SCell i = new SCell("","A56");
        assertEquals(i.getName(),"A56");
    }
    @Test
    public void CellTest(){
        SCell bcell = new SCell("=A0+2","A1");
        assertEquals(Ex2Utils.FORM,bcell.getType());
    }

}

