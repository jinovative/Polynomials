/**
 * The Polynomial interface defines the contract for operations on polynomials.
 */
public interface Polynomial {
  void addTerm(int coefficient, int power);

  void removeTerm(int power);

  int getDegree();

  int getCoefficient(int power);

  double evaluate(double var);

  Polynomial add(Polynomial polynomial);

  String toString();

}