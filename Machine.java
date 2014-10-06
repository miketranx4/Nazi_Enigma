package enigma;

/** Class that represents a complete enigma machine.
 *  @author Michael Tran
 */
class Machine {
    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Rotor holds all the ROTIS machines. */
    private Rotor[] rotis = new Rotor[5];
    /** add function returns sum of B and C. */
    int add(int B, int C) {
        return (B % C + C) % C;
    }
    /** makeRotors will set ROTORS to machine with X and Y. */
    void makeRotors(Rotor X, int Y) {
        rotis[Y] = X;
    }
    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        rotis[1].set(setting.charAt(0) - 'A');
        rotis[2].set(setting.charAt(1) - 'A');
        rotis[3].set(setting.charAt(2) - 'A');
        rotis[4].set(setting.charAt(3) - 'A');
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String sgin = "";
        for (int m = 0; m < msg.length(); m++) {
            int freeiza = msg.charAt(m) - 'A';
            if (rotis[3].atNotch()) {
                rotis[2].advance();
                rotis[3].advance();
                rotis[4].advance();
            } else if (rotis[4].atNotch()) {
                rotis[3].advance();
                rotis[4].advance();
            } else {
                rotis[4].advance();
            }
            freeiza = moveProcess(freeiza, 4, true);
            freeiza = moveProcess(freeiza, 3, true);
            freeiza = moveProcess(freeiza, 2, true);
            freeiza = moveProcess(freeiza, 1, true);
            freeiza = moveProcess(freeiza, 0, true);
            freeiza = moveProcess(freeiza, 1, false);
            freeiza = moveProcess(freeiza, 2, false);
            freeiza = moveProcess(freeiza, 3, false);
            freeiza = moveProcess(freeiza, 4, false);
            char chAdd = (char) (freeiza + 'A');
            sgin = sgin + chAdd;
        }
        return sgin;
    }
    /** moves onto the next rotor Returns using TH B and BLAH. */
    public int moveProcess(int th, int b, boolean blah) {
        th = add(th + rotis[b].getSetting(), ALPHABET_SIZE);
        if (blah) {
            th = rotis[b].convertForward(th);
        } else {
            th = rotis[b].convertBackward(th);
        }
        th = add(th - rotis[b].getSetting(), ALPHABET_SIZE);
        return th;
    }
}
