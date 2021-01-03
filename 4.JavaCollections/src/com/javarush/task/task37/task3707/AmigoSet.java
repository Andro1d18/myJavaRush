package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {

    private static final Object PRESENT  = new Object();
    private transient HashMap<E, Object> map;


//    public static void main(String[] args) {
//        AmigoSet<String> amigoSet = new AmigoSet<>();
//        amigoSet.add("1");
//        amigoSet.add("22");
//        amigoSet.add("333");
//        amigoSet.add("4444");
//        amigoSet.add("55555");
//        amigoSet.add("666666");
//        amigoSet.add("7777777");
//        amigoSet.add("88888888");
//        amigoSet.add("999999999");
//
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream("D:\\javarush\\javaOne.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
//            oos.writeObject(amigoSet);
//            //amigoSet.writeObject(new ObjectOutputStream(fileOutputStream));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        FileInputStream fileInputStream = null;
//        AmigoSet<String> amigoSetSeriazeble = new AmigoSet<>();
//        try{
//            fileInputStream = new FileInputStream("D:\\javarush\\javaOne.txt");
//            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
//            amigoSetSeriazeble = (AmigoSet)ois.readObject();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println(amigoSet.size() + " " + amigoSetSeriazeble.size());
//
//    }
    AmigoSet(){
        map = new HashMap<>();

    }



    AmigoSet(Collection<? extends E> collection){

        this.map = new HashMap<>(Math.max( 16,(int) Math.ceil(collection.size()/.75f)));
        addAll(collection);
    }

    @Override
    public boolean add(Object o) {
        if (!map.containsKey((E)o)) {
            map.put((E) o, PRESENT);
            return true;
        }
        return false;//super.add(o);

    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public Iterator<E> iterator() {

        return map.keySet().iterator();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(map.keySet().size());
        for(E e : map.keySet()) {
            out.writeObject(e);
        }
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap(capacity, loadFactor);
        int size = in.readInt();
        for(int i = 0; i<size; i++) {
            map.put((E)in.readObject(), PRESENT);
        }
    }

    @Override
    public Object clone() throws InternalError {
        try{
            AmigoSet<E> cloneAmigoSet = (AmigoSet) super.clone();
            HashMap<E, Object> cloneMap = (HashMap) map.clone();
            cloneAmigoSet.map = cloneMap;
            return cloneAmigoSet;

        }catch (Exception e){
            throw new InternalError();
        }
        //return null;

    }

    @Override
    public int size() {
        return map.size();
    }
}
