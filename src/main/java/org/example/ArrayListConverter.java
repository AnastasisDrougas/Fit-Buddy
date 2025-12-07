package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.function.Function;

public class ArrayListConverter<E>{
    private ArrayList<E> list = new ArrayList<>();


    public ArrayListConverter(NodeList nodes, Function<Node, E> mapper) {
        converter(nodes, mapper);
    }

    private void converter(NodeList nodes, Function<Node, E> mapper){
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            E field = mapper.apply(node);
            list.add(field);
        }
    }

    public ArrayList<E> getList() {
        return list;
    }
}
