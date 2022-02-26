package cf.ystapi.util.Img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Random;

public class Captcha {

    public InputStream export(String randomStr, int LineCount, int circleCount){
        int width = 320;
        int height = 180;
        return createImage(randomStr, LineCount, circleCount);
    }

    public InputStream export(String randomStr){
        int width = 320;
        int height = 180;
        return createImage(randomStr, 40, 20);
    }

    protected Font font = new Font("Verdana", Font.ITALIC
            | Font.BOLD, 28);

    protected Color color(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + num(bc - fc);
        int g = fc + num(bc - fc);
        int b = fc + num(bc - fc);
        return new Color(r, g, b);
    }

    public int num(int num) {
        return (new Random()).nextInt(num);
    }

    public String generateCaptchaText(int captchaLength) {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        StringBuffer captchaStrBuffer = new StringBuffer();
        Random rnd = new Random();

        while (captchaStrBuffer.length() < captchaLength) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            captchaStrBuffer.append(saltChars.substring(index, index + 1));
        }

        return captchaStrBuffer.toString();
    }

    private final int IMAGE_WIDTH = 160;
    private final int IMAGE_HEIGHT = 80;
    private final int TEXT_SIZE = 25;
    private final String FONT_FAMILY_NAME = "Verdana";

    private SecureRandom randomNumbers = new SecureRandom();

    private InputStream createImage(String word, int lineCount, int circleCount) {
        BufferedImage bImg = null;
        try {
            bImg = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
                    BufferedImage.TYPE_INT_ARGB_PRE);
            Graphics2D g2 = bImg.createGraphics();

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

            Font font = new Font(FONT_FAMILY_NAME, Font.BOLD, TEXT_SIZE);
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            char[] chars = word.toCharArray();
            int x = 10;
            int y = IMAGE_HEIGHT / 2 + TEXT_SIZE / 2;

            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                g2.drawString(String.valueOf(ch), x + font.getSize() * i, y
                        + (int) Math.pow(-1, i) * (TEXT_SIZE / 6));
            }

            Color color;
            for (int i = 0; i < circleCount; i++) {
                color = color(150, 250);
                g2.setColor(color);
                g2.drawOval(num(160), num(80), 5 + num(10),
                        5 + num(10));
            }
            MyLine[] lines = new MyLine[lineCount];

            // create lines
            for (int count = 0; count < lines.length; count++)
            {
                int x1 = randomNumbers.nextInt(300);
                int y1 = randomNumbers.nextInt(300);
                int x2 = randomNumbers.nextInt(300);
                int y2 = randomNumbers.nextInt(300);

                Color colors = new Color(randomNumbers.nextInt(256),
                        randomNumbers.nextInt(256), randomNumbers.nextInt(256));

                lines[count] = new MyLine(x1, y1, x2, y2, colors);
            }
            for (MyLine line : lines)
                line.draw(g2);

            g2.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bImg, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            bImg = null;
        }
        return null;
    }
}
class MyLine
{
    private int x1; // x-coordinate of first endpoint
    private int y1; // y-coordinate of first endpoint
    private int x2; // x-coordinate of second endpoint
    private int y2; // y-coordinate of second endpoint
    private Color color; // color of this line

    // constructor with input values
    public MyLine(int x1, int y1, int x2, int y2, Color color)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    // Actually draws the line
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
