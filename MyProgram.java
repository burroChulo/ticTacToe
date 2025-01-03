import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.border.*;
import java.awt.Color;
import javax.imageio.*;
import java.io.*;

public class MyProgram {
    
    public static JFrame f = new JFrame("jesus lives on o-block");
    
    // color palette         v preview link here v
    // https://colorhunt.co/palette/3ec1d3f6f7d7ff9a00ff165d
    public static Color base = new Color(62, 193, 211);
    public static Color midTone = new Color(246, 247, 215);
    public static Color high1 = new Color(255, 154, 0);
    public static Color typo = new Color(156,154,156);
    
    public static Object[] options = {"Back to Menu", "Restart"};
     
    public static void main(String[] args) throws MalformedURLException, IOException {
        // { 
        java.net.URL ur1 = new java.net.URL("https://"
                                            + "www.codehs.com"
                                            + "/uploads/1aa2f1c47fd10d4d0724fae0ae100f94");
        BufferedImage buff1 = ImageIO.read(ur1);
        Image imp = buff1.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        
        Icon yurl = new ImageIcon(imp);
        // } robot image :)
        
        // { 
        java.net.URL url2 = new java.net.URL("https://"
                                            + "www.codehs.com"
                                            + "/uploads/6e465a3879eb1ad5e8b72bb3d4407398");
        BufferedImage buff2 = ImageIO.read(url2);
        Image imp2 = buff2.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        
        Icon yurl2 = new ImageIcon(imp2);
        // } human image :)
        
        // TITLE PANEL
        JPanel prompt = new JPanel(new GridBagLayout());
        
        JLabel po = new JLabel("Tic-Tac-Toe");
        po.setFont(new Font("Courier", Font.BOLD, 35));
        po.setForeground(midTone);
        prompt.setBackground(base);
        
        prompt.add(po);
        
        f.add(prompt, BorderLayout.NORTH);
        prompt.setPreferredSize(new Dimension(550, 150));
        
        JButton player = new JButton(yurl2);
        JButton bot = new JButton(yurl);
        
        // main Panel
        JPanel panel = new JPanel();
        panel.setBackground(base);
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS); 
        // to set the box layout 
        panel.setLayout(boxlayout); 
        
        panel.setBorder(new EmptyBorder(new Insets(150, 150, 100, 150)));
        
        // PLAYER OPTION
        player.setBackground(midTone);
        player.setForeground(high1);
        panel.add(player);
        
        // PANEL 1 (MICHAEL)
        player.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            f.setVisible(false);
            game tic = new game();
            }  });
        
        panel.add(Box.createRigidArea(new Dimension(5, 50)));
        
        // BOT OPTION (JESUS)
        bot.setBackground(midTone);
        bot.setForeground(high1);
        
        bot.addActionListener(e -> showDifficultyMenu());
        
        panel.add(bot);
        

        // help Panel
        JPanel help = new JPanel();
        help.setBackground(base);
        help.setLayout(new BoxLayout(help, BoxLayout.LINE_AXIS));
        // help Button
        JButton addBtn = new JButton("?");
        addBtn.setBounds(380, 550, 25, 20);
        addBtn.setBorder(new RoundedBorder(5)); //10 is the radius
        addBtn.setBackground(midTone);
        addBtn.setForeground(high1);
        addBtn.addActionListener(e -> showHelp());
        
        help.add(Box.createRigidArea(new Dimension(0, 50)));
        help.add(Box.createHorizontalGlue());
        help.add(addBtn);
        
        f.add(panel); 
        f.add(help, BorderLayout.SOUTH);
        
        f.setSize(400, 550);
        f.setVisible(true);
    }
    
    
    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        
        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    
    private static void startGame(String aiDifficulty) {
        f.dispose();
        new gamev2(aiDifficulty);
    }
    
    public static void showDifficultyMenu() {
        f.setVisible(false);
        
        JFrame difficultyFrame = new JFrame("Select Difficulty");
        difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        difficultyFrame.setSize(400, 400);
        difficultyFrame.setLayout(new GridLayout(2, 2));
    
        JButton easyButton = new JButton("Easy");
        easyButton.setBackground(midTone);
        easyButton.setForeground(high1);
        easyButton.setFont(new Font("Arial", Font.PLAIN, 30));
        easyButton.setFocusPainted(false);
        easyButton.addActionListener(e -> {
            difficultyFrame.dispose();
            startGame("Easy");
        });
    
        JButton mediumButton = new JButton("Medium");
        mediumButton.setBackground(midTone);
        mediumButton.setForeground(high1);
        mediumButton.setFont(new Font("Arial", Font.PLAIN, 30));
        mediumButton.setFocusPainted(false);
        mediumButton.addActionListener(e -> {
            difficultyFrame.dispose();
            startGame("Medium");
        });
    
        JButton hardButton = new JButton("Hard");
        hardButton.setBackground(midTone);
        hardButton.setForeground(high1);
        hardButton.setFont(new Font("Arial", Font.PLAIN, 30));
        hardButton.setFocusPainted(false);
        hardButton.addActionListener(e -> {
            difficultyFrame.dispose();
            startGame("Hard");
        });
    
        JButton impossibleButton = new JButton("Impossible");
        impossibleButton.setBackground(midTone);
        impossibleButton.setForeground(high1);
        impossibleButton.setFont(new Font("Arial", Font.PLAIN, 30));
        impossibleButton.setFocusPainted(false);
        impossibleButton.addActionListener(e -> {
            difficultyFrame.dispose();
            startGame("Impossible");
        });
    
        difficultyFrame.add(easyButton);
        difficultyFrame.add(mediumButton);
        difficultyFrame.add(hardButton);
        difficultyFrame.add(impossibleButton);
        difficultyFrame.setVisible(true);
    }
    
    private static void showHelp() {
        JOptionPane.showMessageDialog(f,
                "Tic Tac Toe is a simple game played on a 3x3 grid.\n\n"
                        + "Players take turns to mark a cell in the grid with their symbol (X or O).\n"
                        + "The first player to align three of their symbols horizontally, vertically, or diagonally wins.\n"
                        + "If all cells are marked and no player has aligned three symbols, the game is a tie.",
                "How to Play Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
    }
}