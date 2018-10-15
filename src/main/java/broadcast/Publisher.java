package broadcast;

import broadcast.Util;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.xdata.packet.DataForm;

import java.io.IOException;

public class Publisher {
    private AbstractXMPPConnection conn;

    public Publisher(String username, String password)
            throws InterruptedException, IOException, SmackException, XMPPException {
        conn = Util.connect(username, password);
    }

    public void publish(String subject, String content) throws XMPPException.XMPPErrorException, SmackException.NotConnectedException,
            InterruptedException, SmackException.NoResponseException {
        // Create a pubsub manager using an existing XMPPConnection
        PubSubManager pubSubManager = PubSubManager.getInstance(conn);

        ConfigureForm form = new ConfigureForm(DataForm.Type.submit);
        form.setAccessModel(AccessModel.open);          //anyone can access
        form.setDeliverPayloads(true);                 //allow payloads with notifications
        form.setPersistentItems(true);                  //save published items in storage @ server
        form.setPresenceBasedDelivery(false);          //notify subscribers even when they are offline
        form.setPublishModel(PublishModel.publishers);       //only publishers (owner) can post items to this node

        LeafNode leafNode;
        Message message = new Message();
        message.setStanzaId();
        message.setSubject(subject);
        message.setBody(content);

//        String xmlMsg = "<message xmlns='pubsub:test:test'>" + msg + "</message>";
        SimplePayload payload = new SimplePayload(message.toXML("").toString());
        PayloadItem<SimplePayload> item = new PayloadItem<>("5", payload);
        try {
            leafNode = pubSubManager.getNode("testNode");
        } catch (XMPPException.XMPPErrorException | PubSubException.NotAPubSubNodeException e) {
            leafNode = (LeafNode) pubSubManager.createNode("testNode", form);
        }
        leafNode.publish(item);
    }

    public AbstractXMPPConnection getConn() {
        return conn;
    }

    public static void main(String[] args) {
        Publisher publisher;
        try {
            publisher = new Publisher("admin", "admin");
            publisher.publish("Hello", "there General Kenobi");
        } catch (InterruptedException | XMPPException | SmackException | IOException e) {
            e.printStackTrace();
        }
    }
}