package com.company;

import java.io.IOException;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        String filename = "data.txt";

        Graph graph = Graph.load(filename, 5);

        Stack<State> states = new Stack<>();

        State state =new State(null, 3, true);
        states.push(state);
        graph.enter(state.getCityNumb());

        State shortest = null;

        state = states.pop();

        while (!state.isStartPoint() || !(state.getNextIndex() >= graph.getCount())) {
            int index = state.getNextIndex();
            state.setNextIndex(index+1);

            if (index >= graph.getCount()) {
                graph.leave(state.getCityNumb());
                state = states.pop();
            } else if (graph.hasEdge(state.getCityNumb(), index) && graph.enter(index)) {
                states.push(state);
                state = new State(state, index);
            }

            if (graph.allVisited()) {
                if (shortest == null) {
                    shortest = state;

                } else {
                    if (shortest.calculateLength(graph) > state.calculateLength(graph)) {
                        shortest = state;
                    }
                }
            }
        }

        System.out.println(shortest);
        System.out.println(shortest.calculateLength(graph));
    }

}
