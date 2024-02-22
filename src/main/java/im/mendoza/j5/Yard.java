package im.mendoza.j5;

import java.awt.*;

public class Yard {
    private final int length;
    private final int width;

    private final Point[] trees;

    public Yard(final int length, final int width, Point ... trees) {
        this.length = length;
        this.width = width;
        this.trees = trees;
    }

    public Point fitPoolOfSize(int poolLength, int poolWidth) {
        for (int row = 1; row < poolLength + length; row++) {
            for (int column = 1; column < poolWidth + width; column ++) {
                if (poolFitsInYard(poolLength, poolWidth, row, column) &&
                    poolContainsNoTrees(poolLength, poolWidth, row, column)) {
                    return new Point(row, column);
                }
            }
        }

        return null;
    }

    private boolean poolContainsNoTrees(int poolLength, int poolWidth, int poolRow, int poolCol) {
        for (Point tree : trees) {
            if (tree.getX() >= poolCol &&
                tree.getX() <= poolCol + poolWidth - 1 &&
                tree.getY() >= poolRow &&
                tree.getY() <= poolRow + poolLength - 1
                ) {
                if (poolRow == 5 && poolCol == 7) {
                    System.out.println(tree);
                }
                return false;
            }
        }
        return true;
    }

    private boolean poolFitsInYard(int poolLength, int poolWidth, int poolRow, int poolCol) {
        return poolLength + poolRow - 1 <= length && poolWidth + poolCol - 1 <= width;
    }


}
