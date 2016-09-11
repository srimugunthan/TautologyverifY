/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndixProb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdhandap
 */
public class TautologyTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserException {
        try {

            InputStream inputStream
                    = TautologyTester.class.getClassLoader().getResourceAsStream("in.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;

            int count = 0;
            while ((line = br.readLine()) != null) {
                System.out.println((line));
                TautologyVerifier verifier = new TautologyVerifier(line);

                System.out.println(verifier.verify());

            }

        } catch (IOException f) {
            System.out.println("IO exception");
        }

    }
}
