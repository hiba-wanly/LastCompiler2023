package Gremmers.var.Expression;

import Gremmers.AST;
import Gremmers.var.Float;
import Gremmers.var.IntI;

public class NumbersIF extends AST {


    public IntI ii;
    public Float ff;

    public void setIi(IntI ii){
        this.ii = ii;
    }
    public void setFf(Float ff){
        this.ff = ff;
    }
    public IntI getInt(){
        return ii;
    }
    public Float fetFf(){
        return  ff;
    }
}
