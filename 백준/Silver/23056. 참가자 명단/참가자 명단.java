import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Student implements Comparable<Student> {

		int c;
		String name;

		public Student(int c, String name) {
			this.c = c;
			this.name = name;
		}

		@Override
		public String toString() {
			return c + " " + name;
		}

		@Override
		public int compareTo(Student o) {
			if (c == o.c) {
				if (name.length() == o.name.length()) {
					return name.compareTo(o.name);
				} else {
					return name.length() - o.name.length();					
				}
			} else {
				return c - o.c;
			}
		}
		
				

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Student> blue = new ArrayList<>();
		List<Student> white = new ArrayList<>();
		int[] classcnt = new int[N+1];

		while (true) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			if (c == 0 && name.equals("0")) {
				break;
			}

			if (classcnt[c] < M) {
				if (c % 2 == 1) {
					blue.add(new Student(c, name));
					classcnt[c]++;
				} else {
					white.add(new Student(c, name));
					classcnt[c]++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Collections.sort(blue);
		Collections.sort(white);
		for(Student s : blue) {
			sb.append(s.toString()).append('\n');
		}
		for(Student s : white) {
			sb.append(s.toString()).append('\n');
		}
		
		System.out.println(sb);
				
	}

}