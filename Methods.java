package battleship;

public class Methods {
    static void initialise() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Main.arr[i][j] = "~";
                Main.dummy[i][j] = "~";
            }
        }
    }

    static void print() {
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            char c = (char) (65 + i);
            System.out.print("\n" + c + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(Main.arr[i][j] + " ");
            }
        }
        System.out.println();
    }

    static void print1() {
        System.out.print("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            char c = (char) (65 + i);
            System.out.print("\n" + c + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(Main.dummy[i][j] + " ");
            }
        }
        System.out.println();
    }

    static void error(int a) {
        System.out.print("\n Error! ");
        switch (a) {
            case 0:
                System.out.print("You placed it too close to another one. ");
                break;
            case 1:
                System.out.print("Wrong ship location! ");
                break;
            case 2:
                System.out.print("Wrong Length of the Destroyer");
                break;
            case 3:
                System.out.print("Wrong Length of the Submarine");
                break;
            case 4:
                System.out.print("Wrong Length of the Battleship");
                break;
            case 5:
                System.out.print("Wrong Length of the Aircraft Carrier");
                break;
        }
        System.out.println(" Try again: \n");
    }

    static Boolean check(int x, int y, int p, int q) {

        try {
            if (x == y) {
                for (int i = Math.min(p, q) - 1; i < Math.max(p, q); i++) {
                    if (Main.arr[y][i - 1].equals("O") || Main.arr[y][i + 1].equals("O") || Main.arr[y - 1][i + 1].equals("O") || Main.arr[y - 1][i - 1].equals("O") ||
                            Main.arr[y + 1][i + 1].equals("O") || Main.arr[y + 1][i - 1].equals("O") || Main.arr[y][i - 1].equals("O") || Main.arr[y][i + 1].equals("O")) {
                        return false;
                    }
                }
            } else if (p == q) {

                for (int i = Math.min(x, y); i <= Math.max(x, y); i++) {
                    if (Main.arr[i - 1][p - 1].equals("O") || Main.arr[i + 1][p - 1].equals("O") || Main.arr[i - 1][p - 1 - 1].equals("O") || Main.arr[i + 1][p - 1 - 1].equals("O") ||
                            Main.arr[i - 1][p - 1 + 1].equals("O") || Main.arr[i + 1][p - 1 + 1].equals("O") || Main.arr[i][p - 1 - 1].equals("O") || Main.arr[i][p - 1 + 1].equals("O")) {
                        return false;
                    }


                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return true;
    }

    static void verify(int a) {
        try {
            String[] str = Main.scanner.nextLine().toUpperCase().split(" ");
            if (str.length != 2) {
                System.out.println("Enter Inputs Correctly");
                verify(a);
                return;
            }
            char a1 = str[0].charAt(0);
            char a2 = str[1].charAt(0);
            String b1 = str[0].substring(1);
            String b2 = str[1].substring(1);
            int x = a1 - 64 - 1;
            int y = a2 - 64 - 1;
            int p = Integer.parseInt(b1);
            int q = Integer.parseInt(b2);
            if (y == x) {
                if ((Math.max(p, q)) - Math.min(q, p) + 1 == a) {
                    for (int i = Math.min(p, q) - 1; i < Math.max(p, q); i++) {
                        if (Main.arr[y][i] == "O") {
                            error(0);
                            verify(a);
                            return;

                        } else
                            Main.arr[y][i] = "O";
                    }
                } else {
                    error(a);
                    verify(a);
                }
            } else if (p == q) {
                if ((Math.max(x, y)) - Math.min(y, x) + 1 == a) {
                    if (check(x, y, p, q)) {
                        for (int i = Math.min(x, y); i <= Math.max(x, y); i++) {
                            if (Main.arr[i][p - 1] == "O") {
                                error(0);
                                verify(a);
                                return;
                            } else
                                Main.arr[i][p - 1] = "O";
                        }
                    } else {
                        error(0);
                        verify(a);
                    }
                } else {
                    error(a);
                    verify(a);
                }


            } else {
                System.out.println("\nError! Wrong ship location! Try again:");
                verify(a);
            }
        } catch (Exception e) {
            error(a);
            verify(a);
        }

    }

    static void check(int a, int b) {
        try {
            if (Main.arr[a][b + 1] == "O" || Main.arr[a][b - 1] == "O" || Main.arr[a + 1][b] == "O" || Main.arr[a - 1][b] == "O") {
                System.out.println("You hit a ship! Try again:");
            } else {
                System.out.println("You sank a ship! Specify a new target: ");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    static void hit() {
        String str = Main.scanner.next().toUpperCase();
        String regex = "[ABCDEFGHIJ][1234567890]{1,2}";
        String regex1 = "[ABCDEFGHIJ][10]{2}";
        if (!str.matches(regex)) {
            error(404);
            hit();
        }
        else {
            int a = str.charAt(0) - 65;
            int b;
            if (str.matches(regex1)) {
                b = 9;
            } else {
                b = str.charAt(1) - '0' - 1;
            }
            String c = Main.arr[a][b];
            Main.tries++;
            if (c == "~" || c == "M") {
                Main.arr[a][b] = "M";
                Main.dummy[a][b] = "M";

                print1();

                System.out.println("\nYou missed! Try again:");
            } else if (c == "X") {
                print1();
                check(a, b);

            } else if (c == "O") {
                Main.arr[a][b] = "X";
                Main.dummy[a][b] = "X";
                Main.counter++;
                print1();
                check(a, b);
                if (Main.counter == 17) {
                    System.out.println("You sank the last ship in " +Main.tries+" Moves. You won. Congratulations!");
                    System.exit(0);

                }

            }
        }

    }

    static void start() {
        initialise();

        print();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells)");
        verify(5);
        print();
        System.out.println("Enter the coordinates of the Battleship (4 cells)");
        verify(4);
        print();

        System.out.println("Enter the coordinates of the Submarine (3 cells)");
        verify(3);
        print();

        System.out.println("Enter the coordinates of the Cruiser (3 cells)");
        verify(3);
        print();

        System.out.println("Enter the coordinates of the Destroyer (2 cells)");
        verify(2);
        print();

        System.out.println("The game starts! ");
        print1();
        System.out.println("\nTake a shot\n");
        while (true)
            hit();


    }


}
