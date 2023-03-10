package Gremmers.var.Expression;

import Gremmers.AST;

import java.util.ArrayList;

public class Logic extends AST {
    private BoolExpresions boolExpresion;
    private ArrayList<NestedLogic> nestedLogics = new ArrayList<>();
    private Logic logic;
    private ArrayList<NestedLogic2> nestedLogic2s = new ArrayList<>();


    public BoolExpresions getBoolExpresion() {
        return boolExpresion;
    }

    public void setBoolExpresion(BoolExpresions boolExpresion) {
        this.boolExpresion = boolExpresion;
    }

    public ArrayList<NestedLogic> getNestedLogics() {
        return nestedLogics;
    }

    public void setNestedLogics(ArrayList<NestedLogic> nestedLogics) {
        this.nestedLogics = nestedLogics;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public ArrayList<NestedLogic2> getNestedLogic2s() {
        return nestedLogic2s;
    }

    public void setNestedLogic2s(ArrayList<NestedLogic2> nestedLogic2s) {
        this.nestedLogic2s = nestedLogic2s;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "boolExpresion=" + boolExpresion +
                ", nestedLogics=" + nestedLogics +
                ", logic=" + logic +
                ", nestedLogic2s=" + nestedLogic2s +
                '}';
    }
}
