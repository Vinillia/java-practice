import java.util.List;
import java.util.Scanner;
import tasks.*;

public class Main
{
    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            while (true)
            {
                System.out.print("Введите номер задания: ");

                List<Task> tasks = Task.get_tasks_list();
                int num = scanner.nextInt();

                if (num < 0 || num > tasks.size())
                {
                    System.out.println("Выбранного задания не существует " + num);
                    break;
                }

                tasks.get(num - 1).execute(scanner);
                continue;
            }
        }
    }
}