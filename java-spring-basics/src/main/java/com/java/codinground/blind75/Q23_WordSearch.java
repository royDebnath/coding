package com.java.codinground.blind75;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Input: board = [["A","B","C","E"],
 *                 ["S","F","C","S"],
 *                 ["A","D","E","E"]],
 *        word = "ABCCED"
 * Output: true
 *
 */
public class Q23_WordSearch {
    public static void main(String[] args) {

        char[][] input = {{'A','B','C','E'},
                          {'S','F','C','S'},
                          {'A','D','E','E'}};

        System.out.println(exist(input, "ABCCEF"));
    }
    public static boolean exist(char[][] board, String word) {
        /*Find word's first letter.  Then call method to check it's surroundings */
        for (int row = 0; row < board.length; row++)
            for (int column = 0; column < board[0].length; column++)
                if (board[row][column] == word.charAt(0) && help(board, word, 0, row, column))
                    return true;

        return false;
    }

    public static boolean help(char[][] board, String word, int start, int row, int column) {
        /* once we get past word.length, we are done. */
        if (word.length() <= start)
            return true;

        /* if off bounds, letter is seen, letter is unequal to word.charAt(start) return false */
        if (row < 0 || column < 0 || row >= board.length || column >= board[0].length || board[row][column] == '0' || board[row][column] != word.charAt(start))
            return false;

        /* set this board position to seen. (Because we can use this postion) */
        char tmp = board[row][column];
        board[row][column] = '0';

        /* recursion on all 4 sides for next letter, if works: return true */
        if (help(board, word, start + 1, row + 1, column) ||
                help(board, word, start + 1, row - 1, column) ||
                help(board, word, start + 1, row, column + 1) ||
                help(board, word, start + 1, row, column - 1))
            return true;

        //Set back to unseen
        board[row][column] = tmp;

        return false;
    }
}
