public class PolynomialImpl implements Polynomial{
  private Node head;

  private static class Node {
    int coefficient;
    int power;
    Node next;

    Node(int coefficient, int power) {
      this.coefficient = coefficient;
      this.power = power;
      this.next = null;
    }
  }

  public PolynomialImpl() {
    this.head = null;
  }

  public PolynomialImpl(String polynomialString) {
    this.head = null;
    parsePolynomialString(polynomialString);
  }

  private void parsePolynomialString(String polynomialString) {
    String[] terms = polynomialString.split("\\s+");
    for (String term : terms) {
      String[] parts = term.split("x\\^");
      int coefficient;
      int power;
      if (parts[0].isEmpty()) {
        coefficient = 1;
      } else if (parts[0].equals("-")) {
        coefficient = -1;
      } else {
        coefficient = Integer.parseInt(parts[0]);
      }
      power = Integer.parseInt(parts[1]);
      addTerm(coefficient, power);
    }
  }

  @Override
  public void addTerm(int coefficient, int power) {

  }

  @Override
  public void removeTerm(int power) {

  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public double evaluate(double var) {
    return 0;
  }

  @Override
  public Polynomial add(Polynomial polynomial) {
    return null;
  }
}
