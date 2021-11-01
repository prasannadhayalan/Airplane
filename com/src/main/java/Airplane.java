import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Airplane {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter count of array : ");
        int count = scan.nextInt();

        int[][] inputArray =new int[count][2];
        for (int i = 0; i < count; ++i) {

            for (int j = 0; j < 2; ++j) {
                System.out.print("Enter  the data for "+ i +" Array : ");
                int col = scan.nextInt();
                inputArray[i][j] = col;
              }
        }

        System.out.print("Enter Customer Count : ");
        int customerCount = scan.nextInt();
        arrangeSeatsInPlane(inputArray,customerCount);
    }

    public static int[][][] arrangeSeatsInPlane(int[][] inputArray, int customerCount) {
        List<String> aisleSeatArray = new ArrayList<>();
        List<String> windowSeatArray = new ArrayList<>();
        List<String> middleSeatArray = new ArrayList<>();
        int initializedArray = findArrayNeedsToCreate(inputArray);
        int[][][] result = new int[initializedArray][initializedArray][initializedArray];
        diffrentiateSeats(inputArray, initializedArray, aisleSeatArray, windowSeatArray, middleSeatArray);
        occupySeats(result, aisleSeatArray, windowSeatArray, middleSeatArray, customerCount);
        printResult(result);
        return result;
    }

    private static int findArrayNeedsToCreate(int[][] inputArray) {
        int intArray = 0;
        int maxRows = 0;
        int maxColumn = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i][1] > maxRows) {
                maxRows = inputArray[i][1];
            }
            if (inputArray[i][0] > maxColumn) {
                maxColumn = inputArray[i][0];
            }
        }

        if (maxColumn >= inputArray.length && maxRows >= inputArray.length) {
            intArray = maxColumn;
        } else if (maxRows >= inputArray.length && maxColumn >= inputArray.length) {
            intArray = maxColumn;
        } else {
            intArray = inputArray.length;
        }
        return intArray;
    }

    private static void printResult(int[][][] result) {
        for (int[][] ints : result) {
            System.out.println();
            for (int[] anInt : ints) {
                System.out.print("         ");
                for (int i : anInt) {
                    if (i == 0) {
                        System.out.print("  ");
                        continue;
                    }
                    System.out.print(i + " ");
                }
            }
        }
    }

    private static void occupySeats(int[][][] result, List<String> aisleSeatArray, List<String> windowSeatArray,
          List<String> middleSeatArray, int customerCount) {
        int count = 1;
        for (String s : aisleSeatArray) {
            String[] array = s.split(",");
            int col = Integer.parseInt(array[0]);
            int row = Integer.parseInt(array[1]);
            int set = Integer.parseInt(array[2]);
            if (customerCount >= count) {
                result[set][col][row] = count++;
            } else {
                break;
            }
        }
        for (String s : windowSeatArray) {
            String[] array = s.split(",");
            int col = Integer.parseInt(array[0]);
            int row = Integer.parseInt(array[1]);
            int set = Integer.parseInt(array[2]);
            if (customerCount >= count) {
                result[set][col][row] = count++;
            } else {
                break;
            }
        }
        for (String s : middleSeatArray) {
            String[] array = s.split(",");
            int col = Integer.parseInt(array[0]);
            int row = Integer.parseInt(array[1]);
            int set = Integer.parseInt(array[2]);
            if (customerCount >= count) {
                result[set][col][row] = count++;
            } else {
                break;
            }
        }
    }

    private static void diffrentiateSeats(int[][] inputArray, int maxRows, List<String> aisleSeatArray,
          List<String> windowSeatArray, List<String> middleSeatArray) {
        for (int r = 0; r < maxRows; r++) {
            for (int i = 0; i < inputArray.length; i++) {
                boolean leftSet = false;
                boolean rightSet = false;
                boolean leftWindow = false;
                boolean rightWindow = false;
                boolean aisleSeat = false;
                boolean middleSeat = false;
                int seatColumns = inputArray[i][0];
                int seatRow = inputArray[i][1];

                if (seatRow <= r) {
                    continue;
                }
                if (i == 0) {
                    leftSet = true;
                }
                if (i == inputArray.length - 1) {
                    rightSet = true;
                }

                for (int c = 0; c < seatColumns; c++) {
                    if (seatColumns < c) {
                        continue;
                    }
                    if (leftSet) {
                        if (seatColumns > 2 && (c > 0 && c < seatColumns - 1)) {
                            middleSeat = true;
                        } else if (c == 0) {
                            leftWindow = true;
                        } else {
                            aisleSeat = true;
                        }
                    }

                    if (rightSet) {
                        if (seatColumns > 2 && (c > 0 && c < seatColumns - 1)) {
                            middleSeat = true;
                        } else if (c == seatColumns - 1) {
                            rightWindow = true;
                        } else {
                            aisleSeat = true;
                        }
                    }
                    if (!leftSet && !rightSet) {
                        if (seatColumns < 2) {
                            aisleSeat = true;
                        } else if (c > 0 && c < seatColumns - 1) {
                            middleSeat = true;
                        } else {
                            aisleSeat = true;
                        }
                    }

                    if (aisleSeat) {
                        aisleSeatArray.add(i + "," + c + "," + r);
                    }
                    if (leftWindow || rightWindow) {
                        windowSeatArray.add(i + "," + c + "," + r);
                    }
                    if (middleSeat) {
                        middleSeatArray.add(i + "," + c + "," + r);
                    }
                    leftWindow = false;
                    rightWindow = false;
                    aisleSeat = false;
                    middleSeat = false;

                }

            }
        }
    }
}