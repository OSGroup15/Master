/*
 * Zach Mason, Ryan Schoppy, Darren Martin, Brian Grillo
 * Operation Systems
 * Binary Buddy Memory Allocation
 *
 * version 4.28.2015
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
    private ArrayList<Tree.TreeNode> d;

    /**
     * Creates new form MemoryVisual
     */
    public MemoryVisual() {
        setSize(width, height);
        getContentPane().setBackground(Color.white);

        d = new ArrayList<Tree.TreeNode>();

        setVisible(true);
    }

    /**
     * Implementation of Observer's update method that prints the data on the
     * screen.
     *
     * @param observed the sorter that is being observed by this observer
     * (visualizer)
     * @param data the list, as passed on to this visualizer by the sorter
     */
    public void update(Observable observed, Object data) {
        d = (ArrayList<Tree.TreeNode>) data;
        repaint();
    }

    /**
     * a method that paints the bar
     *
     * @param Graphics g
     */
    public void paint(Graphics g) {
        super.paint(g);
        int xStart = 300;
        int yStart = 1280;

        for (int i = 0; i < d.size(); i++) {
            int barH = d.get(i).memSize * 10;

            if (d.get(i).process.equals("free")) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.red);
            }
            yStart = yStart - barH;
            g.fill3DRect(xStart, yStart, 300, barH, true);
        }
    }

    public static void main(String args[]) {

        BinaryBuddy binary = new BinaryBuddy();

        MemoryVisual mv = new MemoryVisual();
        binary.addObserver(mv);

        binary.showLeaves();
        binary.allocate(3, "A");
        binary.showLeaves();
        binary.allocate(3, "B");
        binary.showLeaves();
        binary.allocate(24, "C");
        binary.showLeaves();
        binary.allocate(17, "D");
        binary.showLeaves();
        binary.allocate(2, "E");
        binary.showLeaves();
        binary.deallocate("D");
        binary.showLeaves();
        binary.deallocate("A");
        binary.showLeaves();
        binary.allocate(10, "F");
        binary.showLeaves();
        binary.allocate(1, "G");
        binary.showLeaves();
        binary.allocate(56, "H");
        binary.showLeaves();
        binary.deallocate("H");
        binary.showLeaves();
        binary.deallocate("B");
        binary.showLeaves();
        binary.deallocate("E");
        binary.showLeaves();
        binary.allocate(9, "J");
        binary.showLeaves();
        binary.allocate(2, "K");
        binary.showLeaves();
        binary.allocate(5, "L");
        binary.showLeaves();
        binary.deallocate("F");
        binary.showLeaves();
        binary.allocate(8, "M");
        binary.showLeaves();
        binary.deallocate("C");
        binary.showLeaves();
        binary.allocate(14, "N");
        binary.showLeaves();
        binary.allocate(6, "O");
        binary.showLeaves();
        binary.allocate(7, "P");
        binary.showLeaves();
        binary.deallocate("N");
        binary.showLeaves();
    }

}
