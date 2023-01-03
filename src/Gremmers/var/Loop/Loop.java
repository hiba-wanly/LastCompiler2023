package Gremmers.var.Loop;

import Gremmers.AST;

public class Loop extends AST {
    public ForLoop forloop;

    public void setForloop(ForLoop forloop){
        this.forloop = forloop;
    }
    public ForLoop getForloop(){
        return forloop;
    }

}
