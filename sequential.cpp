#include <iostream>
#include <random>

using namespace std;

class Main {
  private:


  public:
    Main(){}

    void ann_training(int d, double l_rate, long double **x_training_set, long double *y_training_set, long double **x_test_set, int m_size, int n_size){
      int error, num_iterations;
      double weights[d+1];
      double delta_w, acum, y_hat, y_difference;
      for (int i=0; i<(d+1); i++){
        weights[i]=(double)rand() / RAND_MAX;
        cout<<weights[i]<<endl;
      }

      error = 1;
      num_iterations = 0;

      while (error > 0 and num_iterations < 1000){
        error = 0;
        num_iterations ++;
        for(int i=0; i<(m_size); i++){
            acum = 0;
            for(int j=0; j<(d+1);j++){
                acum += x_training_set[i][j] * weights[j];
            }
            y_hat = (acum >= 0)?1:0;
            y_difference = y_training_set[i] - y_hat;

            if (y_difference != 0){
              error++;
            }

            for(int k=0; k<(d+1); k++) {
                delta_w = (y_training_set[i]-y_hat) * l_rate * x_training_set[i][k];
                weights[k] += delta_w;
              }
            }
          }

          if (error != 0){
            cout<<"no solution found"<<endl;
          }
          else{
              for(int i=0; i<n_size; i++){
                acum = 0;
                for (int j=0; j<(d+1); j++){
                  acum += x_test_set[i][j] * weights[j];
                }
                y_hat = (acum >= 0) ? 1 : 0;

                cout<< y_hat<<endl;
              }

          }

        }


    /*
    * Parses the input given (see folder Tests with input examples)
    */
    void parse(){
      int d, m_size, n_size, pos;
      string aux;
      double l_rate = 0.01;

      long double **x_training_set;
      long double *y_training_set;
      long double **x_test_set;


      cin >> d;
      cin >> m_size;
      cin >> n_size;

      x_training_set = (long double**) malloc (m_size*sizeof(long double*));
      for (int i = 0; i<m_size; i++) {
        x_training_set[i] = (long double*) malloc ((d+1)*sizeof(long double));
      }

      y_training_set = (long double*) malloc (m_size*sizeof(long double));


      x_test_set = (long double**) malloc (n_size*sizeof(long double*));
      for (int i = 0; i<n_size; i++) {
        x_test_set[i] = (long double*) malloc ((d+2)*sizeof(long double));
      }
      cout << d <<endl;
      cout << m_size <<endl;
      cout << n_size <<endl;

      for (int i = 0; i < m_size; i++) {
        for (int j = 0; j < d; j++) {
          getline(cin, aux, ',');
          x_training_set[i][j]=stod(aux);
          cout.precision(16);
          cout << x_training_set[i][j] << " ";
        }
        x_training_set[i][d]=1.0; //bias
        getline(cin, aux);
        y_training_set[i] = stod(aux);
        cout << y_training_set[i] << endl;
      }

      for (int i = 0; i < n_size; i++) {
        for (int j = 0; j < (d-1); j++) {
          getline(cin, aux, ',');
          x_test_set[i][j] = stod(aux);
          cout.precision(16);
          cout << x_test_set[i][j] <<  " ";
        }
        getline(cin, aux);
        x_test_set[i][d] = stod(aux);
        x_test_set[i][d+1] = 1.0; //bias
        cout << x_test_set[i][d] << endl;
      }

      ann_training(d, l_rate, x_training_set, y_training_set, x_test_set, m_size, n_size);

      for (int i=0; i<m_size; i++) {
        free(x_training_set[i]);
      }
      free(x_training_set);
      free(y_training_set);

      for (int i=0; i<n_size; i++) {
        free(x_test_set[i]);
      }
      free(x_test_set);
    }
 };

int main ()
{

  srand (time(NULL));
  Main p;
  p.parse();


  return 0;
}
