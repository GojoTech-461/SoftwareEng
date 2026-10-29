public class Client{
    public static void main1(){
        Program1 p = new Program1();
        System.out.println("I am main1");
        p.go();
    }

    public static void main2(){
        Program2 p = new Program2();
        System.out.println("I am main2");
        p.go();
    }

    public static void main3(){
        Program3 p = new Program3();
        System.out.println("I am main3");
        p.go();
    }

    public static void main(String[] args) {
        if(args.length > 0) {
            switch (args[0]) {
                case "1" :
                    main1();
                    break;
                case "2":
                    main2();
                    break;
                case "3":
                    main3();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
