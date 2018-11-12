import java.lang.String;
import java.util.*;

class Perceptron {
  private double[] weights;
  private double[][] x_train_set;
  private double[] y_train_set;
  private double[][] x_test_set;
  private int m_size;
  private int n_size;
  private int dim;

  // Constructor
  public Perceptron() {
  }
  // Getters
  public double[] getWeights() {
    return weights;
  }

  public int getMSize() {
    return m_size;
  }

  public int getNSize() {
    return n_size;
  }

  public int getDim() {
    return dim;
  }

  public double[][] getXTrainSet() {
    return x_train_set;
  }

  public double[] getYTrainSet() {
    return y_train_set;
  }

  public double[][] getXTestSet() {
    return x_test_set;
  }

  public void setWeights(double[] weights) {
    this.weights = weights;
  }

  // Set x_train_set
  public void setXTrainSet(double[][] x_set) {
    this.x_train_set = x_set;
  }

  // Set y_train_set
  public void setYTrainSet(double[] y_set) {
    this.y_train_set = y_set;
  }

  // Set x_test_set
  public void setXTestSet(double[][] x_set) {
    this.x_test_set = x_set;
  }

  // Parse input
  public void parse(){
    Scanner input = new Scanner(System.in);
    Random r = new Random();

    // Dimension
    dim = input.nextInt();
    input.nextLine(); //clear buffer
    weights = new double[dim];

    // Train set size
    m_size = input.nextInt();
    x_train_set = new double[m_size][dim];
    y_train_set = new double[m_size];
    input.nextLine(); //clear buffer

    // Test set size
    n_size = input.nextInt();
    x_test_set = new double[n_size][dim];
    input.nextLine(); //clear buffer

    // Fill in x and y train sets
    String line;
    for(int i=0; i<m_size; i++){
      line = input.nextLine();
      String[] aux_line = line.split(",");
      for(int j=0; j<dim; j++){
        x_train_set[i][j] = Double.parseDouble(aux_line[j]);
      }
      y_train_set[i] = Double.parseDouble(aux_line[dim]);
    }

    // Fill in x test set
    for(int i=0; i<n_size; i++){
      line = input.nextLine();
      String[] aux_line = line.split(",");

      for(int j=0; j<dim; j++){
        x_test_set[i][j] = Double.parseDouble(aux_line[j]);
      }
    }

    double random;
    // Fill weights
    for(int i = 0; i < dim; i++) {
      random = 0 + r.nextDouble() * (1 - 0);
      weights[i] = random;
    }
  }

  void train(double learning_rate, int limit) {
    double[][] x_train_set = this.getXTrainSet();
    double[] y_train_set = this.getYTrainSet();
    double[] weights = this.getWeights();
    int dim = this.getDim();
    int m_size = this.getMSize();

    int error = 1;
    int iterations = 0;
    double acum, difference, delta_w;
    int y_hat;

    while(error > 0 && iterations < limit){
      error = 0;
      iterations++;

      for(int i = 0; i < m_size; i++){
        acum = 0;
        for(int j = 0; j < dim; j++){
          acum += x_train_set[i][j] * weights[j];
        }
        if(acum >= 0) y_hat = 1;
        else y_hat = 0;

        difference = y_train_set[i] - y_hat;
        if(difference != 0) error +=1;

        for(int j = 0; j < dim; j++) {
          delta_w = (y_train_set[i] - y_hat) * learning_rate * x_train_set[i][j];
          weights[j] += delta_w;
        }
      }
    }

    if(error != 0) {
      System.out.println("No solution found");
    }
  }

  void train() {

  }

  void printTrainSets() {
    System.out.println("X Train Set: ");
    double[][] x_train_set = this.getXTrainSet();
    double[] y_train_set = this.getYTrainSet();

    int m_size = this.getMSize();
    int dim = this.getDim();

    System.out.println("X train set");

    System.out.println("Dim = "+ dim);
    System.out.println("M_size = "+ m_size);

    for(int i = 0; i < m_size; i++){
      for(int j = 0; j < dim; j++) {
        System.out.print(x_train_set[i][j] + " ");
      }
      System.out.println("\n");
    }

    System.out.println("Y Train Set: ");
    for(int i = 0; i < m_size; i++){
        System.out.println(y_train_set[i] + " ");
    }


  }


  void printTestSet () {
    double[][] x_test_set = this.getXTestSet();

    int dim = this.getDim();
    int n_size = this.getNSize();

    System.out.println("Test Set");
    System.out.println("Dim = "+ dim);
    System.out.println("N_size = "+ n_size);

    for(int i = 0; i < n_size; i++){
      for(int j = 0; j < dim; j++) {
        System.out.print(x_test_set[i][j] + " ");
      }
      System.out.println("\n");
    }
  }

  void printWeights() {
    double[] weights = this.getWeights();
    int dim = this.getDim();

    System.out.println("Weights");
    for(int i = 0; i < dim; i++) {
      System.out.print(weights[i] + " ");
    }
  }



}
