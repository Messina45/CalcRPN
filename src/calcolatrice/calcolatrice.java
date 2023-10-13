package calcolatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class calcolatrice {
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JPanel calc;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn0;
    private JButton btnC;
    private JButton btnUguale;
    private JButton btnpiu;
    private JButton btnmeno;
    private JButton btnper;
    private JButton btndiviso;
    private JButton btnparaperta;
    private JButton btnparchiusa;
    private JTextField txtrisultato;
    private JButton btnRPN;
    private JTextField txtespressione;

    //stampa
    void StampaEspressione(JButton but) {
        txtespressione.setText(txtespressione.getText() + but.getText());
    }

    //conversione
    String convertToRPN() {
        String exp = txtespressione.getText();
        String ris = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                ris += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    ris += " " + stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); //Tolgo la parentesi aperta rimasta
                }
            } else {
                if (!ris.isEmpty() && ris.charAt(ris.length() - 1) != ' ') {
                    ris += " ";
                }

                while (!stack.isEmpty() && compareSigns(stack.peek(), c)) {
                    ris += stack.pop() + " ";
                }

                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            ris += " " + stack.pop();
        }
        return ris;
    }


    boolean compareSigns(char a, char b) {
        int a1 = 0;
        int b1 = 0;

        if (a == '+' || a == '-') {
            a1 = 1;
        } else if (a == '*' || a == '/') {
            a1 = 2;
        }
        if (b == '+' || b == '-') {
            b1 = 1;
        } else if (b == 'x' || b == '/') {
            b1 = 2;
        }

        if (a1 >= b1)
            return true;
        else
            return false;
    }

    //risultato
    Float resolve() {
        String exp = txtespressione.getText();
        Stack<Float> stack = new Stack<>();
        String[] array = exp.split("\\s+");
        float ris = 0, n1, n2;

        for (int i = 0; i < array.length; i++) {
            if (array[i].matches("\\d+")) {
                stack.push(Float.parseFloat(array[i]));
            } else {
                n2 = stack.pop();
                n1 = stack.pop();
                switch (array[i]) {
                    case "+":
                        ris = n1 + n2;
                        break;
                    case "-":
                        ris = n1 - n2;
                        break;
                    case "x":
                        ris = n1 * n2;
                        break;
                    case "/":
                        ris = n1 / n2;
                        break;
                    default:
                        break;

                }
                stack.push(ris);

            }
        }
        return stack.pop();
    }

    public calcolatrice() {
        //numeri
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn0);
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn1);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn2);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn3);
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn4);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn5);
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn6);
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn7);
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn8);
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btn9);
            }
        });

        //segni
        btnpiu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btnpiu);
            }
        });
        btnmeno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btnmeno);
            }
        });
        btnper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btnper);
            }
        });
        btndiviso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btndiviso);
            }
        });
        btnparaperta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btnparaperta);
            }
        });
        btnparchiusa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StampaEspressione(btnparchiusa);
            }
        });
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUguale.setEnabled(false);
                txtespressione.setText("");
                txtrisultato.setText("");
            }
        });
        //risultati
        btnUguale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtrisultato.setText(String.valueOf(resolve()));
            }
        });
        btnRPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUguale.setEnabled(true);
                txtespressione.setText(convertToRPN());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("calcolatrice");
        frame.setContentPane(new calcolatrice().calc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
