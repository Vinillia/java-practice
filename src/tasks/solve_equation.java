package tasks;

import java.util.Scanner;

public class solve_equation extends Task
{
    static
    {
        new solve_equation();
    }

    @Override
    public void execute(Scanner scanner)
    {
        System.out.println("Введите 2 числа");

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        double max = (double)Math.max(a, b);
        double min = (double)Math.min(a, b);

        if (min == 0)
        {
            System.out.println("Ошибка: деление на 0");
            return;
        }

        double numerator = max - 2.0 * Math.sqrt(min) + 4.2 * min;
        double denominator = 1 + max / min;

        System.out.println(numerator / denominator);
    }

    @Override
    public int get_task_id()
    {
        return 2;
    }
}
