import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private int width;
    private int height;
    private Picture currentPicture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        currentPicture = new Picture(picture);
        width = currentPicture.width();
        height = currentPicture.height();
    }

    // current picture
    public Picture picture() {
        return new Picture(currentPicture);
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return 1000.0;
        }


        Color up, down, left, right;
        up = currentPicture.get(x, y - 1);
        down = currentPicture.get(x, y + 1);
        left = currentPicture.get(x - 1, y);
        right = currentPicture.get(x + 1, y);

        int rx = right.getRed() - left.getRed();
        int gx = right.getGreen() - left.getGreen();
        int bx = right.getRGB() - left.getBlue();

        int ry = down.getRed() - up.getRed();
        int gy = down.getGreen() - up.getGreen();
        int by = down.getBlue() - up.getBlue();

        int deltaX2 = rx * rx + gx * gx + bx * bx;
        int deltaY2 = ry * ry + gy * gy + by * by;


        return Math.sqrt(deltaX2 + deltaY2);
    }


    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || height <= 1) {
            throw new IllegalArgumentException();
        }
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width <= 1) {
            throw new IllegalArgumentException();
        }

    }

    //  unit testing (optional)
    public static void main(String[] args) {
    }

}