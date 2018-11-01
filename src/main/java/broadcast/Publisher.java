package broadcast;

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

public class Publisher {

    private AbstractXMPPConnection conn;

    private int sizeOfImageInBytes = 0;
    private LeafNode leafNode;
    private PubSubManager pubSubManager;

    public Publisher(String username, String password)
            throws InterruptedException, IOException, SmackException, XMPPException {
        conn = Util.connect(username, password);
        // Create a pubsub manager using an existing XMPPConnection
        pubSubManager = PubSubManager.getInstance(conn);
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

    public void publish(String subject, String content, String imagedir)
            throws XMPPException.XMPPErrorException, SmackException.NotConnectedException,
            InterruptedException, SmackException.NoResponseException {

        final PayloadItem<SimplePayload> item = getPublishPayload(subject, content, imagedir);
        try {
            leafNode = pubSubManager.getNode("testNode");
        } catch (XMPPException.XMPPErrorException | PubSubException.NotAPubSubNodeException e) {
            ConfigureForm form = getConfigureForm();
            leafNode = (LeafNode) pubSubManager.createNode("testNode", form);
        }
        leafNode.publish(item);
    }

    public void schedulePublish(String subject, String content, String imagedir, final long delayInMilliseconds)
            throws SmackException.NotConnectedException, InterruptedException, SmackException.NoResponseException,
            XMPPException.XMPPErrorException {
        try {
            leafNode = pubSubManager.getNode("testNode");
        } catch (XMPPException.XMPPErrorException | PubSubException.NotAPubSubNodeException e) {
            ConfigureForm form = getConfigureForm();
            leafNode = (LeafNode) pubSubManager.createNode("testNode", form);
        }
        final PayloadItem<SimplePayload> item = getPublishPayload(subject, content, imagedir);

        AppExecutors.getInstance().getDiskIO().execute(() -> {
            Scheduler scheduler = new Scheduler();
            scheduler.schedulePublish(leafNode, delayInMilliseconds, item);
        });
    }

    private ConfigureForm getConfigureForm() {
        ConfigureForm form = new ConfigureForm(DataForm.Type.submit);
        form.setAccessModel(AccessModel.open);          //anyone can access
        form.setDeliverPayloads(true);                 //allow payloads with notifications
        form.setPersistentItems(true);                  //save published items in storage @ server
        form.setPresenceBasedDelivery(false);          //notify subscribers even when they are offline
        form.setPublishModel(PublishModel.publishers);       //only publishers (owner) can post items to this node
        return form;
    }

    private PayloadItem<SimplePayload> getPublishPayload(String subject, String content, String imagedir) {
        Message message = new Message();
        message.setStanzaId();
        message.setSubject(subject);
        message.setBody(content);
        if (imagedir != null) {
            File file = new File(imagedir);
            String imageBase64 = encodeFileToBase64Binary(file);

            StandardExtensionElement extFileNameBuilder = StandardExtensionElement.builder(
                    "file", "jabber:client")
                    .addElement("base64Bin", imageBase64)
                    .addAttribute("name", file.getName())
                    .addAttribute("size", "" + sizeOfImageInBytes)
                    .build();
            message.addExtension(extFileNameBuilder);
        }

        SimplePayload payload = new SimplePayload(message.toXML("").toString());
        return new PayloadItem<>("5", payload);
    }

    public AbstractXMPPConnection getConn() {
        return conn;
    }
}
