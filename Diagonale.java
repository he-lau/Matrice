public class Diagonale {

  private int ligne;
  private int colonne;
  private float[][] donnee;

  public Diagonale(float[][] data, int n, int m) {

    if (n==m) {
      this.ligne = n;
      this.colonne = m;

      float[][] diag = new float[n][m];

      for (int i = 0; i < n; i++) {
              diag[i][i] = data[i][i];
      }
      this.donnee = diag;
    }
    else
      System.out.println("ERREUR : matrice non carrée");
  }


  public String toString() {
    String s = "";

    for (int i = 0; i < this.ligne; i++) {
      if (i != 0)
        s += "\n";

      for (int j= 0; j < this.colonne; j++) {
        if (this.donnee[i][j]==0) {
          s += " . "+ "\t";
        }
        else
          s += String.valueOf(this.donnee[i][j]) + "\t";
      }
    }
    return s;
  }


}
