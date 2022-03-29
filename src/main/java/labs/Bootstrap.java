package labs;
import labs.commands.LagrangeInterpolation;
import labs.models.ICommand;
import labs.modules.MenuModule;

import java.util.ArrayList;

public class Bootstrap {
    public static void main(String[] args) {
        ArrayList<ICommand> commands = new ArrayList<>();
        //Lab4
        commands.add(new LagrangeInterpolation());
        //
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
