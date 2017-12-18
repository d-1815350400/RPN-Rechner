import org.junit.Test;
import static org.junit.Assert.*;

public class RechnerTest {
    @Test
    public void testSkalar() {
        Rechner rechner = new Rechner();
        double d = rechner.berechne("5");

        assertEquals(5.0, d, .00000000001);
    }

    @Test
    public void testAddition() {
        Rechner rechner = new Rechner();
        double d = rechner.berechne("2 4 +");

        assertEquals(6.0, d, .00000000001);
    }

    @Test
    public void testLeerzeichenUnanhaengig() {
        Rechner rechner = new Rechner();
        double d = rechner.berechne("    2   4  +  ");

        assertEquals(6.0, d, .00000000001);
    }

    @Test
    public void testSubtraktion() {
        Rechner rechner = new Rechner();
        double d = rechner.berechne("4 1 -");

        assertEquals(3.0, d, .00000000001);
    }

    @Test
    public void testVerschachtelterAusdruck() {
        Rechner rechner = new Rechner();
        double d = rechner.berechne("3 5 1 - *");

        assertEquals(12.0, d, .00000000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZuWenigArgumente() {
        Rechner rechner = new Rechner();
        rechner.berechne("5 +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZuVieleArgumente() {
        Rechner rechner = new Rechner();
        rechner.berechne("1 5 2 +");
    }
}