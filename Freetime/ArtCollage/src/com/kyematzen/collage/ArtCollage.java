package com.kyematzen.collage; /*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author:
 *
 *************************************************************************/

import java.awt.*;

public class ArtCollage {

    // The orginal picture
    private final Picture original;

    // The collage picture
    private final Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private final int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private final int tileDimension;

    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension,
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
        this.collageDimension = 4;
        this.tileDimension = 100;
        this.original = new Picture(filename);
        this.collage = new Picture(tileDimension*collageDimension, tileDimension*collageDimension);
        for (int ti = 0; ti < tileDimension * collageDimension; ti++) {
            for (int tj = 0; tj < tileDimension * collageDimension; tj++) {
                int si = ti * original.width() / (tileDimension * collageDimension);
                int sj = tj * original.height() / (tileDimension * collageDimension);
                Color color = original.get(si, sj);
                collage.set(ti, tj, color);
            }
        }
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension,
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {
        this.collageDimension = cd;
        this.tileDimension = td;
        this.original = new Picture(filename);
        this.collage = new Picture(tileDimension*collageDimension, tileDimension*collageDimension);
        for (int ti = 0; ti < tileDimension * collageDimension; ti++) {
            for (int tj = 0; tj < tileDimension * collageDimension; tj++) {
                int si = ti * original.width() / (tileDimension * collageDimension);
                int sj = tj * original.height() / (tileDimension * collageDimension);
                Color color = original.get(si, sj);
                collage.set(ti, tj, color);
            }
        }
    }

    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {
        // ArtCollage art = new ArtCollage(args[0],200,3);
        ArtCollage art = new ArtCollage(args[0],200,3);
        art.makeCollage();
        art.replaceTile(args[1],1,2);
        art.colorizeTile("green",1,2);
        art.showCollagePicture();
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return this.collageDimension;
        // WRITE YOUR CODE HERE
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return this.tileDimension;
        // WRITE YOUR CODE HERE
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {
        return original;
        // WRITE YOUR CODE HERE
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {
        return collage;
        // WRITE YOUR CODE HERE
    }

    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
        original.show();
        // WRITE YOUR CODE HERE
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        collage.show();
        // WRITE YOUR CODE HERE
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture replacement = new Picture(filename);
        for (int ti = this.tileDimension * collageCol; ti < (this.tileDimension * collageCol) + this.tileDimension; ti++) {
            for (int tj = this.tileDimension * collageRow; tj < (this.tileDimension * collageRow) + this.tileDimension; tj++) {
                int si = (ti % tileDimension) * replacement.width() / tileDimension;
                int sj = (tj % tileDimension) * replacement.height() / tileDimension;
                Color color = replacement.get(si, sj);
                collage.set(ti, tj, color);
            }
        }
    }

    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {
        int collageDimension = getCollageDimension();
        int tileDimension = getTileDimension();
        int xStart = 0;
        int xEnd = tileDimension;
        int yStart = 0;
        for (int i = 0; i < collageDimension; i++) {
            for (int j = 0; j < collageDimension; j++) {
                xStart = 0;
                xEnd += tileDimension;
                if (xEnd >= collageDimension * tileDimension + 1) {
                    yStart += tileDimension;
                    xStart = 0;
                    xEnd = tileDimension;
                }
                for (int ti = xStart; ti < xEnd; ti++) {
                    for (int tj = yStart; tj < collageDimension * tileDimension; tj++) {
                        int si = (ti % tileDimension) * original.width() / tileDimension;
                        int sj = (tj % tileDimension) * original.height() / tileDimension;
                        Color color = original.get(si, sj);
                        collage.set(ti, tj, color);

                    }
                }
            }
        }
    }


    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    /*
     * Colorizes the tile at (collageCol, collageRow) with component
     * (see CS111 Week 9 slides, the code for color separation is at the
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile(String component, int collageCol, int collageRow) {
         for (int ti = this.tileDimension * collageCol; ti < (this.tileDimension * collageCol) + this.tileDimension; ti++) {
             for (int tj = this.tileDimension * collageRow; tj < (this.tileDimension * collageRow) + this.tileDimension; tj++) {
                 Color color = collage.get(ti, tj);
                 if (component.equals("red")) {
                     Color shade = new Color(color.getRed(), 0, 0);
                     collage.set(ti, tj, shade);
                 } else if (component.equals("green")) {
                     Color shade = new Color(0, color.getGreen(), 0);
                     collage.set(ti, tj, shade);
                 } else {
                     Color shade = new Color(0, 0, color.getBlue());
                     collage.set(ti, tj, shade);
                 }
             }
         }
    }

    public void grayscaleTile(int collageCol, int collageRow) {
        Luminance l = new Luminance();
            for (int ti = this.tileDimension * collageCol; ti < (this.tileDimension * collageCol) + this.tileDimension; ti++) {
                int si = (ti % tileDimension) * original.width() / tileDimension;
                for (int tj = this.tileDimension * collageRow; tj < (this.tileDimension * collageRow) + this.tileDimension; tj++) {
                    int sj = (tj % tileDimension) * original.height() / tileDimension;
                    Color color = original.get(ti, tj);
                    color = l.toGray(color);
                    collage.set(ti, tj, color);
                }
            }

        // WRITE YOUR CODE HERE
    }
}
