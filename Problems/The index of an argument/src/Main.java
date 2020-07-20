class Problem {
    public static void main(String[] args) {
        int b = -1;
        for(int i = 0;  i < args.length; i++) {
            if(args[i].equals("test")) {
                b = i;
            }
        }
        System.out.println(b);
    }
}