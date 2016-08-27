#include"../head.h"
#include <stack>
#include <algorithm>
void getStarSRLG(Graph *p_graph, char *str) {
	for (int i = 0; i <= 19; i++) {
		int node = int(ceil(i * 0.05 * p_graph->nodeNum));
		int n;
		int edge;
		unsigned int k;
		unsigned int len;
		int id = 0;
		vector<bool> vis = vector<bool>(p_graph->nodeNum, false);

		string st = string(str);
		string s1 = "starsrlg/star_";
		stringstream ss;
		ss << i;
		string s2 = string(ss.str());
		string s3 = ".csv";
		st = st + s1 + s2 + s3;

		//FILE *f = fopen(st.c_str(), "w");
		ofstream out;
		out.open(st.c_str(), ios::out);
		if (!out.is_open()) {
			cout << "Error opening file";
			exit(1);
		}

		int ran;
		vector<int> per = vector<int>(p_graph->nodeNum);
		for (int j = 0; j < p_graph->nodeNum; j++) {
			srand(time(NULL));
			ran = rand() % p_graph->nodeNum;
			while (vis[ran]) {
				ran = (ran + 1) % p_graph->nodeNum;
			}
			vis[ran] = true;
			per[j] = ran;
		}
		vis = vector<bool>(p_graph->nodeNum, false);

		int stklen = 0;
		for (int j = 1; j <= node; j++) {
			n = per[stklen];
			stklen++;
			len = p_graph->topo_Node_fEdgeList.at(n).edgeList.size();
			if (len > 1) {
				out << id;
				id++;
				for (k = 0;
						k < p_graph->topo_Node_fEdgeList.at(n).edgeList.size();
						k++) {
					edge = p_graph->topo_Node_fEdgeList.at(n).edgeList.at(k);
					out << "," << edge;
				}
				out << "\n";
			}

		}
		out.flush();
		out.close();
	}
}

void getNeiStarSRLG(Graph *p_graph, char *str) {
	for (int i = 0; i <= 19; i++) {
		int node = int(ceil(i * 0.05 * p_graph->nodeNum));
		int n;
		int edge;
		unsigned int k;
		unsigned int len;
		int id = 0;
		vector<bool> vis = vector<bool>(p_graph->nodeNum, false);
		string st = string(str);
		string s1 = "neistarsrlg/neistar_";
		stringstream ss;
		ss << i;
		string s2 = string(ss.str());
		string s3 = ".csv";
		st = st + s1 + s2 + s3;

		ofstream out;
		out.open(st.c_str(), ios::out);
		if (!out.is_open()) {
			cout << "Error opening file";
			exit(1);
		}

		int ran;
		vector<int> per = vector<int>(p_graph->nodeNum);
		for (int j = 0; j < p_graph->nodeNum; j++) {
			srand(time(NULL));
			ran = rand() % p_graph->nodeNum;
			while (vis[ran]) {
				ran = (ran + 1) % p_graph->nodeNum;
			}
			vis[ran] = true;
			per[j] = ran;
		}
		vis = vector<bool>(p_graph->nodeNum, false);

		int stklen = 0;
		for (int j = 1; j <= node; j++) {

			if (stklen == p_graph->nodeNum)
				break;

			n = per[stklen];
			stklen++;
//			per.pop_back();
			while (vis[n]) {
				if (stklen == p_graph->nodeNum)
					break;
				n = per[stklen];
//				per.pop_back();
				stklen++;
			}
			if (stklen == p_graph->nodeNum)
				break;
			vis[n] = true;
			len = p_graph->topo_Node_fEdgeList.at(n).edgeList.size();
			int next = -1;
			bool have = true;

			if (len >= 1) {
				out << id;
				id++;
				for (k = 0;
						k < p_graph->topo_Node_fEdgeList.at(n).edgeList.size();
						k++) {
					edge = p_graph->topo_Node_fEdgeList.at(n).edgeList.at(k);
					if (!vis[p_graph->edges.at(edge).to]) {
						if (have) {
							next = p_graph->edges.at(edge).to;
							have = false;
							vis[next] = true;

							for (unsigned int l = 0;
									l
											< p_graph->topo_Node_fEdgeList.at(
													next).edgeList.size();
									l++) {
								int e2 =
										p_graph->topo_Node_fEdgeList.at(next).edgeList.at(
												l);
								out << "," << e2;
							}
						}
					}
					out << "," << edge;
				}
				out << "\n";
			}

		}

		out.flush();
		out.close();
	}
}

void getRandomSRLG(Graph *p_graph, char *str) {
	for (int i = 0; i <= 19; i++) {
		int node = int(ceil(i * 0.05 * p_graph->nodeNum));
		int n1;
		int n2;
		int edge;
		unsigned int k;
		unsigned int len;
		int id = 0;
		vector<bool> vis = vector<bool>(p_graph->nodeNum, false);

		string st = string(str);
		string s1 = "randomsrlg/random_";
		stringstream ss;
		ss << i;
		string s2 = string(ss.str());
		string s3 = ".csv";
		st = st + s1 + s2 + s3;

		ofstream out;
		out.open(st.c_str(), ios::out);
		if (!out.is_open()) {
			cout << "Error opening file";
			exit(1);
		}
		int ran;
		vector<int> per = vector<int>(p_graph->nodeNum);
		for (int j = 0; j < p_graph->nodeNum; j++) {
			srand(time(NULL));
			ran = rand() % p_graph->nodeNum;
			while (vis[ran]) {
				ran = (ran + 1) % p_graph->nodeNum;
			}
			vis[ran] = true;
			per[j] = ran;
		}
		vis = vector<bool>(p_graph->nodeNum, false);
		int stklen = 0;
		for (int j = 1; j <= node; j++) {

			if (stklen == p_graph->nodeNum)
				break;
			n1 = per[stklen];
			stklen++;
			while (vis[n1]) {
				if (stklen == p_graph->nodeNum)
					break;
				n1 = per[stklen];
				//				per.pop_back();
				stklen++;
			}

			if (stklen == p_graph->nodeNum)
				break;
			vis[n1] = true;

			srand(time(NULL));
			n2 = rand() % p_graph->nodeNum;
			if (vis[n2]) {
				n2 = -1;
				if ((p_graph->topo_Node_fEdgeList.at(n1).edgeList.size() <= 1)) {
					continue;
				}
			} else {
				if ((((p_graph->topo_Node_fEdgeList.at(n1).edgeList.size())
						+ (p_graph->topo_Node_fEdgeList.at(n2).edgeList.size()))
						<= 1)) {
					continue;
				}
				vis[n2] = true;
			}

			bool have = false;
			if (-1 != n1) {
				len = p_graph->topo_Node_fEdgeList.at(n1).edgeList.size();
				if (len >= 1) {
					out << id;
					id++;
					for (k = 0;
							k
									< p_graph->topo_Node_fEdgeList.at(n1).edgeList.size();
							k++) {
						edge = p_graph->topo_Node_fEdgeList.at(n1).edgeList.at(
								k);
						out << "," << edge;
						have = true;
					}
				}
			}

			if (-1 != n2) {
				len = p_graph->topo_Node_fEdgeList.at(n2).edgeList.size();
				if (len >= 1) {
					if (-1 == n1
							|| ((p_graph->topo_Node_fEdgeList.at(n1).edgeList.size()
									== 0) && (-1 != n1))) {
						out << id;
						id++;
					}
					for (k = 0;
							k
									< p_graph->topo_Node_fEdgeList.at(n2).edgeList.size();
							k++) {
						edge = p_graph->topo_Node_fEdgeList.at(n2).edgeList.at(
								k);
						out << "," << edge;
						have = true;
					}

				}
			}
			if (have)
				out << "\n";
		}
		out.flush();
		out.close();
	}
}
void getSRLGcsv(Graph *p_graph, char *str) {
	getStarSRLG(p_graph, str);
	getNeiStarSRLG(p_graph, str);
	getRandomSRLG(p_graph, str);

}
