/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndixProb;

import IndixProb.Proposition;

import java.util.Stack;

public class PolishEvaluator extends PropositionEvaluator {

    String valueOfVars;
    Proposition propEvaluated;

    PolishEvaluator(Proposition prop) {
        propEvaluated = prop;
    }

    public int applyOp(char op, int a) {
        boolean aboolean = (1 == a);
        boolean result;
        switch (op) {
            case '!':
                result = !aboolean;
                return (result == true) ? 1 : 0;

        }
        return -1;
    }

    // A utility method to apply an operator 'op' on operands 'a' 
    // and 'b'. Return the result.
    public int applyOp(char op, int b, int a) {
        boolean aboolean;
        boolean bboolean;
        boolean result;
        aboolean = (1 == a);
        bboolean = (1 == b);
        switch (op) {
            case '&':
                result = aboolean & bboolean;
                return (result == true) ? 1 : 0;
            case '|':
                result = aboolean | bboolean;
                return (result == true) ? 1 : 0;

        }
        return -1;
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    // op1 = ! and op2  = | return false
    public boolean hasPrecedence(char op1, char op2) {

        if (op2 == '(' || op2 == ')') {
            return false;
        }
        /* ! has higher precedence than & and | */
        if (((op2 == '!') && (op1 == '|')) || (op1 == '&')) {
            return true;
        } else if (((op2 == '|') || (op2 == '&')) && (op1 == '!')) {
            return false;
        }
        
        /* & has higher precedence than | */
       

        if ((op1 == '&' &&  op2 == '|') ) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isAlphabet(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return true;
        } else {
            return false;
        }
    }

    public boolean polishEval() {

        char[] tokens = propEvaluated.toString().toCharArray();
        String valueString = valueOfVars;

        Stack<Integer> values = new Stack<Integer>();

        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            if (isAlphabet(tokens[i])) {

                int bitValue = getVarValue(tokens[i]);

                values.push(bitValue);

            } else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                char opCh;
                while (ops.peek() != '(') {
                    opCh = ops.pop();
                    if (opCh == '&' || opCh == '|') {
                        values.push(applyOp(opCh, values.pop(), values.pop()));
                    } else if (opCh == '!') {
                        values.push(applyOp(opCh, values.pop()));
                    }

                }
                ops.pop();
            } else if (tokens[i] == '&' || tokens[i] == '|' || tokens[i] == '!') {
               
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    char opCh;
                    opCh = ops.pop();
                    if (opCh == '&' || opCh == '|') {
                        values.push(applyOp(opCh, values.pop(), values.pop()));
                    } else if (opCh == '!') {
                        values.push(applyOp(opCh, values.pop()));
                    }
                }

                ops.push(tokens[i]);
            }

        }

        char opCh;
        while (!ops.empty()) {
            opCh = ops.pop();
            if (opCh == '&' || opCh == '|') {
                values.push(applyOp(opCh, values.pop(), values.pop()));
            } else if (opCh == '!') {
                values.push(applyOp(opCh, values.pop()));
            }

        }

        return (values.pop() == 1);
    }

    public void setVariables(int value) {

        valueOfVars = Integer.toBinaryString(value);
        while (valueOfVars.length() < 10) // ensure that length of word is 32
        {
            valueOfVars = "0" + valueOfVars;
        }
        
      
    }

    public boolean evaluate(int value) {
        setVariables(value);

        return polishEval();
    }

    public int getVarValue(char varCh) {

        int pos = propEvaluated.getVarPos(varCh);
       
        return Character.getNumericValue(valueOfVars.charAt(9-pos));
    }

}
