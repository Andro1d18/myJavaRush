package com.javarush.task.task33.task3310.strategy;
/*
Добавь и реализуй класс OurHashMapStorageStrategy, используя класс Entry из предыдущей подзадачи.
Класс OurHashMapStorageStrategy должен реализовывать интерфейс StorageStrategy.
8.1. Добавь в класс следующие поля:
8.1.1. static final int DEFAULT_INITIAL_CAPACITY = 16;
8.1.2. static final float DEFAULT_LOAD_FACTOR = 0.75f;
8.1.3. Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
8.1.4. int size;
8.1.5. int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
8.1.6. float loadFactor = DEFAULT_LOAD_FACTOR;
8.2. Реализуй в классе следующие вспомогательные методы:
8.2.1. int hash(Long k) +
8.2.2. int indexFor(int hash, int length) +
8.2.3. Entry getEntry(Long key) +
8.2.4. void resize(int newCapacity) +
8.2.5. void transfer(Entry[] newTable) +
8.2.6. void addEntry(int hash, Long key, String value, int bucketIndex)
8.2.7. void createEntry(int hash, Long key, String value, int bucketIndex)
8.3. Добавь в класс публичные методы, которые требует интерфейс StorageStrategy.
Какие-либо дополнительные поля класса не использовать. Методы, не описанные в задании, реализовывать не нужно.
Если возникнут вопросы как реализовать какой-то метод или что он должен делать, то ты всегда можешь посмотреть, как работает похожий метод в HashMap.
Можешь добавить в метод main класса Solution тестирование новой стратегии. Запусти и сравни время работы двух стратегий на одинаковом количестве элементов.


Требования:
1. Класс OurHashMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
2. В классе OurHashMapStorageStrategy должны быть созданы все необходимые поля (согласно условию задачи).
3. Методы интерфейса StorageStrategy должны быть реализованы в OurHashMapStorageStrategy таким образом,
чтобы обеспечивать корректную работу Shortener созданного на его основе.
4. В классе OurHashMapStorageStrategy должны присутствовать все вспомогательные методы перечисленные в условии задачи.
 */
public class OurHashMapStorageStrategy implements StorageStrategy{

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    int hash(Long k){
//        int h;
//        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
        return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length-1);
    }

    Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == 1 << 30) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];

        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {

        if (value == null)
            return false;
        for (Entry table :
                table) {
            for(Entry e = table; e!= null; e = e.next)
            if (e.value.equals(value))
                return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key),table.length));
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return 0L;
        for(Entry table : table){
            for(Entry e = table; e != null; e = e.next){
                if (e.value.equals(value))
                    return e.getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {

        return getEntry(key) == null? null: getEntry(key).getValue();
    }
}
