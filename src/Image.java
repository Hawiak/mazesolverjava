import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by harmakkerman on 2/26/17.
 */
public class Image {

    public File file;
    public BufferedImage bufferedImage;

    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        this.file = new File(ImageName);
        this.bufferedImage = ImageIO.read(this.file);

        // get DataBufferBytes from Raster
        WritableRaster raster = this.bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }
}
