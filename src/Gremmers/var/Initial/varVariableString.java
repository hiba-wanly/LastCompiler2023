package Gremmers.var.Initial;

import Gremmers.AST;
import Gremmers.expr.Expr;

import java.util.ArrayList;

public class varVariableString extends AST {

    public  String type;

    public  String id ;

    public ArrayList<Rule> rule;

    public void setVarVaiableType(String type){
        this.type = type;
    }

    public void setVarVaiableId(String id){
        this.id = id;
    }


    public void setVarVaiableRule(Rule rule){
        this.rule.add(rule);
    }

    public String getVarVaiabletype(){
        return type;
    }
    public String getVarVaiableid(){
        return id;
    }


    public ArrayList<Rule> getVarVaiablerule(){
        return rule;
    }

    @Override
    public String toString() {
        return "varVariableString{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", rule=" + rule +
                '}';
    }
}
