import edu.princeton.cs.algs4.Picture;

import java.awt.Color;


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
        int bx = right.getBlue() - left.getBlue();

        int ry = down.getRed() - up.getRed();
        int gy = down.getGreen() - up.getGreen();
        int by = down.getBlue() - up.getBlue();

        int deltaX2 = rx * rx + gx * gx + bx * bx;
        int deltaY2 = ry * ry + gy * gy + by * by;


        return Math.sqrt(deltaX2 + deltaY2);
    }


    // sequence of indices for horizontal seam
    // use direct DP: filling the current cell from a previous one
    public int[] findHorizontalSeam() {
        double[][] distTo = new double[height][width]; // best total energy up to (x,y)
        int[][] edgeTo = new int[height][width];

        // init first col
        for (int y = 0; y < height; y++) {
            distTo[y][0] = energy(0, y);
            edgeTo[y][0] = -1;
        }

        // dp from the previous one
        for (int x = 1; x < width; x++) {
            for (int y = 0; y < height; y++) {
                distTo[y][x] = Double.POSITIVE_INFINITY;

                for (int prevY = y - 1; prevY <= y + 1; prevY++) {
                    if (prevY < 0 || prevY >= height) continue;

                    double candidate = distTo[prevY][x - 1] + energy(x, y);
                    if (candidate < distTo[y][x]) {
                        distTo[y][x] = candidate;
                        edgeTo[y][x] = prevY;
                    }
                }
            }
        }

        // find best end point in last col
        double minDist = Double.POSITIVE_INFINITY;
        int minY = -1;
        for (int y = 0; y < height; y++) {
            if (distTo[y][width - 1] < minDist) {
                minDist = distTo[y][width - 1];
                minY = y;
            }
        }

        // reconstruct seam from right to left
        int[] seam = new int[width];
        int y = minY;
        for (int x = width - 1; x >= 0; x--) {
            seam[x] = y;
            y = edgeTo[y][x];
        }


        return seam;
    }

    // sequence of indices for vertical seam
    // imp in relax forward style: at the source cell and pushing to the next one
    public int[] findVerticalSeam() {
        int[] seam = new int[height];
        double[][] distTo = new double[height][width]; // best total energy up to (x,y)
        int[][] edgeTo = new int[height][width]; // predecessor column from row y-1

        // init top row, the cost of each pixel in top row is its own energy
        // if set top row pixel to infinity means they are unreachable which is false
        for (int x = 0; x < width; x++) {
            distTo[0][x] = energy(x, 0);
            edgeTo[0][x] = -1;
        }
        for (int y = 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                distTo[y][x] = Double.POSITIVE_INFINITY;
            }
        }
        //relax row by row
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                // for a vertical seam, we only search down, down left, down right
                relax(x, y, x, y + 1, distTo, edgeTo); //relax down
                relax(x, y, x - 1, y + 1, distTo, edgeTo); // down left
                relax(x, y, x + 1, y + 1, distTo, edgeTo); //down right
            }
        }

        // find best endpoint in bottom row
        double minDist = Double.POSITIVE_INFINITY;
        int minX = -1;
        for (int x = 0; x < width; x++) {
            if (distTo[height - 1][x] < minDist) {
                minX = x;
                minDist = distTo[height - 1][x];
            }
        }

        // reconstruct seam from bottom to top
        int x = minX;
        for (int y = height - 1; y >= 0; y--) {
            seam[y] = x;
            x = edgeTo[y][x];
        }
        return seam;
    }

    private void relax(int x, int y, int nextx, int nexty, double[][] distTo, int[][] edgeTo) {
        if (nextx < 0 || nexty < 0 || nextx >= width || nexty >= height) {
            return;
        }
        double candidate = distTo[y][x] + energy(nextx, nexty);
        if (candidate < distTo[nexty][nextx]) {
            distTo[nexty][nextx] = candidate;
            edgeTo[nexty][nextx] = x;
        }
    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        validateHorizontalSeam(seam);
        Picture newPic = new Picture(width, height - 1);
        for (int col = 0; col < width; col++) {
            int newRow = 0;
            for (int row = 0; row < height; row++) {
                if (row == seam[col]) continue;
                newPic.set(col, newRow, currentPicture.get(col, row));
                newRow++;
            }
        }
        currentPicture = newPic;
        height--;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        // 1. validate seam
        validateVerticalSeam(seam);
        // 2. make a new picture with width -1
        Picture newPic = new Picture(width - 1, height);
        // 3. for each row, skip the seam pixel and copy the rest
        for (int row = 0; row < height; row++) {
            int newCol = 0;
            for (int col = 0; col < width; col++) {
                if (col == seam[row]) continue;
                newPic.set(newCol, row, currentPicture.get(col, row));
                newCol++;
            }
        }
        currentPicture = newPic;
        width--;

    }

    private void validateVerticalSeam(int[] seam) {
        if (seam == null || width <= 1 || seam.length != height) {
            throw new IllegalArgumentException();
        }
        for (int row = 0; row < height; row++) {
            if (seam[row] < 0 || seam[row] >= width) {
                throw new IllegalArgumentException();
            }
            if (row > 0 && Math.abs(seam[row] - seam[row - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateHorizontalSeam(int[] seam) {
        if (seam == null || height <= 1 || seam.length != width) {
            throw new IllegalArgumentException();
        }
        for (int col = 0; col < width; col++) {
            if (seam[col] < 0 || seam[col] >= height) {
                throw new IllegalArgumentException();
            }
            if (col > 0 && Math.abs(seam[col] - seam[col - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
    }


    //  unit testing (optional)
    public static void main(String[] args) {
        // unused
    }

}