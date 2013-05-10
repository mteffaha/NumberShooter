package gameEngine;

/**
 * User: mteffaha
 * Date: 3/15/13
 * Time: 3:32 PM
 *
 * A class that will hold an abstract representation of the equation passed by the Logic Module of the game
 */
public class RenderEquation {
    private Integer leftOperand;
    private Integer rightOperand;
    private Integer result;
    private Operator operator;

    public RenderEquation(){
        leftOperand = null;
        rightOperand = null;
        result = null;
        operator = Operator.ADDITION;
    }
    public RenderEquation(Integer lop,Integer rop,Integer res,Operator oper){
        leftOperand = lop;
        rightOperand = rop;
        result = res;
        operator = oper;
    }

    public Integer getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(Integer leftOperand) {
        this.leftOperand = leftOperand;
    }

    public Integer getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(Integer rightOperand) {
        this.rightOperand = rightOperand;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String toString(){
        String equation = " ";
        if(leftOperand != null){
            equation+=leftOperand+" ";
        }else{
            equation+= " ?? ";
        }

        equation+=operator.getSymbol()+" ";

        if(rightOperand != null){
            equation+=rightOperand+" ";
        }else{
            equation+= " ?? ";
        }

        equation += " = ";

        if(result != null){
            equation+=result+" ";
        }else{
            equation+= " ?? ";
        }
        return equation;
    }
}


enum Operator{
    ADDITION("+"),
    SUBSTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private String symbol;
    private Operator(String symbol){
      this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}
