package messageSystem;

import main.Service;

/**
 * Created by User on 26.11.2016.
 */
public class MessageExample extends Message {
    public MessageExample(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void execute(Service service) {
        System.out.println("Example message");
    }
}
