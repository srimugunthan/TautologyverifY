package IndixProb;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Test;

public class Test1 {

    @Test
    public void test1Tautology() {

        try {
            String expr = "(a & (!b | b)) | (!a & (!b | b))";
            TautologyVerifier verifier = new TautologyVerifier(expr);
            Assert.assertEquals(verifier.verify(), true);
        } catch (ParserException ex) {
            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void test2Tautology() {

        try {
            String expr = "!a & !b";
            TautologyVerifier verifier = new TautologyVerifier(expr);
            Assert.assertEquals(verifier.verify(), false);
        } catch (ParserException ex) {
            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testTautology() {
        String inputArr[] = {"a",
            "a & b",
            "a & (b | c)",
            "!a & !b",
            "a | !a",
            "(a & (!b | b)) | (!a & (!b | b))"};

        boolean outArr[] = {false, false, false, false, true, true};
        
        for (int i = 0; i < inputArr.length; i++) {
            try {
                String expr = inputArr[i];
                TautologyVerifier verifier = new TautologyVerifier(expr);
                Assert.assertEquals(verifier.verify(), outArr[i]);
            } catch (ParserException ex) {
                Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
