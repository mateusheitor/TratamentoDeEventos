package atv4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LigacaoBolinhas extends JPanel {
    private ArrayList<Point> bolinhas;
    private ArrayList<Color> cores;
    private ArrayList<Integer> ligacoes;
    private Point bolaArrastada;
    
    public LigacaoBolinhas() {
        bolinhas = new ArrayList<>();
        cores = new ArrayList<>();
        ligacoes = new ArrayList<>();
        bolaArrastada = null;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean bolaClicada = false;
                
                for (int i = 0; i < bolinhas.size(); i++) {
                    Point bola = bolinhas.get(i);
                    
                    if (isClicouNaBola(bola, e.getX(), e.getY())) {
                        if (ligacoes.size() < 2) {
                            if (!ligacoes.contains(i)) {
                                ligacoes.add(i);
                            }
                        }
                        bolaClicada = true;
                        break;
                    }
                }
                
                if (!bolaClicada) {
                    bolinhas.add(new Point(e.getX(), e.getY()));
                    cores.add(Color.RED);
                }
                repaint();
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (bolaArrastada != null) {
                    bolaArrastada.setLocation(e.getX(), e.getY());
                    repaint();
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                bolaArrastada = null;
            }
        });
    }
    
    private boolean isClicouNaBola(Point bola, int x, int y) {
        return (x >= bola.x - 20 && x <= bola.x + 20 && y >= bola.y - 20 && y <= bola.y + 20);
    }
    
    private void changeColor(int index) {
        Color cor = cores.get(index);
        
        if (cor == Color.RED) {
            cores.set(index, Color.GREEN);
        } else if (cor == Color.GREEN) {
            cores.set(index, Color.BLUE);
        } else {
            cores.set(index, Color.RED);
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < bolinhas.size(); i++) {
            Point bola = bolinhas.get(i);
            Color cor = cores.get(i);
            
            g.setColor(cor);
            g.fillOval(bola.x - 20, bola.y - 20, 40, 40);
        }
        
        if (ligacoes.size() == 2) {
            Point bola1 = bolinhas.get(ligacoes.get(0));
            Point bola2 = bolinhas.get(ligacoes.get(1));
            
            g.setColor(Color.BLACK);
            g.drawLine(bola1.x, bola1.y, bola2.x, bola2.y);
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ligação de Bolinhas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        LigacaoBolinhas ligacaoBolinhas = new LigacaoBolinhas();
        frame.add(ligacaoBolinhas);
        
        ligacaoBolinhas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < ligacaoBolinhas.bolinhas.size(); i++) {
                    Point bola = ligacaoBolinhas.bolinhas.get(i);
                    if (ligacaoBolinhas.isClicouNaBola(bola, e.getX(), e.getY())) {
                        ligacaoBolinhas.changeColor(i);
                        return;
                    }
                }
            }
        });
        
        ligacaoBolinhas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (Point bola : ligacaoBolinhas.bolinhas) {
                    if (ligacaoBolinhas.isClicouNaBola(bola, e.getX(), e.getY())) {
                        ligacaoBolinhas.bolaArrastada = bola;
                        return;
                    }
                }
            }
        });
        
        frame.setVisible(true);
    }
}
