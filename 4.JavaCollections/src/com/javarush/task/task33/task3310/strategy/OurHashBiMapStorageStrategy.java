package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

/*
Как ты заметил, получение идентификатора для строки требует намного больше времени, чем получение строки по идентификатору.
Это ожидаемо и следует из реализации HashMap. Давай напишем четвертую стратегию OurHashBiMapStorageStrategy, которая будет лишена этого недостатка.
11.1. Создай класс OurHashBiMapStorageStrategy, реализующий интерфейс StorageStrategy.
11.2. Добавь в него два поля HashMap<Long, String> k2v и HashMap<String, Long> v2k.
Первое будет хранить соответствие ключа и значения, а второе наоборот: значения и ключа.
11.3. Реализуй методы интерфейса StorageStrategy, обеспечив максимальную скорость.

Подсказка: при добавлении новой пары ключ-значение необходимо добавлять ее сразу в два поля.

Проверь новую стратегию в методе main(). Запусти программу и сравни скорость работы всех 4х стратегий. Убедись, что мы значительно увеличили скорость получения идентификатора. Но как ты понимаешь, у этого решения есть не только плюсы, но и минусы.
Подумай в каких случаях имеет смысл использовать OurHashBiMapStorageStrategy, а в каких HashMapStorageStrategy.


Требования:
1. Класс OurHashBiMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
2. В классе OurHashBiMapStorageStrategy должны быть созданы и инициализированы две HashMap (k2v и v2k).
3. Метод containsKey должен проверять содержится ли полученный параметр в k2v.
4. Метод containsValue должен проверять содержится ли полученный параметр в v2k.
5. Метод put должен добавлять пару (key, value) в k2v и пару (value, key) в v2k.
6. Метод getValue должен возвращать значение полученное из k2v.
7. Метод getKey должен возвращать значение полученное из v2k.
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy {
    HashMap<Long, String> k2v = new HashMap<>();
    HashMap<String, Long> v2k = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {

        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value) {
        k2v.put(key,value);
        v2k.put(value,key);

    }

    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
