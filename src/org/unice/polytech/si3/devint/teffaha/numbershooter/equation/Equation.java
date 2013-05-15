package org.unice.polytech.si3.devint.teffaha.numbershooter.equation;

import org.unice.polytech.si3.devint.teffaha.numbershooter.core.UnvalidEquationException;

/**
 * @author TEFFAHA Mortadha
 */
public class Equation {
    private Integer left;
    private Integer right;
    private Operator operator;
    private Integer result;



    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) throws  UnvalidEquationException{
        if(notValidNumber(left)){
            throw new UnvalidEquationException();
        }
        this.left = left;
    }

    private boolean notValidNumber(Integer left) {

        if(left < 0 || left >= 100){
            return true;
        }else{
            return false;
        }
    }


    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) throws  UnvalidEquationException {
        if(notValidNumber(left)){
            throw new UnvalidEquationException();
        }
        this.right = right;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result)  throws  UnvalidEquationException{
        if(notValidNumber(left)){
            throw new UnvalidEquationException();
        }
        this.result = result;
    }


    /**
     * constrcutor with all the possible parameters
     * @param left
     * @param right
     * @param operator
     */
    public Equation(int left, int right, Operator operator) throws UnvalidEquationException {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.result = result;
        computeResult();
        if(result == null){
            throw new UnvalidEquationException();
        }
    }

    public Equation(Operator operator) {
        this.operator = operator;
        this.left = null;
        this.right = null;
    }


    /**
     * Calculate the result of the equation if the equation is valid (all members are between 0 and 99)
     * and store either the result if valid or null othewise
     */
    public void computeResult() {
        int res = -1;
        if (left != null && right != null) {
            switch (operator) {
                case ADDITION:
                    res = left + right;
                    if (res < 100) {
                        result = res;
                    } else {
                        result = null;
                        return;
                    }
                    break;
                case SUBSTRATION:
                    res = left - right;
                    if(res >= 0){
                        result = res;
                    }else{
                        result = null;
                        return;
                    }
                    break;
                case MULTIPLICATION:
                    res = left * right;
                    if(res < 100){
                        result = res;
                    }else{
                        result = null;
                        return;
                    }
                    break;
                case DIVISION:
                    if(right > 0){
                        result = left / right;

                    }else{
                        result = null;
                        return;
                    }
            }
        } else {
            result = null;
        }
    }

    public String toString(){
        String str= "";
        if(left != null){
            str+= left+" ";
        }else{
            str+=" ";
        }
        switch(operator){
            case ADDITION:
                str+=" + ";
                break;
            case SUBSTRATION:
                str+=" - ";
                break;
            case DIVISION:
                str+=" / ";
                break;
            case MULTIPLICATION:
                str+=" * ";
                break;
        }

        str+=" ? ";

        str+=" = ";

        if(result != null){
            str+= result+" ";
        }else{
            str+=" ";
        }

        return str;

    }



    public boolean isValidResponse(int proposition) {
        if (left != null && result != null) {
             Integer tmpResult = this.result;
             Integer tmpRight = this.right;
            this.right = proposition;
            computeResult();
            if(this.result != null && tmpResult != null && this.result.equals(tmpResult)){
                this.result = tmpResult;
                this.right = tmpRight;
                return true;
            }else{
                this.result = tmpResult;
                this.right = tmpRight;
                return false;
            }

        }
        return false;
    }
}



