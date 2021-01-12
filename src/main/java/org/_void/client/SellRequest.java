package org._void.client;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SellRequest {
    public String external_id;
    public Receipt receipt;
    public Service service;
    public String timestamp = "01.02.17 13:45:00";

    public SellRequest(){
        Random rand = new Random();
        int n = rand.nextInt(1000) + 1000;
        this.external_id = String.format("2705291756185%s", n);
        this.receipt = new Receipt();
        this.receipt.client = new Client();
        this.receipt.company = new Company();
        this.receipt.items = new ArrayList<Item>();

        Item item = new Item();

        item.vat = new Vat();

        this.receipt.items.add(item);
        this.receipt.payments = new ArrayList<Payment>();
        this.receipt.payments.add(new Payment());

        this.receipt.vats = new ArrayList<Vats>();
        this.receipt.vats.add(new Vats());
        this.service = new Service();
    }

    private class Client {
        public String email ="kkt@kkt.ru";
    }

    private class Company {
        public String email ="chek@romashka.ru";
        public String sno = "osn";
        public String inn = "1234567891";
        public String payment_address = "http://magazin.ru/";
    }

    private class Receipt {
        public Client client;
        public Company company;
        public ArrayList<Item> items;
        public ArrayList<Payment> payments;
        public ArrayList<Vats> vats;
        public float total = (float) 400.00;
    }

    private class Service {
        public String callback_url = "http://testtest";
    }

    private class Payment {
        public int type = 1;
        public float sum = (float) 400.00;
    }

    private class Vat {
        public String type = "vat20";
    }

    private class Vats extends Vat {
        public float sum = (float) 45.76;
    }

    private class Item {
        public String name = "колбаса Клинский Брауншвейгская с/к в/с ";
        public float price = (float) 1000.00;
        public float quantity = (float) 0.3;
        public float sum = (float) 300.00;
        public String measurement_unit = "кг";
        public String payment_method = "full_payment";
        public String payment_object = "commodity";
        public Vat vat;
    }
}
