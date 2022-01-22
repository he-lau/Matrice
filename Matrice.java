// WARNING : LE PROJET NE PORTE QUE SUR LE CAS DES MATRICE CARREE (n=m)

// import de la blibliothéque Random pour pouvoir génerer des chiffres aléatoirement
import java.util.Random;
import java.util.ArrayList;

public class Matrice {

  // attributs protected pour autoriser l'acces aux classes filles (MatriceCreuse & MatriceLineaire)
  protected int ligne;
  protected int colonne;
  protected float[][] donnee;

  private MatriceTriangulaireSup sup;
  private MatriceTriangulaireInf inf;
  private Diagonale diag;

  // pourra être utilisé lorsque l'on ne souhaite que linéariser une matrice creuse
  private boolean estCreuse = false;


 // Constructeurs

  public Matrice(float[][] data, int n, int m) {

      this.donnee = data;
      this.ligne = n;
      this.colonne = m;

      // maj des différentes parties avec les setters qui vas appeler le constructeur des différentes classes
      this.setSup();
      this.setInf();
      this.setDiag();
  }

  // Matrice n*m générer avec valeurs aléatoires
  public Matrice(int n, int m) {

    this.ligne = n;
    this.colonne = m;

    // initialisation matrice n*m, instanciation de Random
    float[][] donnee = new float[n][m];
    Random rand = new Random();

    // borne du chiffre génerer
    float min = 0.0f;
    float max = 9.0f;

    // on parcours la matrice
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          // on génere un floatant aléatoire, on garde la partie entière pour la lisibilité
          donnee[i][j] =(int)((rand.nextFloat() * (max - min) + min));
          //donnee[i][j] =(float)(((rand.nextFloat() * (max - min) + min)));
        }
    }

    // affectation des attributs
    this.donnee = donnee;

    this.setSup();
    this.setInf();
    this.setDiag();
  }

  // Getters

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

  public boolean getCreuse() {
    return this.estCreuse;
  }


  // Setters

  public void setLigne(int n) {
    this.ligne = n;
  }

  public void setColonne(int m) {
    this.colonne = m;
  }


  public void setDiag() {

    if (this.ligne==this.colonne) {
      // init tableau
      ArrayList<Float> diag = new ArrayList<Float>();

      // on affecte seulement les valeurs sur la diagonale
      for (int i = 0; i < this.ligne; i++) {
              diag.add(this.donnee[i][i]);
      }

      Diagonale d = new Diagonale(diag,this.ligne);
      this.diag = d;

    }
  }

  public void setSup() {

    if (this.ligne==this.colonne) {
      // init tableau
      ArrayList<Float> upData = new ArrayList<Float>();

      // on parcours toute les lignes
      for (int i = 0; i < this.ligne; i++) {
        // on parcours seulement les valeurs en aval de la diagonale ([i][i])
        for (int j = i+1; j < this.ligne; j++) {
          upData.add(this.donnee[i][j]);
        }
      }

      MatriceTriangulaireSup up = new MatriceTriangulaireSup(upData,this.ligne);
      this.sup = up;

    }
  }

  public void setInf() {

    if (this.ligne==this.colonne) {
      // init tableau
      ArrayList<Float> lowData = new ArrayList<Float>();

      // on parcours à partir de la 2 ème ligne (la premiere faisant partie de la diagonale)
      for (int i = 1; i < this.ligne ; i++) {
        // on parcours les colonnes qui n'appartient pas à la diagonale ([i][i])
        for (int j = 0; j < i; j++) {
          lowData.add(this.donnee[i][j]);
        }
      }

      MatriceTriangulaireInf low = new MatriceTriangulaireInf(lowData,this.ligne);
      this.inf = low;

    }
  }

// Détermine si la matrice est creuse (plus de la moitié des données sont vide==0), initialise estCreuse
  public void initEstCreuse() {
    // init compteur
    int nbZero = 0;

    // parcours matrice
    for (int i=0; i<this.ligne; i++) {
      for (int j=0; j<this.colonne; j++) {
        // si courant == 0
        if (this.donnee[i][j]==0) {
          // incrementation compteur
          nbZero ++;
        }
      }
    }
    // si plus de la moitié des valeurs égales à 0 alors matrice est creuse
    if (nbZero > (this.ligne*this.colonne)/2) {
      this.estCreuse = true;
    }

  }




  // TODO : addition

  public void somme(Matrice mat) {
    // si les matrices même dimension
    if (this.ligne==mat.getLigne() && this.colonne==mat.getColonne()) {
      // parcours matrice
      for (int i=0;i<this.ligne ; i++) {
        for (int j=0;j<this.colonne ; j++) {
          // somme courant et parametre de même indice
          this.donnee[i][j]+=mat.getDonnee()[i][j];
        }
      }
      // maj parties
      this.setSup();
      this.setInf();
      this.setDiag();
    }
    else
      System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
  }

  // TODO : soustraction

  public void soustraction(Matrice mat) {
    // idem somme()
    if (this.ligne==mat.getLigne() && this.colonne==mat.getColonne()) {
      for (int i=0;i<this.ligne ; i++) {
        for (int j=0;j<mat.getColonne() ; j++) {
          this.donnee[i][j]-=mat.getDonnee()[i][j];
        }
      }
      this.setSup();
      this.setInf();
      this.setDiag();
    }
    else
      System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
  }

  // TODO : produits

  public void produit(Matrice mat) {

    // le produit matriciel : nombre de colonnes de la matrice A soit égal au nombre de lignes de la matrice B
    if (this.colonne==mat.getLigne()) {
      Matrice res;

      // Determination de la dimension de la matrice finale
      // le produit de la matrice A (n × m) par la matrice B (m × p) est la matrice C (n × p)
      res=new Matrice(this.ligne,mat.getColonne());

      // parcours matrice
      for(int i=0; i<this.ligne; i++){
        for(int j=0; j<mat.getColonne(); j++){
          // on initialise la val (mat generer aleatoirement)
          res.getDonnee()[i][j] = 0f;
          // parcours colonne poour faire la somme des produits
          for(int k=0; k<this.colonne ;k++){
            // [i][j] : courant, [i][k] : ligne courant A, [k][j] : colonne courant B
            res.getDonnee()[i][j] += (this.donnee[i][k] * mat.getDonnee()[k][j]);
          }
        }
      }

        this.donnee=res.getDonnee();

        // maj parties
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
          // 1 sur la diagonale
          if (i==j)
            this.donnee[i][j]=1f;
          // 0 sinon
          else
            this.donnee[i][j]=0f;
        }
      }
      // maj parties
      this.setSup();
      this.setInf();
      this.setDiag();
    }
    else
     System.out.println("ERREUR : Les matrices ne sont pas de tailles compatibles.");
  }


  // TODO : transposee()
  public Matrice transposee(){
    float res[][] = new float[this.ligne][this.colonne];
    Matrice mat = new Matrice(res, this.ligne,this.colonne);

    for (int i=0; i<this.ligne; i++) {
      for (int j=0; j<this.colonne; j++) {
        // ligne devient colonne
        res[i][j]=this.donnee[j][i];
      }
    }

    return mat;
  }



  // toString
  public String toString() {
    String s = "";

    for (int i = 0; i < this.ligne; i++) {
      // retour chariot
      if (i != 0)
        s += "\n";

      for (int j= 0; j < this.colonne; j++) {
        // si 0 afficher .
        if (this.donnee[i][j]==0) {
          s += " . "+ "\t";
        }
        // sinon afficher valeur
        else
          s += String.valueOf(this.donnee[i][j]) + "\t";
      }
    }
    // retour chaine
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

    float [][] tab2 = {
                      {0, 0, 2, 3, 0},
                      {0, 6, 7, 8, 9},
                      {0, 0, 0, 13, 0},
                      {0, 16, 0, 0, 0},
                      {20, 21, 22, 0, 0},
                    };


    Matrice mat1 = new Matrice(tab, 5, 5);
    System.out.print("(mat1): \n"+mat1.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    Matrice mat2 = new Matrice(tab2, 5, 5);
    System.out.print("(mat2): \n"+mat2.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    mat1.initEstCreuse();
    mat2.initEstCreuse();

    if (mat1.getCreuse())
      System.out.print("(mat1) Matrice creuse !\n");
    else
      System.out.print("(mat1) Matrice non creuse !\n");

    if (mat2.getCreuse())
      System.out.print("(mat2) Matrice creuse !\n");
    else
      System.out.print("(mat2) Matrice non creuse !\n");

      System.out.println("\n");
      System.out.println("-----------------------------------------------------------------\n");

    System.out.print("(mat1) Diagonale : \n"+mat1.getDiag().toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    System.out.print("(mat1) Matrice triangulaire superieure : \n"+mat1.getSup().toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    System.out.print("(mat1) Matrice triangulaire inferieure : \n"+mat1.getInf().toString()+"\n");


    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    MatriceLineaire mat3 = new MatriceLineaire(mat2.getDonnee(),mat2.getLigne(),mat2.getColonne());
    mat3.lineariser();
    System.out.print("(mat3) mat3.lineariser() (avec donnee mat2): \n"+mat3.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    MatriceCreuse mat4 = new MatriceCreuse(mat2.getDonnee(),mat2.getLigne(),mat2.getColonne());
    mat4.rendreCreuse(mat3);
    System.out.print("(mat4) mat4.rendreCreuse(mat3) : \n"+mat4.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    mat3 = mat4.matLinAssociee();
    mat4 = mat3.matCreuseAssociee();

    System.out.println("(MatriceCreuse mat4), (MatriceLineaire mat3) : ");

    System.out.print(" MatriceCreuse mat3.matCreuseAssociee() : \n"+mat4.toString()+"\n");
    System.out.print(" MatriceLineaire mat4.matLinAssociee() : \n"+mat3.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    Matrice mat5 = new Matrice(2, 2);
    System.out.print("(mat5): \n"+mat5.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    Matrice mat6 = new Matrice(2, 2);
    System.out.print("(mat6) Matrice utilisee : \n"+mat6.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    System.out.print("Addition de deux matrices (mat5=mat5+mat6): \n");
    mat5.somme(mat6);

    System.out.print("(mat5): \n"+mat5.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    System.out.print("(mat3): \n"+mat3.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    //mat3.somme(mat5); // message erreur

    mat5.identite();
    System.out.print("(mat5) Matrice identité : \n"+mat5.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");


    mat6.produit(mat5);
    System.out.print("(mat6.mat5): \n"+mat6.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");

    Matrice mat7 = mat1.transposee();
    System.out.print("(Matrice mat7 = mat1.transposee()) : \n"+mat7.toString()+"\n");

    System.out.println("\n");
    System.out.println("-----------------------------------------------------------------\n");







  }

}
