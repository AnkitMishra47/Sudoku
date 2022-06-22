import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private static JTextField[][] fields ;
    private static JTabbedPane tab ;
    private static JFrame frame ;
    private static JTextArea area ;
    public App(){
        // Giving look and feel
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch ( Exception e ){
            System.out.println("Can't set System look and feel");
        }


        frame = new JFrame("Sudoku Solver");  // Initializing Frame
        JPanel panel = new JPanel();         // Initializing panel
        frame.add(panel);                   // adding panel to Frame

        tab = new JTabbedPane();     // Initializing Tab
        panel.add(tab);             //  adding tab to panel
        tab.setFont(tab.getFont().deriveFont(40f));


        // Panel 11
        JPanel panel1 = new JPanel();
        tab.addTab( "Gird",panel1);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel gird = new JPanel();
        panel1.add(gird);
        gird.setLayout(new GridLayout(9,9));
        fields = new JTextField[9][9];

        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9 ; j++) {
                fields[i][j] = new JTextField(2);
                fields[i][j].setForeground(Color.RED);
                fields[i][j].setFont(fields[i][j].getFont().deriveFont(40f));
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                gird.add(fields[i][j]);
            }
        }
        // Panel 2
        JPanel panel2 = new JPanel();
        tab.addTab( "Text",panel2);
        area = new JTextArea(9,30);
        area.setFont(area.getFont().deriveFont(40f));
        panel2.add(area);


        // Solve Button
        JButton solveButton = new JButton("Solve");
        solveButton.setFont(solveButton.getFont().deriveFont(40f));
        panel.add(solveButton);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int[][] board = new int[9][9];
                    if (tab.getSelectedIndex() == 0) {
                        for (int i = 0; i < fields.length; i++) {
                            for (int j = 0; j < fields[0].length; j++) {
                                if (fields[i][j].getText().equals(" ") || fields[i][j].getText().equals("") || fields[i][j].getText().equals(".")) {
                                    board[i][j] = 0;
                                } else {
                                    board[i][j] = Integer.parseInt(fields[i][j].getText());
                                }
                            }
                        }
                    } else {
                        String[] line_row = area.getText().split("\n");
                        for (int i = 0; i < 9; i++) {
                            line_row[i] = line_row[i].trim();
                            String[] line_col = line_row[i].split(" ");
                            for (int j = 0; j < 9; j++) {
                                if (line_col[j].equals(".") || line_col[j].equals("0")) board[i][j] = 0;
                                else board[i][j] = Integer.parseInt(line_col[j]);
                            }
                        }
                    }
                    Sudoku.solve(board);
                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < fields.length; i++) {
                        for (int j = 0; j < fields.length; j++) {
                            fields[i][j].setText(String.valueOf(Sudoku.getResult()[i][j]));
                           s.append(Sudoku.getResult()[i][j] + " ");

                        }
                        s.append("\n");
                    }
                    area.setText(s.toString());
                    Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD, 40);

                    area.setForeground(Color.BLUE);
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Input Sudoku Not valid ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(clearButton.getFont().deriveFont( 40f));
        panel.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int rows = 0; rows < fields.length; rows++) {
                    for (int cols = 0; cols < fields.length ; cols++) {
                        fields[rows][cols].setText("");
                    }
                }
                area.setText("");
            }
        });


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
    git remote add origin https://github.com/AnkitMishra47/Sudoku.git
}

