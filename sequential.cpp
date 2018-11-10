#include <iostream>
#include <fstream>
#include <cmath>
#include <string>
#include <cstring>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

class Main {
  private:


  public:
    Main(){}

    /*
    * Parses the input given (see folder Tests with input examples)
    */
    void parse(){
      int d, m_size, n_size, pos;
      long double x_training_set[m_size][d];
      long double y_training_set[m_size];
      long double x_test_set[n_size][d];
      string aux;

      cin >> d;
      cin >> m_size;
      cin >> n_size;

      // cout << d <<endl;
      // cout << m_size <<endl;
      // cout << n_size <<endl;

      for (int i = 0; i < m_size; i++) {
        for (int j = 0; j < d; j++) {
          getline(cin, aux, ',');
          x_training_set[i][j]=stod(aux);
          // cout.precision(16);
          // cout << x_training_set[i][j] << " ";
        }
        getline(cin, aux);
        y_training_set[i] = stod(aux);
        // cout << y_training_set[i] << endl;
      }

      for (int i = 0; i < n_size; i++) {
        for (int j = 0; j < (d-1); j++) {
          getline(cin, aux, ',');
          x_test_set[i][j] = stod(aux);
          // cout.precision(16);
          // cout << x_test_set[i][j] <<  " ";
        }
        getline(cin, aux);
        x_test_set[i][d] = stod(aux);
        //cout << x_test_set[i][d] << endl;
      }
    }
 };

int main ()
{
  Main p;
  p.parse();


  return 0;
}
