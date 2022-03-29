package labs.entityes;

import labs.models.IFuncX;
import labs.models.Point;
import labs.modules.GraphModule;

import java.util.*;

public class LagrangianIntegrationMath {
    static Scanner scanner = new Scanner(System.in);

    public static void solve(IFuncX func1) {
        Map<Double, Double> xy = new HashMap<>();
        while (true) {
            try {
                System.out.println("Выберите готовые данные, или введиет свои:");
                System.out.println("1. Сгенерировать равностоящие узлы");
                System.out.println("2. Узлы Полинома Чебышева");
                if (scanner.hasNext()) {
                    String s = scanner.nextLine();
                    if (s.equals("1")) {
                        while (true) {
                            xy = new HashMap<>();
                            double left, right;
                            System.out.println("Введите границы через запятую");
                            System.out.println("Пример для промежутка от -5 до 5:\n-5,5");
                            System.out.println("Введите данные:");
                            try {
                                if (scanner.hasNext()) {
                                    String[] buffer = scanner.nextLine().split(",");
                                    left = Double.parseDouble(buffer[0]);
                                    right = Double.parseDouble(buffer[1]);
                                    if (right < left) {
                                        Double t = right;
                                        right = left;
                                        left = t;
                                    }
                                    Double sep = right - left;
                                    System.out.println("Введите количество точек:");
                                    Double steps = Double.valueOf(scanner.nextLine());
                                    for (double i = left; i < right; i += sep / steps) {
                                        xy.put(i, func1.solve(i));
                                    }
                                    System.out.println("Полученные данные:");
                                    for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                                        System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
                                    }
                                } else {
                                    System.out.println("Завершершение работы");
                                    System.exit(0);
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Введены неправильные данные");
                            }
                        }
                    } else if (s.equals("2")) {
                        System.out.println("Введите промежуток, через запятую (например: 0,4):");
                        String[] strings = scanner.nextLine().trim().split(",");
                        double a = Double.parseDouble(strings[0]);
                        double b = Double.parseDouble(strings[1]);
                        System.out.println("Пожалуйста введите степень полинома Чебышева");
                        int size = Integer.parseInt(scanner.nextLine());
                        Polynom t0 = new Polynom();
                        t0 = t0.initializet0();
                        Polynom t1 = new Polynom();
                        t1 = t1.initializet1();
                        Vector<Polynom> polynomsVectorFirstKind = PolynomUtil.computeChebyshevSeries(t1, t0, size);
                        List<Double> result = polynomsVectorFirstKind.elementAt(size).calculateRootsFirstKind(a, b);
                        xy = new HashMap<>();
                        for (Double d : result) {
                            xy.put(d, func1.solve(d));
                        }

                        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                            System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
                        }
                    } else {
                        System.out.println("Такого варианта нет");
                        throw new Exception("Такого варианта нет");
                    }
                } else {
                    System.out.println("Завершение работы");
                    System.exit(0);
                }
                break;
            } catch (
                    Exception ignored) {
            }
        }
        if (xy.isEmpty()) {
            System.out.println("Что-то пошло не так. Массивы X и Y отсутствуют.");
        } else {
            Map<String, ArrayList<IFuncX>> map_func = new HashMap<>();
            Map<String, ArrayList<Point>> point_func = new HashMap<>();
            // Добавление функции на график
            ArrayList<IFuncX> funcs = new ArrayList<>();
            funcs.add(func1);
            map_func.put("График функции", funcs);

            // Добавление точек исходных данных на график
            ArrayList<Point> data_points = new ArrayList<>();
            for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                data_points.add(new Point(entry.getKey(), entry.getValue()));
            }
            point_func.put("Точки исходных данных", data_points);

            // Интерполяция
//            System.out.println("Введите координату x искомой точки:");
//            double t = Double.parseDouble(scanner.nextLine());
//            double result = lagranz(xy, t);
//            System.out.println("Ln(" + t + ")=" + result);
//            ArrayList<Point> point = new ArrayList<>();
//            point.add(new Point(t, result));
//            point_func.put("Точка Интерполяции", point);
            // Аппроксимирование
            ArrayList<Point> interpolation = Interpolation(xy, 2);
            point_func.put("Точки Интерполяции", interpolation);
            // Экстрополяция
            ArrayList<Point> extrapolation = Extrapolation(xy);
            point_func.put("Точки вне исходных данных, посчитанные Лагранжем", extrapolation);
            // Рисуем график
            new GraphModule(map_func, point_func);
        }

    }

    private static ArrayList<Point> Interpolation(Map<Double, Double> xy, int steps) {
        ArrayList<Point> points = new ArrayList<>();
        // Сортировка
        ArrayList<Map.Entry<Double, Double>> list = new ArrayList<>(xy.entrySet());
        list.sort(Map.Entry.comparingByKey());
        //
        for (int i = 1; i < list.size(); i++) {
            double first = list.get(i - 1).getKey();
            double second = list.get(i).getKey();
            double step = (second - first) / (steps + 1);
            for (double j = first + step; j + 0.0000001 < second; j += step) {
                points.add(new Point(j, lagranz(xy, j)));
            }
        }
        return points;
    }

    private static ArrayList<Point> Extrapolation(Map<Double, Double> xy) {
        ArrayList<Point> points = new ArrayList<>();
        int steps = 100;
        int xplux = 10;
        // Поиск минимума и макисума
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
            if (entry.getKey() >= max) {
                max = entry.getKey();
            }
            if (entry.getKey() <= min) {
                min = entry.getKey();
            }
        }
        //
        for (double i = max; i < (max + xplux); i += (max + xplux) / steps) {
            points.add(new Point(i, lagranz(xy, i)));
        }
        //
        for (double i = min; i > (min - xplux); i += (min - xplux) / steps) {
            points.add(new Point(i, lagranz(xy, i)));
        }
        return points;
    }

    private static double lagranz(Map<Double, Double> xy, double t) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
            x.add(entry.getKey());
            y.add(entry.getValue());
        }
        return lagranz(x, y, t);
    }

    public static double lagranz(ArrayList<Double> X, ArrayList<Double> Y, double t) {
        double sum, prod;
        int n = X.size();
        sum = 0;
        for (int j = 0; j < n; j++) {
            prod = 1;
            for (int i = 0; i < n; i++) {
                if (i != j) {
                    prod *= (t - X.get(i)) / (X.get(j) - X.get(i));
                }
            }
            sum += Y.get(j) * prod;
        }
        return sum;
    }
}
