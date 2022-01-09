package entidades;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalizadorX implements INormalizadorDeImagenes{

    @Override
    public String normalizar(String stringANormalizar, int width, int height) {
        try {
            File pathToFile = new File(stringANormalizar);
            Image image = ImageIO.read(pathToFile);
            BufferedImage resizedBufferedImage = createResizedCopy(image, width, height, true);
            ImageIO.write(resizedBufferedImage, "png", new File("D:\\Documentos\\Santi\\uni\\2021\\diseño\\" + "123.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public NormalizadorX() {}
}