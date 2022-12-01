package nl.erends.advent.imagery;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GifSequenceWriter {

    private static ImageOutputStream output;
    private static GifSequenceWriter writer;
    private final ImageWriter gifWriter;
    private final ImageWriteParam imageWriteParam;
    private final IIOMetadata imageMetaData;

    /**
     * Creates a new GifSequenceWriter
     *
     * @param outputStream the ImageOutputStream to be written to
     * @param timeBetweenFramesMS the time between frames in miliseconds
     * @param loop wether the gif should loop repeatedly
     *
     * @author Elliot Kroo (elliot[at]kroo[dot]net)
     */
    private GifSequenceWriter(ImageOutputStream outputStream, int timeBetweenFramesMS, boolean loop) throws IOException {

        gifWriter = ImageIO.getImageWritersBySuffix("gif").next();
        imageWriteParam = gifWriter.getDefaultWriteParam();
        ImageTypeSpecifier imageTypeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
        imageMetaData = gifWriter.getDefaultImageMetadata(imageTypeSpecifier, imageWriteParam);
        String metaFormatName = imageMetaData.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormatName);

        IIOMetadataNode graphicControlExtension = getNode(root, "GraphicControlExtension");
        graphicControlExtension.setAttribute("disposalMethod", "none");
        graphicControlExtension.setAttribute("userInputFlag", "FALSE");
        graphicControlExtension.setAttribute("transparentColorFlag", "FALSE");
        graphicControlExtension.setAttribute("delayTime", Integer.toString(timeBetweenFramesMS / 10));
        graphicControlExtension.setAttribute("transparentColorIndex", "0");

        IIOMetadataNode applicationExtension = new IIOMetadataNode("ApplicationExtension");

        applicationExtension.setAttribute("applicationID", "NETSCAPE");
        applicationExtension.setAttribute("authenticationCode", "2.0");
        applicationExtension.setUserObject(new byte[]{ 0x1, (byte) ((loop ? 0 : 1) & 0xFF), (byte) (0)});
        getNode(root, "ApplicationExtensions").appendChild(applicationExtension);
        imageMetaData.setFromTree(metaFormatName, root);
        gifWriter.setOutput(outputStream);
        gifWriter.prepareWriteSequence(null);
    }

    private void close() throws IOException {
        gifWriter.endWriteSequence();
    }

    private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        int nNodes = rootNode.getLength();
        for (int i = 0; i < nNodes; i++) {
            if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
                return((IIOMetadataNode) rootNode.item(i));
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return(node);
    }

    static void startGif(String filename, int frameRate) throws IOException {
        output = new FileImageOutputStream(new File(filename));
        writer = new GifSequenceWriter(output, frameRate, true);

    }

    static void addImage(BufferedImage image) throws IOException {
        writer.gifWriter.writeToSequence(new IIOImage(image, null, writer.imageMetaData), writer.imageWriteParam);
    }

    static void closeGif() throws IOException {
        writer.close();
        output.close();
    }
}
