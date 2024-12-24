import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
public class game extends MyProgram {
    
    int boardWidth = 400;
    int boardHeight = 450;
    
    JFrame fame = new JFrame(); // game frame
    
    JPanel pan = new JPanel(); // panel to hold title
    
    JLabel name = new JLabel(); // title
    
    JPanel board = new JPanel(); // actual game board
    
    JButton [][] bero = new JButton[3][3]; // (container for later buttons)
    
    String playerX = "X";
    String playerO = "O";
    boolean gameIsOver = false;
    int turn = 0;
    String currentPlayer= playerX;
    
    public game()
    {
        // FRAME PROPERTIES
        fame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fame.setVisible(true);
        fame.setSize(boardWidth,boardHeight);
        fame.setLocationRelativeTo(null);
        fame.setResizable(true);
        fame.setLayout(new BorderLayout()); // NORTH, SOUTH, WEST, EAST, CENTER
        
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setText("Tic tac toe");        
        name.setOpaque(true);
        
        pan.setLayout(new BorderLayout()); // NORTH, SOUTH, WEST, EAST, CENTER
        pan.add(name);
        fame.add(pan, BorderLayout.NORTH);
        
        board.setLayout(new GridLayout(3, 3));
        fame.add(board);
        
        for( int r = 0; r < 3;r++) {
            for(int c = 0; c < 3;c++) {
                JButton nam = new JButton(); 
                
                bero[r][c] = nam;
                board.add(nam);
                
                nam.setFont(new Font("Arial", Font.PLAIN, 60));
                nam.setBackground(midTone);
                nam.setForeground(typo);
                nam.setFocusable(false);
               
                nam.addActionListener( new ActionListener() {
                  public void actionPerformed(ActionEvent e ) {
                      if (gameIsOver){
                        int n = JOptionPane.showOptionDialog(fame,
                                                    "The Game is Over. Now what?",
                                                    "A Silly Question",
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                options, options[1]);
                        
                        if (n == 0){
                            fame.setVisible(false);
                            f.setVisible(true);
                        }
                        else if (n==1){
                            fame.setVisible(false);
                            game newo = new game();     
                        }
                      }
                      
                      JButton nam = (JButton) e.getSource();
                      
                      if (nam.getText() == "") {
                        nam.setText(currentPlayer);
                        turn++;
                        checkWinner();
                        
                        if (!gameIsOver) {
                          currentPlayer =  currentPlayer == playerX ? playerO : playerX;
                          name.setText(currentPlayer + "'s turn");
                        }
                      }
                      
                  }  });
                  
            }   }
    }
    
    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3;r++)  {
            if (bero[r][0].getText() == "") continue;
            
            if (bero[r][0].getText() == bero[r][1].getText() && bero[r][1].getText() == bero[r][2].getText())  {
                for(int i = 0; i < 3;i++)  {
                    setWinner(bero[r][i]);
                }
                gameIsOver = true;
                return;
            }
        }
        
        //vertical
        for (int c = 0; c<3;c++)  {
             if (bero[0][c].getText() == "") continue;
             
             if (bero[0][c].getText() == bero[1][c].getText() && bero[1][c].getText() == bero[2][c].getText())  {
                for (int i = 0; i < 3;i++)  {
                    setWinner(bero[i][c]);
                }
                gameIsOver = true;
                return;
            }
        }
        
        //diagonally
        if (bero[0][0].getText() == bero [1][1].getText()&& bero[1][1].getText() == bero[2][2].getText()&&bero[0][0].getText()!= "")  {
            for (int e =0; e < 3; e++)  {
                setWinner(bero[e][e]);
            }
            gameIsOver =true;
            return;
        }
        
        //anti diagonally
        if (bero[0][2].getText() == bero [1][1].getText()&& bero[1][1].getText() == bero[2][0].getText()&&bero[0][2].getText()!= "")  {
            setWinner(bero[0][2]);
            setWinner(bero[1][1]);
            setWinner(bero[2][0]);
            gameIsOver =true;
            return;
        }
        
        if (turn ==9)  {
            for(int r = 0; r < 3;r++)  {
                for(int c = 0; c < 3; c++)  {
                    setTie(bero[r][c]);
                }
            }
            gameIsOver = true;
        }
    }
    
    
    void setWinner(JButton nam)    {
        nam.setForeground(Color.green);
        nam.setBackground(Color.gray);
        name.setText(currentPlayer + " is the winner of the game");
    }
    
    void setTie(JButton nam)    {
        nam.setForeground(Color.red);
        nam.setBackground(Color.gray);
        name.setText("tie");
    }
}