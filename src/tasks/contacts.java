package tasks;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class contacts extends Task
{
    static
    {
        new contacts();
    }

    class super_market
    {
        protected static class item
        {
            private int code;

            final private int price;
            final private int price_retail;

            final private String name;
            final private String vendor;
            final private String country_manufacturer;

            item(String name, String vendor, String country_manufacturer, int price, int price_retail)
            {
                this.name = name;
                this.vendor = vendor;
                this.country_manufacturer = country_manufacturer;
                this.price = price;
                this.price_retail = price_retail;
            }

            protected void set_code(int code)
            {
                this.code = code;
            }

            public int get_code()
            {
                return code;
            }

            public int get_price()
            {
                return price;
            }

            public int get_price_retail()
            {
                return price_retail;
            }

            public String get_name()
            {
                return name;
            }

            public String get_vendor()
            {
                return vendor;
            }

            public String get_country_manufacturer()
            {
                return country_manufacturer;
            }


            @Override
            public String toString()
            {
                return code + ": " + vendor + " - " + name;
            }
        }

        String name;

        int item_code_accumulator;
        List<item> products = new ArrayList<item>();

        super_market(String name)
        {
            this.name = name;
        }

        public item add_product(String name,
                                String vendor,
                                String country_manufacturer,
                                int price,
                                int price_retail)
        {
            item product = new item(name, vendor, country_manufacturer, price, price_retail);
            product.set_code(++item_code_accumulator);
            products.add(product);
            return product;
        }

        public void remove_product(item product)
        {
            products.remove(product);
        }

        public List<item> get_products()
        {
            return new ArrayList<>(products);
        }

        @Override
        public String toString()
        {
            return name;
        }
    }

    @Override
    public void execute(Scanner scanner)
    {
        super_market market = new super_market("Пятёрочка");

        market.add_product("Вода", "ООО Байкал", "Россия", 500, 250);
        market.add_product("Молоко", "АО Простоквашино", "Россия", 1000, 120);
        market.add_product("Хлеб", "Пекарня Каравай", "Россия", 500, 60);
        market.add_product("Шоколад", "Nestle", "Швейцария", 100, 150);
        market.add_product("Яблоки", "Фермер Иванов", "Россия", 1000, 200);
        market.add_product("Сахар", "Руссахар", "Россия", 900, 80);
        market.add_product("Картофель", "АгроФерма", "Беларусь", 2000, 75);
        market.add_product("Рис", "ТайФуд", "Таиланд", 1000, 180);
        market.add_product("Сок апельсиновый", "Добрый", "Россия", 1000, 140);
        market.add_product("Кофе", "Jacobs", "Германия", 250, 320);

        System.out.println("Супер маркет: " + market.toString());

        for (super_market.item product : market.get_products())
        {
            System.out.println(product.get_code() + ". " + product.get_vendor() + " - " + product.get_name() + " - " + product.get_price());
        }
    }

    @Override
    public int get_task_id()
    {
        return 5;
    }
}
