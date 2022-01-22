// Utilisation ArrayList
import java.util.ArrayList;

public class MatriceCreuse extends Matrice {

  public MatriceCreuse(float[][] donnee, int ligne, int colonne) {
      super(donnee, ligne, colonne);
  }


  public void rendreCreuse(MatriceLineaire matLin) {

    float[][] matCreuse = new float[matLin.getLigne()][matLin.getColonne()];

    for (int i=0; i<matLin.getDonneeLin().size(); i++ ) {
        matCreuse[matLin.getXDonnee().get(i)][matLin.getYDonnee().get(i)] = matLin.getDonneeLin().get(i);
      }
    this.donnee = matCreuse;

  }

  public MatriceLineaire matLinAssociee() {

    MatriceLineaire matLin = new MatriceLineaire(this.donnee,this.ligne,this.colonne);
    matLin.lineariser();

    return matLin;

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
