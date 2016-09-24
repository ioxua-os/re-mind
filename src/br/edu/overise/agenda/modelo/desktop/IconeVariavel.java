package br.edu.overise.agenda.modelo.desktop;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @dispensavel Classe dispensável?
 * @author RandomDude
 * @credits https://tips4java.wordpress.com/?s=stretch+icon
 * Classe usada para gerar <tt>ImageIcons</tt> de tamanho adaptável
 * @see ImageIcon
 */
public class IconeVariavel extends ImageIcon{
	public IconeVariavel(String f){
		super(f);
	}
	public IconeVariavel(Image i){
		super(i);
	}
	public IconeVariavel(URL url){
		super(url);
	}

	
	
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y)
    {
        Image image = getImage();
        if (image == null) return;
        
        Insets insets = ((Container) c).getInsets();
        x = insets.left;
        y = insets.top;

        int w = c.getWidth() - x - insets.right;
        int h = c.getHeight() - y - insets.bottom;

        int iw = image.getWidth(c);
        int ih = image.getHeight(c);

        if ((iw * h) < (ih * w))
        {
            iw = (h * iw) / ih;
            x += (w - iw) / 2;
            w = iw;
        }
        else
        {
            ih = (w * ih) / iw;
            y += (h - ih) / 2;
            h = ih;
        }
        
        c.setSize(w, h);
        ImageObserver io = getImageObserver();

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, w, h, io == null ? c : io);
        g2d.dispose();

//        g.drawImage(bi, x, y, w, h, io == null ? c : io);
    }

    @Override
    public int getIconWidth()
    {
        return 0;
    }
    
    @Override
    public int getIconHeight()
    {
        return 0;
    }
}
