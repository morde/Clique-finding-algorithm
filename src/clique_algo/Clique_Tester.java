package clique_algo;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;

public class Clique_Tester {
	public static int minQ = 6, maxQ=10;
	public static double TH = 0.75;
	public static String in_file = "test1.csv";
	public static String out_file = null;
	public static boolean Debug = true;
	public static int MAX_CLIQUE = 100000;
	public static boolean Convert = true;
	public static String[] default_args = {"data/small_graph_100.csv", "0.75","5","8"};
	
	public static void main(String[] args) {  // test1.csv_DG.txt  0.8 5 7
		if(args==null || args.length<3) {
			help();
			args = default_args;
		}
		parse(args);
		long t0= new Date().getTime();
		Graph G = new Graph(in_file, TH);
		long t1= new Date().getTime();
		System.out.println("Init Graph: "+(t1-t0)+"  ms");	
		//Vector<VertexSet> c1 = G.All_Cliques(maxQ);	
		//	Vector<VertexSet> c2 = G.All_Cliques_DFS(2,maxQ);
		long t2= new Date().getTime();
		if(out_file==null)  out_file = in_file+"_"+TH+"_"+minQ+"_"+maxQ+".csv";
		G.All_Cliques_DFS(out_file,minQ,maxQ);
		long t3= new Date().getTime();
		System.out.println("Alg3: "+(t3-t2)+"  ms");
	}
	static void help() {
		System.out.println("Wrong Parameters! should use: java -jar All_Cliques <input file> <round value> <min clique> <max clique> <output file> <max_cliques> <Graph convert flag>");
		System.out.println("Wrong Parameters! should use: java -jar All_Cliques test1.csv 0.7 5 7 test1_out.txt 10000 true");
	}
	static void parse(String[] a){
		try {
			in_file = a[0];
			TH = new Double(a[1]);
			minQ=new Integer(a[2]);
			maxQ=new Integer(a[3]);
			if(a.length>4 ) out_file = a[4];
			if(a.length>5) MAX_CLIQUE = new Integer(a[5]);
			if(a.length>6) Convert = new Boolean(a[6]);}
		catch(Exception e) {
			e.printStackTrace();
			help();
		}
	}
}