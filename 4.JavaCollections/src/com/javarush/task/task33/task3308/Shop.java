package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {

    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    @XmlType(name = "goods")
    public static class Goods{
        @XmlAnyElement
        public List<String> names;
    }

//    @Override //для тестирования
//    public String toString(){
//        return "Shop{" +
//                "goods ='" + goods + '\'' +
//                ", count =" + count +
//                ", profit =" + profit +
//                ", secretData =" + secretData.toString() +
//                '}';
//    }
}
