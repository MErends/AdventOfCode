package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day7 {

    public static Map<String, Node> nodes = new HashMap<>();


	public static void main(String[] args) {
		List<String> lines = FileIO.getFileAsList("2017day7.txt");
		for (String line : lines) {
		    String[] words = line.split("->");
		    String[] nodeInfo = words[0].split(" ");
		    String name = nodeInfo[0];
		    int weight = Integer.parseInt(nodeInfo[1].substring(1, nodeInfo[1].length() - 1));
		    List<String> supports = new ArrayList<>();
		    if (words.length > 1) {
		        String[] supportedNodes = words[1].split(",");
		        for (String supportedNode : supportedNodes) {
		            supports.add(supportedNode.trim());
                }
            }
            nodes.put(name, new Node(name, weight, supports));
        }

        System.out.println(Day7.solve1());

		for (Node node : nodes.values()) {
			if (!node.isBalanced()) {
				System.out.println(node.getBalanceEquation());
			}
		}
	}

	static String solve1() {
        for (Node node : nodes.values()) {
            if (node.getSupportedBy() == null) {
                return node.getName();
            }
        }
        return "-1";
	}
}

class Node {
    String name;
    int weight;
    List<String> supports;
    String supportedBy;
    int totalWeight;
    int level;

    public Node(String name, int weight, List<String> supports) {
        this.name = name;
        this.weight = weight;
        this.supports = supports;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getSupports() {
        return supports;
    }

    public String getSupportedBy() {
        if (supportedBy == null) {
            for (Node node : Day7.nodes.values()) {
                if (node.getSupports().contains(name)) {
                    supportedBy = node.getName();
                    break;
                }
            }
        }
        return supportedBy;
    }

    public int getTotalWeight() {
    	if (totalWeight == 0) {
    		for (String upperNode : supports) {
    			totalWeight += Day7.nodes.get(upperNode).getTotalWeight();
			}
			totalWeight += weight;
		}
        return totalWeight;
    }

	public int getLevel() {
    	if (level == 0) {
    		if (getSupportedBy() == null) {
    			level = 1;
			} else {
    			level = Day7.nodes.get(getSupportedBy()).getLevel() + 1;
			}
		}
		return level;
	}

	public boolean isBalanced() {
    	if (supports.isEmpty()) {
    		return true;
		} else {
    		int supportEachWeight = Day7.nodes.get(supports.get(0)).getTotalWeight();
    		for (String supportsName : supports) {
    			if (Day7.nodes.get(supportsName).getTotalWeight() != supportEachWeight) {
    				return false;
				}
			}
		}
		return true;
	}

	public String getBalanceEquation() {
    	String output = "";
    	for (String supportsName : supports) {
			Node supportNode = Day7.nodes.get(supportsName);
			output += supportNode.getWeight() + " -> " + supportNode.getTotalWeight() + "\n";

		}
		return output;
	}
}
