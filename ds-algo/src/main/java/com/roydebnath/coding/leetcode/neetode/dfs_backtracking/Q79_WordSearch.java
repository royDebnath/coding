package com.roydebnath.coding.leetcode.neetode.dfs_backtracking;
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
public class Q79_WordSearch {
    static boolean[][] visited;
    public static void main(String[] args) {

        char[][] input = {{'A','B','C','N'},
                          {'S','F','C','S'},
                          {'A','D','E','T'}};

        System.out.println(exist(input, "ABCCS"));
    }
    public static boolean exist(char[][] board, String word) {
        /*Find word's first letter.  Then call method to check its surroundings */
        int noOfRows = board.length;
        int noOfColumns = board[0].length;
        visited = new boolean[noOfRows][noOfColumns];
        for (int rowIndex = 0; rowIndex < noOfRows; rowIndex++) {
            for (int colIndex = 0; colIndex < noOfColumns; colIndex++)
                if (board[rowIndex][colIndex] == word.charAt(0)) { //If the first character is found call the recursive method to check surroundings to match the word.
                    if (finder(board, word, 0, rowIndex, colIndex)){// calling the finder with the  position(rowIndex, colIndex) of the first character of the word.
                        return true;
                    }
                }
        }
        return false;
    }

    public static boolean finder(char[][] b, String word, int wordIndex, int rowIndex, int colIndex) {
        /* once we get past word.length, we are done. */
        if (wordIndex==word.length())
            return true;

        char currentCharInWord = word.charAt(wordIndex);
        int noOfRows = b[0].length;
        int noOfCols = b.length;

        //check if rowIndex and colIndex are valid
        if (rowIndex < 0 || colIndex < 0 || rowIndex > noOfCols-1 || colIndex > noOfRows-1) {
            return false;
        }
        /* if off bounds, letter is seen, letter is unequal to word.charAt(wordIndex) return false */
        char currentCharInBoard = b[rowIndex][colIndex];

        if ( visited[rowIndex][colIndex] || currentCharInBoard != currentCharInWord)
            return false;

        /**
         * If we reached this point, that means current character is a valid character of the word
         * in the right sequence in the board. Rigt sequence being horizontally or vertically adjacent
         * that we put in the recursion logic. We will mark this position in the board as visited.
         */
        visited[rowIndex][colIndex] = true;


        /* recursion on all 4 sides for next letter, for the next match till the whole word is found.
         If the word is not found will return false to the calling exist() function. It will again look
         for the starting character of the word in the grid and will call this function*/
        if (finder(b, word, wordIndex + 1, rowIndex + 1, colIndex) || // check the character below
                finder(b, word, wordIndex + 1, rowIndex - 1, colIndex) || // check the character up
                finder(b, word, wordIndex + 1, rowIndex, colIndex + 1) || // check the character right
                finder(b, word, wordIndex + 1, rowIndex, colIndex - 1)){// check the character left
            return true;
        }

        /**
         *Will reach this point when the word is not found even though we found the first
         * and some consecutive character. In that case it will returned the last 0-set character
         * to its previous value and the recursion will return to the call from where we started
         * to check for the adjacent cells.
         */
        visited[rowIndex][colIndex] = false;

        return false;
    }
}