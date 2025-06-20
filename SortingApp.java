//import javax.swing.*;
//import java.awt.*;
//import java.util.Random;
//
//    public class SortingVisualizer extends JPanel {
//
//        private static final int NUM_BARS = 50;
//        private int[] array = new int[NUM_BARS];
//        private int highlightedIndex1 = -1;
//        private int highlightedIndex2 = -1;
//
//        public SortingVisualizer() {
//            generateRandomArray();
//        }
//
//        // Generate random values
//        public void generateRandomArray() {
//            Random rand = new Random();
//            for (int i = 0; i < NUM_BARS; i++) {
//                array[i] = rand.nextInt(400) + 50;
//            }
//            repaint();
//        }
//
//        // Bubble Sort Visualization
//        public void bubbleSort() throws InterruptedException {
//            for (int i = 0; i < array.length - 1; i++) {
//                for (int j = 0; j < array.length - i - 1; j++) {
//                    highlightedIndex1 = j;
//                    highlightedIndex2 = j + 1;
//                    if (array[j] > array[j + 1]) {
//                        int temp = array[j];
//                        array[j] = array[j + 1];
//                        array[j + 1] = temp;
//                    }
//                    repaint();
//                    Thread.sleep(50);
//                }
//            }
//            highlightedIndex1 = -1;
//            highlightedIndex2 = -1;
//            repaint();
//        }
//
//        // Paint bars
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            int width = getWidth();
//            int barWidth = width / NUM_BARS;
//
//            for (int i = 0; i < NUM_BARS; i++) {
//                if (i == highlightedIndex1 || i == highlightedIndex2) {
//                    g.setColor(Color.RED);
//                } else {
//                    g.setColor(Color.BLUE);
//                }
//                g.fillRect(i * barWidth, getHeight() - array[i], barWidth - 2, array[i]);
//            }
//        }
//
//        public static void main(String[] args) {
//            JFrame frame = new JFrame("DSA Visualizer - Bubble Sort");
//            SortingVisualizer visualizer = new SortingVisualizer();
//
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//            frame.add(visualizer);
//            frame.setVisible(true);
//
//            new Thread(() -> {
//                try {
//                    visualizer.bubbleSort();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//    }
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SortingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DSA Visualizer");
        JButton bubbleButton = new JButton("Bubble Sort");
        JButton insertButton = new JButton("Insertion Sort");
        JButton resetButton = new JButton("Reset Array");

        SortingVisualizer visualizer = new SortingVisualizer();

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(Color.orange);

        bubbleButton.setBackground(Color.YELLOW);
        insertButton.setBackground(Color.GREEN);
        resetButton.setBackground(Color.white);

        controlPanel.add(bubbleButton);
        controlPanel.add(insertButton);
        controlPanel.add(resetButton);

        frame.setLayout(new BorderLayout());
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(visualizer, BorderLayout.CENTER);

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        bubbleButton.addActionListener(e -> new Thread(() -> {
            try {
                visualizer.bubbleSort();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start());

        insertButton.addActionListener(e -> new Thread(() -> {
            try {
                visualizer.insertionSort();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start());

        resetButton.addActionListener(e -> visualizer.generateRandomArray());
    }
}

class SortingVisualizer extends JPanel {
    private static final int NUM_BARS = 50;
    private int[] array = new int[NUM_BARS];
    private int highlightedIndex1 = -1;
    private int highlightedIndex2 = -1;

    public SortingVisualizer() {
        generateRandomArray();
    }

    public void generateRandomArray() {
        Random rand = new Random();
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = rand.nextInt(300) + 50;
        }
        repaint();
    }

    public void bubbleSort() throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                highlightedIndex1 = j;
                highlightedIndex2 = j + 1;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                repaint();
                Thread.sleep(100);
            }
        }
        highlightedIndex1 = -1;
        highlightedIndex2 = -1;
        repaint();
    }

    public void insertionSort() throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                highlightedIndex1 = j;
                highlightedIndex2 = j + 1;
                array[j + 1] = array[j];
                j--;
                repaint();
                Thread.sleep(50);
            }
            array[j + 1] = key;
            repaint();
        }
        highlightedIndex1 = -1;
        highlightedIndex2 = -1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int barWidth = width / NUM_BARS;

        for (int i = 0; i < NUM_BARS; i++) {
            if (i == highlightedIndex1 || i == highlightedIndex2) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(i * barWidth, getHeight() - array[i], barWidth - 2, array[i]);
        }
    }
}
