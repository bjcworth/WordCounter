/* 
    
   Brandon Charlesworth, bjcworth@bu.edu
    CS112, Snyder, Boston University
    4/15/12
    
WordList.java - This class takes in strings and counts the number of occurences of each string, then sorts the list by number of occurences
 

*/
import java.util.*;
import java.io.PrintStream;
public class WordList
{
    class Node {
        
        // toString method for Node, allowing for a string representation of our Node class
        public String toString()
        { return word + ("\t[")+ count + ("]");}
        
        String word;
        Node left;
        Node right;
        int count;
        
        // constructor for Node
        public Node(String x) {
            super();
            word = x;
            left = null;
            right = null;
            count = 1;
        }
    }
    
    // Initialization of roots
    private static Node root;
    private static Node root2;
    
    // WordList constructor
    public WordList() {
        root = null;
        root2 = null;
    }
    
    public void add(String x)
    {
        if(!x.equals("")) {
            root = add(root, x);
        }
    }
    // add -- allows for adding of word to BST
    public Node add(Node n, String word) {
        if(n == null) {
            return new Node(word);
        }
        if(word.compareTo(n.word) < 0)
        {
            n.left = add(n.left, word);
            return n;
        }
        if(word.compareTo(n.word) > 0)
        {
            n.right = add(n.right, word);
            return n;
        } else
        {
            n.count++;
            return n;
        }
    }
    
 
    
    public void add2(Node o)
    {
        root2 = add2(root2, o);
    }
    // add2 -- allows for BST traversal
    public Node add2(Node n, Node o)
    {
        if(n == null)
        {
            o.left = o.right = null;
            return o;
        }
        if(n.count < o.count)
        {
            n.left = add2(n.left, o);
            return n;
        }
        if(n.count > o.count)
        {
            n.right = add2(n.right, o);
            return n;
        }
        if(n.word.compareTo(o.word) > 0)
        {
            n.left = add2(n.left, o);
            return n;
        }
        if(n.word.compareTo(o.word) < 0)
        {
            n.right = add2(n.right, o);
            return n;
        } else return n;}

   
    public String sortedWordList()
    {
        sortList(root);
        return BSTtoString(root2);
    }
    
    // sortList -- allows for sorting of the word list by calling upon add2 for traversal
    private void sortList(Node p)
    {
        if(p != null)
        {
            sortList(p.left);
            sortList(p.right);
            add2(p);
        }
    }
    
    // toString method for BST -- allows for string representation of BST
    private String BSTtoString(Node q)
    {
        if(q == null)
            return "";
        else
          
            return BSTtoString(q.left) + (q) +("\n")+ BSTtoString(q.right);
    }
        // Unit test
       public static void main(String args[])
    {
        WordList A = new WordList();
        A.add("Hello");
        A.add("my");
        A.add("name");
        A.add("name");
        A.add("is");
        A.add("is");
        A.add("Brandon");
        A.add("Brandon");
        A.add("Brandon");
        A.add("Charlesworth");
        System.out.println(A.sortedWordList());
    }     
       
}