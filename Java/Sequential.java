import java.util.Scanner;
import java.lang.String;
import java.util.*;

/* To run:
*  javac *.java
*  java Sequential < ../Tests/Sprinkler,Rain,GrassWet.txt
*/

class Sequential {

  public static void main(String[] args) {
    Perceptron perceptron = new Perceptron();
    perceptron.parse();
    //only for testing purposes

    perceptron.printTrainSets();
    perceptron.printTestSet();
    perceptron.printWeights();

    double learning_rate = 0.01;
    int limit = 1000;
    perceptron.train(learning_rate, limit);

  }

}
