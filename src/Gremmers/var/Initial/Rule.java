package Gremmers.var.Initial;

import Gremmers.AST;

import java.util.ArrayList;

public class Rule extends AST {
    public String id;
    public String num ;

    public void setId(String id ){
        this.id = id;
    }
    public void setNum(String num){
        this.num = num;
    }

    public String getId(){
        return id;
    }
    public String getNum(){
        return num;
    }


    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", num='" + num + '\'' +

                '}';
    }
}
