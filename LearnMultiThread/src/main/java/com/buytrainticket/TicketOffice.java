package com.buytrainticket;

public class TicketOffice extends Thread{
    private static int ticketNumber = 10;


    public TicketOffice(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (ticketNumber > 0)
                System.out.println("我在" + this.getName() + "买到了票，" + "余票:" + (--ticketNumber));
        }
    }
}
