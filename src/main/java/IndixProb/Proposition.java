/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndixProb;

import IndixProb.ParserException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdhandap
 */
public class Proposition {

    int numVars = 0;
    String expr;
    HashMap <Character, Integer> varMap;

   

    private String deleteSpaces(String expr) {
        String retString = "";
        for(int i = 0; i < expr.length();i++)
        {
            if(expr.charAt(i) != ' '){
                retString = retString +expr.charAt(i); 
            }
        }
        return retString;
        
    }

    Proposition(String boolExpr) throws ParserException {

        expr = deleteSpaces(boolExpr);
        varMap = new HashMap <Character, Integer> ();
        int braceCount = 0;
        int numOps = 0;

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch == '(') {
                braceCount++;
            } else if (ch == ')') {
                braceCount--;
            } else if (ch == '&' || ch == '|' || ch == '!') {
                numOps++;
            } else if (ch >= 'a' && ch <= 'z') {
                
                if (varMap.get(ch) == null) {
                    numVars++;
                if (numVars > 10) {
                    throw new ParserException(" variables > 10");
                }
                    varMap.put(ch, numVars-1);
                }
            } else {
                throw new ParserException("Unrecognized chars in expr");
            }

        }
      
//        System.out.println(expr);
//        System.out.println("numVars ="+numVars);
//        System.out.println("varmap size ="+varMap.size());
    }

    @Override
    public String toString() {
        return expr;
    }

    public int getNumVars() {
        return numVars;
    }

    public int getVarPos(char varCh) {
        return varMap.get(varCh);
    }

   

}
