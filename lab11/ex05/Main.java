package lab11.ex05;

public class Main {

    public static void main(String[] args) {
        if (args.length <= 0) {
            System.err.println("ERROR! No arguments given\n");
            System.out.println(
                    """
                            Usage:
                               	java Main [-option] path
                            \tOption:
                            \t\t-r: Include the size of the directories inside""");
            System.exit(0);
        }

        if (args.length == 1) {
            new Visitor(args[0], false);

        } else if (args.length == 2) {
            if (args[0].equals("-r")) {
                new Visitor(args[1], true);
            } else {
                usage();
            }

        } else {
            usage();
        }

    }

    private static void usage() {
        System.err.println("ERROR!");
        System.out.println(
                """
                        Usage:
                        	java Main [-option] path
                        \tOption:
                        \t\t-r: Include the size of the directories inside""");
        System.exit(0);
    }

}