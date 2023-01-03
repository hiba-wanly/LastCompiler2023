package Gremmers.classes;

import Gremmers.AST;
import Gremmers.var.InputClasses;

import java.util.ArrayList;

public class ClassStatement extends AST {
    public String clas ;
    public String id ;
    public ArrayList<InputClasses> inputclasses;

    public void setClas(String clas ){
        this.clas = clas;
    }
    public String getClas(){
        return clas;
    }

    public void setId(String id ){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public void setInputclasses(ArrayList<InputClasses> inputclasses){
        this.inputclasses = inputclasses;
    }
    public ArrayList<InputClasses> getInputclasses(){
        return inputclasses;
    }
}
