package src.PacManGame;


//958908 Kai Yao Tan (tankk@student.unimelb.edu.au)
//1270676 Aryan Puri (aryanp@student.unimelb.edu.au)
//1272792 Henry Harper (hsharper@student.unimelb.edu.au)

public class Sprite {

    private static final String FOLDER_PATH = "sprites/";
    private boolean rotatable = false;
    private String imageName = FOLDER_PATH;
    private int imgCount = 1;


    public Sprite(boolean rotatable, String imageName, int imgCount) {
        this.rotatable = rotatable;
        this.imageName += imageName;
        this.imgCount = imgCount;
    }

    public Sprite(String imageName) {
        this.imageName += imageName;
    }


    public boolean isRotatable() {
        return rotatable;
    }

    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImgCount() {
        return imgCount;
    }

    public void setImgCount(int imgCount) {
        this.imgCount = imgCount;
    }
}
