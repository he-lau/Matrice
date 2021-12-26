public class MatriceTriangulaireInf {

  private int ligne;
  private int colonne;
  private float[][] donnee;

  public MatriceTriangulaireInf(float[][] data, int n, int m) {

    if (n==m) {
      this.ligne = n;
      this.colonne = m;

      float[][] low = new float[n][m];

      for (int i = 1; i < n ; i++) {
        for (int j = 0; j < i; j++) {

          low[i][j] = data[i][j];

        }
      }
      this.donnee = low;
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
