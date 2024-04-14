package Introduction_to_Algorithms.Part5.vEB_Tree;

import java.util.*;

class vEBTree {
    private int universeSize;
    private int min, max;
    private vEBTree summary;
    private Map<Integer, vEBTree> cluster;

    vEBTree(int universeSize) {
        this.universeSize = universeSize;
        this.min = -1;
        this.max = -1;

        if (universeSize > 2) {
            int upper = upperSquareRoot(universeSize);
            int lower = lowerSquareRoot(universeSize);
            summary = new vEBTree(upper);
            cluster = new HashMap<>();
            for (int i = 0; i < upper; i++) {
                cluster.put(i, new vEBTree(lower));
            }
        }
    }

    private int upperSquareRoot(int x) {
        return (int) Math.ceil(Math.sqrt(x));
    }

    private int lowerSquareRoot(int x) {
        return (int) Math.floor(Math.sqrt(x));
    }

    private int index(int x, int y) {
        return x * lowerSquareRoot(universeSize) + y;
    }

    public void insert(int x) {
        if (min == -1) {
            min = x;
            max = x;
        } else {
            if (x < min) {
                int temp = min;
                min = x;
                x = temp;
            }
            if (universeSize > 2) {
                int clusterIdx = x / lowerSquareRoot(universeSize);
                int offset = x % lowerSquareRoot(universeSize);
                if (cluster.get(clusterIdx).min == -1) {
                    summary.insert(clusterIdx);
                    cluster.get(clusterIdx).min = offset;
                    cluster.get(clusterIdx).max = offset;
                } else {
                    cluster.get(clusterIdx).insert(offset);
                }
            }
            if (x > max) {
                max = x;
            }
        }
    }

    public boolean contains(int x) {
        if (x == min || x == max) {
            return true;
        } else if (universeSize <= 2) {
            return false;
        } else {
            int clusterIdx = x / lowerSquareRoot(universeSize);
            int offset = x % lowerSquareRoot(universeSize);
            return cluster.containsKey(clusterIdx) && cluster.get(clusterIdx).contains(offset);
        }
    }

    public int successor(int x) {
        if (universeSize <= 2) {
            if (x == 0 && max == 1) return 1;
            else return -1;
        } else if (min != -1 && x < min) {
            return min;
        } else {
            int clusterIdx = x / lowerSquareRoot(universeSize);
            int offset = x % lowerSquareRoot(universeSize);
            int maxLow = cluster.get(clusterIdx).max;
            if (maxLow != -1 && offset < maxLow) {
                int succCluster = clusterIdx;
                int succOffset = cluster.get(clusterIdx).successor(offset);
                return index(succCluster, succOffset);
            } else {
                int succCluster = summary.successor(clusterIdx);
                if (succCluster == -1) return -1;
                int succOffset = cluster.get(succCluster).min;
                return index(succCluster, succOffset);
            }
        }
    }

    public int predecessor(int x) {
        if (universeSize <= 2) {
            if (x == 1 && min == 0) return 0;
            else return -1;
        } else if (max != -1 && x > max) {
            return max;
        } else {
            int clusterIdx = x / lowerSquareRoot(universeSize);
            int offset = x % lowerSquareRoot(universeSize);
            int minLow = cluster.get(clusterIdx).min;
            if (minLow != -1 && offset > minLow) {
                int predCluster = clusterIdx;
                int predOffset = cluster.get(clusterIdx).predecessor(offset);
                return index(predCluster, predOffset);
            } else {
                int predCluster = summary.predecessor(clusterIdx);
                if (predCluster == -1) {
                    if (min != -1 && x > min) return min;
                    else return -1;
                }
                int predOffset = cluster.get(predCluster).max;
                return index(predCluster, predOffset);
            }
        }
    }

    public void delete(int x) {
        if (min == max) {
            min = -1;
            max = -1;
        } else if (universeSize <= 2) {
            if (x == 0) min = 1;
            else min = 0;
            max = min;
        } else {
            if (x == min) {
                int firstCluster = summary.min;
                x = index(firstCluster, cluster.get(firstCluster).min);
                min = x;
            }
            int clusterIdx = x / lowerSquareRoot(universeSize);
            int offset = x % lowerSquareRoot(universeSize);
            cluster.get(clusterIdx).delete(offset);
            if (cluster.get(clusterIdx).min == -1) {
                summary.delete(clusterIdx);
                if (x == max) {
                    int summaryMax = summary.max;
                    if (summaryMax == -1) {
                        max = min;
                    } else {
                        max = index(summaryMax, cluster.get(summaryMax).max);
                    }
                }
            } else if (x == max) {
                max = index(clusterIdx, cluster.get(clusterIdx).max);
            }
        }
    }
}

class Driver {
    public static void main(String[] args) {
        vEBTree tree = new vEBTree(16);
        tree.insert(2);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(9);
        tree.insert(10);
        tree.insert(12);
        tree.insert(13);
        tree.insert(15);

        /**
         * true
         * 9
         * 5
         * false
         */

        System.out.println(tree.contains(7)); // Output: true
        System.out.println(tree.successor(7)); // Output: 9
        System.out.println(tree.predecessor(7)); // Output: 5

        tree.delete(7);
        System.out.println(tree.contains(7)); // Output: false
    }
}