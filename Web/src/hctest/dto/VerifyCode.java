package hctest.dto;

import java.awt.image.BufferedImage;

public class VerifyCode {
    private BufferedImage image;
    private String code;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
