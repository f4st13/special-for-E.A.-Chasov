import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int INF = Integer.MAX_VALUE / 10;

        int[] citiesPrices = new int[n];
        for (int i = 0; i < n; i++) {
            citiesPrices[i] = in.nextInt();
        }

        int[][] graph = new int[n][n];
        boolean[] vis = new boolean[n];
        int[] dist = new int[n];

        Arrays.fill(dist, INF);
        Arrays.fill(vis, false);

        dist[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = -1;
                }
            }
        }

        int rn = in.nextInt();
        int first, second;

        for (int i = 0; i < rn; i++) {
            first = in.nextInt() - 1;
            second = in.nextInt() - 1;

            graph[first][second] = citiesPrices[first];
            graph[second][first] = citiesPrices[second];
        }

        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < n; nv++) // перебираем вершины
                if (!vis[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                    v = nv;
            if (v == -1) break; // ближайшая вершина не найдена
            vis[v] = true; // помечаем ее
            for (int nv = 0; nv < n; nv++)
                if (!vis[nv] && graph[v][nv] < INF && graph[v][nv] > -1) // для всех непомеченных смежных
                    dist[nv] = Integer.min(dist[nv], dist[v] + graph[v][nv]); // улучшаем оценку расстояния (релаксация)
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }

        System.out.println(dist[n - 1]);

    }
}
