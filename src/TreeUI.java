/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package components;

/**
 * This application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TreeUI extends JPanel
        implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public static Node x = null;
    public static String a = "";
    
    public TreeUI() {
        super(new GridLayout(1,0));

      
        //Create the nodes.
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode(x);
 
        createNodes(top  , x);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        //initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));
        
        ImageIcon leafIcon = createImageIcon("middle.gif");
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer =
                    new DefaultTreeCellRenderer();
            renderer.setClosedIcon(leafIcon);
            renderer.setOpenIcon(leafIcon);
            tree.setCellRenderer(renderer);
        }
        //Add the split pane to this panel.
        add(splitPane);
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
    	
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (true) {
        	
            Node book = (Node)nodeInfo;

            if(book.item == '+' || book.item == '/' || book.item == '*' || book.item == '-'){
               // Homework1 w = null ;
            	a = Homework1.infix(book) ;
            	displayURL(a + "");
       //         System.out.println(book);
            	
            	a = "" ;
            }else{
            	displayURL(book.item + "");
           }
        } else {
        //    displayURL(helpURL);
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }
//-----------------------------------------------------------------------------
    private class BookInfo {
        public String bookName;
        public String bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = filename;
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                        + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }
  //-----------------------------------------------------------------------------
    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }

     //   displayURL(helpURL);
    }
 //-----------------------------------------------------------------------------
    private void displayURL(String bookURL) {
    	
    	htmlPane.setText(bookURL);
    	
    }
 //-----------------------------------------------------------------------------
    private DefaultMutableTreeNode createNodes(DefaultMutableTreeNode top , Node temp ) {


    	if(temp.left != null){
    	//	top.add(now);
    		DefaultMutableTreeNode now = new DefaultMutableTreeNode(temp.left);
    		top.add(now);
    		createNodes(now , temp.left) ;
    	}else if(temp.left == null&& temp.dad.left == temp){
//System.out.println(a);
    		DefaultMutableTreeNode now = new DefaultMutableTreeNode(new BookInfo (""+temp.item, a));
    		return now;
    	}
  
    	if(temp.right != null){
    //		top.add(now);
    		DefaultMutableTreeNode now = new DefaultMutableTreeNode(temp.right);
    		top.add(now);
    		createNodes(now , temp.right) ;
    	}else if(temp.right == null && temp.dad.right == temp){
//System.out.println(a);
    		DefaultMutableTreeNode now = new DefaultMutableTreeNode(new BookInfo (""+temp.item, a));
    		return now;
    	}
    	
    	return null ;
    }
//-----------------------------------------------------------------------------
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */

    
    
    public static void createAndShowGUI(Node n) {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
        //Create and set up the window.
    	x = n ;
    	
        JFrame frame = new JFrame("Binary Tree Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new TreeUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(Node n){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(n);
            }
        });
    }
    
//    private class MyRenderer extends DefaultTreeCellRenderer {
//        Icon tutorialIcon;
//
//        public MyRenderer(Icon icon) {
//            tutorialIcon = icon;
//        }

//        public Component getTreeCellRendererComponent(
//                            JTree tree,
//                            Object value,
//                            boolean sel,
//                            boolean expanded,
//                            boolean leaf,
//                            int row,
//                            boolean hasFocus) {
//
//            super.getTreeCellRendererComponent(
//                            tree, value, sel,
//                            expanded, leaf, row,
//                            hasFocus);
//            if (leaf && isTutorialBook(value)) {
//                setIcon(tutorialIcon);
//                setToolTipText("This book is in the Tutorial series.");
//            } else {
//                setToolTipText(null); //no tool tip
//            }
//
//            return this;
//        }
        
//        protected boolean isTutorialBook(Object value) {
//            DefaultMutableTreeNode node =
//                    (DefaultMutableTreeNode)value;
//            BookInfo nodeInfo = 
//                    (BookInfo)(node.getUserObject());
//            String title = nodeInfo.bookName;
//            if (title.indexOf("Tutorial") >= 0) {
//                return true;
//            } 
//
//            return false;
//        }
    
    
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TreeUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
}