package org.unice.polytech.si3.devint.teffaha.numbershooter.equation;

import org.unice.polytech.si3.devint.teffaha.numbershooter.core.UnvalidEquationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author TEFFAHA Mortadha
 */
public class EquationFactory {
    private static EquationFactory instance;

    public static EquationFactory getInstance() {
        if(instance == null){
            instance =  new EquationFactory();
        }
        return instance;
    }


    private EquationFactory(){

    }

    public Equation getEquation(int level){
        switch((level / 3 )){
            case 0:
                return calculateEquation(getRandomOperator(false),20);
            case 1:
                return calculateEquation(getRandomOperator(false),40);
            case 2:
                return calculateEquation(getRandomOperator(true),80);
            default:
                return calculateEquation(getRandomOperator(true),100);
        }
    }

    public List<Integer> getProposition(int solution,int number){
        ArrayList<Integer> listProposition =  new ArrayList<Integer>();
        boolean propPut = false;
        for(int i=0;i<number;i++){
            int start = solution-number;
            start = (start >= 0)?start:0;
            int end = solution +number;
            end = (end <= 99)?end:99;
            Integer rand = getRandomInt(start,end);
            if(rand.equals(propPut)){
                propPut = true;
            }
            listProposition.add(rand);
        }
        if(!propPut){
            listProposition.set(getRandomInt(number),solution);
        }

        return listProposition;

    }
    private Equation calculateEquation(Operator operator,int max){
        Equation equation = new Equation(operator);
        try {

            while(equation.getResult() == null){
                equation.setLeft(getRandomInt(max));
                equation.setRight(getRandomInt(max));
                equation.computeResult();
            }

            return equation;
        } catch (UnvalidEquationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }



    public Operator getRandomOperator(boolean withDivAndMul){
        if(withDivAndMul){
            switch (getRandomInt(4)){
                case  0:
                    return Operator.ADDITION;
                case 1:
                    return Operator.SUBSTRATION;
                case 2:
                    return Operator.MULTIPLICATION;
                case 3:
                    return Operator.DIVISION;
            }
        }else{
            switch (getRandomInt(2)){
                case  0:
                    return Operator.ADDITION;
                case 1:
                    return Operator.SUBSTRATION;
            }

        }
        return null;
    }


    private int getRandomInt(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    private int getRandomInt(int min,int max){
        return getRandomInt(max-min)+min;
    }


    public static void main(String[] args){
        for(int i=0;i<100;i++){
            Equation eq =  EquationFactory.getInstance().getEquation(i);
            System.out.println("Level : "+i+" Equation : "+eq+" Propositions:"+listToSTring(getInstance().getProposition(eq.getRight(),4)));
        }
    }


    public static String listToSTring(List<Integer> list){
        String str ="";
        for(int i=0;i<list.size();i++){
            str+="["+list.get(i)+"]";
        }
        return str;
    }
}
                                  