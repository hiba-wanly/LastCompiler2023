package Gremmers.var.Expression;

import Gremmers.AST;

public class Operations extends AST {
    private String PLUS;
    private String MINUS;
    private String MULTI;
    private String DIVIDE;

    public String getPLUS() {
        return PLUS;
    }

    public void setPLUS(String PLUS) {
        this.PLUS = PLUS;
    }

    public String getMINUS() {
        return MINUS;
    }

    public void setMINUS(String MINUS) {
        this.MINUS = MINUS;
    }

    public String getMULTI() {
        return MULTI;
    }

    public void setMULTI(String MULTI) {
        this.MULTI = MULTI;
    }

    public String getDIVIDE() {
        return DIVIDE;
    }

    public void setDIVIDE(String DIVIDE) {
        this.DIVIDE = DIVIDE;
    }

    @Override
    public String toString() {
        return "Operations{" +
                "PLUS='" + PLUS + '\'' +
                ", MINUS='" + MINUS + '\'' +
                ", MULTI='" + MULTI + '\'' +
                ", DIVIDE='" + DIVIDE + '\'' +
                '}';
    }
}
