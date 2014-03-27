  /* Brandon Charlesworth, bjcworth@bu.edu
    CS112, Snyder, Boston University
    4/15/12
    
Concordance.java --- this class allows for a GUI for implementing our WordList class upon strings entered into a textbox
*/
import java.util.EventObject;
import java.awt.*;
import javax.swing.*;

public class Concordance
{

    public Concordance()
    {}

    //booloean method for whether or not string is a member of list[] array
    static Boolean exists(String string, String list[])
    {
        Boolean found = Boolean.valueOf(false);
        int i = 0;
        do
        {
            if(i >= list.length)
                break;
            if(string.equals(list[i]))
            {
                found = Boolean.valueOf(true);
                break;
            }
            i++;
        } while(true);
        return found;
    }
// processString -- replaces separators entered in the string with spaces
    static void processString(String str, WordList W)
    {
        //converts entered string to lowercase
        str = str.toLowerCase();
        str = str.replace('.', ' ');
        str = str.replace(',', ' ');
        str = str.replace(':', ' ');
        str = str.replace(';', ' ');
        str = str.replace('\'', ' ');
        str = str.replace('"', ' ');
        str = str.replace('-', ' ');
        str = str.replace('?', ' ');
        str = str.replace('!', ' ');
        str = str.replace('(', ' ');
        str = str.replace(')', ' ');
        String result[] = str.split("\\s");
        for(int i = 0; i < result.length; i++)
            if(!exists(result[i], blackList).booleanValue())
                W.add(result[i]);

    }
// main method -- creates window, window title, two text boxes, buttons, etc.
    public static void main(String args[])
    {
        
                       
                             
        JFrame frame = new JFrame("Concordance");
     
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(3);
        frame.setBackground(new Color(100, 149, 237));
        // Window title
        JLabel l1 = new JLabel("Concordance");
        // Window title font and style
        l1.setFont(new Font("TimesRoman", Font.BOLD, 45));
        JLabel l2 = new JLabel("Instructions: Insert text into the Input Box and click the Build button to produce a sorted word list.");
        JLabel l3 = new JLabel("Input Box");
        JLabel l4 = new JLabel("Word List");
        l2.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        l3.setFont(new Font("TimesRoman", 0, 20));
        l4.setFont(new Font("TimesRoman", 0, 20));
        //titles of buttons
        JButton b1 = new JButton("Clear");
              // build button, which initiates WordList to act upon the strings entered into textbox 1
        JButton b2 = new JButton("Build");
        b2.setFont(new Font("TimesRoman", 0, 20));
        JButton b3 = new JButton("Clear");
        JTextArea t1 = new JTextArea();
        t1.setLineWrap(true);
        JScrollPane left = new JScrollPane(t1);
        JBox.setSize(left, 400, 500);
        //scrollbar of left textbox set to appear as-needed
        left.setHorizontalScrollBarPolicy(31);
        left.setVerticalScrollBarPolicy(20);
        JTextArea t2 = new JTextArea();
        t2.setLineWrap(true);
        JScrollPane right = new JScrollPane(t2);
        JBox.setSize(right, 400, 500);
       //scrollbar of right textbox set to appear as-needed
       right.setHorizontalScrollBarPolicy(31);
        right.setVerticalScrollBarPolicy(20);
        // set width and height around text boxes using vspace,, hspace, and vglue
        JBox body = JBox.vbox(0.5F, new Component[] {
            JBox.vspace(30), l1, JBox.vglue(), l2, JBox.vglue(), JBox.hbox(0.5F, new Component[] {
                JBox.vbox(0.5F, new Component[] {
                    l3, JBox.vspace(15), left, JBox.vspace(15), b1
                }), JBox.hspace(10), b2, JBox.hspace(10), JBox.vbox(0.5F, new Component[] {
                    l4, JBox.vspace(15), right, JBox.vspace(15), b3
                })
            }), JBox.vspace(30)

        }
        );
        frame.add(body);
        frame.setVisible(true);
        JEventQueue events = new JEventQueue();
        events.listenTo(b1, "clearInput");
        events.listenTo(b2, "build");
        events.listenTo(b3, "clearOutput");
        do
        {
            EventObject event = events.waitEvent();
            JEventQueue _tmp = events;
            String name = JEventQueue.getName(event);
            if(name.equals("clearInput"))
                t1.setText("");
            else
            if(name.equals("build"))
                
            {
                String text = t1.getText();
                WordList X = new WordList();
                processString(text, X);
                t2.setText(X.sortedWordList());
            } else
            if(name.equals("clearOutput"))
                t2.setText("");
        } while(true);
    }
// blacklist array which contains forbidden entries
    static String blackList[] = {
        "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ll", ""};
    
    

}