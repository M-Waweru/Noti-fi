package broadcast;

import broadcast.Util;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.StandardExtensionElement;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.xdata.packet.DataForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class Publisher {

    private AbstractXMPPConnection conn;

    private int sizeOfImageInBytes = 0;

    public Publisher(String username, String password)
            throws InterruptedException, IOException, SmackException, XMPPException {
        conn = Util.connect(username, password);
    }

    private String encodeFileToBase64Binary(File file) {
        String encodedFile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            sizeOfImageInBytes = fileInputStreamReader.read(bytes);
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedFile;
    }

    public void publish(String subject, String content, String imagedir) throws XMPPException.XMPPErrorException, SmackException.NotConnectedException,
            InterruptedException, SmackException.NoResponseException {
        // Create a pubsub manager using an existing XMPPConnection
        PubSubManager pubSubManager = PubSubManager.getInstance(conn);

        ConfigureForm form = new ConfigureForm(DataForm.Type.submit);
        form.setAccessModel(AccessModel.open);          //anyone can access
        form.setDeliverPayloads(true);                 //allow payloads with notifications
        form.setPersistentItems(true);                  //save published items in storage @ server
        form.setPresenceBasedDelivery(false);          //notify subscribers even when they are offline
        form.setPublishModel(PublishModel.publishers);       //only publishers (owner) can post items to this node

        String filepath = imagedir;
        File file = new File(filepath);
        String imageBase64 = encodeFileToBase64Binary(file);

        LeafNode leafNode;
        String msg = content;
        StandardExtensionElement extFileNameBuilder = StandardExtensionElement.builder(
                "file", "jabber:client")
                .addElement("base64Bin", imageBase64)
                .addAttribute("name", file.getName())
                .addAttribute("size", "" + sizeOfImageInBytes)
                .build();

        Message message = new Message();
        message.setStanzaId();
        message.setSubject(subject);
        message.setBody(msg);
        message.addExtension(extFileNameBuilder);

        SimplePayload payload = new SimplePayload(message.toXML("").toString());
        final PayloadItem<SimplePayload> item = new PayloadItem<>("5", payload);
        try {
            leafNode = pubSubManager.getNode("testNode");
            final LeafNode finalLeafNode = leafNode;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Scheduler scheduler = new Scheduler();
                    scheduler.scheduleTask(finalLeafNode, TimeUnit.MINUTES, 1, item);
                }
            });
            thread.start();
        } catch (XMPPException.XMPPErrorException | PubSubException.NotAPubSubNodeException e) {
            leafNode = (LeafNode) pubSubManager.createNode("testNode", form);
        }
    }

    public AbstractXMPPConnection getConn() {
        return conn;
    }

    public static void main(String[] args) {
        Publisher publisher;
        try {
            publisher = new Publisher("admin", "admin");
//            publisher.publish("Hello", "there");
        } catch (InterruptedException | XMPPException | SmackException | IOException e) {
            e.printStackTrace();
        }
    }
}
