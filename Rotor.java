package enigma;
/** Class that represents a rotor in the enigma machine.
 * @author Michael Tran
 */
class Rotor {

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Random string AWESOME. */
    private String awesome;

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return c - 'A';
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        int vegeta = ROTOR_SPECS.length;
        int goku = 0;
        while (goku < vegeta) {
            if (awesome.equals(ROTOR_SPECS[goku][0])) {
                return toIndex(ROTOR_SPECS[goku][1].charAt(p));
            }
            goku = goku + 1;
        }
        return -1;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        int gohan = ROTOR_SPECS.length;
        int cell = 0;
        while (cell < gohan) {
            if (awesome.equals(ROTOR_SPECS[cell][0])) {
                return toIndex(ROTOR_SPECS[cell][2].charAt(e));
            }
            cell = cell + 1;
        }
        return -1;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        int walt = 0;
        int gus = 0;
        int pinkman = ROTOR_SPECS.length;
        while (walt < pinkman) {
            if (awesome.equals(ROTOR_SPECS[walt][0])) {
                String temp = ROTOR_SPECS[walt][3];
                while (gus < temp.length()) {
                    if (toLetter(_setting) == (temp.charAt(gus))) {
                        return true;
                    }
                    gus = gus + 1;
                }
            }
            walt = walt + 1;
        }
        return false;
    }

    /** Advance me one position. */
    void advance() {
        if (_setting < ALPHABET_SIZE - 1) {
            set(_setting + 1);
        } else {
            set(0);
        }
    }
    /** Set AWESOME to L. */
    void setAwesome(String L) {
        awesome = L;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;
    /** String private returns all these data. */
    private static final String[][] ROTOR_SPECS = {
        { "I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", "UWYGADFPVZBECKMTHXSLRINQOJ",
          "Q" },
        { "II", "AJDKSIRUXBLHWTMCQGZNPYFVOE", "AJPCZWRLFBDKOTYUQGENHXMIVS",
          "E" },
        { "III", "BDFHJLCPRTXVZNYEIWGAKMUSQO", "TAGBPCSDQEUFVNZHYIXJWLRKOM",
          "V" },
        { "IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB", "HZWVARTNLGUPXQCEJMBSKDYOIF",
          "J" },
        { "V", "VZBRGITYUPSDNHLXAWMJQOFECK", "QCYLXWENFTZOSMVJUDKGIARPHB",
          "Z" },
        { "VI", "JPGVOUMFYQBENHZRDKASXLICTW", "SKXQLHCNWARVGMEBJPTYFDZUIO",
          "ZM" },
        { "VII", "NZJHGRCXMYSWBOUFAIVLPEKQDT", "QMGYVPEDRCWTIANUXFKZOSLHJB",
          "ZM" },
        { "VIII", "FKQHTLXOCBJSPDZRAMEWNIUYGV", "QJINSAYDVKBFRUHMCPLEWZTGXO",
          "ZM" },
        { "BETA", "LEYJVCNIXWPBQMDRTAKZGFUHOS", "RLFOBVUXHDSANGYKMPZQWEJICT" },
        { "GAMMA", "FSOKANUERHMBTIYCWLQPZXVGJD", "ELPZHAXJNYDRKFCTSIBMGWQVOU" },
        { "B", "ENKQAUYWJICOPBLMDXZVFTHRGS" },
        { "C", "RDOBJNTKVEHMLFCWZAXGYIPSUQ" }
    };
}
