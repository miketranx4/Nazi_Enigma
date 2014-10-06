package enigma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** Enigma simulator.
 *  @author Michael Tran
 */
public final class Main {
    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) {
        Machine M = null;
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

        int alpha = 0;
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    alpha = 1;
                    M = new Machine();
                    configure(M, line);
                } else {
                    if (alpha == 0) {
                        System.exit(1);
                    }
                    if (line.equals("")) {
                        System.out.println("");
                    } else {
                        printMessageLine(M.convert(standardize(line)));
                    }
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        String[] something = line.split("\\s+");
        if (!(something[0].equals("*"))) {
            return false;
        }
        if (!(something[1].equals("B")) && !(something[1].equals("C"))) {
            return false;
        }
        if (!(something[2].equals("BETA")) && !(something[2].equals("GAMMA"))) {
            return false;
        }
        if (!(something[3].equals("I")) && !(something[3].equals("II"))
            &&
            !(something[3].equals("III")) && !(something[3].equals("IV"))
            &&
            !(something[3].equals("V")) && !(something[3].equals("VI"))
            &&
            !(something[3].equals("VII")) && !(something[3].equals("VIII"))) {
            return false;
        }
        if (!(something[4].equals("I")) && !(something[4].equals("II"))
            &&
            !(something[4].equals("III")) && !(something[4].equals("IV"))
            &&
            !(something[4].equals("V")) && !(something[4].equals("VI"))
            &&
            !(something[4].equals("VII")) && !(something[4].equals("VIII"))) {
            return false;
        }
        if (!(something[5].equals("I")) && !(something[5].equals("II"))
            &&
            !(something[5].equals("III")) && !(something[5].equals("IV"))
            &&
            !(something[5].equals("V")) && !(something[5].equals("VI"))
            &&
            !(something[5].equals("VII"))
            && !(something[5].equals("VIII"))) {
            return false;
        }
        if ((something[6].length() != 4)
            || (!(something[6].matches("[A-Z]+")))) {
            return false;
        }
        if (something.length != 7) {
            return false;
        }
        if (something[3].equals(something[4])
            || something[4].equals(something[5])
            || something[3].equals(something[5])) {
            return false;
        }
        return true;
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        Rotor rotor1 = new Rotor();
        Rotor rotor2 = new Rotor();
        Rotor rotor3 = new Rotor();
        Rotor rotor4 = new Rotor();
        Rotor rotor5 = new Rotor();
        String[] dude = config.split("\\s+");
        rotor1.setAwesome(dude[1]);
        rotor2.setAwesome(dude[2]);
        rotor3.setAwesome(dude[3]);
        rotor4.setAwesome(dude[4]);
        rotor5.setAwesome(dude[5]);
        M.makeRotors(rotor1, 0);
        M.makeRotors(rotor2, 1);
        M.makeRotors(rotor3, 2);
        M.makeRotors(rotor4, 3);
        M.makeRotors(rotor5, 4);
        M.setRotors(dude[6]);
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        if ((line.replaceAll("\\s+", "").matches("[a-zA-Z ]+"))) {
            return line.replaceAll("\\s+", "").toUpperCase();
        } else {
            System.exit(1);
        }
        return null;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private static void printMessageLine(String msg) {
        String hells = "";
        int kitchen = msg.length();
        int ishere = 0;
        while (ishere < kitchen) {
            hells = hells + msg.charAt(ishere);
            if ((ishere + 1) % 5 == 0) {
                hells = hells + " ";
            }
            ishere = ishere + 1;
        }
        System.out.println(hells);
    }
}

