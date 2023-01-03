package Gremmers.var.PrintStatement;

import Gremmers.AST;
import Gremmers.var.Initial.Rule;

import java.util.ArrayList;

public class PrintText extends AST {
    public String prin;
    public ArrayList<Rule> rules;

    public void setPrin(String prin){
        this.prin = prin;
    }
    public void setRules(ArrayList<Rule> world){
        this.rules = rules;
    }

    public String  getPrin(){
        return prin;
    }
    public ArrayList<Rule> getRules(){
        return rules;
    }
}
