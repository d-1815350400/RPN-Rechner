import java.util.NoSuchElementException;

public interface Operator {
    public Double wendeAn(Stack<Double> stack) throws NoSuchElementException, IllegalArgumentException;
}