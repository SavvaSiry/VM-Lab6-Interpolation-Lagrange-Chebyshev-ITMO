package labs.commands;

import labs.entityes.IndividualFunc;
import labs.entityes.LagrangianIntegrationMath;
import labs.models.ICommand;
import labs.models.IFuncX;

import java.util.*;

public class LagrangeInterpolation implements ICommand {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String getMessage() {
        return "Интерполирование многочленом Лагранжа";
    }


    @Override
    public void execute() {
        System.out.println("Интерполирование многочленом Лагранжа:");
        Map<String, IFuncX> funcs = new HashMap<>();
        // 1
        funcs.put("sin(x)", Math::sin);
        // 2
        funcs.put("2x", x -> 2 * x);
        //3
        int i = 1;
        ArrayList<String> keys = new ArrayList<>();
        for (Map.Entry<String, IFuncX> entry : funcs.entrySet()) {
            System.out.println((i++) + ". " + entry.getKey());
            keys.add(entry.getKey());
        }
        System.out.println((funcs.size() + 1) + ". Ввести своё уравнение");
        String str = scanner.nextLine();
        try {
            if (Objects.equals(str, "3")) {
                IndividualFunc f = new IndividualFunc();
                f.execute();
                LagrangianIntegrationMath.solve(f);
            } else {
                IFuncX func1 = funcs.get(keys.get(Integer.parseInt(str) - 1));
                LagrangianIntegrationMath.solve(func1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
