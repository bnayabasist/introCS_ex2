package ex2;
// Add your documentation below:

public class CellEntry  implements Index2D {
private int x;
private int y;

    public CellEntry (char x,char y){
this.x =x;
this.y = y;
    }
    @Override
    public boolean isValid() {
        if (x>25 || x<0 || y<0|| y> 99){
            return false;
        }

        return true;
    }

    @Override
    public int getX() {
        if (isValid()){
            return x;
        }
        return Ex2Utils.ERR;}

    @Override
    public int getY() {
        if (isValid()){
            return y;
        }
        return Ex2Utils.ERR;}
}
