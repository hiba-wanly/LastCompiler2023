package Gremmers.var;

import Gremmers.AST;
import Gremmers.functions.Functions;
import Gremmers.var.DoWhileStatement.DoWhileStatement;
import Gremmers.var.IfStatement.IfStatment;
import Gremmers.var.Initial.Initial;
import Gremmers.var.Loop.Loop;
import Gremmers.var.PrintStatement.PrintStatement;
import Gremmers.var.SwitchStatement.SwitchStatement;
import Gremmers.var.TryCatchStatement.TryCatchStatement;
import Gremmers.var.WhileStatement.WhileStatement;

public class InputClasses extends AST {

    public Initial initial;
    public IfStatment ifStatment;
    public Loop loop;
    public DoWhileStatement doWhileStatement;
    public WhileStatement whileStatement;
    public PrintStatement printStatement;
    public SwitchStatement switchStatement;
    public TryCatchStatement tryCatchStatement;

    public Functions fun;

    public void setInitial(Initial initial){
        this.initial = initial;
    }
    public Initial getInitial(){
        return initial;
    }
    public void setIfStatment(IfStatment ifStatment){
        this.ifStatment = ifStatment;
    }
    public IfStatment getIfStatment(){
        return ifStatment;
    }
    public void setLoop(Loop loop){
        this.loop = loop;
    }
    public Loop getLoop(){
        return loop;
    }
    public void setDoWhileStatement(DoWhileStatement doWhileStatement){
        this.doWhileStatement = doWhileStatement;
    }
    public DoWhileStatement getDoWhileStatement(){
        return doWhileStatement;
    }
    public void setWhileStatement(WhileStatement whileStatement){
        this.whileStatement = whileStatement;
    }
    public WhileStatement getWhileStatement(){
        return whileStatement;
    }
    public void setPrintStatement(PrintStatement printStatement){
        this.printStatement = printStatement;
    }
    public PrintStatement getPrintStatement(){
        return printStatement;
    }
    public void setSwitchStatement(SwitchStatement switchStatement){
        this.switchStatement = switchStatement;
    }
    public SwitchStatement getSwitchStatement(){
        return switchStatement;
    }
    public void setTryCatchStatement(TryCatchStatement tryCatchStatement){
        this.tryCatchStatement = tryCatchStatement;
    }
    public TryCatchStatement getTryCatchStatement(){
        return tryCatchStatement;
    }


    public void setFun(Functions fun){
        this.fun = fun;
    }
    public Functions getFun(){
        return fun;
    }



}
