package Gremmers.prog;
import Gremmers.AST;

public class Element extends AST{
    public Body body;

    public Body getBody(){
        return body;
    }

    public void setBody(Body body){
        this.body = body;
    }

    @Override
    public String toString() {
        return "Element{" +
                "body=" + body +
                '}';
    }

}
