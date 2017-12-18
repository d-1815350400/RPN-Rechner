import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testSkalar() {
        double d = App.berechne("5");

        assertEquals(5.0, d, .00000000001);
    }

    @Test
    public void testAddition() {
        double d = App.berechne("2 4 +");

        assertEquals(6.0, d, .00000000001);
    }

    @Test
    public void testLeerzeichenUnanhaengig() {
        double d = App.berechne("    2   4  +  ");

        assertEquals(6.0, d, .00000000001);
    }

    @Test
    public void testSubtraktion() {
        double d = App.berechne("4 1 -");

        assertEquals(3.0, d, .00000000001);
    }

    @Test
    public void testVerschachtelterAusdruck() {
        double d = App.berechne("3 5 1 - *");

        assertEquals(12.0, d, .00000000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZuWenigArgumente() {
        App.berechne("5 +");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZuVieleArgumente() {
        App.berechne("1 5 2 +");
    }
}