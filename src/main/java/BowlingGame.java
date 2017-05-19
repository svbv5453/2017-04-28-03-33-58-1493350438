public class BowlingGame {
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String MISS = "-";



    public int getBowlingScore(String bowlingCode) {


        int[][] bowlingArray = parserFrame(bowlingCode);
        int[][] bowlingResult = countScore(bowlingArray);
        print(bowlingArray);
        int score = bowlingResult[3][10];
        return score;
    }

    private int[][] countScore(int[][] bowlingArray) {


            for (int j = 0; j < bowlingArray[0].length - 1; j++) {
                if (bowlingArray[0][j] == 10) {
                    if (bowlingArray[0][j + 1] == 10) {
                        if (j == 9) {
                            bowlingArray[2][j] = 10 * 2 + bowlingArray[1][j + 1];
                        } else {
                            bowlingArray[2][j] = 10 * 2 + bowlingArray[0][j + 2];
                        }

                    } else {
                        bowlingArray[2][j] = 10 + bowlingArray[0][j + 1] + bowlingArray[1][j + 1];
                    }


                } else if (bowlingArray[0][j] + bowlingArray[1][j] == 10) {
                    bowlingArray[2][j] = bowlingArray[0][j] + bowlingArray[1][j] + bowlingArray[0][j + 1];
                } else {
                    bowlingArray[2][j] = bowlingArray[0][j] + bowlingArray[1][j];
                }

                    bowlingArray[3][j + 1] = bowlingArray[2][j] + bowlingArray[3][j];


            }


        return bowlingArray;
    }

    private int[][] parserFrame(String bowlingCode) {

        String[] grids = bowlingCode.split("\\|");
        int[][] bowlingArray = putIn2Array(grids);
        return bowlingArray;
    }

    private int[][] putIn2Array(String[] grids) {
        int[][] bowlingArray = new int[4][11];
        int j = -1;
        for (int i = 0; i < grids.length; i++) {
            if (grids[i].length() != 0) {
                j++;

            }

            if (grids[i].length() == 1) {
                if (grids[i].equals(STRIKE)) {
                    bowlingArray[0][j] = 10;
                } else if (grids[i].equals(MISS)) {
                    bowlingArray[0][j] = 0;
                } else {
                    bowlingArray[0][j] = Integer.parseInt(grids[i]);
                }


            } else if(grids[i].length() == 2){

                String firstBowling = String.valueOf(grids[i].charAt(0));
                String secondBowling = String.valueOf(grids[i].charAt(1));
                if (firstBowling.equals(MISS)) {
                    bowlingArray[0][j] = 0;
                } else if (firstBowling.equals(STRIKE)) {
                    bowlingArray[0][j] = 10;
                } else {
                    bowlingArray[0][j] = Integer.parseInt(firstBowling);
                }
                if (secondBowling.equals(MISS)) {
                    bowlingArray[1][j] = 0;
                } else if (secondBowling.equals(SPARE)) {
                    bowlingArray[1][j] = 10 - bowlingArray[0][j];
                } else if (secondBowling.equals(STRIKE)) {
                    bowlingArray[1][j] = 10;
                } else {
                    bowlingArray[1][j] = Integer.parseInt(secondBowling);
                }

            }
        }


        return bowlingArray;
    }

    private void print(int[][] bowlingArray) {
        for (int i = 0; i < bowlingArray.length; i++) {
            for (int j = 0; j < bowlingArray[0].length; j++) {
                System.out.print(bowlingArray[i][j] + "  ");
            }
            System.out.println();

        }
    }



}
