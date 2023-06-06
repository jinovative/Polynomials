import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PolynomialImplTest {

  @Test
  public void testAddTerm() {
    Polynomial polynomial = new PolynomialImpl();
    polynomial.addTerm(3, 2);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(4, 0);

    assertEquals(3, polynomial.getCoefficient(2));
    assertEquals(2, polynomial.getCoefficient(1));
    assertEquals(4, polynomial.getCoefficient(0));
    assertEquals(0, polynomial.getCoefficient(3));
  }

  @Test
  public void testRemoveTerm() {
    Polynomial polynomial = new PolynomialImpl("3x^2 + 2x + 4");
    polynomial.removeTerm(2);
    polynomial.removeTerm(0);

    assertEquals(0, polynomial.getCoefficient(2));
    assertEquals(0, polynomial.getCoefficient(1));
    assertEquals(0, polynomial.getCoefficient(0));
  }

  @Test
  public void testGetDegree() {
    Polynomial polynomial = new PolynomialImpl("3x^2 + 2x + 4");

    assertEquals(2, polynomial.getDegree());
  }

  @Test
  public void testEvaluate() {
    Polynomial polynomial = new PolynomialImpl("3x^2 + 2x + 4");

    assertEquals(12.0, polynomial.evaluate(2), 0.0001);
  }

  @Test
  public void testAdd() {
    Polynomial polynomial1 = new PolynomialImpl("3x^2 + 2x + 4");
    Polynomial polynomial2 = new PolynomialImpl("2x^2 - 1");

    Polynomial sum = polynomial1.add(polynomial2);

    assertEquals(0, sum.getCoefficient(1));
    assertEquals(5, sum.getCoefficient(2));
    assertEquals(0, sum.getCoefficient(0));
  }
}