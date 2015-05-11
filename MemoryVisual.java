/*
 * Zach Mason, Ryan Schoppy, Darren Martin, Brian Grillo
 * Operation Systems
 * Binary Buddy Memory Allocation
 *
 * version 05.03.2015
 */
package tree;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MemoryVisual extends JFrame implements Observer {

    final int width = 800;
    final int height = 1280;
    private static ArrayList<Tree.TreeNode> d;
    private static ArrayList<Integer> e;

    /**
     * Creates new form MemoryVisual
     */
    public MemoryVisual() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        getContentPane().setBackground(Color.white);
        setVisible(true);
        
        d = new ArrayList<Tree.TreeNode>();

    }

    /**
     * Implementation of Observer's update method that prints the data on the
     * screen.
     *
     * @param observed the sorter that is being observed by this observer
     * (visualizer)
     * @param data the list, as passed on to this visualizer by the sorter
     */
    @Override
    public void update(Observable observed, Object data) {
        d = (ArrayList<Tree.TreeNode>) data;
        repaint();
    }

    /**
     * a method that paints the bar
     *
     * @param Graphics g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int xStart = 300;
        int yStart = 1280;

        for (int i = 0; i < d.size(); i++) {
            int barH = d.get(i).memSize * 20;
            // Displays the letters being allocated on the right and the memory locations on the left
            if (d.get(i).process.equals("free")) {
                drawBlockSize(String.valueOf(d.get(i).memSize), 250, yStart - (barH / 2), g);
                g.setColor(Color.green);
            } else {
                drawBlockSize(String.valueOf(d.get(i).memSize), 250, yStart - (barH / 2), g);
                g.drawString(d.get(i).process, 625, yStart - (barH / 2));
                g.setColor(Color.red);
            }
            yStart = yStart - barH;
            g.fill3DRect(xStart, yStart, 300, barH, true);
        }
    }

    /**
     * a method that draws a string representing the memory size of the block
     *
     * @param size a String of the memory size
     * @param x the x axis location
     * @param y the y axis location
     * @param g Graphics
     */
    public void drawBlockSize(String size, int x, int y, Graphics g) {
        g.setColor(Color.black);
        g.drawString(size, x, y);
    }

     public static void main(String args[]) {

        BinaryBuddy binary = new BinaryBuddy();

        MemoryVisual mv = new MemoryVisual();
        binary.addObserver(mv);
        Random rn = new Random();
        e = new  ArrayList<Integer>();
        
        for(int i = 0; i < 50; i++)
        {
        	int choice = rn.nextInt(2);
        	if(choice == 0)
        	{
        		if(e.size() != 0)
        		{
        			int rand = rn.nextInt(e.size());
        			String temp = Integer.toString(e.get(rand));
        			binary.showLeaves();
        			binary.deallocate(temp);
        			e.remove(rand);
        		}
        		
        	}
        	else
        	{
        		int tmp = d.size();
        		int size = rn.nextInt(63)+1;
        		binary.showLeaves();
        		binary.allocate(size,Integer.toString(i));
        		if (tmp != d.size())
        		{
        			e.add(i);
        		}
        	}
        }
    }

}
