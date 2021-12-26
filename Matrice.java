// WARNING : LE PROJET NE PORTE QUE SUR LE CAS DES MATRICE CARREE (n=m)

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.lang.Math;


public class Matrice {

  protected int ligne;
  protected int colonne;
  protected float[][] donnee;

  private MatriceTriangulaireSup sup;
  private MatriceTriangulaireInf inf;
  private Diagonale diag;

  private boolean estCreuse = false;
  private boolean estLineaire = false;


 // Constructeur
  public Matrice(float[][] data, int n, int m) {

    if (n==m) {
      this.donnee = data;
      this.ligne = n;
      this.colonne = m;
    }
    else
      System.out.println("ERREUR : matrice non carrée");
  }

// Getters/ Setters
  public int getColonne() {
    return this.colonne;
  }

  public int getLigne() {
    return this.ligne;
  }

  public float[][] getDonnee() {
    return this.donnee;
  }

  public MatriceTriangulaireSup getSup() {
    return this.sup;
  }

  public MatriceTriangulaireInf getInf() {
    return this.inf;
  }

  public Diagonale getDiag() {
    return this.diag;
  }



  public void setLigne(int n) {
    this.ligne = n;
  }

  public void setColonne(int m) {
    this.colonne = m;
  }

  public void setDiag() {
    Diagonale d = new Diagonale(this.donnee,this.ligne,this.colonne);
    this.diag = d;
  }

  public void setSup() {
    MatriceTriangulaireSup up = new MatriceTriangulaireSup(this.donnee,this.ligne,this.colonne);
    this.sup = up;
  }

  public void setInf() {
    MatriceTriangulaireInf low = new MatriceTriangulaireInf(this.donnee,this.ligne,this.colonne);
    this.inf = low;
  }

// Détermine si la matrice est creuse (plus de la moitié des données sont vide==0), initialise estCreuse
  public void initEstCreuse() {
    int nbZero = 0;

    for (int i=0; i<this.ligne; i++) {
      for (int j=0; j<this.colonne; j++) {
        if (this.donnee[i][j]==0) {
          nbZero ++;
        }
      }
    }
    if (nbZero > (this.ligne*this.colonne)/2) {
      this.estCreuse = true;
    }

  }


  // Initialise la diagonale
  public float[] diagonale() {

    float[] diag = new float[this.ligne];

    for (int i = 0; i < this.ligne; i++) {
            diag[i] = this.donnee[i][i];
    }

    System.out.print("diagonale() : \n");

    for (int i = 0; i < diag.length; i++) {
      System.out.print(String.valueOf(diag[i])+"\t");
    }

    System.out.print("\n");

    return diag;
  }

  public float[][] partieSup() {

    float[][] up = new float[this.ligne][this.colonne];

    for (int i = 0; i < this.ligne; i++) {
      for (int j = 0; j < this.colonne; j++) {
        up[i][j] = Float.NaN;
      }
    }

    for (int i = 0; i < this.ligne; i++) {
      for (int j = i+1; j < this.colonne; j++) {

        up[i][j] = this.donnee[i][j];

      }
    }

    System.out.print("partieSup() : \n");

    String res = "";
    for (int i = 0; i < this.ligne; i++) {
      if (i!=0) {
        res+="\n";
      }
      for (int j = 0; j < this.colonne; j++) {
        if (Float.isNaN(up[i][j])) {
          res += " . \t";
        }
        else
          res += String.valueOf(up[i][j])+"\t";
      }
    }

    System.out.print(res);
    System.out.print("\n");

    return up;
  }

  public float[] partieInf() {

    float[] low = new float[this.ligne*(this.ligne-1)/2];

    int k = 0;

    for (int i = 1; i < this.ligne ; i++) {
      for (int j = 0; j < i; j++) {

        low[k] = this.donnee[i][j];
        k++;

      }
    }

    System.out.print("partieInf() : \n");

    for (int i = 0; i < low.length; i++) {
      System.out.print(String.valueOf(low[i])+"\t");
    }

    System.out.print("\n");

    return low;
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






  public static void main(String[] args) {

    float [][] tab = {
                      {0, 1, 2, 3, 0},
                      {5, 6, 7, 8, 9},
                      {10, 11, 12, 13, 14},
                      {15, 16, 17, 18, 19},
                      {20, 21, 22, 23, 24},
                    };

    Matrice mat = new Matrice(tab, 5, 5);
    System.out.print("Matrice utilisee : \n"+mat.toString()+"\n");

    mat.setDiag();
    System.out.print("Diagonale : \n"+mat.getDiag().toString()+"\n");

    mat.setSup();
    System.out.print("Matrice triangulaire superieure : \n"+mat.getSup().toString()+"\n");

    mat.setInf();
    System.out.print("Matrice triangulaire inferieure : \n"+mat.getInf().toString()+"\n");

    MatriceLineaire matLin = new MatriceLineaire(mat.getDonnee(),mat.getLigne(),mat.getColonne());
    System.out.print("Test MatriceLineaire : \n"+matLin.getDonneeLin().length+"\n");

    mat.initEstCreuse();




  }

}
