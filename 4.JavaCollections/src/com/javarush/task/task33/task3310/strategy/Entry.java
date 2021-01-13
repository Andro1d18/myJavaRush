package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
/*
7.3. Добавь в Entry следующие поля: Long key, String value, Entry next, int hash.
Как видишь, наша реализация будет поддерживать только тип Long для ключа и только String для значения.
Область видимости полей оставь по умолчанию.
7.4. Добавь и реализуй конструктор Entry(int hash, Long key, String value, Entry next).
7.5. Добавь и реализуй методы: Long getKey(), String getValue(), int hashCode(), boolean equals() и String toString().
Реализовывать остальные методы оригинального Entry не нужно, мы пишем упрощенную версию.
 */
public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }
    public Long getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    public int hashCode(){
        return (key   == null ? 0 :   key.hashCode()) ^
                (value == null ? 0 : value.hashCode());
    }

    public boolean equals(Object o){
        if (!(o instanceof Entry))
            return false;

        Long k1 = getKey();
        Long k2 = ((Entry) o).getKey();
        String v1 = getValue();
        String v2 = ((Entry) o).getValue();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return key + "=" + value;
    }
}
