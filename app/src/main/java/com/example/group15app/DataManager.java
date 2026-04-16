package com.example.group15app;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<Deal> getDeals() {
        List<Deal> deals = new ArrayList<>();
        deals.add(new Deal("10% Student Discount",  "Subway Limerick",      "10% OFF",        "Show your student ID at the counter to get 10% off any footlong or salad.",                   "Food",          "https://www.subway.com/en-IE",          "Sarsfield St, Limerick",         "V94 Y2X3"));
        deals.add(new Deal("Student Meal Deal",     "Domino's Pizza",       "20% OFF",        "Order online using code STUDENT20 at checkout. Valid on all pizzas.",                         "Food",          "https://www.dominos.ie",               "Childers Rd, Limerick",          "V94 A2C4"));
        deals.add(new Deal("Free Coffee Monday",    "Insomnia Coffee",      "FREE",           "Get a free small coffee every Monday with a valid student card.",                             "Coffee",        "https://www.insomnia.ie",              "O'Connell St, Limerick",         "V94 T2NX"));
        deals.add(new Deal("Gym Membership Deal",   "UL Sport Arena",       "30% OFF",        "Student rate on full gym membership. Valid with UL student ID only.",                         "Fitness",       "https://www.ulsport.ie",               "University of Limerick",         "V94 T9PX"));
        deals.add(new Deal("Laptop Student Price",  "Currys PC World",      "15% OFF",        "Bring your student ID for 15% off laptops and accessories in-store.",                         "Tech",          "https://www.currys.ie",                "Crescent Shopping Centre",       "V94 H2Y6"));
        deals.add(new Deal("Bus Pass Discount",     "Bus Eireann Limerick", "50% OFF",        "Student Leap Card gives 50% off all Bus Eireann routes.",                                     "Transport",     "https://www.buseireann.ie",             "Colbert Station, Limerick",      "V94 VR78"));
        deals.add(new Deal("Cinema Tuesday Deal",   "Omniplex Limerick",    "6 EUR Ticket",   "Every Tuesday students pay just 6 EUR. Show ID at the box office.",                          "Entertainment", "https://www.omniplex.ie",              "Jetland, Ennis Rd, Limerick",    "V94 X2K4"));
        deals.add(new Deal("Haircut Discount",      "Rush Hair Limerick",   "25% OFF",        "25% off all cuts and styles for students. Book online and mention STUDENT25.",                "Beauty",        "https://www.rushhair.com",             "Cruises St, Limerick",           "V94 N2F8"));
        deals.add(new Deal("Student Coffee Card",   "Costa Coffee",         "10% OFF",        "Flash your student card for 10% off every drink at any Costa Coffee branch.",                 "Coffee",        "https://www.costacoffee.ie",            "Dooradoyle Rd, Limerick",        "V94 K2P3"));
        deals.add(new Deal("Tech Student Bundle",   "Apple Education",      "Up to 200 EUR",  "Buy a Mac or iPad with Apple education pricing. Available online with college email.",         "Tech",          "https://www.apple.com/ie/education/",  "Patrick St, Cork",               "T12 AX2Y"));
        deals.add(new Deal("Student Eats Deal",     "Boojum Burrito",       "15% OFF",        "Get 15% off your burrito bowl with a valid student card at any Boojum location.",             "Food",          "https://www.boojummex.com",            "Thomas St, Limerick",            "V94 W2H5"));
        deals.add(new Deal("Fitness First Student", "PureGym Limerick",     "40% OFF",        "Monthly student membership at a discounted rate. No joining fee in September.",               "Fitness",       "https://www.puregym.com/ireland/",     "Parkway Shopping Centre",        "V94 R2T7"));
        return deals;
    }
}
