package java01.basic.datastructure.improve.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yudazhi
 */
public class Graph {
    private int v;
    private LinkedList<Integer> adj[];
    boolean found = false;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        //  init queue and flag
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            //  找到当前节点的位置
            int w = queue.poll();
            //  遍历节点中边，若未遍历过，将当前节点值标记为遍历值的前一个点
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    //  发现此点，打印信息
                    if (q == t) {
                        printRoute(prev, s, t);
                        return;
                    }
                    //  未发现，将此点标记为访问过，并将此点加入到队列中
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        printRoute(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found) {
            return;
        }
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        LinkedList<Integer> cur = adj[w];
        for (int i = 0; i < cur.size(); i++) {
            int q = cur.get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    private void printRoute(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            printRoute(prev, s, prev[t]);
        }
        System.out.println(t + "-->");
    }
}
