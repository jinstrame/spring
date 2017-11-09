package quoters;

import javax.annotation.PostConstruct;


@Profiling
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {


    @InjectRandomInt(min = 2, max = 12)
    private int repeat;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public TerminatorQuoter() {
        System.out.println();
        System.out.println("Phase 1");
        System.out.println(repeat);
    }


    @PostConstruct
    public void init() {
        System.out.println();
        System.out.println("Phase 2");
        System.out.println(repeat);

    }


    @PostProxy
    @Override
    public void sayQuote() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }

}