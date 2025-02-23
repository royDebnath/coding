package leetcode.neetode.graphs;

/**
 * In this problem, we are given a m x n matrix called board, composed of characters 'X' and 'O'. The goal is to identify all the regions formed by 'O' that are completely surrounded by 'X' in all four directions (up, down, left, and right) without any breaks. Once these regions are identified, we need to flip all the 'O's in those regions to 'X's. It's important to note that an 'O' on the boundary of the board isn't considered surrounded, and hence, the regions connected to an 'O' on the board boundary are safe and should not be flipped.
 *
 * The solution is based on the idea that an 'O' region would not be captured if it's connected directly or indirectly to an 'O' on the boundary of the board since it cannot be surrounded entirely by 'X's. To implement this, we apply depth-first search (DFS) to mark all 'O's that are connected to the boundary 'O's with a temporary marker (in this case, '.'). This multi-step approach ensures we only flip those 'O's into 'X's that are truly surrounded:
 *
 * Identify Boundary-Connected 'O's: Iterate over the border rows and columns. For any 'O' found on the boundary, perform a DFS search to mark not only this 'O' but also any other 'O' connected to this one, directly or indirectly. We replace these 'O's temporarily with '.' to indicate they're safe from flipping.
 *
 * Capture Surrounded Regions: After the DFS marking step, we go through the entire board to flip the remaining 'O's (those that are not marked with '.') into 'X's as they are surrounded by 'X's.
 *
 * Restore Boundary-Connected Regions: Finally, we make another pass to convert all temporary markers '.' back into 'O's to restore the initial state for all the 'O's that were connected to the board boundary.
 *
 * By taking this approach, we ensure to flip only those 'O's that are truly surrounded by 'X's, while preserving the boundary-connected 'O's.
 *
 * Example Walkthrough
 *
 * Let's consider a small 3x4 board as an example:
 *
 * 1Board:
 * 2O X X X
 * 3X O O X
 * 4X X X X
 * Step 1: Identify Boundary-Connected 'O's
 *
 * We find that the 'O' in the top left corner is on the boundary. Applying the DFS algorithm starting from this 'O', we change it to a '.' to mark it as visited and safe:
 *
 * 1Board:
 * 2. X X X
 * 3X O O X
 * 4X X X X
 * Since there are no other 'O's directly connected to the boundary 'O's, our board remains the same after the first step.
 *
 * Step 2: Capture Surrounded Regions
 *
 * Now, we scan through the rest of the board. The 'O's in the middle are surrounded by 'X's and there is no DFS path from any boundary 'O' to them. So we flip these 'O's to 'X's:
 *
 * 1Board:
 * 2. X X X
 * 3X X X X
 * 4X X X X
 * Step 3: Restore Boundary-Connected Regions
 *
 * Finally, we need to revert the '.' back to 'O', since it was marked only for the purpose of identification and not flipping:
 *
 * 1Board:
 * 2O X X X
 * 3X X X X
 * 4X X X X
 * Our final board shows the 'O's in the middle flipped to 'X's, while the 'O' on the boundary remains intact. This concludes our walkthrough of how the depth-first search algorithm can be used to solve the problem of flipping all 'O's surrounded by 'X's on a board, while leaving the boundary-connected 'O's untouched.
 *
 *
 */
public class Q130_SurroundedRegions {
        private char[][] board; // Member variable to hold the input board
        private int rows; // Number of rows in the board
        private int cols; // Number of columns in the board

        // Main function that solves the board by replacing all 'O' not surrounded by 'X' with 'X'
        public void solve(char[][] board) {
            rows = board.length; // Set the number of rows
            cols = board[0].length; // Set the number of columns
            this.board = board; // Initialize the board member variable

            // Explore all 'O' on the borders, any 'O' connected to them should not be flipped
            // hence temporarily mark them with '.'
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Condition to check if it's on the border and if it's an 'O'
                    if ((i == 0 || i == rows - 1 || j == 0 || j == cols - 1) && board[i][j] == 'O') {
                        depthFirstSearch(i, j); // Call DFS to mark the connected 'O's
                    }
                }
            }

            // Flip all remaining 'O' to 'X' and back all '.' to 'O'.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // If it was marked '.', it's safe to flip it back to 'O'
                    if (board[i][j] == '.') {
                        board[i][j] = 'O';
                    }
                    // If it's still an 'O', it should be flipped to 'X' as it is not connected to a border
                    else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        // Depth-first search function to find all the 'O's connected to a border 'O'
        private void depthFirstSearch(int row, int col) {
            board[row][col] = '.'; // Mark the cell as visited by replacing 'O' with '.'
            int[] directions = {-1, 0, 1, 0, -1}; // Directions to move in the matrix
            for (int k = 0; k < 4; k++) { // Loop through possible directions (up, right, down, left)
                int nextRow = row + directions[k];
                int nextCol = col + directions[k + 1];
                // Check bounds and if the next cell is 'O', continue DFS
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && board[nextRow][nextCol] == 'O') {
                    depthFirstSearch(nextRow, nextCol); // Recursive call for connected 'O's
                }
            }
    }
}
