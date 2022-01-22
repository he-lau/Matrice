// Utilisation ArrayList
import java.util.ArrayList;

public class MatriceLineaire extends Matrice {

  private ArrayList<Float> donneeLin = new ArrayList<Float>();
  private ArrayList<Integer> xDonnee = new ArrayList<Integer>();
  private ArrayList<Integer> yDonnee = new ArrayList<Integer>();

  public MatriceLineaire(float[][] donnee, int ligne, int colonne) {
      super(donnee, ligne, colonne);
  }

  public ArrayList<Float> getDonneeLin() {
    return this.donneeLin;
  }

  public ArrayList<Integer> getXDonnee() {
    return this.xDonnee;
  }

  public ArrayList<Integer> getYDonnee() {
    return this.yDonnee;
  }


  public void lineariser() {

    for (int i=0; i<this.ligne; i++) {
      for (int j=0; j<this.colonne; j++) {
        if (this.donnee[i][j]!=0) {
          this.donneeLin.add(this.donnee[i][j]);
          this.xDonnee.add(i);
          this.yDonnee.add(j);
        }
      }
    }
  }// lineariser()

  public MatriceCreuse matCreuseAssociee() {

    MatriceCreuse matCreuse = new MatriceCreuse(this.donnee,this.ligne,this.colonne);
    matCreuse.rendreCreuse(this);

    return matCreuse;

  }


  public void somme(Matrice mat) {
    if (this.ligne==mat.getLigne() && this.colonne==mat.getColonne()) {
      for (int i=0;i<this.ligne ; i++) {
        for (int j=0;j<this.colonne ; j++) {
          this.donnee[i][j]+=mat.getDonnee()[i][j];
        }
      }
      // mise à jour la matrice lineaire
      this.donneeLin.clear();
      this.lineariser();

      this.setSup();
      this.setInf();
      this.setDiag();
    }
    else
      System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
  }

  public void soustraction(Matrice mat) {
    if (this.ligne==mat.getLigne() && this.colonne==mat.getColonne()) {
      for (int i=0;i<this.ligne ; i++) {
        for (int j=0;j<this.colonne ; j++) {
          this.donnee[i][j]-=mat.getDonnee()[i][j];
        }
      }
      // mise à jour la matrice lineaire
      this.donneeLin.clear();
      this.lineariser();
      this.setSup();
      this.setInf();
      this.setDiag();
    }
    else
      System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
  }

  // TODO : produits

  public void produit(Matrice mat) {

    if (this.colonne==mat.getLigne()) {
      Matrice res;

      if (this.ligne>=mat.getLigne())
        res=new Matrice(this.ligne,this.colonne);
      else
        res=new Matrice(mat.getLigne(),mat.getColonne());


      for(int i=0; i<this.ligne; i++){
        for(int j=0; j<mat.getColonne(); j++){
          res.getDonnee()[i][j] = 0f;
          for(int k=0; k<this.colonne ;k++){
            res.getDonnee()[i][j] += (this.donnee[i][k] * mat.getDonnee()[k][j]);
          }
        }
      }
      //  System.out.println(res.toString());
        this.donnee=res.getDonnee();
        // mise à jour la matrice lineaire
        this.donneeLin.clear();
        this.lineariser();

        this.setSup();
        this.setInf();
        this.setDiag();
    }
    else {
      System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
    }
  }

  // TODO : identité

  public void identite() {
    if (this.ligne==this.colonne) {
      for (int i=0; i<this.ligne; i++) {
        for (int j=0; j<this.ligne; j++) {
          if (i==j)
            this.donnee[i][j]=1f;
          else
            this.donnee[i][j]=0f;
        }
      }
      // mise à jour la matrice lineaire
      this.donneeLin.clear();
      this.lineariser();
      this.setSup();
      this.setInf();
      this.setDiag();
    }
    else
     System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
  }

  public String toString() {
    String s = "";

    for (int i=0; i<this.donneeLin.size(); i++) {
      s+=String.valueOf(this.donneeLin.get(i)+"\t");
    }

    s+="\n";

    return s;
  }



}
