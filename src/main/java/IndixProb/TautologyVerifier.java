/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndixProb;

import IndixProb.Proposition;

/**
 *
 * @author sdhandap
 */
public class TautologyVerifier {
    
    Proposition prop;
    PropositionEvaluator eval;
    
    TautologyVerifier(String expr) throws ParserException {
        prop = new Proposition(expr);
        eval = new PolishEvaluator(prop);

    }

    public boolean verify() {
        int expCount = (1 << prop.getNumVars());
        for (int i = 0; i < expCount; i++) {
            if (eval.evaluate(i) == false) {
                return false;
            }

        }
        return true;
    }

}
