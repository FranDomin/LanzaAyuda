
package lanzaayuda;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.net.URL;
import javax.help.HelpSet;
import javax.help.HelpBroker;
import javax.swing.JOptionPane;

/* @author Francisco Dominguez Ruiz */

public class LanzaAyuda implements ActionListener, ItemListener
{
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Ayuda");
        JMenuItem menuItem1 = new JMenuItem("Contenido de ayuda");
        JMenuItem menuItem2 = new JMenuItem("About");
       
        menuBar.add(menu);
       
        menu.add(menuItem1);
        menu.add(menuItem2);
       
        HelpSet hs = obtenFicAyuda();
        HelpBroker hb = hs.createHelpBroker();
       
        hb.enableHelpOnButton(menuItem1,"baseDatos",hs);
       
        return menuBar;
    }
    
    
    private static void createAndShowGUI() 
    {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        LanzaAyuda demo = new LanzaAyuda();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
        
    }
    
    public Container createContentPane() 
    {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
    
    @Override
    public void itemStateChanged(ItemEvent arg0) 
    {
        obtenFicAyuda();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        obtenFicAyuda();
    }
    
    public HelpSet obtenFicAyuda()
    {
        try
        {
            ClassLoader c1 = LanzaAyuda.class.getClassLoader();
            File file = new File(LanzaAyuda.class.getResource("/help/helpset.hs").getFile()); // NO ENCUENTRA EL FICHERO helpset.hs
            URL url = file.toURI().toURL();
            
            // Crea un objeto HelpSet
            HelpSet hs = new HelpSet(null, url);
            return hs;
        }
	catch (Exception ex)
	{
            JOptionPane.showMessageDialog(null, "Fichero HelpSet no encontrado");
            return null;
        }
    }

    public static void main(String[] args) 
    {
       javax.swing.SwingUtilities.invokeLater(new Runnable() 
       {
            @Override
            public void run() 
            {
                createAndShowGUI();
            }
        }); 
    }
    
}
