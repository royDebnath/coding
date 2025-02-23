package leetcode.blind75.graph;

import java.util.*;

/**
 * 417. Pacific Atlantic Water Flow
 * Medium
 * 6.8K
 * 1.3K
 * Companies
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and
 * the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells.
 * You are given an m x n integer matrix heights where
 * heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells
 * directly north, south, east, and west if the neighboring cell's height is
 * less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that
 * rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights =
 * [[1,2,2,3,5],
 *  [3,2,3,4,4],
 *  [2,4,5,3,1],
 *  [6,7,1,4,5],
 *  [5,1,1,2,4]]
 *
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 *        [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *        [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 *        [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 *        [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 *        [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 *        [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 *
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 */
public class Q70_PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlanticBFS(int[][] heights) {
        final int rows = heights.length;
        final int cols = heights[0].length;

        List<List<Integer>> ans = new ArrayList<>();

        Queue<int[]> queuePacific = new LinkedList<>();
        Queue<int[]> queueAtlantic = new LinkedList<>();

        boolean[][] visitedPacific = new boolean[rows][cols];
        boolean[][] visitedAtlantic = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            queuePacific.offer(new int[] {i, 0});
            queueAtlantic.offer(new int[] {i, cols - 1});
            visitedPacific[i][0] = true;
            visitedAtlantic[i][cols - 1] = true;
        }

        for (int j = 0; j < cols; j++) {
            queuePacific.offer(new int[] {0, j});
            queueAtlantic.offer(new int[] {rows - 1, j});
            visitedPacific[0][j] = true;
            visitedAtlantic[rows - 1][j] = true;
        }

        bfs(heights, queuePacific, visitedPacific);
        bfs(heights, queueAtlantic, visitedAtlantic);

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (visitedPacific[i][j] && visitedAtlantic[i][j])
                    ans.add(new ArrayList<>(Arrays.asList(i, j)));

        return ans;
    }

    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void bfs(int[][] heights, Queue<int[]> q, boolean[][] seen) {
        int noOfRows = heights.length;
        int noOfCols = heights[0].length;

        while (!q.isEmpty()) {
            final int currentRowIdx = q.peek()[0];
            final int currentColIdx = q.poll()[1];

            for (int[] dir : directions) { // traverse in all directions
                final int nextRowIdx = currentRowIdx + dir[0];
                final int nextColIdx = currentColIdx + dir[1];

                if (nextRowIdx < 0 || nextRowIdx == noOfRows || nextColIdx < 0 || nextColIdx == noOfCols){
                    continue; // skip invalid positions.
                }


                if (seen[nextRowIdx][nextColIdx] ||   // skip already seen
                        heights[nextRowIdx][nextColIdx] < heights[currentRowIdx][currentColIdx]){ // skip lower islands
                    continue;
                }
                //when the above conditions fail, its eligible for water flow
                q.offer(new int[] {nextRowIdx, nextColIdx});
                seen[nextRowIdx][nextColIdx] = true;
            }
        }
    }

    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        final int rows = heights.length;
        final int cols = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        boolean[][] visitedPacific = new boolean[rows][cols];
        boolean[][] visitedAtlantic = new boolean[rows][cols];

        //The dfs starts with a previous height of 0, considering ocean is zero, islands are higher
        for (int i = 0; i < rows; ++i) {
            dfs(heights, i, 0, 0, visitedPacific); // left wall
            dfs(heights, i, cols - 1, 0, visitedAtlantic);
        }

        for (int j = 0; j < cols; ++j) {
            dfs(heights, 0, j, 0, visitedPacific);
            dfs(heights, rows - 1, j, 0, visitedAtlantic);
        }

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (visitedPacific[i][j] && visitedAtlantic[i][j])
                    ans.add(new ArrayList<>(Arrays.asList(i, j)));

        return ans;
    }

    private void dfs(final int[][] heights, int rowIndex, int colIndex, int previousHeight, boolean[][] visited) {
        if (rowIndex < 0 || rowIndex == heights.length || colIndex < 0 || colIndex == heights[0].length)
            return;

        if (visited[rowIndex][colIndex] || //already seen
                heights[rowIndex][colIndex] < previousHeight) // island is lower than previous
            return;

        visited[rowIndex][colIndex] = true;

        dfs(heights, rowIndex + 1, colIndex, heights[rowIndex][colIndex], visited);
        dfs(heights, rowIndex - 1, colIndex, heights[rowIndex][colIndex], visited);
        dfs(heights, rowIndex, colIndex + 1, heights[rowIndex][colIndex], visited);
        dfs(heights, rowIndex, colIndex - 1, heights[rowIndex][colIndex], visited);
    }
}
