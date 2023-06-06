/**
 * The PolynomialImpl class is an implementation of the Polynomial interface.
 */
public class PolynomialImpl implements Polynomial {
  private Node head;

  /**
   * The Node class represents a term in the polynomial.
   */
  private static class Node {
    int coefficient;
    int power;
    Node next;

    /**
     * Constructs a new Node with the given coefficient and power.
     *
     * @param coefficient the coefficient of the term
     * @param power the power of the term
     */
    Node(int coefficient, int power) {
      this.coefficient = coefficient;
      this.power = power;
      this.next = null;
    }
    // This is the implementation class PolynomialImpl that implements the Polynomial interface. It contains a private inner class Node to represent each term of the polynomial.
  }

  /**
   * Constructs an empty polynomial with no terms.
   */
  public PolynomialImpl() {
    this.head = null;
  }
  // This is the constructor of PolynomialImpl that creates an empty polynomial with no terms. It initializes the head of the linked list representation to null.

  /**
   * Constructs a polynomial by parsing the given polynomial string.
   *
   * @param polynomialString the string representing the polynomial
   */
  public PolynomialImpl(String polynomialString) {
    this.head = null;
    parsePolynomialString(polynomialString);
  }
  // This is the constructor of PolynomialImpl that takes a polynomial as a string, parses it, and creates the polynomial accordingly. It calls the parsePolynomialString method to process the string.

  /**
   * Parses the given polynomial string and adds the terms to the polynomial.
   *
   * @param polynomialString the string representing the polynomial
   */
  private void parsePolynomialString(String polynomialString) {
    String[] terms = polynomialString.split("\\s+");

    for (String term : terms) {
      String[] parts = term.split("x\\^");
      if (parts.length != 2) {
        continue; // Skip invalid term
      }

      int coefficient;
      int power;
      if (parts[0].isEmpty()) {
        coefficient = 1;
      } else if (parts[0].equals("+") || parts[0].equals("-")) {
        coefficient = Integer.parseInt(parts[0] + "1");
      } else {
        coefficient = Integer.parseInt(parts[0]);
      }

      power = Integer.parseInt(parts[1]);
      addTerm(coefficient, power);
    }
  }

  //This private method parsePolynomialString processes the input polynomial string. It splits the string into individual terms, and for each term, it further splits it into coefficient and power parts using the regular expression x\^. It handles the special cases where the coefficient is omitted or negative. Then, it calls the addTerm method to add the parsed term to the polynomial.

  /**
   * Adds a term with the given coefficient and power to the polynomial.
   *
   * @param coefficient the coefficient of the term
   * @param power the power of the term
   * @throws IllegalArgumentException if the power is negative
   */
  @Override
  public void addTerm(int coefficient, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (coefficient == 0) {
      return; // Ignore terms with zero coefficient
    }
    Node newNode = new Node(coefficient, power);
    if (head == null || power > head.power) {
      newNode.next = head;
      head = newNode;
    } else {
      Node curr = head;
      while (curr.next != null && curr.next.power > power) {
        curr = curr.next;
      }
      if (curr.power == power) {
        curr.coefficient += coefficient; // Combine terms with the same power
      } else {
        newNode.next = curr.next;
        curr.next = newNode;
      }
    }
  }
  // This method addTerm adds a term with the given coefficient and power to the polynomial. It performs checks to ensure that the power is non-negative and the coefficient is not zero. It creates a new node representing the term and adds it to the linked list representation of the polynomial in descending order of powers.

  /**
   * Removes all terms with the given power from the polynomial.
   *
   * @param power the power of the terms to be removed
   */
  @Override
  public void removeTerm(int power) {
    if (head == null) {
      return;
    }
    if (head.power == power) {
      head = head.next;
      removeTerm(power); // Recursively remove all terms with the given power
    } else {
      Node curr = head;
      while (curr.next != null && curr.next.power >= power) {
        if (curr.next.power == power) {
          curr.next = curr.next.next;
          removeTerm(power); // Recursively remove all terms with the given power
          return;
        }
        curr = curr.next;
      }
    }
  }
  //This method removeTerm removes all terms with the given power from the polynomial. It iterates through the linked list and removes the nodes that match the given power. It uses recursion to remove all matching terms.

  /**
   * Returns the degree of the polynomial.
   *
   * @return the degree of the polynomial
   */
  @Override
  public int getDegree() {
    if (head == null) {
      return 0; // Degree of the zero polynomial is 0
    }
    return head.power;
  }

  /**
   * Returns the coefficient of the term with the given power.
   *
   * @param power the power of the term
   * @return the coefficient of the term with the given power
   */
  @Override
  public int getCoefficient(int power) {
    Node curr = head;
    while (curr != null) {
      if (curr.power == power) {
        return curr.coefficient;
      }
      curr = curr.next;
    }
    return 0; // Coefficient of a term that doesn't exist is 0
  }
  //This method getCoefficient returns the coefficient of the term with the given power. It iterates through the linked list and returns the coefficient if it finds a matching term. If the term doesn't exist, it returns 0.

  /**
   * Evaluates the polynomial for the given value of x.
   *
   * @param x the value of x
   * @return the result of evaluating the polynomial
   */
  @Override
  public double evaluate(double x) {
    double result = 0;
    Node curr = head;
    while (curr != null) {
      result += curr.coefficient * Math.pow(x, curr.power);
      curr = curr.next;
    }
    return result;
  }

  /**
   * Adds the given polynomial to the current polynomial and returns the result as a new polynomial.
   *
   * @param polynomial the polynomial to be added
   * @return the sum of the two polynomials
   * @throws IllegalArgumentException if the given polynomial is of an invalid type
   */
  @Override
  public Polynomial add(Polynomial polynomial) {
    if (!(polynomial instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("Invalid polynomial type");
    }
    PolynomialImpl other = (PolynomialImpl) polynomial;
    PolynomialImpl sum = new PolynomialImpl();
    Node curr1 = head;
    Node curr2 = other.head;
    while (curr1 != null && curr2 != null) {
      if (curr1.power > curr2.power) {
        sum.addTerm(curr1.coefficient, curr1.power);
        curr1 = curr1.next;
      } else if (curr1.power < curr2.power) {
        sum.addTerm(curr2.coefficient, curr2.power);
        curr2 = curr2.next;
      } else {
        int coefficient = curr1.coefficient + curr2.coefficient;
        sum.addTerm(coefficient, curr1.power);
        curr1 = curr1.next;
        curr2 = curr2.next;
      }
      // Add any remaining terms from either polynomial
      while (curr1 != null) {
        sum.addTerm(curr1.coefficient, curr1.power);
        curr1 = curr1.next;
      }
      while (curr2 != null) {
        sum.addTerm(curr2.coefficient, curr2.power);
        curr2 = curr2.next;
      }
      return sum;
    }
    return polynomial;
  }
}