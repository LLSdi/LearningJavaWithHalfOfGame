package com.buytrainticket;

public class Test {
    public static void main(String[] args) {
        TicketOffice ticketOffice1 = new TicketOffice("窗口1");
        ticketOffice1.start();
        TicketOffice ticketOffice2 = new TicketOffice("窗口2");
        ticketOffice2.start();
        TicketOffice ticketOffice3 = new TicketOffice("窗口3");
        ticketOffice3.start();
    }
}
