package Gremmers.var.Loop;

import Gremmers.AST;
import Gremmers.var.Input;

import java.util.ArrayList;

public class ForLoop extends AST {
    public String forf;
    public String varr;
    public String id;
    public String num;
    public String id1;
    public String singl;
    public String id2;
    public String id3;
    public String plmi;
    public ArrayList<Input> inputs ;


    public void setForf(String forf){
        this.forf = forf;
    }
    public void setVarr(String varr){
        this.varr = varr;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setNum(String num){
        this.num = num;
    }
    public void setId1(String id1){
        this.id1 = id1;
    }
    public void setSingl(String singl){
        this.singl = singl;
    }
    public void setId2(String id2){
        this.id2 = id2;
    }
    public void setId3(String id3){
        this.id3 = id3;
    }
    public void setPlmi(String plmi){
        this.plmi = plmi;
    }
    public void setInputs(ArrayList<Input> inputs){
        this.inputs = inputs;
    }



    public String getForf(){
        return forf;
    }
    public String getVarr(){
        return varr;
    }
    public String getId(){
        return id;
    }
    public String getNum(){
        return num;
    }
    public String getId1(){
        return id1;
    }
    public String getSingl(){
        return singl;
    }
    public String  getId2(){
        return id2;
    }
    public String getId3(){
        return id3;
    }
    public String  getPlmi(){
        return plmi;
    }
    public ArrayList<Input> getInputs(){
        return inputs;
    }

}
