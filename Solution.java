public class Solution {
    public static int getOptimalSolution(int sideLen, int[][] blockCoords) {
        int[][] matrix = new int[sideLen][sideLen];

        // 0: normal
        // 1: block or visited
        for (int[] block : blockCoords) {
            matrix[block[0]][block[1]] = 1;
        }

        int result = 0;
        int tempResult;
        for (int i = 0; i < sideLen; i++) {
            for (int j = 0; j < sideLen; j++) {
                if (matrix[i][j] == 1) {
                    continue;
                }

                matrix[i][j] = 1;
                tempResult = 1 + dfsCalc(matrix, i, j);
                result = result > tempResult ? result : tempResult;
                matrix[i][j] = 0;
            }
        }

        return result;
    }

    private static int dfsCalc(int[][] matrix, int row, int col) {
        int sideLen = matrix.length;
        int result = 0;
        int tempResult = 0;
        if (row > 0 && matrix[row - 1][col] == 0) {
            tempResult = calcTop(matrix, row, col);
            result = result > tempResult ? result : tempResult;
        }

        if (row < sideLen - 1 && matrix[row + 1][col] == 0) {
            tempResult = calcBottom(matrix, row, col);
            result = result > tempResult ? result : tempResult;
        }

        if (col > 0 && matrix[row][col - 1] == 0) {
            tempResult = calcLeft(matrix, row, col);
            result = result > tempResult ? result : tempResult;
        }

        if (col < sideLen - 1 && matrix[row][col + 1] == 0) {
            tempResult = calcRight(matrix, row, col);
            result = result > tempResult ? result : tempResult;
        }

        return result;
    }

    private static int calcTop(int[][] matrix, int row, int col) {
        int result;
        int count;
        int tempRow;
        count = 0;
        for (tempRow = row - 1; tempRow >= 0 && matrix[tempRow][col] == 0; tempRow--) {
            matrix[tempRow][col] = 1;
            count++;
        }
        tempRow += 1;

        result = count + dfsCalc(matrix, tempRow, col);

        for (int i = tempRow; i < row; i++) {
            matrix[i][col] = 0;
        }

        return result;
    }

    private static int calcBottom(int[][] matrix, int row, int col) {
        int result;
        int count;
        int tempRow;
        count = 0;
        for (tempRow = row + 1; tempRow < matrix.length && matrix[tempRow][col] == 0; tempRow++) {
            matrix[tempRow][col] = 1;
            count++;
        }

        tempRow -= 1;
        result = count + dfsCalc(matrix, tempRow, col);

        for (int i = tempRow; i > row; i--) {
            matrix[i][col] = 0;
        }

        return result;
    }

    private static int calcLeft(int[][] matrix, int row, int col) {
        int result;
        int count;
        int tempCol;
        count = 0;
        for (tempCol = col - 1; tempCol >= 0 && matrix[row][tempCol] == 0; tempCol--) {
            matrix[row][tempCol] = 1;
            count++;
        }

        tempCol += 1;
        result = count + dfsCalc(matrix, row, tempCol);

        for (int i = tempCol; i < col; i++) {
            matrix[row][i] = 0;
        }

        return result;
    }

    private static int calcRight(int[][] matrix, int row, int col) {
        int result;
        int count;
        int tempCol;
        count = 0;
        for (tempCol = col + 1; tempCol < matrix.length && matrix[row][tempCol] == 0; tempCol++) {
            matrix[row][tempCol] = 1;
            count++;
        }

        tempCol -= 1;
        result = count + dfsCalc(matrix, row, tempCol);

        for (int i = tempCol; i > col; i--) {
            matrix[row][i] = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] blockCoords = {{0, 0}, {1, 1}};
        System.out.println(getOptimalSolution(4, blockCoords));

//        int[][] blockCoords = {{0, 0}, {4, 0}};
//        System.out.println(getOptimalSolution(5, blockCoords));

//        int[][] blockCoords = {{0, 0}};
//        System.out.println(getOptimalSolution(1, blockCoords));
    }
}

