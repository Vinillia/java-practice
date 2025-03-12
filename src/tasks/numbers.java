package tasks;
import java.util.Scanner;

public class numbers extends Task
{
    static
    {
        new numbers();
    }

    @Override
    public void execute(Scanner scanner)
    {
        System.out.println("Введите 2 числа: ");

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int result = a * 5;

        if (a * b > 100)
            result = (int)(3.0 * Math.tan((double)b));

        System.out.println(result);
    }

    @Override
    public int get_task_id()
    {
        return 1;
    }
}
