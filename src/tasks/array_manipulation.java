package tasks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class array_manipulation extends Task
{
    static
    {
        new array_manipulation();
    }

    @Override
    public void execute(Scanner scanner)
    {
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        int count = random.nextInt(50) + 50;
        for (int i = 0; i < count; i++)
        {
            numbers.add(random.nextInt(100));
        }

        for (int i = 0; i < count; i++)
        {
            int num = numbers.get(i);

            if (result.contains(num))
                continue;

            result.add(num);
        }

        System.out.println(numbers);
        System.out.println(result);
    }

    @Override
    public int get_task_id()
    {
        return 3;
    }
}
