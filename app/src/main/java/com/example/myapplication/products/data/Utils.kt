package com.example.myapplication.products.data

val testData =
    """
    INSERT INTO item VALUES(1,'iPhone 13',1633046400000,'["Телефон", "Новый", "Распродажа"]',15);
    INSERT INTO item VALUES(2,'Samsung Galaxy S21',1633132800000,'["Телефон", "Хит"]',30);
    INSERT INTO item VALUES(3,'PlayStation 5',1633219200000,'["Игровая приставка", "Акция", "Распродажа"]',7);
    INSERT INTO item VALUES(4,'LG OLED TV',1633305600000,'["Телевизор", "Эксклюзив", "Ограниченный"]',22);
    INSERT INTO item VALUES(5,'Apple Watch Series 7',1633392000000,'["Часы", "Новый", "Рекомендуем"]',0);
    INSERT INTO item VALUES(6,'Xiaomi Mi 11',1633478400000,'["Телефон", "Скидка", "Распродажа"]',5);
    INSERT INTO item VALUES(7,'MacBook Air M1',1633564800000,'["Ноутбук", "Тренд"]',12);
    INSERT INTO item VALUES(8,'Amazon Kindle Paperwhite',1633651200000,'["Электронная книга", "Последний шанс", "Ограниченный"]',18);
    INSERT INTO item VALUES(9,'Fitbit Charge 5',1633737600000,'[]',27);
    INSERT INTO item VALUES(10,'GoPro Hero 10',1633824000000,'["Камера", "Эксклюзив"]',25);
    """
        .trimIndent()
        .split("\n")
        .map { it
            .substringAfter("INSERT INTO item VALUES(")
            .substringBeforeLast(");")
            .split(regex = Regex("""('?,(?!\s)'?)"""))
            .run {
                Product(
                    id = get(0).toInt(),
                    name = get(1),
                    time = get(2).toLong().let { millis -> LocalDateTimeConverter().toDateTime(millis) },
                    tags = get(3).let { string -> TagsConverter().toTags(string)  },
                    amount = get(4).toInt()
                )
            }
        }