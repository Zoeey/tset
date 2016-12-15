package messageSystem.messages;

import main.Service;
import mechanics.Mechanics;
import messageSystem.Address;
import messageSystem.Message;
import model.gameInfo.Player;

/**
 * Created by User on 28.11.2016.
 */
public class MoveMsg extends Message {
    private Player player;
    private float dx, dy;

    public MoveMsg(Address from, Address to, Player player, float dx, float dy) {
        super(from, to);
        this.player = player;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute(Service service) {
        ((Mechanics)service).move(player,dx,dy);
    }
}
