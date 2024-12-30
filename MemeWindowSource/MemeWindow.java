package com.example.demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;

public class MemeWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MemeWindow().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Meme Follows Cursor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        MemePanel memePanel = new MemePanel();
        frame.add(memePanel);
        frame.setVisible(true);
    }

    static class MemePanel extends JPanel {
        private Image memeImage;
        private int memeX = -100, memeY = -100; // Meme position
        private int currentSize = 50; // Current size of the meme
        private final int MIN_SIZE = 75; // Minimum size
        private final int MAX_SIZE = 300; // Maximum size

        private int prevX = -1, prevY = -1; // Previous mouse positions
        private boolean isMouseInside = false; // Flag for mouse presence
        private boolean isHorizontalLogic = true; // Determines which logic to follow (horizontal or vertical)
        private int entryDirection = 0; // -1 for left, 1 for right
        private int entryVerticalDirection = 0; // -1 for top, 1 for bottom

        public MemePanel() {
            try {
                memeImage = new ImageIcon(getClass().getResource("/meme.png")).getImage();
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if (isMouseInside) {
                        int newX = e.getX();
                        int newY = e.getY();

                        if (prevX == -1 || prevY == -1) {
                            prevX = newX;
                            prevY = newY;
                        }

                        // If meme has reached MAX_SIZE, stop resizing
                        if (currentSize == MAX_SIZE) {
                            memeX = newX;
                            memeY = newY;
                            prevX = newX;
                            prevY = newY;
                            repaint();
                            return;
                        }

                        // Calculate movement (horizontal or vertical based on entry type)
                        int sizeChange = 0;
                        if (isHorizontalLogic) {
                            int deltaX = Math.abs(newX - prevX);
                            sizeChange = Math.max(1, deltaX / 2);

                            // Horizontal resizing logic
                            if (newX > prevX) {
                                if (entryDirection == -1) {
                                    currentSize = Math.min(MAX_SIZE, currentSize + sizeChange);
                                } else {
                                    currentSize = Math.max(MIN_SIZE, currentSize - sizeChange);
                                }
                            } else if (newX < prevX) {
                                if (entryDirection == 1) {
                                    currentSize = Math.min(MAX_SIZE, currentSize + sizeChange);
                                } else {
                                    currentSize = Math.max(MIN_SIZE, currentSize - sizeChange);
                                }
                            }
                        } else {
                            int deltaY = Math.abs(newY - prevY);
                            sizeChange = Math.max(1, deltaY / 2);

                            // Vertical resizing logic
                            if (newY > prevY) {
                                if (entryVerticalDirection == -1) {
                                    currentSize = Math.min(MAX_SIZE, currentSize + sizeChange);
                                } else {
                                    currentSize = Math.max(MIN_SIZE, currentSize - sizeChange);
                                }
                            } else if (newY < prevY) {
                                if (entryVerticalDirection == 1) {
                                    currentSize = Math.min(MAX_SIZE, currentSize + sizeChange);
                                } else {
                                    currentSize = Math.max(MIN_SIZE, currentSize - sizeChange);
                                }
                            }
                        }

                        // Meme follows the cursor regardless of resizing
                        memeX = newX;
                        memeY = newY;
                        prevX = newX;
                        prevY = newY;
                        repaint();
                    }
                }
            });

            addMouseListener(new MouseListener() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isMouseInside = true;

                    // Reset scaling logic
                    prevX = e.getX();
                    prevY = e.getY();
                    currentSize = MIN_SIZE; // Reset meme size to minimum

                    // Determine proximity to edges for corner handling
                    int distanceToLeft = e.getX();
                    int distanceToRight = getWidth() - e.getX();
                    int distanceToTop = e.getY();
                    int distanceToBottom = getHeight() - e.getY();

                    // Determine entry type based on closest edge
                    if (Math.min(distanceToLeft, distanceToRight) < Math.min(distanceToTop, distanceToBottom)) {
                        // Horizontal entry (left or right)
                        isHorizontalLogic = true;
                        entryDirection = distanceToLeft < distanceToRight ? -1 : 1;
                        System.out.println("Mouse entered from: " + (entryDirection == -1 ? "LEFT" : "RIGHT"));
                    } else {
                        // Vertical entry (top or bottom)
                        isHorizontalLogic = false;
                        entryVerticalDirection = distanceToTop < distanceToBottom ? -1 : 1;
                        System.out.println("Mouse entered from: " + (entryVerticalDirection == -1 ? "TOP" : "BOTTOM"));
                    }

                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isMouseInside = false;
                    System.out.println("Mouse left the window.");
                    repaint();
                }

                @Override public void mouseClicked(MouseEvent e) {}
                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            if (isMouseInside && memeImage != null) {
                g2d.drawImage(memeImage, memeX - currentSize / 2, memeY - currentSize / 2, currentSize, currentSize, this);
            }
        }
    }
}
