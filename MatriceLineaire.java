public class MatriceLineaire extends Matrice {

  private float[] donneeLin = new float[super.ligne];
  private int[] xDonnee = new int[super.ligne];
  private int[] yDonnee = new int[super.colonne];

  public MatriceLineaire(float[][] donnee, int ligne, int colonne) {
      super(donnee, ligne, colonne);
  }

  public float[] getDonneeLin() {
    return this.donneeLin;
  }

  public void lineariser() {

    
  }



}
