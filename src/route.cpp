#include "route.h"
#include<iostream>
#include "lib/lib_record.h"
#include "lib/lib_time.h"
#include "head.h"

Graph *p_graph;
DisjointPaths *AlgorithmResult;
pthread_mutex_t mutex_result;
pthread_mutex_t mutex_thread;
void print_answer(DisjointPaths *FranzAlgorithmResult) {

	cout << endl
			<< "**************Successful to find disjoint path**************"
			<< endl;
	cout << "APcost:" << FranzAlgorithmResult->APcostsum << "  " << endl;
	cout << "BPcost:" << FranzAlgorithmResult->BPcostsum << "  " << endl;

	cout << "AP edge(" << (FranzAlgorithmResult->APhop - 1) << "): ";
	unsigned int i = 0;
	for (i = 0; i < FranzAlgorithmResult->APedge.size(); i++) {

		record_result(WORK_PATH, FranzAlgorithmResult->APedge.at(i));
		cout << FranzAlgorithmResult->APedge.at(i) << "   ";
	}
	cout << endl;

	cout << "AP node(" << (FranzAlgorithmResult->APhop) << "): ";
	for (i = 0; i < FranzAlgorithmResult->APedge.size(); i++) {

		cout
				<< p_graph->nid_nindex[p_graph->edges.at(
						FranzAlgorithmResult->APedge.at(i)).from] << "   ";

	}
	cout
			<< p_graph->nid_nindex[p_graph->edges.at(
					FranzAlgorithmResult->APedge.at(i - 1)).to] << "   ";
	cout << endl;

	cout << "BP edge(" << (FranzAlgorithmResult->BPhop - 1) << "): ";
	for (i = 0; i <= (FranzAlgorithmResult->BPedge.size() - 1); i++) {
		record_result(BACK_PATH, FranzAlgorithmResult->BPedge.at(i));
		cout << FranzAlgorithmResult->BPedge.at(i) << "   ";
	}
	cout << endl;
	cout << "BP node(" << (FranzAlgorithmResult->BPhop) << "): ";
	for (i = 0; i <= (FranzAlgorithmResult->BPedge.size() - 1); i++) {
		cout
				<< p_graph->nid_nindex[p_graph->edges.at(
						FranzAlgorithmResult->BPedge.at(i)).from] << "   ";
	}

	cout
			<< p_graph->nid_nindex[p_graph->edges.at(
					FranzAlgorithmResult->BPedge.at(i - 1)).to] << "   ";
	cout << endl << endl;

}
//verify whether the answer is really right.
void verify_result(DisjointPaths *FranzAlgorithmResult) {

}
void search_route(char *topo[MAX_EDGE_NUM], int edge_num,
		char *demand[MAX_DEMAND_NUM], int demand_num, char *srlg[MAX_SRLG_NUM],
		int srlg_num, int algorithm, char *str) {

	AlgorithmResult = new DisjointPaths();
	p_graph = new Graph(edge_num);

	if (!LoadData(p_graph, topo, edge_num, demand, demand_num, srlg,
			srlg_num)) {
		printf("Error:LoadData\n");
		return;
	}

	DebugPrint(p_graph);
	if ((algorithm_IHKSP == algorithm) || (algorithm_all == algorithm)) {
		AlgorithmResult->clear();
		cout << endl << "--------------------------------------------" << endl;
		print_time("IHKSPbegin\n");
		if (KSPAlgorithmBasicFlows(p_graph)) { //ILPAlgorithmBasicFlows ILPAlgorithm_glpk
			print_answer(AlgorithmResult);
		} else {
			cout << "||||||||Failure to find disjoint path||||||||" << endl;
		}
		print_time("IHKSPEnd");
		cout << "--------------------------------------------" << endl << endl;
	}

	if ((algorithm_franz == algorithm) || (algorithm_all == algorithm)) {
		AlgorithmResult->clear();
		cout << endl << "--------------------------------------------" << endl;
		print_time("FranzAlgorithmBegin\n");
		if (FranzAlgorithmBasicFlows(p_graph)) {
			print_answer(AlgorithmResult);
		} else {
			cout << "||||||||Failure to find disjoint path||||||||" << endl;
		}
		print_time("FranzAlgorithmEnd");
		cout << "--------------------------------------------" << endl << endl;
	}

	if ((algorithm_ILP == algorithm) || (algorithm_all == algorithm)) {
		AlgorithmResult->clear();
		cout << endl << "--------------------------------------------" << endl;
		print_time("ILPBegin\n");
		if (ILPAlgorithm_gurobi(p_graph)) { // ILPAlgorithmBasicFlows   ILPAlgorithmBasicFlows_LocalSolver
			print_answer(AlgorithmResult);
		} else {
			cout << "||||||||Failure to find disjoint path||||||||" << endl;

		}
		print_time("ILPEnd");
		cout << "--------------------------------------------" << endl << endl;
	}

	if ((algorithm_IQP == algorithm) || (algorithm_all == algorithm)) {
		AlgorithmResult->clear();
		cout << endl << "--------------------------------------------" << endl;
		print_time("IQPBegin\n");
		if (IQPAlgorithm_gurobi(p_graph)) { // ILPAlgorithmBasicFlows   ILPAlgorithmBasicFlows_LocalSolver
			print_answer(AlgorithmResult);
		} else {
			cout << "||||||||Failure to find disjoint path||||||||" << endl;

		}
		print_time("IQPEnd");
		cout << "--------------------------------------------" << endl << endl;
	}

	if (algorithm_getSRLGcsv == algorithm) {
		char abusolutepath[100];
		int i;
		int len = strlen(str);
		for (i = 0; i < (len - 8); i++) {
			abusolutepath[i] = str[i];
		}
		abusolutepath[i] = '\0';
		getSRLGcsv(p_graph, abusolutepath);
	}

	verify_result(AlgorithmResult);
	free(p_graph);
	return;
}

