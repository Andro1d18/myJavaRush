package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

/*
3.4. Реализуй метод getId, он должен:
3.4.1. Проверить есть ли переданное значение в хранилище, если есть - вернуть его ключ.
3.4.2. Если преданного значения нет в хранилище, то:
3.4.2.1. Увеличить значение lastId на единицу;
3.4.2.2. Добавить в хранилище новую пару ключ-значение (новое значение lastId и переданную строку);
3.4.2.3. Вернуть новое значение lastId.
3.5. Реализуй метод getString, он должен вернуть строку по заданному идентификатору (ключу).
3.6. Предусмотреть возможность вызова методов getId и getString из разных потоков добавив соответствующий модификатор к заголовкам методов.
 */

public class Shortener {

    private Long lastId = new Long(0L);

    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy){

        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string){
        if (storageStrategy.containsValue(string))
            return storageStrategy.getKey(string);
        lastId++;
        storageStrategy.put(lastId, string);
        return lastId;
    }

    public synchronized String getString(Long id){
//        if (storageStrategy.containsKey(id))
//            return storageStrategy.getValue(id);
        return storageStrategy.getValue(id);
    }
}
