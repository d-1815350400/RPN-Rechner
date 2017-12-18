import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Rechner {
    private HashMap<String, Operator> operatoren = new HashMap<>();

    public Rechner() {
        operatoren.put("+", (s) -> s.pop() + s.pop());
        operatoren.put("-", (s) -> - s.pop() + s.pop());
        operatoren.put("*", (s) -> s.pop() * s.pop());
        operatoren.put("/", (s) -> 1 / s.pop() * s.pop());
        operatoren.put("abs", (s) -> Math.abs(s.pop()));
        operatoren.put("^", (s) -> {
            Double exponent = s.pop();
            Double basis = s.pop();
            return Math.pow(basis, exponent);
        });
        operatoren.put("sin", (s) -> Math.sin(s.pop()));
        operatoren.put("cos", (s) -> Math.cos(s.pop()));
        operatoren.put("tan", (s) -> Math.tan(s.pop()));
        operatoren.put("PI", (s) -> Math.PI);
    }

    public static void main(String[] args) {
        Rechner rechner = new Rechner();

        // erzeugt einen Leser
        Scanner sc = new Scanner(System.in);

        // Aesthetik
        System.out.print(">> ");
        System.out.flush();

        while (sc.hasNextLine()) {
            // solange es einen naechsten String gibt

            // gib das Ergebnis dessen aus
            System.out.println(rechner.berechne(sc.nextLine()));

            // Aesthetik
            System.out.print(">> ");
            System.out.flush();
        }

        sc.close();
    }

    public Double evaluiere(String s) {
        return berechne(s);
    }

    public Double berechne(String s) {
        return berechne(s.trim().split("\\s+"));
    }

    private Double berechne(String[] symbole) throws IllegalArgumentException {
        Stack<Double> stack = new Stack<>();
        for (String symbol : symbole) {
            // pruefe ob der String in unserer HashMap der Operatoren ist
            // (null falls nicht)
            Operator op = operatoren.get(symbol);
            if (op == null) {
                // symbol ist kein Operator sondern eine Zahl

                // lege Zahl aud dem Stack ab
                stack.push(new Double(symbol));
            } else {
                try {
                    // versuche, den Operator auf den Stack anzuwenden
                    stack.push(op.wendeAn(stack));
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("Nicht genug Operanden fuer den " + symbol + " Operator");
                }
            }
        }

        if (stack.isEmpty()) {
            // falls kein Element auf dem Stapel uebrig bleibt
            throw new IllegalArgumentException();
        }

        // entferne das Ergebnis vom Stapel
        Double ergebnis = stack.pop();

        if (!stack.isEmpty()) {
            // falls danach noch Elemente auf Stapel sind,
            // der Ausdruck also nicht korrekt ausgewertet wurde
            throw new IllegalArgumentException();
        }

        return ergebnis;
    }
}
