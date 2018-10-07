package hctest.util;

import hctest.dto.VerifyCode;

import java.awt.Color;import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;

public class GraphicHelper {
    /**
     * 以字符串形式返回生成的验证码，同时输出一个图片
     *
     * @param width
     *            图片的宽度
     * @param height
     *            图片的高度
     */
    public static VerifyCode randRomVerifyCode(final int width, final int height) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        graphic.setColor(Color.getColor("F8F8F8"));
        graphic.fillRect(0, 0, width, height);

        Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
                Color.CYAN };
        // 在 "画板"上生成干扰线条 ( 50 是线条个数)
        for (int i = 0; i< 50; i++) {
            graphic.setColor(colors[random.nextInt(colors.length)]);
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int w = random.nextInt(20);
            final int h = random.nextInt(20);
            final int signA = random.nextBoolean() ? 1 : -1;
            final int signB = random.nextBoolean() ? 1 : -1;
            graphic.drawLine(x, y, x + w * signA, y + h * signB);
        }

        // 在 "画板"上绘制字母
        graphic.setFont(new Font("Comic Sans MS", Font.BOLD, random.nextInt(10)+25));
        int i;
        for (i = 0; i< 4; i++) {
            final int temp = random.nextInt(26) + 97;
            String s = String.valueOf((char) temp);
            sb.append(s);
            if(random.nextInt(2)>=1){
                s=s.toUpperCase();
            }else
            {
                s=s.toLowerCase();
            }
            graphic.setColor(colors[random.nextInt(colors.length)]);
            graphic.drawString(s, width/16+i * (width / 4), height - (height / 4));
        }
        graphic.dispose();

        VerifyCode vcode = new VerifyCode();
        vcode.setCode(sb.toString());
        vcode.setImage(image);
        return vcode;
    }

    public static VerifyCode randRomVerifyCode() {
        return GraphicHelper.randRomVerifyCode(180,40);
    }
}
