import java.util.ArrayList;

public class Diagonale {

  private int dim;
  private ArrayList<Float> donnee;

  public Diagonale(ArrayList<Float> data, int n) {
    this.dim = n;
    this.donnee = data;
  }


  // toString : idem Matrice
  public String toString() {
    String s = "";

    for (int i = 0; i < this.dim; i++) {
      if (i != 0)
        s += "\n";

      for (int j= 0; j < this.dim; j++) {

        if (i==j) {
          if (this.donnee.get(i)==0) {
            s += " . "+ "\t";
          }
          else
            s += String.valueOf(this.donnee.get(i)) + "\t";

        }
        else
          s += " . "+ "\t";

      }
    }
    return s;
  }

}
