import java.util.*;
import java.io.*;

public class Main {
	static int N; // �׷����� ��� ����
	static int M; // �׷����� ���� ����
	static int start; // �������� ����� ��ȣ

	static ArrayList<Node>[] arr; // �� ������ ������踦 ��Ÿ���� �迭
	static int[] distance; // �� �������� �ִ� �Ÿ��� �����ϴ� �迭
	static boolean[] visited; // �� ����� �湮���θ� �����ϴ� �迭

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new ArrayList[N + 1];
		distance = new int[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < N + 1; i++) {
			arr[i] = new ArrayList<>();
		}

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int node01 = Integer.parseInt(st.nextToken());
			int node02 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			arr[node01].add(new Node(node02, weight));
		}

		start = Integer.parseInt(br.readLine());

		dijkstra(start);

		for (int i = 1; i < N + 1; i++)
			bw.write(distance[i] + " ");

		bw.close();
	}

	/** ���ͽ�Ʈ�� �˰��� */
	static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();

		Arrays.fill(distance, Integer.MAX_VALUE);

		distance[start] = 0;
		queue.add(new Node(start, 0));

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			if (visited[current.number] == false)
				visited[current.number] = true;
			else
				continue;

			for (int i = 0; i < arr[current.number].size(); i++) {
				Node next = arr[current.number].get(i);

				if (distance[current.number] + next.weight < distance[next.number]) {
					distance[next.number] = distance[current.number] + next.weight;

					queue.add(new Node(next.number, distance[next.number]));
				}
			}
		}
	}

	/** ��忡 ���� ���� */
	static class Node implements Comparable<Node> {
		int number;
		int weight;

		Node(int number, int weight) {
			this.number = number;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

}
