public class Queen {
    private int[] queens;
    private int size;
    private int solution;

    Queen(int size) {
        this.size = size;
        this.queens = new int[size];
        this.solution = 0;
    }

    private void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ", " + queens[i] + "; ");
        }
        System.out.println();
    }

    // 检测当前皇后位置是否和之前的皇后互相攻击
    private boolean check(int row, int col) {
        for (int preRow = 0; preRow < row; preRow++) {
            int preCol = queens[preRow];
            if (preCol == col) {
                return false;
            }
            if (row + col == preRow + preCol || row + preCol == col + preRow ) {
                return false;
            }
        }
        return true;
    }

    private void put(int row) {
        if (row == size) {
            solution++;
            System.out.print("第" + solution + "种放法: ");
            print();
            return;
        }

        for (int col = 0; col < size; col++) {
            if (check(row, col)) {
                queens[row] = col;
                put(row + 1);
            }
        }

    }

    /**
     * 非递归解法，其实就是手动搜寻和回溯
     * @param row
     */
    private void put_1(int row) {
        int col = 0;
        while (true) {
            while (col < size) {
                if (check(row, col)) {
                    queens[row] = col;
                    if (row == size - 1) {
                        // 找到一个解，回溯
                        solution++;
                        System.out.print("第" + solution + "种放法: ");
                        print();
                        //return;
                        row--;
                        col = queens[row] + 1;
                    } else {
                        row++;
                        col = 0;
                    }
                } else {
                    col++;
                }
            }
            // 当前行搜寻完毕无解，回溯
            row--;
            if (row >= 0) {
                col = queens[row] + 1;
            } else {
                break;
            }
        }
    }

}
