import java.util.ArrayList;

public class MatriceTriangulaireInf {

  private int dim;
  private ArrayList<Float> donnee;

  public MatriceTriangulaireInf(ArrayList<Float> data, int n) {
    this.dim = n;
    this.donnee = data;
  }
  
  public String toString() {
    String s = "";
    for (int i = 0; i < this.donnee.size(); i++) {
        if (this.donnee.get(i)==0) {
          s += " . "+ "\t";
        }
          else
            s += String.valueOf(this.donnee.get(i)) + "\t";
    }
    return s;
}

}
