package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    LinkedHashSet<Entry<String>> entrySet = new LinkedHashSet<>();
    private int sizeInner = 0;

    public CustomTree() {
        root = new Entry<String>("0");
        entrySet.add(root);
    }

    //@Override ЭТО НЕ НУЖНЫЙ на 07.11. ОСТАВЛЯЮ на прозапас - чтобы видеть как было раньше!
    public boolean addT(String s) {

        Entry<String> newEntry = new Entry<String>(s);
        entrySet.add(newEntry);
        for (Entry<String> e:
            entrySet){
            if(e.availableToAddLeftChildren) {
                e.leftChild = newEntry;
                e.availableToAddLeftChildren = false;
                newEntry.parent = e;
             //   System.out.println("add left children ("+ s +") to " + e.elementName); //проверка. Удалить пред сдачей
                return true;
            }
            else if(e.availableToAddRightChildren){
                e.rightChild = newEntry;
                e.availableToAddRightChildren = false;
                newEntry.parent = e;
              //  System.out.println("add right children ("+ s +") to  " + e.elementName);//проверка. Удалить пред сдачей
                return true;
            }
        }
        return super.add(s);
    }

    //Где то в промежутке между 31.10 и 05.11 закончил здесь
    //Начал создавать addTrue для корректного добавления элементов без привязки к LinkedHashSet<Entry<String>> entrySet
    //06.11 решил, что пускай пока будет через entrySet т.к. работает, а исправлять времени нет. Потом.
    //Нет! Нельзя оставлять на потом, потому что после первого же удаления добавление на основе entrySet может дать сбой.
    //нужно править right now!
    @Override
    public boolean add(String s) {

        Entry<String> newEntry = new Entry<String>(s);

        ArrayList<Entry<String>> arrayList = new ArrayList<>();
        arrayList.add(root);

        Entry<String> parrentEntry = getEntryAvaliableToAddChildren(arrayList);

        if (parrentEntry.availableToAddLeftChildren){
            parrentEntry.leftChild = newEntry;
            parrentEntry.availableToAddLeftChildren = false;
            newEntry.parent = parrentEntry;
            sizeInner++;
         //   System.out.println("add left children ("+ s +") to " + parrentEntry.elementName); //проверка. Удалить пред сдачей
            return true;
        }
        if (parrentEntry.availableToAddRightChildren){
            parrentEntry.rightChild = newEntry;
            parrentEntry.availableToAddRightChildren = false;
            newEntry.parent = parrentEntry;
            sizeInner++;
         //   System.out.println("add right children ("+ s +") to " + parrentEntry.elementName); //проверка. Удалить пред сдачей
            return true;
        }
        return false;
    }

    //Дописал метод, который возвращает Entry в который можно добавить ребенка, но не тестировал
    //Протестируй addTrue чтобы понять рабоатает ли getEntryAvaliableToAddChildren
    //Протестировал - работает корректно!
    //Теперь нужно переписать метод remove - чтобы он не был завязан на entrySet и работал корректно
    private Entry<String> getEntryAvaliableToAddChildren(ArrayList<Entry<String>> list) {

        ArrayList<Entry<String>> newList = new ArrayList<>();
        for (Entry<String> entry :
                list){

            if (entry != null && entry.isAvailableToAddChildren())
                return entry;
            else {
                if (entry != null && entry.leftChild !=null) {
                    newList.add(entry.leftChild);
                }
                if (entry != null && entry.rightChild != null){
                    newList.add(entry.rightChild);
                }
            }
            //Если в итоге у всех элементов list нет детей при этом нельзя их добавить (все были ранее удалены), то
            //обнуляем флаг невозможности добавки детей у всех элементов list (согласно условию задачи) и возвращаем
            //первый элемент
            if (newList.isEmpty()){
                for (Entry<String> e :
                        list) {
                    e.availableToAddLeftChildren = true;
                    e.availableToAddRightChildren = true;
                }
                return list.get(0);

            }
        }
        return getEntryAvaliableToAddChildren(newList);
    }

    //на 07.11 остановился тут - нужно переписать метод remove - чтобы он не был завязан на entrySet и работал корректно
    @Override
    public boolean remove(Object o) {

        if (!(o instanceof String)){
            throw new UnsupportedOperationException();
        }
        boolean result = false;
        String s = (String) o;
        
        Entry<String> neededEntry = findEntryByName(root, s);
        
        if (neededEntry != null){
            result = true;
            if (neededEntry.parent.leftChild==neededEntry)
                neededEntry.parent.leftChild=null;
            if (neededEntry.parent.rightChild==neededEntry)
                neededEntry.parent.rightChild=null;
        }
        return result; //super.remove(o)
    }

//    private void removeAllChildren(Entry<String> entry){ //07.11 пришёл к выводу что всех детей можно не удалять,
//        // достаточно стереть ссылку у родителя
//    }
    //Итератором пользоваться не вариант, т.к. он обращается к метуду get(int index) а он возвращает UnsupportedOperationException
//        Iterator<String> itr = this.iterator();
//        while (itr.hasNext()){
//            System.out.println(itr.next());
//        }

    @Override
    public int size() {
        sizeInner = 0; //сбрасываем счетчик перед начала подсчета
        ArrayList<Entry<String>> arrayList = new ArrayList<>();
        arrayList.add(root);
        countEntry(arrayList);
        return sizeInner-1; //-1 потому что root элемент не нужно учитывать!!!
    }

    //внутренний метод подсчета количества энтри основанный на рекурсии
    private void countEntry(ArrayList<Entry<String>> list) {
        ArrayList<Entry<String>> newList = new ArrayList<>(); //создаём ньюлист куда будет записывать детей
        for (Entry<String> entry : //идём по листу, который получили в параметрах метода, и считаем каждый элемент путем sizeInner++;
                list) {

            if (entry != null ){
                sizeInner++;
                //если у элемента есть дети, записываем их в ньюлист
                if (entry.leftChild != null) {
                    newList.add(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    newList.add(entry.rightChild);
                }
            }
        }
        //если ньюлист не пустой (то есть были дети у каких то элементов list),
        // входим в рекурсию тем самым size учитывает количество всех детей
        if (!(newList.isEmpty()))
            countEntry(newList);
    }


    //22.10 сделал поиск парента, но не проверил, так как добавление элементов еще не готово :)
    public String getParent(String s){
        //если в параметрах пришло имя root-элемента, у которого нет родителя, возвращаем его же (root)
        if (s.equals(root.elementName)){
            return null;
        }

        //Ищем нужный нам Entry через метод findEntryByName, подкидываем root элемент и искомое имя эллемента
        Entry<String> neededEnrty = findEntryByName(root, s);

        //когда нашли нужный элемент, возвращем имя его родителя
        return neededEnrty != null? neededEnrty.parent.elementName : null;
    }

    //рекурсивный поиск Entry по имени
    private Entry<String> findEntryByName (Entry<String> entry, String nameEntry){

        Entry<String> neededEntry = null;

        //если имя переданного Enrty совпадает с искомым, возвращаем Enrty
        if (entry.elementName.equals(nameEntry)){
            return entry;
        }

        //если левый потомок существует, используем метод findEntryByName с указанием этого потомка
        if(entry.leftChild != null)
            neededEntry = findEntryByName(entry.leftChild, nameEntry);

        //если прошли по левой ветке и искомое Entry всё ещё null (то есть не нашли нужного), то идём по правому потомку,
        //если правый потомок существует
        if (neededEntry == null) {
            if (entry.rightChild != null)
                neededEntry = findEntryByName(entry.rightChild, nameEntry);
        }
        return neededEntry;
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index){ throw new UnsupportedOperationException(); }

    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren ||availableToAddRightChildren;
        }
    }

}
