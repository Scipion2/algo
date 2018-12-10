package GUI;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;


public class ZDialog extends JDialog {

    private ZDialogInfo zInfo = new ZDialogInfo();

    private boolean sendData;

    private JLabel numberLabel, xminLabel, xmaxLabel, yminLabel, ymaxLabel;;

    private JFormattedTextField number_of_point,Xmin,Xmax, Ymin, Ymax;


    public ZDialog(JFrame parent, String title, boolean modal){

        super(parent, title, modal);

        this.setSize(550, 270);

        this.setLocationRelativeTo(null);

        this.setResizable(false);

        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        this.initComponent();

    }


    public ZDialogInfo showZDialog(){

        this.sendData = false;

        this.setVisible(true);

        return this.zInfo;

    }


    private void initComponent()
    {



        //Le number_of_point

        JPanel panNumber = new JPanel();
        panNumber.setBackground(Color.white);
        panNumber.setPreferredSize(new Dimension(220, 60));
        number_of_point=new JFormattedTextField(NumberFormat.getIntegerInstance());
        number_of_point.setPreferredSize(new Dimension(100, 25));
        number_of_point.setText("10");
        panNumber.setBorder(BorderFactory.createTitledBorder("Nombre de points"));

        numberLabel = new JLabel("nombre de point:");
        panNumber.add(numberLabel);
        panNumber.add(number_of_point);


        //Le Xmin

        JPanel panXmin = new JPanel();
        panXmin.setBackground(Color.white);
        panXmin.setPreferredSize(new Dimension(220, 60));
        panXmin.setBorder(BorderFactory.createTitledBorder("Abscisse minimale"));

        Xmin=new JFormattedTextField(NumberFormat.getIntegerInstance());
        Xmin.setPreferredSize(new Dimension(100,25));
        Xmin.setText("1");
        xminLabel = new JLabel("X min : ");
        panXmin.add(xminLabel);
        panXmin.add(Xmin);


        //Le Xmax

        JPanel panXmax = new JPanel();
        panXmax.setBackground(Color.white);
        panXmax.setBorder(BorderFactory.createTitledBorder("Abscisse maximale"));
        panXmax.setPreferredSize(new Dimension(440, 60));

        Xmax=new JFormattedTextField(NumberFormat.getIntegerInstance());
        Xmax.setPreferredSize(new Dimension(100,25));
        Xmax.setText("10");
        xmaxLabel=new JLabel("X max :");
        panXmax.add(xmaxLabel);
        panXmax.add(Xmax);


        //Le Ymin

        JPanel panYmin = new JPanel();
        panYmin.setBackground(Color.white);
        panYmin.setPreferredSize(new Dimension(220, 60));
        panYmin.setBorder(BorderFactory.createTitledBorder("Ordonnee minimale"));

        Ymin=new JFormattedTextField(NumberFormat.getIntegerInstance());
        Ymin.setPreferredSize(new Dimension(100,25));
        Ymin.setText("1");
        yminLabel = new JLabel("Y min : ");
        panYmin.add(yminLabel);
        panYmin.add(Ymin);


        //Le Ymax

        JPanel panYmax = new JPanel();
        panYmax.setBackground(Color.white);
        panYmax.setPreferredSize(new Dimension(220, 60));
        panYmax.setBorder(BorderFactory.createTitledBorder("Ordonnee maximale"));

        Ymax=new JFormattedTextField(NumberFormat.getIntegerInstance());
        Ymax.setPreferredSize(new Dimension(100,25));
        Ymax.setText("10");
        ymaxLabel = new JLabel("Y max :");
        panYmax.add(ymaxLabel);
        panYmax.add(Ymax);



        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panNumber);
        content.add(panXmin);
        content.add(panXmax);
        content.add(panYmin);
        content.add(panYmax);


        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");
        okBouton.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {

                zInfo = new ZDialogInfo(number_of_point.getText(), Xmin.getText(), Xmax.getText(), Ymin.getText() ,Ymax.getText());
                setVisible(false);

            }

        });


        JButton cancelBouton = new JButton("Annuler");

        cancelBouton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {

                setVisible(false);

            }

        });


        control.add(okBouton);

        control.add(cancelBouton);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);

    }

}