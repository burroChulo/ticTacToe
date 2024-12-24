import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class gamev2 extends MyProgram {
    private JFrame gameFrame;
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameActive;
    private String aiDifficulty;
    
    public gamev2(String aiDifficulty) {
        this.aiDifficulty = aiDifficulty;
        gameFrame = new JFrame("Tic Tac Toe Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(400, 400);
        gameFrame.setLayout(new GridLayout(3, 3));
        gameFrame.setLocationRelativeTo(null);
        
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameActive = true;

        initializeBoard();

        gameFrame.setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setBackground(midTone);
                buttons[row][col].setForeground(typo);
                buttons[row][col].setFocusable(false);
                
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                gameFrame.add(buttons[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("") && gameActive) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setEnabled(false);
                if (checkForWin()) {
                    //JOptionPane.showMessageDialog(gameFrame, "Player " + currentPlayer + " wins!");
                    int n = JOptionPane.showOptionDialog(gameFrame,
                                                    "Player " + currentPlayer + " wins!",
                                                    "A Silly Question",
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                options, options[1]);
                    if (n == 0){
                        gameFrame.setVisible(false);
                        f.setVisible(true);
                    }
                    else if (n==1){
                        gameFrame.setVisible(false);
                        showDifficultyMenu();  }  
                    gameActive = false;
                } else if (isBoardFull()) {
                    //JOptionPane.showMessageDialog(gameFrame, "It's a tie!");
                    int n = JOptionPane.showOptionDialog(gameFrame,
                                                    "It's a tie!",
                                                    "A Silly Question",
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                options, options[1]);
                    if (n == 0){
                        gameFrame.setVisible(false);
                        f.setVisible(true); }
                    else if (n==1){
                        gameFrame.setVisible(false);
                        showDifficultyMenu(); 
                        
                    }
                    gameActive = false;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    if (aiDifficulty != null && currentPlayer == 'O') {
                        aiMove();
                    }
                }
            }
        }
    }

    private void aiMove() {
        if ("Easy".equals(aiDifficulty)) {
            randomMove();
        } else if ("Medium".equals(aiDifficulty)) {
            mediumMove();
        } else if ("Hard".equals(aiDifficulty)) {
            hardMove();
        } else if ("Impossible".equals(aiDifficulty)) {
            impossibleMove();
        }
    }

    private void randomMove() {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!buttons[row][col].getText().equals(""));

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);

        if (checkForWin()) {
            //JOptionPane.showMessageDialog(gameFrame, "Player " + currentPlayer + " wins!");
            int n = JOptionPane.showOptionDialog(gameFrame,
                    "Player " + currentPlayer + " wins!",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
        if (n == 0){
            gameFrame.setVisible(false);
            f.setVisible(true);
        }
        else if (n==1){
            gameFrame.setVisible(false);
            showDifficultyMenu(); 
            
        }
                gameActive = false;
        } else if (isBoardFull()) {
            //JOptionPane.showMessageDialog(gameFrame, "It's a tie!");
            int n = JOptionPane.showOptionDialog(gameFrame,
                    "It's a tie!",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
        if (n == 0){
            gameFrame.setVisible(false);
            f.setVisible(true); }
        else if (n==1){
            gameFrame.setVisible(false);
            showDifficultyMenu(); 
        }
            gameActive = false;
        } else {
            currentPlayer = 'X';
        }
    }

    private void mediumMove() {
        if (blockOpponentWin()) {
            return;
        }
        randomMove();
    }

    private boolean blockOpponentWin() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    buttons[row][col].setText("X");
                    if (checkForWin()) {
                        buttons[row][col].setText(String.valueOf(currentPlayer));
                        buttons[row][col].setEnabled(false);
                        return true;
                    }
                    buttons[row][col].setText("");
                }
            }
        }
        return false;
    }

    private void hardMove() {
        int[] bestMove = minimax(true).move;
        int row = bestMove[0];
        int col = bestMove[1];

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);

        if (checkForWin()) {
            //JOptionPane.showMessageDialog(gameFrame, "Player " + currentPlayer + " wins!");
            int n = JOptionPane.showOptionDialog(gameFrame,
                    "Player " + currentPlayer + " wins!",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
        if (n == 0){
            gameFrame.setVisible(false);
            f.setVisible(true);
        }
        else if (n==1){
            gameFrame.setVisible(false);
            showDifficultyMenu(); 
            
        }
            gameActive = false;
        } else if (isBoardFull()) {
            //JOptionPane.showMessageDialog(gameFrame, "It's a tie!");
            int n = JOptionPane.showOptionDialog(gameFrame,
                    "It's a tie!",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
        if (n == 0){
            gameFrame.setVisible(false);
            f.setVisible(true); }
        else if (n==1){
            gameFrame.setVisible(false);
            showDifficultyMenu(); 
            
        }
            gameActive = false;
        } else {
            currentPlayer = 'X';
        }
    }

    private void impossibleMove() {
        int[] bestMove = minimax(true).move;
        int row = bestMove[0];
        int col = bestMove[1];

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);

        if (checkForWin()) {
            //JOptionPane.showMessageDialog(gameFrame, "Player " + currentPlayer + " wins!");
            int n = JOptionPane.showOptionDialog(gameFrame,
                    "Player " + currentPlayer + " wins!",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
        if (n == 0){
            gameFrame.setVisible(false);
            f.setVisible(true);
        }
        else if (n==1){
            gameFrame.setVisible(false);
            showDifficultyMenu(); 
        }
            gameActive = false;
        } else if (isBoardFull()) {
            //JOptionPane.showMessageDialog(gameFrame, "It's a tie!");
            int n = JOptionPane.showOptionDialog(gameFrame,
                    "It's a tie!",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
        if (n == 0){
            gameFrame.setVisible(false);
            f.setVisible(true); }
        else if (n==1){
            gameFrame.setVisible(false);
            showDifficultyMenu(); 
        }
            gameActive = false;
        } else {
            currentPlayer = 'X';
        }
    }

    private class MoveScore {
        int[] move;
        int score;

        MoveScore(int[] move, int score) {
            this.move = move;
            this.score = score;
        }
    }

    private MoveScore minimax(boolean isMaximizing) {
        if (checkForWin('O')) {
            return new MoveScore(new int[]{-1, -1}, 1);
        } else if (checkForWin('X')) {
            return new MoveScore(new int[]{-1, -1}, -1);
        } else if (isBoardFull()) {
            return new MoveScore(new int[]{-1, -1}, 0);
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    buttons[row][col].setText(isMaximizing ? "O" : "X");
                    int score = minimax(!isMaximizing).score;
                    buttons[row][col].setText("");
                    if (isMaximizing) {
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = new int[]{row, col};
                        }
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove = new int[]{row, col};
                        }
                    }
                }
            }
        }
        return new MoveScore(bestMove, bestScore);
    }

    private boolean checkForWin() {
        return checkForWin(currentPlayer);
    }

    private boolean checkForWin(char player) {
        String playerSymbol = String.valueOf(player);

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(playerSymbol) &&
                buttons[row][1].getText().equals(playerSymbol) &&
                buttons[row][2].getText().equals(playerSymbol)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(playerSymbol) &&
                buttons[1][col].getText().equals(playerSymbol) &&
                buttons[2][col].getText().equals(playerSymbol)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(playerSymbol) &&
            buttons[1][1].getText().equals(playerSymbol) &&
            buttons[2][2].getText().equals(playerSymbol)) {
            return true;
        }

        if (buttons[0][2].getText().equals(playerSymbol) &&
            buttons[1][1].getText().equals(playerSymbol) &&
            buttons[2][0].getText().equals(playerSymbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}