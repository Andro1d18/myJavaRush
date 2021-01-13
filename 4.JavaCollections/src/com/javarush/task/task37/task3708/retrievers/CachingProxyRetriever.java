package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/*
Реализация паттерна Proxy предоставляет объект, который контролирует доступ к другому объекту.

Тебе необходимо создать класс CachingProxyRetriever в пакете retrievers,
который будет обеспечивать ту же функциональность что и класс OriginalRetriever,
но с одним важным дополнением. Необходимо всегда пробовать сначала получить объект из кеша, и только если его там нет -
выполнять дорогостоящую операцию загрузки из storage. После получения необходимого объекта из хранилища storage,
его необходимо кешировать с помощью объекта типа LRUCache.

Для клиента (в данном случае класс Solution) использование CachingProxyRetriever ничем не должно отличаться
от использования класса OriginalRetriever.

P.S. Тебе понадобятся поля типов LRUCache и OriginalRetriever в классе CachingProxyRetriever.


 */
public class CachingProxyRetriever implements Retriever{
    //Storage storage;
    LRUCache lruCache;
    OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage){
        //this.storage = storage;
        lruCache = new LRUCache(16);
        originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object result = lruCache.find(id);
        if (result != null){
            return result;
        }else {
            result = originalRetriever.retrieve(id);
            lruCache.set(id , result);
        }
        return result;
    }
}
