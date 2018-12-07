package GUI;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Button extends JButton implements MouseListener
{

    private String name;

    public Button(String str)
    {

        super(str);
        this.name = str;


        //Grâce à cette instruction, notre objet va s'écouter

        //Dès qu'un événement de la souris sera intercepté, il en sera averti

        this.addMouseListener(this);

    }

    //Méthode appelée lors du clic de souris

    public void mouseClicked(MouseEvent event) { }


    //Méthode appelée lors du survol de la souris

    public void mouseEntered(MouseEvent event) { }


    //Méthode appelée lorsque la souris sort de la zone du bouton

    public void mouseExited(MouseEvent event) { }


    //Méthode appelée lorsque l'on presse le bouton gauche de la souris

    public void mousePressed(MouseEvent event) { }


    //Méthode appelée lorsque l'on relâche le clic de souris

    public void mouseReleased(MouseEvent event) { }

}
