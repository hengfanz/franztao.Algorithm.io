/**
 * 
 */
package evsnr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Taoheng
 *
 */
public class Result {

	String FileAbsolutePath = EVSNR.FileAbsolutePath;//
	String prefix = "\\newcommand{\\";

	double[] array_AcceptionRatio_evn;
	double[] array_AcceptionRatio_vn;
	int num_AcceptionRatio;

	int[] array_MappingCost_edge;
	int[] array_MappingCost_node;
	int num_MappingCost;
	private int[] array_MappingCost_nodeUsed;

	/**
	 * 
	 */
	public Result() {
		array_AcceptionRatio_evn = new double[(EVSNR.ExperimentPicturePlotNumber + 1) * EVSNR.ExperimentTimes];
		array_AcceptionRatio_vn = new double[(EVSNR.ExperimentPicturePlotNumber + 1) * EVSNR.ExperimentTimes];
		array_MappingCost_edge = new int[(EVSNR.ExperimentPicturePlotNumber + 1) * EVSNR.ExperimentTimes];
		array_MappingCost_node = new int[(EVSNR.ExperimentPicturePlotNumber + 1) * EVSNR.ExperimentTimes];
		array_MappingCost_nodeUsed = new int[(EVSNR.ExperimentPicturePlotNumber + 1) * EVSNR.ExperimentTimes];
		num_AcceptionRatio = 0;
		num_MappingCost = 0;
	}

	void recordTexParameter() {
		try {
			FileWriter TexFileWriter = new FileWriter(FileAbsolutePath + "number.tex");
			TexFileWriter.write(prefix + "addNewNodeCost" + "}{" + EVSNR.addNewNodeCost + "}\n");
			TexFileWriter.write(prefix + "transformExistedNodeCost" + "}{" + EVSNR.transformExistedNodeCost + "}\n");
			TexFileWriter.write(prefix + "addNodeComputaionCost" + "}{" + EVSNR.addNodeComputaionCost + "}\n");
			TexFileWriter.write(prefix + "addEdgeBandwithCost" + "}{" + EVSNR.addEdgeBandwithCost + "}\n");
			TexFileWriter.write(prefix + "RelativeCostbetweenComputingBandwidth" + "}{"
					+ EVSNR.RelativeCostbetweenComputingBandwidth + "}\n");

			TexFileWriter.write(
					prefix + "SubstrateNewtorkRunTimeInterval" + "}{" + EVSNR.SubstrateNewtorkRunTimeInterval + "}\n");
			TexFileWriter.write(prefix + "unitTimeInterval" + "}{" + EVSNR.unitTimeInterval + "}\n");
			TexFileWriter.write(prefix + "requestAppearProbability" + "}{" + EVSNR.requestAppearProbability + "}\n");
			TexFileWriter.write(prefix + "VNRequestsDuration" + "}{" + EVSNR.VNRequestsDuration + "}\n");
			TexFileWriter.write(
					prefix + "VNRequestsContinueTimeMinimum" + "}{" + EVSNR.VNRequestsContinueTimeMinimum + "}\n");
			TexFileWriter.write(
					prefix + "VNRequestsContinueTimeMaximum" + "}{" + EVSNR.VNRequestsContinueTimeMaximum + "}\n");

			TexFileWriter.write(prefix + "SubStrateNodeSize" + "}{" + EVSNR.SubStrateNodeSize + "}\n");
			TexFileWriter.write(
					prefix + "SubStrateNodeComputationMinimum" + "}{" + EVSNR.SubStrateNodeComputationMinimum + "}\n");
			TexFileWriter.write(
					prefix + "SubStrateNodeComputationMaximum" + "}{" + EVSNR.SubStrateNodeComputationMaximum + "}\n");
			TexFileWriter
					.write(prefix + "SubStrateNodenodeProbability" + "}{" + EVSNR.SubStrateNodenodeProbability + "}\n");
			TexFileWriter
					.write(prefix + "SubStrateEdgeBandwithMinimum" + "}{" + EVSNR.SubStrateEdgeBandwithMinimum + "}\n");
			TexFileWriter
					.write(prefix + "SubStrateEdgeBandwithMaximum" + "}{" + EVSNR.SubStrateEdgeBandwithMaximum + "}\n");

			TexFileWriter.write(prefix + "VirtualNodeSizeMinimum" + "}{" + EVSNR.VirtualNodeSizeMinimum + "}\n");
			TexFileWriter.write(prefix + "VirtualNodeSizeMaximum" + "}{" + EVSNR.VirtualNodeSizeMaximum + "}\n");
			TexFileWriter.write(
					prefix + "VirtualNodeComputationMinimum" + "}{" + EVSNR.VirtualNodeComputationMinimum + "}\n");
			TexFileWriter.write(
					prefix + "VirtualNodeComputationMaximum" + "}{" + EVSNR.VirtualNodeComputationMaximum + "}\n");
			TexFileWriter
					.write(prefix + "VirtualNodenodeProbability" + "}{" + EVSNR.VirtualNodenodeProbability + "}\n");
			TexFileWriter
					.write(prefix + "VirtualEdgeBandwithMinimum" + "}{" + EVSNR.VirtualEdgeBandwithMinimum + "}\n");
			TexFileWriter
					.write(prefix + "VirtualEdgeBandwithMaximum" + "}{" + EVSNR.VirtualEdgeBandwithMaximum + "}\n");
			TexFileWriter.write(prefix + "SubStrateFacilityNodeFailDuration" + "}{"
					+ EVSNR.SubStrateFacilityNodeFailDuration + "}\n");

			TexFileWriter.flush();
			TexFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param experimentTimes
	 * @param algorithm
	 * @param time
	 * 
	 */
	public void recordExperimentData(int experimentTimes, Algorithm algorithm, int time) {
		recordExperimentData4AcceptionRatio(experimentTimes, algorithm, time);
		recordExperimentData4MappingCost(experimentTimes, algorithm, time);
		// recordExperimentData4MigrationFrequence(experimentTimes, algorithm,
		// time);
	}

	/**
	 * @param experimentTimes
	 * @param algorithm
	 * @param time
	 */
	private void recordExperimentData4MigrationFrequence(int experimentTimes, Algorithm algorithm, int time) {
		File f_MappingCost_node = new File(
				FileAbsolutePath + "\\Data\\" + "MigrationFrequence_node" + algorithm.algorithmName + ".txt");
		File f_MappingCost_edge = new File(
				FileAbsolutePath + "\\Data\\" + "MigrationFrequence_edge" + algorithm.algorithmName + ".txt");
		FileWriter fw_MigrationFrequence_node;
		FileWriter fw_MigrationFrequence_edge;

		if (!f_MappingCost_node.exists()) {
			try {
				f_MappingCost_node.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!f_MappingCost_edge.exists()) {
			try {
				f_MappingCost_edge.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			if ((time == 0) && (experimentTimes == 0)) {
				fw_MigrationFrequence_node = new FileWriter(f_MappingCost_node);
				fw_MigrationFrequence_edge = new FileWriter(f_MappingCost_edge);
			} else {
				fw_MigrationFrequence_node = new FileWriter(f_MappingCost_node, true);
				fw_MigrationFrequence_edge = new FileWriter(f_MappingCost_edge, true);
			}

			if ((time == 0)) {
				fw_MigrationFrequence_node.write("0\n");
				fw_MigrationFrequence_edge.write("0\n");
			} else {

				int failnode = (int) (Math.random() * (algorithm.getSn().nodeSize - 1));
				int migrate = 0;
				for (int i = 0; i < algorithm.getSn().VNCollection.size(); i++) {
					if (algorithm.getSn().VNCollection.get(i).getIsRunning()) {
						for (int j = 0; j < algorithm.getSn().VNCollection.get(i).nodeSize; j++) {
							if (algorithm.getSn().VNCollection.get(i).vNode2sNode[j] == failnode) {
								migrate++;
							}
						}
					}
				}
				fw_MigrationFrequence_node.write(migrate + "\n");
			}

			fw_MigrationFrequence_node.flush();
			fw_MigrationFrequence_edge.flush();

			fw_MigrationFrequence_node.close();
			fw_MigrationFrequence_edge.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param experimentTimes
	 * @param algorithm
	 */
	private void writeExperimentData4AcceptionRatio(int experimentTimes, Algorithm algorithm) {
		File f_AcceptionRatio_evn = new File(
				FileAbsolutePath + "\\Data\\" + "AcceptionRatio_EVN_" + algorithm.algorithmName + ".txt");
		File f_AcceptionRatio_vn = new File(
				FileAbsolutePath + "\\Data\\" + "AcceptionRatio_VN_" + algorithm.algorithmName + ".txt");
		FileWriter fw_AcceptionRatio_evn;
		FileWriter fw_AcceptionRatio_vn;

		if (!f_AcceptionRatio_evn.exists()) {
			try {
				f_AcceptionRatio_evn.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!f_AcceptionRatio_vn.exists()) {
			try {
				f_AcceptionRatio_vn.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			if (experimentTimes == 0) {
				fw_AcceptionRatio_evn = new FileWriter(f_AcceptionRatio_evn);
				fw_AcceptionRatio_vn = new FileWriter(f_AcceptionRatio_vn);
			} else {
				fw_AcceptionRatio_evn = new FileWriter(f_AcceptionRatio_evn, true);
				fw_AcceptionRatio_vn = new FileWriter(f_AcceptionRatio_vn, true);
			}
			for (int i = 0; i < num_AcceptionRatio; i++) {
				fw_AcceptionRatio_vn.write(array_AcceptionRatio_vn[i] + "\n");
				fw_AcceptionRatio_evn.write(array_AcceptionRatio_evn[i] + "\n");

			}
			num_AcceptionRatio = 0;
			fw_AcceptionRatio_vn.flush();
			fw_AcceptionRatio_evn.flush();

			fw_AcceptionRatio_vn.close();
			fw_AcceptionRatio_evn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param experimentTimes
	 * @param time
	 * @param algorithm
	 * 
	 */
	private void recordExperimentData4AcceptionRatio(int experimentTimes, Algorithm algorithm, int time) {

		if (0 == algorithm.getSn().VNCollection.size()) {
			array_AcceptionRatio_evn[num_AcceptionRatio] = 1.0;
			array_AcceptionRatio_vn[num_AcceptionRatio] = 1.0;

		} else {
			array_AcceptionRatio_evn[num_AcceptionRatio] = (1.0 * algorithm.getSn().evnSuceedMap
					/ algorithm.getSn().vnqNumber);
			array_AcceptionRatio_vn[num_AcceptionRatio] = (1.0 * algorithm.getSn().vnSuceedMap
					/ algorithm.getSn().vnqNumber);

		}
		num_AcceptionRatio++;

	}

	/**
	 * @param experimentTimes
	 * @param algorithms
	 * 
	 */
	public void recordExperimentParameter(int experimentTimes, Vector<Algorithm> algorithms) {
		File f_Parameter = new File(FileAbsolutePath + "\\Data\\" + "Parameter.txt");
		FileWriter fw_Parameter;

		if (!f_Parameter.exists()) {
			try {
				f_Parameter.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (experimentTimes == 0) {
			try {
				fw_Parameter = new FileWriter(f_Parameter);
				fw_Parameter.write(EVSNR.ExperimentTimes + "\n");
				fw_Parameter.write((EVSNR.ExperimentPicturePlotNumber + 1) + "\n");
				fw_Parameter.write(EVSNR.SubstrateNewtorkRunTimeInterval + "\n");
				fw_Parameter.write(algorithms.size() + "\n");
				fw_Parameter.write(EVSNR.RelativeCostbetweenComputingBandwidth + "\n");

				fw_Parameter.write(EVSNR.addNewNodeCost+ "\n");
				fw_Parameter.flush();
				fw_Parameter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @param experimentTimes
	 * @param algorithm
	 * @param time
	 */
	private void recordExperimentData4MappingCost(int experimentTimes, Algorithm algorithm, int time) {

		int nodeCompution = 0;
		int nodeUsed = 0;
		for (int i = 0; i < algorithm.getSn().nodeSize; i++) {
			int compution = (algorithm.getSn().nodeComputationCapacity[i]
					- algorithm.getSn().getSubstrateRemainComputaion4VN(i, algorithm.isShared()));
			if (compution > 0) {
				nodeCompution += compution;
				nodeUsed++;
			} 
				
			
		}
		int edgeBandwith = 0;
		for (int i = 0; i < algorithm.getSn().nodeSize; i++) {
			for (int j = 0; j < i; j++) {
				edgeBandwith += (algorithm.getSn().edgeBandwithCapacity[i][j]
						- algorithm.getSn().getSubStrateRemainBandwith4VN(i, j, algorithm.isShared()));
			}
		}
		array_MappingCost_node[num_MappingCost] = nodeCompution;
		array_MappingCost_edge[num_MappingCost] = edgeBandwith;
		array_MappingCost_nodeUsed[num_MappingCost] = nodeUsed;
		num_MappingCost++;

	}

	/**
	 * @param experimentTimes
	 * @param algorithm
	 */
	public void writeExperimentData4MappingCost(int experimentTimes, Algorithm algorithm) {
		File f_MappingCost_node = new File(
				FileAbsolutePath + "\\Data\\" + "MappingCost_node_" + algorithm.algorithmName + ".txt");
		File f_MappingCost_nodeUsed = new File(
				FileAbsolutePath + "\\Data\\" + "MappingCost_nodeUsed_" + algorithm.algorithmName + ".txt");
		File f_MappingCost_edge = new File(
				FileAbsolutePath + "\\Data\\" + "MappingCost_edge_" + algorithm.algorithmName + ".txt");
		FileWriter fw_MappingCost_node;
		FileWriter fw_MappingCost_edge;
		FileWriter fw_MappingCost_nodeUsed;

		if (!f_MappingCost_node.exists()) {
			try {
				f_MappingCost_node.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!f_MappingCost_nodeUsed.exists()) {
			try {
				f_MappingCost_nodeUsed.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!f_MappingCost_edge.exists()) {
			try {
				f_MappingCost_edge.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			if (experimentTimes == 0) {
				fw_MappingCost_node = new FileWriter(f_MappingCost_node);
				fw_MappingCost_nodeUsed = new FileWriter(f_MappingCost_nodeUsed);

				fw_MappingCost_edge = new FileWriter(f_MappingCost_edge);
			} else {
				fw_MappingCost_node = new FileWriter(f_MappingCost_node, true);
				fw_MappingCost_edge = new FileWriter(f_MappingCost_edge, true);
				fw_MappingCost_nodeUsed = new FileWriter(f_MappingCost_nodeUsed, true);
			}
			for (int i = 0; i < num_MappingCost; i++) {
				fw_MappingCost_edge.write(array_MappingCost_edge[i] + "\n");
				fw_MappingCost_node.write(array_MappingCost_node[i] + "\n");
				fw_MappingCost_nodeUsed.write(array_MappingCost_nodeUsed[i] + "\n");

			}
			num_MappingCost = 0;

			fw_MappingCost_edge.flush();
			fw_MappingCost_node.flush();
			fw_MappingCost_nodeUsed.flush();

			
			fw_MappingCost_edge.close();
			fw_MappingCost_node.close();
			fw_MappingCost_nodeUsed.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param experimentTimes
	 * @param algorithm
	 */
	public void writeExperimentData(int experimentTimes, Algorithm algorithm) {
		writeExperimentData4MappingCost(experimentTimes, algorithm);
		writeExperimentData4AcceptionRatio(experimentTimes, algorithm);
	}

}