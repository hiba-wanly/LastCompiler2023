package Gremmers.visitor;

import Gremmers.AST;
import Gremmers.array.*;
import Gremmers.classes.AbstractStatment;
import Gremmers.classes.ClassStatement;
import Gremmers.classes.ExtendsClass;
import Gremmers.expr.*;
import Gremmers.functions.Functions;
import Gremmers.prog.*;
import Gremmers.var.*;
import Gremmers.var.DoWhileStatement.DoWhileStatement;
import Gremmers.var.Double;
import Gremmers.var.Expression.*;
import Gremmers.var.Expression.Math;
import Gremmers.var.Float;
import Gremmers.var.IfStatement.IfStatment;
import Gremmers.var.Initial.*;
import Gremmers.var.Loop.ForLoop;
import Gremmers.var.Loop.Loop;
import Gremmers.var.PrintStatement.PrintIdNum;
import Gremmers.var.PrintStatement.PrintStatement;
import Gremmers.var.PrintStatement.PrintText;
import Gremmers.var.SwitchStatement.SwitchStatement;
import Gremmers.var.TryCatchStatement.TryCatchStatement;
import Gremmers.var.WhileStatement.WhileStatement;
import syntax.projectParserBaseVisitor;
import syntax.projectParser;

import java.lang.Integer;

public class AntlrToAST extends projectParserBaseVisitor<AST> {

    @Override
    public Prog visitProg(projectParser.ProgContext ctx) {
       System.out.println("visitProg");
        Prog pp = new Prog();
        System.out.println("H");
        for(int i = 0 ; i < ctx.element().size() ;i++){
            System.out.println("HHH");
            pp.getElements().add(visitElement(ctx.element(i)));
        }
        return  pp ;
    }

    @Override
    public Element visitElement(projectParser.ElementContext ctx) {
       System.out.println("visitElement");
        Element e = new Element();
        if(ctx.body() != null) {
            e.setBody(visitBody(ctx.body()));
        }
        return e;
    }


    @Override
    public Body visitBody(projectParser.BodyContext ctx) {
        System.out.println("visitBody");
        Body body = new Body();
        if(ctx.classS() != null) {
            body.setClasses(visitClassS(ctx.classS()));
        }
        else if(ctx.functionStatement() != null) {
            body.setFunctions(visitFunctionStatement(ctx.functionStatement()));
        }
        return body;
    }

    @Override
    public Float visitFloat_f(projectParser.Float_fContext ctx) {
        Float float_p = new Float();
        float_p.setFLOAT(ctx.NUM_FLOAT().toString());
        return float_p;
    }

    @Override
    public IntI visitInt_i(projectParser.Int_iContext ctx) {
        IntI int_p = new IntI();
        int_p.setIntt(ctx.NUM().toString());
        return int_p;
    }

    @Override
    public Double visitDouble_d(projectParser.Double_dContext ctx) {
        Double double_p = new Double();
        double_p.setDouble(ctx.NUM_DOUBLE().toString());
        return double_p;
    }

    @Override
    public ArrayType visitArrayType(projectParser.ArrayTypeContext ctx) {
        ArrayType a = new ArrayType();
        if(ctx.array_var() != null){
            a.setArrayVar(visitArray_var(ctx.array_var()));
        }
        if(ctx.array_int() != null){
            a.setArrayInt(visitArray_int(ctx.array_int()));
        }
        if(ctx.array_string() != null){
            a.setArrayString(visitArray_string(ctx.array_string()));
        }
        if(ctx.array_float() != null){
            a.setArrayfloat(visitArray_float(ctx.array_float()));
        }
        return a;
    }

    @Override
    public ArrayVar visitArray_var(projectParser.Array_varContext ctx) {
        ArrayVar arrvar = new ArrayVar();
        arrvar.setVar(ctx.VAR().toString());
        arrvar.setId(ctx.ID().toString());
        arrvar.setArraybody(visitArraybody(ctx.arraybody()));
        return arrvar;
    }

    @Override
    public ArrayInt visitArray_int(projectParser.Array_intContext ctx) {
        ArrayInt arrint = new ArrayInt();
        arrint.setVar(ctx.INT().toString());
        arrint.setId(ctx.ID().toString());
        arrint.setArrayVarInt(visitArrayINT(ctx.arrayINT()));
        return arrint;
    }

    @Override
    public ArrayString visitArray_string(projectParser.Array_stringContext ctx) {
        ArrayString arrstr = new ArrayString();
        arrstr.setVar(ctx.STRING().toString());
        arrstr.setId(ctx.ID().toString());
        arrstr.setArrayVarString(visitArraySTRING(ctx.arraySTRING()));
        return arrstr;
    }

    @Override
    public ArrayFloat visitArray_float(projectParser.Array_floatContext ctx) {
        ArrayFloat arrflo = new ArrayFloat();
        arrflo.setVar(ctx.FLOAT().toString());
        arrflo.setId(ctx.ID().toString());
        arrflo.setArrayVarFloat(visitArrayFLOAT(ctx.arrayFLOAT()));
        return arrflo;
    }

    @Override
    public ArrayBody visitArraybody(projectParser.ArraybodyContext ctx) {
        ArrayBody aa = new ArrayBody();
        if(ctx.arrayINT() != null){
            aa.setArrayVarInt(visitArrayINT(ctx.arrayINT()));
        }
        if(ctx.arraySTRING() != null){
            aa.setArrayVarString(visitArraySTRING(ctx.arraySTRING()));
        }
        if(ctx.arrayFLOAT() != null){
            aa.setArrayVarFloat(visitArrayFLOAT(ctx.arrayFLOAT()));
        }
        return aa;
    }

    @Override
    public ArrayVarInt visitArrayINT(projectParser.ArrayINTContext ctx) {
        ArrayVarInt aarvarint = new ArrayVarInt();
        for(int i=0 ; i< ctx.NUM().size(); i++){
            aarvarint.addChild(Integer.parseInt(ctx.NUM().get(i).getText()));
        }
        return aarvarint;
    }

    @Override
    public ArrayVarString visitArraySTRING(projectParser.ArraySTRINGContext ctx) {
        ArrayVarString aarvarstr = new ArrayVarString();
        for(int i=0 ; i< ctx.INPUT_D_Q_I().size(); i++){
            aarvarstr.addChild(ctx.INPUT_D_Q_I().get(i).getText());
        }
        return aarvarstr;
    }

    @Override
    public ArrayVarFloat visitArrayFLOAT(projectParser.ArrayFLOATContext ctx) {
        ArrayVarFloat aarvarflo = new ArrayVarFloat();
        for(int i=0 ; i< ctx.NUM_FLOAT().size(); i++){
            aarvarflo.addChild(ctx.NUM_FLOAT().get(i).getText());
        }
        return aarvarflo;
    }

    @Override
    public Initial visitInitial(projectParser.InitialContext ctx) {
        System.out.println("visitInitial");
        Initial in = new Initial();
        if(ctx.var_Variable() != null){
            in.setVarvariable(visitVar_Variable(ctx.var_Variable()));
        }
        else if(ctx.var_Variable_expr() != null){
            in.setVarvariableexpr(visitVar_Variable_expr(ctx.var_Variable_expr()));
        }
        else if(ctx.var_Variable_emp() != null){
            in.setVarvariableemp(visitVar_Variable_emp(ctx.var_Variable_emp()));
        }
        else if(ctx.varVariable_INPUT_D_Q_N() != null){
            in.setVarvariable(visitVarVariable_INPUT_D_Q_N(ctx.varVariable_INPUT_D_Q_N()));
        }
        else if(ctx.varVariable_INPUT_D_Q_I() != null){
            in.setVarvariable(visitVarVariable_INPUT_D_Q_I(ctx.varVariable_INPUT_D_Q_I()));
        }
        else if(ctx.const_Variable() != null){
            in.setVarvariable(visitConst_Variable(ctx.const_Variable()));
        }
        else if(ctx.const_Variable_expr() != null){
            in.setVarvariableexpr(visitConst_Variable_expr(ctx.const_Variable_expr()));
        }
        else if(ctx.const_Variable_emp() != null){
            in.setVarvariableemp(visitConst_Variable_emp(ctx.const_Variable_emp()));
        }
        else if(ctx.const_Variable_INPUT_D_Q_N() != null){
            in.setVarvariable(visitConst_Variable_INPUT_D_Q_N(ctx.const_Variable_INPUT_D_Q_N()));
        }
        else if(ctx.const_Variable_INPUT_D_Q_I() != null){
            in.setVarvariable(visitConst_Variable_INPUT_D_Q_I(ctx.const_Variable_INPUT_D_Q_I()));
        }
        else if(ctx.dynamic_Variable() != null){
            in.setVarvariable(visitDynamic_Variable(ctx.dynamic_Variable()));
        }
        else if(ctx.dynamic_Variable_expr() != null){
            in.setVarvariableexpr(visitDynamic_Variable_expr(ctx.dynamic_Variable_expr()));
        }
        else if(ctx.dynamic_Variable_emp() != null){
            in.setVarvariableemp(visitDynamic_Variable_emp(ctx.dynamic_Variable_emp()));
        }
        else if(ctx.dynamic_Variable_INPUT_D_Q_N() != null){
            in.setVarvariable(visitDynamic_Variable_INPUT_D_Q_N(ctx.dynamic_Variable_INPUT_D_Q_N()));
        }
        else if(ctx.dynamic_Variable_INPUT_D_Q_I() != null){
            in.setVarvariable(visitDynamic_Variable_INPUT_D_Q_I(ctx.dynamic_Variable_INPUT_D_Q_I()));
        }
        else if(ctx.final_Variable() != null){
            in.setVarvariable(visitFinal_Variable(ctx.final_Variable()));
        }
        else if(ctx.final_Variable_expr() != null){
            in.setVarvariableexpr(visitFinal_Variable_expr(ctx.final_Variable_expr()));
        }
        else if(ctx.final_Variable_emp() != null){
            in.setVarvariableemp(visitFinal_Variable_emp(ctx.final_Variable_emp()));
        }
        else if(ctx.final_Variable_INPUT_D_Q_N() != null){
            in.setVarvariable(visitFinal_Variable_INPUT_D_Q_N(ctx.final_Variable_INPUT_D_Q_N()));
        }
        else if(ctx.final_Variable_INPUT_D_Q_I() != null){
            in.setVarvariable(visitFinal_Variable_INPUT_D_Q_I(ctx.final_Variable_INPUT_D_Q_I()));
        }
        else if(ctx.string_Variable_INPUT_D_Q_N() != null){
            in.setVarvariable(visitString_Variable_INPUT_D_Q_N(ctx.string_Variable_INPUT_D_Q_N()));
        }
        else if(ctx.string_Variable_INPUT_D_Q_I() != null){
            in.setVarvariable(visitString_Variable_INPUT_D_Q_I(ctx.string_Variable_INPUT_D_Q_I()));
        }
        else if(ctx.string_Variable_TEXT() != null){
            in.setVarvariableString(visitString_Variable_TEXT(ctx.string_Variable_TEXT()));
        }
        else if(ctx.int_Variable() != null){
            in.setVarvariable(visitInt_Variable(ctx.int_Variable()));
        }
        else if(ctx.int_Variable_expr() != null){
            in.setVarvariableexpr(visitInt_Variable_expr(ctx.int_Variable_expr()));
        }
        else if(ctx.int_Variable_emp() != null){
            in.setVarvariableemp(visitInt_Variable_emp(ctx.int_Variable_emp()));
        }
        else if(ctx.float_Variable() != null){
            in.setVarvariable(visitFloat_Variable(ctx.float_Variable()));
        }
        else if(ctx.double_Variable() != null){
            in.setVarvariable(visitDouble_Variable(ctx.double_Variable()));
        }
        else if(ctx.boolean_Variable() != null){
            in.setVarvariable(visitBoolean_Variable(ctx.boolean_Variable()));
        }
        else if(ctx.arrayType() != null){
            in.setArrayType(visitArrayType(ctx.arrayType()));
        }
        return in;
    }

    @Override
    public varVariable visitVar_Variable(projectParser.Var_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.VAR().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM().toString());
        return vv;
    }

    @Override
    public varVariableExpr visitVar_Variable_expr(projectParser.Var_Variable_exprContext ctx) {
        varVariableExpr vv = new varVariableExpr();
        vv.setVarVaiableType(ctx.VAR().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableExpr(visitExpr(ctx.expr()));
        return vv;
    }

    @Override
    public varVariableEmp visitVar_Variable_emp(projectParser.Var_Variable_empContext ctx) {
        varVariableEmp vv = new varVariableEmp();
        vv.setVarVaiableType(ctx.VAR().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        return vv;
    }

    @Override
    public varVariable visitVarVariable_INPUT_D_Q_N(projectParser.VarVariable_INPUT_D_Q_NContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.VAR().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_N().toString());
        return vv;
    }

    @Override
    public varVariable visitVarVariable_INPUT_D_Q_I(projectParser.VarVariable_INPUT_D_Q_IContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.VAR().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_I().toString());
        return vv;
    }

    @Override
    public varVariable visitConst_Variable(projectParser.Const_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.CONST().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM().toString());
        return vv;
    }

    @Override
    public varVariableExpr visitConst_Variable_expr(projectParser.Const_Variable_exprContext ctx) {
        varVariableExpr vv = new varVariableExpr();
        vv.setVarVaiableType(ctx.CONST().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableExpr(visitExpr(ctx.expr()));
        return vv;
    }

    @Override
    public varVariableEmp visitConst_Variable_emp(projectParser.Const_Variable_empContext ctx) {
        varVariableEmp vv = new varVariableEmp();
        vv.setVarVaiableType(ctx.CONST().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        return vv;
    }

    @Override
    public varVariable visitConst_Variable_INPUT_D_Q_N(projectParser.Const_Variable_INPUT_D_Q_NContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.CONST().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_N().toString());
        return vv;
    }

    @Override
    public varVariable visitConst_Variable_INPUT_D_Q_I(projectParser.Const_Variable_INPUT_D_Q_IContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.CONST().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_I().toString());
        return vv;
    }

    @Override
    public varVariable visitDynamic_Variable(projectParser.Dynamic_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.DYNAMIC().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM().toString());
        return vv;
    }

    @Override
    public varVariableExpr visitDynamic_Variable_expr(projectParser.Dynamic_Variable_exprContext ctx) {
        varVariableExpr vv = new varVariableExpr();
        vv.setVarVaiableType(ctx.DYNAMIC().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableExpr(visitExpr(ctx.expr()));
        return vv;
    }

    @Override
    public varVariableEmp visitDynamic_Variable_emp(projectParser.Dynamic_Variable_empContext ctx) {
        varVariableEmp vv = new varVariableEmp();
        vv.setVarVaiableType(ctx.DYNAMIC().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        return vv;
    }

    @Override
    public varVariable visitDynamic_Variable_INPUT_D_Q_N(projectParser.Dynamic_Variable_INPUT_D_Q_NContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.DYNAMIC().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_N().toString());
        return vv;
    }

    @Override
    public varVariable visitDynamic_Variable_INPUT_D_Q_I(projectParser.Dynamic_Variable_INPUT_D_Q_IContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.DYNAMIC().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_I().toString());
        return vv;
    }

    @Override
    public varVariable visitFinal_Variable(projectParser.Final_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.FINAL().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM().toString());
        return vv;
    }

    @Override
    public varVariableExpr visitFinal_Variable_expr(projectParser.Final_Variable_exprContext ctx) {
        varVariableExpr vv = new varVariableExpr();
        vv.setVarVaiableType(ctx.FINAL().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableExpr(visitExpr(ctx.expr()));
        return vv;
    }

    @Override
    public varVariableEmp visitFinal_Variable_emp(projectParser.Final_Variable_empContext ctx) {
        varVariableEmp vv = new varVariableEmp();
        vv.setVarVaiableType(ctx.FINAL().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        return vv;
    }

    @Override
    public varVariable visitFinal_Variable_INPUT_D_Q_N(projectParser.Final_Variable_INPUT_D_Q_NContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.FINAL().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_N().toString());
        return vv;
    }

    @Override
    public varVariable visitFinal_Variable_INPUT_D_Q_I(projectParser.Final_Variable_INPUT_D_Q_IContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.FINAL().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_I().toString());
        return vv;
    }

    @Override
    public varVariable visitString_Variable_INPUT_D_Q_N(projectParser.String_Variable_INPUT_D_Q_NContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.STRING().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_N().toString());
        return vv;
    }

    @Override
    public varVariable visitString_Variable_INPUT_D_Q_I(projectParser.String_Variable_INPUT_D_Q_IContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.STRING().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.INPUT_D_Q_I().toString());
        return vv;
    }

    @Override
    public varVariableString visitString_Variable_TEXT(projectParser.String_Variable_TEXTContext ctx) {
        varVariableString vv = new varVariableString();
        vv.setVarVaiableType(ctx.STRING().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        for (int i=0 ; i<ctx.rule_().size() ; i++){
            vv.setVarVaiableRule(visitRule(ctx.rule_().get(i)));
        }
        return vv;
    }

    @Override
    public varVariable visitInt_Variable(projectParser.Int_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.INT().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM().toString());
        return vv;
    }

    @Override
    public varVariableExpr visitInt_Variable_expr(projectParser.Int_Variable_exprContext ctx) {
        varVariableExpr vv = new varVariableExpr();
        vv.setVarVaiableType(ctx.INT().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableExpr(visitExpr(ctx.expr()));
        return vv;
    }

    @Override
    public varVariableEmp visitInt_Variable_emp(projectParser.Int_Variable_empContext ctx) {
        varVariableEmp vv = new varVariableEmp();
        vv.setVarVaiableType(ctx.INT().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        return vv;
    }

    @Override
    public varVariable visitFloat_Variable(projectParser.Float_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.FLOAT().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM_FLOAT().toString());
        return vv;
    }

    @Override
    public varVariable visitDouble_Variable(projectParser.Double_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.DOUBLE().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM_DOUBLE().toString());
        return vv;
    }

    @Override
    public varVariable visitBoolean_Variable(projectParser.Boolean_VariableContext ctx) {
        varVariable vv = new varVariable();
        vv.setVarVaiableType(ctx.BOOLEAN().toString());
        vv.setVarVaiableId(ctx.ID().toString());
        vv.setVarVaiableValue(ctx.NUM_BOOL().toString());
        return vv;
    }

    @Override
    public Loop visitLoop(projectParser.LoopContext ctx) {
        Loop lo = new Loop();
        if(ctx.for_INT_With_ID_ID() != null){
            lo.setForloop(visitFor_VAR_With_ID_ID(ctx.for_VAR_With_ID_ID()));
        }
        if(ctx.for_INT_With_ID_ID() != null){
            lo.setForloop(visitFor_INT_With_ID_ID(ctx.for_INT_With_ID_ID()));
        }
        if(ctx.for_VAR_With_ID_NUM() != null){
            lo.setForloop(visitFor_VAR_With_ID_NUM(ctx.for_VAR_With_ID_NUM()));
        }
        if(ctx.for_INT_With_ID_NUM() != null){
            lo.setForloop(visitFor_INT_With_ID_NUM(ctx.for_INT_With_ID_NUM()));
        }
        return  lo;
    }

    @Override
    public ForLoop visitFor_VAR_With_ID_ID(projectParser.For_VAR_With_ID_IDContext ctx) {
        ForLoop fo = new ForLoop();
        ///////
        return fo;
    }

    @Override
    public ForLoop visitFor_INT_With_ID_ID(projectParser.For_INT_With_ID_IDContext ctx) {
        ForLoop fo = new ForLoop();
        ///////
        return fo;
    }

    @Override
    public ForLoop visitFor_VAR_With_ID_NUM(projectParser.For_VAR_With_ID_NUMContext ctx) {
        ForLoop fo = new ForLoop();
        ///////
        return fo;
    }

    @Override
    public ForLoop visitFor_INT_With_ID_NUM(projectParser.For_INT_With_ID_NUMContext ctx) {
        ForLoop fo = new ForLoop();
        ///////
        return fo;
    }

    @Override
    public PrintStatement visitPrintstatement(projectParser.PrintstatementContext ctx) {
        PrintStatement pr = new PrintStatement();
             ////////////
        return pr;
    }

    @Override
    public PrintIdNum visitPrint_ID(projectParser.Print_IDContext ctx) {
        PrintIdNum pp = new PrintIdNum();
        ////////
        return pp;
    }

    @Override
    public PrintIdNum visitPrint_NUM(projectParser.Print_NUMContext ctx) {

        PrintIdNum pp = new PrintIdNum();
        ////////
        return pp;
    }

    @Override
    public PrintText visitPrint_TEXT(projectParser.Print_TEXTContext ctx) {
        PrintText pp = new PrintText();
        ////////
        return pp;
    }

    @Override
    public Rule visitRule(projectParser.RuleContext ctx) {
        Rule rr = new Rule();
        if(ctx.ID() != null){
            rr.setId(ctx.ID().toString());
        }
        if(ctx.NUM() != null){
            rr.setNum(ctx.NUM().toString());
        }
        return rr;
    }

    @Override
    public IfStatment visitIfstatement(projectParser.IfstatementContext ctx) {
        IfStatment ifif = new IfStatment();
        /////////
        return ifif ;
    }

    @Override
    public AST visitIf_WithInput(projectParser.If_WithInputContext ctx) {
        return super.visitIf_WithInput(ctx);
    }

    @Override
    public AST visitIf_WithBreak(projectParser.If_WithBreakContext ctx) {
        return super.visitIf_WithBreak(ctx);
    }

    @Override
    public AST visitIf_WithContinue(projectParser.If_WithContinueContext ctx) {
        return super.visitIf_WithContinue(ctx);
    }

    @Override
    public WhileStatement visitWhilestatemen(projectParser.WhilestatemenContext ctx) {
         WhileStatement whilstat = new WhileStatement();
         /////////
        return whilstat;
    }

    @Override
    public AST visitWhile_WithInput(projectParser.While_WithInputContext ctx) {
        return super.visitWhile_WithInput(ctx);
    }

    @Override
    public AST visitWhile_WithBreak(projectParser.While_WithBreakContext ctx) {
        return super.visitWhile_WithBreak(ctx);
    }

    @Override
    public AST visitWhile_WithContinue(projectParser.While_WithContinueContext ctx) {
        return super.visitWhile_WithContinue(ctx);
    }

    @Override
    public SwitchStatement visitSwitchstatement(projectParser.SwitchstatementContext ctx) {
        SwitchStatement swstat = new SwitchStatement();
        /////////
        return swstat;
    }

    @Override
    public AST visitSwitch_With_ID(projectParser.Switch_With_IDContext ctx) {
        return super.visitSwitch_With_ID(ctx);
    }

    @Override
    public AST visitSwitch_With_NUM(projectParser.Switch_With_NUMContext ctx) {
        return super.visitSwitch_With_NUM(ctx);
    }

    @Override
    public AST visitCasestatement(projectParser.CasestatementContext ctx) {
        return super.visitCasestatement(ctx);
    }

    @Override
    public AST visitCase_With_ID(projectParser.Case_With_IDContext ctx) {
        return super.visitCase_With_ID(ctx);
    }

    @Override
    public AST visitCase_With_NUM(projectParser.Case_With_NUMContext ctx) {
        return super.visitCase_With_NUM(ctx);
    }

    @Override
    public AST visitDefaultstatement(projectParser.DefaultstatementContext ctx) {
        return super.visitDefaultstatement(ctx);
    }

    @Override
    public TryCatchStatement visitTryCatchstatement(projectParser.TryCatchstatementContext ctx) {
        TryCatchStatement trystat = new TryCatchStatement();
        ///////////
        return  trystat;
    }

    @Override
    public booleanP visitBoolean_p(projectParser.Boolean_pContext ctx) {

        System.out.println("visitBoolean_p");
        booleanP boolean_p = new booleanP();

        if (ctx.TRUE() != null) {
            boolean_p.setTRUE(ctx.TRUE().toString().trim());
        }
        else if (ctx.FALSE() != null) {
            boolean_p.setFALSE(ctx.FALSE().toString().trim());
        }
        return boolean_p;
    }

    @Override
    public NumbersIF visitNumbers(projectParser.NumbersContext ctx) {

        System.out.println("visitNumbers");
        NumbersIF  numbers=new NumbersIF();
        if(ctx.float_f()!=null) {
            numbers.setFf(visitFloat_f(ctx.float_f()));
        }
        else if(ctx.int_i()!=null) {
            numbers.setIi(visitInt_i(ctx.int_i()));
        }
        return numbers;
    }

    @Override
    public Var visitVar(projectParser.VarContext ctx) {

        Var vv = new Var();
        vv.setVARNAME(ctx.ID().toString().trim());
        return vv;
    }

    @Override
    public Expression visitExpression(projectParser.ExpressionContext ctx) {

        System.out.println("visitExpression");
        Expression  expression=new Expression();
        if (ctx.numbers() != null) {
            expression.setNumbers( visitNumbers(ctx.numbers()));
        }
        else if (ctx.boolean_p() != null) {
            expression.setBoolean_p( visitBoolean_p(ctx.boolean_p()));
        }
        else if (ctx.var() != null) {
            expression.setVar(visitVar(ctx.var()));
        }
        else if (ctx.expression() != null) {
            expression.setExpression( visitExpression(ctx.expression()));
        }
        else if(ctx.math() != null){
            expression.setMath(visitMath(ctx.math()));
        }
        return expression;

    }

    @Override
    public Math visitMath(projectParser.MathContext ctx) {
        System.out.println("visitMath");
        Math math = new Math();
        if (ctx.var().isEmpty()){
            math.setNumbers1(visitNumbers(ctx.numbers(0)));
            math.setNumbers2(visitNumbers(ctx.numbers(1)));
        }
        else if(ctx.numbers().isEmpty()){
            math.setVar1(visitVar(ctx.var(0)));
            math.setVar2(visitVar(ctx.var(1)));
        }else{
            math.setVar1(visitVar(ctx.var(0)));
            math.setNumbers1(visitNumbers(ctx.numbers(0)));
        }
        math.setOperation(visitOperation(ctx.operation()));
        return math;
    }

    @Override
    public BoolExpresions visitBoolExpresion(projectParser.BoolExpresionContext ctx) {

        System.out.println("visitBoolExpresion");
        BoolExpresions  boolExpresion=new BoolExpresions();
        if(ctx.expression()!=null) {
            boolExpresion.setExpression1( visitExpression(ctx.expression(0)));
            boolExpresion.setBOOLEAN_SIGNS(ctx.SINGLS().toString().trim());
            if (ctx.expression() != null)
                boolExpresion.setExpression2(visitExpression(ctx.expression(1)));
        }
        else if(ctx.boolExpresion()!=null){
            boolExpresion.setBoolExpresion(visitBoolExpresion(ctx.boolExpresion()));
        }

        return boolExpresion;
    }

    @Override
    public Logic visitLogic(projectParser.LogicContext ctx) {
        System.out.println("visitLogic");
        Logic   logic=new Logic();
        if(ctx.boolExpresion()!=null){
            logic.setBoolExpresion( visitBoolExpresion(ctx.boolExpresion()));
            for (int i = 0; i < ctx.nestedLogic().size(); i++) {
                logic.getNestedLogics().add( visitNestedLogic(ctx.nestedLogic(i)));
            }
        }
        else if(ctx.logic()!=null){
            logic.setLogic(visitLogic(ctx.logic()));
            for (int i = 0; i < ctx.nestedLogic2().size(); i++) {
                logic.getNestedLogic2s().add( visitNestedLogic2(ctx.nestedLogic2(i)));
            }
        }
        return logic;
    }

    @Override
    public NestedLogic visitNestedLogic(projectParser.NestedLogicContext ctx) {

        System.out.println("visitNestedLogic");
        NestedLogic nestedLogic=new NestedLogic();
        nestedLogic.setLOGIC_SIGNS(ctx.LOGIC_SIGNS().toString().trim());
        nestedLogic.setBoolExpresion(visitBoolExpresion(ctx.boolExpresion()));

        return nestedLogic;
    }

    @Override
    public NestedLogic2 visitNestedLogic2(projectParser.NestedLogic2Context ctx) {

        System.out.println("visitNestedLogic2");
        NestedLogic2 nestedLogic2=new NestedLogic2();
        nestedLogic2.setLOGIC_SIGNS(ctx.LOGIC_SIGNS().toString().trim());
        nestedLogic2.setLogic(visitLogic(ctx.logic()));

        return nestedLogic2;
    }

    @Override
    public Conditions visitConditions(projectParser.ConditionsContext ctx) {

        System.out.println("visitConditions");
        Conditions  conditions=new Conditions();
        if(ctx.boolExpresion()!=null) {
            conditions.setBoolExpresion( visitBoolExpresion(ctx.boolExpresion()));
        }
        else if(ctx.logic()!=null) {
            conditions.setLogic( visitLogic(ctx.logic()));
        }
        return conditions;
    }

    @Override
    public Operations visitOperation(projectParser.OperationContext ctx) {
        System.out.println("visitOperation");
        Operations operation=new Operations();
        if(ctx.PLUS()!=null){
            operation.setPLUS(ctx.PLUS().toString().trim());
        }

        else  if(ctx.MINUS()!=null){
            operation.setMINUS(ctx.MINUS().toString().trim());
        }
        else if(ctx.MULTI()!=null){
            operation.setMULTI(ctx.MULTI().toString().trim());
        }
        else if(ctx.DIVIDE()!=null){
            operation.setDIVIDE(ctx.DIVIDE().toString().trim());
        }

        return operation;
    }

    @Override
    public DoWhileStatement visitDoWhilestatement(projectParser.DoWhilestatementContext ctx) {
         DoWhileStatement dostat = new DoWhileStatement();
         ////////
        return dostat;
    }

    @Override
    public Expr visitExpr(projectParser.ExprContext ctx) {
        Expr ex = new Expr();
        if(ctx.multiplication() != null){
            ex.setMultiplication(visitMultiplication(ctx.multiplication()));
        }
        if(ctx.addition() != null){
            ex.setAddition(visitAddition(ctx.addition()));
        }
        if(ctx.subtraction() != null){
            ex.setSubtraction(visitSubtraction(ctx.subtraction()));
        }
        if(ctx.division() != null){
            ex.setDivision(visitDivision(ctx.division()));
        }
        if(ctx.identifier() != null){
            ex.setIdentifier(visitIdentifier(ctx.identifier()));
        }
        if(ctx.integer_i() != null){
            ex.setInteger(visitInteger_i(ctx.integer_i()));
        }
        return ex;
    }

    @Override
    public Multiplication visitMultiplication(projectParser.MultiplicationContext ctx) {
        Multiplication mm = new Multiplication();
        mm.setNum1(ctx.getChild(0).toString());
        mm.setM(ctx.MULTI().toString());
        mm.setNum2(ctx.getChild(2).toString());
        return mm;
    }

    @Override
    public Addition visitAddition(projectParser.AdditionContext ctx) {
        Addition mm = new Addition();
        mm.setNum1(ctx.getChild(0).toString());
        mm.setM(ctx.PLUS().toString());
        mm.setNum2(ctx.getChild(2).toString());
        return mm;
    }

    @Override
    public Subtraction visitSubtraction(projectParser.SubtractionContext ctx) {
        Subtraction mm = new Subtraction();
        mm.setNum1(ctx.getChild(0).toString());
        mm.setM(ctx.MINUS().toString());
        mm.setNum2(ctx.getChild(2).toString());
        return mm;
    }

    @Override
    public Division visitDivision(projectParser.DivisionContext ctx) {
        Division mm = new Division();
        mm.setNum1(ctx.getChild(0).toString());
        mm.setM(ctx.DIVIDE().toString());
        mm.setNum2(ctx.getChild(2).toString());
        return mm;
    }

    @Override
    public Identifier visitIdentifier(projectParser.IdentifierContext ctx) {
        Identifier ii = new Identifier();
        ii.setIdentifier(ctx.ID().toString());
        return ii;
    }

    @Override
    public IntegerII visitInteger_i(projectParser.Integer_iContext ctx) {
            IntegerII ii = new IntegerII();
            ii.setIntegerII(ctx.NUM().toString());
            return ii;
    }

    @Override
    public Functions visitFunctionStatement(projectParser.FunctionStatementContext ctx) {
          Functions fun = new Functions();
          //////////
        return fun;
    }

    @Override
    public AST visitVoid_Function_NOReturn_NoARG(projectParser.Void_Function_NOReturn_NoARGContext ctx) {
        return super.visitVoid_Function_NOReturn_NoARG(ctx);
    }

    @Override
    public AST visitInt_Function_Return_NoARG(projectParser.Int_Function_Return_NoARGContext ctx) {
        return super.visitInt_Function_Return_NoARG(ctx);
    }

    @Override
    public AST visitString_Function_Return_NoARG(projectParser.String_Function_Return_NoARGContext ctx) {
        return super.visitString_Function_Return_NoARG(ctx);
    }

    @Override
    public AST visitDouble_Function_Return_NoARG(projectParser.Double_Function_Return_NoARGContext ctx) {
        return super.visitDouble_Function_Return_NoARG(ctx);
    }

    @Override
    public AST visitFloat_Function_Return_NoARG(projectParser.Float_Function_Return_NoARGContext ctx) {
        return super.visitFloat_Function_Return_NoARG(ctx);
    }

    @Override
    public AST visitVoid_Function_NOReturn_ARG(projectParser.Void_Function_NOReturn_ARGContext ctx) {
        return super.visitVoid_Function_NOReturn_ARG(ctx);
    }

    @Override
    public AST visitInt_Function_Return_ARG(projectParser.Int_Function_Return_ARGContext ctx) {
        return super.visitInt_Function_Return_ARG(ctx);
    }

    @Override
    public AST visitString_Function_Return_ARG(projectParser.String_Function_Return_ARGContext ctx) {
        return super.visitString_Function_Return_ARG(ctx);
    }

    @Override
    public AST visitDouble_Function_Return_ARG(projectParser.Double_Function_Return_ARGContext ctx) {
        return super.visitDouble_Function_Return_ARG(ctx);
    }

    @Override
    public AST visitFloate_Function_Return_ARG(projectParser.Floate_Function_Return_ARGContext ctx) {
        return super.visitFloate_Function_Return_ARG(ctx);
    }

    @Override
    public AST visitArgumentFunctionType(projectParser.ArgumentFunctionTypeContext ctx) {
        return super.visitArgumentFunctionType(ctx);
    }

    @Override
    public AST visitReturnStatement(projectParser.ReturnStatementContext ctx) {
        return super.visitReturnStatement(ctx);
    }

    @Override
    public Classes visitClassS(projectParser.ClassSContext ctx) {
       System.out.println("visitClasses");
        Classes clas = new Classes();
        if(ctx.classstatement() != null){
            clas.setClassStatement(visitClassstatement(ctx.classstatement()));
        }
        else if(ctx.abstractstatment() != null){
            clas.setAbstractStatment(visitAbstractstatment(ctx.abstractstatment()));
        }
        else if(ctx.extendsClass() != null){
            clas.setExtendsClass(visitExtendsClass(ctx.extendsClass()));
        }
        return clas;
    }

    @Override
    public ClassStatement visitClassstatement(projectParser.ClassstatementContext ctx) {
        System.out.println("VisitClassStatment");
        ClassStatement clas = new ClassStatement();
        clas.setClas(ctx.CLASS().toString().trim());
        clas.setId(ctx.ID().toString().trim());
        for(int i=0; i<ctx.inputclass().size() ; i++){
//            clas.setInputclasses(visitInputclass(ctx.inputclass().get(i)));
             clas.getInputclasses().add(visitInputclass(ctx.inputclass(i)));
        }
        return clas;
    }

    @Override
    public AbstractStatment visitAbstractstatment(projectParser.AbstractstatmentContext ctx) {
         AbstractStatment abclas = new AbstractStatment();
         abclas.setAbstr(ctx.ABSTRACT().toString());
        abclas.setClas(ctx.CLASS().toString());
         abclas.setId(ctx.ID().toString());
        return abclas;
    }

    @Override
    public ExtendsClass visitExtendsClass(projectParser.ExtendsClassContext ctx) {
        ExtendsClass extclas = new ExtendsClass();
        /////////
        return extclas;
    }

    @Override
    public AST visitExtends_ClassStatment_Withinput(projectParser.Extends_ClassStatment_WithinputContext ctx) {
        return super.visitExtends_ClassStatment_Withinput(ctx);
    }

    @Override
    public AST visitExtends_ClassStatment_Withoutinput(projectParser.Extends_ClassStatment_WithoutinputContext ctx) {
        return super.visitExtends_ClassStatment_Withoutinput(ctx);
    }

    @Override
    public AST visitExtends_ClassStateFull(projectParser.Extends_ClassStateFullContext ctx) {
        return super.visitExtends_ClassStateFull(ctx);
    }

    @Override
    public AST visitExtends_ClassStateLess(projectParser.Extends_ClassStateLessContext ctx) {
        return super.visitExtends_ClassStateLess(ctx);
    }

    @Override
    public AST visitExtends_ClassStateFull_WithAppBar(projectParser.Extends_ClassStateFull_WithAppBarContext ctx) {
        return super.visitExtends_ClassStateFull_WithAppBar(ctx);
    }

    @Override
    public AST visitExtends_ClassStateLess_WithAppBar(projectParser.Extends_ClassStateLess_WithAppBarContext ctx) {
        return super.visitExtends_ClassStateLess_WithAppBar(ctx);
    }

    @Override
    public AST visitExtends_ClassStateFullwithinput(projectParser.Extends_ClassStateFullwithinputContext ctx) {
        return super.visitExtends_ClassStateFullwithinput(ctx);
    }

    @Override
    public AST visitExtends_ClassStateLesswithinput(projectParser.Extends_ClassStateLesswithinputContext ctx) {
        return super.visitExtends_ClassStateLesswithinput(ctx);
    }

    @Override
    public AST visitExtends_ClassStateFullwithinput_appBar(projectParser.Extends_ClassStateFullwithinput_appBarContext ctx) {
        return super.visitExtends_ClassStateFullwithinput_appBar(ctx);
    }

    @Override
    public AST visitExtends_ClassStateLesswithinput_appBar(projectParser.Extends_ClassStateLesswithinput_appBarContext ctx) {
        return super.visitExtends_ClassStateLesswithinput_appBar(ctx);
    }

    @Override
    public AST visitInput_AppBar(projectParser.Input_AppBarContext ctx) {
        return super.visitInput_AppBar(ctx);
    }

    @Override
    public AST visitColor(projectParser.ColorContext ctx) {
        return super.visitColor(ctx);
    }


    @Override
    public AST visitRowStatement(projectParser.RowStatementContext ctx) {
        return super.visitRowStatement(ctx);
    }

    @Override
    public AST visitColStatemenst(projectParser.ColStatemenstContext ctx) {
        return super.visitColStatemenst(ctx);
    }

    @Override
    public AST visitTexeStatement(projectParser.TexeStatementContext ctx) {
        return super.visitTexeStatement(ctx);
    }

    @Override
    public AST visitText_INPUT_D_Q_N(projectParser.Text_INPUT_D_Q_NContext ctx) {
        return super.visitText_INPUT_D_Q_N(ctx);
    }

    @Override
    public AST visitText_INPUT_D_Q_I(projectParser.Text_INPUT_D_Q_IContext ctx) {
        return super.visitText_INPUT_D_Q_I(ctx);
    }

    @Override
    public AST visitText_rule(projectParser.Text_ruleContext ctx) {
        return super.visitText_rule(ctx);
    }

    @Override
    public AST visitSizedBox(projectParser.SizedBoxContext ctx) {
        return super.visitSizedBox(ctx);
    }

    @Override
    public AST visitHight_SizedBox(projectParser.Hight_SizedBoxContext ctx) {
        return super.visitHight_SizedBox(ctx);
    }

    @Override
    public AST visitWidth_SizedBox(projectParser.Width_SizedBoxContext ctx) {
        return super.visitWidth_SizedBox(ctx);
    }

    @Override
    public AST visitWidthHight_SizedBox(projectParser.WidthHight_SizedBoxContext ctx) {
        return super.visitWidthHight_SizedBox(ctx);
    }

    @Override
    public AST visitHightWidth_SizedBox(projectParser.HightWidth_SizedBoxContext ctx) {
        return super.visitHightWidth_SizedBox(ctx);
    }

    @Override
    public AST visitTextButton(projectParser.TextButtonContext ctx) {
        return super.visitTextButton(ctx);
    }

    @Override
    public AST visitImageState(projectParser.ImageStateContext ctx) {
        return super.visitImageState(ctx);
    }

    @Override
    public AST visitImage_withAssetImage(projectParser.Image_withAssetImageContext ctx) {
        return super.visitImage_withAssetImage(ctx);
    }

    @Override
    public AST visitImage_withAssetImage_WithDirection(projectParser.Image_withAssetImage_WithDirectionContext ctx) {
        return super.visitImage_withAssetImage_WithDirection(ctx);
    }

    @Override
    public AST visitImage_withAssetImage_WithDirection_fit(projectParser.Image_withAssetImage_WithDirection_fitContext ctx) {
        return super.visitImage_withAssetImage_WithDirection_fit(ctx);
    }

    @Override
    public AST visitImage_withAsset(projectParser.Image_withAssetContext ctx) {
        return super.visitImage_withAsset(ctx);
    }

    @Override
    public AST visitImage_withNetWoek(projectParser.Image_withNetWoekContext ctx) {
        return super.visitImage_withNetWoek(ctx);
    }

    @Override
    public AST visitImage_withFile(projectParser.Image_withFileContext ctx) {
        return super.visitImage_withFile(ctx);
    }

    @Override
    public AST visitImageDirection(projectParser.ImageDirectionContext ctx) {
        return super.visitImageDirection(ctx);
    }

    @Override
    public AST visitImageFit(projectParser.ImageFitContext ctx) {
        return super.visitImageFit(ctx);
    }

    @Override
    public AST visitExpandedStatment(projectParser.ExpandedStatmentContext ctx) {
        return super.visitExpandedStatment(ctx);
    }

    @Override
    public AST visitExpanded_withCol(projectParser.Expanded_withColContext ctx) {
        return super.visitExpanded_withCol(ctx);
    }

    @Override
    public AST visitExpanded_withRow(projectParser.Expanded_withRowContext ctx) {
        return super.visitExpanded_withRow(ctx);
    }

    @Override
    public AST visitPaddingStatement(projectParser.PaddingStatementContext ctx) {
        return super.visitPaddingStatement(ctx);
    }

    @Override
    public AST visitPadding_all(projectParser.Padding_allContext ctx) {
        return super.visitPadding_all(ctx);
    }

    @Override
    public AST visitPadding_sym_vertical_num(projectParser.Padding_sym_vertical_numContext ctx) {
        return super.visitPadding_sym_vertical_num(ctx);
    }

    @Override
    public AST visitPadding_sym_vertical_numFloat(projectParser.Padding_sym_vertical_numFloatContext ctx) {
        return super.visitPadding_sym_vertical_numFloat(ctx);
    }

    @Override
    public AST visitPadding_sym_horisantal_num(projectParser.Padding_sym_horisantal_numContext ctx) {
        return super.visitPadding_sym_horisantal_num(ctx);
    }

    @Override
    public AST visitPadding_sym_horisantal_numFloat(projectParser.Padding_sym_horisantal_numFloatContext ctx) {
        return super.visitPadding_sym_horisantal_numFloat(ctx);
    }

    @Override
    public AST visitPadding_only(projectParser.Padding_onlyContext ctx) {
        return super.visitPadding_only(ctx);
    }

    @Override
    public AST visitDirection(projectParser.DirectionContext ctx) {
        return super.visitDirection(ctx);
    }

    @Override
    public AST visitFlutterWidget(projectParser.FlutterWidgetContext ctx) {
        return super.visitFlutterWidget(ctx);
    }

    @Override
    public Input visitInputI(projectParser.InputIContext ctx) {
        Input input = new Input();
        if(ctx.initial() != null){
            input.setInitial(visitInitial(ctx.initial()));
        }
        if(ctx.ifstatement() != null){
            input.setIfStatment(visitIfstatement(ctx.ifstatement()));
        }
        if(ctx.loop() != null){
            input.setLoop(visitLoop(ctx.loop()));
        }
        if(ctx.doWhilestatement() != null){
            input.setDoWhileStatement(visitDoWhilestatement(ctx.doWhilestatement()));
        }
        if(ctx.whilestatemen() != null){
            input.setWhileStatement(visitWhilestatemen(ctx.whilestatemen()));
        }
        if(ctx.printstatement() != null){
            input.setPrintStatement(visitPrintstatement(ctx.printstatement()));
        }
        if(ctx.switchstatement() != null){
            input.setSwitchStatement(visitSwitchstatement(ctx.switchstatement()));
        }
        if(ctx.tryCatchstatement() != null){
            input.setTryCatchStatement(visitTryCatchstatement(ctx.tryCatchstatement()));
        }
        return input;
    }

    @Override
    public InputClasses visitInputclass(projectParser.InputclassContext ctx) {
        System.out.println("visitInputClass");
        InputClasses input = new InputClasses();
        if(ctx.initial() != null){
            input.setInitial(visitInitial(ctx.initial()));
        }
        else if(ctx.ifstatement() != null){
            input.setIfStatment(visitIfstatement(ctx.ifstatement()));
        }
        else if(ctx.loop() != null){
            input.setLoop(visitLoop(ctx.loop()));
        }
        else if(ctx.doWhilestatement() != null){
            input.setDoWhileStatement(visitDoWhilestatement(ctx.doWhilestatement()));
        }
        else if(ctx.whilestatemen() != null){
            input.setWhileStatement(visitWhilestatemen(ctx.whilestatemen()));
        }
        else if(ctx.printstatement() != null){
            input.setPrintStatement(visitPrintstatement(ctx.printstatement()));
        }
        else if(ctx.switchstatement() != null){
            input.setSwitchStatement(visitSwitchstatement(ctx.switchstatement()));
        }
        else if(ctx.tryCatchstatement() != null){
            input.setTryCatchStatement(visitTryCatchstatement(ctx.tryCatchstatement()));
        }
        return input;
    }

    @Override
    public AST visitIfif(projectParser.IfifContext ctx) {
        return super.visitIfif(ctx);
    }

    @Override
    public AST visitElseifif(projectParser.ElseififContext ctx) {
        return super.visitElseifif(ctx);
    }

    @Override
    public AST visitElseelse(projectParser.ElseelseContext ctx) {
        return super.visitElseelse(ctx);
    }

    @Override
    public AST visitElseif_WithInput(projectParser.Elseif_WithInputContext ctx) {
        return super.visitElseif_WithInput(ctx);
    }

    @Override
    public AST visitElseif_WithBreak(projectParser.Elseif_WithBreakContext ctx) {
        return super.visitElseif_WithBreak(ctx);
    }

    @Override
    public AST visitElseif_WithContinue(projectParser.Elseif_WithContinueContext ctx) {
        return super.visitElseif_WithContinue(ctx);
    }

    @Override
    public AST visitElse_WithInput(projectParser.Else_WithInputContext ctx) {
        return super.visitElse_WithInput(ctx);
    }

    @Override
    public AST visitElse_WithBreak(projectParser.Else_WithBreakContext ctx) {
        return super.visitElse_WithBreak(ctx);
    }

    @Override
    public AST visitElse_WithContinue(projectParser.Else_WithContinueContext ctx) {
        return super.visitElse_WithContinue(ctx);
    }

    @Override
    public AST visitContainerStatement(projectParser.ContainerStatementContext ctx) {
        return super.visitContainerStatement(ctx);
    }
}

