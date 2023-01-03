package Gremmers.var.PrintStatement;

import Gremmers.AST;

public class PrintIdNum extends AST {
    public String prin;
    public String world;

    public void setPrin(String prin){
        this.prin = prin;
    }
    public void setWorld(String world){
        this.world = world;
    }

    public String  getPrin(){
        return prin;
    }
    public String getWorld(){
        return world;
    }
}
