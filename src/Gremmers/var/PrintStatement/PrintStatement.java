package Gremmers.var.PrintStatement;

import Gremmers.AST;

public class PrintStatement extends AST {
    public PrintIdNum prindidnum;
    public PrintText printtext;

    public void setPrindidnum(PrintIdNum prindidnum){
        this.prindidnum = prindidnum;
    }
    public void setPrindText(PrintText printtext){
        this.printtext = printtext;
    }
    public PrintIdNum getPrindidnum(){
       return prindidnum;
    }
    public PrintText getPrindText(){
        return printtext;
    }

}
